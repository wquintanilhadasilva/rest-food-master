package br.com.pcsist.mobile.restfood.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.com.pcsist.mobile.restfood.PedidoActivity;
import br.com.pcsist.mobile.restfood.R;
import br.com.pcsist.mobile.restfood.application.App;
import br.com.pcsist.mobile.restfood.business.model.Produto;
import br.com.pcsist.mobile.restfood.business.model.Response;
import br.com.pcsist.mobile.restfood.presenter.ProductPresenter;
import br.com.pcsist.mobile.restfood.presenter.cardapio.ExibirCardapioCallback;
import br.com.pcsist.mobile.restfood.presenter.pedido.AdicionarProdutoAoPedidoCallback;
import br.com.pcsist.mobile.restfood.ui.adapter.ProdutosAdapter;
import br.com.pcsist.mobile.restfood.ui.utils.ActivityUtils;

public class CardapioActivity extends AppCompatActivity implements ExibirCardapioCallback, ProdutosAdapter.CardapioCallback, AdicionarProdutoAoPedidoCallback {

    RecyclerView recyclerView;
    ProdutosAdapter produtosAdapter;
    ProductPresenter productPresenter;
    ProgressBar progressBar;
    ProgressDialog progressDialog;

    List<Produto> produtosDoPedido;

    int produtosAdicionados = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);
        setTitle("Cardápio");
        progressDialog = new ProgressDialog(this);
        productPresenter = new ProductPresenter();
        produtosDoPedido = new ArrayList<>();
        inicializarRecyclerView();
        carregarCardapio();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adicionar_pedido, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        adicionarItensDePedido();
        return super.onOptionsItemSelected(item);

    }

    private void adicionarItensDePedido() {

        progressDialog.setMessage("Adicionando produtos ao pedido...");
        progressDialog.show();
        for (Produto produto: produtosDoPedido) {
            productPresenter.adicionarProdutoAoPedido(produto, this);
        }
    }

    private void listarItensDoPedido() {

        progressDialog.dismiss();
        startActivity(new Intent(this, PedidoActivity.class));
    }

    private void inicializarRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.listaDeProdutos);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        produtosAdapter = new ProdutosAdapter(this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(produtosAdapter);
    }

    private void carregarCardapio() {
        productPresenter.listarCardapio(this);
    }

    @Override
    public void onListagemDeCardapio(List<Produto> produtos) {
        produtosAdapter.setProdutos(produtos);
        shutProgress();
    }

    @Override
    public void onListagemDeCardapioError(String error) {
        ActivityUtils.showSnackbarMessage(this, error);
        shutProgress();
    }

    private void shutProgress(){
        progressBar.setVisibility(View.GONE);
    }

    public List<Produto> getProdutosDoPedido() {
        return produtosDoPedido;
    }

    public void setProdutosDoPedido(List<Produto> produtosDoPedido) {
        this.produtosDoPedido = produtosDoPedido;
    }

    @Override
    public void adicionarProdutoAoPedido(Produto produto) {
        produtosDoPedido.add(produto);
    }

    @Override
    public void removerProdutoDoPedido(Produto produto) {
        produtosDoPedido.remove(produto);
    }

    @Override
    public void onProdutoAdicionado(Response response) {
        if(response.isSuccessful()){
            produtosAdicionados++;

            if(produtosAdicionados == produtosDoPedido.size()){
                listarItensDoPedido();
            }
        }else{
            progressDialog.dismiss();
            ActivityUtils.showSnackbarMessage(this, "Não foi possível adicionar produto ao pedido");
        }
    }

    @Override
    public void onAdicionarProdutoError(String error) {
        progressDialog.dismiss();
        ActivityUtils.showSnackbarMessage(this, error);
    }
}
