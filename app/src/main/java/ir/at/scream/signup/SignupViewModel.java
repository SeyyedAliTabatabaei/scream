package ir.at.scream.signup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.at.scream.model.Response;
import ir.at.scream.model.RetrofitApiService;
import ir.at.scream.model.SharedPrefrance;

public class SignupViewModel extends ViewModel {

    private final RetrofitApiService apiService;
    private final SharedPrefrance sharedPrefrance;
    private final MutableLiveData<Integer> responseSignup = new MutableLiveData<>();
    private Disposable disposable;

    public SignupViewModel(RetrofitApiService apiService, SharedPrefrance sharedPrefrance) {
        this.apiService = apiService;
        this.sharedPrefrance = sharedPrefrance;
    }

    public void signup(String name , String username , String password){
        apiService.signup(name, username, password)
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<Response>() {
                    @Override
                    public void onSubscribe( Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess( Response response) {
                        if (response.getResponse().equals("existUser"))
                            responseSignup.postValue(0);
                        else if (response.getResponse().equals("error"))
                            responseSignup.postValue(1);
                        else{
                            sharedPrefrance.saveLogin(response.getResponse() , name , username , "0" , "1");
                            responseSignup.postValue(2);
                        }
                    }

                    @Override
                    public void onError( Throwable e) {
                        responseSignup.postValue(1);
                    }
                });
    }

    public MutableLiveData<Integer> getResponseSignup() {
        return responseSignup;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
