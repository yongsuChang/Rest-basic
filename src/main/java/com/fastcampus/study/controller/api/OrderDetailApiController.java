package com.fastcampus.study.controller.api;

import com.fastcampus.study.controller.CrudController;
import com.fastcampus.study.model.entity.OrderDetail;
import com.fastcampus.study.model.network.request.OrderDetailApiRequest;
import com.fastcampus.study.model.network.response.OrderDetailApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailApiController extends CrudController<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {

}
