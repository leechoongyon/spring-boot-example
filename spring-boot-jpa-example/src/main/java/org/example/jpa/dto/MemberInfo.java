package org.example.jpa.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.domain.Address;
import org.example.jpa.domain.Member;

@Setter
@Getter
public class MemberInfo {
    private Member member;
    private Address address;
}
