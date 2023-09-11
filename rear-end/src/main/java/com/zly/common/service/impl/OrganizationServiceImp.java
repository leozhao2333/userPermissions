package com.zly.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zly.common.dto.OrganizationDto;
import com.zly.common.dto.TokenPayload;
import com.zly.common.entity.Organization;
import com.zly.common.enums.ErrorCode;
import com.zly.common.exception.BizException;
import com.zly.common.mapper.OrganizationMapper;
import com.zly.common.service.OrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zly.common.utils.CalendarUtils;
import com.zly.common.utils.TokenUtils;
import com.zly.common.vo.OrganizationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zly
 * @since 2023-02-17
 */
@Service
public class OrganizationServiceImp extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    @Override
    public List<OrganizationVo> getList(OrganizationDto organizationDto, HttpServletRequest request) {

        LambdaQueryWrapper<Organization> wrapper = new LambdaQueryWrapper<>();

        List<Organization> organizationList = this.baseMapper.selectList(wrapper);

        List<OrganizationVo> organizationVoList = new ArrayList<>();
        for (Organization organization:organizationList){
            OrganizationVo organizationVo = new OrganizationVo();
            BeanUtils.copyProperties(organization,organizationVo);
            organizationVoList.add(organizationVo);
        }

        return organizationVoList;
    }

    @Override
    public void editOrganization(OrganizationDto organizationDto, HttpServletRequest request) {

        String token = request.getHeader("token");
        TokenPayload tokenPayload = TokenUtils.getTokenPayload(token);

        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationDto,organization);

        if (organizationDto.getId()==null){

            organization.setCreateBy(tokenPayload.getUserId());
            organization.setCreateTime(CalendarUtils.now());

        }else {
            Organization one = this.baseMapper.selectOne(new LambdaQueryWrapper<Organization>()
                    .eq(Organization::getId, organizationDto.getId()));
            if (ObjectUtils.isEmpty(one)){
                throw new BizException(ErrorCode.LOGIN_ERROR_USERNAME);
            }
        }

        this.saveOrUpdate(organization);
    }

    @Override
    public void deleteOrganization(OrganizationDto organizationDto, HttpServletRequest request) {

        this.baseMapper.deleteById(organizationDto.getId());

    }

    @Override
    public String getByid(String oid) {
        Organization organization = this.baseMapper.selectOne(new LambdaQueryWrapper<Organization>()
                .eq(Organization::getId, oid));
        return organization.getOrganizationName();
    }
}
