package com.hodbenor.project.robust.access.limit.data;

import java.util.List;
import java.util.Optional;

public interface DataResourceService<T> {
    DataResourceType getDataResourceType();

    T save(T t);

    default Optional<T> get(long id) {
        return Optional.empty();
    }

    default Optional<T> get(long firstId, long secondId) {
        return Optional.empty();
    }

    List<T> getAll();

    default void increment(long id, int incrementBy) {
    }

    void deleteById(long id);

    default boolean update(T t) {
        return false;
    }
}