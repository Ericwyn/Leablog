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

    @RequestMapping(path = "/*",method = RequestMethod.GET)
    public String pathIndex(){
        return "index";
    }
    /*
     * 前端优化
     *  1.进入到具体博客页面之后，显示标题
     *  2.纯前端的博客搜索
     *  3.标签
     *  4.归档页面
     *  5,
     *
     *  前端特性
     *  1.JSON 配置文件的支持 (替换ConfigGet 工具)
     *  2.不行啊json没法搞啊不能直接操作前端文件夹里面的JSON
     *  3.微信？微博？
     *
     */

}
