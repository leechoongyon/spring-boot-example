package com.example.api;


import com.example.dto.SimpleCommand;
import com.example.dto.SimpleDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.api.SimpleDtoMapper.INSTANCE;

public class SimpleDtoMapperTest {

    @Test
    public void 단건_mapper_test() throws Exception {
        SimpleDto.Request request = SimpleDto.Request.builder()
                .key("keytest")
                .name("keyName")
                .build();

        SimpleCommand.Request result = INSTANCE.of(request);
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

        SimpleCommand.ListRequest result = INSTANCE.of(request);
        Assert.assertEquals("keyTest", result.getKey());
        Assert.assertEquals("item1", result.getItems().get(0).getItemName());
    }

    @Test
    public void 아규먼트_다건_mapper_테스트() {
        // given
        SimpleDto.Request request = SimpleDto.Request.builder()
                .key("keytest")
                .name("keyName")
                .build();

        final int testIntValue = 25;

        SimpleCommand.RequestV2 result = INSTANCE.of(request, testIntValue);
        Assert.assertEquals("keytest", result.getKey());
        Assert.assertEquals(25, result.getIntValue());
    }
}
