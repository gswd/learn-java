package com.hm707.sort;

import com.hm707.utils.SortUtil;

/**
 * 计数排序，是桶排序中的特殊情况，适合 问题规模大，但样本数量少。
 *
 * 比如：1. 大型企业数万名员工的年龄排序 2. 如何快速得知高考名次.
 *
 * 设计思想：
 * 1. 分配一个与样本数量一样长的数组K。
 * 2. 将待排序数组N中的数作为K数组的下标，在K数组对应值上累加.
 * 3. 分配一个新数组N1，使用K数组中的计数一次填入数组N1中
 */
public class CountSort {

  public static int[] sort(int[] arr) {

    int max = arr[0], min = arr[0];
    for(int i : arr){
      max=Math.max(max,i);
      min=Math.min(min,i);
    }
    System.out.println("max:"+max+"  min:"+min);
    int k = max - min + 1;
    System.out.println("count array len："+k);

    int[] bucket = new int[k];

    for (int i = 0; i < arr.length; i++) {
      bucket[arr[i] - min]++;
    }

    System.out.print("bucket --> ");
    SortUtil.printArray(bucket);

    int[] orderedArr = new int[arr.length];

    for (int i = 0, j = 0; i < bucket.length; i++) {
      int count = bucket[i];
      for (; count > 0; count--) {
        orderedArr[j++] = i + min;
      }
    }
    return orderedArr;
  }

  public static void main(String[] args) {
    int[] arr = {2, 9, 4, 3, 3, 3, 3, 3, 9, 8, 8, 4};
    SortUtil.printArray(arr);
    int[] orderedArr = sort(arr);
    SortUtil.printArray(orderedArr);

  }
}
