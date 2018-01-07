package com.ericwyn.leablog.tools;

import com.ericwyn.leablog.api.entity.LoginMsg;

/**
 * Created by Ericwyn on 17-12-13.
 */
public class LeablogConfig {
    private static final ConfigGet conf=new ConfigGet("leablog.cfg",true);

    public static final String API_URL=conf.getValue("API_URL","http://127.0.0.1:9000");
    public static final String MAIL =conf.getValue("MAIL","admin@leanote.com");
    public static final String PASSWORD =conf.getValue("PASSWORD","PASSWORD");
    public static final String TIME_INTERVE=conf.getValue("TIME_INTERVE","10");

    public static final String API_TOKEN=conf.getValue("API_TOKEN","null");
    public static final String USER_ID=conf.getValue("USER_ID","null");
    public static final String USER_NAME=conf.getValue("USER_NAME","null");

    public static void updataLoginMsg(LoginMsg loginMsg){
        conf.updateValue("API_TOKEN",loginMsg.getToken());
        conf.updateValue("USER_ID",loginMsg.getUserId());
        conf.updateValue("USER_NAME",loginMsg.getUsername());
        System.out.println("token 信息已经保存到本地");

    }

}
