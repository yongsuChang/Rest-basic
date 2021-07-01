package com.fastcampus.study.sampledata;

import com.fastcampus.study.StudyApplicationTests;
import com.fastcampus.study.model.entity.Item;
import com.fastcampus.study.model.entity.OrderDetail;
import com.fastcampus.study.model.entity.OrderGroup;
import com.fastcampus.study.model.entity.User;
import com.fastcampus.study.model.enumclass.OrderTypeEnum;
import com.fastcampus.study.repository.ItemRepository;
import com.fastcampus.study.repository.OrderDetailRepository;
import com.fastcampus.study.repository.OrderGroupRepository;
import com.fastcampus.study.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class OrderDetailSample extends StudyApplicationTests {

    private Random random = new Random();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void createOrder(){

        List<User> userList = userRepository.findAll();

        for(int j = 0; j < 1; j++){
            User user = userList.get(j);
            item(user);

        }


        userList.forEach(user -> {

            int orderCount = random.nextInt(10) + 1;
            for (int i = 0; i < orderCount; i++) {
                item(user);
            }
        });


    }


    private void item(User user){
        double totalAmount = 0;

        List<Item> items = new ArrayList<>();
        List<OrderDetail> orderHistoryDetails = new ArrayList<>();


        int itemCount = random.nextInt(10)+1;
        for(int i = 0 ; i < itemCount; i ++){
            // db에 아이템 갯수가 총 500개 ( * 자신의 샘플에 맞추세요 )
            int itemNumber = random.nextInt(405)+1;

            Item item = itemRepository.findById((long)itemNumber).get();
            totalAmount += item.getPrice().doubleValue();
            items.add(item);
        }


        int s = random.nextInt(3)+1;
        String status = "ORDERING";
        String paymentType = "BANK_TRANSFER";
        switch (s){
            case 1 :
                status = "ORDERING";
                paymentType = "BANK_TRANSFER";
                break;

            case 2 :
                status = "COMPLETE";
                paymentType = "CARD";
                break;

            case 3 :
                status = "CONFIRM";
                paymentType = "CHECK_CARD";
                break;
        }

        int t = random.nextInt(2)+1;
        OrderTypeEnum type = t==1? OrderTypeEnum.ALL:OrderTypeEnum.EACH;


        OrderGroup orderGroup = OrderGroup.builder()
                .user(user)
                .status(status)
                .orderType(type)
                .revAddress("경기도 분당구 판교역로")
                .revName(user.getEmail())
                .paymentType(paymentType)
                .totalPrice(new BigDecimal(totalAmount))
                .orderAt(getRandomDate())
                .totalQuantity(itemCount)
                .arrivalDate(getRandomDate().plusDays(3))
                .orderDetailList(orderHistoryDetails)
                .build();

        orderGroupRepository.save(orderGroup);



        for(Item item : items){

            String orderDetailStatus = "ORDERING";
            switch (random.nextInt(3)+1){
                case 1 :
                    orderDetailStatus = "ORDERING";
                    break;

                case 2 :
                    orderDetailStatus = "COMPLETE";
                    break;

                case 3 :
                    orderDetailStatus = "CONFIRM";
                    break;
            }


            OrderDetail orderDetail = OrderDetail.builder()
                    .orderGroup(orderGroup)
                    .item(item)
                    .quantity(itemCount)
                    .totalPrice(new BigDecimal(totalAmount))
                    .arrivalDate(type.equals(OrderTypeEnum.ALL) ? orderGroup.getArrivalDate() : getRandomDate())
                    .status(type.equals(OrderTypeEnum.ALL) ? status :orderDetailStatus)
                    .build();
            orderDetailRepository.save(orderDetail);
            orderHistoryDetails.add(orderDetail);
        }


    }


    private LocalDateTime getRandomDate(){
        return LocalDateTime.of(2019,getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber());
    }

    private int getRandomNumber(){
        return random.nextInt(11)+1;
    }
}
