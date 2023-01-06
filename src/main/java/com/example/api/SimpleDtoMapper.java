package com.example.api;

import com.example.dto.SimpleCommand;
import com.example.dto.SimpleDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",  // spring component bean 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,  // 생성자 주입 방식 사용
        unmappedTargetPolicy = ReportingPolicy.ERROR    // mapping 없으면 에러
)
public interface SimpleDtoMapper {

    SimpleDtoMapper INSTANCE = Mappers.getMapper(SimpleDtoMapper.class);

    // list 없음
    SimpleCommand.Request of (SimpleDto.Request request);

    @Mapping(source = "request.key", target = "key")
    @Mapping(target = "name", ignore = true)
    @Mapping(source = "testIntValue", target = "intValue")
    SimpleCommand.RequestV2 of(SimpleDto.Request request, int testIntValue);

    // request 에 list 존재
    @Mappings({@Mapping(source = "itemList", target = "items")})
    SimpleCommand.ListRequest of (SimpleDto.ListRequest request);

}
