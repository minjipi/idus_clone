package com.idus.web.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public void createCategory(CategoryDTO categoryDTO){
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .pidx(categoryDTO.getPidx())
                .build();
        categoryRepository.save(category);
    }

    public CategoryDTO createRootCategory() {
        Map<Integer, List<CategoryDTO>> groupingByParent = categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryDTO(category.getIdx(), category.getName(), category.getPidx()))
                .collect(groupingBy(categoryDTO -> categoryDTO.getPidx()));

        System.out.println("테스트 =========================== ");
        CategoryDTO rootCategoryDto = new CategoryDTO(0, "ROOT");
        addSubCategories(rootCategoryDto, groupingByParent); // 아직 구현되지 않음

        return rootCategoryDto;
    }

    private void addSubCategories(CategoryDTO parent, Map<Integer, List<CategoryDTO>> groupingByParentId) {
        // 1. parent의 키로 subCategories를 찾는다.
        List<CategoryDTO> subCategorylist = groupingByParentId.get(parent.getIdx());

        // 종료 조건
        if (subCategorylist == null)
            return;

        // 2. sub categories 셋팅
        parent.setSubCategorylist(subCategorylist);

        // 3. 재귀적으로 subcategories들에 대해서도 수행
        subCategorylist.stream()
                .forEach(subCategory -> {
                    addSubCategories(subCategory, groupingByParentId);
                });
    }

}
