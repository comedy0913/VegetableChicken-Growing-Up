class Link{
    private Object element;
    private Link next;

    Link(Object it ,Link nextVal){
        element = it;
        next = nextVal;
    }
    Link(Link nextVal){
        next = nextVal;
    }
    Link next(){return next;};
    Link setNext(Link nextVal){return next = nextVal;}
    Object element(){return element;}
    Object setElement(Object it){return element = it;}
}

public class LList {
    private Link head;
    private Link tail;
    protected Link curr;

    LList(int sz){ setup(); }
    LList(){ setup(); }

    private void setup() {
        tail = head = curr = new Link(null);
    }

    public void clear(){
        head.setNext(null);
        curr = tail = head;
    }

    public void insert(Object it){
        curr.setNext(new Link(it,curr.next()));
        if(tail == curr)
            tail = curr.next();
    }

    public void append(Object it){
        tail.setNext(new Link(it,null));
        tail = tail.next();
    }

    public Object remove(){
        if(!isInList()) return null;
        Object it = curr.next().element();
        if(tail == curr.next()) tail = curr;
        curr.setNext(curr.next().next());
        return it;
    }

    public void setFirst() {
        curr = head;
    }

    public void next(){
        if(curr!=null) curr = curr.next();
    }

    public void prev(){
        if((curr==null)||(curr==head))
        {
            curr = null;
            return;
        }
        Link temp = head;
        while((temp!=null)&&(temp.next()!=curr)){
            temp = temp.next();
        }
        curr = temp;
    }

    public int length(){
        int cnt = 0;
        for (Link temp = head.next();temp!=null;temp=temp.next())
            cnt++;
        return cnt;
    }

    public void setPos(int pos){
        curr = head;
        for (int i = 0;(curr!=null)&&(i<pos);i++)
        {
            curr = curr.next();
        }
    }

    public void setValue(Object it)
    {
        curr.next().setElement(it);
    }

    public Object currValue(){
        if(!isInList()) return null;
        return curr.next().element();
    }

    public boolean isEmpty() {
        return head.next()==null;
    }

    public boolean isInList(){
        return (curr!=null)&&(curr.next()!=null);
    }
}
