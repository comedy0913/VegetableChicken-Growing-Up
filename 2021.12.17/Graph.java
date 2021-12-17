//通过邻接矩阵存图，并且实现广度优先搜索和深度优先搜索
interface EdgeAdt{
    public int v1();
    public int v2();
}

interface GraphADT{
    public int n();
    public int e();
    public Edge First(int v);
    public Edge next(Edge w);
    public boolean isEdge(Edge w);
    public boolean isEdge(int i,int j);
    public int v1(Edge w);
    public int v2(Edge w);
    public void setEdge(int i, int j, int weight);
    public void setEdge(Edge w, int weight);
    public void delEdge(Edge w);
    public void delEdge(int i, int j);
    public int weight(Edge w);
    public int weight(int i, int j);
    public void setMark(int v, int val);
    public int getMark(int v);
}

class Edge implements EdgeAdt{

    private int vert1;
    private int vert2;

    public Edge(int v1,int v2){
        vert1=v1;
        vert2=v2;
    }

    @Override
    public int v1() {
        return this.vert1;
    }

    @Override
    public int v2() {
        return this.vert2;
    }
}


class MatrixGraph implements GraphADT {

    private int[][] matrix;
    private int numEdge;
    private int[] Mark;

    public MatrixGraph(int n){
        Mark = new int[n];
        matrix = new int[n][n];
        numEdge = 0;
    }

    @Override
    public int n() {
        return Mark.length;
    }

    @Override
    public int e() {
        return numEdge;
    }

    @Override
    public Edge First(int v) {
        for (int i = 0; i < Mark.length; i++)
        {
            if(matrix[v][i]!=0)
                return new Edge(v,i);
        }
        return null;
    }

    @Override
    public Edge next(Edge w) {
        for (int i = w.v2() + 1; i < Mark.length; i++)
        {
            if(matrix[w.v1()][i]!=0)
                return new Edge(w.v1(),i);
        }
        return null;
    }

    @Override
    public boolean isEdge(Edge w) {
        if(w == null) return false;
        return (matrix[w.v1()][w.v2()]!=0);
    }

    @Override
    public boolean isEdge(int i, int j) {
        return (matrix[i][j]!=0);
    }

    @Override
    public int v1(Edge w) {
        return w.v1();
    }

    @Override
    public int v2(Edge w) {
        return w.v2();
    }

    @Override
    public void setEdge(int i, int j, int weight) {
        matrix[i][j]=weight;
        numEdge++;
    }

    @Override
    public void setEdge(Edge w, int weight) {
        setEdge(w.v1(),w.v2(),weight);
    }

    @Override
    public void delEdge(Edge w) {
        if(w!=null)
            delEdge(w.v1(),w.v2());
    }

    @Override
    public void delEdge(int i, int j) {
        matrix[i][j]=0;
        numEdge--;
    }

    @Override
    public int weight(Edge w) {
        return matrix[w.v1()][w.v2()];
    }

    @Override
    public int weight(int i, int j) {
        return matrix[i][j];
    }

    @Override
    public void setMark(int v, int val) {
        Mark[v]=val;
    }

    @Override
    public int getMark(int v) {
        return Mark[v];
    }

    public void DFS(int start){
        System.out.println(start);
        setMark(start,1);
        for (Edge i = First(start); isEdge(i); i = next(i))
        {
            if(getMark(i.v2())==0)
                DFS(i.v2());
        }
    }

    public void BFS(int start){
        System.out.println(start);
        MyQueue temp = new MyQueue();
        temp.enQueue(start);
        setMark(start,1);
        int len = temp.getCurLen();
        while (!temp.isEmpty()){
            int father = (int) temp.deQueue();
            for (int j = 0; j < len; j++)
            {
                for (Edge i = First(father); isEdge(i); i = next(i)){
                    if(getMark(i.v2())==0)
                    {
                        setMark(i.v2(),1);
                        System.out.println(i.v2());
                        temp.enQueue(i.v2());
                    }
                }
            }
            len = temp.getCurLen();
        }
    }
}


class Edge1 implements EdgeAdt{
    private int vert1,vert2;
    private Link itself;

    public Edge1(int v1,int v2,Link it){
        vert1=v1;
        vert2=v2;
        itself=it;
    }

    @Override
    public int v1() {
        return vert1;
    }

    @Override
    public int v2() {
        return vert2;
    }

    public Link theLink(){return itself;}
}

class GraphList extends LList{
    public Link currLink(){return curr;}
    public void setCurr(Link who){curr = who;}
}

class ListGraph {
    private GraphList[] vertex;
    private int numEdge;
    public int[] Mark;


    public ListGraph(int n) {
        Mark = new int[n];
        vertex = new GraphList[n];
        for (int i = 0; i < n; i++)
            vertex[i] = new GraphList();
        numEdge = 0;
    }
}

public class Graph {
    public static void main(String[] args){
        MatrixGraph test = new MatrixGraph(20);
        test.setEdge(1,3,1);
        test.setEdge(1,4,1);
        test.setEdge(3,4,1);
        test.setEdge(3,5,1);
        test.setEdge(4,5,1);
        test.setEdge(4,7,1);
        test.setEdge(5,8,1);
        test.setEdge(7,8,1);
//        test.BFS(1);
        test.DFS(1);
    }
}