package little.free.library.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// This is the Library entity. 
// Each Library has an Id, Street, Theme, and Intended Age.

@Entity
@Data
public class Library {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long libraryId;
	
	private String libraryStreet;
	private String libraryTheme;
	private String libraryIntendedAge;
	
// Library has a many to many relationship with Material.
// The name of the join table is library_material.
	// The primary key foreign key 1 is library_id
	// The primary key foreign key 2 is material_id
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "library_material",
		joinColumns = @JoinColumn(name = "library_id"),
		inverseJoinColumns = @JoinColumn(name = "material_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Material> materials = new HashSet<>();

// Library has a one to many relationship with Visitor.	
	
	@OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Visitor> visitors = new HashSet<>();
	
}
