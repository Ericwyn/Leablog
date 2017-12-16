package com.ericwyn.leablog.tools;

/**
 * Created by Ericwyn on 17-12-13.
 */
public class LeablogConfig {
    private static final ConfigGet conf=new ConfigGet("leablog.cfg",true);

    public static final String API_URL=conf.getValue("http://127.0.0.1:9000","API_URL");
    public static final String MAIL =conf.getValue("admin@leanote.com","MAIL");
    public static final String PASSWORD =conf.getValue("PASSWORD","PASSWORD");
    public static final String TIME_INTERVE=conf.getValue("10","TIME_INTERVE");



}
