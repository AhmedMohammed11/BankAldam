package com.example.bankaldam.Data.Api;

import com.example.bankaldam.Data.Model.addfavorite.AddFavorite;
import com.example.bankaldam.Data.Model.auth.Auth;
import com.example.bankaldam.Data.Model.changepassword.ChangePassword;
import com.example.bankaldam.Data.Model.contactus.ContactUs;
import com.example.bankaldam.Data.Model.donation.DonationReq;
import com.example.bankaldam.Data.Model.editeuserinfo.EditUserInfo;
import com.example.bankaldam.Data.Model.generalResposnse.GeneralResponse;
import com.example.bankaldam.Data.Model.order.Order;
import com.example.bankaldam.Data.Model.postes.Postes;
import com.example.bankaldam.Data.Model.reset.ResetPassword;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("blood-types")
    Call<GeneralResponse> getBloodTypes();

    @GET("governorates")
    Call<GeneralResponse> getGovernment();

    @GET("cities")
    Call<GeneralResponse> getCities(@Query("governorate_id") int governmentId);

    @POST("signup")
    @FormUrlEncoded
    Call<Auth> onRegister(@Field("name") String name,
                          @Field("email") String email,
                          @Field("birth_date") String birth_date,
                          @Field("city_id") int city_id,
                          @Field("phone") String phone,
                          @Field("donation_last_date") String donation_last_date,
                          @Field("password") String password,
                          @Field("password_confirmation") String password_confirmation,
                          @Field("blood_type_id") int blood_type_id);

    @POST("login")
    @FormUrlEncoded
    Call<Auth> onLogin(@Field("phone") String phone,
                       @Field("password") String password);


    @POST("reset-password")
    @FormUrlEncoded
    Call<ResetPassword> resetPassword(@Field("phone") String phone);

    @POST("new-password")
    @FormUrlEncoded
    Call<ChangePassword> changePassword(@Field("password") String pass,
                                        @Field("password_confirmation") String passwordConfirmation,
                                        @Field("pin_code") String pinCode,
                                        @Field("phone") String phone);

    @GET("posts")
    Call<Postes> getAllPostes(@Query("api_token") String apiToken,
                              @Query("page") int page);

    @GET("my-favourites")
    Call<Postes> getAllFiviort(@Query("api_token") String apiToken,
                               @Query("page") int page);

    @GET("categories")
    Call<GeneralResponse> getCategories();

    @GET("donation-requests")
    Call<DonationReq> getRequestes(@Query("api_token") String apiToken,
                                   @Query("page") int page
    );

    @POST("profile")
    @FormUrlEncoded
    Call<EditUserInfo> editUserInfo(@Field("name") String name,
                                    @Field("email") String email,
                                    @Field("birth_date") String birthDate,
                                    @Field("city_id") int cityId,
                                    @Field("phone") String phone,
                                    @Field("donation_last_date") String donationLastDate,
                                    @Field("password") String password,
                                    @Field("password_confirmation") String confirmPassword,
                                    @Field("blood_type_id") int bloodTypeId,
                                    @Field("api_token") String apitoken
    );


    @POST("contact")
    @FormUrlEncoded
    Call<ContactUs> contact(@Field("api_token") String apitoken,
                            @Field("title") String title,
                            @Field("message") String mesage
    );


    @POST("donation-request/create")
    @FormUrlEncoded
    Call<Order> createDonationRequest(@Field("api_token") String apitoken,
                                      @Field("patient_name") String pationName,
                                      @Field("patient_age") String pationAge,
                                      @Field("blood_type_id") int bloodTypeId,
                                      @Field("bags_num") int bagsNum,
                                      @Field("hospital_name") String hospitalName,
                                      @Field("hospital_address") String hospitalAddress,
                                      @Field("city_id") int cityId,
                                      @Field("phone") String phone,
                                      @Field("notes") String notes,
                                      @Field("latitude") Double latitude,
                                      @Field("longitude") Double longitude
    );

//    @GET("my-favourites")
//    Call<Postes> getAllFavoritePostes(@Query("api_token") String apiToken);


    @POST("post-toggle-favourite")
    @FormUrlEncoded
    Call<AddFavorite> addFavorite(@Field("post_id") int postId,
                                  @Field("api_token") String apiToken
    );

}
