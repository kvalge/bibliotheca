package com.lib.bibliotheca.domain.book;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookMapper {
    Book toEntity(BookDto bookDto);

    BookDto toDto(Book book);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Book partialUpdate(BookDto bookDto, @MappingTarget Book book);
}