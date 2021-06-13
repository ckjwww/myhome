package com.godcoder.myhome.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Blob;

@Getter
@Setter
public class fileDTO {

    private Integer fileId;
    private String orgFileName;
    private String writeFileName;
    private Long fileSize;
    private String contentType;
    private Blob chkSum;

}
