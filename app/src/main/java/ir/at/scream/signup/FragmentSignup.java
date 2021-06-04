package ir.at.scream.signup;

import android.os.Bundle;
import android.util.Log;
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
import ir.at.scream.databinding.FragmentSignupBinding;
import ir.at.scream.model.ApiService;
import ir.at.scream.model.SharedPrefrance;
import ir.at.scream.model.ViewModelFactory;

public class FragmentSignup extends Fragment {

    private FragmentSignupBinding binding;
    private SignupViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(inflater , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(getActivity() , new ViewModelFactory(ApiService.getApiService() , new SharedPrefrance(getContext()))).get(SignupViewModel.class);

        binding.btnSignupLogin.setOnClickListener(v -> Navigation.findNavController(view).popBackStack());

        binding.etSignupUsername.setError(getString(R.string.usernameHelp));

        binding.btnSignupSignup.setOnClickListener(v -> {
            if (binding.etSignupName.getText().toString().length() < 2)
                binding.lSignupName.setError(getString(R.string.notBlank));
            else if (binding.etSignupUsername.getText().toString().length() < 4)
                binding.lSignupUsername.setError(getString(R.string.invalide));
            else if (binding.etSignupPassword.getText().toString().length() < 4)
                binding.lSignupPassword.setError(getString(R.string.invalide));
            else if (!binding.etSignupPassword.getText().toString().equals(binding.etSignupRepitepassword.getText().toString()))
                binding.lSignupRepitepassword.setError(getString(R.string.passwordInvalide));
            else {
                viewModel.signup(binding.etSignupName.getText().toString() ,
                        binding.etSignupUsername.getText().toString().toLowerCase() ,
                        binding.etSignupPassword.getText().toString());

                binding.btnSignupSignup.setEnabled(false);
                binding.btnSignupLogin.setEnabled(false);
            }
        });

        viewModel.getResponseSignup().observe(getViewLifecycleOwner(), signup -> {
            if (signup == 0)
                Toast.makeText(getContext() , getString(R.string.existUser), Toast.LENGTH_SHORT).show();
            else if (signup == 1)
                Toast.makeText(getContext(), getString(R.string.errorConnection), Toast.LENGTH_SHORT).show();
            else if (signup == 2)
                Navigation.findNavController(view).navigate(R.id.action_fragmentSignup_to_fragmentMain2);

            binding.btnSignupSignup.setEnabled(true);
            binding.btnSignupLogin.setEnabled(true);
        });


        super.onViewCreated(view, savedInstanceState);
    }
}
