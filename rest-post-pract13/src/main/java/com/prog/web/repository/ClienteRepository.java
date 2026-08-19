package com.prog.web.repository;

import com.prog.web.db.Cliente;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface ClienteRepository extends FullEntityRepository<Cliente, Integer> {
}
