package com.an.sfs.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/chart")
public class ChartController {

    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public String linechart(@PathVariable String page, Locale locale, Model model) {

        return "chart/" + page;
    }
}