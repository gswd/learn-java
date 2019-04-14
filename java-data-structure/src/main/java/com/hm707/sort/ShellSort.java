package com.hm707.sort;

import com.hm707.utils.SortUtil;

/**
 * 升级版的插入排序（不太重要），时间O(n^1.3)  不稳定
 *
 * 用于中型序列的排序
 *
 * 1. 取一个间隔(gap)
 * 2. 将每一个数按照gap做一次插入排序。
 * 3. 缩小间隔重复步骤2.
 * 4. 直到gap的值为1.
 *
 * 每次按照间隔排序后，会使其大致有序。
 *
 * 效率高的原因：间隔大时->移动的次数少   间隔小时->移动的距离短
 *
 */
public class ShellSort {
  /**
   * 使用Knuth序列，希尔排序效率比较高
   * h = 1
   * h = 3*h + 1
   */
  public static void sort(int[] arr) {
    //找最大间隔
    int h = 1;
    while (h <= arr.length / 3) {
      h = 3 * h + 1;
    }

    for (int gap = h; gap > 0; gap = (gap - 1) / 3) {
      insertionSort(arr, gap);
      //SortUtil.printArray(arr);
    }
  }

  private static void insertionSort(int[] arr, int gap) {
    for (int i = gap; i < arr.length; i += gap) {
      for (int j = i; j > 0; j -= gap) {
        if (arr[j] < arr[j - gap]) {
          SortUtil.swap(arr, j, j - gap);
        } else {
          break;
        }
      }
    }
  }
  public static void main(String[] args) {

    int[] arr = {9, 6, 11, 3, 5, 12, 8, 7, 10, 15, 14, 4, 1, 13, 2};
    SortUtil.printArray(arr);
    sort(arr);
    SortUtil.printArray(arr);
  }
}
