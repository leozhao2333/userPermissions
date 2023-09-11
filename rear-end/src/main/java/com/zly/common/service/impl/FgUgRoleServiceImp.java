package com.zly.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zly.common.dto.FgUgRoleDto;
import com.zly.common.entity.FgUgRole;
import com.zly.common.mapper.FgUgRoleMapper;
import com.zly.common.service.FgUgRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zly.common.vo.FgUgRoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
public class FgUgRoleServiceImp extends ServiceImpl<FgUgRoleMapper, FgUgRole> implements FgUgRoleService {

    @Override
    public List<FgUgRoleVo> getUgRoleByFg(FgUgRoleDto fgUgRoleDto) {

        List<FgUgRoleVo> fgUgRoleVoList = this.baseMapper.getUgRoleByFg(fgUgRoleDto);

        for (FgUgRoleVo fgUgRoleVo:fgUgRoleVoList){
            if (fgUgRoleVo.getRoleRead().equals("0")){
                fgUgRoleVo.setRoleReadName("可读");
            }else {
                fgUgRoleVo.setRoleReadName("不可读");
            }
            if (fgUgRoleVo.getRoleManage().equals("0")){
                fgUgRoleVo.setRoleManageName("管理");
            }else {
                fgUgRoleVo.setRoleManageName("不可管理");
            }
        }


        return fgUgRoleVoList;
    }

    @Override
    public void editFgUgRole(FgUgRoleDto fgUgRoleDto) {

        FgUgRole fgUgRole = new FgUgRole();
        BeanUtils.copyProperties(fgUgRoleDto,fgUgRole);

        FgUgRole one = this.baseMapper.selectOne(new LambdaQueryWrapper<FgUgRole>()
                .eq(FgUgRole::getFgId, fgUgRole.getFgId())
                .eq(FgUgRole::getUgId, fgUgRole.getUgId()));
        if (ObjectUtils.isEmpty(one)){

        }else {
            fgUgRole.setId(one.getId());
        }
        this.saveOrUpdate(fgUgRole);
    }

    @Override
    public void deleteFgUgRole(FgUgRoleDto fgUgRoleDto) {
        this.baseMapper.deleteById(fgUgRoleDto.getId());
    }
}
