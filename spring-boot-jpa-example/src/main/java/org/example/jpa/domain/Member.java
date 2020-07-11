package org.example.jpa.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String name;

    @Column
    private String telNo;

    @Column
    private int age;

    String addrId;

    @Builder
    public Member(String name, String telNo, int age, String addrId) {
        this.name = name;
        this.telNo = telNo;
        this.age = age;
        this.addrId = addrId;
    }
}
