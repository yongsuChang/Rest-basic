package com.fastcampus.study.controller.api;

import com.fastcampus.study.controller.CrudController;
import com.fastcampus.study.model.entity.AdminUser;
import com.fastcampus.study.model.network.request.AdminUserApiRequest;
import com.fastcampus.study.model.network.response.AdminUserApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adminUser")
public class AdminUserApiController extends CrudController<AdminUserApiRequest, AdminUserApiResponse, AdminUser> {

}
