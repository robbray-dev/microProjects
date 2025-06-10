import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

public class Player {
    private Stack<Card> collectedCards = new Stack<>();
    private Stack<Card> handCards = new Stack<>();

    // make the logic for dealing with hand cards and collected cards.
    // need a way to populate hand cards and collected cards, even though they are private fields
    // shuffling of collected cards and using the populate method to populate cards into the hand.
    // public populate method, that adds to either stack with new cards.
    // populate(Stack choice, card ), push card to stack

    public Player(){
        this.collectedCards = new Stack<>();
        this.handCards = new Stack<>();
    }

    public void populateHandCard(Card cardToAdd){
        this.handCards.push(cardToAdd);
    }

    public void populateCollectedCard(Card cardToAdd){
        this.collectedCards.push(cardToAdd);
    }

    public Stack<Card> getCollectedCards() {
        return this.collectedCards;
    }

    public Stack<Card> getHandCards() {
        return this.handCards;
    }

    public void shuffleCollectedCard(){
        //loop through the cards stack, randomly pop and push onto a new stack, then save the new stack to the old one.
        //turn the stack to a list, then randomly select an index, to remove and then push it to a new stack, do the same till the entire list is traversed.

        ArrayList<Card> listOfStack = new ArrayList<>(this.collectedCards);
        Stack<Card> newCollectedCards = new Stack<>();

        Random random = new Random();

        HashMap<Card,Boolean> cardUsageMap = new HashMap<>();


        for (Card card:
             listOfStack) {
            cardUsageMap.put(card,false);
        }


        int count = listOfStack.size();

        while(count>0){
            int removedInd = random.nextInt(listOfStack.size());
            if(cardUsageMap.get(listOfStack.get(removedInd)) == false){
                Card cardToBeRemoved = listOfStack.get(removedInd);
                newCollectedCards.push(cardToBeRemoved);
                cardUsageMap.put(listOfStack.get(removedInd), true);
                count--;
            }
        }

        this.collectedCards = newCollectedCards;

    }


    //place card into play pile. returns the popped card from the hand.
    public Card placeCardIntoPlayPile(){
        if(!this.handCards.isEmpty()){
            Card removedAndPlacedCard = this.handCards.pop();
            return removedAndPlacedCard;
        }
        return null;
    }



    public static void main(String[] args){
        Player player = new Player();
        Deck deck = new Deck();
        Card[] playingCards = deck.getDeckOfCards();
        deck.shuffleCards(playingCards);

        for (int i = 0; i < 10; i++) {
            player.populateHandCard(playingCards[i]);
        }

        System.out.println("--- Collected cards below ---");

        for (int i = 10; i < 20; i++) {
            player.populateCollectedCard(playingCards[i]);
        }
        for (Card card:
             player.getCollectedCards()) {
            card.printCard(card);
        }

        System.out.println("--- Shuffled Collected cards ---");

        player.shuffleCollectedCard();

        for (Card card:
             player.getCollectedCards()) {
            card.printCard(card);
        }

    }




}
