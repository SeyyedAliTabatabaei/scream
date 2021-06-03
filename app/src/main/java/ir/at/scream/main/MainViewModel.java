package ir.at.scream.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.tyorikan.voicerecordingvisualizer.RecordingSampler;

public class MainViewModel extends ViewModel {

    private MutableLiveData<Integer> getVolume = new MutableLiveData<>();
    private MutableLiveData<Integer> getMaxVolume = new MutableLiveData<>();
    private RecordingSampler recordingSampler = new RecordingSampler();
    private int max = 0;

    public void visulizer(boolean start){

        recordingSampler.setVolumeListener(volume -> {
            if (volume > max){
                max = volume;
                getMaxVolume.postValue(max);
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
