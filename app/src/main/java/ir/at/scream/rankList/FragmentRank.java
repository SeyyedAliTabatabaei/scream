package ir.at.scream.rankList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ir.at.scream.R;
import ir.at.scream.databinding.FragmentRankListBinding;
import ir.at.scream.model.ApiService;
import ir.at.scream.model.SharedPrefrance;
import ir.at.scream.model.Users;
import ir.at.scream.model.ViewModelFactory;

public class FragmentRank extends Fragment implements AdapterRank.EventListener {

    private FragmentRankListBinding binding;
    private ListRankViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRankListBinding.inflate(inflater , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(getActivity() , new ViewModelFactory(ApiService.getApiService() , new SharedPrefrance(getContext()))).get(ListRankViewModel.class);

        binding.btnRankBack.setOnClickListener(v -> Navigation.findNavController(view).popBackStack());

        viewModel.getList();

        viewModel.getResponseGetList().observe(getViewLifecycleOwner(), users -> {
            if (users != null){
                binding.rvRank.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false));
                binding.rvRank.setAdapter(new AdapterRank(getContext() , users , viewModel.getUsername() , this));
            }
        });

        viewModel.getErrorConnection().observe(getViewLifecycleOwner(), error -> {
            if (error != null)
                if (error)
                    Toast.makeText(getContext(), getString(R.string.errorConnection), Toast.LENGTH_SHORT).show();
        });


        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.getErrorConnection().setValue(null);
        viewModel.getResponseGetList().setValue(null);
    }

    @Override
    public void intRank(int rank) {
        binding.tvRankUserrank.setText(" رتبه شما : " + String.valueOf(rank + 1) + "   ");
    }
}
