package com.app.controllers;

import com.app.services.ProductService;
import com.app.util.InitDefaultEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.app.entities.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Guillaume Gingembre on 30/10/2017.
 */

@Controller
@RequestMapping(value = "/product")
public class ShowProductController{

    private final ProductService productService;
    private final Logger logger = LoggerFactory.getLogger(Product.class);

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
        logger.debug("loading Product registration form...");
        model.addAttribute("productRegistration", new Product());
        return "productRegistration";
    }

    @PostMapping("/add")
    public String ProductSubmit(@ModelAttribute Product product){
        logger.debug("saving Product: {}", product.getId(), product.getName());
        // saving product
        productService.save(product);

        return "productResult";
    }

    @RequestMapping(value="/{productId}") //, method=RequestMethod.GET)
    public String showProduct(Model model, @PathVariable long productId) {
        logger.debug("showing Product: {}", productId);

        Product product = productService.findOne(productId);
        model.addAttribute("product", product);

        return "productView";
    }

    // delete business plan
    @RequestMapping(value = "/{productId}/delete")
    public String deleteProduct(@PathVariable long productId){ //, final RedirectAttributes redirectAttributes) {

        logger.debug("deleting Product: {}", productId);

        productService.delete(productId);

        return "productDeleted";

    }

    // show update form
    @GetMapping(value = "/{productId}/update")
    public ModelAndView showUpdateProjectForm(@PathVariable("productId") long productId) {

        logger.debug("showUpdateProductForm() : {}", productId);

        Product product = productService.findById(productId);
        return new ModelAndView("productUpdateForm","command", product);
    }

    // save updated business plan
    @PostMapping(value="/updated")
    public String updateProduct(@ModelAttribute ("product") Product product){

        logger.debug("updating product() : {}", product.getId());

        productService.save(product);
        return "productUpdated";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    /*
    @PostConstruct
    public void initDefaultUsers() {
        InitDefaultEntities.initDefaultProducts(productService);
    }
    */
}
