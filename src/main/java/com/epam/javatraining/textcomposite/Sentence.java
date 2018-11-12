package com.epam.javatraining.textcomposite;

import java.util.Collections;
import java.util.List;

public class Sentence extends Entity<Word>
        implements Comparable<Sentence>, TextElementInterface<Sentence> {

    private final String REGEX_WORD_DELIMITER = "[\\s.,?!'\":;_+=(){}\\[\\]|<>&-]+";

    public Sentence(String str) {
        super();
        // Cut text
        TextCutter cutter = new TextCutter(str, REGEX_WORD_DELIMITER);

        boolean isSepFirst = cutter.isSeparatorFirst();
        List<String> first = isSepFirst ? cutter.getSeparators() : cutter.getElements();
        List<String> second = !isSepFirst ? cutter.getSeparators() : cutter.getElements();

        // Assemble list of words and separators
        int fi = 0;
        int si = 0;
        Word word;
        while( fi < first.size() && si < second.size()) {
            word = new Word(first.get(fi));
            word.setSeparator(isSepFirst);
            this.add(word);

            word = new Word(second.get(si));
            word.setSeparator(!isSepFirst);
            this.add(word);

            fi++;
            si++;
        }

        if(fi < first.size()) {
            word = new Word(first.get(fi));
            word.setSeparator(isSepFirst);
            this.add(word);
        }

        if(si < second.size()) {
            word = new Word(second.get(si));
            word.setSeparator(!isSepFirst);
            this.add(word);
        }
    }

    public Sentence(Entity<Word> s) {
        super(s);
    }

    @Override
    public int compareTo(Sentence other) {

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
    public Sentence copy() {
        return new Sentence( super.copy() );
    }

    public int countRealWord() {
        int count = 0;
        for(int i = 0; i < size(); i++) {
            if(!get(i).isSeparator()) {
                count++;
            }
        }
        return count;
    }
}