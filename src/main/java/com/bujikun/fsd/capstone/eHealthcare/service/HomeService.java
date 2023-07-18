package com.bujikun.fsd.capstone.eHealthcare.service;

import com.bujikun.fsd.capstone.eHealthcare.entity.Category;
import com.bujikun.fsd.capstone.eHealthcare.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class HomeService{
    private final CategoryService categoryService;
    private final ProductService productService;

    public List<HashMap<String,String>> fetchHomeItems(){
        var list = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> o = getMap("Products","Currently in stock",productService.getCount());
        list.add(o);
        o = getMap("Product Categories","Available",categoryService.getCount());
        list.add(o);
        return list;
    }

    private  HashMap<String, String> getMap(String title,String summary,Integer pCount) {
        var o = new HashMap<String,String>();
        o.put("title",title);
        o.put("count",String.valueOf(pCount));
        o.put("summary",summary);
        return o;
    }
}
