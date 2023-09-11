package com.zly.common.controller;


import com.zly.common.dto.FgUgRoleDto;
import com.zly.common.enums.ErrorCode;
import com.zly.common.exception.BizException;
import com.zly.common.service.FgUgRoleService;
import com.zly.common.utils.StringUtils;
import com.zly.common.vo.FgUgRoleVo;
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
@RequestMapping("/common/fg-ug-role")
public class FgUgRoleController {

    @Autowired
    private FgUgRoleService fgUgRoleService;

    @RequestMapping("/getUgRoleByFg")
    public Restful getUgRoleByFg(@RequestBody FgUgRoleDto fgUgRoleDto){

        log.info("getUgRoleByFg");

        if (StringUtils.isBlank(fgUgRoleDto.getFgId())){
            throw new BizException(ErrorCode.NULL_PARAM);
        }

        List<FgUgRoleVo> fgUgRoleVoList = fgUgRoleService.getUgRoleByFg(fgUgRoleDto);

        return Restful.ok(fgUgRoleVoList);
    }

    @RequestMapping("/editFgUgRole")
    public Restful editFgUgRole(@RequestBody FgUgRoleDto fgUgRoleDto){

        log.info("editFgUgRole");

        fgUgRoleService.editFgUgRole(fgUgRoleDto);

        return Restful.ok();
    }

    @RequestMapping("/deleteFgUgRole")
    public Restful deleteFgUgRole(@RequestBody FgUgRoleDto fgUgRoleDto){

        log.info("deleteFgUgRole");

        if (StringUtils.isBlank(fgUgRoleDto.getId())){
            throw new BizException(ErrorCode.NULL_PARAM);
        }

        fgUgRoleService.deleteFgUgRole(fgUgRoleDto);

        return Restful.ok();
    }

}
