package org.example.jpa.service;

import com.sun.tools.javac.jvm.Items;
import org.example.jpa.domain.Category;
import org.example.jpa.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public List<String> findAllItemNames(){
        return extractItemNames(categoryRepository.findAll());
    }

    @Transactional
    public List<String> findAllItemNamesJoinFetch(){
        return extractItemNames(categoryRepository.findAllJoinFetch());
    }

    @Transactional
    public List<String> findAllItemNamesEntityGraph(){
        return extractItemNames(categoryRepository.findAllEntityGraph());
    }

    private List<String> extractItemNames(List<Category> categories) {
        return categories.stream().map(tmp -> tmp.getItems().get(0).getName())
                .collect(Collectors.toList());
    }
}
