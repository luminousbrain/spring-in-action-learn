package ru.gontarenko.tacojpa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gontarenko.tacojpa.entity.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}