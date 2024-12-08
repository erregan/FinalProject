package little.free.library.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import little.free.library.LittleFreeLibraryApplication;
import little.free.library.controller.model.LibraryData;

@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = LittleFreeLibraryApplication.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
@SqlConfig(encoding = "utf-8")
class LibraryControllerTest extends LibraryServiceTestSupport {

	// This test was created with Spring Boot Testing, so it's possible to test the whole application.
	// This type of testing uses the H2 database.
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testInsertLibrary() {
		// Given: a library request 
		LibraryData request = buildInsertLibrary(1);
		LibraryData expected = buildInsertLibrary(1);
		
		// When: the library is added to the library table
		LibraryData actual = insertLibrary(request);
		
		// Then: the library returned is what is expected 
		assertThat(actual).isEqualTo(expected);
		
		// And: there is one row in the library table
		assertThat(rowsInLibraryTable()).isOne();
	}



}
