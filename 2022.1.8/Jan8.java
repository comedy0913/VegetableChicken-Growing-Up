import java.util.ArrayList;
import java.util.List;

class Solution1{
    public int findDuplicate(int[] nums) {
        int fast = 0;
        int slow = 0;
        while (true)
        {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if(fast == slow)
            {
                slow = 0;
                while (fast!=slow){
                    slow = nums[slow];
                    fast = nums[fast];
                }
                return slow;
            }
        }
    }

}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){};
    TreeNode(int x){val = x;left = right = null;}
    public TreeNode insert(TreeNode head,int num)
    {
        if(head==null)
        {
            head = new TreeNode(num);
            return head;
        }
        if(head.val==Integer.MAX_VALUE)
        {
            head.val = num;
            return head;
        }
        if(num> head.val)
        {
            head.right = insert(head.right,num);
            return head;
        }
        else if(num< head.val)
        {
            head.left = insert(head.left,num);
            return head;
        }
        return null;
    }
    public TreeNode find(TreeNode head,int num)
    {
        if(head==null)  return null;
        if(num> head.val)   return find(head.right,num);
        else if(num < head.val) return find(head.left,num);
        else return head;
    }
    public int lLen(TreeNode head)
    {
        int res = 0;
        while (true)
        {
            if(head.left==null)
                return res;
            else
            {
                res++;
                head = head.left;
            }
        }
    }

}

class Solution {

    public TreeNode BST(int[] nums)
    {
        TreeNode head = new TreeNode(Integer.MAX_VALUE);
        for (int i = 0; i < nums.length; i++)
        {
            head.insert(head,nums[i]);
        }
        return head;
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        TreeNode head = BST(nums);
        for (int i = 0; i < nums.length; i++)
        {
            TreeNode temp = head.find(head,nums[i]);
            res.add(temp.lLen(temp));
        }
        return res;
    }
}

public class Jan8 {
    public static void main(String[] args)
    {
        Solution test = new Solution();
        int[] nums = new int[]{5,2,6,1};
        test.countSmaller(nums);
//        System.out.println(test.findDuplicate(nums));
    }
}
