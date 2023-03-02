package com.muecode.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Base Class
 */
public final class MissingInteger {
  private MissingInteger() {}

  /**
   * Test Cases
   * 
   * @param args The arguments of the program.
   */
  public static void main(String[] args) {
    System.out.println(solution(new int[] {1, 2, 3}));
    System.out.println(solution(new int[] {-3, -1, 0, 0, 2}));
    System.out.println(solution(new int[] {-3, -1, 0, 0, 1}));
    System.out.println(solution(new int[] {-1, -3}));
    System.out.println(solution(new int[] {1, 0}));
  }

  /**
   * Missing Integer. Find the first positive integer from a given array of integers.
   * 
   * @param A Given an int array
   * @return the first positive integer
   */
  public static int solution(int[] A) {

    List<Integer> integers = Arrays.stream(A) //
        .boxed() //
        .sorted() //
        .collect(Collectors.toList());

    Integer prev = null;
    int count = 1;
    System.out.println("start");
    for (Integer curr : integers) {
      // System.out.println(prev + " " + curr);
      if (prev != null && prev <= 0) {
        if (curr > 1) {
          return 1;
        }
      } else if (prev != null && prev >= 0) {
        if (curr > prev + 1) {
          return prev + 1;
        }
      }
      if (count == integers.size()) {
        if (curr <= 0) {
          return 1;
        } else { // == 1
          return curr + 1;
        }
      }
      prev = curr;
      count += 1;
    }
    return -1;
  }
}
