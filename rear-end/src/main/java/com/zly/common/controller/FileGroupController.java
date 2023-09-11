package com.zly.common.controller;


import com.zly.common.dto.FileGroupDto;
import com.zly.common.enums.ErrorCode;
import com.zly.common.exception.BizException;
import com.zly.common.service.FileGroupService;
import com.zly.common.utils.StringUtils;
import com.zly.common.vo.FileGroupVo;
import com.zly.common.vo.Restful;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/common/file-group")
public class FileGroupController {

    @Autowired
    private FileGroupService fileGroupService;

    @RequestMapping("/getList")
    public Restful getList(@RequestBody FileGroupDto fileGroupDto, HttpServletRequest request){

        log.info("getList");

        List<FileGroupVo> fileGroupVoList = fileGroupService.getList(fileGroupDto, request);

        return Restful.ok(fileGroupVoList);
    }

    @RequestMapping("/editFileGroup")
    public Restful editFileGroup(@RequestBody FileGroupDto fileGroupDto, HttpServletRequest request){

        log.info("editFileGroup");

        fileGroupService.editFileGroup(fileGroupDto, request);

        return Restful.ok();
    }

    @RequestMapping("/deleteFileGroup")
    public Restful deleteFileGroup(@RequestBody FileGroupDto fileGroupDto, HttpServletRequest request){

        log.info("deleteFileGroup");

        if (StringUtils.isBlank(fileGroupDto.getId())){
            throw new BizException(ErrorCode.NULL_PARAM);
        }

        fileGroupService.deleteFileGroup(fileGroupDto, request);

        return Restful.ok();
    }

    @RequestMapping("/getRole")
    public Restful getRole(@RequestBody FileGroupDto fileGroupDto, HttpServletRequest request){

        log.info("getRole");

        if (StringUtils.isBlank(fileGroupDto.getId())){
            throw new BizException(ErrorCode.NULL_PARAM);
        }

        FileGroupVo role = fileGroupService.getRole(fileGroupDto, request);

        return Restful.ok(role);
    }

}
