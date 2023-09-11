package com.zly.common.service;

import com.zly.common.dto.FileDto;
import com.zly.common.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zly.common.vo.FileVo;

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
public interface FileService extends IService<File> {

    /**
     * 获取文件列表
     * @param fileDto
     * @param request
     * @return
     */
    List<FileVo> getFileList(FileDto fileDto, HttpServletRequest request);

    /**
     * 新增或者修改文件
     * @param fileDto
     * @param request
     */
    void editFile(FileDto fileDto,HttpServletRequest request);

    void editFileNull(FileDto fileDto);

    /**
     * 删除文件
     * @param fileDto
     * @param request
     */
    void deleteFile(FileDto fileDto,HttpServletRequest request);

    /**
     * 获取文件对应用户权限
     * @param fileDto
     * @param request
     * @return
     */
    FileVo getFileWithRole(FileDto fileDto,HttpServletRequest request);

}
