package com.zly.common.controller;


import com.zly.common.dto.UserGroupDto;
import com.zly.common.enums.ErrorCode;
import com.zly.common.exception.BizException;
import com.zly.common.service.UserGroupService;
import com.zly.common.utils.StringUtils;
import com.zly.common.vo.Restful;
import com.zly.common.vo.UserGroupVo;
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
@RequestMapping("/common/user-group")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @RequestMapping("/getList")
    public Restful getList(@RequestBody UserGroupDto userGroupDto, HttpServletRequest request){

        log.info("getList");

        List<UserGroupVo> userGroupVoList = userGroupService.getList(userGroupDto, request);

        return Restful.ok(userGroupVoList);
    }

    @RequestMapping("/editUserGroup")
    public Restful editUserGroup(@RequestBody UserGroupDto userGroupDto, HttpServletRequest request){

        log.info("editUserGroup");

        if (StringUtils.isBlank(userGroupDto.getGroupName())){
            throw new BizException(ErrorCode.NULL_PARAM);
        }

        userGroupService.editUserGroup(userGroupDto, request);

        return Restful.ok();
    }

    @RequestMapping("/deleteUserGroup")
    public Restful deleteUserGroup(@RequestBody UserGroupDto userGroupDto, HttpServletRequest request){

        log.info("deleteUserGroup");

        if (StringUtils.isBlank(userGroupDto.getId())){
            throw new BizException(ErrorCode.NULL_PARAM);
        }

        userGroupService.deleteUserGroup(userGroupDto, request);

        return Restful.ok();
    }
}
