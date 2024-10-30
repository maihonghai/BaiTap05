package vn.iotstar.Controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.iotstar.Entity.CategoryEntity;
import vn.iotstar.Service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	public String listCategories(Model model, 
	                             @RequestParam(defaultValue = "") String keyword, 
	                             Pageable pageable) {
	    Page<CategoryEntity> categoryPage = categoryService.findByNameContaining(keyword, pageable);
	    model.addAttribute("categories", categoryPage.getContent());
	    model.addAttribute("currentPage", pageable.getPageNumber());
	    model.addAttribute("totalPages", categoryPage.getTotalPages());
	    model.addAttribute("keyword", keyword);
	    return "category/list";
	}
	
	 

	@GetMapping("/add")
	public String showCreateForm(Model model) {
	    model.addAttribute("category", new CategoryEntity()); // Đổi tên thành "category"
	    return "category/add"; // Đường dẫn tới add.html
	}

	@PostMapping
	public String saveCategory(@ModelAttribute("category") CategoryEntity category) {
	    categoryService.save(category);
	    return "redirect:/categories";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
	    CategoryEntity category = categoryService.findById(id)
	                                       .orElseThrow(() -> new IllegalArgumentException("Invalid Category ID:" + id));
	    model.addAttribute("category", category); // Đổi tên thành "category"
	    return "category/edit"; // Đường dẫn tới edit.html
	}

	    @GetMapping("/delete/{id}")
	    public String deleteCategory(@PathVariable Long id) {
	        categoryService.deleteById(id);
	        return "redirect:/categories";
	    }

}
