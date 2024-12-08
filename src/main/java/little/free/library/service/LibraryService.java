package little.free.library.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import little.free.library.controller.model.LibraryData;
import little.free.library.controller.model.LibraryMaterial;
import little.free.library.controller.model.LibraryVisitor;
import little.free.library.dao.LibraryDao;
import little.free.library.dao.MaterialDao;
import little.free.library.dao.VisitorDao;
import little.free.library.entity.Library;
import little.free.library.entity.Material;
import little.free.library.entity.Visitor;

//Library section starts here!

@Service
public class LibraryService {
	@Autowired
	private LibraryDao libraryDao;

	
	//Create or post Library
	@Transactional
	public LibraryData saveLibrary(LibraryData libraryData) {
		Long libraryId = libraryData.getLibraryId();
		Library library = findOrCreateLibrary(libraryId);
		
		copyLibraryFields(library, libraryData);
		return new LibraryData(libraryDao.save(library));
	}
	
	private void copyLibraryFields(Library library, LibraryData libraryData) {
		library.setLibraryId(libraryData.getLibraryId());
		library.setLibraryStreet(libraryData.getLibraryStreet());
		library.setLibraryTheme(libraryData.getLibraryTheme());
		library.setLibraryIntendedAge(libraryData.getLibraryIntendedAge());
		
	}

	private Library findOrCreateLibrary(Long libraryId) {
		if (Objects.isNull(libraryId)) {
		return new Library();
		} else { 
			return findLibraryById(libraryId);
		}
	}

	private Library findLibraryById(Long libraryId) {
		return libraryDao.findById(libraryId)
				.orElseThrow(() -> new NoSuchElementException("Library with ID=" + libraryId + " was not found."));
	}


	//Read or get Library
	@Transactional (readOnly = true)
	public List<LibraryData> retrieveAllLibraries() {
	List<Library> libraries = libraryDao.findAll();
	
	List<LibraryData> result = new LinkedList<>();
	
	{
		
		for(Library library : libraries) {
			LibraryData ld = new LibraryData(library);
			
			ld.getMaterials().clear();
			ld.getVisitors().clear();
			
			
			result.add(ld);
		}
		
		return result;
	}	
	}
	
	@Transactional(readOnly = true)
	public LibraryData retrieveLibraryById(Long libraryId) {
		return new LibraryData(findLibraryById(libraryId));
	}
	

	//Delete Library by Id
	@Transactional (readOnly = false)
	public void deleteLibraryById(Long libraryId) {
		Library library = findLibraryById(libraryId);
		libraryDao.delete(library);
		
	}

													//Library section ends here
	
///////////////////////////////////////////////////////////////////////////////////	
//Visitor section starts here!

	@Autowired
	private VisitorDao visitorDao;
	
	//Create or post Visitor
	private void copyVisitorFields(Visitor visitor, LibraryVisitor libraryVisitor) {
		visitor.setVisitorFirstName(libraryVisitor.getVisitorFirstName());
		visitor.setVisitorId(libraryVisitor.getVisitorId());
		visitor.setVisitorAgeCategory(libraryVisitor.getVisitorAgeCategory());
	}

	private Visitor findOrCreateVisitor(Long libraryId, Long visitorId) {
		if (Objects.isNull(visitorId)) {
			return new Visitor();
		}
		return findVisitorById(libraryId, visitorId);
	}

	//Find Visitor by Id
	private Visitor findVisitorById(Long libraryId, Long visitorId) {
		Visitor visitor = visitorDao.findById(visitorId)
				.orElseThrow(() -> new NoSuchElementException("Visitor with ID=" + visitorId + " was not found."));

		if (visitor.getLibrary().getLibraryId() != libraryId) {
			throw new IllegalArgumentException("The visitor with ID=" + visitorId
					+ " does not consider the library with ID=" + libraryId + " their home library.");
		}
		return visitor;
	}
	

	//Save Visitor
	@Transactional(readOnly = false)
	public LibraryVisitor saveVisitor(Long libraryId, LibraryVisitor libraryVisitor) {
		Library library = findLibraryById(libraryId);
		Long visitorId = libraryVisitor.getVisitorId();
		Visitor visitor = findOrCreateVisitor(libraryId, visitorId);

	copyVisitorFields(visitor, libraryVisitor);
		
		visitor.setLibrary(library);
		library.getVisitors().add(visitor);
		
		Visitor dbVisitor = visitorDao.save(visitor);
		
		return new LibraryVisitor(dbVisitor);
	
	}
		
												//Visitor Section Ends Here
		
/////////////////////////////////////////////////////////////////////////////////////////////////
//Material section starts here!

	@Autowired
	private MaterialDao materialDao;
	
	private void copyMaterialFields(Material material, LibraryMaterial libraryMaterial) {
		material.setMaterialTitle(libraryMaterial.getMaterialTitle());
		material.setMaterialId(libraryMaterial.getMaterialId());
		material.setMaterialGenre(libraryMaterial.getMaterialGenre());
		material.setMaterialCondition(libraryMaterial.getMaterialCondition());
	}
	
	//Find or create Material
	private Material findOrCreateMaterial(Long libraryId, Long materialId) {
		if (Objects.isNull(materialId)) {
			return new Material();
		}
		return findMaterialById(libraryId, materialId);
	}

	//Find Material by ID
	@Transactional(readOnly = false)
	private Material findMaterialById(Long libraryId, Long materialId) {
		Material material = materialDao.findById(materialId)
				.orElseThrow(() -> new NoSuchElementException("Material with ID=" + materialId + " was not found."));
		boolean found = false;

		for (Library library : material.getLibraries()) {
			if (library.getLibraryId() == libraryId) {
				found = true;
				break;

			}
		}
		if (!found) {
			throw new IllegalArgumentException("The material with ID=" + materialId
					+ " is not a member of the library with ID=" + libraryId + ".");
		}
		return material;
	}
	
	//Save Material 
	@Transactional (readOnly = false)
	public LibraryMaterial saveMaterial(Long libraryId, LibraryMaterial libraryMaterial) {
		Library library = findLibraryById(libraryId);
		Long materialId = libraryMaterial.getMaterialId();
		Material material = findOrCreateMaterial(libraryId, materialId);

	copyMaterialFields(material, libraryMaterial);
		
		material.getLibraries().add(library);
		library.getMaterials().add(material);
		
		Material dbMaterial = materialDao.save(material);
		
		return new LibraryMaterial(dbMaterial);

}
												//Material section ends here
}
