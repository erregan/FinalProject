package little.free.library.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import little.free.library.controller.model.LibraryData;
import little.free.library.controller.model.LibraryMaterial;
import little.free.library.controller.model.LibraryVisitor;
import little.free.library.service.LibraryService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/little_free_library")
@Slf4j

public class LibraryController {
	@Autowired
	private LibraryService libraryService;
	
//Library section
	//Create or post Library starts here!	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public LibraryData insertLibrary(@RequestBody LibraryData libraryData) {
		log.info("Creating little free library {}", libraryData);
		return libraryService.saveLibrary(libraryData);
	}
	
	//Read or get Library starts here!
	@GetMapping
	public List<LibraryData> retrieveAllLibraries() {
		log.info("Retrieving all libraries");
		return libraryService.retrieveAllLibraries();
	}
	
	@GetMapping("/{petStoreId}")
	public LibraryData retrieveLibraryById(@PathVariable Long libraryId) {
		log.info("Retrieving library with ID={}", libraryId);
		return libraryService.retrieveLibraryById(libraryId);
	}
	
	
//Update Library starts here!
	@PutMapping("/{libraryId}")
	public LibraryData updateLibrary(@PathVariable Long libraryId, @RequestBody LibraryData libraryData) {
		libraryData.setLibraryId(libraryId);
		log.info("Updating library {}", libraryData);
		return libraryService.saveLibrary(libraryData);	
	}
	
// Delete Library starts here!
	@DeleteMapping("/{libraryId}")
	public Map<String, String> deleteLibraryById(@PathVariable Long libraryId){
		log.info("Deleting library with ID={}", libraryId);
		
		libraryService.deleteLibraryById(libraryId);
		
		return Map.of("Message", "Library with ID=" + libraryId + " deleted.");
			
	}
	
											// Library section ends here
	
//Visitor section
	
	//Create or post Visitor starts here!
	@PostMapping("/{libraryId}/visitor")
	@ResponseStatus(code = HttpStatus.CREATED)
	public LibraryVisitor addVisitorToLibrary(@PathVariable Long libraryId,
			@RequestBody LibraryVisitor libraryVisitor) {
		log.info("Adding visitor {} to library with ID={}", libraryVisitor, libraryId);

		return libraryService.saveVisitor(libraryId, libraryVisitor);
	
}
											//Visitor section ends here
	
//Material section
	
//Create or Post Material starts here!
	@PostMapping("/{libraryId}/material")
	@ResponseStatus(code = HttpStatus.CREATED)
	public LibraryMaterial addMaterialToLibrary(@PathVariable Long libraryId,
			@RequestBody LibraryMaterial libraryMaterial) {
		log.info("Adding material {} to library with ID={}", libraryMaterial, libraryId);

		return libraryService.saveMaterial(libraryId, libraryMaterial);
		
}
											//Material section ends here

}
