public class Deck {
    private Card[] DeckOfCards;

    public Deck() {
        this.DeckOfCards = intializeCards(new Card[54]);

    }



    public Card[] intializeCards(Card[] cards){
        for (int i = 0; i < 13; i++) {
            if(i < 10) {
                cards[i] = new Card(Integer.toString(i+1),"heart",false,false);
                cards[i+13] = new Card(Integer.toString(i+1),"clubs",false,false);
                cards[i+26] = new Card(Integer.toString(i+1),"spades",false,false);
                cards[i+39] = new Card(Integer.toString(i+1),"diamonds",false,false);
            }else{
                cards[10] = new Card("King", "heart", false, false);
                cards[11] = new Card("Queen", "heart", false, false);
                cards[12] = new Card("Jack", "heart", false, false);
                cards[20] = new Card("King", "clubs", false, false);
                cards[21] = new Card("Queen", "clubs", false, false);
                cards[22] = new Card("Jack", "clubs", false, false);
                cards[30] = new Card("King", "spades", false, false);
                cards[31] = new Card("Queen", "spades", false, false);
                cards[32] = new Card("Jack", "spades", false, false);
                cards[40] = new Card("King", "diamonds", false, false);
                cards[41] = new Card("Queen", "diamonds", false, false);
                cards[42] = new Card("Jack", "diamonds", false, false);
            }
        }
        return cards;
    }

    public static void main(String[] args){
        Deck deckOfCard = new Deck();
        Card[] cardsTo = deckOfCard.DeckOfCards;

        for (int i = 0; i < cardsTo.length; i++) {
            cardsTo[i].printCard(cardsTo[i]);
        }
    }

}
