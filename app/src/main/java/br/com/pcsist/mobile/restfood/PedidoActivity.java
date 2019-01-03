package br.com.pcsist.mobile.restfood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import br.com.pcsist.mobile.restfood.business.model.Produto;
import br.com.pcsist.mobile.restfood.business.model.Valor;
import br.com.pcsist.mobile.restfood.presenter.ProductPresenter;
import br.com.pcsist.mobile.restfood.presenter.pedido.ConsultarValorDoPedidoCallback;
import br.com.pcsist.mobile.restfood.presenter.pedido.ListarPedidoCallback;
import br.com.pcsist.mobile.restfood.ui.adapter.PedidoAdapter;
import br.com.pcsist.mobile.restfood.ui.utils.ActivityUtils;

public class PedidoActivity extends AppCompatActivity implements ListarPedidoCallback, ConsultarValorDoPedidoCallback {

    RecyclerView recyclerView;
    PedidoAdapter pedidoAdapter;
    ProductPresenter productPresenter;
    ProgressBar progressBar;

    TextView valorTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        setTitle("Pedido");
        productPresenter = new ProductPresenter();
        valorTotal = (TextView) findViewById(R.id.valorTotalTxt) ;
        productPresenter.consultarValorTotalDoPedido(this);
        inicializarRecyclerView();
        carregarPedido();
    }


    private void carregarPedido() {
        productPresenter.listarPedido(this);
    }

    private void inicializarRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.listaDePedido);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        pedidoAdapter = new PedidoAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(pedidoAdapter);
    }

    @Override
    public void onListagemDePedidoRecebida(List<Produto> produtos) {
        pedidoAdapter.setProdutos(produtos);
        shutProgress();
    }

    @Override
    public void onListarPedidoError(String error) {
        shutProgress();
        ActivityUtils.showSnackbarMessage(this, error);
    }

    private void shutProgress(){
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void valorConsultado(Valor valor) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String preco = decimalFormat.format(valor.getValorTotal());
        valorTotal.setText(preco);
    }

    @Override
    public void onConsultarError(String error) {
        ActivityUtils.showSnackbarMessage(this, error);
    }
}
