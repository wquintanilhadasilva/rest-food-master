package br.com.pcsist.mobile.restfood.presenter.cardapio;

import java.util.List;

import br.com.pcsist.mobile.restfood.business.model.Produto;

/**
 * Created by bruno.andrade on 07/02/2017.
 */
public interface ExibirCardapioCallback {

    void onListagemDeCardapio(List<Produto> produtos);

    void onListagemDeCardapioError(String error);
}
