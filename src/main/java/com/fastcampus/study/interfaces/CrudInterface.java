package com.fastcampus.study.interfaces;

import com.fastcampus.study.model.network.Header;

public interface CrudInterface {

    Header create();    // TODO: request object 추가

    Header read(Long id);

    Header update();

    Header delete(Long id);

}
