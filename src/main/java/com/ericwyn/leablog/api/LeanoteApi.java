package com.ericwyn.leablog.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ericwyn.leablog.api.entity.LoginMsg;
import com.ericwyn.leablog.api.entity.NoteBookMsg;
import com.ericwyn.leablog.api.entity.NoteMsg;
import com.ericwyn.leablog.tools.LeablogConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ericwyn on 17-12-13.
 */
public class LeanoteApi {
    private String url= LeablogConfig.API_URL;
    private static OkHttpClient client=new OkHttpClient.Builder()
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)
            .build();

    public LoginMsg getToken(String email, String password){
        Request request=new Request.Builder()
                .get()
                .url(url+"/auth/login?email="+email+"&pwd="+password)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()){
                return JSONObject.parseObject(response.body().string(),LoginMsg.class);
            }else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NoteBookMsg> getAllNoteBook(LoginMsg loginMsg){
        ///notebook/getNotebooks
        Request request=new Request.Builder()
                .get()
                .url(url+"/notebook/getNotebooks?token="+loginMsg.getToken())
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()){
                return JSONArray.parseArray(response.body().string(), NoteBookMsg.class);
            }else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<NoteMsg> getAllNote(LoginMsg loginMsg,List<NoteBookMsg> list){
        List<NoteMsg> noteList=new ArrayList<>();
//        System.out.println("一共有"+noteList.size());
        for (NoteBookMsg noteBookMsg : list) {
            Request request = new Request.Builder()
                    .get()
                    .url(url + "/note/getNotes?token=" + loginMsg.getToken() + "&notebookId=" + noteBookMsg.getNotebookId())
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {


                        String resTemp = response.body().string();
                        List<NoteMsg> noteMsgs = JSONArray.parseArray(resTemp, NoteMsg.class);
                        noteList.addAll(noteMsgs);
//                        System.out.println("完成1本笔记");

                    }
                }
            });
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (client.dispatcher().runningCallsCount()!=0){
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return noteList;
    }

    public List<NoteMsg> getNoteContent(LoginMsg loginMsg,List<NoteMsg> list){
        System.out.println("一共有"+list.size()+"篇笔记文章需要获取");
        for (NoteMsg noteMsg : list) {
            Request request = new Request.Builder()
                    .get()
                    .url(url + "/note/getNoteAndContent?token=" + loginMsg.getToken() + "&noteId=" + noteMsg.getNoteId())
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String resTemp = response.body().string();
                        noteMsg.setContent(JSON.parseObject(resTemp).getString("Content"));
//                        System.out.println("完成1篇笔记内容详情"+client.dispatcher().runningCallsCount());
                    }
                }
            });
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (client.dispatcher().runningCallsCount()!=0){
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


}
