package com.fastcampus.study.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchParam {
    private String account;
    private String email;
    private int page;
}
