package com.epam.javatraining.textcomposite;

import java.util.List;

public class Text extends Entity<Paragraph>
        implements Comparable<Text>, TextElementInterface<Text> {

    private final String REGEX_PARAGRAPH_DELIMITER = "(\r\n|\n)+\\s+(?=[A-ZА-Я\'\"-])";

    public Text(String str) {
        super();

        // Cut text
        TextCutter cutter = new TextCutter(str, REGEX_PARAGRAPH_DELIMITER);

        boolean isSepFirst = cutter.isSeparatorFirst();
        List<String> first = isSepFirst ? cutter.getSeparators() : cutter.getElements();
        List<String> second = !isSepFirst ? cutter.getSeparators() : cutter.getElements();

        // Assemble list of words and separators
        int fi = 0;
        int si = 0;
        Paragraph paragraph;
        while( fi < first.size() && si < second.size()) {
            paragraph = new Paragraph(first.get(fi));
            paragraph.setSeparator(isSepFirst);
            this.add(paragraph);

            paragraph = new Paragraph(second.get(si));
            paragraph.setSeparator(!isSepFirst);
            this.add(paragraph);

            fi++;
            si++;
        }

        if(fi < first.size()) {
            paragraph = new Paragraph(first.get(fi));
            paragraph.setSeparator(isSepFirst);
            this.add(paragraph);
        }

        if(si < second.size()) {
            paragraph = new Paragraph(second.get(si));
            paragraph.setSeparator(!isSepFirst);
            this.add(paragraph);
        }
    }

    @Override
    public int compareTo(Text text) {
        return super.compareTo(text);
    }

    @Override
    public Text copy() {
        return (Text) super.copy();
    }
}
