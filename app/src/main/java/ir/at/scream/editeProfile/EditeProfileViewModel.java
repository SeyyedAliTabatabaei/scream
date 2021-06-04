package ir.at.scream.editeProfile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.at.scream.model.Response;
import ir.at.scream.model.RetrofitApiService;
import ir.at.scream.model.SharedPrefrance;

public class EditeProfileViewModel extends ViewModel {

    private RetrofitApiService apiService;
    private SharedPrefrance sharedPrefrance;
    private MutableLiveData<Boolean> responseUpdate = new MutableLiveData<>();

    public EditeProfileViewModel(RetrofitApiService apiService, SharedPrefrance sharedPrefrance) {
        this.apiService = apiService;
        this.sharedPrefrance = sharedPrefrance;
    }

    public String name(){
        return sharedPrefrance.getName();
    }

    public int img(){
        return Integer.parseInt(sharedPrefrance.getImage());
    }

    public void updateInfo(String name , String img){
        apiService.updateInfo(sharedPrefrance.getSlt() , name , img)
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<Response>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NotNull Response response) {
                        if (response.getResponse().equals("ok")){
                            sharedPrefrance.updateInfo(name, img);
                            responseUpdate.postValue(true);
                        }
                        else
                            responseUpdate.postValue(false);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        responseUpdate.postValue(false);
                    }
                });
    }

    public MutableLiveData<Boolean> getResponseUpdate() {
        return responseUpdate;
    }
}
