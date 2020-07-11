package org.example.jpa.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    private String addrId;

    @Column
    private String addr;

    @Builder
    public Address(String addrId, String addr) {
        this.addrId = addrId;
        this.addr = addr;
    }
}
