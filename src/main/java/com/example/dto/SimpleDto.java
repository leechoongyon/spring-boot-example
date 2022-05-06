package com.example.dto;

import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@ToString
public class SimpleDto {
    private String key;
    private String name;

    @Builder
    public SimpleDto(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public static SimpleDto create(SimpleDto simpleDto) {
        return SimpleDto.builder()
                .key(simpleDto.getKey())
                .name(simpleDto.getName())
                .build();
    }

    @Getter
    public static class Request {
        private final String key;
        private final String name;

        @Builder
        public Request(String key, String name) {
            this.key = key;
            this.name = name;
        }
    }

    @Setter
    @Getter
    @ToString
    public static class ListRequest {
        private String key;
        private List<Item> itemList;

        @Builder
        public ListRequest(String key, List<Item> itemList) {
            this.key = key;
            this.itemList = itemList;
        }
    }

    @Setter
    @Getter
    @Builder
    @ToString
    public static class Item {
        private String itemName;
    }
}
