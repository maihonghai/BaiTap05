package vn.iotstar.Service;

import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.iotstar.Entity.CategoryEntity;
import vn.iotstar.Repository.CategoryRepository;

//khai báo service
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@Override
	public <S extends CategoryEntity> S save(S entity) {
		if (entity.getCategoryId() == null) {
			return categoryRepository.save(entity);
		} else {
			Optional<CategoryEntity> opt = findById(entity.getCategoryId());
			if (opt.isPresent()) {
				if (StringUtils.isEmpty(entity.getImages())) {
					entity.setImages(opt.get().getImages());
				} else {
					// lay lai images cũ
					entity.setImages(entity.getImages());
				}
			}
		

		return categoryRepository.save(entity);
		}
	}

	@Override
	public List<CategoryEntity> findByName(String name) {

		return categoryRepository.findByName(name);
	}
	
	
	@Override
	public Page<CategoryEntity> findByNameContaining(String name, Pageable pageable) {
		Pageable defaultPageable = PageRequest.of(pageable.getPageNumber(), 2); // Kích thước trang mặc định
		return categoryRepository.findByNameContaining(name, defaultPageable);
	}

	@Override
	public List<CategoryEntity> findAll() {
		return categoryRepository.findAll();
	}
	
	@Override
	public Page<CategoryEntity> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}
	
	@Override
	public List<CategoryEntity> findAll(Sort sort) {
		return categoryRepository.findAll(sort);
	}
	
	@Override
	public List<CategoryEntity> findAllById(Iterable<Long> ids) {
		return categoryRepository.findAllById(ids);
	}
	
	@Override
	public Optional<CategoryEntity> findById(Long id) {
		return categoryRepository.findById(id);
	}



	@Override
	public <S extends CategoryEntity> Optional<S> findOne(Example<S> example) {
		return categoryRepository.findOne(example);
	}

	@Override
	public long count() {
		return categoryRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public void delete(CategoryEntity entity) {
		categoryRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		categoryRepository.deleteAll();
	}

}