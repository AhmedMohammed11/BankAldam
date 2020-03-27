
package com.example.bankaldam.Data.Model.editeuserinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditUserData {

    @SerializedName("client")
    @Expose
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
