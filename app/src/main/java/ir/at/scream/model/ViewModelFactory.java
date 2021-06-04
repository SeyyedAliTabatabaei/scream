package ir.at.scream.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import ir.at.scream.editeProfile.EditeProfileViewModel;
import ir.at.scream.login.LoginViewModel;
import ir.at.scream.main.MainViewModel;
import ir.at.scream.rankList.ListRankViewModel;
import ir.at.scream.signup.SignupViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final RetrofitApiService apiService;
    private final SharedPrefrance sharedPrefrance;

    public ViewModelFactory(RetrofitApiService apiService, SharedPrefrance sharedPrefrance) {
        this.apiService = apiService;
        this.sharedPrefrance = sharedPrefrance;
    }

    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class))
            return (T) new MainViewModel(sharedPrefrance , apiService);
        else if (modelClass.isAssignableFrom(LoginViewModel.class))
            return (T) new LoginViewModel(sharedPrefrance , apiService);
        else if (modelClass.isAssignableFrom(SignupViewModel.class))
            return (T) new SignupViewModel(apiService , sharedPrefrance);
        else if (modelClass.isAssignableFrom(EditeProfileViewModel.class))
            return (T) new EditeProfileViewModel(apiService , sharedPrefrance);
        else if (modelClass.isAssignableFrom(ListRankViewModel.class))
            return (T) new ListRankViewModel(apiService , sharedPrefrance);
        else
            return null;
    }
}
