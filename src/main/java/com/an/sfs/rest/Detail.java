package com.an.sfs.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.an.sfs.service.DetailService;

@Controller
@RequestMapping("/rest/detail")
public class Detail {
    @Autowired
    private DetailService detailService;

    private static final Logger logger = LoggerFactory.getLogger(Detail.class);

    @RequestMapping(value = "/allDetails", method = RequestMethod.GET)
    public @ResponseBody
    List<List<Object>> getAllDetails() {
        String code = "002647";
        List<List<Object>> result = detailService.getAllDetails(code);
        logger.debug(result.toString());
        return result;
    }
}