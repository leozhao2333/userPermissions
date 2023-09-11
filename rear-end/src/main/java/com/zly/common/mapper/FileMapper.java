package com.zly.common.mapper;

import com.zly.common.dto.FileDto;
import com.zly.common.entity.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zly.common.vo.FileVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zly
 * @since 2023-02-17
 */
public interface FileMapper extends BaseMapper<File> {

    List<FileVo> getList(@Param("dto") FileDto dto);

    FileVo getFileWithRole(@Param("dto") FileDto dto);

//    FileVo getFileByGUID(@Param("dto") FileDto dto);

}
