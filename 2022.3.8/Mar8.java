import java.util.*;

/*
* 今天练习的题目是滑动窗口
* 第一题：
* 创建两个指针l、r和一个哈希表，while循环r<s.length,如果哈希表中不含有r，len=r-l+1，并且r++，将r添加到哈希表中，如果含有r,l等于l和与r重复元素的下标加一大一点的
*
* 第二题：
* 从0循环到words中元素长度，这样就可以覆盖所有的子串
* 然后令r-l+1等于words的总长，然后判断两个哈希表是否相等即可
*
* 第三题：
* 如果没完全覆盖，r往右走，存在要覆盖的字符就添加，如果完全覆盖，更新start和len，l往右走，碰到要覆盖的就减一，最终返回start到start+len位置的字符串即为最终结果
* */

/*
* 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。

 

示例 1:

输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

class Solution1{
    public int lengthOfLongestSubstring(String s){
        Map<Character,Integer> temp = new HashMap<Character,Integer>();
        int res = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++){
            if(temp.containsKey(s.charAt(i))){
                left = Math.max(left,temp.get(s.charAt(i)) + 1);
                temp.remove(s.charAt(i));
            }
            temp.put(s.charAt(i),i);
            res = Math.max(res,i-left+1);
        }
        return res;
    }
}

/*
* 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。

注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。

 

示例 1：

输入：s = "barfoothefoobarman", words = ["foo","bar"]
输出：[0,9]
解释：
从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
输出的顺序不重要, [9,0] 也是有效答案。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

class Solution2{
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if(s == null || s.length()==0 || words == null || words.length == 0) return res;
        HashMap<String,Integer> temp = new HashMap<>();
        for (String word : words){
            temp.put(word,temp.getOrDefault(word,0) + 1);
        }
        for (int i = 0; i < words[0].length(); i++){
            int left = i, right = i, count = 0;
            HashMap<String,Integer> tmp_map = new HashMap<>();
            while (right+words[0].length()<=s.length()){
                String str = s.substring(right,right+words[0].length());
                tmp_map.put(str, tmp_map.getOrDefault(str,0)+1);
                right+=words[0].length();
                count++;
                while (tmp_map.getOrDefault(str,0)>temp.getOrDefault(str,0)){
                    String t_w = s.substring(left,left+words[0].length());
                    count--;
                    tmp_map.put(t_w, tmp_map.getOrDefault(t_w, 0) - 1);
                    left += words[0].length();
                }
                if(count == words.length) res.add(left);
            }
        }
        return res;
    }
}

class Solution3{
    public List<Integer> findSubstring(String s, String[] words) {
        HashMap<String,Integer> temp = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (String string : words){
            temp.put(string,temp.getOrDefault(string,0)+1);
        }
        for (int i = 0; i < words[0].length(); i++){
            int left = i,right = i+ words.length*words[0].length();
            while (right<=s.length()){
                HashMap<String,Integer> hash = new HashMap<>();
                for (int j = left;j<right;j+=words[0].length()){
                    String str = s.substring(j,j+ words[0].length());
                    if(temp.containsKey(str))
                        hash.put(str,hash.getOrDefault(str,0)+1);
                }
                if(hash.size()== temp.size()) {
                    if(temp.equals(hash))
                        res.add(left);
                }
                left += words[0].length();
                right += words[0].length();
            }
        }
        return res;
    }
}

/*
*给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

 

注意：

对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
如果 s 中存在这样的子串，我们保证它是唯一的答案。
 

示例 1：

输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-window-substring
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character,Integer> temp = new HashMap<>();
        for (int i = 0; i < t.length(); i++){
            temp.put(t.charAt(i),temp.getOrDefault(t.charAt(i),0)+1);
        }
        HashMap<Character,Integer> hash = new HashMap<>();
        int left = 0,right = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;
        if(temp.containsKey(s.charAt(left)))
            hash.put(s.charAt(left),hash.getOrDefault(s.charAt(left),0)+1);
        while (true){
            if(check(hash,temp)){
                if(right-left+1<len){
                    len = right-left+1;
                    start = left;
                }
                if(hash.containsKey(s.charAt(left))){
                    hash.put(s.charAt(left),hash.getOrDefault(s.charAt(left),0)-1);

                }
                left++;
            }
            else {
                right++;
                if(right==s.length()) break;
                if(temp.containsKey(s.charAt(right))){
                    hash.put(s.charAt(right),hash.getOrDefault(s.charAt(right),0)+1);
                }
            }
        }
        return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
    }

    private boolean check(HashMap<Character,Integer> a, HashMap<Character,Integer> b){
        if(a.size()!=b.size()) return false;
        for (Map.Entry<Character, Integer> characterIntegerEntry : b.entrySet()) {
            Character key = (Character) ((Map.Entry) characterIntegerEntry).getKey();
            Integer val = (Integer) ((Map.Entry) characterIntegerEntry).getValue();
            if (a.getOrDefault(key, 0) < val)
                return false;
        }
        return true;
    }
}

public class Mar8 {
    public static void main(String[] args){
//        Solution3 test = new Solution3();
//        System.out.println(test.findSubstring("wordgoodgoodgoodbestword",new String[]{"word","good","best","good"}));
        Solution test = new Solution();
        System.out.println(test.minWindow("ab","b"));
    }
}
