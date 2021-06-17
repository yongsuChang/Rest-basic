package com.fastcampus.study.controller.api;

import com.fastcampus.study.interfaces.CrudInterface;
import com.fastcampus.study.model.network.Header;
import com.fastcampus.study.model.network.request.UserApiRequest;
import com.fastcampus.study.model.network.response.UserApiResponse;
import com.fastcampus.study.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("")    // /api/user
    public Header<UserApiResponse> create(
            @RequestBody Header<UserApiRequest> request
            ) {
        log.info("{}", request);
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // /api/user/{id}
    public Header<UserApiResponse> read(
            @PathVariable(name = "id") Long id  // path의 이름 그대로 사용해서 name 생략 가능
    ) {
        log.info("read id : {}", id);
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("")     // /api/user
    public Header<UserApiResponse> update(
            @RequestBody Header<UserApiRequest> request
    ) {
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")  // /api/user/{id}
    public Header delete(@PathVariable Long id) {
        log.info("delete : {}", id);
        return userApiLogicService.delete(id);
    }
}
