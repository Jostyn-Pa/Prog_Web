package com.prog.web.repository;

import com.prog.web.db.Autor;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface AutorRepository extends FullEntityRepository<Autor, Integer> {

}
