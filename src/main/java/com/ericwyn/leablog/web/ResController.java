package com.ericwyn.leablog.web;

import com.ericwyn.leablog.api.LeanoteApi;
import com.ericwyn.leablog.api.entity.LoginMsg;
import com.ericwyn.leablog.api.entity.NoteMsg;
import com.ericwyn.leablog.tools.LeablogConfig;
import com.ericwyn.leablog.tools.ResJson;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Ericwyn on 17-12-14.
 */
@RestController
@EnableAutoConfiguration
public class ResController {
    private static List<NoteMsg> noteList=new ArrayList<>();
    private static long lastUpdateTime=0;
    private static long timeInterve= 1000 * 60 * Integer.parseInt(LeablogConfig.TIME_INTERVE);
    private static LeanoteApi api;
    private static LoginMsg apiToken = null;

    @RequestMapping(path = "/allblog",method = RequestMethod.GET)
    public ResJson getAllBlog(){
        return ResJson.successJson("成功",getBlogList());
    }

    //获取博客
    private List<NoteMsg> getBlogList(){
        //判断时间
        long nowTime = System.currentTimeMillis();
        if(api==null){
            api=new LeanoteApi();
        }
        if(apiToken==null){
            apiToken=api.getToken(LeablogConfig.MAIL, LeablogConfig.PASSWORD);
        }
        //超过了时限，重新获取
        if(1.0*(nowTime-lastUpdateTime)>timeInterve){

            for (int i=1;i<3;i++){
                try {
                    //重新获取笔记
                    List<NoteMsg> allNote = api.getAllNote(apiToken, api.getAllNoteBook(apiToken));
                    List<NoteMsg> blogList=new ArrayList<>();
                    for (NoteMsg note:allNote){
                        if(note.isBlog()){
                            blogList.add(note);
                        }
                    }
                    noteList = api.getNoteContent(apiToken, blogList);
                    lastUpdateTime=nowTime;
                    //排序
                    noteList.sort(new Comparator<NoteMsg>() {
                        @Override
                        public int compare(NoteMsg o1, NoteMsg o2) {
                            return o2.getCreatedTime().compareTo(o1.getCreatedTime());
                        }
                    });
                    break;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return noteList;
//        ArrayList<NoteMsg> noteMsgs = new ArrayList<>();
//        noteMsgs.add(noteList.get(0));
//        return noteMsgs;
    }

    //无论如何，先返回缓存，然后在请求
    //滚动位置的记录
    //侧边栏点击返回
    //
}
