package com.lib.bibliotheca.domain.librarian;

import com.lib.bibliotheca.domain.user.UserMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {UserMapper.class})
public interface LibrarianMapper {
    Librarian toEntity(LibrarianDto librarianDto);

    Librarian toEntity(LibrarianRequest librarianRequest);

    LibrarianDto toDto(Librarian librarian);

    List<LibrarianDto> toDto(List<Librarian> librarian);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Librarian partialUpdate(LibrarianDto librarianDto, @MappingTarget Librarian librarian);
}