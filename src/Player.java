public class Player {
    //Test my Github things
    //Linked List instance
    SinglyLinkedList<String> playerDeck;

    //Constructor to give give each player an linked list
    public Player(){
        playerDeck = new SinglyLinkedList<String>();
    }

    //Allows other classes to get the linked list
    public SinglyLinkedList<String> getPlayerLinked(){
        return playerDeck;
    }
}
