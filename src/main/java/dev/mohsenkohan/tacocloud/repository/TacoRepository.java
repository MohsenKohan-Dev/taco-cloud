package dev.mohsenkohan.tacocloud.repository;

import dev.mohsenkohan.tacocloud.domain.Taco;

public interface TacoRepository {

    Taco save(Taco taco);
}
