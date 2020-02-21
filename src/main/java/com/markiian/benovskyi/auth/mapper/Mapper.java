package com.markiian.benovskyi.auth.mapper;

public interface Mapper<TBase, TDto> {

    default TDto toDto(TBase base) {
        return null;
    }

    default TBase toBase(TDto dto) {
        return null;
    }

    TDto toDto(TDto dto, TBase base);

    TBase toBase(TBase base, TDto dto);

}
