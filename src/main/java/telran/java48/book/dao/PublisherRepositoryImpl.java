package telran.java48.book.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import telran.java48.book.model.Publisher;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<String> findByPublishersByAuthor(String authorName) {
		 String jpqlQuery = "SELECT DISTINCT p.publisherName " +
	                "FROM Publisher p JOIN p.books b JOIN b.authors a " +
	                "WHERE a.name = :authorName";

	        List<String> result = em.createQuery(jpqlQuery, String.class)
	                .setParameter("authorName", authorName)
	                .getResultList();

	        return result;
	}

	@Override
	public Stream<Publisher> findDistinctByBooksAuthorsName(String authorName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Publisher> findById(String publisher) {
		return Optional.ofNullable(em.find(Publisher.class, publisher));
	}

	@Override
	public Publisher save(Publisher publisher) {
		em.persist(publisher);
//		em.merge(publisher);
		return publisher;
	}

}
