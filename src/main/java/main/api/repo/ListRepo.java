package main.api.repo;

import main.api.entitys.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepo extends CrudRepository<List, Long> {
}
