package com.fastcampus.study.controller.api;

import com.fastcampus.study.controller.CrudController;
import com.fastcampus.study.interfaces.CrudInterface;
import com.fastcampus.study.model.network.Header;
import com.fastcampus.study.model.network.request.OrderDetailApiRequest;
import com.fastcampus.study.model.network.response.OrderDetailApiResponse;
import com.fastcampus.study.service.OrderDetailApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailApiController extends CrudController<OrderDetailApiRequest, OrderDetailApiResponse> {

    @Autowired
    private OrderDetailApiLogicService orderDetailApiLogicService;

    @PostConstruct
    public void init(){
        this.baseService = orderDetailApiLogicService;
    }
}
