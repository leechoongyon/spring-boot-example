package com.example.domain.member;

import lombok.Builder;
import lombok.Getter;

public class MemberCommand {

    @Getter
    public static class Create {
        private String id;
        private String name;

        @Builder
        public Create(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public Member toEntity() {
            return Member.builder()
                    .id(id)
                    .name(name)
                    .build();
        }
    }
}
