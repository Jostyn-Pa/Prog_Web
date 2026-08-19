package com.prog.web.repository;

import com.prog.web.db.Producto;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface ProductoRepository extends FullEntityRepository<Producto, Integer> {
}
