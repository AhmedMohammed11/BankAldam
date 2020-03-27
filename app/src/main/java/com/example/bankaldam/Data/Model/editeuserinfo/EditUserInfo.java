
package com.example.bankaldam.Data.Model.editeuserinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditUserInfo {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private EditUserData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public EditUserData getData() {
        return data;
    }

    public void setData(EditUserData data) {
        this.data = data;
    }

}
