package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.thtf.deconfliction.domain.UserSalaryInfo;
import com.thtf.deconfliction.service.dto.UserSalaryInfoDTO;

/**
 * Mapper for the entity UserSalaryInfo and its DTO UserSalaryInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {UserExtendSalaryMapper.class})
public interface UserSalaryInfoMapper extends EntityMapper<UserSalaryInfoDTO, UserSalaryInfo> {

    @Mapping(source = "userExtendSalary.id", target = "userExtendSalaryId")
    UserSalaryInfoDTO toDto(UserSalaryInfo userSalaryInfo);

    @Mapping(source = "userExtendSalaryId", target = "userExtendSalary")
    UserSalaryInfo toEntity(UserSalaryInfoDTO userSalaryInfoDTO);

    default UserSalaryInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserSalaryInfo userSalaryInfo = new UserSalaryInfo();
        userSalaryInfo.setId(id);
        return userSalaryInfo;
    }
}
