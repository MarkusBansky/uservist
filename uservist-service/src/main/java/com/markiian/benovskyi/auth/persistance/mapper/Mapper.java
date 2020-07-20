package com.markiian.benovskyi.auth.persistance.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<TBase, TDto> {

    TDto toDto(TBase base);

    TBase toBase(TDto dto);

    default List<TDto> toDto(Collection<TBase> baseCollection) {
        return baseCollection.stream().map(this::toDto).collect(Collectors.toList());
    }

    default List<TBase> toBase(Collection<TDto> dtoCollection) {
        return dtoCollection.stream().map(this::toBase).collect(Collectors.toList());
    }

}
