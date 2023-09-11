package com.zly.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.zly.common.dto.FileDto;
import com.zly.common.dto.FileUserRoleDto;
import com.zly.common.dto.TokenPayload;
import com.zly.common.entity.File;
import com.zly.common.enums.LockType;
import com.zly.common.mapper.FileMapper;
import com.zly.common.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zly.common.service.FileUserRoleService;
import com.zly.common.service.UserGroupService;
import com.zly.common.utils.CalendarUtils;
import com.zly.common.utils.StringUtils;
import com.zly.common.utils.TokenUtils;
import com.zly.common.vo.FileVo;
import com.zly.common.vo.UserGroupVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zly
 * @since 2023-02-17
 */
@Slf4j
@Service
public class FileServiceImp extends ServiceImpl<FileMapper, File> implements FileService {

    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private FileUserRoleService fileUserRoleService;

    @Override
    public List<FileVo> getFileList(FileDto fileDto, HttpServletRequest request) {

        log.info("start getFileList");

        String token = request.getHeader("token");
        TokenPayload tokenPayload = TokenUtils.getTokenPayload(token);

        fileDto.setOid(tokenPayload.getOid());

        List<FileVo> fileVoList = this.baseMapper.getList(fileDto);

        return fileVoList;
    }

    @Override
    public void editFile(FileDto fileDto, HttpServletRequest request) {

        log.info("start editFile");

        String token = request.getHeader("token");
        TokenPayload tokenPayload = TokenUtils.getTokenPayload(token);

        File file = new File();
        BeanUtils.copyProperties(fileDto,file);


        if (StringUtils.isBlank(fileDto.getId())){
            file.setCreateBy(tokenPayload.getUserId());
            file.setCreateTime(CalendarUtils.now());
            file.setDelFlag(LockType.UNLOCK.getCode());
        }

        this.saveOrUpdate(file);

        if (fileDto.getId()==null){
            FileUserRoleDto fileUserRoleDto = new FileUserRoleDto();
            fileUserRoleDto.setFileId(file.getId());
            fileUserRoleDto.setUserId(tokenPayload.getUserId());
            fileUserRoleDto.setRoleRead("0");
            fileUserRoleDto.setRoleManage("0");

            fileUserRoleService.editFileUserRole(fileUserRoleDto);
        }
    }

    @Override
    public void editFileNull(FileDto fileDto) {

        LambdaUpdateWrapper<File> wrapper = new LambdaUpdateWrapper<>();

        wrapper.set(File::getFgId, null);

        wrapper.eq(File::getId, fileDto.getId());

        this.update(wrapper);

    }

    @Override
    public void deleteFile(FileDto fileDto, HttpServletRequest request) {

        log.info("start getFileList");

        this.baseMapper.deleteById(fileDto.getId());
    }

    @Override
    public FileVo getFileWithRole(FileDto fileDto, HttpServletRequest request) {

        log.info("start getFileList");

        File file = this.baseMapper.selectById(fileDto.getId());

        if (ObjectUtils.isEmpty(file)){
            return null;
        }

        String token = request.getHeader("token");
        TokenPayload tokenPayload = TokenUtils.getTokenPayload(token);
        fileDto.setSelecter(tokenPayload.getUserId());

        FileVo fileVo = new FileVo();
        if (StringUtils.isNotBlank(file.getGuid())){
            fileVo= this.baseMapper.getFileWithRole(fileDto);
        }else {
            fileVo.setRoleManage("1");
            fileVo.setRoleRead("1");
        }
        if (StringUtils.isNotBlank(file.getFgId())){
            UserGroupVo userGroupVo = userGroupService.getUserGroupFileRole(tokenPayload.getUserId(), file.getFgId());
            if (ObjectUtils.isNotEmpty(userGroupVo)){
                if (fileVo.getRoleRead().equals("0")&&userGroupVo.getRoleRead().equals("1")){
                    fileVo.setRoleRead("1");
                }
                if (fileVo.getRoleManage().equals("0")&&userGroupVo.getRoleManage().equals("1")){
                    fileVo.setRoleManage("1");
                }
            }
        }
        return fileVo;
    }
}
