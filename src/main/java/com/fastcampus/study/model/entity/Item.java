package com.fastcampus.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private String content;

    // 1 : N
    // LAZY = 지연 로딩, EAGER = 즉시 로딩

    // LAZY = SELECT * FROM item WHERE id = ?;

    // EAGER =
    // item_id = order_detail.item_id
    // user_id = order_detail.user_id
    // WHERE item.id = ?
    // JOIN item item0_ LEFT OUTER JOIN .......
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;
}
