package com.demo.controller;

import com.demo.entity.Dictionary;
import com.demo.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    public String addDictionary(Dictionary dictionary) {
        dictionaryService.addDictionary(dictionary);
        return "success";
    }

    public String updateDictionary(Dictionary dictionary) {
        dictionaryService.updateDictionary(dictionary);
        return "success";
    }

    public String deleteDictionary(Integer id) {
        dictionaryService.deleteDictionary(id);
        return "success";
    }

    public Page<Dictionary> selectDictionary(Integer pageCount) {
        return dictionaryService.selectDictionary(pageCount);
    }

    public Page<Dictionary> findAllByName(String name, Integer pageCount) {
        return dictionaryService.findAllByName(name, pageCount);
    }

    public List<Dictionary> findAll() {
        return dictionaryService.findAll();
    }
}
