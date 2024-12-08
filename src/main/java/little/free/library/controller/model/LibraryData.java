package little.free.library.controller.model;

import java.util.HashSet;
import java.util.Set;

import little.free.library.entity.Library;
import little.free.library.entity.Material;
import little.free.library.entity.Visitor;
import lombok.Data;
import lombok.NoArgsConstructor;

// This is a Data Transfer Object used to connect between the user adding input and the application returning results. 

@Data
@NoArgsConstructor
public class LibraryData {
	private Long libraryId;
	private String libraryStreet;
	private String libraryTheme;
	private String libraryIntendedAge;

	private Set<LibraryMaterial> materials = new HashSet<>();
	private Set<LibraryVisitor> visitors = new HashSet<>();

	public LibraryData(Library library) {
		libraryId = library.getLibraryId();
		libraryStreet = library.getLibraryStreet();
		libraryTheme = library.getLibraryTheme();
		libraryIntendedAge = library.getLibraryIntendedAge();

		for (Material material : library.getMaterials()) {
			materials.add(new LibraryMaterial(material));
		}

		for (Visitor visitor : library.getVisitors()) {
			visitors.add(new LibraryVisitor(visitor));
		}
	}

	public LibraryData(Long libraryId, String libraryStreet, String libraryTheme, String libraryIntendedAge) {
		this.libraryId = libraryId;
		this.libraryStreet = libraryStreet;
		this.libraryTheme = libraryTheme;
		this.libraryIntendedAge = libraryIntendedAge;
	}

	public Library toLibrary() {
		Library library = new Library();
		library.setLibraryId(libraryId);
		library.setLibraryStreet(libraryStreet);
		library.setLibraryTheme(libraryTheme);
		library.setLibraryIntendedAge(libraryIntendedAge);
		
		return library;
	}
	
	
}
