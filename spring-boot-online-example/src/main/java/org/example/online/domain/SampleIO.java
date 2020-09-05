package org.example.online.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SampleIO {
    private long id;
    private String name;

    @Builder
    public SampleIO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SampleIO(String name) {
        this.name = name;
    }
}