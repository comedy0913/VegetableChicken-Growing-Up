import java.util.*;

/*
* 单词接龙
字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：

每一对相邻的单词只差一个字母。
 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
sk == endWord
给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。

 
示例 1：

输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
输出：5
解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/x2rkbs/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */
class Solution1 {
    List<List<Integer>> edge = new ArrayList<List<Integer>>() {};
    Map<String,Integer> wordId = new HashMap<>();
    int nodeNum = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        addEdge(beginWord);
        for (String s : wordList) {
            addEdge(s);
        }
        //深度优先搜索
        int[] dis = new int[nodeNum];
        Arrays.fill(dis,Integer.MAX_VALUE);
        Queue<Integer> temp = new LinkedList<>();
        int endId = wordId.get(endWord);
        int beginId = wordId.get(beginWord);
        dis[beginId]=0;
        temp.offer(beginId);
        while (!temp.isEmpty()){
            int father = temp.poll();
            if(father==endId){
                return dis[endId]/2+1;
            }
            for (int it:edge.get(father)){
                if(dis[it]==Integer.MAX_VALUE)
                {
                    dis[it] = dis[father]+1;
                    temp.offer(it);
                }
            }
        }
        return 0;
    }

    private void addWord(String word){
        if(!wordId.containsKey(word)){
            wordId.put(word,nodeNum);
            nodeNum++;
            edge.add(new ArrayList<>());
        }
    }

    private void addEdge(String word){
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        for (int i = 0; i< array.length; i++){
            char temp = array[i];
            array[i]='*';
            String str = Arrays.toString(array);
            addWord(str);
            int id2 = wordId.get(str);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i]=temp;
        }
    }

}

/*
*矩阵中的最长递增路径
给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。

对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。

 

示例 1：


输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
输出：4
解释：最长递增路径为 [1, 2, 6, 9]。

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/x2osfr/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

/*
* 使用深度优先搜索加上记忆化搜索。
* */

class Solution2{
    public int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    public int rows,columns;

    public int longestIncreasingPath(int[][] matrix){
        if(matrix == null || matrix.length ==0 || matrix[0].length==0)
            return 0;

        rows = matrix.length;
        columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        int ans = 0;
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++){
                ans = Math.max(ans,dfs(i,j,dp,matrix));
            }
        }
        return ans;
    }
    private int dfs(int row, int column, int[][] dp, int[][] matrix){
        if(dp[row][column]!=0) return dp[row][column];
        dp[row][column]++;
        for (int[] dir: dirs){
            int newRow = row+dir[0];
            int newColumn = column+dir[1];
            if(newRow>=0&&newRow< rows&&newColumn>=0&&newColumn<columns&&matrix[newRow][newColumn] > matrix[row][column]){
                dp[row][column]=Math.max(dp[row][column],dfs(newRow,newColumn,dp,matrix)+1);
            }
        }
        return dp[row][column];
    }
}

/*
* 岛屿数量
给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。

 

示例 1：

输入：grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
输出：1

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/x2p3cd/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

/*
* 使用dfs加上记忆化搜索
* */

class Solution3{
    public int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    int rows,columns;
    public int numIslands(char[][] grid){
        rows = grid.length;
        columns = grid[0].length;
        boolean[][] mark = new boolean[rows][columns];
        int ans = 0;
        for (int i = 0; i<rows; i++){
            for (int j = 0; j < columns; j++){
                if(grid[i][j]!='0'&&!mark[i][j])
                    if(isLand(i,j,grid,mark)){
                        ans++;
                }
            }
        }
        return ans;
    }

    private boolean isLand(int row, int column, char[][] grid, boolean[][] mark)
    {
        if(mark[row][column]) return true;
        mark[row][column]=true;
        for (int[] dir : dirs){
            int newRow = row+dir[0];
            int newColumn = column+dir[1];
            if(newRow>=0&&newRow< rows&&newColumn>=0&&newColumn<columns&& !mark[newRow][newColumn]&& grid[newRow][newColumn]!='0'){
                isLand(newRow,newColumn,grid,mark);
            }
        }
        return mark[row][column];
    }
}

/*
* 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

 

示例 1：

输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。

作者：力扣 (LeetCode)
链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/x2muyh/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */

//使用深度优先搜索，这里每个结点都有三个状态，mark[]中0：未搜索；2：完成搜索；1：搜索中

class Solution4{
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    int[] mark;
    boolean res = true;

    public boolean canFinish(int numCourses, int[][] prerequisites){
        mark = new int[numCourses];
        for (int i = 0; i< numCourses; i++){
            edge.add(new ArrayList<>());
        }
        for (int[] pre : prerequisites){
            edge.get(pre[0]).add(pre[1]);
        }
        for (int i = 0; i < numCourses; i++)
        {
            if(mark[i]==0)
                dfs(i);
        }
        return res;
    }

    private void dfs(int course){
        mark[course]=1;
        for (int c : edge.get(course)){
            if(mark[c]==0) {
                dfs(c);
                if(!res)
                    return;
            }
            else if(mark[c]==1) {
                res = false;
                return;
            }
        }
        mark[course]=2;
    }
}

public class Dec15 {
    public static void main(String[] args){
        int[][] temp = new int[][]{{1,4},{2,4},{3,1},{3,2}};
    }
}

