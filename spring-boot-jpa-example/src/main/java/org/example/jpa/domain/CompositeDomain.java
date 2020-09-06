package org.example.jpa.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CompositeDomain {
    @EmbeddedId
    private EmbeddedDomain embeddedDomain;

    private String content;
}
