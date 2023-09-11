package com.zly.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zly.common.dto.UgUserDto;
import com.zly.common.entity.UgUser;
import com.zly.common.mapper.UgUserMapper;
import com.zly.common.service.UgUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zly.common.vo.UgUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zly
 * @since 2023-04-26
 */
@Service
public class UgUserServiceImp extends ServiceImpl<UgUserMapper, UgUser> implements UgUserService {

    @Override
    public List<UgUserVo> getUgUserList(UgUserDto userDto) {

        List<UgUserVo> ugUserList = this.baseMapper.getUgUserList(userDto);
        return ugUserList;
    }

    @Override
    public void editUgUser(UgUserDto userDto) {

        UgUser ugUser = new UgUser();
        BeanUtils.copyProperties(userDto,ugUser);

        this.saveOrUpdate(ugUser);
    }

    @Override
    public void deleteUgUser(UgUserDto userDto) {
        this.baseMapper.deleteById(userDto.getId());
    }

    @Override
    public List<UgUser> getUgUserByUgUser(List<String> ugIds, String uid) {

        LambdaQueryWrapper<UgUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UgUser::getUid,uid);
        wrapper.in(UgUser::getUgid,ugIds);

        return this.baseMapper.selectList(wrapper);


    }
}
