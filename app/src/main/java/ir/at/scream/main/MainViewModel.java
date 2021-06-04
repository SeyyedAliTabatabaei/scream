package ir.at.scream.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tyorikan.voicerecordingvisualizer.RecordingSampler;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.at.scream.model.Response;
import ir.at.scream.model.RetrofitApiService;
import ir.at.scream.model.SharedPrefrance;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<Integer> getVolume = new MutableLiveData<>();
    private final MutableLiveData<Integer> getMaxVolume = new MutableLiveData<>();
    private final MutableLiveData<Boolean> errorConnection = new MutableLiveData<>();
    private RecordingSampler recordingSampler;
    private final SharedPrefrance sharedPrefrance;
    private final RetrofitApiService apiService;
    private Disposable disposable;
    private int max = 0;


    public MainViewModel(SharedPrefrance sharedPrefrance, RetrofitApiService apiService) {
        this.sharedPrefrance = sharedPrefrance;
        this.apiService = apiService;

        max = Integer.parseInt(sharedPrefrance.getScore());
        getMaxVolume.setValue(max);
        updateInfo(String.valueOf(max));
    }

    public String getName(){
        return sharedPrefrance.getName();
    }

    public String getImage(){
        return sharedPrefrance.getImage();
    }

    public void visulizer(boolean start){

        if (recordingSampler == null)
            recordingSampler = new RecordingSampler();

        recordingSampler.setVolumeListener(volume -> {
            if (volume > max){
                max = volume;
                getMaxVolume.postValue(max);
                sharedPrefrance.updateScore(String.valueOf(max));
                updateInfo(String.valueOf(max));
            }
            getVolume.postValue(volume);
        });
        recordingSampler.setSamplingInterval(100);

        if (start)
            recordingSampler.startRecording();
        else
            recordingSampler.stopRecording();
    }

    public void updateInfo(String maxScore){
        apiService.updateScore(sharedPrefrance.getSlt() , maxScore)
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<Response>() {
                    @Override
                    public void onSubscribe( Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess( Response response) {

                    }

                    @Override
                    public void onError( Throwable e) {
                        errorConnection.postValue(true);

                    }
                });
    }

    public MutableLiveData<Integer> getGetVolume() {
        return getVolume;
    }

    public MutableLiveData<Integer> getGetMaxVolume() {
        return getMaxVolume;
    }

    public MutableLiveData<Boolean> getErrorConnection() {
        return errorConnection;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
