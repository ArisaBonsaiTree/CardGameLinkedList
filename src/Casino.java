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

        private String playerOneCard;
        private String playerTwoCard;
        private String buffer;

        private int currentRound = 1;
        private int cardCount = 0;

        //Constructor to set the objects and start the game
        public Casino(Card card, Player playerOne, Player playerTwo){
            this.card = card;
            this.playerOne = playerOne;
            this.playerTwo = playerTwo;
            setGame();
            playGame();
            declareWinner();
        }

        //Place [0000] in the 2D array, cards in player linked list, and get the size of the player list
        private void setGame(){
            //Fill the entire array with "[0000]"
            fillWithZero();
            flipCoin();

            //Place cards into player LinkedList
            card.linkedListStuff(playerOne);
            card.linkedListStuff(playerTwo);

            //Place the number of cards in the ints
            placePlayerCardSizeArray(2, 0, String.valueOf(playerOne.getPlayerLinked().size()));
            placePlayerCardSizeArray(0, 4, String.valueOf(playerTwo.getPlayerLinked().size()));
        }

        private void playGame(){
            //Int to String String.valueOF
            //String to Int Integer.parseInt

            //TODO Swap turn when a player wins
            while(currentRound <= MAX_ROUNDS){
                System.out.println();
                System.out.println("\t\tRound: " + currentRound);
                printTable();

                //Add the first card of player one into the table list
                playerRemoveFirstAndPlaceCardOnTable(playerOne, 1,1,2,0, playerOneCard, "PlayerOne");
                printTable();

                //Checks to see if playerOne card is equal to playerTwo
                if(playerOneCard.equals(playerTwoCard)){
                    addCardsToWinner("Player One", playerOne);
                    continue;
                }

                playerRemoveFirstAndPlaceCardOnTable(playerTwo, 1,2,0,4, playerTwoCard, "NPC");
                printTable();

                if(playerTwoCard.equals(playerOneCard)){
                    addCardsToWinner("NPC", playerTwo);
                    continue;
                }

                //Print the current stack of cards
                System.out.print("Current stack: ");
                tableList.printList();

                //Add some cool deco
                System.out.println("\n\tEND OF ROUND " + currentRound);
                System.out.println("============================");
                System.out.println("============================");
                System.out.println("============================");
                currentRound++;
                System.out.println("\nType anything to move on to the next round");
                buffer = scanner.nextLine();
            }

        }

    private void playerRemoveFirstAndPlaceCardOnTable(Player player, int tableRow, int tableCol, int playRow, int playCol, String cardData, String playerName) {
        tableList.addFirst(player.getPlayerLinked().removeFirst());
        cardCount++;
        //Declares what card was placed
        System.out.println();
        System.out.println("\tPlayer" + playerName + " placed " + tableList.first());
        placeTableCard(tableRow,tableCol, (String) tableList.first(), 0);
        placePlayerCardSizeArray(playRow, playCol, String.valueOf(player.getPlayerLinked().size()));
        if(player.equals(playerOne))
            playerOneCard = (String) tableList.first();
        else if(player.equals(playerTwo))
            playerTwoCard = (String) tableList.first();
    }

    private void addCardsToWinner(String whoWon,Player player) {
        System.out.println("PLAYER " + whoWon + " HAS A MATCHING CARD");

        for(int i = 0; i < cardCount; i++){
            player.getPlayerLinked().addLast((String) tableList.first());
            tableList.removeFirst();
        }

        playerTwoCard = "TWONULL";
        playerOneCard = "ONENULL";

        currentRound++;

        placePlayerCardSizeArray(2, 0, String.valueOf(player.getPlayerLinked().size()));

        emptyPlace(1,1);
        emptyPlace(1,2);

        System.out.println("NEXT ROUND");

        System.out.println("============================");
        System.out.println(whoWon + " won this round! Plus " + cardCount + " cards");
        cardCount = 0;
        System.out.println("============================");
        System.out.println("\nType anything to move on to the next round");
        buffer = scanner.nextLine();
    }

    private void declareWinner() {
        if(playerOne.getPlayerLinked().size() > playerTwo.getPlayerLinked().size()){
            System.out.println("Player ONE WINS!");
        }else if(playerOne.getPlayerLinked().size() < playerTwo.getPlayerLinked().size()){
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

    //When given location it will 'zero out' that location --> Good for clearing the table after a winner
    private void emptyPlace(int row, int col){
            twoDArray[row][col] = "[0000]";
        }

    //Places a two digit value into the array uniformly
    //Needed since ints don't have '00' 26 --> Need to hard code the 00
    private void placePlayerCardSizeArray(int row, int col, String data){
        twoDArray[row][col] = "[00" + data + "]";
    }

    //Place a 4 length data correctly
    private void placeTableCard(int row, int col, String data, int random){
        twoDArray[row][col] = "[" + data + "]";
    }

    //Print the map for our viewing pleasure
    private void printTable(){
        for (int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                System.out.print(twoDArray[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //Fill the entire 2D array with zero's
    private void fillWithZero() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                twoDArray[i][j] = "[0000]";
            }
        }
    }

}

