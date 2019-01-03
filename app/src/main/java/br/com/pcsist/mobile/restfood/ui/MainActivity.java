package br.com.pcsist.mobile.restfood.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.pcsist.mobile.restfood.R;
import br.com.pcsist.mobile.restfood.business.model.Response;
import br.com.pcsist.mobile.restfood.presenter.ProductPresenter;
import br.com.pcsist.mobile.restfood.presenter.pedido.IniciarPedidoCallback;
import br.com.pcsist.mobile.restfood.ui.utils.ActivityUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IniciarPedidoCallback {


    ProductPresenter productPresenter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productPresenter = new ProductPresenter();
        Button iniciarPedidoBtn = (Button) findViewById(R.id.iniciarPedidoBtn);
        progressDialog = new ProgressDialog(this);
        iniciarPedidoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        productPresenter.iniciarPedido(this);
        progressDialog.setMessage("Iniciando pedido...");
        progressDialog.show();

    }

    @Override
    public void onIniciarPedido(Response response) {
        progressDialog.dismiss();
        if(response.isSuccessful()){
            startActivity(new Intent(this, CardapioActivity.class));
        }else{
            ActivityUtils.showSnackbarMessage(this, "Não foi possível iniciar o pedido");
        }

    }

    @Override
    public void onIniciarPedidoError(String error) {
        progressDialog.dismiss();
        ActivityUtils.showSnackbarMessage(this, error);
    }
}
