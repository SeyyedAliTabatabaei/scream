package ir.at.scream.rankList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import ir.at.scream.databinding.FragmentRankListBinding;

public class FragmentRank extends Fragment {

    private FragmentRankListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRankListBinding.inflate(inflater , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {


        binding.btnRankBack.setOnClickListener(v -> Navigation.findNavController(view).popBackStack());

        binding.rvRank.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false));
        binding.rvRank.setAdapter(new AdapterRank());

        super.onViewCreated(view, savedInstanceState);
    }
}
