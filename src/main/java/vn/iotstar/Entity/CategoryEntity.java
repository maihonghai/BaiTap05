package vn.iotstar.Entity;

import java.io.Serializable;
import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Categories")
public class CategoryEntity implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryId")
	private Long categoryId;

	@Column(name = "categoryname", columnDefinition = "NVARCHAR(255)")
	@NotEmpty(message="Không được phép rỗng")
	private String name;

	@Column(name = "images", columnDefinition = "NVARCHAR(255)")
	private String images;

	//@Column(name = "status")
	private String status;
	
	//@OneToMany (mappedBy = "category", cascade = CascadeType.ALL)
	//private Set<ProductEntity> products;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}


