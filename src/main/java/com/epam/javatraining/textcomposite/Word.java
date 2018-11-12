package com.epam.javatraining.textcomposite;

import java.util.Collections;

public class Word extends Entity<Symbol>
        implements TextElementInterface<Word>, Comparable<Word> {
    private boolean isSeparator = false;

    public Word(String str) {
        super();
        char[] chars = str.toCharArray();
        for(char ch : chars) {
            this.add(new Symbol(ch));
        }
    }

    public Word(Entity<Symbol> w) {
        super(w);
    }

    @Override
    public int compareTo(Word other) {

        int size = Math.min(this.size(), other.size());
        for(int i = 0; i < size; i++) {
            int compare = this.get(i).compareTo( other.get(i) );
            if( compare != 0 ) {
                return compare;
            }
        }

        return (this.size() == other.size()) ? 0 : (this.size() < other.size()) ? -1 : 1;
    }

    public void sort() {
        Collections.sort(elements);
    }

    @Override
    public Word copy() {
        Word w = new Word( super.copy() );
        w.setSeparator(isSeparator());
        return w;
    }

    public boolean isSeparator() {
        return isSeparator;
    }

    public void setSeparator(boolean separator) {
        isSeparator = separator;
        for(int i = 0; i < size(); i++) {
            this.get(i).setSeparator(isSeparator);
        }
    }
}
