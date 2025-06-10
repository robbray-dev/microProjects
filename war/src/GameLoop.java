import java.util.HashMap;
import java.util.Stack;

public class GameLoop {

    public static Stack<Card> playPile = new Stack<>();
    private static HashMap<String, Integer> getFaceRanks(){
        HashMap<String, Integer> faceRanks = new HashMap<>();

        faceRanks.put("Joker", 1);
        faceRanks.put("King", 2);
        faceRanks.put("Queen", 3);
        faceRanks.put("Jack", 4);

        return faceRanks;
    }



    //intialize and shuffle deck
    //while loop for game play in main method
    //within the loop, code the rules.
    //player one places a card, then player two
    //have a method to determine whos the winner
    //if draw then place four cards down. and then one card up. repeat if needed.
    //if player runs out of cards in hand then take four collected cards and shuffle them before hand.

    public Card[] intializeAndShuffleDeck(){
        Deck deck = new Deck();
        return deck.shuffleCards(deck.getDeckOfCards());
    }

    public void doDeal(Card[] cards, Player playerOne, Player playerTwo){
        boolean isDeckDone = false;
        int deckCount = 0;

        while(deckCount<54){
            if(deckCount%2==0){
                playerOne.populateHandCard(cards[deckCount]);
            } else{
                playerTwo.populateHandCard(cards[deckCount]);
            }
            deckCount++;
        }
    }

    private static boolean isInteger(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // DOUBLE CHECk THIS FOR EDGE CASES, ESPECIALLY ADDING MORE HAND CARDS
    private static Player warLogic(Player playerOne, Player playerTwo){

        while(true){
            for (int i = 0; i < 4; i++) {
                playPile.push(playerOne.placeCardIntoPlayPile());
                playPile.push(playerTwo.placeCardIntoPlayPile());

            }
            Card playerOneRevaledCard = playerOne.placeCardIntoPlayPile();
            Card playerTwoRevaledCard = playerTwo.placeCardIntoPlayPile();
            playPile.push(playerOneRevaledCard);
            playPile.push(playerTwoRevaledCard);

            if(whoWonRound(playerOne,playerTwo,playerOneRevaledCard, playerTwoRevaledCard) == playerOne){
                return playerOne;
            }  else if (whoWonRound(playerOne,playerTwo,playerOneRevaledCard, playerTwoRevaledCard) == playerTwo) {
                return playerTwo;
            }
        }


    }

    // DOUBLE CHECK THIS FOR EDGE CASES
    private static Player whoWonRound(Player playerOne, Player playerTwo, Card playerOneCard, Card playerTwoCard){

        if(playerTwoCard == null || playerOneCard == null){
            System.out.println("player has no more in hand cards. implement the out of hand card logic");

        }
        if(isInteger(playerOneCard.getCardValue())&&isInteger(playerTwoCard.getCardValue())){
            int oneNum = Integer.parseInt(playerOneCard.getCardValue());
            int twoNum = Integer.parseInt(playerTwoCard.getCardValue());
            if(oneNum>twoNum && twoNum!=1){
                return playerOne;
            } else if (twoNum == 1 && oneNum != 1) {
                return playerTwo;
            } else if (twoNum>oneNum && oneNum!=1) {
                return playerTwo;
            } else if (oneNum ==1 && twoNum != 1) {
                return playerOne;
            } else{
                return warLogic(playerOne, playerTwo);
            }
        } else if (isInteger(playerOneCard.getCardValue()) && isInteger(playerTwoCard.getCardValue()) == false) {
            if(Integer.parseInt(playerOneCard.getCardValue()) == 1){
                return playerOne;
            } else {
                return playerTwo;
            }
        } else if(isInteger(playerOneCard.getCardValue()) == false && isInteger(playerTwoCard.getCardValue())){
            if(Integer.parseInt(playerTwoCard.getCardValue())==1){
                return playerTwo;
            } else{
                return playerOne;
            }

        } else{
            if(getFaceRanks().get(playerOneCard.getCardValue()) > getFaceRanks().get(playerTwoCard.getCardValue())){
                return playerOne;
            } else if(getFaceRanks().get(playerTwoCard.getCardValue())> getFaceRanks().get(playerOneCard.getCardValue())){
                return playerTwo;
            } else {
                return warLogic(playerOne,playerTwo);
            }

        }

    }

    public static void redeemCards(Player winner){
        while(!playPile.isEmpty()){
            winner.populateCollectedCard(playPile.pop());
        }
    }

    // MUST FINISH THIS
    private static void populateHandCardsFromCollectedCards(Player player){
        for (int i = 0; i < 4; i++) {
            if(player.getCollectedCards().size() >= 4){
                player.populateHandCard(player.getCollectedCards().pop());
            } else {
                // game over logic
            }
        }
    }
    
    
    // MUST FINISH THIS
    public void playGame(){
        boolean gameDone = false;
        GameLoop game = new GameLoop();
        Card[] gameCards = game.intializeAndShuffleDeck();

        Player player_One = new Player();
        Player player_Two = new Player();

        game.doDeal(gameCards,player_One,player_Two);

        while(gameDone == false){
            if(player_One.getHandCards().size() == 0){
                if(player_One.getCollectedCards().size() > 0){
                    player_One.shuffleCollectedCard();
                    populateHandCardsFromCollectedCards(player_One);

                }
            }
            if(player_Two.getHandCards().size() == 0){
                if(player_Two.getCollectedCards().size() > 0){
                    player_Two.shuffleCollectedCard();
                    populateHandCardsFromCollectedCards(player_Two);
                }
            }


            Card playerOneCard = player_One.placeCardIntoPlayPile();

            //a check for null and adding more cards if necssary
            playPile.push(playerOneCard);

            Card playerTwoCard = player_Two.placeCardIntoPlayPile();

            //a check for null and adding more cards if necessary
            playPile.push(playerTwoCard);

            Player winner = whoWonRound(player_One,player_Two, playerOneCard, playerTwoCard);

            if(winner!=null){
                redeemCards(winner);
            }

            //if either player runs out of hand cards we populate their hand. by four


        }

    }

    public static void main(String[] args){



    }

}
