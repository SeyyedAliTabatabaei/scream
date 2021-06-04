package ir.at.scream.editeProfile;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ir.at.scream.R;
import ir.at.scream.databinding.FragmentEditeprofileBinding;
import ir.at.scream.model.ApiService;
import ir.at.scream.model.RetrofitApiService;
import ir.at.scream.model.SharedPrefrance;
import ir.at.scream.model.ViewModelFactory;

public class FragmentEditeProfile extends Fragment implements AdapterAvatar.EventOnclick {

    private FragmentEditeprofileBinding binding;
    private EditeProfileViewModel viewModel;
    private  String img = "1";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditeprofileBinding.inflate(inflater , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(getActivity() , new ViewModelFactory(ApiService.getApiService() , new SharedPrefrance(getContext()))).get(EditeProfileViewModel.class);

        binding.rvEditeProfile.setLayoutManager(new GridLayoutManager(getContext() , 2 , RecyclerView.VERTICAL , false));
        binding.rvEditeProfile.setAdapter(new AdapterAvatar(getContext() , this));

        binding.etEditeProfileName.setText(viewModel.name());
        onClickAvatar(viewModel.img() + 1);


        binding.btnEditeProfileSave.setOnClickListener(v -> {

            if (binding.etEditeProfileName.getText().toString().length() < 4)
                binding.lEditeProfileName.setError(getString(R.string.invalide));
            else {
                viewModel.updateInfo(binding.etEditeProfileName.getText().toString() , img);
                binding.btnEditeProfileSave.setEnabled(false);
            }
        });

        viewModel.getResponseUpdate().observe(getViewLifecycleOwner(), response -> {
            if (response != null)
                if (response){
                    viewModel.getResponseUpdate().setValue(null);
                    Navigation.findNavController(view).popBackStack();
                    Toast.makeText(getContext(), getString(R.string.okUpdate), Toast.LENGTH_SHORT).show();
                    binding.btnEditeProfileSave.setEnabled(true);
                }
                else {
                    binding.btnEditeProfileSave.setEnabled(true);
                    Toast.makeText(getContext(), getString(R.string.errorConnection), Toast.LENGTH_SHORT).show();
                }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClickAvatar(int number) {
        img = String.valueOf(number - 1);
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

    @Override
    public void onStop() {
        super.onStop();
        viewModel.getResponseUpdate().setValue(null);
    }
}
