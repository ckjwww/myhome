package com.godcoder.myhome.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.DateTimeException;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date textDate;

    private String makeId;
}
