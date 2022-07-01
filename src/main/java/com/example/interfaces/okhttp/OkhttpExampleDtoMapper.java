package com.example.interfaces.okhttp;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",  // spring component bean 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,  // 생성자 주입 방식 사용
        unmappedTargetPolicy = ReportingPolicy.ERROR    // mapping 없으면 에러
)
public interface OkhttpExampleDtoMapper {
    OkhttpExampleDto.PostResponse of (OkhttpExampleDto.PostRequest request);
}
