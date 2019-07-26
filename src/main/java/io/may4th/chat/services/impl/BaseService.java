package io.may4th.chat.services.impl;

import io.may4th.chat.services.api.exceptions.ResourceNotFoundException;
import io.may4th.chat.services.impl.mappers.BaseMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

@AllArgsConstructor
abstract class BaseService<E, T, N> {

    private final BaseMapper<E, T, N> mapper;
    private final PagingAndSortingRepository<E, UUID> repository;

    public T findById(UUID id) {
        return mapper.to(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(repository.getClass().getSimpleName(), id.toString())));
    }

    T save(N newTO) {
        return mapper.to(repository.save(mapper.en(newTO)));
    }
}
