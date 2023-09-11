package com.zly.common.mapper;

import com.zly.common.dto.FgUgRoleDto;
import com.zly.common.entity.FgUgRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zly.common.vo.FgUgRoleVo;
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
public interface FgUgRoleMapper extends BaseMapper<FgUgRole> {

    List<FgUgRoleVo> getUgRoleByFg(@Param("dto")FgUgRoleDto dto);

}
