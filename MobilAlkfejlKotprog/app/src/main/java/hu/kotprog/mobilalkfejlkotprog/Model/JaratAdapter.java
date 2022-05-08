package hu.kotprog.mobilalkfejlkotprog.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hu.kotprog.mobilalkfejlkotprog.R;
import hu.kotprog.mobilalkfejlkotprog.Shop;

public class JaratAdapter extends RecyclerView.Adapter<JaratAdapter.ViewHolder> {
    private ArrayList<Jarat> lista;
    private Context context;
    private int lastPos=-1;
    public JaratAdapter(Context context,ArrayList<Jarat> lista){
        this.context=context;
        this.lista=lista;
    }
    @NonNull
    @Override
    public JaratAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull JaratAdapter.ViewHolder holder, int position) {
            Jarat current=lista.get(position);
            holder.bindTo(current);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nev;
        private TextView indul;
        private TextView erk;
        private TextView perc;
        private TextView unnep;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nev=itemView.findViewById(R.id.nev_card);
            indul=itemView.findViewById(R.id.indul_card);
            erk=itemView.findViewById(R.id.erkezik_card);
            perc=itemView.findViewById(R.id.perc_card);
            unnep=itemView.findViewById(R.id.unnep_card);
        }
        public void bindTo(Jarat jarat)
        {
            nev.setText(jarat.nev);
            indul.setText(jarat.indulas);
            erk.setText(jarat.erkezes);
            perc.setText(jarat.indulasperc);
            if(jarat.unnep)
            {
                unnep.setText("Igen");
            }
            else{
                unnep.setText("Nem");
            }
            itemView.findViewById(R.id.delete_card).setOnClickListener(view->((Shop)context).delete(jarat));
            itemView.findViewById(R.id.foglalas_card).setOnClickListener(view -> ((Shop)context).foglal(jarat));
        }

    }
}
