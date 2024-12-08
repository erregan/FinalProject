package little.free.library.dao;

//This is an interface that extends the Spring JPA interface JPA repository. 

import org.springframework.data.jpa.repository.JpaRepository;

import little.free.library.entity.Visitor;

public interface VisitorDao extends JpaRepository<Visitor, Long> {

}
