package merchant_backend.controller;

import lombok.RequiredArgsConstructor;
import merchant_backend.dto.category.AllCategoriesResponse;
import merchant_backend.dto.category.RegisterCategory;
import merchant_backend.service.category.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCategory(@RequestBody List<RegisterCategory> requests){
        String response= categoryService.registerNewCategory(requests);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getallcategories")
    public ResponseEntity<List<AllCategoriesResponse>> getAllCategories(){
    List<AllCategoriesResponse> response= categoryService.getAllCategories();
    return ResponseEntity.ok(response);
    }
}
