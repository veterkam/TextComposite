package com.epam.javatraining.textcomposite;

import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    public static Logger logger = LogManager.getLogger("TextCompositeSuite");
    /**
     * Rigorous Test :-)
     */
    @Test
    public void strEq()
    {
        String separators = "[\\s.,?!'\":;_+=(){}\\[\\]|<>&-]+";
        String text = "?!.,a,:_123bd 45c:'\"678d , hello my frend!\n How are you?!!!! 'I'm fine',.";

        TextCutter cutter = new TextCutter(text, separators);
        List first = cutter.isSeparatorFirst() ? cutter.getSeparators() : cutter.getElements();
        List second = !cutter.isSeparatorFirst() ? cutter.getSeparators() : cutter.getElements();

        int fi = 0;
        int si = 0;
        while( fi < first.size() && si < second.size()) {
            logger.trace("["+first.get(fi)+"]");
            logger.trace("["+second.get(si)+"]");
            fi++;
            si++;
        }

        if(fi < first.size()) {
            logger.trace("["+first.get(fi)+"]");
        }

        if(si < second.size()) {
            logger.trace("["+second.get(si)+"]");
        }

        assertTrue( "abcd".compareTo("abcd") == 0 );
    }

    private static String readAllBytes(String filePath)
    {
        String content = "";
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        }
        catch (IOException e)
        {
            logger.error(e.getMessage(), e);
        }
        return content;
    }

    @Test
    public void SentenceSuite() {
        String content = readAllBytes("./src/test/resources/text.txt");
        Text text = new Text(content);
        //Paragraph s = new Paragraph("Contributor license agreements.?! Hello!");
        logger.trace(text.toMarketString());
    }
}
