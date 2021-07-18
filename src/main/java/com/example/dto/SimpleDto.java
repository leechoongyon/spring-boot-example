package com.example.dto;

import lombok.*;

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
}
