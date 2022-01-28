import sun.security.util.Length;

import java.util.*;

/*
给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。

如果存在则返回 true，不存在返回 false。

 

示例 1：

输入：nums = [1,2,3,1], k = 3, t = 0
输出：true

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/contains-duplicate-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
//大概思路就是维护一个滑动窗口，然后对这个滑动窗口内的数进行排序，然后用两个最小的相减与t进行比较
//上面这个实现的结果是错误的，因为距离最小不代表两个数本身很小，正确的方法应该是使用桶排序加上滑动窗口（这个滑动窗口的数据结构必须支持元素的快速增删），这里我们选择了哈希表
//分成若干个大小为t+1的桶，如果一个桶里面有两个元素，那么这两个元素必然符合条件
class Solution1 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        long w = (long)t + 1;
        Map<Long, Long> temp = new HashMap<>();
        for (int i =0; i<len; i++)
        {
            long id = getID(nums[i],w);
            if(temp.containsKey(id))    return true;
            if(temp.containsKey(id-1)&&Math.abs(nums[i]-temp.get(id-1))<=(long) t) return true;
            if(temp.containsKey(id+1)&&Math.abs(nums[i]- temp.get(id+1))<=(long) t) return true;
            temp.put(id,(long)nums[i]);
            if(i>=k)
                temp.remove(getID(nums[i-k],w ));
        }
        return false;
    }

    private long getID(long x,long w){
        if(x>=0) return x/w;
        else return (x+1)/w-1;
    }
}

/*
* 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。

 

 

示例 1：

输入：[3,2,3]
输出：[3]
示例 2：

输入：nums = [1]
输出：[1]
示例 3：

输入：[1,1,1,3,3,2,2,2]
输出：[1,2]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/majority-element-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
/*
* 第一个想法就是使用哈希表进行统计
* 这道题的想法就是首先排序，然后遍历一遍
* */

class Solution2{
    public List<Integer> majorityElement(int[] nums) {
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        int key = nums[0];
        int times = 0;
        for (int i = 0; i<nums.length; i++)
        {
            if(nums[i]== key) {
                times++;
            }else {
                key = nums[i];
                if(times>(nums.length/3)) res.add(nums[i-1]);
                times=1;
            }
            if(res.size()==2) return res;
        }
        if(times>(nums.length/3)) res.add(nums[nums.length-1]);
        return res;
    }
}

/*
* 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。

 

示例 1:

输入: s = "anagram", t = "nagaram"
输出: true

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-anagram
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

//想法就是给两个字符串排序，如果两个字符串完全相同那么true
class Solution3{
    public boolean isAnagram(String s, String t) {
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }
}

/*
* 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。

根据维基百科上 h 指数的定义：h 代表“高引用次数”，一名科研人员的 h指数是指他（她）的 （n 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。且其余的 n - h 篇论文每篇被引用次数 不超过 h 次。

如果 h 有多种可能的值，h 指数 是其中最大的那个。

 

示例 1：

输入：citations = [3,0,6,1,5]
输出：3
解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
     由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/h-index
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
//计算h指数，先排序，计算nums.length-i和nums[]的大小关系，若nums.length-i>nums[]，为h值

class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }
}

public class Jan28 {
    public static void main(String[] args){
        int[] nums = new int[]{0};
        Solution test = new Solution();
        test.hIndex(nums);
    }
}
