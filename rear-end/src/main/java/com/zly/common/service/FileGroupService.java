package com.zly.common.service;

import com.zly.common.dto.FileGroupDto;
import com.zly.common.entity.FileGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zly.common.vo.FileGroupVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zly
 * @since 2023-04-23
 */
public interface FileGroupService extends IService<FileGroup> {

    /**
     * 获取文件组列表
     * @param fileGroupDto
     * @param request
     * @return
     */
    List<FileGroupVo> getList(FileGroupDto fileGroupDto, HttpServletRequest request);

    /**
     * 新增或者编辑文件组
     * @param fileGroupDto
     * @param request
     */
    void editFileGroup(FileGroupDto fileGroupDto, HttpServletRequest request);

    /**
     * 删除文件组
     * @param fileGroupDto
     * @param request
     */
    void deleteFileGroup(FileGroupDto fileGroupDto, HttpServletRequest request);

    /**
     * 获得请求者关于某文件组权限
     * @param fileGroupDto
     * @param request
     * @return
     */
    FileGroupVo getRole(FileGroupDto fileGroupDto,HttpServletRequest request);
}
