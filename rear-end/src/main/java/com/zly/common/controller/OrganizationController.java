package com.zly.common.controller;


import com.zly.common.dto.OrganizationDto;
import com.zly.common.enums.ErrorCode;
import com.zly.common.exception.BizException;
import com.zly.common.service.OrganizationService;
import com.zly.common.utils.StringUtils;
import com.zly.common.vo.OrganizationVo;
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
@RequestMapping("/common/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping("/getList")
    public Restful getList(OrganizationDto organizationDto, HttpServletRequest request){

        List<OrganizationVo> list = organizationService.getList(organizationDto, request);

        return Restful.ok(list);
    }

    @RequestMapping("/editOrganization")
    public Restful editOrganization(OrganizationDto organizationDto, HttpServletRequest request){

        organizationService.editOrganization(organizationDto, request);

        return Restful.ok();
    }

    @RequestMapping("/deleteOrganization")
    public Restful deleteOrganization(OrganizationDto organizationDto, HttpServletRequest request){

        if (StringUtils.isBlank(organizationDto.getId())){
            throw new BizException(ErrorCode.NULL_PARAM);
        }

        organizationService.deleteOrganization(organizationDto, request);

        return Restful.ok();
    }

}
