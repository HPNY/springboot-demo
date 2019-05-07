package com.demo.dao;

import com.demo.entity.Dictionary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryRepository extends JpaRepository<Dictionary,Integer> {

    Page<Dictionary> findAllByNameContaining(Pageable pageable,String name);
}
