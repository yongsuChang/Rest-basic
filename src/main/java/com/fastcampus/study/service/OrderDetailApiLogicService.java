package com.fastcampus.study.service;

import com.fastcampus.study.model.entity.OrderDetail;
import com.fastcampus.study.model.network.Header;
import com.fastcampus.study.model.network.request.OrderDetailApiRequest;
import com.fastcampus.study.model.network.response.OrderDetailApiResponse;
import com.fastcampus.study.repository.ItemRepository;
import com.fastcampus.study.repository.OrderGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailApiLogicService extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {

    @Autowired
    private OrderGroupRepository orderGroupRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest body = request.getData();

        OrderDetail orderDetail = OrderDetail.builder()
                .status(body.getStatus())
                .arrivalDate(body.getArrivalDate())
                .quantity(body.getQuantity())
                .totalPrice(body.getTotalPrice())
                .orderGroup(orderGroupRepository.getById(body.getOrderGroupId()))
                .item(itemRepository.getById(body.getItemId()))
                .build();

        OrderDetail newOrderDetail = baseRepository.save(orderDetail);
        return Header.OK(response(newOrderDetail));
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest body = request.getData();
        return baseRepository.findById(body.getId())
            .map(orderDetail -> {
                orderDetail.setStatus(body.getStatus())
                        .setArrivalDate(body.getArrivalDate())
                        .setQuantity(body.getQuantity())
                        .setTotalPrice(body.getTotalPrice())
                        .setOrderGroup(orderGroupRepository.getById(body.getOrderGroupId()))
                        .setItem(itemRepository.getById(body.getItemId()));
                return orderDetail;
            })
            .map(changedOrderDetail -> baseRepository.save(changedOrderDetail))
            .map(this::response)
            .map(Header::OK)
            .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(orderDetail -> {
                    baseRepository.delete(orderDetail);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    protected OrderDetailApiResponse response(OrderDetail orderDetail){
        return OrderDetailApiResponse.builder()
                .status(orderDetail.getStatus())
                .arrivalDate(orderDetail.getArrivalDate())
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .orderGroupId(orderDetail.getOrderGroup().getId())
                .itemId(orderDetail.getItem().getId())
                .build();
    }
}
