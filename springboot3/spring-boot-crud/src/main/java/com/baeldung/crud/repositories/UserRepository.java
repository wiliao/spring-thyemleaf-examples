package com.baeldung.crud.repositories;

import com.baeldung.crud.entities.MyUser;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<MyUser, Long> {
    
    List<MyUser> findByName(String name);
    
}
