package io.may4th.chat.services.impl.mappers;

import java.util.List;

interface BaseMapper<E, T, N> {

    T to(E entity);

    E en(N to);

    List<T> to(List<E> entity);
}
