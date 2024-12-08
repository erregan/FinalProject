package little.free.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import little.free.library.controller.model.LibraryData;
import little.free.library.entity.Library;


public class LibraryServiceTestSupport {

	private static final String LIBRARY = "library";

	// @formatter:off
	private LibraryData insertLibrary1 = new LibraryData(
		1L,
		"Dale",
		"Fantasy",
		"Teen"
		);

	private LibraryData insertLibrary2 = new LibraryData(
		2L,
		"Como",
		"Soccer",
		"Everyone"
		);	
	
	// @formatter:on

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private LibraryController libraryController;

	protected LibraryData buildInsertLibrary(int which) {
		return which == 1 ? insertLibrary1 : insertLibrary2;
	}
	
	protected LibraryData insertLibrary(LibraryData libraryData) {
		Library library = libraryData.toLibrary();
		LibraryData clone = new LibraryData(library);
		
		clone.setLibraryId(null);
		return libraryController.insertLibrary(clone);
	}

	protected int rowsInLibraryTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, LIBRARY);
	}

}
