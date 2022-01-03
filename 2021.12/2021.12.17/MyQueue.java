class node
{
    Object val;
    node next;
    node()
    {
        val = 0;
        next = null;
    }
    node(Object x)
    {
        val = x;
        next = null;
    }
    node(Object x,node y)
    {
        val = x;
        next = y;
    }
}

public class MyQueue {

    private node top = new node();
    private node bot = new node();
    private int length = 0;

    public MyQueue()
    {
        top = bot = null;
    }

    public void enQueue(Object val)
    {
        if((top==bot)&&(bot==null)){
            top = bot =new node(val);
        }else {
            top = new node(val,top);
        }
        length++;
    }

    public Object deQueue()
    {
        if((top==bot)&&(bot==null)){
            return null;
        }else {
            if(top!=bot)
            {
                node temp = top;
                while (temp.next!=bot){
                    temp=temp.next;
                }
                bot = temp;
                length--;
                return bot.next.val;
            }
            else {
                node ret = new node(top.val);
                bot = top = null;
                length--;
                return ret.val;
            }
        }
    }

    public boolean isEmpty()
    {
        return (top==bot)&&(bot==null);
    }

    public int getCurLen()
    {
        return length;
    }
}
