/*
* 给定一个含有 n 个正整数的数组和一个正整数 target 。

找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。

 

示例 1：

输入：target = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Solution1{
    public int minSubArrayLen(int target, int[] nums){
        int left = 0;
        int right = left;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while(right<nums.length){
            sum+=nums[right];
            while (sum >= target) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return res == Integer.MAX_VALUE?0:res;
    }
}

/*
* 219. 存在重复元素 II
给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。



示例 1：

输入：nums = [1,2,3,1], k = 3
输出：true
示例 2：

输入：nums = [1,0,1,1], k = 1
输出：true
* */

class Solution2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> temp = new HashSet<>();
        for (int i = 0; i < nums.length; i++){
            if(i>k){
                temp.remove(nums[i-k-1]);
            }
            if(!temp.add(nums[i])){
                return true;
            }
        }
        return false;
    }
}

/*
* 424. 替换后的最长重复字符
给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。

在执行上述操作后，返回包含相同字母的最长子字符串的长度。



示例 1：

输入：s = "ABAB", k = 2
输出：4
解释：用两个'A'替换为两个'B',反之亦然。
* */

class Solution{
    public int characterReplacement(String s, int k){
        int[] num = new int[26];
        int n = s.length();
        int maxn = 0;
        int left = 0, right = 0;
        while(right<n){
            num[s.charAt(right)-'A']++;
            maxn = Math.max(maxn,num[s.charAt(right)-'A']);
            if(right-left+1-maxn>k){
                num[s.charAt(left)-'A']--;
                left++;
            }
            right++;
        }
        return right-left;
    }
}

public class Mar21 {
    public static void main(String[] args){
        Solution test = new Solution();
        int[] nums = new int[]{1,2,1};
    }
}
