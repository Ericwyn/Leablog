package com.ericwyn.leablog.api.entity;

public class Files {

    private String FileId;
    private String LocalFileId;
    private String Type;
    private String Title;
    private boolean HasBody;
    private boolean IsAttach;
    public void setFileId(String FileId) {
        this.FileId = FileId;
    }
    public String getFileId() {
        return FileId;
    }

    public void setLocalFileId(String LocalFileId) {
        this.LocalFileId = LocalFileId;
    }
    public String getLocalFileId() {
        return LocalFileId;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
    public String getType() {
        return Type;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    public String getTitle() {
        return Title;
    }

    public void setHasBody(boolean HasBody) {
        this.HasBody = HasBody;
    }
    public boolean getHasBody() {
        return HasBody;
    }

    public void setIsAttach(boolean IsAttach) {
        this.IsAttach = IsAttach;
    }
    public boolean getIsAttach() {
        return IsAttach;
    }

}