// SPDX-License-Identifier: MIT
pragma solidity ^0.8.17;

contract TravelInsuranceFactory {
    address public manager; // our company's wallet address
    address[] private deployedInsurances; // list of deployed insurance contracts
    mapping(string => address[]) deployedInsuranceMap; // flight uid => insurance contract addresses, uid = flight number + '_' + departure time (e.g. SQ123_1678621644)
    mapping(address => address[]) deployedInsuranceByInsuredAddress; // insured address => insurance contract addresses

    // Templetes
    uint256 lastTemplateId; // id of the last insurance template, starts from 0
    mapping(uint256 => InsuranceTemplate) insuranceTemplateMap; // id => InsuranceTemplate
    InsuranceTemplate[] insuranceTemplates; // list of insurance templates

    struct InsuranceTemplate {
        uint256 id; // id of the insurance template
        string name; // name of the insurance template
        uint256 premium; // price of the insurance
        uint256 payoutAmount; // amount to be paid out to the purchaser once claimed
    }

    struct MyInsurances {
        TravelInsurance.TravelInsuranceData[] insurances;
        address[] addresses;
    }

    constructor() {
        manager = msg.sender;
    }

    function createInsuranceTemplate(
        string memory _name,
        uint256 _premium,
        uint256 _payoutAmount
        ) public onlyManager {

        uint256 newId = lastTemplateId++;
        InsuranceTemplate memory newTemplate = InsuranceTemplate({
            id: newId, // id of the insurance template, starts from 0, auto increment
            name: _name,
            premium: _premium,
            payoutAmount: _payoutAmount
        });
        insuranceTemplateMap[newId] = newTemplate;
        insuranceTemplates.push(newTemplate);
    }

    function purchaseTravelInsurance(
        uint256 _templateId,
        string memory _flightNumber, // flight number (e.g. SQ123)
        string memory _departureTime // unix timestamp (e.g. 1678621644)
        ) public payable {
        // create new contract
        // add to deployedInsurance

        InsuranceTemplate memory template = insuranceTemplateMap[_templateId];
        require(template.premium > 0, "Template does not exist"); // template must exist

        require(msg.value == template.premium, "Insured must pay the premium");
        payable(manager).transfer(template.premium);

        //SQ123_1678621644
        TravelInsurance newInsurance = new TravelInsurance(
            template.id,
            template.name,
            _flightNumber,
            _departureTime,
            manager,
            msg.sender,
            template.premium,
            template.payoutAmount
        );
        string memory flightUid = getFlightUid(_flightNumber, _departureTime);
        deployedInsurances.push(address(newInsurance));
        deployedInsuranceMap[flightUid].push(address(newInsurance));
        deployedInsuranceByInsuredAddress[msg.sender].push(address(newInsurance));
    }

    function getDeployedInsurances() public onlyManager view returns (address[] memory) {
        return deployedInsurances;
    }

    function getDeployedInsurancesByFlight(string memory _flightNumber, string memory _departureTime) public view returns (address[] memory) {
        string memory flightUid = getFlightUid(_flightNumber, _departureTime);
        return deployedInsuranceMap[flightUid];
    }

    function getMyInsurances() public view returns (MyInsurances memory) {
        TravelInsurance.TravelInsuranceData[] memory myInsurances = new TravelInsurance.TravelInsuranceData[](deployedInsuranceByInsuredAddress[msg.sender].length);
        for (uint256 i = 0; i < deployedInsuranceByInsuredAddress[msg.sender].length; i++) {
            TravelInsurance.TravelInsuranceData memory insuranceData = TravelInsurance(deployedInsuranceByInsuredAddress[msg.sender][i]).getData();
            myInsurances[i] = insuranceData;
        }
        address[] memory addresses = deployedInsuranceByInsuredAddress[msg.sender];
        return MyInsurances(myInsurances, addresses);
    }

    function getInsuranceTemplates() public view returns (InsuranceTemplate[] memory) {
        return insuranceTemplates;
    }

    modifier onlyManager() {
        require(msg.sender == manager);
        _;
    }

    function getFlightUid(string memory a, string memory b) private pure returns (string memory) {
        bytes memory bytesA = bytes(a);
        bytes memory bytesB = bytes(b);
        bytes memory result = abi.encodePacked(bytesA, bytes("_"), bytesB);
        return string(result);
    }
}


contract TravelInsurance {

    TravelInsuranceData public data;

    event ClaimEvent(address indexed from, TravelInsurance.TravelInsuranceData data);

    struct TravelInsuranceData {
        uint256 templateId; // id of the insurance template
        string templateName; // name of the insurance template
        string flightNumber; // flight number (e.g. SQ123)
        string departureTime; // unix timestamp (e.g. 1678621644)
        address insurer; // our company's wallet address
        address insured; // the person who buys the insurance
        uint256 premium; // price of the insurance
        uint256 payoutAmount; // amount to be paid out to the purchaser once claimed
        bool isActive; // whether the insurance is active
        bool isPaidOut; // whether the insurance has been paid out
    }

    constructor(
        uint256 _templateId,
        string memory _templateName,
        string memory _flightNumber, // flight number (e.g. SQ123)
        string memory _departureTime, // unix timestamp (e.g. 1678621644)
        address _insurer,
        address _insured,
        uint256 _premium,
        uint256 _payoutAmount
        ) {
        data.templateId = _templateId;
        data.templateName = _templateName;
        data.flightNumber = _flightNumber;
        data.departureTime = _departureTime;
        data.insurer = _insurer;
        data.insured = _insured;
        data.premium = _premium;
        data.payoutAmount = _payoutAmount;
        data.isActive = true;
        data.isPaidOut = false;
    }

    // function payPremium() public payable {
    //     require(msg.value == premium); // Pay the premium of the insurance
    //     isActive = true; // onle when the premium is paid, the insurance is active
    // }

    function cancelInsurance() public onlyInsurer payable {
        payable(data.insured).transfer(data.premium); // refund the premium to the insured
        data.isActive = false;
    }

    function claimInsurance() public onlyInsurer payable {
        require(data.isActive); // can only claim when the insurance is active
        require(!data.isPaidOut); // can only claim once

        payable(data.insured).transfer(data.payoutAmount);
        data.isPaidOut = true;

        emit ClaimEvent(msg.sender, data);
    }

    modifier onlyInsurer() {
        require(msg.sender == data.insurer);
        _;
    }

    function getData() public view returns (
        TravelInsuranceData memory
    ) {
        return data;
    }
}