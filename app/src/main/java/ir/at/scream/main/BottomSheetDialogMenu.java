package ir.at.scream.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import ir.at.scream.R;

public class BottomSheetDialogMenu extends BottomSheetDialogFragment {

    private Context context;
    private View vieww;

    public BottomSheetDialogMenu(View vieww) {
        this.vieww = vieww;
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
