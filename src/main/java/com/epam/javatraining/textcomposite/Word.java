package com.epam.javatraining.textcomposite;

public class Word extends Entity<Symbol>
        implements Comparable<Word>, TextElementInterface<Word> {

    public Word(String str) {
        super();
        char[] chars = str.toCharArray();
        for(char ch : chars) {
            this.add(new Symbol(ch));
        }
    }

    @Override
    public int compareTo(Word word) {
        return super.compareTo(word);
    }

    @Override
    public Word copy() {
        return (Word) super.copy();
    }
}
