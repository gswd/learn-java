package com.hm707.sort;

import com.hm707.utils.SortUtil;

/**
 * 归并排序
 * 用于对象排序(一般要求稳定),java内部使用改进的归并排序(TIM Sort)为对象排序（可以认为是多路归并）
 *
 * 时间复杂度:O(N*LogN) 空间复杂度 O(N) 稳定
 *
 *
 */
public class MergeSort {
  /**
   *
   * @param left 数组开始位置
   * @param right 数组结束位置
   */
  public static void sort(int[] arr, int left, int right) {
    if (left == right) {
      return;
    }
    //分成两部分
    int mid = left + (right - left) / 2;
    //System.out.println("[left sort] left : " + left + " right : " + right + " mid : " + mid);
    //左边排序
    sort(arr, left, mid);
    //System.out.println("--> [left sort] done. ");
    //右边排序
    //System.out.println("[right sort] left : " + left + " right : " + right + " mid : " + mid);
    sort(arr, mid + 1, right);
    //System.out.println("--> [right sort] done. ");
    //归并
    merge(arr, left, mid + 1, right);
  }

  /**
   *
   * @param lp left pointer
   * @param rp right pointer
   * @param rbp right bound pointer
   */
  static void merge(int[] arr, int lp, int rp, int rbp) {
    int mid = rp - 1;
    int[] temp = new int[rbp - lp + 1];

    int i = lp;
    int j = rp;
    int k = 0;
    while (i <= mid && j <= rbp) {
      if(arr[i] <= arr[j]) {
        temp[k++] = arr[i++];
      } else {
        temp[k++] = arr[j++];
      }
    }

    while (i <= mid) {
      temp[k++] = arr[i++];
    }
    while (j <= rbp) {
      temp[k++] = arr[j++];
    }

    for (int m = 0; m < temp.length; m++) {
      arr[lp++] = temp[m];
    }

  }

  public static void main(String[] args) {

    int[] arr = {1, 4, 7, 8, 3, 6, 9};
    SortUtil.printArray(arr);
    sort(arr, 0, arr.length - 1);
    SortUtil.printArray(arr);
  }

}
