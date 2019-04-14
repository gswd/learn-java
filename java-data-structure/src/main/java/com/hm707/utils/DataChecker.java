package com.hm707.utils;

import java.util.Arrays;
import java.util.Random;

import com.hm707.sort.InsertionSort;
import com.hm707.sort.MergeSort;
import com.hm707.sort.QuickSort;
import com.hm707.sort.SelectionSort;
import com.hm707.sort.ShellSort;

public class DataChecker {

  public static void main(String[] args) {
    check();
  }

  public static boolean check() {
    int[] arr = generateRandomArray();
    int[] arr2 = new int[arr.length];
    System.arraycopy(arr, 0, arr2, 0, arr.length);

    Arrays.sort(arr);
    //sort
    //SelectionSort.sort(arr2);
    //InsertionSort.sort2(arr2);
    //ShellSort.sort(arr2);
    //MergeSort.sort(arr2, 0, arr2.length - 1);
    QuickSort.sort(arr2, 0, arr2.length - 1);
    boolean same = true;
    for (int i = 0; i < arr.length; i++) {
      if(arr[i] != arr2[i]){
        same = false;
      }
    }
    System.out.println(same ? "Right!" : "Wrong");
    return same;

  }


  private static int[] generateRandomArray() {
    Random random = new Random();

    int[] arr = new int[10000];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = random.nextInt();
    }

    return arr;
  }
}
