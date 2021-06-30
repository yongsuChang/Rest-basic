package com.fastcampus.study.controller;

import com.fastcampus.study.interfaces.CrudInterface;
import com.fastcampus.study.model.network.Header;
import org.springframework.web.bind.annotation.*;

public abstract class CrudController<Request, Response> implements CrudInterface<Request, Response> {

    protected CrudInterface<Request, Response> baseService;
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
