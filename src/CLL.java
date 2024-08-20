import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
        System.out.println("HEAD");
    }
    public void delete(int val) {
        Node node = head;
        if (node == null) {
            return;
        }

        if (head == tail){
            head = null;
            tail = null;
            return;
        }

        if (node.value == val) {
            head = head.next;
            tail.next = head;
            return;
        }

        do {
            Node n = node.next;
            if (n.value == val) {
                node.next = n.next;
                break;
            }
            node = node.next;
        } while (node != head);

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
        list.delete(16);
        list.display();

    }


}
