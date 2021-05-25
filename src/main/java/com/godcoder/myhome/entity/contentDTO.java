package com.godcoder.myhome.entity;

import javax.validation.constraints.*;
import java.io.Serializable;

public class contentDTO {

    private Integer id;

    @NotBlank(message = "빈값을입력할수없습니다.")
    @Size(min=5, max=30, message = "필요값")
    private String title = "";

    private String content = "";

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "Content(Id: " + this.id + ", Title: " + this.title + ", Content: " + this.content + ")";
    }
}
