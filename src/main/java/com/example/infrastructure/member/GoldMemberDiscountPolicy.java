package com.example.infrastructure.member;

import com.example.domain.member.MemberDiscountPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoldMemberDiscountPolicy implements MemberDiscountPolicy {
    @Override
    public String getDiscountRate() {
        return "30%";
    }
}
