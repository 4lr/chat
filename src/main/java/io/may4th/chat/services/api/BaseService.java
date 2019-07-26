package io.may4th.chat.services.api;

import java.util.UUID;

interface BaseService<T, N> {

    T findById(UUID id);

    T save(N newTO);
}
