import java.util.Random;

public class Card{

    Random random = new Random();

    //Set the max number of cards --> can change later but for now the max cards and types
    static int NUMBER_OF_CARDS = 52;

    //Set the suit's and have it easily changeable
    static String PI = "3.14";
    static String HEART = "_<3_";
    static String OWO = "OwO_";
    static String HAPPY = "^__^";

    //Max amount of each cards i.e 52/4 --? 13
    private int MAX_PI = NUMBER_OF_CARDS/4;
    private int MAX_HEART = NUMBER_OF_CARDS/4;
    private int MAX_OWO = NUMBER_OF_CARDS/4;
    private int MAX_HAPPY = NUMBER_OF_CARDS/4;

    //Counter for each card
    private int numOFPiCards = 0;
    private int numOFHeartCards = 0;
    private int numOFOWOCards = 0;
    private int numOFHappyCards = 0;

    //Places cards into players LinkedList instead of arrays!
    public void linkedListStuff(Player player){
        for(int i = 0; i < NUMBER_OF_CARDS/2; i++)
            player.getPlayerLinked().addLast(getLinkCard());
    }

    //Places cards using linkedList!
    public String getLinkCard(){
        //Will only run half of the max cards and cap with the limits given
        int flipFourSide = random.nextInt(4);

        switch (flipFourSide){
            case 0:
                if(numOFPiCards <= MAX_PI){
                    numOFPiCards++;
                    return PI;
                }
            case 1:
                if(numOFHeartCards <= MAX_HEART){
                    numOFHeartCards++;
                    return HEART;

                }
            case 2:
                if(numOFOWOCards <= MAX_OWO){
                    numOFOWOCards++;
                    return OWO;
                }
            case 3:
                if(numOFHappyCards <= MAX_HAPPY){
                    numOFHappyCards++;
                    return HAPPY;
                }
        }

        //Reruns it if the conditions don't meet
        //Gotta practice recursion somewhere ;/
        return getLinkCard();
    }

}
