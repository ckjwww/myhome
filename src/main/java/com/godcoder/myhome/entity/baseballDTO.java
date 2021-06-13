package com.godcoder.myhome.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class baseballDTO {

    private String manageId;
    private String dropDownCd;
    private String dropDownNm;
    private String checkBox;
    private String radioButton;
    private String textString;
    private Integer textInteger;
    private Integer fileId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date textDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime typeDateTime;

    private String makeId;
}
