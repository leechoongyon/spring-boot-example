package org.example.jpa.repository;

import org.example.jpa.domain.Member;
import org.example.jpa.dto.MemberInfo;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findByTelNo(String telNo);
    MemberInfo findMemberInfo(String id);
}