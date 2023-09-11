package com.zly.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zly.common.dto.GroupDto;
import com.zly.common.dto.TokenPayload;
import com.zly.common.entity.Group;
import com.zly.common.enums.ErrorCode;
import com.zly.common.exception.BizException;
import com.zly.common.mapper.GroupMapper;
import com.zly.common.service.GroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zly.common.utils.CalendarUtils;
import com.zly.common.utils.TokenUtils;
import com.zly.common.vo.GroupVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zly
 * @since 2023-02-17
 */
@Service
public class GroupServiceImp extends ServiceImpl<GroupMapper, Group> implements GroupService {

    @Override
    public List<GroupVo> getList(GroupDto groupDto, HttpServletRequest request) {

        LambdaQueryWrapper<Group> wrapper = new LambdaQueryWrapper<>();

        List<Group> groups = this.baseMapper.selectList(wrapper);

        List<GroupVo> groupVoList = new ArrayList<>();
        for (Group group:groups){
            GroupVo groupVo = new GroupVo();
            BeanUtils.copyProperties(group,groupVo);
            groupVoList.add(groupVo);
        }

        return groupVoList;
    }

    @Override
    public void editGroup(GroupDto groupDto, HttpServletRequest request) {

        String token = request.getHeader("token");
        TokenPayload tokenPayload = TokenUtils.getTokenPayload(token);

        Group group = new Group();
        BeanUtils.copyProperties(groupDto,group);

        if (groupDto.getId()==null){
            group.setCreateBy(tokenPayload.getUserId());
            group.setCreateTime(CalendarUtils.now());

        }else {
            Group one = this.baseMapper.selectOne(new LambdaQueryWrapper<Group>()
                    .eq(Group::getId, groupDto.getId()));
            if (ObjectUtils.isEmpty(one)){
                throw new BizException(ErrorCode.LOGIN_ERROR_USERNAME);
            }
        }
        group.setUpdateBy(tokenPayload.getUserId());
        group.setUpdateTime(CalendarUtils.now());

        this.saveOrUpdate(group);
    }

    @Override
    public void deleteGroup(GroupDto groupDto) {

        this.baseMapper.deleteById(groupDto.getId());

    }
}
