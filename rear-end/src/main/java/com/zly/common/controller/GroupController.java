package com.zly.common.controller;


import com.zly.common.dto.GroupDto;
import com.zly.common.enums.ErrorCode;
import com.zly.common.exception.BizException;
import com.zly.common.service.GroupService;
import com.zly.common.utils.StringUtils;
import com.zly.common.vo.GroupVo;
import com.zly.common.vo.Restful;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2023-02-17
 */
@Slf4j
@RestController
@RequestMapping("/common/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @RequestMapping("/getList")
    public Restful getList(GroupDto groupDto, HttpServletRequest request){

        log.info("getList");

        List<GroupVo> list = groupService.getList(groupDto, request);

        return Restful.ok(list);
    }

    @RequestMapping("/editGroup")
    public Restful editGroup(GroupDto groupDto, HttpServletRequest request){

        log.info("editGroup");

        groupService.editGroup(groupDto, request);

        return Restful.ok();
    }

    @RequestMapping("/deleteGroup")
    public Restful deleteGroup(GroupDto groupDto, HttpServletRequest request){

        log.info("deleteGroup");

        if (StringUtils.isBlank(groupDto.getId())){
            throw new BizException(ErrorCode.NULL_PARAM);
        }

        groupService.deleteGroup(groupDto);

        return Restful.ok();
    }

}
