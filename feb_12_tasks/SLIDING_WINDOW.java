package feb_12_tasks;
import java.util.*;

public class SLIDING_WINDOW {
	public static void main(String[] args) {
		
		//Q1
//		You are given an integer array nums consisting of n elements, and an integer k.
//
//		Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.
//
//		 
//
//		Example 1:
//
//		Input: nums = [1,12,-5,-6,50,3], k = 4
//		Output: 12.75000
//		Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
//		Example 2:
//
//		Input: nums = [5], k = 1
//		Output: 5.00000
		int[] nums = {1,12,-5,-6,50,3};
		int k = 4;
		double total = 0.0;
        int start = 0;
        int count = 0;
        double avg = -Double.MAX_VALUE;

        for (int end = 0; end < nums.length; end++) {
            total += nums[end];
            count++;

            if (count > k) {
                total -= nums[start];
                start++;
                count--;
            }

            if (count == k) {
                avg = Math.max(avg, total / k);
            }
        }
//        return avg;
        
//        Q2
//        1652. Defuse the Bomb
//        Solved
//        Easy
//        Topics
//        premium lock icon
//        Companies
//        Hint
//        You have a bomb to defuse, and your time is running out! Your informer will provide you with a circular array code of length of n and a key k.
//
//        To decrypt the code, you must replace every number. All the numbers are replaced simultaneously.
//
//        If k > 0, replace the ith number with the sum of the next k numbers.
//        If k < 0, replace the ith number with the sum of the previous k numbers.
//        If k == 0, replace the ith number with 0.
//        As code is circular, the next element of code[n-1] is code[0], and the previous element of code[0] is code[n-1].
//
//        Given the circular array code and an integer key k, return the decrypted code to defuse the bomb!
//
//         
//
//        Example 1:
//
//        Input: code = [5,7,1,4], k = 3
//        Output: [12,10,16,13]
//        Explanation: Each number is replaced by the sum of the next 3 numbers. The decrypted code is [7+1+4, 1+4+5, 4+5+7, 5+7+1]. Notice that the numbers wrap around.
        
        int[] code = {5, 7, 1, 4};
        int k1 = 3;

        int n = code.length;
        int[] ans = new int[n];

        if (k1 == 0) {
            System.out.println(Arrays.toString(ans));
            return;
        }

        if (k1 > 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= k1; j++) {
                    ans[i] += code[(i + j) % n];
                }
            }
            System.out.println(Arrays.toString(ans));
            return;
        }

        for (int i = 0; i < n / 2; i++) {
            int temp = code[i];
            code[i] = code[n - 1 - i];
            code[n - 1 - i] = temp;
        }

        k1 = -k1;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k1; j++) {
                ans[i] += code[(i + j) % n];
            }
        }

        for (int i = 0; i < n / 2; i++) {
            int temp = ans[i];
            ans[i] = ans[n - 1 - i];
            ans[n - 1 - i] = temp;
        }

        System.out.println(Arrays.toString(ans));
        
//        Q3
//        1493. Longest Subarray of 1's After Deleting One Element
//        Solved
//        Medium
//        Topics
//        premium lock icon
//        Companies
//        Hint
//        Given a binary array nums, you should delete one element from it.
//
//        Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.
//
//         
//
//        Example 1:
//
//        Input: nums = [1,1,0,1]
//        Output: 3
//        Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
        

        int[] nums1 = {1, 1, 0, 1, 1, 1, 0, 1};

        int count1 = 0;
        int maxLength = 0;
        int start1 = 0;

        for (int end = 0; end < nums1.length; end++) {
            if (nums1[end] == 0) {
                count1++;
            }

            while (count1 > 1) {
                if (nums1[start1] == 0) {
                    count1--;
                }
                start1++;
            }

            maxLength = Math.max(maxLength, end - start1);
        }

        System.out.println(maxLength);

        
        
	}
}
