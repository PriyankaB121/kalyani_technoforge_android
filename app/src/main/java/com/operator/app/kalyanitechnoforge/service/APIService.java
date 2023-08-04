package com.operator.app.kalyanitechnoforge.service;

import com.operator.app.kalyanitechnoforge.Model.DetailsResponse;
import com.operator.app.kalyanitechnoforge.Model.KaizenMasterResponse;
import com.operator.app.kalyanitechnoforge.Model.KaizenResponse;
import com.operator.app.kalyanitechnoforge.Model.ApiResponse;
import com.operator.app.kalyanitechnoforge.Model.RequestData;
import com.operator.app.kalyanitechnoforge.Model.ResponseBody;
import com.operator.app.kalyanitechnoforge.Model.UpdateDelete;
import com.operator.app.kalyanitechnoforge.Model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @FormUrlEncoded
    @POST("operator_login/")
    Call<ResponseBody>login(@Field("mobile") String mobile,
                            @Field("password") String user_password);

    @FormUrlEncoded
    @POST("operator_kaizen_all_list")
    Call<KaizenResponse> getOperatorKaizens(@Field("operator_id") String operatorId);


    @GET("get_kaizen_masters")
    Call<KaizenMasterResponse> get_kaizen_masters();

    @POST("get_profile")
    @FormUrlEncoded
    Call<UserResponse> getProfile(@Field("user_id") String user_id);

    @POST("operator_kaizen_delete")
    @FormUrlEncoded
    Call<ResponseBody> delete(@Field("operator_kaizen_id") String operator_kaizen_id);
    @POST("operator_kaizen_view")
    @FormUrlEncoded
    Call<DetailsResponse> getDetails(@Field("kaizen_id") int kaizen_id);

    @FormUrlEncoded
    @POST("operator_kaizen_create")

        Call<ApiResponse>createKaizen( @Field("activity_pillers") List<String> activity_pillers,
                                       @Field("standard_loss_number1") List<Integer> standard_loss_number1,
                                       @Field("standard_loss_number2") List<Integer> standard_loss_number2,
                                       @Field("standard_loss_number3") List<Integer> standard_loss_number3,
                                       @Field("standard_loss_number4") List<Integer> standard_loss_number4,
                                       @Field("standard_loss_number5") List<Integer> standard_loss_number5,
                                       @Field("standard_loss_number6") List<Integer> standard_loss_number6,
                                       @Field("standard_loss_number7") List<Integer> standard_loss_number7,
                                       @Field("standard_loss_number8") List<Integer> standard_loss_number8,
                                       @Field("standard_loss_number9") List<Integer> standard_loss_number9,
                                       @Field("result_area") List<String> result_area,
                                       @Field("operator_id") String operatorId,
                                @Field("doc_no") String doc_no,
                                @Field("rev_no") String rev_no,
                                @Field("rev_date") String rev_date,
                                @Field("tpm_circle_no") String tpm_circle_no,
                                @Field("tpm_circle_name") String tpm_circle_name,
                                @Field("kaizen_statrt_date") String kaizen_statrt_date,
                                @Field("kaizen_end_date") String kaizen_end_date,
                                @Field("theme") String theme,
                                @Field("rev_details") String rev_details,
                                @Field("shop") String shop,
                                @Field("date_of_implimentation") String date_of_implimentation,
                                @Field("machine") String machine,
                                @Field("idea_by") String idea_by,
                                @Field("present_status") String present_status,
                                @Field("countermeasure") String countermeasure,
                                @Field("result") String result,
                                @Field("benefits") String benefits,
                                @Field("cost_incurred_1") String cost_incurred_1,
                                @Field("cost_incurred_input_1") String cost_incurred_input_1,
                                @Field("cost_incurred_2") String cost_incurred_2,
                                @Field("cost_incurred_input_2") String cost_incurred_input_2,
                                @Field("cost_incurred_3") String cost_incurred_3,
                                @Field("cost_incurred_input_3") String cost_incurred_input_3,
                                @Field("cost_incurred_4") String cost_incurred_4,
                                @Field("cost_incurred_input_4") String cost_incurred_input_4,
                                @Field("cost_incurred_5") String cost_incurred_5,
                                @Field("cost_incurred_input_5") String cost_incurred_input_5,
                                @Field("cost_incurred_total") String cost_incurred_total,
                                @Field("cost_incurred_frequency") String cost_incurred_frequency,
                                @Field("cost_incurred_frequency_input") String cost_incurred_frequency_input,
                                @Field("cost_saved") String cost_saved,
                                @Field("before_image_data") String before_image_data,
                                @Field("after_image_data") String after_image_data,
                                @Field("current_status") String current_status);


    @FormUrlEncoded
    @POST("operator_kaizen_update")
    Call<ApiResponse> updateKaizen(
    @Field("operator_kaizen_id") int operator_kaizen_id,
            @Field("activity_pillers") List<String> activity_pillers,
            @Field("standard_loss_number1") List<Integer> standard_loss_number1,
            @Field("standard_loss_number2") List<Integer> standard_loss_number2,
            @Field("standard_loss_number3") List<Integer> standard_loss_number3,
            @Field("standard_loss_number6") List<Integer> standard_loss_number6,
            @Field("standard_loss_number7") List<Integer> standard_loss_number7,
            @Field("standard_loss_number8") List<Integer> standard_loss_number8,
            @Field("standard_loss_number9") List<Integer> standard_loss_number9,
            @Field("result_area") List<String> result_area,
            @Field("doc_no") String doc_no,
            @Field("rev_no") String rev_no,
            @Field("rev_date") String rev_date,
            @Field("tpm_circle_no") String tpm_circle_no,
            @Field("tpm_circle_name") String tpm_circle_name,
            @Field("kaizen_statrt_date") String kaizen_statrt_date,
            @Field("kaizen_end_date") String kaizen_end_date,
            @Field("theme") String theme,
            @Field("rev_details") String rev_details,
            @Field("shop") String shop,
            @Field("date_of_implimentation") String date_of_implimentation,
            @Field("machine") String machine,
            @Field("idea_by") String idea_by,
            @Field("present_status") String present_status,
            @Field("countermeasure") String countermeasure,
            @Field("result") String result,
            @Field("benefits") String benefits

    );
}