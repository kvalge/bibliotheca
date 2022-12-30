package com.lib.bibliotheca.domain.library_user;

import com.lib.bibliotheca.domain.user.UserMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {UserMapper.class})
public interface LibraryUserMapper {
    LibraryUser toEntity(LibraryUserDto libraryUserDto);

    LibraryUser toEntity(LibraryUserRequest libraryUserRequest);

    LibraryUserDto toDto(LibraryUser libraryUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LibraryUser partialUpdate(LibraryUserDto libraryUserDto, @MappingTarget LibraryUser libraryUser);
}