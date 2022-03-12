import java.util.*;

/*
* DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。

例如，"ACGAATTCCG" 是一个 DNA序列 。
在研究 DNA 时，识别 DNA 中的重复序列非常有用。

给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。

 

示例 1：

输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
输出：["AAAAACCCCC","CCCCCAAAAA"]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/repeated-dna-sequences
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

class Solution1 {
    public List<String> findRepeatedDnaSequences(String s) {
        if(s.length()<=10)  return new ArrayList<>();
        Set<String> set = new HashSet<>(), res = new HashSet<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i <= s.length(); i++){
                String str = String.valueOf(chars,i,10);
                if (!set.add(str)) res.add(str);
        }
        return new ArrayList<>(res);
    }
}

class Solution{
    Map<Character,Integer> bin = new HashMap<Character,Integer>(){{
     put('A',0);
     put('C',1);
     put('G',2);
     put('T',3);
    }
    };

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        if(len<=10)
            return res;
        int str = 0;
        for (int i = 0; i < 9; i++){
            str = str<<2|bin.get(s.charAt(i));
        }
        Map<Integer,Integer> temp = new HashMap<>();
        for (int i = 0; i < 10; i++){
            str=((str<<2)|bin.get(s.charAt(i+9)))&((1 << 20) - 1);
            temp.put(str,temp.getOrDefault(str,0)+1);
            if(temp.get(str)==2){
                res.add(s.substring(i,i+10));
            }
        }
        return res;
    }
}

public class Mar12 {
    public static void main(String[] args){
        Solution1 test = new Solution1();
        System.out.println(test.findRepeatedDnaSequences("AAAAAAAAAAA"));
    }
}
