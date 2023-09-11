package com.zly.common.mapper;

import com.zly.common.dto.UserGroupDto;
import com.zly.common.entity.UserGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zly.common.vo.UserGroupVo;
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
public interface UserGroupMapper extends BaseMapper<UserGroup> {

    UserGroupVo getListByUser(@Param("uId")String uId,@Param("fgId")String fgId);

    List<UserGroupVo> getList(@Param("dto")UserGroupDto dto);

}
