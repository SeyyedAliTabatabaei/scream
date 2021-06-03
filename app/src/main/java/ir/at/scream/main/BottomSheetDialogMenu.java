package ir.at.scream.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import ir.at.scream.R;

public class BottomSheetDialogMenu extends BottomSheetDialogFragment {

    private Context context;
    private View vieww;
    private String img;
    private String name;


    public BottomSheetDialogMenu(View vieww, String img, String name) {
        this.vieww = vieww;
        this.img = img;
        this.name = name;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context , R.style.SheetDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.bottomsheet_menu, null , false);
        bottomSheetDialog.setContentView(view);

        int im = Integer.parseInt(img);
        ImageView ivProfile = view.findViewById(R.id.iv_bottomSheetMenu_profile);

        if (im == 0)
            ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar1));
        else if (im == 1)
            ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar2));
        else if (im == 2)
            ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar3));
        else if (im == 3)
            ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar4));
        else if (im == 4)
            ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar5));
        else if (im == 5)
            ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar6));
        else if (im == 6)
            ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar7));
        else if (im == 7)
            ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar8));
        else if (im == 8)
            ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar9));
        else if (im == 9)
            ivProfile.setImageDrawable(context.getDrawable(R.drawable.ic_avatar10));

        TextView nameUser = view.findViewById(R.id.tv_bottomSheetMenu_name);
        nameUser.setText(name);

        LinearLayout editeProfile = view.findViewById(R.id.btn_menu_editeProfile);
        editeProfile.setOnClickListener(v -> {
            Navigation.findNavController(vieww).navigate(R.id.action_fragmentMain_to_fragmentEditeProfile);
            bottomSheetDialog.dismiss();

        });

        LinearLayout rankList = view.findViewById(R.id.btn_menu_rankList);
        rankList.setOnClickListener(v -> {
            Navigation.findNavController(vieww).navigate(R.id.action_fragmentMain_to_fragmentRank);
            bottomSheetDialog.dismiss();

        });


        bottomSheetDialog.create();
        return bottomSheetDialog;
    }

}
