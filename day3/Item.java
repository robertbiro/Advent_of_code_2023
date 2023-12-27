package day3;

public class Item {
    private char symbol;
    private boolean isInvolved;

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public boolean isInvolved() {
        return isInvolved;
    }

    public void setInvolved(boolean involved) {
        isInvolved = involved;
    }

    public Item(char symbol) {
        this.symbol = symbol;
        this.isInvolved = false;
    }
}