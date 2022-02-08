import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 至少有K个重复字符的最长子串
给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。

 

示例 1：

输入：s = "aaabb", k = 3
输出：3
解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xafdmc/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */
/*
* 分治，找到出现次数小于k的元素，以该元素为分界点进行分治
* */
class Solution1{
    public int longestSubstring(String s, int k){
        if(s.length()<k)    return 0;
        return getMaxLen(s,0,s.length()-1,k);
    }
    public int dfs(String s, int l, int r, int k) {
        int[] cnt = new int[26];
        for (int i = l; i <= r; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        if (split == 0) {
            return r - l + 1;
        }

        int i = l;
        int ret = 0;
        while (i <= r) {
            while (i <= r && s.charAt(i) == split) {
                i++;
            }
            if (i > r) {
                break;
            }
            int start = i;
            while (i <= r && s.charAt(i) != split) {
                i++;
            }

            int length = dfs(s, start, i - 1, k);
            ret = Math.max(ret, length);
        }
        return ret;
    }
    private int getMaxLen(String s,int left, int right, int k){
        int[] count = new int[26];
        for (int i = left; i <= right; i++)
        {
            count[(s.charAt(i)-'a')]++;
        }
        char split = 0;
        for (int i = 0; i < 26; i++)
        {
            if(count[i]>0&&count[i]<k)
            {
                split = (char)(i+'a');
                break;
            }
        }
        if(split == 0) return right - left + 1;
        int i = 0;
        int res = 0;
        while(i<=right)
        {
            while (i <= right && s.charAt(i) == split)
            {
                i++;
            }
            if(i>right)
                break;
            int start = i;
            while (i <= right && s.charAt(i) != split) {
                i++;
            }
            int length = getMaxLen(s,start,i-1,k);
            res = Math.max(res,length);
        }
        return res;
    }
}

/*
* 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。

路径和 是路径中各节点值的总和。

给你一个二叉树的根节点 root ，返回其 最大路径和 。

 

示例 1：


输入：root = [1,2,3]
输出：6
解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
* */


  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }


class Solution2{
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        getMax(root);
        return maxSum;
    }
    private int getMax(TreeNode root)
    {
        if(root == null)
            return 0;
        int leftMax = Math.max(0,getMax(root.left));
        int rightMax = Math.max(0,getMax(root.right));
        maxSum = Math.max(maxSum,root.val+leftMax+rightMax);
        return root.val+Math.max(leftMax,rightMax);
    }
}

/*
* 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

 

示例 1：

输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/x2xmre/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

class Solution3{
    public int longestConsecutive(int[] nums){
        if(nums.length<=1) return nums.length;
        Arrays.sort(nums);
        int max = 1;
        int temp = 1;
        for (int i = 0; i < nums.length-1; i++)
        {
            if(nums[i+1]==nums[i]+1)
            {
                temp++;
            }
            else if (nums[i+1]==nums[i]) {
            }
            else {
                max=Math.max(max,temp);
                temp=1;
            }
        }
        return max=Math.max(max,temp);
    }
}

/*
* 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。

 

示例 1：

输入：n = 12
输出：3
解释：12 = 4 + 4 + 4

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/x2959v/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

class Solution4{
    public int numSquares1(int n) {
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < Math.sqrt(n+1); i++)
        {
            temp.add(i*i);
        }
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++)
        {
            dp[i] = i;
            if(temp.contains(i)) dp[i]=1;
            else {
                for (int j = 0; (j < temp.size())&&(i>j*j); j++)
                {
                    dp[i] = Math.min(dp[i],dp[i-temp.get(j)]+1);
                }
            }
        }
        return dp[n];
    }
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++)
        {
            dp[i] = i;
            if(i == Math.pow((int)(Math.sqrt(i)),2)) dp[i]=1;
            else {
                for (int j = 0; i>j*j; j++)
                {
                    dp[i] = Math.min(dp[i],dp[i-j*j]+1);
                }
            }
        }
        return dp[n];
    }
}

/*
* 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。

计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。

你可以认为每种硬币的数量是无限的。

 

示例 1：

输入：coins = [1, 2, 5], amount = 11
输出：3
解释：11 = 5 + 5 + 1

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/x2echt/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

class Solution{
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0]=0;
        for (int i = 1; i <= amount; i++)
        {
            for (int coin : coins) {
                if(i>=coin) dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] > amount?-1:dp[amount];
    }
}

public class Dec8 {
    public static void main(String[] args){
        Solution test = new Solution();
        int[] coins = new int[]{2};
        System.out.println(test.coinChange(coins,1));
    }
}
