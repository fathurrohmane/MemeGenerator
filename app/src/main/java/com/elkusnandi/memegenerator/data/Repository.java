package com.elkusnandi.memegenerator.data;

import com.elkusnandi.memegenerator.data.model.LanguageCode;
import com.elkusnandi.memegenerator.data.model.MemePopularRespond;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private static Repository repository;
    private Retrofit retrofit;

    public Repository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

    }

    public static Repository getRepository() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    private Api getApiService() {
        return retrofit.create(Api.class);
    }

    public Single<MemePopularRespond> getPopularMeme(String languageCode, int page, Integer dayPeriod) {
        return getApiService().getPopularMeme(languageCode, page, dayPeriod);
    }
}
