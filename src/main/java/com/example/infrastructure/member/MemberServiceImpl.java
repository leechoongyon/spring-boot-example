package com.example.infrastructure.member;


import com.example.domain.member.Member;
import com.example.domain.member.MemberCommand;
import com.example.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public String registerMember(MemberCommand.Create create) {
        Member member = memberRepository.save(create.toEntity());
        return member.getId();
    }
}
