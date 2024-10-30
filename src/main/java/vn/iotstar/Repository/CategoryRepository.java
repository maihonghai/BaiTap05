package vn.iotstar.Repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.Entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long>  {
	List<CategoryEntity>findByName(String name);
	Page<CategoryEntity>findByNameContaining(String name,Pageable pageable);

}







