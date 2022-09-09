package com.lzy.security;

/**
 * @Author: zhanyang
 * @Date: 2022/8/31 16:38
 */
public class Solution001 {
    /**
     * 排序
     *
     * @return arr
     */
    public static void main(String[] args) {
        //init param
        int[] arr = {1, 2, 3, 5, 4, 2, 7, 2};
        //冒泡排序
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
