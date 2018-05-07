package com.ericwyn.leablog.controller;


import com.ericwyn.ezerver.SimpleHttpServer;
import com.ericwyn.ezerver.handle.HandleMethod;
import com.ericwyn.ezerver.request.Request;
import com.ericwyn.ezerver.response.Response;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ericwyn on 17-12-14.
 */


public class PageController implements Controller{
    @Override
    public void loadHandMethod(SimpleHttpServer.Builder builder) {
        //根目录访问设置
        builder.addHandleMethod(new HandleMethod("/") {
            @Override
            public void requestDo(Request request, Response response) throws IOException {
                response.sendHtmlFile(new File("webroot/index.html"));
                response.closeStream();
            }
        });
        //正则表达式匹配所有的页面规则
        builder.addHandleMethod(new HandleMethod("\\/[A-Za-z0-9&%]+",true) {
            @Override
            public void requestDo(Request request, Response response) throws IOException {
                response.sendHtmlFile(new File("webroot/index.html"));
                response.closeStream();
            }
        });

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
