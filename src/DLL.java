public class DLL {
    private Node head,tail;
    public void insertf(int value){
        Node node =new Node(value);
        node.next=head;
        node.prev=null;
        if(head!=null){
            head.prev=node;
        }
        head=node;
    }
    private void insertl(int value) {
        Node node = new Node(value);
        Node last = head;
        node.next = null;
        if (head == null) {
            node.prev = null; // can also give insert first
            head = node;
            return;
        }
        while (last.next != null) {
            last = last.next;
        }
        last.next = node;
        node.prev = last;
    }
    private static class Node{
        int value;
        Node prev,next;

        Node(int value){
            this.value=value;
        }
        Node(int value,Node prev,Node next){
            this.value=value;
            this.prev=prev;
            this.next=next;
        }

    }

    public static void main(String[] args) {
        DLL list =new DLL();
        list.insertf(12);
        list.insertf(34);
        list.insertf(90);
        list.display();
        list.insertl(100);
        list.insertl(102);
        list.display();
    }



    public void display() {
        Node node = head;
        Node last = null;
        while (node != null) {
            System.out.print(node.value + " -> ");
            last = node;
            node = node.next;
        }
        System.out.println("END");

        System.out.println("Print in reverse");
        while (last != null) {
            System.out.print(last.value + " -> ");
            last = last.prev;
        }
        System.out.println("START");
    }
}
