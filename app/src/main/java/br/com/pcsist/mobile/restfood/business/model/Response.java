package br.com.pcsist.mobile.restfood.business.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bruno.andrade on 07/02/2017.
 */

public class Response {

    @SerializedName("retorno")
    private boolean successful;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
