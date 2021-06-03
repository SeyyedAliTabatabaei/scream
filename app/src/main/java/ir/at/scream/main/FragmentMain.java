package ir.at.scream.main;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.tyorikan.voicerecordingvisualizer.RecordingSampler;

import org.jetbrains.annotations.NotNull;

import ir.at.scream.R;
import ir.at.scream.databinding.FragmentMainBinding;
import ir.at.scream.model.ApiService;
import ir.at.scream.model.SharedPrefrance;
import ir.at.scream.model.ViewModelFactory;

public class FragmentMain extends Fragment {

    private FragmentMainBinding binding;
    private MainViewModel viewModel;
    private boolean recorder = true;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater , container , false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(getActivity() , new ViewModelFactory(ApiService.getApiService() , new SharedPrefrance(getContext()))).get(MainViewModel.class);

        binding.tvMainNameUser.setText(" سلام " + viewModel.getName());

        binding.btnMainMic.setOnClickListener(v -> {
            viewModel.visulizer(recorder);
            if (recorder){
                binding.animMainVisulizer.playAnimation();
                unmute();
                recorder = false;
            }
            else{
                binding.animMainVisulizer.pauseAnimation();
                binding.animMainVisulizer.setFrame(1);
                binding.tvMainColumeMoment.setText("0");
                binding.progressBarMainColumeMoment.setProgress(0);
                mute();
                recorder = true;
            }
        });

        viewModel.getGetVolume().observe(getViewLifecycleOwner(), volume -> {
            binding.progressBarMainColumeMoment.setProgress(volume);
            binding.tvMainColumeMoment.setText(String.valueOf(volume));

            if (volume < 33)
                binding.progressBarMainColumeMoment.setProgressDrawable(getContext().getDrawable(R.drawable.prograssbar_circule_low));
            else if (volume < 60 && volume > 33)
                binding.progressBarMainColumeMoment.setProgressDrawable(getContext().getDrawable(R.drawable.prograssbar_circule_medume));
            else if (volume > 60)
                binding.progressBarMainColumeMoment.setProgressDrawable(getContext().getDrawable(R.drawable.prograssbar_circule_high));
        });

        viewModel.getGetMaxVolume().observe(getViewLifecycleOwner(), volume -> {
            binding.progressBarMainMax.setProgress(volume);
            binding.tvMainMax.setText("بیشترین امتیاز \n " + String.valueOf(volume));

            if (volume < 33)
                binding.progressBarMainMax.setProgressDrawable(getContext().getDrawable(R.drawable.prograssbar_circule_low));
            else if (volume < 60 && volume > 33)
                binding.progressBarMainMax.setProgressDrawable(getContext().getDrawable(R.drawable.prograssbar_circule_medume));
            else if (volume > 60)
                binding.progressBarMainMax.setProgressDrawable(getContext().getDrawable(R.drawable.prograssbar_circule_high));
        });


        binding.btnMainMenu.setOnClickListener(v -> {
            BottomSheetDialogMenu bottomSheetDialogMenu = new BottomSheetDialogMenu(view , viewModel.getImage() , viewModel.getName());
            bottomSheetDialogMenu.show(getActivity().getSupportFragmentManager() , null);
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private void mute(){

        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT , 0 , Animation.RELATIVE_TO_PARENT , 0 , Animation.RELATIVE_TO_PARENT , 0 , Animation.RELATIVE_TO_PARENT , 1);
        translateAnimation.setDuration(100);
        translateAnimation.setFillAfter(true);
        binding.ivMainUnmute.startAnimation(translateAnimation);

        TranslateAnimation translateAnimation2 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT , 0 , Animation.RELATIVE_TO_PARENT , 0 , Animation.RELATIVE_TO_PARENT , -1 , Animation.RELATIVE_TO_PARENT , 0);
        translateAnimation2.setDuration(100);
        translateAnimation2.setFillAfter(true);
        binding.ivMainMute.startAnimation(translateAnimation2);
    }

    private void unmute(){

        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT , 0 , Animation.RELATIVE_TO_PARENT , 0 , Animation.RELATIVE_TO_PARENT , 1 , Animation.RELATIVE_TO_PARENT , 0);
        translateAnimation.setDuration(100);
        translateAnimation.setFillAfter(true);
        binding.ivMainUnmute.startAnimation(translateAnimation);

        TranslateAnimation translateAnimation2 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT , 0 , Animation.RELATIVE_TO_PARENT , 0 , Animation.RELATIVE_TO_PARENT , 0 , Animation.RELATIVE_TO_PARENT , -1);
        translateAnimation2.setDuration(100);
        translateAnimation2.setFillAfter(true);
        binding.ivMainMute.startAnimation(translateAnimation2);
    }

}
