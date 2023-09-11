package com.zly.common.service;

import com.zly.common.dto.OrganizationDto;
import com.zly.common.entity.Organization;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zly.common.vo.OrganizationVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zly
 * @since 2023-02-17
 */
public interface OrganizationService extends IService<Organization> {

    /**
     * 获取组织列表
     * @param organizationDto
     * @param request
     * @return
     */
    List<OrganizationVo> getList(OrganizationDto organizationDto, HttpServletRequest request);

    /**
     * 编辑组织
     * @param organizationDto
     * @param request
     */
    void editOrganization(OrganizationDto organizationDto, HttpServletRequest request);

    /**
     * 删除组织
     * @param organizationDto
     * @param request
     */
    void deleteOrganization(OrganizationDto organizationDto, HttpServletRequest request);

    String getByid(String oid);
}
