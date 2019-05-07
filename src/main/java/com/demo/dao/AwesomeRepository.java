package com.demo.dao;

import com.demo.entity.Awesome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AwesomeRepository extends JpaRepository<Awesome,Integer> {

    List<Awesome> findAllByArticleId(Integer articleId);

    Awesome findAwesomeByUserIdAndArticleId(Integer userId, Integer articleId);

    List<Awesome> findAllByUserId(Integer userId);
}
