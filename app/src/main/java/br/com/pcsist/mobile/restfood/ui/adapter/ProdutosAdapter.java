package br.com.pcsist.mobile.restfood.ui.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
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
import br.com.pcsist.mobile.restfood.ui.CardapioActivity;

/**
 * Created by bruno.andrade on 07/02/2017.
 */
public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.MyViewHolder> {

    List<Produto> produtos;
    CardapioCallback callback;
    DecimalFormat decimalFormat;
    Activity activity;

    public ProdutosAdapter(Activity activity, CardapioCallback callback) {
        produtos = new ArrayList<>();
        decimalFormat = new DecimalFormat("#.00");
        this.callback = callback;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Produto produto = produtos.get(position);
        holder.codigo.setText("Código: " + produto.getCodigo());
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

        TextView codigo;
        TextView preco;
        TextView descricao;
        ImageView addProduto;

        MyViewHolder(final View itemView) {
            super(itemView);

            codigo = (TextView) itemView.findViewById(R.id.codigo);
            preco = (TextView) itemView.findViewById(R.id.preco);
            descricao = (TextView) itemView.findViewById(R.id.descricao);
            addProduto = (ImageView) itemView.findViewById(R.id.add_produto);

            addProduto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Produto produto = produtos.get(getAdapterPosition());
                    if(((CardapioActivity) activity).getProdutosDoPedido().contains(produto)){
                        callback.removerProdutoDoPedido(produto);
                        Drawable plusSign = itemView.getContext().getResources().getDrawable(R.drawable.ic_add_circle_outline_black_48dp);
                        addProduto.setImageDrawable(plusSign);

                    }else{
                        callback.adicionarProdutoAoPedido(produto);
                        Drawable checkMark = itemView.getContext().getResources().getDrawable(R.drawable.ic_check_circle_black_24dp);
                        addProduto.setImageDrawable(checkMark);
                        DrawableCompat.setTint(addProduto.getDrawable(), ContextCompat.getColor(itemView.getContext(), android.R.color.holo_green_dark));
                    }


                }
            });
        }
    }

    public interface CardapioCallback{

        void adicionarProdutoAoPedido(Produto produto);
        void removerProdutoDoPedido(Produto produto);
    }
}
