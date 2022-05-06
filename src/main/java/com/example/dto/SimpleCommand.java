package com.example.dto;

import lombok.*;

import java.util.List;

public class SimpleCommand {
    @Getter @Setter
    @ToString
    public static class Request {
        private final String key;
        private final String name;

        public Request(String key, String name) {
            this.key = key;
            this.name = name;
        }
    }

    @Getter @Setter
    @ToString
    public static class ListRequest {
        private final String key;
        private final List<Item> items;

        public ListRequest(String key, List<Item> items) {
            this.key = key;
            this.items = items;
        }
    }

    @Getter @Setter
    @ToString
    public static class Item {
        private final String itemName;

        public Item(String itemName) {
            this.itemName = itemName;
        }
    }
}
