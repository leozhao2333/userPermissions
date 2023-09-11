package com.zly.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zly.common.dto.FgUgRoleDto;
import com.zly.common.dto.FileGroupDto;
import com.zly.common.dto.TokenPayload;
import com.zly.common.entity.FileGroup;
import com.zly.common.entity.UgUser;
import com.zly.common.enums.ErrorCode;
import com.zly.common.exception.BizException;
import com.zly.common.mapper.FileGroupMapper;
import com.zly.common.service.FgUgRoleService;
import com.zly.common.service.FileGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zly.common.service.UgUserService;
import com.zly.common.utils.CalendarUtils;
import com.zly.common.utils.TokenUtils;
import com.zly.common.vo.FgUgRoleVo;
import com.zly.common.vo.FileGroupVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zly
 * @since 2023-04-23
 */
@Service
public class FileGroupServiceImp extends ServiceImpl<FileGroupMapper, FileGroup> implements FileGroupService {

    @Autowired
    private FgUgRoleService fgUgRoleService;

    @Autowired
    private UgUserService ugUserService;


    @Override
    public List<FileGroupVo> getList(FileGroupDto fileGroupDto, HttpServletRequest request) {

        String token = request.getHeader("token");
        TokenPayload tokenPayload = TokenUtils.getTokenPayload(token);
        fileGroupDto.setSelecter(tokenPayload.getUserId());

        List<FileGroupVo> fileGroupVoList = this.baseMapper.getList(fileGroupDto);

        return fileGroupVoList;
    }

    @Override
    public void editFileGroup(FileGroupDto fileGroupDto, HttpServletRequest request) {

        String token = request.getHeader("token");
        TokenPayload tokenPayload = TokenUtils.getTokenPayload(token);

        FileGroup fileGroup = new FileGroup();
        BeanUtils.copyProperties(fileGroupDto,fileGroup);

        fileGroup.setOid(tokenPayload.getOid());
        fileGroup.setCreateBy(tokenPayload.getUserId());
        fileGroup.setCreateTime(CalendarUtils.now());

        FileGroup one = this.baseMapper.selectOne(new LambdaQueryWrapper<FileGroup>()
                .eq(FileGroup::getOid, fileGroup.getOid())
                .eq(FileGroup::getFileGroupName, fileGroup.getFileGroupName()));

        if (ObjectUtils.isEmpty(one)){
            this.saveOrUpdate(fileGroup);
        }else {
            throw new BizException(ErrorCode.SAME_FILE_GROUP_NAME);
        }
    }

    @Override
    public void deleteFileGroup(FileGroupDto fileGroupDto, HttpServletRequest request) {
        this.baseMapper.deleteById(fileGroupDto.getId());
    }

    @Override
    public FileGroupVo getRole(FileGroupDto fileGroupDto, HttpServletRequest request) {

        String token = request.getHeader("token");
        TokenPayload tokenPayload = TokenUtils.getTokenPayload(token);

        FileGroupVo fileGroupVo = new FileGroupVo();
        fileGroupVo.setRoleRead("1");
        fileGroupVo.setRoleManage("1");

        FgUgRoleDto fgUgRoleDto = new FgUgRoleDto();
        fgUgRoleDto.setFgId(fileGroupDto.getId());
        List<FgUgRoleVo> fgUgRoleVoList = fgUgRoleService.getUgRoleByFg(fgUgRoleDto);
        if (CollectionUtils.isEmpty(fgUgRoleVoList)){
            fileGroupVo.setRoleRead("0");
            fileGroupVo.setRoleManage("0");
            return fileGroupVo;
        }

        List<String> ugIds = fgUgRoleVoList.stream().map(a -> a.getUgId()).collect(Collectors.toList());
        Map<String, FgUgRoleVo> map = fgUgRoleVoList.stream().collect(Collectors.toMap(
                key -> key.getUgId(),
                value -> value
        ));

        List<UgUser> uguserList = ugUserService.getUgUserByUgUser(ugIds, tokenPayload.getUserId());

        if (CollectionUtils.isEmpty(uguserList)){
//            fileGroupVo.setRoleRead("1");
//            fileGroupVo.setRoleManage("1");
            return fileGroupVo;
        }
        for (UgUser ugUser:uguserList){
            FgUgRoleVo fgUgRoleVo = map.get(ugUser.getUgid());
            if (fgUgRoleVo==null){
                continue;
            }else {
                if (fileGroupVo.getRoleRead().equals("1")
                        &&fgUgRoleVo.getRoleRead().equals("0")){
                    fileGroupVo.setRoleRead("0");
                }
                if (fileGroupVo.getRoleManage().equals("1")
                        &&fgUgRoleVo.getRoleManage().equals("0")){
                    fileGroupVo.setRoleManage("0");
                }
            }
        }
        return fileGroupVo;
    }
}
