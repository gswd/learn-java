package com.hm707.sort;

import com.hm707.utils.SortUtil;

/**
 * 对于基本有序的数组最好用
 * 时间复杂度O(n^2) 最好时间复杂度O(n) 稳定
 */
public class InsertionSort {

  /**
   * 9 6 1 3 5
   */
  public static void sort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      for (int j = i; j > 0; j--) {
        if (arr[j] < arr[j - 1]) {
          SortUtil.swap(arr, j, j - 1);
        } else {
          break;
        }
      }
    }
  }
  public static void sort2(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      int p = i;
      for (int j = i; j > 0; j--) {
        if (arr[i] < arr[j - 1]) {
          p = j - 1;
        } else {
          break;
        }
      }

      //移位
      int temp = arr[i];
      int m = i;
      for (; m > p; m--) {
        SortUtil.swap(arr, m, m -1);
      }
      arr[p] = temp;

    }
  }

  public static void main(String[] args) {

    int[] arr = {9, 6, 1, 3, 5};
    SortUtil.printArray(arr);
    sort2(arr);
    SortUtil.printArray(arr);
  }

}
