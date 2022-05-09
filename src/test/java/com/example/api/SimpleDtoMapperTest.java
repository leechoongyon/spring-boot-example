package com.example.api;


import com.example.dto.SimpleCommand;
import com.example.dto.SimpleDto;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

public class SimpleDtoMapperTest {

    private SimpleDtoMapper simpleDtoMapper = Mappers.getMapper(SimpleDtoMapper.class);

    @Test
    public void 단건_mapper_test() throws Exception {
        SimpleDto.Request request = SimpleDto.Request.builder()
                .key("keytest")
                .name("keyName")
                .build();

        SimpleCommand.Request result = simpleDtoMapper.of(request);
        Assert.assertEquals("keytest", result.getKey());
    }

    @Test
    public void List_mapper_test() throws Exception {
        SimpleDto.Item item = SimpleDto.Item.builder()
                .itemName("item1")
                .build();
        List<SimpleDto.Item> itemsList = new ArrayList<>();
        itemsList.add(item);
        SimpleDto.ListRequest request = SimpleDto.ListRequest.builder()
                .key("keyTest")
                .itemList(itemsList)
                .build();

        SimpleCommand.ListRequest result = simpleDtoMapper.of(request);
        Assert.assertEquals("keyTest", result.getKey());
        Assert.assertEquals("item1", result.getItems().get(0).getItemName());
    }

}
