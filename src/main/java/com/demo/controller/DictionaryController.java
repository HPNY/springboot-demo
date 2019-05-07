package com.demo.controller;

import com.demo.entity.Dictionary;
import com.demo.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 新增数据字典
     *
     * @param dictionary
     * @return
     */
    @RequestMapping("/dictionary/add")
    public String addDictionary(Dictionary dictionary) {
        dictionaryService.addDictionary(dictionary);
        return "success";
    }

    @RequestMapping("/dictionary/update")
    public String updateDictionary(Dictionary dictionary) {
        dictionaryService.updateDictionary(dictionary);
        return "success";
    }

    @RequestMapping("/dictionary/delete")
    public String deleteDictionary(Integer id) {
        dictionaryService.deleteDictionary(id);
        return "success";
    }

    @RequestMapping("/dictionary/list")
    public Page<Dictionary> findAllByName(String name, Integer pageCount) {
        if (name != null) {
            return dictionaryService.findAllByName(name, pageCount);
        }
        return dictionaryService.selectDictionary(pageCount);
    }

    @RequestMapping("/dictionary/findall")
    public List<Dictionary> findAll() {
        List<Dictionary> list = dictionaryService.findAll();
        System.out.println(list);
        return list;
    }
}
