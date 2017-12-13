package com.ericwyn.leablog.api.entity;

import java.util.List;
import java.util.Date;

public class NoteMsg {

    private String NoteId;
    private String NotebookId;
    private String UserId;
    private String Title;
    private String Desc;
    private List<String> Tags;
    private String Abstract;
    private String Content;
    private boolean IsMarkdown;
    private boolean IsBlog;
    private boolean IsTrash;
    private boolean IsDeleted;
    private int Usn;
    private List<Files> Files;
    private String CreatedTime;
    private String UpdatedTime;
    private String PublicTime;
    public void setNoteId(String NoteId) {
        this.NoteId = NoteId;
    }
    public String getNoteId() {
        return NoteId;
    }

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

    public void setTitle(String Title) {
        this.Title = Title;
    }
    public String getTitle() {
        return Title;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }
    public String getDesc() {
        return Desc;
    }

    public void setTags(List<String> Tags) {
        this.Tags = Tags;
    }
    public List<String> getTags() {
        return Tags;
    }

    public void setAbstract(String Abstract) {
        this.Abstract = Abstract;
    }
    public String getAbstract() {
        return Abstract;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
    public String getContent() {
        return Content;
    }

    public void setIsMarkdown(boolean IsMarkdown) {
        this.IsMarkdown = IsMarkdown;
    }
    public boolean getIsMarkdown() {
        return IsMarkdown;
    }

    public void setIsBlog(boolean IsBlog) {
        this.IsBlog = IsBlog;
    }
    public boolean getIsBlog() {
        return IsBlog;
    }

    public void setIsTrash(boolean IsTrash) {
        this.IsTrash = IsTrash;
    }
    public boolean getIsTrash() {
        return IsTrash;
    }

    public void setIsDeleted(boolean IsDeleted) {
        this.IsDeleted = IsDeleted;
    }
    public boolean getIsDeleted() {
        return IsDeleted;
    }

    public void setUsn(int Usn) {
        this.Usn = Usn;
    }
    public int getUsn() {
        return Usn;
    }

    public void setFiles(List<Files> Files) {
        this.Files = Files;
    }
    public List<Files> getFiles() {
        return Files;
    }

    public boolean isMarkdown() {
        return IsMarkdown;
    }

    public void setMarkdown(boolean markdown) {
        IsMarkdown = markdown;
    }

    public boolean isBlog() {
        return IsBlog;
    }

    public void setBlog(boolean blog) {
        IsBlog = blog;
    }

    public boolean isTrash() {
        return IsTrash;
    }

    public void setTrash(boolean trash) {
        IsTrash = trash;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }

    public String getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        CreatedTime = createdTime;
    }

    public String getUpdatedTime() {
        return UpdatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        UpdatedTime = updatedTime;
    }

    public String getPublicTime() {
        return PublicTime;
    }

    public void setPublicTime(String publicTime) {
        PublicTime = publicTime;
    }
}