package com.wico;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.parse.ParseObject;

import junit.framework.TestResult;

public class ApplicationTest extends ApplicationTestCase<Application>{

    public ApplicationTest(){
        super(Application.class);
    }

    public void test() throws Exception{
        final int expected = 1;
        final int reality = 1;
        assertEquals(expected, reality);
    }

    public void testObjectStorage() throws Exception{
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }

    public void testMultipleObjectStorage() throws Exception{
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.put("foo1", "bar1");
        testObject.put("foo2", "bar2");
        testObject.put("foo3", "bar3");
        testObject.saveInBackground();
    }
}