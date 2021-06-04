package ir.at.scream.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SharedPrefrance {

    private static SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SharedPrefrance(Context context) {
        this.context = context;

        if (sharedPreferences == null){
            sharedPreferences = context.getApplicationContext().getSharedPreferences("Scream" , Context.MODE_PRIVATE);
        }

        if (editor == null)
            editor = sharedPreferences.edit();
    }


    public void saveLogin(String token , String name , String username , String score , String img){

        if (editor == null)
            editor = sharedPreferences.edit();

        editor.putBoolean("login" , true);
        editor.putString("salt" , token);
        editor.putString("name" , name);
        editor.putString("username" , username);
        editor.putString("score" , score);
        editor.putString("img" , img);


        editor.apply();
    }

    public void updateInfo(String name , String img){

        if (editor == null)
            editor = sharedPreferences.edit();

        editor.putString("name" , name);
        editor.putString("img" , img);

        editor.apply();
    }

    public void exiteAccount(){

        if (editor == null)
            editor = sharedPreferences.edit();

        editor.putBoolean("login" , false);
        editor.putString("salt" , "");
        editor.putString("name" , "");
        editor.putString("username" , "");
        editor.putString("score" , "");
        editor.putString("img" , "");


        editor.apply();
    }

    public void updateScore(String score){
        editor.putString("score" , score);
        editor.apply();
    }

    public boolean getLogin(){
        return sharedPreferences.getBoolean("login" , false);
    }

    public String getName(){
        return sharedPreferences.getString("name" , "");
    }

    public String getUsername(){
        return sharedPreferences.getString("username" , "");
    }

    public String getScore(){
        return sharedPreferences.getString("score" , "");
    }

    public String getSlt(){
        return sharedPreferences.getString("salt" , "");
    }

    public String getImage(){
        return sharedPreferences.getString("img" , "");
    }

}
