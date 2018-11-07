package com.epam.javatraining.textcomposite;

import java.util.List;

public class Paragraph extends Entity<Sentence>
        implements Comparable<Paragraph>, TextElementInterface<Paragraph> {

    private final String REGEX_SENTENCE_DELIMITER = "[.!?]+";

    public Paragraph(String str) {
        super();

        // Cut text
        TextCutter cutter = new TextCutter(str, REGEX_SENTENCE_DELIMITER);

        boolean isSepFirst = cutter.isSeparatorFirst();
        List<String> first = isSepFirst ? cutter.getSeparators() : cutter.getElements();
        List<String> second = !isSepFirst ? cutter.getSeparators() : cutter.getElements();

        // Assemble list of words and separators
        int fi = 0;
        int si = 0;
        Sentence sentence;
        while( fi < first.size() && si < second.size()) {
            sentence = new Sentence(first.get(fi));
            sentence.setSeparator(isSepFirst);
            this.add(sentence);

            sentence = new Sentence(second.get(si));
            sentence.setSeparator(!isSepFirst);
            this.add(sentence);

            fi++;
            si++;
        }

        if(fi < first.size()) {
            sentence = new Sentence(first.get(fi));
            sentence.setSeparator(isSepFirst);
            this.add(sentence);
        }

        if(si < second.size()) {
            sentence = new Sentence(second.get(si));
            sentence.setSeparator(!isSepFirst);
            this.add(sentence);
        }

    }

    @Override
    public int compareTo(Paragraph paragraph) {
        return super.compareTo(paragraph);
    }

    @Override
    public Paragraph copy() {
        return (Paragraph) super.copy();
    }
}