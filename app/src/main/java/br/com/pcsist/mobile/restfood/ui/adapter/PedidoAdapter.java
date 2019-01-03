package br.com.pcsist.mobile.restfood.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.pcsist.mobile.restfood.R;
import br.com.pcsist.mobile.restfood.business.model.Produto;

/**
 * Created by bruno.andrade on 08/02/2017.
 */
public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.MyViewHolder> {

    List<Produto> produtos;
    ProdutosAdapter.CardapioCallback callback;
    DecimalFormat decimalFormat;
    Activity activity;

    public PedidoAdapter(Activity activity) {
        produtos = new ArrayList<>();
        decimalFormat = new DecimalFormat("#.00");
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido, parent, false);
        return new PedidoAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Produto produto = produtos.get(position);
        holder.codigoItem.setText("Item: " + produto.getItem());
        holder.codigoProduto.setText("Código: " + produto.getCodigo());
        holder.preco.setText("Preço: " + String.valueOf(decimalFormat.format(produto.getPreco())));
        holder.descricao.setText("Descrição: " + produto.getDescricao());
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView codigoItem;
        TextView codigoProduto;
        TextView preco;
        TextView descricao;
        ImageView addProduto;

        MyViewHolder(final View itemView) {
            super(itemView);

            codigoItem = (TextView) itemView.findViewById(R.id.codigoItem);
            codigoProduto = (TextView) itemView.findViewById(R.id.codigoProduto);
            preco = (TextView) itemView.findViewById(R.id.preco);
            descricao = (TextView) itemView.findViewById(R.id.descricao);

        }
    }
}
