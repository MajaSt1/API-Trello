package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task,Long> {
    @Override
    List<Task> findAll();

    @Override
    Task save( Task task);

    // @Override
    Optional<Task> findById(Long id);

    @Modifying
    void deleteById(Long id);

    @Override
    long count();
}
