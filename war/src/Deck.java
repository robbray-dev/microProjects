import java.util.Random;

public class Deck {
    private Card[] DeckOfCards;

    public Deck() {
        this.DeckOfCards = intializeCards(new Card[54]);


    }

    public Card[] getDeckOfCards() {
        return this.DeckOfCards;
    }

    public Card[] intializeCards(Card[] cards){
        String[] faces = {"King", "Queen", "Jack"};
        String[] suits = {"heart", "club","spades","diamonds"};
        int j = 0;
        for(String suit : suits) {
            for(int i = 1; i <=10; i++, j++){
                cards[j] = new Card(Integer.toString(i), suit, false, false);
            }
            for (String face: faces) {
                cards[j] = new Card(face,suit,false,false);
                j++;
            }

        }
        cards[52] = new Card("Joker", "Joker", false, false);
        cards[53] = new Card("Joker", "Joker", false, false);
        return cards;
    }


    public Card[] shuffleCards(Card[] cards){
        //im going for a n * n swap
        Random rand = new Random();
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards.length; j++) {
                int firstIndex = rand.nextInt(cards.length);
                int secondIndex = rand.nextInt(cards.length);
                while(secondIndex == firstIndex){
                    secondIndex = rand.nextInt(cards.length);
                }

                //swap now
                Card temp = cards[firstIndex];
                cards[firstIndex] = cards[secondIndex];
                cards[secondIndex] = temp;
            }
        }
        return cards;
    }

    //really understand how the main method works and that Deck object already has the array of cards
    public static void main(String[] args){
        Deck deckOfCard = new Deck();
        Card[] cardsTo = deckOfCard.DeckOfCards;

        for (int i = 0; i < cardsTo.length; i++) {
            cardsTo[i].printCard(cardsTo[i]);
        }

        Card[] shuffledCards = new Card[54];

        shuffledCards = deckOfCard.shuffleCards(cardsTo);

        for (int i = 0; i < shuffledCards.length; i++) {
            shuffledCards[i].printCard(shuffledCards[i]);
        }



    }

}
