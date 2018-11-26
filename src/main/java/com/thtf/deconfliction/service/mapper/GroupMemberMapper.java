package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.thtf.deconfliction.domain.GroupMember;
import com.thtf.deconfliction.service.dto.GroupMemberDTO;

/**
 * Mapper for the entity GroupMember and its DTO GroupMemberDTO.
 */
@Mapper(componentModel = "spring", uses = {UserExtendMapper.class, OfficeGroupMapper.class})
public interface GroupMemberMapper extends EntityMapper<GroupMemberDTO, GroupMember> {

    @Mapping(source = "userExtend.id", target = "userExtendId")
    @Mapping(source = "group.id", target = "groupId")
    GroupMemberDTO toDto(GroupMember groupMember);

    @Mapping(source = "userExtendId", target = "userExtend")
    @Mapping(source = "groupId", target = "group")
    GroupMember toEntity(GroupMemberDTO groupMemberDTO);

    default GroupMember fromId(Long id) {
        if (id == null) {
            return null;
        }
        GroupMember groupMember = new GroupMember();
        groupMember.setId(id);
        return groupMember;
    }
}
