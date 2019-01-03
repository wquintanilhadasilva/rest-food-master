package br.com.pcsist.mobile.restfood.presenter.pedido;

import java.util.List;

import br.com.pcsist.mobile.restfood.business.model.Produto;

/**
 * Created by bruno.andrade on 07/02/2017.
 */
public interface ListarPedidoCallback {

    void onListagemDePedidoRecebida(List<Produto> produtos);
    void onListarPedidoError(String error);
}
