public class PlayButton {

    public static void main(String[] args) {

        //Player objects
        Player playerOne = new Player();
        Player playerTwo = new Player();

        //Card objects
        Card card = new Card();

        //Start the game --> No need to give it a name, we will never use it!
        new Casino(card, playerOne, playerTwo);

    }
}
