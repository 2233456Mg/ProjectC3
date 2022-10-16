package com.ciclo3.projectc3.Service;

import com.ciclo3.projectc3.Entities.Category;
import com.ciclo3.projectc3.Entities.Client;
import com.ciclo3.projectc3.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> getAll(){
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int categoryId){
        return categoryRepository.getCategory(categoryId);
    }

    public Category save(Category category){
        if (category.getId() == null){
            return categoryRepository.save(category);
        } else {
            Optional<Category> category1 = categoryRepository.getCategory(category.getId());
            if(category1.isPresent()){
                return categoryRepository.save(category);
            } else {
                return category;
            }
        }
    }

    public Category update(Category category){
        if(category.getId()!=null){
            Optional<Category> category1 = categoryRepository.getCategory(category.getId());
            if(!category1.isPresent()) {
                if (category.getDescription() != null) {
                    category1.get().setDescription(category.getDescription());
                }
                if (category.getName() != null) {
                    category1.get().setName(category.getName());
                }
                return categoryRepository.save(category1.get());
            }
        } return category;
    }
    public boolean deleteCategory(int id){
        Boolean resultado = getCategory(id).map(category -> {
            categoryRepository.delete(category);
            return true;
        }).orElse(false);
        return resultado;
    }
}
