package com.fastcampus.study.controller.api;

import com.fastcampus.study.controller.CrudController;
import com.fastcampus.study.model.entity.OrderGroup;
import com.fastcampus.study.model.network.request.OrderGroupApiRequest;
import com.fastcampus.study.model.network.response.OrderGroupApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

}
