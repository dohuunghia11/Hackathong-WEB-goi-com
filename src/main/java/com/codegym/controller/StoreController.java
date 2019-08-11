package com.codegym.controller;

import com.codegym.model.Store;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/store")
public class StoreController {
    @ModelAttribute("store")
    public Store setupStore(){
        return new Store();
    }

    @GetMapping("/show")
    public ModelAndView showStore (@SessionAttribute("store") Store store){
        ModelAndView modelAndView = new ModelAndView("/store/show");
        modelAndView.addObject("store",store);
        return modelAndView;
    }
    @PostMapping("/show")
    public ModelAndView paypill(@SessionAttribute("store") Store store){

        ModelAndView modelAndView = new ModelAndView("/store/show");
        modelAndView.addObject("store",store);
        modelAndView.addObject("message","thanh toan thanh cong");
        return modelAndView;
    }
}
