package com.zly.common.controller;


import com.zly.common.dto.FileUserRoleDto;
import com.zly.common.enums.ErrorCode;
import com.zly.common.exception.BizException;
import com.zly.common.service.FileUserRoleService;
import com.zly.common.utils.StringUtils;
import com.zly.common.vo.FileUserRoleVo;
import com.zly.common.vo.Restful;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zly
 * @since 2023-04-23
 */
@Slf4j
@RestController
@RequestMapping("/common/file-user-role")
public class FileUserRoleController {

    @Autowired
    private FileUserRoleService fileUserRoleService;

    @RequestMapping("/editFileUserRole")
    public Restful editFileUserRole(@RequestBody FileUserRoleDto fileUserRoleDto){

        log.info("editFileUserRole");

        fileUserRoleService.editFileUserRole(fileUserRoleDto);

        return Restful.ok();
    }

    @RequestMapping("/deleteFileUserRole")
    public Restful deleteFileUserRole(@RequestBody FileUserRoleDto fileUserRoleDto){

        log.info("editFileUserRole");

        if (StringUtils.isBlank(fileUserRoleDto.getId())){
            throw new BizException(ErrorCode.NULL_PARAM);
        }

        fileUserRoleService.deleteFileUserRole(fileUserRoleDto);

        return Restful.ok();
    }

    @RequestMapping("/getUserRoleByFile")
    public Restful getUserRoleByFile(@RequestBody FileUserRoleDto fileUserRoleDto){

        log.info("getUserRoleByFile");

        if (StringUtils.isBlank(fileUserRoleDto.getFileId())){
            throw new BizException(ErrorCode.NULL_PARAM);
        }

        List<FileUserRoleVo> fileUserRoleVoList = fileUserRoleService.getUserRoleByFile(fileUserRoleDto);

        return Restful.ok(fileUserRoleVoList);
    }
}
