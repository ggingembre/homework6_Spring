package com.app.controllers;

import com.app.services.ProductService;
import com.app.util.InitDefaultEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.app.entities.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Guillaume Gingembre on 30/10/2017.
 */

@Controller
@RequestMapping(value = "/product")
public class ShowProductController{

    private final ProductService productService;

    @Autowired
    public ShowProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/showAll")
    public ModelAndView showProducts() {
        List<Product> products = productService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("products");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/add")
    public String productForm(Model model) {
        model.addAttribute("productRegistration", new Product());
        return "productRegistration";
    }

    @PostMapping("/add")
    public String BusinessPlanSubmit(@ModelAttribute Product product){

        // saving product
        productService.save(product);

        return "productResult";
    }

    @PostConstruct
    public void initDefaultUsers() {
        InitDefaultEntities.initDefaultProducts(productService);
    }

}
