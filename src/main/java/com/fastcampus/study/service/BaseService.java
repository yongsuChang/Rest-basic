package com.fastcampus.study.service;

import com.fastcampus.study.interfaces.CrudInterface;
import com.fastcampus.study.model.network.Header;
import com.fastcampus.study.model.network.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public abstract class BaseService<Request, Response, Entity> implements CrudInterface<Request, Response> {

    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository;

    protected abstract Response response(Entity entity);

    public Header<List<Response>> search(Pageable pageable){
        Page<Entity> entities = baseRepository.findAll(pageable);
        List<Response> responseList = entities.stream()
                .map(this::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .currentPage(entities.getNumber())
                .currentElements(entities.getNumberOfElements())
                .build();

        return Header.OK(responseList, pagination);
    }
}
