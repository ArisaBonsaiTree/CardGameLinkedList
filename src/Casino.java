import java.util.Random;
import java.util.Scanner;

public class Casino {

        private Scanner scanner = new Scanner(System.in);
        private Random rand = new Random();

        private Card card;
        private Player playerOne;
        private Player playerTwo;

        private final int MAX_ROUNDS = 10;

        //Find use for these?
        private static int playerNumberOneCard;
        private static int playerNumberTwoCard;

        //For 2D Arrays
        //[x][] --> row
        //[][y] --> col
        private int col = 5;
        private int row = 3;

        private String twoDArray[][] = new String[row][col];

        //LinkedList for the table --> stack
        private SinglyLinkedList tableList = new SinglyLinkedList<String>();

        //Constructor to set the objects and start the game
        public Casino(Card card, Player playerOne, Player playerTwo){
            this.card = card;
            this.playerOne = playerOne;
            this.playerTwo = playerTwo;
            setGame();
            playGame();
        }

        //Place [0000] in the 2D array, cards in player linked list, and get the size of the player list
        private void setGame(){
            //Fill the entire array with "[0000]"
            fillWithZero();
            flipCoin();

            //Place cards into player LinkedList
            card.linkedListShuff(playerOne);
            card.linkedListShuff(playerTwo);

            //Place the number of cards in the ints
            playerNumberOneCard = playerOne.getPlayerLinked().size();
            playerNumberTwoCard = playerTwo.getPlayerLinked().size();
        }

        private void playGame(){

            String playerTwoCard = null;
            String playerOneCard = null;
            int currentRound = 1;
            int cardCount = 0;
            //Since .size isnt working as planned...

            // || playerOne.getPlayerDeck().size() == Card.getNumberOfCards()
            //            || playerTwo.getPlayerDeck().size() == Card.getNumberOfCards()|| !playerTwo.getPlayerDeck().isEmpty()
            //            ||!playerOne.getPlayerDeck().isEmpty())
            //Int to String String.valueOF
            //String to Int Integer.parseInt
            setTwoDArray(2, 0, String.valueOf(playerNumberOneCard));
            setTwoDArray(0, 4, String.valueOf(playerNumberTwoCard));


            while(currentRound <= MAX_ROUNDS){
                System.out.println();
                System.out.println("\t\tRound: " + currentRound);
                printArray();

                //Add the first card of player one into the table list
                tableList.addFirst(playerOne.getPlayerLinked().removeFirst());
                cardCount++;
                //Declares what card was placed
                System.out.println();
                System.out.println("\tPlayer ONE placed " + tableList.first());
                setTwoDArray(1,1, (String) tableList.first(), 0);
                playerNumberOneCard -= 1;
                setTwoDArray(2, 0, String.valueOf(playerNumberOneCard));
                printArray();

                //Place into a String so we can compare with another one
                playerOneCard = (String) tableList.first();

                //Checks to see if playerOne card is equal to playerTwo
                if(playerOneCard.equals(playerTwoCard)){
                    System.out.println("PLAYER ONE HAS A MATCHING CARD");
                    for(int i = 0; i < cardCount; i++){
                        playerOne.getPlayerLinked().addLast((String) tableList.first());
                        tableList.removeFirst();
                    }
                    playerTwoCard = "TWONULL";
                    playerOneCard = "ONENULL";
                    currentRound++;

                    playerNumberOneCard += cardCount;
                    setTwoDArray(2, 0, String.valueOf(playerNumberOneCard));
                    cardCount = 0;

                    emptyPlace(1,1);
                    emptyPlace(1,2);

                    System.out.println("NEXT ROUND");

                    System.out.println("============================");
                    System.out.println("============================");
                    System.out.println("============================");
                    System.out.println("\nType anything to move on to the next round");
                    String buffer = scanner.nextLine();
                    continue;
                }

                tableList.addFirst(playerTwo.getPlayerLinked().removeFirst());
                cardCount++;

                System.out.println();
                System.out.println("\tPlayer TWO placed " + tableList.first());
                setTwoDArray(1,2, (String) tableList.first(), 0);
                playerNumberTwoCard--;
                setTwoDArray(0, 4,  Integer.toString(playerNumberTwoCard));
                printArray();
                playerTwoCard = (String) tableList.first();

                if(playerTwoCard.equals(playerOneCard)){
                    System.out.println("PLAYER TWO HAS A MATCHING CARD");
                    for(int i = 0; i < cardCount; i++){
                        playerTwo.getPlayerLinked().addLast((String) tableList.first());
                        tableList.removeFirst();
                    }
                    playerTwoCard = "TWONULL";
                    playerOneCard = "ONENULL";
                    currentRound++;
                    playerNumberTwoCard += cardCount;
                    cardCount = 0;
                    //System.out.print("Current stack ");
                    //tableList.printList();

                    setTwoDArray(0, 4, String.valueOf(playerNumberTwoCard));
                    emptyPlace(1,1);
                    emptyPlace(1,2);
                    System.out.println("NEXT ROUND");

                    System.out.println("============================");
                    System.out.println("============================");
                    System.out.println("============================");
                    System.out.println("\nType anything to move on to the next round");
                    String buffer = scanner.nextLine();
                    continue;
                    }

                System.out.print("Current stack ");

                tableList.printList();

                System.out.println("\nEND OF ROUND " + currentRound);
                System.out.println("============================");
                System.out.println("============================");
                System.out.println("============================");
                currentRound++;
                System.out.println("\nType anything to move on to the next round");
                String buffer = scanner.nextLine();
            }

            //The checkers can go here
            if(playerNumberOneCard> playerNumberTwoCard){
                System.out.println("Player ONE WINS!");
            }else if(playerNumberOneCard < playerNumberTwoCard){
                System.out.println("Player TWO WINS!");
            }
            else
                System.out.println("TIE");
        }

    //Ask user for heads/tails and decide who goes first --> Doesnt affect the game yet
    private void flipCoin() {
        System.out.println("Heads or tails?");
        String userInput;

        try {
            userInput = scanner.nextLine();
        }catch (Exception e){
            System.out.println("Invalid input, you will not get neither");
            userInput = "neither";
        }

        userInput = userInput.toLowerCase();
        int coin = rand.nextInt(2);
        String[] coinArray = {"heads", "tails"};

        if(userInput.equals(coinArray[coin])){
            System.out.println("Player One will be human");
            System.out.println("Player Two will be NPC");
        }

    }

    private void emptyPlace(int row, int col){
            twoDArray[row][col] = "[0000]";
        }
        private void setTwoDArray(int row, int col, String data){
            twoDArray[row][col] = "[00" + data + "]";
        }

        private void setTwoDArray(int row, int col, String data, int random){
            twoDArray[row][col] = "[" + data + "]";
        }

        private void printArray(){
            for (int i = 0; i < row; i++){
                for(int j = 0; j < col; j++){
                    System.out.print(twoDArray[i][j] + "\t");
                }
                System.out.println();
            }
        }

        private void fillWithZero() {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    twoDArray[i][j] = "[0000]";
                }
            }
        }

    }

