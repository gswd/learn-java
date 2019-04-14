package com.hm707.utils;

import java.util.Arrays;

public class SortUtil {

  public static void printArray(int[] arr) {
    Arrays.stream(arr).forEach(e -> System.out.print(e + "  "));
    System.out.println();
  }

  public static <T> void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

}
