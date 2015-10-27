package com.wico;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * Created by Bruno on 18/10/2015.
 */
public class QuestionTest extends ApplicationTestCase<Application> {
    public QuestionTest() {
        super(Application.class);
    }

    public void testCreation() throws Exception {
        final int expected = 1;
        final int reality = 1;
        assertEquals(expected, reality);
    }



}

