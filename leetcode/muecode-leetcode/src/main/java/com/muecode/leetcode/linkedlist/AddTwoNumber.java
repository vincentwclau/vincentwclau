package com.muecode.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class AddTwoNumber {
  /**
   * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode()
   * {} ListNode(int val) { this.val = val; } ListNode(int val, ListNode next) { this.val = val;
   * this.next = next; } }
   */

  public static void main(String[] args) {
    // 9
    // [1,9,9,9,9,9,9,9,9,9]
    // [0,0,0,0,0,0,0,0,0,0,1]
    int a = convertInteger(new ListNode(9));
    int b = convertInteger(new ListNode(1, new ListNode(9, new ListNode(9))));
    int sum = a + b;
    System.out.println(sum);
    ListNode result = convertListNode(1000);
    System.out.println(result.val);
    System.out.println(result.next.val);
    System.out.println(result.next.next.val);
    System.out.println(result.next.next.next.val);
    // System.out.println("635".charAt("635".length() - 1));
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int sum = convertInteger(l1) + convertInteger(l2);
    return convertListNode(sum);
  }

  public static ListNode convertListNode(Integer integer) {
    // 342 -> 2, 4, 3
    String str = String.valueOf(integer);
    int value = Character.getNumericValue(str.charAt(str.length() - 1));
    ListNode node = new ListNode(value);
    // System.out.println("node.val=" + node.val);
    if (str.length() == 1)
      return node;

    ListNode headnode = node;
    // System.out.println(headnode.val);
    for (int index = str.length() - 2; index >= 0; index--) {
      value = Character.getNumericValue(str.charAt(index));
      node.next = new ListNode(value);
      // System.out.println("node.val=" + node.val);
      node = node.next;
    }
    return headnode;
  }

  public static int convertInteger(ListNode node) {
    // 2, 4, 3 -> 342
    List<Integer> integers = new ArrayList<>();
    ListNode copyNode = node;

    for (int i = 0; i < 100; i++) {
      integers.add(copyNode.val);
      if (copyNode.next == null)
        break;
      copyNode = copyNode.next;
    }
    int result = 0;
    int exponent = 0;
    for (Integer i : integers) {
      result += i * Math.pow(10, exponent);
      exponent++;
    }
    return result;
  }


}
