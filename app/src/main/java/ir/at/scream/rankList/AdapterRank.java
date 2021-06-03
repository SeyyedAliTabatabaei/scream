package ir.at.scream.rankList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import ir.at.scream.R;

public class AdapterRank extends RecyclerView.Adapter<AdapterRank.ViewHolderRank> {


    @Override
    public ViewHolderRank onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolderRank(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderRank holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public class ViewHolderRank extends RecyclerView.ViewHolder{
        public ViewHolderRank(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
