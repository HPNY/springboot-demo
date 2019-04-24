package com.demo.service;

import com.demo.dao.DictionaryRepository;
import com.demo.entity.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryService {

    public static final int pageSize = 10;

    public static final String sortProperties = "id";

    @Autowired
    private DictionaryRepository dictionaryRepository;

    public Dictionary addDictionary(Dictionary dictionary) {
        return dictionaryRepository.save(dictionary);
    }

    public void deleteDictionary(Integer id) {
        dictionaryRepository.deleteById(id);
    }

    public Dictionary updateDictionary(Dictionary dictionary) {
        return dictionaryRepository.save(dictionary);
    }

    public Page<Dictionary> selectDictionary(Integer pageCount) {
        Sort sort = new Sort(Sort.Direction.ASC, sortProperties);
        Pageable pageable = PageRequest.of(pageCount, pageSize, sort);
        return dictionaryRepository.findAll(pageable);
    }

    public Page<Dictionary> findAllByName(String name, Integer pageCount) {
        Sort sort = new Sort(Sort.Direction.ASC, sortProperties);
        Pageable pageable = PageRequest.of(pageCount, pageSize, sort);
        return dictionaryRepository.findAllByNameContaining(pageable, name);
    }

    public List<Dictionary> findAll() {
        return dictionaryRepository.findAll();
    }
}
