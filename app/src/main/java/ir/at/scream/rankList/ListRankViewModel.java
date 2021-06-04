package ir.at.scream.rankList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.lang.invoke.MutableCallSite;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.at.scream.model.RetrofitApiService;
import ir.at.scream.model.SharedPrefrance;
import ir.at.scream.model.Users;

public class ListRankViewModel extends ViewModel {

    private final RetrofitApiService apiService;
    private final SharedPrefrance sharedPrefrance;
    private MutableLiveData<List<Users>> responseGetList = new MutableLiveData<>();
    private MutableLiveData<Boolean> errorConnection = new MutableLiveData<>();

    public ListRankViewModel(RetrofitApiService apiService, SharedPrefrance sharedPrefrance) {
        this.apiService = apiService;
        this.sharedPrefrance = sharedPrefrance;
    }

    public String getUsername(){
        return sharedPrefrance.getUsername();
    }

    public void getList(){
        apiService.getListRanks()
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<List<Users>>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NotNull List<Users> users) {
                        responseGetList.postValue(users);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        errorConnection.postValue(true);
                    }
                });
    }

    public MutableLiveData<List<Users>> getResponseGetList() {
        return responseGetList;
    }

    public MutableLiveData<Boolean> getErrorConnection() {
        return errorConnection;
    }
}
