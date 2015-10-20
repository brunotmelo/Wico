package com.wico.ui.dummy;

import com.wico.datatypes.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add 3 sample items.
        addItem(new DummyItem("1", null));
        addItem(new DummyItem("2", null));
        addItem(new DummyItem("3", null));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String title;
        public String content;

        public DummyItem(String id, Question question) {
            question = new Question("is course good?", "i was wondering if the course was good, and if i would have to work a lot.");
            this.id = id;
            this.title = question.getTitle();
            this.content = question.getContent();
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
