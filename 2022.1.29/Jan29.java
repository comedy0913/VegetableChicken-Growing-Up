import java.util.*;
/*
* 计算右侧小于当前元素的个数
给你一个整数数组 nums ，按要求返回一个新数组 counts 。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。

 

示例 1：

输入：nums = [5,2,6,1]
输出：[2,1,1,0]
解释：
5 的右侧有 2 个更小的元素 (2 和 1)
2 的右侧仅有 1 个更小的元素 (1)
6 的右侧有 1 个更小的元素 (1)
1 的右侧有 0 个更小的元素

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xajl22/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

//第一种想法就是暴力求解，直接两个for循环解决问题，但是这样做时间复杂度很高
//第二种做法看网上的，使用改良版本BST进行求解(超时了，我并不是很理解为什么)
//第三种做法是使用归并排序，在并的时候进行统计
class Solution1 {
    private int[] index;
    private int[] temp;
    private int[] tempIndex;
    private int[] ans;

    public List<Integer> countSmaller(int[] nums) {
        this.index = new int[nums.length];
        this.temp = new int[nums.length];
        this.tempIndex = new int[nums.length];
        this.ans = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            index[i] = i;
        }
        int l = 0, r = nums.length - 1;
        mergeSort(nums, l, r);
        List<Integer> list = new ArrayList<Integer>();
        for (int num : ans) {
            list.add(num);
        }
        return list;
    }

    public void mergeSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) >> 1;
        mergeSort(a, l, mid);
        mergeSort(a, mid + 1, r);
        merge(a, l, mid, r);
    }

    public void merge(int[] a, int l, int mid, int r) {
        int i = l, j = mid + 1, p = l;
        while (i <= mid && j <= r) {
            if (a[i] <= a[j]) {
                temp[p] = a[i];
                tempIndex[p] = index[i];
                ans[index[i]] += (j - mid - 1);
                ++i;
                ++p;
            } else {
                temp[p] = a[j];
                tempIndex[p] = index[j];
                ++j;
                ++p;
            }
        }
        while (i <= mid)  {
            temp[p] = a[i];
            tempIndex[p] = index[i];
            ans[index[i]] += (j - mid - 1);
            ++i;
            ++p;
        }
        while (j <= r) {
            temp[p] = a[j];
            tempIndex[p] = index[j];
            ++j;
            ++p;
        }
        for (int k = l; k <= r; ++k) {
            index[k] = tempIndex[k];
            a[k] = temp[k];
        }
    }
}

class Node{
    Node left;
    Node right;
    int val;
    int count;//这个变量记录的是该节点左边的节点个数
    Node(){};
    Node(int value,int counts){this.val=value;this.count=counts;}
}

class Solution{
    public List<Integer> countSmaller(int[] nums){
        if(nums.length==0) return null;
        Integer[] temp = new Integer[nums.length];
        Arrays.fill(temp,0);
        Node head = new Node(nums[nums.length-1],0);
        for (int i = nums.length-2; i >= 0; i--){
            head = AddNode(head,nums[i],i,temp);
        }
        return Arrays.asList(temp);
    }
    private Node AddNode(Node head,int val,int pos,Integer[] res){
        if(head==null){
            return new Node(val,0);
        }
        if(val> head.val){
            res[pos]+=(head.count+1);
            head.right = AddNode(head.right,val,pos,res);
        }
        if(val<=head.val){
            head.count++;
            head.left = AddNode(head.left,val,pos,res);
        }
        return head;
    }
}

public class Jan29 {
    public static void main(String[] args){
        int[] nums = new int[]{5,2,6,1};
        Solution test = new Solution();
        test.countSmaller(nums);
    }
}
