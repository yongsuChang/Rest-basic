package com.fastcampus.study.service;

import com.fastcampus.study.model.entity.Partner;
import com.fastcampus.study.model.network.Header;
import com.fastcampus.study.model.network.request.PartnerApiRequest;
import com.fastcampus.study.model.network.response.PartnerApiResponse;
import com.fastcampus.study.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {

        PartnerApiRequest body = request.getData();
        Partner partner = Partner.builder()
                .name(body.getName())
                .status(body.getStatus())
                .address(body.getAddress())
                .callCenter(body.getCallCenter())
                .partnerNumber(body.getPartnerNumber())
                .businessNumber(body.getBusinessNumber())
                .ceoName(body.getCeoName())
                .registeredAt(LocalDateTime.now())
                .category(categoryRepository.getById(body.getCategoryId()))
                .build();

        Partner newPartner = baseRepository.save(partner);
        return Header.OK(response(newPartner));
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {

        PartnerApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(partner -> {
                    partner
                        .setName(body.getName())
                        .setStatus(body.getStatus())
                        .setAddress(body.getAddress())
                        .setCallCenter(body.getCallCenter())
                        .setPartnerNumber(body.getPartnerNumber())
                        .setBusinessNumber(body.getBusinessNumber())
                        .setCeoName(body.getCeoName())
                        .setRegisteredAt(body.getRegisteredAt())
                        .setUnregisteredAt(body.getUnregisteredAt());
                    return partner;
                })
                .map(changedPartner -> baseRepository.save(changedPartner))
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(partner -> {
                    baseRepository.delete(partner);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    protected PartnerApiResponse response(Partner partner){
        return PartnerApiResponse.builder()
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .partnerNumber(partner.getPartnerNumber())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .registeredAt(partner.getRegisteredAt())
                .unregisteredAt(partner.getUnregisteredAt())
                .build();
    }
}
