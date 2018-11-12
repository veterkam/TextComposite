package com.epam.javatraining.textcomposite;

import java.util.Collections;
import java.util.List;

public class Text extends Entity<Paragraph>
        implements Comparable<Text>, TextElementInterface<Text> {

    private final String REGEX_PARAGRAPH_DELIMITER = "(\r\n|\n)+\\s+(?=[A-ZА-Я\'\"-])";

    public Text(String str) {
        super();
        // Cut text
        List<String> list = TextCutter.cut(str, REGEX_PARAGRAPH_DELIMITER);

        for(String s : list) {
            this.add(new Paragraph(s));
        }
    }

    public Text(Entity<Paragraph> t) {
        super(t);
    }

    @Override
    public int compareTo(Text other) {

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
    public Text copy() {
        return new Text( super.copy() );
    }

    public boolean contains(Word word) {

        for(Paragraph paragraph : elements) {
            if(paragraph.contains(word)) {
                return true;
            }
        }

        return false;
    }

    public boolean contains(Sentence sentence) {

        for(Paragraph paragraph : elements) {
            if(paragraph.contains(sentence)) {
                return true;
            }
        }

        return false;
    }
}
