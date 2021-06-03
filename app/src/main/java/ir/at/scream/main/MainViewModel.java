package ir.at.scream.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tyorikan.voicerecordingvisualizer.RecordingSampler;

import ir.at.scream.model.RetrofitApiService;
import ir.at.scream.model.SharedPrefrance;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<Integer> getVolume = new MutableLiveData<>();
    private final MutableLiveData<Integer> getMaxVolume = new MutableLiveData<>();
    private final RecordingSampler recordingSampler = new RecordingSampler();
    private final SharedPrefrance sharedPrefrance;
    private final RetrofitApiService apiService;
    private int max = 0;


    public MainViewModel(SharedPrefrance sharedPrefrance, RetrofitApiService apiService) {
        this.sharedPrefrance = sharedPrefrance;
        this.apiService = apiService;

        max = Integer.parseInt(sharedPrefrance.getScore());
        getMaxVolume.setValue(max);
    }

    public String getName(){
        return sharedPrefrance.getName();
    }

    public String getImage(){
        return sharedPrefrance.getImage();
    }

    public void visulizer(boolean start){
        recordingSampler.setVolumeListener(volume -> {
            if (volume > max){
                max = volume;
                getMaxVolume.postValue(max);
                sharedPrefrance.updateScore(String.valueOf(max));
            }
            getVolume.postValue(volume);
        });
        recordingSampler.setSamplingInterval(100);

        if (start)
            recordingSampler.startRecording();
        else
            recordingSampler.stopRecording();
    }

    public MutableLiveData<Integer> getGetVolume() {
        return getVolume;
    }

    public MutableLiveData<Integer> getGetMaxVolume() {
        return getMaxVolume;
    }
}
