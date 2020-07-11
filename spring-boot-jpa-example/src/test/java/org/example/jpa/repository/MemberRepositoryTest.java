package org.example.jpa.repository;

import org.example.jpa.domain.Address;
import org.example.jpa.domain.Member;
import org.example.jpa.dto.MemberInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void findByTelNoTest() {
        //given
        String telNo = "010-1234-1234";
        Member member = Member.builder().telNo(telNo)
                .name("simple")
                .age(20)
                .build();

        memberRepository.save(member);

        //when
        List<Member> result = memberRepository.findByTelNo(telNo);

        //then
        assertThat(result.size(), is(1));
    }

    @Test
    public void findMemberInfoTest() {
        String addrId = "11111";
        Member member = Member.builder()
                .telNo("010-1234-5678")
                .addrId(addrId)
                .age(20)
                .name("홍길동")
                .build();
        member = memberRepository.save(member);
        String memberId = member.getId();
        Address address = Address.builder()
                .addr("주소")
                .addrId(addrId).build();

        addressRepository.save(address);

        MemberInfo memberInfo = memberRepository.findMemberInfo(memberId);
        Assert.assertEquals(memberInfo.getMember().getId(), memberId);
        Assert.assertEquals(memberInfo.getMember().getAddrId(), memberInfo.getAddress().getAddrId());
    }
}
