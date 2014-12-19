package com.an.sfs.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/trend")
public class TrendController {

    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public String trend(@PathVariable String page, Locale locale, Model model) {
        return "trend/" + page;
    }
}