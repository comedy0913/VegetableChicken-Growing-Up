/*
* 438. 找到字符串中所有字母异位词
给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。



示例 1:

输入: s = "cbaebabacd", p = "abc"
输出: [0,6]
解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
* */

import java.util.*;

class Solution1{
    public List<Integer> findAnagrams(String s, String p){
        List<Integer> res = new ArrayList<>();
        if(s.length()<p.length())   return res;
        HashMap<Character,Integer> target = new HashMap<>();
        HashMap<Character,Integer> temp = new HashMap<>();
        for (int i = 0; i < p.length(); i++){
            target.put(p.charAt(i),target.getOrDefault(p.charAt(i),0)+1);
        }
        int l = 0,r=0;
        while (r<s.length()){
            while (r-l<p.length()){
                temp.put(s.charAt(r),temp.getOrDefault(s.charAt(r),0)+1);
                r++;
            }
            if(temp.equals(target))
                res.add(l);
            if(temp.getOrDefault(s.charAt(l),0)-1==0)
                temp.remove(s.charAt(l));
            else
                temp.put(s.charAt(l),temp.getOrDefault(s.charAt(l),0)-1);
            l++;
        }
        return res;
    }
}

/*
* 480. 滑动窗口中位数
中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。

例如：

[2,3,4]，中位数是 3
[2,3]，中位数是 (2 + 3) / 2 = 2.5
给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。



示例：

给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。

窗口位置                      中位数
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7      -1
 1  3 [-1  -3  5] 3  6  7      -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
 因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
* */

class Solution{
    public double[] medianSlidingWindow(int[] nums, int k){
        DualHeap dh = new DualHeap(k);
        for (int i = 0; i < k; i++){
            dh.insert(nums[i]);
        }
        double[] ans = new double[nums.length - k + 1];
        ans[0] = dh.getMedian();
        for (int i = k; i < nums.length; ++i){
            dh.insert(nums[i]);
            dh.erase(nums[i-k]);
            ans[i-k+1] = dh.getMedian();
        }
        return ans;
    }
}

class DualHeap{
    private PriorityQueue<Integer> small;
    private PriorityQueue<Integer> large;
    private Map<Integer,Integer> delayed;

    private int k;

    private int smallSize, largeSize;

    public DualHeap(int k){
        this.small = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        this.large = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        this.delayed = new HashMap<Integer,Integer>();
        this.k = k;
        this.smallSize = 0;
        this.largeSize = 0;
    }

    public double getMedian(){
        return (k&1)==1?small.peek():((double)((small.peek()+large.peek())/2));
    }

    public void insert(int num){
        if(small.isEmpty()||num<=small.peek()){
            small.offer(num);
            smallSize++;
        } else {
            large.offer(num);
            largeSize++;
        }
        makeBalance();
    }

    public void erase(int num){
        delayed.put(num,delayed.getOrDefault(num,0)+1);
        if(num <= small.peek()){
            smallSize--;
            if(num == small.peek())
                prune(small);
        } else {
            largeSize--;
            if(num == large.peek())
                prune(large);
        }
        makeBalance();
    }

    private void prune(PriorityQueue<Integer> heap){
        while (!heap.isEmpty()){
            int num = heap.peek();
            if(delayed.containsKey(num)){
                delayed.put(num,delayed.get(num)-1);
                if(delayed.get(num)==0){
                    delayed.remove(num);
                }
                heap.poll();
            }else {
                break;
            }
        }
    }

    private void makeBalance(){
        if(smallSize > largeSize + 1){
            large.offer(small.poll());
            smallSize--;
            largeSize++;
            prune(small);
        }else if(smallSize<largeSize){
            small.offer(large.poll());
            smallSize++;
            largeSize--;
            prune(large);
        }
    }
}

public class Mar22 {
    public static void main(String[] args){
        Solution test = new Solution();

    }
}
