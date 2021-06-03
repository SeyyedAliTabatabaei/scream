package ir.at.scream.model;

import androidx.core.os.BuildCompat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ir.at.scream.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static RetrofitApiService apiService;

    public static RetrofitApiService getApiService(){

        if (apiService == null){

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://starshop99.ir/scream/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            apiService = retrofit.create(RetrofitApiService.class);
        }
        return apiService;
    }
}
