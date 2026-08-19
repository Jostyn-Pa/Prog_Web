package com.prog.web.repository;

import com.prog.web.db.Director;
import com.prog.web.db.Pelicula;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends FullEntityRepository<Director, Integer> {
}
