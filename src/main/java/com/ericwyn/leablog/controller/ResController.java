package com.ericwyn.leablog.controller;

import com.alibaba.fastjson.JSON;
import com.ericwyn.ezerver.SimpleHttpServer;
import com.ericwyn.ezerver.handle.HandleMethod;
import com.ericwyn.ezerver.request.Request;
import com.ericwyn.ezerver.response.Response;
import com.ericwyn.leablog.api.LeanoteApi;
import com.ericwyn.leablog.api.entity.LoginMsg;
import com.ericwyn.leablog.api.entity.NoteMsg;
import com.ericwyn.leablog.tools.LeablogConfig;
import com.ericwyn.leablog.tools.ResJson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Ericwyn on 17-12-14.
 */

public class ResController implements Controller{
    private static List<NoteMsg> noteList=new ArrayList<>();
    private static long lastUpdateTime=0;
    private static long timeInterve= 1000 * 60 * Integer.parseInt(LeablogConfig.TIME_INTERVE);
    private static LeanoteApi api;
    private static LoginMsg apiToken = null;

    private boolean init=false;

    //匹配 /allblog 的请求，优先级高于 PageController 里面的正则表达式匹配
    @Override
    public void loadHandMethod(SimpleHttpServer.Builder builder) {
        builder.addHandleMethod(new HandleMethod("/allblog") {
            @Override
            public void requestDo(Request request, Response response) throws IOException {
                response.sendJsonData(JSON.toJSONString(getAllBlog()));
                response.closeStream();
            }
        });
    }

    public ResJson getAllBlog(){
        return ResJson.successJson("成功",getBlogList());
    }

    //获取博客
    private List<NoteMsg> getBlogList(){
        if(api==null){
            api=new LeanoteApi();
        }
        if(apiToken==null){
            if(LeablogConfig.API_TOKEN.equals("null")
                    || LeablogConfig.USER_ID.equals("null")
                    || LeablogConfig.USER_NAME.equals("null")){
                apiToken=api.getToken(LeablogConfig.MAIL, LeablogConfig.PASSWORD);
                LeablogConfig.updataLoginMsg(apiToken);
            }else {
                apiToken=new LoginMsg();
                apiToken.setOk(true);
                apiToken.setEmail(LeablogConfig.MAIL);
                apiToken.setToken(LeablogConfig.API_TOKEN);
                apiToken.setUserId(LeablogConfig.USER_ID);
                apiToken.setUsername(LeablogConfig.USER_NAME);
                System.out.println("成功从本地取得token");
            }
        }
        //初始化
        if(!init){
            init=true;
            Thread getBlogThread =new Thread(new GetBlogThreadRun());
            getBlogThread.start();
            while (getBlogThread.isAlive()){
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("初始化完毕");
        }else {
            //判断时间
            long nowTime = System.currentTimeMillis();
            //超过了时限，重新获取，但是只是开启了一个获取线程，于是前台需要先刷新一下才会看到新的列表
            //避免了卡顿
            if(1.0*(nowTime-lastUpdateTime)>timeInterve){
                new Thread(new GetBlogThreadRun()).start();
            }
        }
        return noteList;
    }

    class GetBlogThreadRun implements Runnable{
        @Override
        public void run() {
            System.out.println("查询线程开启");
            long oldTime=lastUpdateTime;
            List<NoteMsg> oldList=new ArrayList<>();
            oldList.addAll(noteList);
            lastUpdateTime=System.currentTimeMillis();
//            for (int i=1;i<3;i++){
                try {
                    //重新获取笔记
                    List<NoteMsg> allNote = api.getAllNote(apiToken, api.getAllNoteBook(apiToken));
                    List<NoteMsg> blogList=new ArrayList<>();
                    for (NoteMsg note:allNote){
                        if(note.isBlog()){
                            //去除返回数据当中的notebookid 数据和userid数据
                            note.setNotebookId("");
                            note.setUserId("");
                            blogList.add(note);
                        }
                    }
                    noteList = api.getNoteContent(apiToken, blogList);
                    System.out.println("成功取回"+noteList.size()+"篇文章");
                    //排序
                    noteList.sort(new Comparator<NoteMsg>() {
                        @Override
                        public int compare(NoteMsg o1, NoteMsg o2) {
                            return o2.getCreatedTime().compareTo(o1.getCreatedTime());
                        }
                    });
    //                    break;
                }catch (Exception e){
                    e.printStackTrace();
                    //回滚
                    lastUpdateTime=oldTime;
                    noteList=oldList;
                }

        }
    }
}
