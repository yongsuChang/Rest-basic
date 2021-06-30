package com.fastcampus.study.controller;

import com.fastcampus.study.interfaces.CrudInterface;
import com.fastcampus.study.model.network.Header;
import com.fastcampus.study.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
public abstract class CrudController<Request, Response, Entity> implements CrudInterface<Request, Response> {

    @Autowired(required = false)
    protected BaseService<Request, Response, Entity> baseService;

    @Override
    @PostMapping("")
    public Header<Response> create(@RequestBody Header<Request> request) {
        return baseService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<Response> read(@PathVariable Long id) {
        return baseService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<Response> update(@RequestBody Header<Request> request) {
        return baseService.update(request);
    }

    @Override
    @DeleteMapping
    public Header delete(@PathVariable Long id) {
        return baseService.delete(id);
    }
}
