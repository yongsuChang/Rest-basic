package com.fastcampus.study.controller.api;

import com.fastcampus.study.controller.CrudController;
import com.fastcampus.study.interfaces.CrudInterface;
import com.fastcampus.study.model.network.Header;
import com.fastcampus.study.model.network.request.CategoryApiRequest;
import com.fastcampus.study.model.network.response.CategoryApiResponse;
import com.fastcampus.study.service.CategoryApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/category")
public class CategoryApiController extends CrudController<CategoryApiRequest, CategoryApiResponse> {

    @Autowired
    private CategoryApiLogicService categoryApiLogicService;

    @PostConstruct
    public void init(){
        this.baseService = categoryApiLogicService;
    }
}
