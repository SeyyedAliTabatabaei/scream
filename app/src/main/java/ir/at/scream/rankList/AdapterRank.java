package ir.at.scream.rankList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ConcurrentModificationException;
import java.util.List;

import ir.at.scream.R;
import ir.at.scream.model.Users;

public class AdapterRank extends RecyclerView.Adapter<AdapterRank.ViewHolderRank> {

    private List<Users> usersList;
    private Context context;
    private String username;
    private EventListener eventListener;

    public AdapterRank(Context context , List<Users> usersList , String username , EventListener eventListener) {
        this.usersList = usersList;
        this.context = context;
        this.username = username;
        this.eventListener = eventListener;

        rank();
    }

    @Override
    public ViewHolderRank onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolderRank(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderRank holder, int position) {
        holder.onBind(position , usersList.get(position));
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolderRank extends RecyclerView.ViewHolder{

        ImageView ivProfile;
        TextView tvRank , tvName , tvScore;

        public ViewHolderRank(@NonNull @NotNull View itemView) {
            super(itemView);

            ivProfile = itemView.findViewById(R.id.iv_rank_imgProfile);
            tvName = itemView.findViewById(R.id.tv_rank_name);
            tvRank = itemView.findViewById(R.id.tv_rank_numberRank);
            tvScore = itemView.findViewById(R.id.tv_rank_score);
        }


        public void onBind(int rank , Users users){
            int a = rank + 1;
            tvRank.setText(String.valueOf(a));
            tvName.setText(users.getName());
            tvScore.setText(users.getScore());

            int position = Integer.parseInt(users.getImg());
            if (position == 0)
                ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar1));
            else if (position == 1)
                ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar2));
            else if (position == 2)
                ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar3));
            else if (position == 3)
                ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar4));
            else if (position == 4)
                ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar5));
            else if (position == 5)
                ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar6));
            else if (position == 6)
                ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar7));
            else if (position == 7)
                ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar8));
            else if (position == 8)
                ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar9));
            else if (position == 9)
                ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar10));
        }
    }

    public void rank(){
        for (int i = 0 ; i < usersList.size() ; i++){
            if (usersList.get(i).getUsername().equals(username)){
                eventListener.intRank(i);
                break;
            }
        }
    }

    public interface EventListener{
        void intRank(int rank);
    }
}
