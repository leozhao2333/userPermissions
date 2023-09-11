package com.zly.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 锁定枚举
 */
@AllArgsConstructor
@Getter
public enum LockType {

    //锁定或已删除
    LOCK(1),
    //未锁定或使用中
    UNLOCK(0)
    ;
    private final Integer code;

    public Integer getCode() {
        return this.code;
    }
}
