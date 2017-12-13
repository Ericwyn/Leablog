package com.ericwyn.leablog.api.entity;

import java.util.Date;
/**
 * Created by Ericwyn on 17-12-13.
 */
public class NoteBookMsg {

    private String NotebookId;
    private String UserId;
    private String ParentNotebookId;
    private int Seq;
    private String Title;
    private String UrlTitle;
    private boolean IsBlog;
    private String CreatedTime;
    private String UpdatedTime;
    private int Usn;
    private boolean IsDeleted;

    public void setNotebookId(String NotebookId) {
        this.NotebookId = NotebookId;
    }
    public String getNotebookId() {
        return NotebookId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }
    public String getUserId() {
        return UserId;
    }

    public void setParentNotebookId(String ParentNotebookId) {
        this.ParentNotebookId = ParentNotebookId;
    }
    public String getParentNotebookId() {
        return ParentNotebookId;
    }

    public void setSeq(int Seq) {
        this.Seq = Seq;
    }
    public int getSeq() {
        return Seq;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    public String getTitle() {
        return Title;
    }

    public void setUrlTitle(String UrlTitle) {
        this.UrlTitle = UrlTitle;
    }
    public String getUrlTitle() {
        return UrlTitle;
    }

    public void setIsBlog(boolean IsBlog) {
        this.IsBlog = IsBlog;
    }
    public boolean getIsBlog() {
        return IsBlog;
    }

    public boolean isBlog() {
        return IsBlog;
    }

    public void setBlog(boolean blog) {
        IsBlog = blog;
    }

    public void setUsn(int Usn) {
        this.Usn = Usn;
    }
    public int getUsn() {
        return Usn;
    }

    public void setIsDeleted(boolean IsDeleted) {
        this.IsDeleted = IsDeleted;
    }
    public boolean getIsDeleted() {
        return IsDeleted;
    }
}
