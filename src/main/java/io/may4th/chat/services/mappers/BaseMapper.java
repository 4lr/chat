package io.may4th.chat.services.mappers;

import java.util.List;

interface BaseMapper<E, T, N> {

    T to(E entity);

    E en(N to);

    List<T> to(Iterable<E> entity);

    List<E> en(Iterable<T> to);
}
