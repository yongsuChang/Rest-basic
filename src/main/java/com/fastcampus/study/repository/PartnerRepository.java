package com.fastcampus.study.repository;

import com.fastcampus.study.model.entity.Category;
import com.fastcampus.study.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    public List<Partner> findByCategory(Category category);
}
