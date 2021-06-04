package ir.at.scream.editeProfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import ir.at.scream.R;

public class AdapterAvatar extends RecyclerView.Adapter<AdapterAvatar.ViewHolderAvatar> {

    Context context;
    private EventOnclick eventOnclick;

    public AdapterAvatar(Context context , EventOnclick eventOnclick) {
        this.context = context;
        this.eventOnclick = eventOnclick;
    }

    @NonNull
    @Override
    public ViewHolderAvatar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderAvatar(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_avatar , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAvatar holder, int position) {

        if (position == 0)
            holder.ivAvatar.setImageDrawable(context.getDrawable(R.drawable.ic_avatar1));
        else if (position == 1)
            holder.ivAvatar.setImageDrawable(context.getDrawable(R.drawable.ic_avatar2));
        else if (position == 2)
            holder.ivAvatar.setImageDrawable(context.getDrawable(R.drawable.ic_avatar3));
        else if (position == 3)
            holder.ivAvatar.setImageDrawable(context.getDrawable(R.drawable.ic_avatar4));
        else if (position == 4)
            holder.ivAvatar.setImageDrawable(context.getDrawable(R.drawable.ic_avatar5));
        else if (position == 5)
            holder.ivAvatar.setImageDrawable(context.getDrawable(R.drawable.ic_avatar6));
        else if (position == 6)
            holder.ivAvatar.setImageDrawable(context.getDrawable(R.drawable.ic_avatar7));
        else if (position == 7)
            holder.ivAvatar.setImageDrawable(context.getDrawable(R.drawable.ic_avatar8));
        else if (position == 8)
            holder.ivAvatar.setImageDrawable(context.getDrawable(R.drawable.ic_avatar9));
        else if (position == 9)
            holder.ivAvatar.setImageDrawable(context.getDrawable(R.drawable.ic_avatar10));


        holder.ivAvatar.setOnClickListener(v -> {
            if (position == 0)
                eventOnclick.onClickAvatar(1);
            else if (position == 1)
                eventOnclick.onClickAvatar(2);
            else if (position == 2)
                eventOnclick.onClickAvatar(3);
            else if (position == 3)
                eventOnclick.onClickAvatar(4);
            else if (position == 4)
                eventOnclick.onClickAvatar(5);
            else if (position == 5)
                eventOnclick.onClickAvatar(6);
            else if (position == 6)
                eventOnclick.onClickAvatar(7);
            else if (position == 7)
                eventOnclick.onClickAvatar(8);
            else if (position == 8)
                eventOnclick.onClickAvatar(9);
            else if (position == 9)
                eventOnclick.onClickAvatar(10);
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolderAvatar extends RecyclerView.ViewHolder{

        ImageView ivAvatar;

        public ViewHolderAvatar(@NonNull View itemView) {
            super(itemView);

            ivAvatar = itemView.findViewById(R.id.iv_avatar);
        }
    }

    public interface EventOnclick{
        void onClickAvatar(int number);
    }
}
