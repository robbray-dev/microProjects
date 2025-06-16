import java.util.HashMap;
import java.util.Stack;
//pretty much done, the only question is why doesn't the final count equal 54, I'll fix it later.
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


    private static Player warLogic(Player playerOne, Player playerTwo){


        Card playerOnePlayingCard;
        Card playerTwoPlayingCard;

        if(playerOne.getHandCards().size()>=4){
            for(int i = 0; i < 4; i++){
                playPile.push(playerOne.getHandCards().pop());
            }
        } else {
            if(!populateHandCardsFromCollectedCards(playerOne)){
                //handle playerTwo winning cards in the game loop
                return playerTwo;
            }
        }

        if(playerTwo.getHandCards().size()>=4){
            for(int i = 0; i < 4; i++){
                playPile.push(playerTwo.getHandCards().pop());
            }
        } else {
            if(!populateHandCardsFromCollectedCards(playerTwo)){
                //handle playerOne winning cards in the game loop

                return playerOne;
            }
        }

        playerOnePlayingCard = playerOne.placeCardIntoPlayPile();
        if(playerOnePlayingCard == null){
            if(playerOne.getCollectedCards().size() > 0) {
                System.out.println("One works");
                playerOne.populateHandCard(playerOne.getCollectedCards().pop());
                playerOnePlayingCard = playerOne.placeCardIntoPlayPile();
            } else {
                return playerTwo;
            }

        }



        playerTwoPlayingCard = playerTwo.placeCardIntoPlayPile();

        if (playerTwoPlayingCard == null){
            if(playerTwo.getCollectedCards().size() > 0){
                System.out.println("Two works");
                playerTwo.populateHandCard(playerTwo.getCollectedCards().pop());
                playerTwoPlayingCard = playerTwo.placeCardIntoPlayPile();
            } else {
                return playerOne;
            }
        }

        return whoWonRound(playerOne, playerTwo, playerOnePlayingCard, playerTwoPlayingCard);


    }

    // DOUBLE CHECK THIS FOR EDGE CASES
    private static Player whoWonRound(Player playerOne, Player playerTwo, Card playerOneCard, Card playerTwoCard){


        if (isInteger(playerOneCard.getCardValue()) && isInteger(playerTwoCard.getCardValue())) {
            int oneNum = Integer.parseInt(playerOneCard.getCardValue());
            int twoNum = Integer.parseInt(playerTwoCard.getCardValue());
            if(oneNum == 1 && twoNum != 1){
                return playerOne;
            } else if (twoNum == 1 && oneNum != 1) {
                return playerTwo;
            } else {
               return warLogic(playerOne,playerTwo);
            }
        } else if (isInteger(playerOneCard.getCardValue()) && !isInteger(playerTwoCard.getCardValue())) {
            return playerTwo;
        } else if (isInteger(playerTwoCard.getCardValue()) && !isInteger(playerOneCard.getCardValue())) {
            return playerOne;
        } else{
            if(getFaceRanks().get(playerOneCard.getCardValue()) > getFaceRanks().get(playerTwoCard.getCardValue())){
                return playerOne;
            } else if(getFaceRanks().get(playerTwoCard.getCardValue()) > getFaceRanks().get(playerOneCard.getCardValue())){
                return playerTwo;
            } else{
                return warLogic(playerOne,playerTwo);
            }
        }

    }

    public static void redeemCards(Player winner){
        while(!playPile.isEmpty()){
            winner.populateCollectedCard(playPile.pop());
        }
    }


    private static boolean populateHandCardsFromCollectedCards(Player player){
        boolean success = true;
        if(player.getCollectedCards().size() >= 4){
            for (int i = 0; i < 4; i++) {
                player.populateHandCard(player.getCollectedCards().pop());

            }
        } else{
            success = false;
        }
        return success;

    }



    // MUST FINISH THIS
    public void playGame(){
        GameLoop game = new GameLoop();
        Card[] gameCards = game.intializeAndShuffleDeck();

        Player player_One = new Player();
        Player player_Two = new Player();


        game.doDeal(gameCards,player_One,player_Two);
        int roundCount = 0;
        while(true){
            System.out.println("The beginning of round " + roundCount + " there are " + player_One.getHandCards().size()  + " for player 1 and " + player_Two.getHandCards().size() + " for player 2");
            if(player_One.getHandCards().isEmpty()){
                if (!player_One.getCollectedCards().isEmpty()){
                    player_One.shuffleCollectedCard();
                    player_One.populateHandCard(player_One.getCollectedCards().pop());
                } else {
                    //player two won and player one lost.

                    //player two get player one hand cards and collected cards

                    //playerTwo gets the play pile cards
                    redeemCards(player_Two);

                    int countForOne = player_One.getCollectedCards().size()+player_One.getHandCards().size();
                    int countForTwo = player_Two.getCollectedCards().size()+player_Two.getHandCards().size();

                    System.out.println("On round " + roundCount + " Player Two won this game with a card count of " +countForTwo + " versus Player One who now has a card count of "  +
                            countForOne + " Thanks for playing.");
                    break;
                }
            }

            if(player_Two.getHandCards().isEmpty()){
                if(!player_Two.getCollectedCards().isEmpty()){
                    player_Two.shuffleCollectedCard();
                    player_Two.populateHandCard(player_Two.getCollectedCards().pop());
                } else {
                    //player one lost and player two one

                    //player one gets player two cards.

                    //playerOne gets the play pile cards
                    redeemCards(player_One);
                    int countForOne = player_One.getCollectedCards().size()+player_One.getHandCards().size();
                    int countForTwo = player_Two.getCollectedCards().size()+player_Two.getHandCards().size();
                    System.out.println("On round " + roundCount + " Player One won this game with a card count of " + countForOne + " versus Player Two who now has a card count of "  +
                            countForTwo + " Thanks for playing.");
                    break;
                }
            }

            Card playerOneCardInGame = player_One.placeCardIntoPlayPile();
            Card playerTwoCardInGame = player_Two.placeCardIntoPlayPile();

            playPile.push(playerOneCardInGame);

            playPile.push(playerTwoCardInGame);

            Player winnerThisTime = whoWonRound(player_One,player_Two,playerOneCardInGame,playerTwoCardInGame);

            if(winnerThisTime == player_One){
                System.out.println("On round " + roundCount +" Player One won this round with " + playerOneCardInGame.getCardValue() + " of " +
                        playerOneCardInGame.getSuit() + " while Player Two lost this round with " + playerTwoCardInGame.getCardValue() +
                        " of " + playerTwoCardInGame.getSuit());
                redeemCards(player_One);

                System.out.println("For round " + roundCount);
                System.out.println("Player One Count of hand cards: " + player_One.getHandCards().size());
                System.out.println("Player One Count of collected cards: " + player_One.getCollectedCards().size());
                System.out.println("Player Two Count of hand cards: " + player_Two.getHandCards().size());
                System.out.println("Player Two Count of collected cards: " + player_Two.getCollectedCards().size());
            }

            if (winnerThisTime == player_Two){
                System.out.println("On round " + roundCount + " Player Two won this round with " + playerTwoCardInGame.getCardValue() + " of " +
                        playerTwoCardInGame.getSuit() + " while Player One lost this round with " + playerOneCardInGame.getCardValue() +
                        " of " + playerOneCardInGame.getSuit());

                redeemCards(player_Two);

                System.out.println("For round " + roundCount);
                System.out.println("Player One Count of hand cards: " + player_One.getHandCards().size());
                System.out.println("Player One Count of collected cards: " + player_One.getCollectedCards().size());
                System.out.println("Player Two Count of hand cards: " + player_Two.getHandCards().size());
                System.out.println("Player Two Count of collected cards: " + player_Two.getCollectedCards().size());

            }

            roundCount++;
        }

    }

    public static void main(String[] args){
        GameLoop newGame = new GameLoop();

        newGame.playGame();




    }

}
