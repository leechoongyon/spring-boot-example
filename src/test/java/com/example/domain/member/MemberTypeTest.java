package com.example.domain.member;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberTypeTest {

    @Test
    @DisplayName("Enum Bean 주입 테스트")
    public void injectBeanIntoEnumTest() throws Exception {
        MemberType memberType = MemberType.GOLD;
        String discountRate = memberType.getMemberDiscountPolicy().getDiscountRate();
        Assertions.assertEquals("30%", discountRate);

        memberType = MemberType.SILVER;
        discountRate = memberType.getMemberDiscountPolicy().getDiscountRate();
        Assertions.assertEquals("10%", discountRate);
    }
}
