package ir.at.scream.model;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitApiService {

    @FormUrlEncoded
    @POST("login.php")
    Single<Response> login(@Field("username") String username , @Field("password") String password);
}
