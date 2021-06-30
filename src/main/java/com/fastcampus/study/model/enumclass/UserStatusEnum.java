package com.fastcampus.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    REGISTERED(0, "등록", "사용자 등록 상태"),
    UNREGISTERED(1, "해지", "사용자 해지 상태")
    ;

    private Integer id;
    private String title;
    private String description;
}
