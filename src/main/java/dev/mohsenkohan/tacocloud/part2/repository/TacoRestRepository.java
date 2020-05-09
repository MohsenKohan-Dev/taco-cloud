package dev.mohsenkohan.tacocloud.part2.repository;

import dev.mohsenkohan.tacocloud.part1.domain.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRestRepository extends PagingAndSortingRepository<Taco, Long> {
}
