package org.example.jpa.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.jpa.domain.Member;
import org.example.jpa.dto.MemberInfo;

import java.util.List;

import static org.example.jpa.domain.QMember.member;
import static org.example.jpa.domain.QAddress.address;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> findByTelNo(String telNo) {
        return queryFactory.selectFrom(member)
                .where(member.telNo.eq(telNo))
                .fetch();
    }

    @Override
    public MemberInfo findMemberInfo(String id) {
        return queryFactory.select(Projections.bean(MemberInfo.class
                , member.as("member"), address.as("address")))
                .from(member)
                .join(address).on(member.addrId.eq(address.addrId))
                .where(member.id.eq(id))
                .fetchOne();
    }
}
