package com.lib.bibliotheca.domain.lending;

import com.lib.bibliotheca.domain.book.BookMapper;
import com.lib.bibliotheca.domain.librarian.LibrarianMapper;
import com.lib.bibliotheca.domain.library_user.LibraryUserMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {LibraryUserMapper.class, BookMapper.class, LibrarianMapper.class})
public interface LendingMapper {
    Lending toEntity(LendingDto lendingDto);

    @Mapping(target = "libraryUser.idCode", source = "libraryUserIdCode")
    @Mapping(target = "book.name", source = "bookName")
    @Mapping(target = "librarian.idCode", source = "librarianIdCode")
    Lending toEntity(LendingRequest lendingRequest);

    LendingDto toDto(Lending lending);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Lending partialUpdate(LendingDto lendingDto, @MappingTarget Lending lending);
}