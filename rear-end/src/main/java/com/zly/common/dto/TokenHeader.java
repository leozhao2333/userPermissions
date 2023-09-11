package com.zly.common.dto;

import lombok.Data;

@Data
public class TokenHeader {

    //签名的算法（algorithm）
    private String alg;

    //令牌（token）的类型
    private String type;
}
