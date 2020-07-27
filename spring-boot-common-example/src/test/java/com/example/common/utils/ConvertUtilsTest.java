package com.example.common.utils;

import org.example.common.utils.ConvertUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

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

    /**
     * 특정 필드 제외 테스트
     */
    @Test
    public void convertObjToStringExcludeFieldsTest() {
        TestObject testObject = new TestObject();
        testObject.setA("aaa");
        testObject.setB("bbb");
        String str = ConvertUtils.convertObjToStringExcludeFields(
                testObject, ",", new HashSet<>(Arrays.asList("a")));
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
