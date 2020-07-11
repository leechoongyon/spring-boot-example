package org.example.batch.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class RepeatStepExampleDomain {
    @Id
    private String id;

    @Column
    private String data;

    @Builder
    public RepeatStepExampleDomain(String id, String data) {
        this.id = id;
        this.data = data;
    }
}
