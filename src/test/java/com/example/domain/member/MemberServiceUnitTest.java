package com.example.domain.member;

import com.example.infrastructure.member.MemberRepository;
import com.example.infrastructure.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MemberServiceUnitTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    private Member member;

    @BeforeEach
    public void setUp() {
        member = Member.builder()
                .id("1")
                .name("test")
                .build();
    }

    @Test
    @DisplayName("MEMBER_도메인_등록_단위테스트")
    public void registerMemberUnitTest() throws Exception {
        // given
        // any 를 사용하는 이유는 객체를 생성할 때, 주소 값이 다르기에 any 를 사용했습니다.
        given(memberRepository.save(any())).willReturn(member);

        // when
        MemberCommand.Create create = MemberCommand.Create.builder()
                .id("1")
                .name("test")
                .build();

        String id = memberService.registerMember(create);


        // then
        Assertions.assertEquals("1", id);
    }
}
