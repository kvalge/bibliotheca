package com.lib.bibliotheca.domain.user;

import com.lib.bibliotheca.domain.role.RoleMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {
    User toEntity(UserDto userDto);

    @Mapping(target = "role.name", source = "roleName")
    @Mapping(target = "role.id", source = "roleId")
    User toEntity(UserRequest userRequest);

    UserDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDto userDto, @MappingTarget User user);
}