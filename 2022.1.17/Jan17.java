import java.util.*;

/*
给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。

字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。

 

示例 1:

输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
输出: [["bat"],["nat","tan"],["ate","eat","tea"]]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/group-anagrams
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
class Solution1{
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        for (String str : strs)
        {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key,new ArrayList<String>());
            list.add(str);
            map.put(key,list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}

/*
* 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。

 

示例 1：

输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/merge-intervals
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

/*
* 空间浪费很严重，而且效率不高，要先排序再出队列
* 结果就是用时和内存消耗十分严重，正确的方法应该使用排序，然后从左到右遍历直至终点小于下一个的起点
* */
class Solution{
    static Comparator<Integer[]> cmp = new Comparator<Integer[]>() {
        public int compare(Integer[] e1, Integer[] e2) {
            if((Objects.equals(e1[0], e2[0])))
            {
                if(e1[1]==1)    return -1;
                else return 1;
            }
            else
            {
                return e1[0]-e2[0];
            }
        }
    };
    public int[][] merge1(int[][] intervals) {
        Queue<Integer[]> temp = new PriorityQueue<Integer[]>(cmp);
        for (int[] index : intervals)
        {
            temp.add(new Integer[]{index[0],1});
            temp.add(new Integer[]{index[1],-1});
        }
        int i = -1;
        int start = 0, end = 0;
        List<int[]> res = new ArrayList<>();
        while (!temp.isEmpty()){
            Integer[] point = temp.poll();
            if(i == -1){
                start = point[0];
                i=0;
            }
            i+=point[1];
            if(i==0){
                end = point[0];
                res.add(Arrays.stream(new Integer[]{start,end}).mapToInt(Integer::valueOf).toArray());
                i=-1;
            }
        }
        return res.toArray(new int[0][0]);
    }
    public int[][] merge(int[][] intervals){
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> res = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length ; i++){
            int L = intervals[i][0], R = intervals[i][1];
            if (res.size() == 0 || res.get(res.size() - 1)[1] < L) {
                res.add(new int[]{L, R});
            } else {
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], R);
            }
        }
        return res.toArray(new int[0][0]);
    }
}

public class Jan17 {
    public static void main(String[] args){
        int[][] temp = new int[][]{{362,367},{314,315},{133,138},{434,443},{202,203},{144,145},{229,235},{205,212},{314,323},{128,129},
        {413,414},{342,345},{43,49},{333,342},{173,178},{386,391},{131,133},{157,163},{187,190},{186,186},{17,19},{63,69},{70,79},{386,391},
        {98,102},{236,239},{195,195},{338,338},{169,170},{151,153},{409,416},{377,377},{90,96},{156,165},{182,186},{371,372},{228,233},{297,306},
        {56,61},{184,190},{401,403},{221,228},{203,212},{39,43},{83,84},{66,68},{80,83},{32,32},{182,182},{300,306},{235,238},{267,272},{458,464},
        {114,120},{452,452},{372,375},{275,280},{302,302},{5,9},{54,62},{237,237},{432,439},{415,421},{340,347},{356,358},{165,168},{15,17},
        {259,265},{201,204},{192,197},{376,383},{210,211},{362,367},{481,488},{59,64},{307,315},{155,164},{465,467},{55,60},{20,24},{297,304},
        {207,210},{322,328},{139,142},{192,195},{28,36},{100,108},{71,76},{103,105},{34,38},{439,441},{162,168},{433,433},{368,369},{137,137},
        {105,112},{278,280},{452,452},{131,132},{475,480},{126,129},{95,104},{93,99},{394,403},{70,78}};
        //int[][] temp = new int[][]{{131,133},{133,136}};
        Solution test = new Solution();
        test.merge(temp);
    }
}
