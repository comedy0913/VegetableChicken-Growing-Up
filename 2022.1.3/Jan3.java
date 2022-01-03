import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode (int x){
        val = x;
        next = null;
    }
    ListNode() {};
    ListNode(int x,ListNode n){val = x;next = n;};
}

/*
* 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

进阶：

你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xa262d/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

class Solution1{
    /*使用归并排序的方法*/
    /*
     * 先利用快慢指针分割链表，再分治
     * */
    //归并排序
    public static ListNode sortList(ListNode head){
        if(head == null || head.next == null)  //空链表或者只有单个结点
            return head;
        ListNode slow = head, fast = head.next;

        while(fast != null && fast.next != null){  //使用快慢指针寻找中间 结点
            slow = slow.next;

            fast = fast.next;
            if(fast.next != null)
                fast = fast.next;
        }

        ListNode ptr1 = slow.next;
        slow.next = null;

        ListNode tmp1 = sortList(head);
        ListNode tmp2 = sortList(ptr1);
        return merge(tmp1, tmp2);
    }


    public static ListNode merge(ListNode start1,  ListNode start2){
        ListNode header = new ListNode(-1);
        ListNode pre = header;

        ListNode ptr1 = start1, ptr2 = start2;
        while(ptr1 != null && ptr2 != null){
            if(ptr1.val <= ptr2.val){
                pre.next = ptr1;
                pre = ptr1;
                ptr1 = ptr1.next;
            }else{
                pre.next = ptr2;
                pre = ptr2;
                ptr2 = ptr2.next;
            }
        }
        while(ptr1 != null){
            pre.next = ptr1;
            pre = ptr1;
            ptr1 = ptr1.next;
        }

        while(ptr2 != null){
            pre.next = ptr2;
            pre = ptr2;
            ptr2 = ptr2.next;
        }


        return header.next;

    }
    /*
    * 使用快速排序的方法
    *
    * */
    public ListNode sortList1(ListNode head){
        if(head==null||head.next==null)  return head;

        ListNode lowHead = new ListNode(0);
        ListNode low = lowHead;
        ListNode midHead = new ListNode(0);
        ListNode mid = midHead;
        ListNode highHead = new ListNode(0);
        ListNode high = highHead;

        //标准值
        int val = head.val;
        ListNode node = head;

        while (node!=null){
            if(node.val>val){
                high.next = node;
                high = high.next;
            }
            else if(node.val<val)
            {
                low.next = node;
                low = low.next;
            }
            else {
                mid.next = node;
                mid = mid.next;
            }
            node = node.next;
        }
        low.next = null;
        high.next = null;

        lowHead.next = sortList1(lowHead.next);
        highHead.next = sortList1(highHead.next);

        low = lowHead;
        while (low.next!=null){
            low = low.next;
        }
        low.next = mid;
        mid.next = high;

        return lowHead.next;
    }

}

/*
给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。

图示两个链表在节点 c1 开始相交：



题目数据 保证 整个链式结构中不存在环。

注意，函数返回结果后，链表必须 保持其原始结构 。

自定义评测：

评测系统 的输入如下（你设计的程序 不适用 此输入）：

intersectVal - 相交的起始节点的值。如果不存在相交节点，这一值为 0
listA - 第一个链表
listB - 第二个链表
skipA - 在 listA 中（从头节点开始）跳到交叉节点的节点数
skipB - 在 listB 中（从头节点开始）跳到交叉节点的节点数
评测系统将根据这些输入创建链式数据结构，并将两个头节点 headA 和 headB 传递给你的程序。如果程序能够正确返回相交节点，那么你的解决方案将被 视作正确答案 。

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xan8ah/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

class Solution2{
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA;
        ListNode tempB = headB;
        HashMap<ListNode,ListNode> temp = new HashMap<>();
        while (tempA!=null){
            temp.put(tempA,tempA);
            tempA = tempA.next;
        }
        while (tempB!=null){
            if(temp.containsKey(tempB))
                return tempB;
            else
                temp.put(tempB,tempB);
            tempB = tempB.next;
        }
        return null;
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode tempA = headA;
        ListNode tempB = headB;
        HashMap<ListNode,ListNode> temp = new HashMap<>();
        int i = 0;
        while (tempA!=null){
            temp.put(tempA,tempA);
            tempA = tempA.next;
            i++;
        }
        int iA = i;
        i=0;
        while (tempB!=null){
            temp.put(tempB,tempB);
            tempB = tempB.next;
            i++;
        }
        i = i+iA-temp.size();
        if(i==0)    return null;
        i = iA - i;
        tempA = headA;
        while (i>0)
        {
            tempA = tempA.next;
            i--;
        }
        return tempA;
    }
}

/*
* 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

示例 1:

输入: 1->2->3->4->5->NULL
输出: 1->3->5->2->4->NULL
示例 2:

输入: 2->1->3->5->6->4->7->NULL
输出: 2->3->6->7->1->5->4->NULL
说明:

应当保持奇数节点和偶数节点的相对顺序。
链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xa0a97/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

class Solution3{
    public ListNode oddEvenList(ListNode head) {
        if(head==null)  return null;
        ListNode odd = head;
        ListNode even = new ListNode();
        ListNode ev = even;
        while (odd.next != null)
        {
            ev.next = odd.next;
            if(odd.next.next == null)
            {
                ev =ev.next;
                break;
            }
            else
            {
                odd.next = odd.next.next;
                odd = odd.next;
                ev = ev.next;
            }

        }
        ev.next = null;
        odd.next = even.next;
        return head;
    }
}
/*
* 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回该列名称对应的列序号。

 

例如，

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
    ...
 

示例 1:

输入: columnTitle = "A"
输出: 1

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xa6dkt/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

class Solution4{
    public int titleToNumber(String columnTitle){
        int res = 0;
        for (int j = 0; j<columnTitle.length()-1; j++)
        {
            res+=((columnTitle.charAt(j)-'A'+1)*26*Math.pow(26,columnTitle.length()-j-2));
        }
        return res+(columnTitle.charAt(columnTitle.length()-1)-'A'+1);
    }
}

/*
* 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：

0 <= i, j, k, l < n
nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 

示例 1：

输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
输出：2
解释：
两个元组如下：
1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xakn6r/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */
class Solution5{
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            for (int j : nums2) {
                map.put(i + j, map.getOrDefault(i + j, 0) + 1);
            }
        }
        for (int i : nums3) {
            for (int j : nums4) {
                if (map.containsKey(-(i + j)))
                    res += map.get(-(i + j));
            }
        }
        return res;
    }
}

/*
* 实现RandomizedSet 类：

RandomizedSet() 初始化 RandomizedSet 对象
bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。

 

示例：

输入
["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
[[], [1], [2], [2], [], [1], [2], []]
输出
[null, true, false, true, 2, true, false, 2]

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xagm3s/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

class RandomizedSet {

    private HashMap<Integer,Integer> hash;

    public RandomizedSet() {
        hash = new HashMap<>();
    }

    public boolean insert(int val) {
        if(hash.containsKey(val))
            return false;
        else {
            hash.put(val, val);
            return true;
        }
    }

    public boolean remove(int val) {
        if(hash.containsKey(val)){
            hash.remove(val);
            return true;
        }
        else
            return false;
    }

    public int getRandom() {
        Integer[] nList = hash.keySet().toArray(new Integer[0]);
        Random random = new Random();
        return nList[random.nextInt(nList.length)];
    }
}

/*
* 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。

 

示例 1：


输入：root = [3,1,4,null,2], k = 1
输出：1

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xazo8d/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

//用一个DFS深度优先搜索搞定

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution{
    private List<Integer> list = new ArrayList<>();
    private int len;
    public int kthSmallest(TreeNode root, int k) {
        len = k;
        return dfs(root);
    }
    public int dfs(TreeNode root)
    {
        if(root==null) return 0;
        int left = dfs(root.left);
        if (left!=0)
            return left;
        list.add(root.val);
        if(list.size()==len)
            return root.val;
        int right = dfs(root.right);
        if (right!=0)
            return right;
        return 0;
    }
}

public class Jan3 {
    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode head1 = new ListNode(5);

        Solution test = new Solution();
        //System.out.println(test.titleToNumber("FXSHRXW"));
    }
}
