package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class SortController {

    @PostMapping("/sort")
    public int[] sort(@RequestBody int[] nums) {
        Arrays.sort(nums);
        return nums;
    }
}