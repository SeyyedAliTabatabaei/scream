package ir.at.scream.login;

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

import ir.at.scream.R;
import ir.at.scream.databinding.FragmentLoginBinding;
import ir.at.scream.model.ApiService;
import ir.at.scream.model.SharedPrefrance;
import ir.at.scream.model.ViewModelFactory;

public class FragmentLogin extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(getActivity() , new ViewModelFactory(ApiService.getApiService() , new SharedPrefrance(getContext()))).get(LoginViewModel.class);

        if (viewModel.getLogin())
            Navigation.findNavController(view).navigate(R.id.action_fragmentLogin_to_fragmentMain);


        binding.btnLoginSignup.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_fragmentMain_to_fragmentSignup));
        binding.btnLoginLogin.setOnClickListener(v -> {
            if (binding.etLoginUsername.length() < 5)
                binding.etLoginUsername.setError(getString(R.string.invalide));
            else if (binding.etLoginPassword.length() < 5)
                binding.etLoginPassword.setError(getString(R.string.invalide));
            else {
                viewModel.login(binding.etLoginUsername.getText().toString() , binding.etLoginPassword.getText().toString());
                binding.btnLoginLogin.setEnabled(false);
                binding.btnLoginSignup.setEnabled(false);
            }
        });


        viewModel.getResponseErrorConnection().observe(getViewLifecycleOwner(), error -> {
            if (error != null)
                if (error){
                    binding.btnLoginLogin.setEnabled(true);
                    binding.btnLoginSignup.setEnabled(true);
                    Toast.makeText(getContext(), getText(R.string.errorConnection), Toast.LENGTH_SHORT).show();
                }
        });

        viewModel.getResponseLogin().observe(getViewLifecycleOwner(), login -> {
            if (login)
                Navigation.findNavController(view).navigate(R.id.action_fragmentLogin_to_fragmentMain);
            else{
                Toast.makeText(getContext(), getString(R.string.notUser), Toast.LENGTH_SHORT).show();
                binding.btnLoginLogin.setEnabled(true);
                binding.btnLoginSignup.setEnabled(true);
            }
        });


        super.onViewCreated(view, savedInstanceState);
    }
}
