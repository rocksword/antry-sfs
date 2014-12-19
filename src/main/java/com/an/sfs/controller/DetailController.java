package com.an.sfs.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/detail")
// Path name among URL: http://localhost:8080/detail/detail
public class DetailController {

    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public String detail(@PathVariable String page, Locale locale, Model model) {
        // Relative path under WEB-INF/views where store jsp file
        return "detail/" + page;
    }
}
