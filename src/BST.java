public class BST {
    private Node root;
    BST(){

    }
    public class Node {
        int value,height;
        Node left,right;
        Node(int value){
            this.value=value;
        }
        int getValue(){
            return value;
        }
    }
    public int height(Node node) {
        if (node == null) {
          return -1;
        }
        return node.height;
      }
    
      public void insert(int value){
        root=insert(value,root);
    }

    public Node insert(int value,Node node){
        if(node==null){
            node=new Node(value);
            return node;
        }
        if(value<node.value){
            node.left=insert(value, node.left);
        }
        if(value > node.value){
            node.right=insert(value, node.right);
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public void populate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
          this.insert(nums[i]);
        }
      }
    public void display(){
        display(root,"Root Node :");
    }
    public void display(Node node,String s){
        if(node==null){
            return;
        }
        System.out.println(s+" "+node.value);
        display(node.left,"Left of Node "+node.value+" : ");
        display(node.right,"Right of Node "+node.value+" : ");
    }
    public void depth(){
        System.out.println("Depth of the tree : "+depth(root));
    }
    public int depth(Node node){
        if(node==null){
            return 0;
        }
        return Math.max(depth(node.left),depth(node.right))+1;
    }
    public static void main(String[] args) {
        BST tree=new BST();
        tree.populate(new int[]{5,2,7,1,4,6,9,8,3,10});
        tree.display();
        tree.depth();
    }
    }

