package org.example.jpa.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class EmbeddedDomain implements Serializable {
    private String pk1;
    private String pk2;

    @Builder
    public EmbeddedDomain(String pk1, String pk2) {
        this.pk1 = pk1;
        this.pk2 = pk2;
    }
}
