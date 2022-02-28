import com.sun.glass.ui.View;

import java.util.*;

class Solution1 {
    public int trailingZeroes(int n){
        int res = 0;
        while (n>0){
            res+=n/5;
            n/=5;
        }
        return res;
    }
}

class Solution2 {
    public String fractionToDecimal(int numerator, int denominator){
        long numeratorLong = (long) numerator;
        long denominatorLong = (long) denominator;
        if(numeratorLong%denominatorLong==0){
            return String.valueOf(numeratorLong/denominatorLong);
        }
        StringBuffer sb = new StringBuffer();
        if(numeratorLong<0^denominatorLong<0){
            sb.append('-');
        }

        //整数部分
        numeratorLong = Math.abs(numeratorLong);
        denominatorLong = Math.abs(denominatorLong);
        long integerPart = numeratorLong/denominatorLong;
        sb.append(integerPart);
        sb.append('.');

        //小数部分
        StringBuffer fractionPart = new StringBuffer();
        Map<Long,Integer> remainderIndexMap = new HashMap<Long,Integer>();
        long remainder = numeratorLong%denominatorLong;
        int index = 0;
        while (remainder != 0 && !remainderIndexMap.containsKey(remainder)){
            remainderIndexMap.put(remainder,index);
            remainder*=10;
            fractionPart.append(remainder/denominatorLong);
            remainder%=denominatorLong;
            index++;
        }
        if(remainder!=0){
            int insertIndex = remainderIndexMap.get(remainder);
            fractionPart.insert(insertIndex,'(');
            fractionPart.append(')');
        }
        sb.append(fractionPart.toString());
        return sb.toString();
    }
}

class Solution3{
    private int gcd(int a,int b){
        return b!=0?gcd(b,a%b):a;
    }

    public int maxPoints(int[][] points){
        int n = points.length;
        if(n<2)
            return n;
        int ret = 0;
        for (int i = 0; i<n; i++){
            if(ret>=n-i||ret>n/2){
                break;
            }
            Map<Integer,Integer> map = new HashMap<Integer,Integer>();
            for (int j = i+1; j<n; j++){
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if(x==0){
                    y = 1;
                }else if(y==0){
                    x = 1;
                } else {
                    if(y<0){
                        x=-x;
                        y=-y;
                    }
                    int gcdXY = gcd(Math.abs(x),Math.abs(y));
                    x /= gcdXY;
                    y /= gcdXY;
                }
                int key = y + x*20001;
                map.put(key,map.getOrDefault(key,0)+1);
            }
            int maxn = 0;
            for (Map.Entry<Integer,Integer> entry:map.entrySet()){
                int num = entry.getValue();
                maxn = Math.max(maxn,num+1);
            }
            ret = Math.max(ret,maxn);
        }
        return ret;
    }
}

//模拟面试
/*
* 快乐数
编写一个算法来判断一个数 n 是不是快乐数。

「快乐数」 定义为：

对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
如果这个过程 结果为 1，那么这个数就是快乐数。
如果 n 是 快乐数 就返回 true ；不是，则返回 false 。

 

示例 1：

输入：n = 19
输出：true
解释：
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xm3y2i/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

class Solution4{
    public boolean isHappy(int n){
        while (n>6)
        {
            Stack<Integer> temp = new Stack<>();
            while (n>=10){
                temp.add(n%10);
                n/=10;
            }
            n*=n;
            while (!temp.isEmpty())
            {
                n+=Math.pow(temp.pop(),2);
            }
        }
        return n==1;
    }
}

/*
* 给你两个整数 a 和 b ，不使用 运算符 + 和 - ​​​​​​​，计算并返回两整数之和。

示例 1：

输入：a = 1, b = 2
输出：3
* */
class Solution5{
        public int getSum(int a, int b){
            while ((a&b)!=0){
                int temp = a;
                a=a^b;
                b=(temp&b)<<1;
            }
            return a^b;
        }
}
/*
* 给你一个整数 n ，找出从 1 到 n 各个整数的 Fizz Buzz 表示，并用字符串数组 answer（下标从 1 开始）返回结果，其中：

answer[i] == "FizzBuzz" 如果 i 同时是 3 和 5 的倍数。
answer[i] == "Fizz" 如果 i 是 3 的倍数。
answer[i] == "Buzz" 如果 i 是 5 的倍数。
answer[i] == i （以字符串形式）如果上述条件全不满足。
 

示例 1：

输入：n = 3
输出：["1","2","Fizz"]

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xm6kpg/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */
class Solution6 {
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++){
            if((i+1)%15==0)
                res.add("FizzBuzz");
            else if((i+1)%5==0)
                res.add("Buzz");
            else if((i+1)%3==0)
                res.add("Fizz");
            else
                res.add(String.valueOf(i+1));
        }
        return res;
    }
}

/*
* 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。

你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。

给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。

 

示例 1:

输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
输出: 3
解释:
从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
因此，3 可为起始索引。

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xmguej/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        while (i < n) {
            int sumOfGas = 0, sumOfCost = 0;
            int cnt = 0;
            while (cnt < n) {
                int j = (i + cnt) % n;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                if (sumOfCost > sumOfGas) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) {
                return i;
            } else {
                i = i + cnt + 1;
            }
        }
        return -1;
    }
}

/*
* LRU缓存机制
请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
实现 LRUCache 类：
LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。

 

示例：

输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xmsw5r/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */
/**********************************完成了但是很菜*****************************************/

class LRUCache1 {
    int len;
    int curLen = 0;
    Map<Integer,Integer> lru;
    Queue<Integer> act;
    public LRUCache1(int capacity) {
        len = capacity;
        lru = new HashMap<Integer, Integer>();
        act = new LinkedList<>();
    }

    public int get(int key) {
        if(lru.containsKey(key))
        {
            act.remove(key);
            act.add(key);
            return lru.get(key);
        }
        else
            return -1;
    }

    public void put(int key, int value) {
        if(lru.containsKey(key)){
            lru.put(key,value);
            act.remove(key);
            act.add(key);
        }
        else {
            if(curLen>=len){
                lru.remove(act.poll());
                lru.put(key,value);
                act.add(key);
            }
            else {
                lru.put(key,value);
                act.add(key);
                curLen++;
            }
        }
    }
}

/************************************一些比较厉害的方法**********************************************/
/*
继承双向链表加哈希表的LinkedHashMap类，重写removeEldestEntry方法
*/

class LRUCache2 extends LinkedHashMap<Integer,Integer> {
    private int capacity;
    public LRUCache2(int capacity) {
        super(capacity,0.75F,true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key,-1);
    }

    public void put(int key, int value) {
        super.put(key,value);

    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest){
        return size() > capacity;
    }
}

/*
* 第三种方法可以构建一个哈希映射 hashMap<key,linkedNode>,定位到双链表中key值对应的位置，在get和put函数中将对应的结点移到最前面，添加新的结点添加在最前面，删除最后面的，即最长时间没有使用的结点
* */

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

public class Dec28 {
    public static void main(String[] args){

    }
}
