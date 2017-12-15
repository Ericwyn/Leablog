package com.ericwyn.leablog.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Ericwyn on 17-12-14.
 */

@Controller
@EnableAutoConfiguration
public class WebController {
    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }



}
