package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class SortController {

    private int[] a;

    @PostMapping("/sort")
    public List<int[]> sort(@RequestBody int[] nums) {
        int[] arr = Arrays.copyOf(nums, nums.length);

        List<int[]> steps = new ArrayList<>();
        steps.add(Arrays.copyOf(arr, arr.length));

        mergeSort(arr, 0, arr.length - 1, steps);

        return steps;
    }

    private void mergeSort(int[] arr, int left, int right, List<int[]> steps) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid, steps);
        mergeSort(arr, mid + 1, right, steps);
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        System.arraycopy(temp, 0, arr, left, temp.length);
        steps.add(Arrays.copyOf(arr, arr.length));
    }
}