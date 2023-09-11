package com.zly.common.mapper;

import com.zly.common.dto.UgUserDto;
import com.zly.common.entity.UgUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zly.common.vo.UgUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zly
 * @since 2023-04-26
 */
public interface UgUserMapper extends BaseMapper<UgUser> {

    List<UgUserVo> getUgUserList(@Param("dto")UgUserDto dto);

}
