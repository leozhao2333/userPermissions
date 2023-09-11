package com.zly.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zly.common.dto.TokenPayload;
import com.zly.common.dto.UserDto;
import com.zly.common.entity.User;
import com.zly.common.enums.ErrorCode;
import com.zly.common.exception.BizException;
import com.zly.common.mapper.UserMapper;
import com.zly.common.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zly.common.utils.CalendarUtils;
import com.zly.common.utils.StringUtils;
import com.zly.common.utils.TokenUtils;
import com.zly.common.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public UserVo getUserInfo(String id) {

        log.info("start getUserInfo");

        User user = this.baseMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getId, id));
        if (ObjectUtils.isEmpty(user)){
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);

        return userVo;
    }

    @Override
    public UserVo login(String userName, String pwd) {

        log.info("start login");

        if (StringUtils.isBlank(userName)){
            throw new BizException(ErrorCode.LOGIN_ERROR_USERNAME);
        } else if (StringUtils.isBlank(pwd)){
            throw new BizException(ErrorCode.ERROR_PASSWORD_IS_NULL);
        }

        User user = this.baseMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getLoginName, userName)
                .eq(User::getUserPass, pwd)
                );

        if (ObjectUtils.isEmpty(user)){
            return null;
        }

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);

        return userVo;
    }

    @Override
    public List<UserVo> getList(UserDto userDto,HttpServletRequest request) {

        String oid = request.getHeader("token");
        TokenPayload tokenPayload = TokenUtils.getTokenPayload(oid);

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getOid,tokenPayload.getOid());

        if (StringUtils.isNotBlank(userDto.getUserName())){
            wrapper.like(User::getUserName,userDto.getUserName());
        }

        List<User> users = this.baseMapper.selectList(wrapper);

        List<UserVo> userVoList = new ArrayList<>();
        for (User user:users){
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user,userVo);
            userVoList.add(userVo);
        }

        return userVoList;
    }


    @Override
    public String cteateUser(UserDto userDto, HttpServletRequest request) {

        User one = this.baseMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getLoginName, userDto.getLoginName()));

        if (ObjectUtils.isEmpty(one)){
            String token = request.getHeader("token");
            TokenPayload tokenPayload = TokenUtils.getTokenPayload(token);

            User user = new User();
            BeanUtils.copyProperties(userDto,user);

            user.setOid(tokenPayload.getOid());
            user.setCreateBy(tokenPayload.getUserId());
            user.setCreateTime(CalendarUtils.now());

            this.saveOrUpdate(user);

            return user.getId();
        }else {
            throw new BizException(ErrorCode.SAME_LOGIN_NAME);
        }
    }

    @Override
    public void editUser(UserDto userDto, HttpServletRequest request) {

        String token = request.getHeader("token");
        TokenPayload tokenPayload = TokenUtils.getTokenPayload(token);

        User user = new User();
        BeanUtils.copyProperties(userDto,user);

        User one = this.baseMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getId, userDto.getId()));
        if (ObjectUtils.isEmpty(one)){
            throw new BizException(ErrorCode.LOGIN_ERROR_USERNAME);
        }


        this.saveOrUpdate(user);
    }

    @Override
    public void deleteUser(UserDto userDto,HttpServletRequest request) {

        String token = request.getHeader("token");
        TokenPayload tokenPayload = TokenUtils.getTokenPayload(token);

        this.baseMapper.deleteById(userDto.getId());
    }
}
