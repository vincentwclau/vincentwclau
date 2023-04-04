package com.muecode.leetcode.linkedlist;

public class AddTwoNumber2 {
  /**
   * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode()
   * {} ListNode(int val) { this.val = val; } ListNode(int val, ListNode next) { this.val = val;
   * this.next = next; } }
   */

  public static void main(String[] args) {
    // 9
    // [1,9,9,9,9,9,9,9,9,9]
    // [0,0,0,0,0,0,0,0,0,0,1]
    ListNode result = addTwoNumbers(new ListNode(8, new ListNode(7)), //
        new ListNode(8, new ListNode(9, new ListNode(9))));

    System.out.println(result.val);
    System.out.println(result.next.val);
    System.out.println(result.next.next.val);
    System.out.println(result.next.next.next.val);
    // System.out.println(result.next.next.next.val);
    // System.out.println("635".charAt("635".length() - 1));
  }

  public static ListNode addTwoNumbers(ListNode node1, ListNode node2) {
    // 87
    // 642
    // 423
    int nextDigit = 0;
    int currDigit = 0;
    int sum = node1.val + node2.val;
    System.out.println("sum=" + sum);

    if (sum >= 10) {
      nextDigit = Character.getNumericValue(String.valueOf(sum).charAt(0));
      currDigit = Character.getNumericValue(String.valueOf(sum).charAt(1));
    } else {
      currDigit = Character.getNumericValue(String.valueOf(sum).charAt(0));
      nextDigit = 0;
    }
    node1 = node1.next;
    node2 = node2.next;
    ListNode node = new ListNode(currDigit);
    ListNode headNode = node;

    int node1Value = 0;
    int node2Value = 0;
    for (int i = 1; i < 99; i++) {
      if (node1 != null || node2 != null) {
        node1Value = node1 == null ? 0 : node1.val;
        node2Value = node2 == null ? 0 : node2.val;
        sum = node1Value + node2Value + nextDigit;
        System.out.println("node1Value=" + node1Value);
        System.out.println("node2Value=" + node2Value);
        System.out.println("nextDigit=" + nextDigit);
        System.out.println("sum=" + sum);
        if (sum >= 10) {
          nextDigit = Character.getNumericValue(String.valueOf(sum).charAt(0));
          currDigit = Character.getNumericValue(String.valueOf(sum).charAt(1));
        } else {
          currDigit = Character.getNumericValue(String.valueOf(sum).charAt(0));
          nextDigit = 0;
        }
        node.next = new ListNode(currDigit);
        // System.out.println(node.next.val);
        node = node.next;
        node1 = node1 == null ? null : node1.next;
        node2 = node2 == null ? null : node2.next;

      }
    }
    if (nextDigit > 0)
      node.next = new ListNode(nextDigit);
    return headNode;
  }

}
