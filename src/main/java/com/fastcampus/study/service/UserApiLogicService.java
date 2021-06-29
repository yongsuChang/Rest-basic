package com.fastcampus.study.service;

import com.fastcampus.study.interfaces.CrudInterface;
import com.fastcampus.study.model.entity.User;
import com.fastcampus.study.model.enumclass.UserStatusEnum;
import com.fastcampus.study.model.network.Header;
import com.fastcampus.study.model.network.request.UserApiRequest;
import com.fastcampus.study.model.network.response.UserApiResponse;
import com.fastcampus.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data 가져오기
        UserApiRequest userApiRequest = request.getData();

        // 2. user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatusEnum.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        // 3. 생성된 데이터 -> return UserApiResponse
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return userRepository.findById(id)      // find User by id
                .map(user -> response(user))    // user -> userApiResponse return
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();

        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user ->{
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;
        })  // 람다식에서 user 내용 바뀜
        .map(changedUser -> userRepository.save(changedUser))   // update -> 새로운 user 반환
        .map(updateUser -> response(updateUser))                // UserApiResponse
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.map(user ->{
            userRepository.delete(user);
            return Header.OK();
        })
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    /* User객체로 UserApiResponse 만들어 반환 */
    private Header<UserApiResponse> response(User user){
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())      // TODO : 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // return Header + Data
        return Header.OK(userApiResponse);
    }
}
