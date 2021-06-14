package com.fastcampus.study.repository;

import com.fastcampus.study.StudyApplicationTests;
import com.fastcampus.study.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    // Dependency Injection (DI)
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        User newUser = userRepository.save(user);
        System.out.println("newUser : " + newUser); // @Data에 toString()이 있어서 바로 JSON으로 볼 수 있음
    }

    @Test
    @Transactional
    public void read(){

        // SELECT * FROM User WHERE Account = ?;
        Optional<User> user = userRepository.findByAccount("TestUser03");
        user.ifPresent(selectUser ->{   // 값이 있을 때만
            selectUser.getOrderDetailList().stream().forEach(detail ->{
                System.out.println(detail.getId());
                System.out.println(detail.getItem());
            });
        });
    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{   // 값이 있을 때만
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            // JPA가 해당 id가 이미 존재하는지 확인. 기존에 없으면 create, 이미 있으면 update
            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(3L);

        Assert.assertTrue(user.isPresent());            // true

        user.ifPresent(selectUser ->{   // 값이 있을 때만
            userRepository.delete(selectUser);
        });

        Optional<User> deletedUser = userRepository.findById(3L);

        Assert.assertFalse(deletedUser.isPresent());    // false
    }
}