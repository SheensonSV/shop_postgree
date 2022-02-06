package main.api.repo;

import main.api.entitys.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepo extends CrudRepository<List, Long> {
    @Query("SELECT u FROM list u WHERE u.name = ?1")
    java.util.List<List> findListByName(String name);
}
