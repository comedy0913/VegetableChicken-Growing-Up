/*
给你一个嵌套的整数列表 nestedList 。每个元素要么是一个整数，要么是一个列表；该列表的元素也可能是整数或者是其他列表。请你实现一个迭代器将其扁平化，使之能够遍历这个列表中的所有整数。

实现扁平迭代器类 NestedIterator ：

NestedIterator(List<NestedInteger> nestedList) 用嵌套列表 nestedList 初始化迭代器。
int next() 返回嵌套列表的下一个整数。
boolean hasNext() 如果仍然存在待迭代的整数，返回 true ；否则，返回 false 。
你的代码将会用下述伪代码检测：

initialize iterator with nestedList
res = []
while iterator.hasNext()
    append iterator.next() to the end of res
return res
如果 res 与预期的扁平化列表匹配，那么你的代码将会被判为正确。

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xa3tsv/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

import java.util.*;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
/*
* 基本的思路很简单，就是一个深度优先搜索，首先遍历第一个列表，如果列表中的元素并不是Integer类型，说明它本身就是一个迭代器的数组，可以继续迭代，对
* 该迭代器继续使用深度优先搜索
* */

//public class NestedIterator implements Iterator<Integer> {
//    private Deque<Integer> deque=new LinkedList<>();
//    public NestedIterator(List<NestedInteger> nestedList) {
//        dfs(nestedList);
//    }
//
//    public void dfs(List<NestedInteger> list){
//        for (NestedInteger nestedInteger : list) {
//            if (nestedInteger.isInteger()){
//                deque.addLast(nestedInteger.getInteger());
//            }else {
//                dfs(nestedInteger.getList());
//            }
//        }
//    }
//
//    @Override
//    public boolean hasNext() {
//        return !deque.isEmpty();
//    }
//
//    @Override
//    public Integer next() {
//        return deque.pollFirst();
//    }
//}


/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */


/*
* 根据 逆波兰表示法，求表达式的值。

有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

 

说明：

整数除法只保留整数部分。
给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 

示例 1：

输入：tokens = ["2","1","+","3","*"]
输出：9
解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
示例 2：

输入：tokens = ["4","13","5","/","+"]
输出：6
解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xaqlgj/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
class Solution1{
    public int evalRPN(String[] tokens) {
        Stack<String> temp = new Stack<>();
        int index = 0;
        temp.add(tokens[0]);
        index++;
        while (index!= tokens.length)
        {
            if(tokens[index].equals("/")||tokens[index].equals("*")||tokens[index].equals("+")||tokens[index].equals("-"))
            {
                int num1 = Integer.parseInt(temp.pop());
                int num2 = Integer.parseInt(temp.pop());
                if(tokens[index].equals("/"))
                {
                    temp.add(String.valueOf(num2/num1));
                }
                else if(tokens[index].equals("*"))
                {
                    temp.add(String.valueOf(num2*num1));
                }
                else if(tokens[index].equals("+"))
                {
                    temp.add(String.valueOf(num2+num1));
                }
                else {
                    temp.add(String.valueOf(num2-num1));
                }
            }
            else
            {
                temp.add(tokens[index]);
            }
            index++;
        }
        return Integer.parseInt(temp.pop());
    }
}

/*
复制带随机指针的链表
给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。

构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。

例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。

返回复制链表的头节点。

用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：

val：一个表示 Node.val 的整数。
random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
你的代码 只 接受原链表的头节点 head 作为传入参数。

 

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xam1wr/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

/*
* 这道题做得很垃圾，思路是比较暴力的思路，首先把原链表里面random对应的结点位置记下来，先不拷贝random，等拷贝完next之后统一拷贝random
* 正确的做法应该是：
* 方法一：使用奇偶链表，首先把所有的元素都复制一遍，挂在后面，然后新复制结点的random就可以表示为原结点.next.random = 原结点.random.next，这样遍历一遍链表然后再删除原来的结点之后就可以得到最终的结果
* 方法二：使用哈希表，key为结点，value为复制结点，hash[原结点].random = hash[原结点.random]
*  */


class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Solution2{
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node node = head;
        HashMap<Node,Node> temp = new HashMap<>();
        while (node!= null)
        {
            temp.put(node,new Node(node.val));
            node = node.next;
        }
        node = head;
        while (node!=null)
        {
            temp.get(node).next = temp.get(node.next);
            temp.get(node).random = temp.get(node.random);
            node = node.next;
        }
        return temp.get(head);
    }
}

class Solution{

}

public class Jan2 {
    public static void main(String[] args){
        Solution test = new Solution();
    }
}
