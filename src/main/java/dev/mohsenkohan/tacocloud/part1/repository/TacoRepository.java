package dev.mohsenkohan.tacocloud.part1.repository;

import dev.mohsenkohan.tacocloud.part1.domain.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
