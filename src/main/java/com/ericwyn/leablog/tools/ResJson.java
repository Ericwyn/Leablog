package com.ericwyn.leablog.tools;

import java.util.List;

/**
 * 普通的
 * 返回Json 的格式化类
 *
 *
 * Created by Ericwyn on 17-11-4.
 */
public class ResJson<T> {
    private String success;
    private String code;

    private String msg;

    private List<T> dataList;

//    public ResJson(String success, String code, String msg) {
//        this.success = success;
//        this.code = code;
//        this.msg = msg;
//    }

    private ResJson(String success, String code, String msg, List<T> dataList) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.dataList = dataList;
    }

    public static ResJson successJson(String msg){
        return new ResJson("true","1",msg,null);
    }


    public static ResJson failJson(String msg){
        return new ResJson("false","-1",msg,null);
    }

    public static ResJson successJson(String msg,List<?> dataList){
        return new ResJson("true","1",msg,dataList);
    }

    public static ResJson failJson(String msg, List<?> dataList){
       return new ResJson("false","-1",msg,dataList);
    }


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
