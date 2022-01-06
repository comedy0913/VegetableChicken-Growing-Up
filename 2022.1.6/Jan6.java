/*
给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
* */

import java.util.Arrays;

class Solution1{
    public String largestNumber(int[] nums){
        String[] num = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
        {
            num[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(num, (o1, o2) -> (-(o1+o2).compareTo(o2+o1)));
        if(num[0].equals("0"))
            return num[0];
        String res = "";
        for (String n : num){
            res+=n;
        }
        return res;
    }
}

class Solution2 {
    public void wiggleSort(int[] nums) {
        if (nums.length == 0 || nums.length == 1 || nums == null)
            return;
        int[] clone = nums.clone();
        Arrays.sort(clone);

        int n = nums.length;
        int k = n / 2;

        int N = n - 1;
        for (int i = 0;i < n - 1; i+=2) {
            nums[i] = clone[N - k];
            nums[i+1] = clone[N];
            N--;
        }
        //奇数的情况下，最后一位没有处理到，需要进行处理
        if (n %2 == 1){
            nums[n - 1] =  clone[0];
        }

    }
}

class Solution{
    public int findPeakElement(int[] nums){
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

public class Jan6 {
    public static void main(String[] args){
        int[] nums = new int[]{3,30,34,5,9};
        Solution test = new Solution();
        System.out.println();
    }
}
