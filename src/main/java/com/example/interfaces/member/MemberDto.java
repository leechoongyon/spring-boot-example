package com.example.interfaces.member;

import lombok.Getter;

public class MemberDto {

    @Getter
    public static class Create {
        private String id;
        private String name;

        public Create(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Getter
    public static class Base {
        private String id;
        private String name;

        public Base(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
