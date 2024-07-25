public class LL {
    private Node head;
    private Node tail;
    private int size;
    public LL(){
        this.size=0;
    }
    public void insertf(int value){
        Node node=new Node(value);
        node.next=head;
        head=node;
        size+=1;
        if(tail==null){
            tail=head;
        }
    }
    private void insertl(int i) {
        if(tail==null){
            insertf(i);
            return;
        }
        Node node=new Node(i);
        tail.next=node;
        tail=node;
        size+=1;
    }
    private void insert(int value, int index) {
        if(head==null){
            insertf(value);
            return;
        }
        if(index==size){
            insertl(value);
            return;
        }

        Node node=head;
        for(int i=1;i<index;i++){
            node=node.next;
        }
        Node node1=new Node(value,node.next);
        node.next=node1;
        size+=1;
    }
    private void deletef() {
        head=head.next;
        if(head==null){
            tail=null;
        }
        size--;
    }
    private void deletel(){
        if(size<=1){
            deletef();
        }
        Node node=head;
        for(int i=0;i<size-2;i++){
            node=node.next;
        }
        tail=node;
        tail.next=null;
        size--;
    }
    private void delete(int index) {
        if(index==0){
            deletef();
        }if(index==size-1){
            deletel();
        }
        Node p=head;
        for(int i=0;i<index-1;i++){
            p=p.next;
        }
        p.next=p.next.next;
        size--;

    }


    private class Node{
        private int value;
        private Node next;
        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LL list=new LL();
        list.insertf(4);
        list.insertf(17);
        list.insertf(23);
        list.insertf(34);
        list.insertl(99);
        list.display();
        list.insert(5,1);
        list.display();
        list.deletef();
        list.display();
        list.deletel();
        list.display();
        list.delete(1);
        list.display();

    }



    private void display() {
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.value+" ");
            temp=temp.next;
        }
        System.out.println();
    }
}
