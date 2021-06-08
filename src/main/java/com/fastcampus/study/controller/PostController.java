package com.fastcampus.study.controller;

import com.fastcampus.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    // HTML <form> tag
    // ajax 검색 등에서 사용
    // http post body에 데이터 포함
    // json, xml, multipart-frm, text-plain 등으로

//    @RequestMapping(method= RequestMethod.POST, path="/postMethod")
//    @PostMapping(value = "/postMethod", produces = {"application-json"})
    @PostMapping("/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }
}
