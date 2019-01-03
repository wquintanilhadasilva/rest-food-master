package br.com.pcsist.mobile.restfood.business.model;

import java.text.Format;
import java.util.Formatter;

/**
 * Created by bruno.andrade on 07/02/2017.
 */

public class Produto {

    private String codigo;
    private double preco;
    private String descricao;
    private String item;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPreco() {

        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
