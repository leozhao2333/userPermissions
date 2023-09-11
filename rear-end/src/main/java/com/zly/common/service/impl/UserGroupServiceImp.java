package com.zly.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zly.common.dto.TokenPayload;
import com.zly.common.dto.UserGroupDto;
import com.zly.common.entity.UserGroup;
import com.zly.common.enums.ErrorCode;
import com.zly.common.exception.BizException;
import com.zly.common.mapper.UserGroupMapper;
import com.zly.common.service.UserGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zly.common.utils.CalendarUtils;
import com.zly.common.utils.StringUtils;
import com.zly.common.utils.TokenUtils;
import com.zly.common.vo.UserGroupVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zly
 * @since 2023-04-23
 */
@Service
public class UserGroupServiceImp extends ServiceImpl<UserGroupMapper, UserGroup> implements UserGroupService {

    @Override
    public UserGroupVo getUserGroupFileRole(String userId,String fgId) {

        UserGroupVo userGroupVo = this.baseMapper.getListByUser(userId,fgId);

        return userGroupVo;
    }

    @Override
    public List<UserGroupVo> getList(UserGroupDto userGroupDto, HttpServletRequest request) {

        String token = request.getHeader("token");
        TokenPayload tokenPayload = TokenUtils.getTokenPayload(token);
        userGroupDto.setSelecter(tokenPayload.getUserId());

        List<UserGroupVo> userGroupVoList = this.baseMapper.getList(userGroupDto);

        return userGroupVoList;
    }

    @Override
    public void editUserGroup(UserGroupDto userGroupDto, HttpServletRequest request) {

        String token = request.getHeader("token");
        TokenPayload tokenPayload = TokenUtils.getTokenPayload(token);

        UserGroup userGroup = new UserGroup();
        BeanUtils.copyProperties(userGroupDto,userGroup);

        if (StringUtils.isBlank(userGroupDto.getId())){
            userGroup.setOid(tokenPayload.getOid());
            userGroup.setCreateBy(tokenPayload.getUserId());
            userGroup.setCreateTime(CalendarUtils.now());
        }

        UserGroup one = this.baseMapper.selectOne(new LambdaQueryWrapper<UserGroup>()
                .eq(UserGroup::getGroupName, userGroupDto.getGroupName())
                .eq(UserGroup::getOid,userGroup.getOid()));
        if (ObjectUtils.isEmpty(one)){
            this.saveOrUpdate(userGroup);
        }else {
            throw new BizException(ErrorCode.SAME_USER_GROUP_NAME);
        }
    }

    @Override
    public void deleteUserGroup(UserGroupDto userGroupDto, HttpServletRequest request) {
        this.baseMapper.deleteById(userGroupDto.getId());
    }
}
