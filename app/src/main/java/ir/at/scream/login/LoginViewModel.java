package ir.at.scream.login;

import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.at.scream.model.Response;
import ir.at.scream.model.RetrofitApiService;
import ir.at.scream.model.SharedPrefrance;

public class LoginViewModel extends ViewModel {

    private SharedPrefrance sharedPrefrance;
    private RetrofitApiService retrofitApiService;
    private MutableLiveData<Boolean> responseLogin = new MutableLiveData<>();
    private MutableLiveData<Boolean> responseErrorConnection = new MutableLiveData<>();

    public LoginViewModel(SharedPrefrance sharedPrefrance, RetrofitApiService retrofitApiService) {
        this.sharedPrefrance = sharedPrefrance;
        this.retrofitApiService = retrofitApiService;
    }

    public boolean getLogin(){
        return sharedPrefrance.getLogin();
    }

    public void login(String username , String password){
        retrofitApiService.login(username, password)
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<Response>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NotNull Response response) {
                        if (response.getResponse().equals("error"))
                            responseLogin.postValue(false);
                        else{
                            sharedPrefrance.saveLogin(response.getResponse() , response.getName() , response.getScore() , response.getImgProf());
                            responseLogin.postValue(true);
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        responseErrorConnection.postValue(true);
                    }
                });
    }


    public MutableLiveData<Boolean> getResponseLogin() {
        return responseLogin;
    }

    public MutableLiveData<Boolean> getResponseErrorConnection() {
        return responseErrorConnection;
    }
}
