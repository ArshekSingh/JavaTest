package test.linkedlist;



public class LinkedListDemo {

    static Node head;

    public static void main(String a[]){

        System.out.println("Initial value of head "+ head);
        addFirst(10);
        display();
        addSecond(20);
        display();
        addFirst(30);
        display();
        System.out.println("Final value of head "+ head.data);
    }

    public static void addSecond(int data){
        Node newNode = new Node(data);

        if(head == null){
            head = newNode;
        }

        else {
            newNode.next = head;
            head = newNode;
        }
    }


    public static void addFirst(int data){

        Node newNode = new Node(data);

        //this indicates that the list was empty. So make the new node as head.
        if(head == null){
            head = newNode;
        }

        //List is not empty.
        else {
            newNode.next = head;
            head = newNode;
        }
    }

    public static void display(){

        if(head == null){
            System.out.println("List is Empty");
        }

        else {
            Node curr = head;
            while(curr!=null){
                System.out.print(curr.data+" ");
                curr = curr.next;
            }
        }
        System.out.println();
    }


}


class Node{
    int data;
    Node next;

    Node(int data){
        this.data = data;
        this.next = null;
    }

}
