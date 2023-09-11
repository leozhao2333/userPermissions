package com.zly.common.controller;

import com.zly.common.dto.FileDto;
import com.zly.common.enums.ErrorCode;
import com.zly.common.exception.BizException;
import com.zly.common.service.FileService;
import com.zly.common.utils.StringUtils;
import com.zly.common.vo.FileVo;
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
 * @since 2023-02-17
 */
@Slf4j
@RestController
@RequestMapping("/common/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/getFileList")
    public Restful getFileList(@RequestBody FileDto fileDto, HttpServletRequest request){

        log.info("getFileList");

        List<FileVo> fileList = fileService.getFileList(fileDto, request);

        return Restful.ok(fileList);
    }

    @RequestMapping("/saveFile")
    public Restful saveFile(@RequestBody FileDto fileDto, HttpServletRequest request){

        log.info("saveFile");

        fileService.editFile(fileDto, request);

        return Restful.ok();
    }

    @RequestMapping("/updateFile")
    public Restful updateFile(@RequestBody FileDto fileDto, HttpServletRequest request){

        log.info("updateFile");

        fileService.editFile(fileDto, request);

        return Restful.ok();
    }

    @RequestMapping("/editFileNull")
    public Restful editFileNull(@RequestBody FileDto fileDto, HttpServletRequest request){

        log.info("editFileNull");

        fileService.editFileNull(fileDto);

        return Restful.ok();
    }

    @RequestMapping("/deleteFile")
    public Restful deleteFile(@RequestBody FileDto fileDto, HttpServletRequest request){

        log.info("deleteFile");

        if (StringUtils.isBlank(fileDto.getId())){
            throw new BizException(ErrorCode.NULL_PARAM);
        }

        fileService.deleteFile(fileDto, request);

        return Restful.ok();
    }

    @RequestMapping("/getFileWithRole")
    public Restful getFileWithRole(@RequestBody FileDto fileDto, HttpServletRequest request){

        log.info("getFileWithRole");

        FileVo fileById = fileService.getFileWithRole(fileDto, request);

        return Restful.ok(fileById);
    }
}
