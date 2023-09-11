package com.zly.common.controller;


import com.zly.common.dto.UgUserDto;
import com.zly.common.service.UgUserService;
import com.zly.common.vo.Restful;
import com.zly.common.vo.UgUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zly
 * @since 2023-04-26
 */
@Slf4j
@RestController
@RequestMapping("/common/ug-user")
public class UgUserController {

    @Autowired
    private UgUserService ugUserService;

    @RequestMapping("/getUgUserList")
    public Restful getUgUserList(@RequestBody UgUserDto userDto){

        log.info("getUgUserList");

        List<UgUserVo> ugUserList = ugUserService.getUgUserList(userDto);

        return Restful.ok(ugUserList);
    }

    @RequestMapping("/editUgUser")
    public Restful editUgUser(@RequestBody UgUserDto userDto){

        log.info("editUgUser");

        ugUserService.editUgUser(userDto);

        return Restful.ok();
    }

    @RequestMapping("/deleteUgUser")
    public Restful deleteUgUser(@RequestBody UgUserDto userDto){

        log.info("deleteUgUser");

        ugUserService.deleteUgUser(userDto);

        return Restful.ok();
    }
}
