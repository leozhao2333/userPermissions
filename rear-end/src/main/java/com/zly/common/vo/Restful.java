package com.zly.common.vo;

import com.zly.common.enums.ErrorCode;
import lombok.Data;


@Data
public class Restful {

    /**
     * 返回状态码
     */
    private int code;

    /**
     * 返回状态信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 列表返回总条数
     */
    private long recordsCount;

    public Restful setCode(int code) {
        this.code = code;
        return this;
    }

    public Restful setData(Object data) {
        this.data = data;
        return this;
    }

    public Restful setMsg(String message) {
        this.msg = message;
        return this;
    }

    public Restful setRecordsCount(long recordsCount) {
        this.recordsCount = recordsCount;
        return this;
    }

    public Restful setCodeEnum(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
        return this;
    }

    public static Restful ok() {
        return new Restful().setCodeEnum(ErrorCode.OK);
    }

    public static Restful ok(Object data) {
        return new Restful().setCodeEnum(ErrorCode.OK).setData(data);
    }

    public static Restful ok(Object data,String message) {
        return new Restful().setCodeEnum(ErrorCode.OK).setData(data).setMsg(message);
    }

    public static Restful ok(String message) {
        return new Restful().setCodeEnum(ErrorCode.OK).setMsg(message);
    }

    public static Restful ok(Object data, long recordsCount) {
        return new Restful().setCodeEnum(ErrorCode.OK).setData(data).setRecordsCount(recordsCount);
    }

    public static Restful error(ErrorCode errorCode) {
        return new Restful().setCodeEnum(errorCode);
    }

    public static Restful error(int code, String message) {
        return new Restful().setCode(code).setMsg(message);
    }
}
