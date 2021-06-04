package ir.at.scream.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitApiService {

    @FormUrlEncoded
    @POST("login.php")
    Single<Response> login(@Field("username") String username , @Field("password") String password);

    @FormUrlEncoded
    @POST("signup.php")
    Single<Response> signup(@Field("name") String name ,
                            @Field("username") String username ,
                            @Field("password") String password);

    @FormUrlEncoded
    @POST("updateinfo.php")
    Single<Response> updateInfo(@Field("token") String token ,
                                @Field("name") String name ,
                                @Field("img") String img);

    @FormUrlEncoded
    @POST("updateScore.php")
    Single<Response> updateScore(@Field("token") String token ,
                                @Field("score") String score);

    @POST("getListRanks.php")
    Single<List<Users>> getListRanks();
}
