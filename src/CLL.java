public class CLL {
    Node head,tail;
    private void insert(int value){
        Node node=new Node(value);
        if(head==null){
            head=tail=node;
            return;
        }
        tail.next=node;
        node.next=head;
        tail=node;
    }
    private void display(){
        Node node=head;
        if(node!=null){
            do{
                System.out.print(node.value+"->");
                node=node.next;
            }while(node!=head);
        }
        System.out.print("HEAD");
    }
    private class Node{
        int value;
        Node next;
        Node(int value){
            this.value=value;
        }
        Node(int value, Node next){
            this.value=value;
            this.next=next;
        }
    }

    public static void main(String[] args) {
        CLL list =new CLL();
        list.insert(12);
        list.insert(14);
        list.insert(16);
        list.insert(18);
        list.display();

    }


}
