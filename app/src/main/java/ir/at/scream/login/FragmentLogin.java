package ir.at.scream.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import ir.at.scream.R;
import ir.at.scream.databinding.FragmentLoginBinding;

public class FragmentLogin extends Fragment {

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.btnLoginSignup.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_fragmentMain_to_fragmentSignup));
        binding.btnLoginLogin.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_fragmentLogin_to_fragmentMain));

        super.onViewCreated(view, savedInstanceState);
    }
}
