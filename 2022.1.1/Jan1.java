import com.sun.org.apache.bcel.internal.generic.SWAP;

import java.util.*;
/*
给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

 

示例:

输入: [1,2,3,4]
输出: [24,12,8,6]
 

提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。

说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

进阶：
你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xmf6z5/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/

class Solution1{
    public int[] productExceptSelf(int[] nums) {
        int[] front = new int[nums.length];
        int[] rear = new int[nums.length];
        front[0]=1;
        for (int i = 1; i < nums.length; i++)
        {
            front[i] = front[i-1]*nums[i-1];
        }
        rear[nums.length-1]=1;
        for (int i = nums.length-2; i>=0; i--)
        {
            rear[i] = rear[i+1]*nums[i+1];
        }
        for (int i = 0; i<nums.length; i++)
        {
            front[i]*=rear[i];
        }
        return front;
    }
}

/*
* 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。

 

示例 1：

输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
输出：13
解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
示例 2：

输入：matrix = [[-5]], k = 1
输出：-5

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xaicbc/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

class Solution2 {
    public int kthSmallest(int[][] matrix, int k) {
        Queue<Integer> temp = new PriorityQueue<>();
        for (int i  = 0; i< matrix.length; i++)
        {
            for (int j = 0; j < matrix.length; j++){
                temp.add(matrix[i][j]);
            }
        }
        for (int i =0; i < k-1 ; i++)
        {
            temp.poll();
        }
        return temp.poll();
    }
}


/*
给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。

 

示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]
 

提示：

1 <= nums.length <= 105
k 的取值范围是 [1, 数组中不相同的元素的个数]
题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 

进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xau4ci/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

class Solution3{
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        int[] res = new int[k];
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        maxHeap.addAll(map.entrySet());
        for (int i = k - 1; i >= 0; i--) {
            res[i] = maxHeap.poll().getKey();
        }
        return res;
    }

    public int[] topKFrequent1(int[] nums, int k) {
        HashMap<Integer,Integer> temp = new HashMap<>();
        for (int i = 0; i < nums.length; i++)
        {
            if(temp.containsKey(nums[i])) {
                int a = temp.get(nums[i])+1;
                temp.replace(nums[i], a);
            }
            else {
                temp.put(nums[i],1);
            }
        }
        Integer[][] b = new Integer[2][];
        b[0] = temp.keySet().toArray(new Integer[0]);
        b[1] = temp.values().toArray(new Integer[0]);
        for (int i = 0; i < b[0].length; i++)
        {
            for (int j = b[0].length-1;j>i;j--)
            {
                if(b[1][i] < b[1][j]){
                    Integer index = b[0][i];
                    b[0][i] = b[0][j];
                    b[0][j] = index;
                    index = b[1][i];
                    b[1][i] = b[1][j];
                    b[1][j] = index;
                }
            }
        }
        int[] res = new int[k];
        for (int i = 0; i<k ; i++)
        {
            res[i] = b[0][i];
        }
        return res;
    }
}

/*
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xatgye/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

class Solution4{
    public int[] maxSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> result = new ArrayList<>();

        if (nums == null || nums.length == 0 || k == 0 || k > nums.length) {
            return result.stream().mapToInt(Integer::intValue).toArray();
        }

        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            if (!queue.isEmpty()) {
                // 如果队列元素不在滑动窗口中了，就删除头元素
                if (i >= queue.peek() + k) {
                    queue.pop();
                }

                while (!queue.isEmpty() && nums[i] >= nums[queue.getLast()]) {
                    queue.removeLast();
                }
            }
            queue.offer(i);

            // 滑动窗口经过三个元素，获取当前的最大值，也就是队列的头元素
            if (i + 1 >= k) {
                result.add(nums[queue.peek()]);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

}
/*
给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。

整数除法仅保留整数部分。

 

示例 1：

输入：s = "3+2*2"
输出：7
示例 2：

输入：s = " 3/2 "
输出：1
示例 3：

输入：s = " 3+5 / 2 "
输出：5
 

提示：

1 <= s.length <= 3 * 105
s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
s 表示一个 有效表达式
表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
题目数据保证答案是一个 32-bit 整数

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xa8q4g/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

class Solution {
    public int calculate(String s) {
        Stack<String> temp = new Stack<String>();
        while(!s.equals("")){
            String str = s.substring(0,1);
            while (str.equals(" "))
            {
                s=s.substring(1,s.length());
                str=s.substring(0,0);
            }
            if(s.equals(""))
                break;

            if(s.charAt(0)=='*'||s.charAt(0)=='/'||s.charAt(0)=='+'||s.charAt(0)=='-')
            {
                temp.add(s.substring(0,1));
                s=s.substring(1,s.length());
            }
            else if (s.charAt(0)>='0'&&s.charAt(0)<='9')
            {
                String str1 = "";
                while (s.charAt(0)>='0'&&s.charAt(0)<='9')
                {
                    str1 += s.substring(0,1);
                    s=s.substring(1,s.length());
                    if(s.equals(""))
                        break;
                }
                temp.add(str1);
            }
            if(s.equals(""))
                break;

            while (temp.peek().equals("*")||temp.peek().equals("/"))
            {
                String chr = temp.pop();
                int dev = Integer.parseInt(temp.pop());
                str=s.substring(0,1);
                while (str.equals(" "))
                {
                    s=s.substring(1,s.length());
                    str=s.substring(0,0);
                }
                str = "";
                while (s.charAt(0)>'0'&&s.charAt(0)<'9')
                {
                    str += s.substring(0,1);
                    s=s.substring(1,s.length());
                    if(s.equals(""))
                        break;
                }
                int BDev = Integer.parseInt(str);
                if(chr.equals("/"))
                    temp.add(String.valueOf((int)(dev/BDev)));
                if(chr.equals("*"))
                    temp.add(String.valueOf((int)(dev*BDev)));
            }
        }
        int num1 = 0;
        if(temp.size()==1) return Integer.parseInt(temp.pop());
        Queue<String> queue = new LinkedList<>();
        while (!temp.empty())
        {
            queue.add(temp.pop());
        }
        while (!queue.isEmpty())
        {
            temp.add(queue.poll());
        }
        while (!temp.isEmpty())
        {
            num1 = Integer.parseInt(temp.pop());
            if(temp.isEmpty())
                break;
            if(temp.peek().equals("+")||temp.peek().equals("-"))
            {
                String chr = temp.pop();
                int num2 = Integer.parseInt(temp.pop());
                if(chr.equals("+"))
                    temp.add(String.valueOf((int)(num1+num2)));
                if(chr.equals("-"))
                    temp.add(String.valueOf((int)(num1-num2)));
            }
        }
        return num1;
    }
}

public class Jan1 {
    public static void main(String[] args) {
        Solution test = new Solution();
        System.out.println(test.calculate(" 30 "));
    }
}
