package com.example.common.utils;

import org.example.common.utils.ConvertUtils;
import org.junit.Assert;
import org.junit.Test;

public class ConvertUtilsTest {

    @Test
    public void convertObjectToStringAllStrTest() {
        TestObject testObject = new TestObject();
        testObject.setA("aaa");
        testObject.setB("bbb");
        String str = ConvertUtils.convertObjToString(testObject, ",");
        Assert.assertEquals("aaa,bbb", str);
    }

    @Test
    public void convertObjectToStringPartStrTest() {
        TestObject testObject = new TestObject();
        testObject.setB("bbb");
        String str = ConvertUtils.convertObjToString(testObject, ",");
        Assert.assertEquals("bbb", str);
    }

    public static class TestObject {
        private String a;
        private String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }
}
