package com.botic.coreapps.networks;

import com.botic.coreapps.models.Token;
import com.botic.coreapps.models.ObjectItem;
import com.botic.coreapps.models.User;
import com.botic.coreapps.responses.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by alfianh on 7/4/17.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("oauth/token")
    Call<Token> login(@Field("grant_type") String grantType,
                      @Field("client_id") String clientId,
                      @Field("client_secret") String clientSecret,
                      @Field("username") String username,
                      @Field("password") String password,
                      @Field("scope") String scope);

    @POST("api/user")
    Call<BaseResponse<Object>> register(@Field("name") String name,
                                        @Field("email") String email,
                                        @Field("password") String password);

    @GET("api/user")
    Call<BaseResponse<User>> getProfile();

    @GET("api/tourism")
    Call<BaseResponse<List<ObjectItem>>> getTourism();

    @GET("api/hotel")
    Call<BaseResponse<List<ObjectItem>>> getHotel();

    @GET("api/shopping")
    Call<BaseResponse<List<ObjectItem>>> getShopping();

    @GET("api/culinary")
    Call<BaseResponse<List<ObjectItem>>> getCulinary();

}
