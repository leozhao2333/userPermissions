package com.zly.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zly.common.dto.FileUserRoleDto;
import com.zly.common.entity.FileUserRole;
import com.zly.common.mapper.FileUserRoleMapper;
import com.zly.common.service.FileUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zly.common.vo.FileUserRoleVo;
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
public class FileUserRoleServiceImp extends ServiceImpl<FileUserRoleMapper, FileUserRole> implements FileUserRoleService {
    @Override
    public void editFileUserRole(FileUserRoleDto fileUserRoleDto) {

        FileUserRole fileUserRole = new FileUserRole();
        BeanUtils.copyProperties(fileUserRoleDto,fileUserRole);

        FileUserRole one = this.baseMapper.selectOne(new LambdaQueryWrapper<FileUserRole>()
                .eq(FileUserRole::getFileId, fileUserRole.getFileId())
                .eq(FileUserRole::getUserId, fileUserRole.getUserId()));
        if (ObjectUtils.isEmpty(one)){

        }else {
            fileUserRole.setId(one.getId());
        }

        this.saveOrUpdate(fileUserRole);
    }

    @Override
    public void deleteFileUserRole(FileUserRoleDto fileUserRoleDto) {
        this.baseMapper.deleteById(fileUserRoleDto.getId());
    }

    @Override
    public List<FileUserRoleVo> getUserRoleByFile(FileUserRoleDto fileUserRoleDto) {

        List<FileUserRoleVo> fileUserRoleVoList = this.baseMapper.getUserRoleByFile(fileUserRoleDto);

        for (FileUserRoleVo fileUserRoleVo:fileUserRoleVoList){
            if (fileUserRoleVo.getRoleRead().equals("0")){
                fileUserRoleVo.setRoleReadName("可读");
            }else {
                fileUserRoleVo.setRoleReadName("不可读");
            }
            if (fileUserRoleVo.getRoleManage().equals("0")){
                fileUserRoleVo.setRoleManageName("管理");
            }else {
                fileUserRoleVo.setRoleManageName("不可管理");
            }
        }

        return fileUserRoleVoList;
    }
}
