import java.util.Arrays;

/*
* 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

 

示例 1：


输入：head = [4,2,1,3]
输出：[1,2,3,4]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sort-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
class ListNode{
    int val;
    ListNode next;
    ListNode(){}
    ListNode(int val){
        this.val = val;
    }
    ListNode(int val, ListNode next){
        this.val = val;
        this.next = next;
    }
}

class Solution1{
    public ListNode insertionSortList(ListNode head) {
        if(head==null||head.next==null) return head;
        for (ListNode i = head; i != null; i=i.next)
        {
            for (ListNode j = head; j != i; j=j.next)
            {
                if(j.val>i.val)
                {
                    int temp = j.val;
                    j.val =i.val;
                    i.val = temp;
                }
            }
        }
        return head;
    }
}

/*
* 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。

如果数组元素个数小于 2，则返回 0。

示例 1:

输入: [3,6,9,1]
输出: 3
解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-gap
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

/*
* 这个题，笑死了，直接用sort搞偷袭，但是正经点写的话，根据题目的要求，应该使用桶排序
* */

class Solution{
    public int maximumGap1(int[] nums) {
        if(nums.length<2)   return 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i++)
        {
            nums[i] = nums[i+1]-nums[i];
        }
        nums[nums.length-1] = 0;
        Arrays.sort(nums);
        return nums[nums.length-1];
    }

    public int maximumGap(int[] nums){
        int n = nums.length;
        if(n<2){
            return 0;
        }
        int minVal = Arrays.stream(nums).min().getAsInt();
        int maxVal = Arrays.stream(nums).max().getAsInt();
        int d = Math.max(1,(maxVal-minVal)/(n-1));
        int bucketSize = (maxVal-minVal)/ d + 1;
        int[][] bucket = new int[bucketSize][2];
        for (int i = 0; i < bucketSize; ++i){
            Arrays.fill(bucket[i],-1);
        }
        for (int i = 0; i < n; i++){
            int idx = (nums[i]-minVal)/d;
            if(bucket[idx][0]==-1){
                bucket[idx][0]=bucket[idx][1]=nums[i];
            }else{
                bucket[idx][0] = Math.min(bucket[idx][0],nums[i]);
                bucket[idx][1] = Math.max(bucket[idx][1],nums[i]);
            }
        }
        int ret = 0;
        int prev = -1;
        for (int i = 0; i < bucketSize; i++){
            if(bucket[i][0]==-1){
                continue;
            }
            if(prev!=-1){
                ret = Math.max(ret,bucket[i][0]-bucket[prev][1]);
            }
            prev = i;
        }
        return ret;
    }
}

public class Jan18 {
    public static void main(String[] args){
        Solution test = new Solution();
        ListNode head = new ListNode(4,new ListNode(2,new ListNode(1,new ListNode(3))));
    }
}
