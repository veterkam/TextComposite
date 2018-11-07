package com.epam.javatraining.textcomposite;

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

    @Override
    public int compareTo(Sentence sentence) {
        return super.compareTo(sentence);
    }

    @Override
    public Sentence copy() {
        return (Sentence) super.copy();
    }
}