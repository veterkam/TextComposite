package com.epam.javatraining.textcomposite;

public class Symbol implements Comparable<Symbol>, TextElementInterface<Symbol> {
    private char ch;
    private boolean isSeparator;

    public Symbol(char ch) {
        this.ch = ch;
    }

    public char getChar() {
        return ch;
    }

    public void setChar(char ch) {
        this.ch = ch;
    }

    @Override
    public String toString() {
        return Character.toString(ch);
    }

    @Override
    public String toMarketString() {
        return toString();
    }

    @Override
    public int compareTo(Symbol other) {
        return (this.ch == other.ch) ? 0 : (this.ch < other.ch) ? -1 : 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Symbol other = (Symbol) obj;
        return (this.ch == other.ch);
    }

    @Override
    public Symbol copy() {
        Symbol s = new Symbol(ch);
        s.setSeparator(isSeparator());
        return s;
    }

    public int size() {
        return 1;
    }

    public boolean isSeparator() {
        return isSeparator;
    }

    public void setSeparator(boolean separator) {
        isSeparator = separator;
    }
}
