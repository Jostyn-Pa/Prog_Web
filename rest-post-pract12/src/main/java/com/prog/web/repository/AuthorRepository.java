package com.prog.web.repository;

import com.prog.web.db.Author;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface AuthorRepository extends FullEntityRepository<Author, Integer> {

}
