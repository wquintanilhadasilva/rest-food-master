package br.com.pcsist.mobile.restfood.presenter.pedido;

import br.com.pcsist.mobile.restfood.business.model.Response;

/**
 * Created by bruno.andrade on 07/02/2017.
 */
public interface IniciarPedidoCallback {

    void onIniciarPedido(Response response);
    void onIniciarPedidoError(String error);
}
