package com.hm707.sort;

import com.hm707.utils.SortUtil;

/**
 * 最简单，最没用
 * 时间复杂度 O(n^2) 最好时间复杂度 O(n^2) 不稳定
 */
public class SelectionSort {

  /**
   * 1. 找到最小的和第一个位置交换
   * 2. 找次小的和第二个位置交换
   *
   * --
   *
   * 5 4 2 1 3 ->
   * 1 4 2 5 3
   * 1 2 4 5 3 ....
   *
   */
  public static void sort(int[] arr) {

    for (int i = 0; i < arr.length - 1; i++) {
      int minPos = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[minPos] > arr[j]) {
          minPos = j;
        }
      }
      SortUtil.swap(arr, i, minPos);
    }

  }

  public static void main(String[] args) {

    int[] arr = {5, 4, 2, 1, 3};
    SortUtil.printArray(arr);
    sort(arr);
    SortUtil.printArray(arr);
  }


}
