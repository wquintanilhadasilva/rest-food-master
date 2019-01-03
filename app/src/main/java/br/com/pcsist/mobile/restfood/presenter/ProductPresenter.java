package br.com.pcsist.mobile.restfood.presenter;

import java.util.List;

import br.com.pcsist.mobile.restfood.business.model.Produto;
import br.com.pcsist.mobile.restfood.business.model.Response;
import br.com.pcsist.mobile.restfood.business.model.Valor;
import br.com.pcsist.mobile.restfood.business.services.ProdutoService;
import br.com.pcsist.mobile.restfood.presenter.cardapio.ExibirCardapioCallback;
import br.com.pcsist.mobile.restfood.presenter.pedido.AdicionarProdutoAoPedidoCallback;
import br.com.pcsist.mobile.restfood.presenter.pedido.ConsultarValorDoPedidoCallback;
import br.com.pcsist.mobile.restfood.presenter.pedido.IniciarPedidoCallback;
import br.com.pcsist.mobile.restfood.presenter.pedido.ListarPedidoCallback;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by bruno.andrade on 07/02/2017.
 */

public class ProductPresenter extends Presenter {

    public void iniciarPedido(final IniciarPedidoCallback callback){

        retrofit.create(ProdutoService.class).iniciarPedido()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<Response>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onIniciarPedidoError(e.getMessage());
                    }

                    @Override
                    public void onNext(Response response) {
                        callback.onIniciarPedido(response);
                    }
                });
    }

    public void listarCardapio(final ExibirCardapioCallback callback){

        retrofit.create(ProdutoService.class).listarCardapio()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Produto>>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onListagemDeCardapioError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Produto> produtos) {
                        callback.onListagemDeCardapio(produtos);
                    }
                });
    }

    public void listarPedido(final ListarPedidoCallback callback){

        retrofit.create(ProdutoService.class).listarPedido()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Produto>>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onListarPedidoError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Produto> produtos) {
                        callback.onListagemDePedidoRecebida(produtos);
                    }
                });
    }

    public void adicionarProdutoAoPedido(Produto produto, final AdicionarProdutoAoPedidoCallback callback){

        retrofit.create(ProdutoService.class).adicionarProdutoAoPedido(produto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<Response>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onAdicionarProdutoError(e.getMessage());
                    }

                    @Override
                    public void onNext(Response response) {
                        callback.onProdutoAdicionado(response);
                    }
                });
    }

    public void consultarValorTotalDoPedido(final ConsultarValorDoPedidoCallback callback){

        retrofit.create(ProdutoService.class).consultarValorTotal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<Valor>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onConsultarError(e.getMessage());
                    }

                    @Override
                    public void onNext(Valor valor) {
                        callback.valorConsultado(valor);
                    }
                });
    }

}
