package little.free.library.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// This is the Material entity.
// Each Material has an Id, Title, Genre, and Condition.

@Entity
@Data
public class Material {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long materialId;
	
	private String materialTitle;
	private String materialGenre;
	private String materialCondition;
	
// Material has a many to many relationship with Library.
// The name of the join table is library_material.
		// The primary key foreign key 1 is library_id
		// The primary key foreign key 2 is material_id
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "materials", cascade = CascadeType.PERSIST)
	private Set<Library> libraries = new HashSet<> ();
}
