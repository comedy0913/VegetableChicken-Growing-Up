import java.util.Arrays;

class Solution{
    public int lengthOfLIS(int[] nums){
        int dp[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
        {
            dp[i]=1;
            for (int j = 0;j < i; j++)
            {
                if(nums[i]>nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}

public class Dec13 {
    public static void main(String[] args)
    {
        Solution test = new Solution();
        int[] nums = new int[]{1,3,6,7,9,4,10,5,6};
        System.out.println(test.lengthOfLIS(nums));
    }
}
