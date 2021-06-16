package com.fastcampus.study.controller.api;

import com.fastcampus.study.interfaces.CrudInterface;
import com.fastcampus.study.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface {

    @Override
    @PostMapping("")    // /api/user
    public Header create(
//            @RequestBody
    ) {
        return null;
    }

    @Override
    @GetMapping("{id}") // /api/user/{id}
    public Header read(
            @PathVariable(name = "id") Long id  // path의 이름 그대로 사용해서 name 생략 가능
    ) {
        return null;
    }

    @Override
    @PutMapping("")     // /api/user
    public Header update() {
        return null;
    }

    @Override
    @DeleteMapping("{id}")  // /api/user/{id}
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
