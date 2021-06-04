package ir.at.scream.login;

import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.at.scream.model.Response;
import ir.at.scream.model.RetrofitApiService;
import ir.at.scream.model.SharedPrefrance;

public class LoginViewModel extends ViewModel {

    private final SharedPrefrance sharedPrefrance;
    private final RetrofitApiService retrofitApiService;
    private final MutableLiveData<Boolean> responseLogin = new MutableLiveData<>();
    private final MutableLiveData<Boolean> responseErrorConnection = new MutableLiveData<>();
    private Disposable disposable;


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
                    public void onSubscribe( Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess( Response response) {
                        if (response.getResponse().equals("error"))
                            responseLogin.postValue(false);
                        else{
                            sharedPrefrance.saveLogin(response.getResponse() , response.getName() , response.getUsername() , response.getScore() , response.getImgProf());
                            responseLogin.postValue(true);
                        }
                    }

                    @Override
                    public void onError( Throwable e) {
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

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
