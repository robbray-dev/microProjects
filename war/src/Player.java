import java.util.ArrayList;
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

    public void populateCard(Stack<Card> setOfCards, Card cardToAdd){
        setOfCards.push(cardToAdd);
    }

    public void shuffleCollectedCard(){
        //loop through the cards stack, randomly pop and push onto a new stack, then save the new stack to the old one.
        //turn the stack to a list, then randomly select an index, to remove and then push it to a new stack, do the same till the entire list is traversed.

        ArrayList<Card> listOfStack = new ArrayList<>(this.collectedCards);
        Stack<Card> newCollectedCards = new Stack<>();

        Random random = new Random();

        //loop through, grab a random index and push it to the new stack, then delete the index from the list


        //come back to this because the size of the stack changes when you remove from it.
        for (int i = 0; i < listOfStack.size(); i++) {
            int removedInd = random.nextInt(listOfStack.size());
            Card cardToBeRemoved = listOfStack.get(removedInd);
            newCollectedCards.push(cardToBeRemoved);
            listOfStack.remove(removedInd);
        }

    }





}
