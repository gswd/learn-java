package com.hm707.sort;

import com.hm707.utils.SortUtil;

/**
 * 时间复杂度O(N*LogN) 最差情况是O(N^2) 当整个数组有序时会出现最会情况
 *
 * 为避免最坏情况发生: 1. 取轴时可以随机取一个，然后放到最后 2. 先检查数组是否有顺序，如果有序可以采用归并排序，或者插入排序
 *
 * 空间复杂度O(logN)
 */
public class QuickSort {

  /**
   * 单轴(pivot)快排
   */
  public static void sort(int[] arr, int leftBound, int rightBound) {

    if(leftBound >= rightBound) return;
    int mid = partition(arr, leftBound, rightBound);
    //System.out.println("------- mid : " + mid);
    sort(arr, leftBound, mid-1);
    sort(arr, mid + 1, rightBound);

  }

  /**
   *
   * @return 返回轴的位置
   */
  private static int partition(int[] arr, int leftBound, int rightBound) {

    int pivot = arr[rightBound];
    int left = leftBound;
    int right = rightBound - 1;

    while (left <= right) {//没有等于时，之后两个数时不能进入到循环中。
      while (left <= right && arr[left] <= pivot) {//当left==right时需要left指针继续向前一个才能找到比pivot大的值
        left++;
      }
      while (left <= right && arr[right] > pivot) {
        right--;
      }

      //System.out.println("[swap before] left : " + left + " right : " + right + " rightBound : " + rightBound);
      if (left < right) {
        SortUtil.swap(arr, left, right);
      }
      //System.out.print("[swap after] ");
      //SortUtil.printArray(arr);
    }

    SortUtil.swap(arr, left, rightBound);//先移动左指针，所以左指针先找到大的值。

    //System.out.print("[swap pivot] ");
    //SortUtil.printArray(arr);

    return left;
  }

  public static void main(String[] args) {

    int[] arr = {7, 3, 2, 6, 8, 1, 9, 5, 4, 10};
    SortUtil.printArray(arr);
    sort(arr, 0, arr.length - 1);
    SortUtil.printArray(arr);
  }
}
