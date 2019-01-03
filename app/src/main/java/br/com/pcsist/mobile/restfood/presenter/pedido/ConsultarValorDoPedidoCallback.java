package br.com.pcsist.mobile.restfood.presenter.pedido;

import br.com.pcsist.mobile.restfood.business.model.Valor;

/**
 * Created by bruno.andrade on 07/02/2017.
 */
public interface ConsultarValorDoPedidoCallback {

    void valorConsultado(Valor valor);
    void onConsultarError(String error);
}
