package com.zly.common.mapper;

import com.zly.common.dto.FileUserRoleDto;
import com.zly.common.entity.FileUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zly.common.vo.FileUserRoleVo;
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
public interface FileUserRoleMapper extends BaseMapper<FileUserRole> {

    List<FileUserRoleVo> getUserRoleByFile(@Param("dto")FileUserRoleDto dto);

}
