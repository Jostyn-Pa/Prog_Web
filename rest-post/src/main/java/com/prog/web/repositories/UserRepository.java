package com.prog.web.repositories;

import com.prog.web.db.User;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface UserRepository extends FullEntityRepository<User, Integer> {

}
