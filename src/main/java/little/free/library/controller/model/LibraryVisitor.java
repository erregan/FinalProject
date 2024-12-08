package little.free.library.controller.model;

import little.free.library.entity.Visitor;
import lombok.Data;
import lombok.NoArgsConstructor;

//This is a Data Transfer Object used to connect between the user adding input and the application returning results. 

@Data
@NoArgsConstructor
public class LibraryVisitor {

	private Long visitorId;
	private String visitorAgeCategory;
	private String visitorFirstName;

	public LibraryVisitor(Visitor visitor) {
		visitorId = visitor.getVisitorId();
		visitorAgeCategory = visitor.getVisitorAgeCategory();
		visitorFirstName = visitor.getVisitorFirstName();
	}

}
