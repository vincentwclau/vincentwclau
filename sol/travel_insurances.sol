// SPDX-License-Identifier: MIT
pragma solidity ^0.8.19;

contract TravelInsuranceFactory {
    address public manager; // our company's wallet address
    address[] public deployedInsurances; // list of deployed insurance contracts
    mapping(string => address[]) deployedInsuranceMap; // flight uid => insurance contract addresses, uid = flight number + '_' + departure time (e.g. SQ123_1678621644)
    mapping(string => InsuranceTemplate) insuranceTemplates; // name => InsuranceTemplate
    string[] public insuranceTemplateNames; // list of insurance template names

    struct InsuranceTemplate {
        string name; // name of the insurance template
        uint256 premium; // price of the insurance
        uint256 payoutAmount; // amount to be paid out to the purchaser once claimed
    }

    constructor() {
        manager = msg.sender;
    }

    function createInsuranceTemplate(
        string memory _name,
        uint256 _premium,
        uint256 _payoutAmount
        ) public onlyManager {

        InsuranceTemplate memory newTemplate = InsuranceTemplate({
            name: _name,
            premium: _premium,
            payoutAmount: _payoutAmount
        });
        insuranceTemplates[_name] = newTemplate;
        insuranceTemplateNames.push(_name);
    }

    function createTravelInsurance(
        string memory _templateName,
        string memory _flightNumber, // flight number (e.g. SQ123)
        string memory _departureTime // unix timestamp (e.g. 1678621644)
        ) public payable {
        // create new contract
        // add to deployedInsurance

        InsuranceTemplate memory template = insuranceTemplates[_templateName];
        require(template.premium > 0, "Template does not exist"); // template must exist

        require(msg.value == template.premium, "Insured must pay the premium");
        payable(manager).transfer(template.premium);

        //SQ123_1678621644
        TravelInsurance newInsurance = new TravelInsurance(
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
    }

    function getDeployedInsurances() public view returns (address[] memory) {
        return deployedInsurances;
    }

    function getInsuranceTemplateNames() public view returns (string[] memory) {
        return insuranceTemplateNames;
    }

    function getInsuranceTemplate(string memory _name) public view returns (
        string memory _templateName,
        uint256 _premium,
        uint256 _payoutAmount
    ) {
        InsuranceTemplate memory template = insuranceTemplates[_name];
        return (
            template.name,
            template.premium,
            template.payoutAmount
        );
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
    string public templateName; // name of the insurance template
    string public flightNumber; // flight number (e.g. SQ123)
    string public departureTime; // unix timestamp (e.g. 1678621644)
    address public insurer; // our company's wallet address
    address public insured; // the person who buys the insurance
    uint256 public premium; // price of the insurance
    uint256 public payoutAmount; // amount to be paid out to the purchaser once claimed
    bool public isActive; // whether the insurance is active
    bool public isPaidOut; // whether the insurance has been paid out

    constructor(
        string memory _templateName,
        string memory _flightNumber, // flight number (e.g. SQ123)
        string memory _departureTime, // unix timestamp (e.g. 1678621644)
        address _insurer,
        address _insured,
        uint256 _premium,
        uint256 _payoutAmount
        ) {
        templateName = _templateName;
        flightNumber = _flightNumber;
        departureTime = _departureTime;
        insurer = _insurer;
        insured = _insured;
        premium = _premium;
        payoutAmount = _payoutAmount;
        isActive = true;
        isPaidOut = false;
    }

    // function payPremium() public payable {
    //     require(msg.value == premium); // Pay the premium of the insurance
    //     isActive = true; // onle when the premium is paid, the insurance is active
    // }

    function cancelInsurance() public onlyInsurer payable {
        payable(insured).transfer(premium); // refund the premium to the insured
        isActive = false;
    }

    function claimInsurance() public onlyInsurer payable {
        require(isActive); // can only claim when the insurance is active
        require(!isPaidOut); // can only claim once

        payable(insured).transfer(payoutAmount);
        isPaidOut = true;
    }

    modifier onlyInsurer() {
        require(msg.sender == insurer);
        _;
    }

    function getDetails() public view returns (
        address _insurer,
        address _insured,
        uint256 _premium,
        uint256 _payoutAmount,
        bool _isActive,
        bool _isPaidOut
    ) {
        return (
            insurer,
            insured,
            premium,
            payoutAmount,
            isActive,
            isPaidOut
        );
    }
}