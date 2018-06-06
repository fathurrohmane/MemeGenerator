package com.elkusnandi.memegenerator.data;

import com.elkusnandi.memegenerator.data.model.MemePopularRespond;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "http://version1.api.memegenerator.net/";

    //    http://version1.api.memegenerator.net//
// Instances_Select_ByPopular?languageCode=en&pageIndex=0&urlName=Insanity-Wolf&days=&apiKey=demo
    @GET("/Instances_Select_ByPopular")
    Single<MemePopularRespond> getPopularMeme(
            @Query("languageCode") String langCode,
            @Query("pageIndex") int page,
            @Query("days") Integer dayPeriod
    );

}
