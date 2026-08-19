package com.prog.web.repository;

import com.prog.web.db.Book;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface BookRepository extends FullEntityRepository<Book, Integer> {
    List<Book> findByTitleLike(String title);
    List<Book> findByPublishedYearGreaterThan(Integer year);
    List<Book> findByIsAvailable(Boolean status);
}
