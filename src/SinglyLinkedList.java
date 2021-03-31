public class SinglyLinkedList<T>{
//LinkedList of any sort of elements - generics {

    //https://www.youtube.com/watch?v=uijdYLYAc1U
    //Implement a Node
    private static class Node<T>{
        //Element itself
        private T element;

        //Pointer
        private Node<T> next;

        //Node with it's own element and pointer
        public Node(T e, Node<T> n){
            element = e;
            next = n;
        }

        public T getElement(){
            return element;
        }

        public Node<T> getNext(){
            return next;
        }

        //Change what the node is pointing to
        public void setNext(Node<T> n){
            next = n;
        }
    }

    //List Implementation
    private Node<T> head = null;
    private Node<T> tail = null;
    private Node<T> current = null;
    private int size = 0;

    //Don't have anything at the moment
    public SinglyLinkedList(){ }
    public int size(){
        return size;
    }

    //If the list is empty return True
    public boolean isEmpty(){
        return size == 0;
    }

    public T first(){
        if(isEmpty())
            return null;
        return head.getElement();
    }

    public T last(){
        if(isEmpty())
            return null;
        return tail.getElement();
    }

    //T are all generics any element --> String
    //Create a new Node, pass the element and point towards the head
    public void addFirst(T e){
        head = new Node<>(e, head);
        if(size == 0)
            tail = head;
        size++;
        //System.out.println("Added head node with " + head.getElement() + " element ");
    }

    public void addLast(T e){
        Node<T> newNode = new Node<>(e, null);
        if(isEmpty())
            head = newNode;
        else
            tail.setNext(newNode);
        tail = newNode;
        size++;
        //System.out.println("Added tail node with " + tail.getElement() + " element ");
    }

    //remove the elements from the head
    public T removeFirst(){
        if(isEmpty())
            return null;
        T answer = head.getElement();
        head = head.getNext();
        size--;
        if(size == 0)
            tail = null;
        //System.out.println("Removed head node with " + answer+ " element ");
        return answer;
    }
    //Linked list in Java: 2 - Print elements of Linked List
    //https://www.youtube.com/watch?v=NaZJ5fmzDhA
    //Source cited @1:43 is what I used Added a current variable and that helped
    public void printList(){
        current = head;

        while (current != null){
            System.out.print("[" + current.element + "] ");
            current = current.next;
        }
    }

}
