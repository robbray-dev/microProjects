public class GameLoop {

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

    public Player whoWonRound(Player playerOne, Player playerTwo){
        Card playerOneCard = playerOne.placeCardIntoPlayPile();
        Card playerTwoCard = playerTwo.placeCardIntoPlayPile();
        if(playerTwoCard == null || playerOneCard == null){
            System.out.println("player has no more in hand cards. implement the out of hand card logic");
            
        }
        if(isInteger(playerOneCard.getCardValue())&&isInteger(playerTwoCard.getCardValue())){
            int oneNum = Integer.parseInt(playerOneCard.getCardValue());
            int twoNum = Integer.parseInt(playerTwoCard.getCardValue());
            if(oneNum>twoNum){
                return playerOne;
            } else if (twoNum>oneNum) {
                return playerTwo;
            } else{
                //war logic
            }
        } else if (isInteger(playerOneCard.getCardValue()) && isInteger(playerTwoCard.getCardValue()) == false) {
            //make a method for face versus number
        } else if(isInteger(playerOneCard.getCardValue()) == false && isInteger(playerTwoCard.getCardValue())){
            //method for face versus number
        } else{
            // face versus face logic.
        }

    }

    public static void main(String[] args){

        GameLoop game = new GameLoop();
        Card[] gameCards = game.intializeAndShuffleDeck();

        Player player_One = new Player();
        Player player_Two = new Player();

        game.doDeal(gameCards,player_One,player_Two);


    }

}
