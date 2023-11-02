package telran.java48.book.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import telran.java48.book.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public Stream<Book> findByAuthorsName(String name) {
		 List<Book> books = em.createQuery(
	                "SELECT b FROM Book b JOIN b.authors a WHERE a.name = :name", Book.class)
	                .setParameter("name", name)
	                .getResultList();
	        return books.stream();
	}

	@Override
	public Stream<Book> findByPublisherPublisherName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(String isbn) {
		return em.find(Book.class, isbn) != null;
	}

	@Override
	public Book save(Book book) {
		em.persist(book);
		return book;
	}

	@Override
	public Optional<Book> findById(String isbn) {
		return Optional.ofNullable(em.find(Book.class, isbn));
	}

	@Override
	public void deleteById(String isbn) {
		em.remove(em.find(Book.class, isbn));
	}

}
