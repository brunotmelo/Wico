package com.wico.datatypes;

import com.parse.ParseObject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WicoPageTest {

    private WicoPage page;
    private static final String PAGE_TITLE = "testPage";
    private static final String PAGE_CONTENT = "An h1 header\n" +
            "============\n" +
            "\n" +
            "Paragraphs are separated by a blank line.\n" +
            "\n" +
            "2nd paragraph. *Italic*, **bold**, and `monospace`. Itemized lists\n" +
            "look like:\n" +
            "\n" +
            "  * this one\n" +
            "  * that one\n" +
            "  * the other one\n" +
            "\n" +
            "Note that --- not considering the asterisk --- the actual text\n" +
            "content starts at 4-columns in.\n" +
            "\n" +
            "> Block quotes are\n" +
            "> written like so.\n" +
            ">\n" +
            "> They can span multiple paragraphs,\n" +
            "> if you like.\n" +
            "\n" +
            "Use 3 dashes for an em-dash. Use 2 dashes for ranges (ex., \"it's all\n" +
            "in chapters 12--14\"). Three dots ... will be converted to an ellipsis.\n" +
            "Unicode is supported. ☺";
    private static final String PAGE_PATH = "/testPath";

    @Before
    public void initializePage(){
        // although we are not using the internet,
        // the parse API requires registering the classes before using
        ParseObject.registerSubclass(WicoPage.class);
        page = new WicoPage.Builder().title(PAGE_TITLE).content(PAGE_CONTENT).path(PAGE_PATH).build();
    }

    @Test
    public void testSavedPageTitle(){
        final String expected = PAGE_TITLE;
        final String reality = page.getTitle();
        assertEquals(expected,reality);
    }

    @Test
    public void testSavedPageContent(){
        final String expected = PAGE_CONTENT;
        final String reality = page.getContent();
        assertEquals(expected,reality);
    }

    @Test
    public void testSavedPagePath(){
        final String expected = PAGE_PATH;
        final String reality = page.getString("path");
        assertEquals(expected,reality);
    }


}
