package com.botic.coreapps.networks;

import com.botic.coreapps.models.Asset;
import com.botic.coreapps.models.CheckInParams;
import com.botic.coreapps.models.IdentityType;
import com.botic.coreapps.models.ObjectItem;
import com.botic.coreapps.models.Review;
import com.botic.coreapps.models.Token;
import com.botic.coreapps.models.User;
import com.botic.coreapps.responses.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @FormUrlEncoded
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

    @GET("api/shopping/{id_category}")
    Call<BaseResponse<List<ObjectItem>>> getShoppingByCategory(@Path("id_category") int idCategory);

    @GET("api/culinary")
    Call<BaseResponse<List<ObjectItem>>> getCulinary();

    @GET("api/culinary/{id_category}")
    Call<BaseResponse<List<ObjectItem>>> getCulinaryByCategory(@Path("id_category") int idCategory);

    @GET("api/transportation")
    Call<BaseResponse<List<ObjectItem>>> getTransportation();

    @GET("api/transportation/{id_category}")
    Call<BaseResponse<List<ObjectItem>>> getTransportationByCategory(@Path("id_category") int idCategory);

    @GET("api/praying/{id_category}")
    Call<BaseResponse<List<ObjectItem>>> getPrayingByCategory(@Path("id_category") int idCategory);

    @GET("api/publicService/{id_category}")
    Call<BaseResponse<List<ObjectItem>>> getPublicServiceByCategory(@Path("id_category") int idCategory);

    @GET("api/finance/{id_category}")
    Call<BaseResponse<List<ObjectItem>>> getFinanceByCategory(@Path("id_category") int idCategory);

    @GET("api/health/{id_category}")
    Call<BaseResponse<List<ObjectItem>>> getHealthByCategory(@Path("id_category") int idCategory);

    @GET("api/leisure/{id_category}")
    Call<BaseResponse<List<ObjectItem>>> getLeisureByCategory(@Path("id_category") int idCategory);

    @FormUrlEncoded
    @POST("api/booking")
    Call<BaseResponse<Object>> booking(@Field("identity_type_id") int identityTypeId,
                                       @Field("identity_number") String identityNumber,
                                       @Field("name") String name,
                                       @Field("phone") String phone,
                                       @Field("date") String date,
                                       @Field("time") String time,
                                       @Field("description") String description,
                                       @Field("id_object") int idObject,
                                       @Field("id_category") int idCategory);

    @GET("api/listasset")
    Call<BaseResponse<List<Asset>>> getListAsset();

    @GET("api/listidentity")
    Call<BaseResponse<List<IdentityType>>> getListIdentity();

    @FormUrlEncoded
    @POST("api/login")
    Call<Token> loginSocialite(@Field("name") String name,
                               @Field("email") String email,
                               @Field("provider") String provider);

    @Headers("Content-Type: application/json")
    @POST("api/checkin")
    Call<BaseResponse<Object>> checin(@Body CheckInParams params);

    @GET("api/review/{id}")
    Call<BaseResponse<List<Review>>> getReview(@Path("id") int id);

    @FormUrlEncoded
    @POST("api/review")
    Call<BaseResponse<Review>> review(@Field("review") String review,
                                      @Field("rating") int rating,
                                      @Field("id_object") int idObject,
                                      @Field("id_menu") int idMenu);

}
