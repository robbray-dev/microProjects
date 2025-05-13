public class Card{
    private String cardValue;
    private String suit;
    private boolean isItCollected;
    private boolean isItInHand;



    public Card(String cardValue, String suit, boolean isItCollected, boolean isItInHand){
        this.cardValue = cardValue;
        this.suit = suit;
        this.isItCollected = isItCollected;
        this.isItInHand = isItInHand;
    }

    public Card(){
        System.out.println("You have to intialize the card");
    }

    public String getCardValue(){
        return this.cardValue;
    }

    public String getSuit(){
        return this.suit;
    }

    public boolean getIsItCollected(){
        return this.isItCollected;
    }

    public boolean getIsItInHand(){
        return this.isItInHand;
    }

    public void setCardValue(String newValue){
        this.cardValue = newValue;
    }

    public void setSuit(String suitValue){
        this.suit = suitValue;
    }

    public void setIsItCollected(boolean val){
        this.isItCollected = val;
    }

    public void setIsItHandled(boolean val){
        this.isItInHand = val;
    }
}

