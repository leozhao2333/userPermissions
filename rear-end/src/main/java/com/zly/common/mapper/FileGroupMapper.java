package com.zly.common.mapper;

import com.zly.common.dto.FileGroupDto;
import com.zly.common.entity.FileGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zly.common.vo.FileGroupVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zly
 * @since 2023-04-23
 */
public interface FileGroupMapper extends BaseMapper<FileGroup> {

    List<FileGroupVo> getList(@Param("dto")FileGroupDto dto);

}
