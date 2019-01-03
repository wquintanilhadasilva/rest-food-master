package br.com.pcsist.mobile.restfood.presenter;

import br.com.pcsist.mobile.restfood.application.App;
import retrofit2.Retrofit;

/**
 * Created by bruno.andrade on 23/01/2017.
 */

public abstract class Presenter {

    Retrofit retrofit;

    Presenter() {
        this.retrofit = App.getApiManager().getInstance();
    }
}
