package ir.at.scream.editeProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import ir.at.scream.R;
import ir.at.scream.databinding.FragmentEditeprofileBinding;

public class FragmentEditeProfile extends Fragment implements AdapterAvatar.EventOnclick {

    private FragmentEditeprofileBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditeprofileBinding.inflate(inflater , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        binding.rvEditeProfile.setLayoutManager(new GridLayoutManager(getContext() , 2 , RecyclerView.VERTICAL , false));
        binding.rvEditeProfile.setAdapter(new AdapterAvatar(getContext() , this));

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClickAvatar(int number) {
        if (number == 1)
            binding.ivEditeProfileAvatar.setImageDrawable(getContext().getDrawable(R.drawable.ic_avatar1));
        else if (number == 2)
            binding.ivEditeProfileAvatar.setImageDrawable(getContext().getDrawable(R.drawable.ic_avatar2));
        else if (number == 3)
            binding.ivEditeProfileAvatar.setImageDrawable(getContext().getDrawable(R.drawable.ic_avatar3));
        else if (number == 4)
            binding.ivEditeProfileAvatar.setImageDrawable(getContext().getDrawable(R.drawable.ic_avatar4));
        else if (number == 5)
            binding.ivEditeProfileAvatar.setImageDrawable(getContext().getDrawable(R.drawable.ic_avatar5));
        else if (number == 6)
            binding.ivEditeProfileAvatar.setImageDrawable(getContext().getDrawable(R.drawable.ic_avatar6));
        else if (number == 7)
            binding.ivEditeProfileAvatar.setImageDrawable(getContext().getDrawable(R.drawable.ic_avatar7));
        else if (number == 8)
            binding.ivEditeProfileAvatar.setImageDrawable(getContext().getDrawable(R.drawable.ic_avatar8));
        else if (number == 9)
            binding.ivEditeProfileAvatar.setImageDrawable(getContext().getDrawable(R.drawable.ic_avatar9));
        else if (number == 10)
            binding.ivEditeProfileAvatar.setImageDrawable(getContext().getDrawable(R.drawable.ic_avatar10));
    }
}
