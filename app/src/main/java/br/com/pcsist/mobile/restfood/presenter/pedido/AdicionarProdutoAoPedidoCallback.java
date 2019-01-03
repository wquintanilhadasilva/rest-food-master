package br.com.pcsist.mobile.restfood.presenter.pedido;

import java.util.List;

import br.com.pcsist.mobile.restfood.business.model.Produto;
import br.com.pcsist.mobile.restfood.business.model.Response;

/**
 * Created by bruno.andrade on 07/02/2017.
 */
public interface AdicionarProdutoAoPedidoCallback {

    void onProdutoAdicionado(Response response);
    void onAdicionarProdutoError(String error);
}
