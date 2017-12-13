package com.ericwyn.leablog.tools;

/**
 * Created by Ericwyn on 17-12-13.
 */
public class LeablogConfig {
    private static final ConfigGet conf=new ConfigGet("leablog.cfg",true);

    public static final String API_URL=conf.getValue("http://127.0.0.1:9000","api_url");
    public static final String mail=conf.getValue("admin@leanote.com","mail");
    public static final String password=conf.getValue("password","password");


}
