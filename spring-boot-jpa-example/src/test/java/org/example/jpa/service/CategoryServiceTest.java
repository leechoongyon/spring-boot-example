package org.example.jpa.service;


import org.example.jpa.domain.Category;
import org.example.jpa.domain.Item;
import org.example.jpa.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        Item item = Item.builder()
                .name("컴퓨터")
                .build();
        List<Item> items = new ArrayList<>(Arrays.asList(item));
        Category category = Category.builder()
                .name("IT")
                .items(items)
                .build();

        categoryRepository.save(category);

        item = Item.builder()
                .name("사과")
                .build();
        items = new ArrayList<>(Arrays.asList(item));
        category = Category.builder()
                .name("식품")
                .items(items)
                .build();

        categoryRepository.save(category);

    }

    /**
     * 실행하면 Category select 하는 쿼리 1개
     * 2개의 category 각각에 대한 items 을 조회하는 쿼리 2개
     * 총 3개 쿼리가 실행 됨.
     * 이것이 n+1
     */
    @Test
    public void categoryRepositoryLazyLodingTest() {
        List<String> itemNames = categoryService.findAllItemNames();
        Assert.assertThat(itemNames.size(), is(2));
    }

    /**
     * 조회 시 한 번에 읽어들인다.
     * 1개의 select 쿼리 발생.
     */
    @Test
    public void categoryRepositoryJoinFetchTest() {
        List<String> itemNames = categoryService.findAllItemNamesJoinFetch();
        Assert.assertThat(itemNames.size(), is(2));
    }

    /**
     * Entity Graph 를 이용해 n+1 문제 해결
     */
    @Test
    public void categoryRepositoryEntityGraphTest() {
        List<String> itemNames = categoryService.findAllItemNamesEntityGraph();
        Assert.assertThat(itemNames.size(), is(2));
    }
}
