package com.example.domain.member;

import com.example.infrastructure.member.GoldMemberDiscountPolicy;
import com.example.infrastructure.member.SilverMemberDiscountPolicy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Getter
public enum MemberType {
    GOLD("골드"),
    SILVER("실버")
    ;

    private String desc;

    private MemberDiscountPolicy memberDiscountPolicy;

    MemberType(String desc) {
        this.desc = desc;
    }
    
    @Component
    @RequiredArgsConstructor
    private static class MemberTypeInjector {
        private final GoldMemberDiscountPolicy goldMemberDiscountPolicy;
        private final SilverMemberDiscountPolicy silverMemberDiscountPolicy;
        @PostConstruct
        public void postConstruct() {
            MemberType.GOLD.injectMemberDiscountPolicy(goldMemberDiscountPolicy);
            MemberType.SILVER.injectMemberDiscountPolicy(silverMemberDiscountPolicy);
        }
    }

    private void injectMemberDiscountPolicy(MemberDiscountPolicy memberDiscountPolicy) {
        this.memberDiscountPolicy = memberDiscountPolicy;
    }
}
