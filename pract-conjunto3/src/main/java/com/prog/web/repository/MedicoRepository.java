package com.prog.web.repository;

import com.prog.web.db.Medico;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface MedicoRepository extends FullEntityRepository<Medico, Integer> {
}
