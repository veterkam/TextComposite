package com.epam.javatraining.textcomposite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Paragraph extends Entity<Sentence>
        implements Comparable<Paragraph>, TextElementInterface<Paragraph> {

    private final String REGEX_SENTENCE_DELIMITER = "[.!?]+(\\s*)";

    public Paragraph(String str) {
        super();
        // Cut text
        List<String> list = TextCutter.cut(str, REGEX_SENTENCE_DELIMITER);

        for(String s : list) {
            this.add(new Sentence(s));
        }
    }

    public Paragraph(Entity<Sentence> p) {
        super(p);
    }

    @Override
    public int compareTo(Paragraph other) {

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
    public Paragraph copy() {
        return new Paragraph( super.copy() );
    }

    public boolean contains(Word word) {

        for(Sentence sentence : elements) {
            if(sentence.contains(word)) {
                return true;
            }
        }

        return false;
    }
}