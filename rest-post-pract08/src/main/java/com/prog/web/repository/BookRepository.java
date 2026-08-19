package com.prog.web.repository;

import com.prog.web.db.Book;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface BookRepository extends FullEntityRepository<Book, Integer> {
}
