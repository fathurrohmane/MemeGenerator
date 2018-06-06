package com.elkusnandi.memegenerator.feature.main;

import com.elkusnandi.memegenerator.data.Repository;
import com.elkusnandi.memegenerator.data.model.LanguageCode;
import com.elkusnandi.memegenerator.data.model.MemePeriod;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

    protected MainContract.View view;
    protected CompositeDisposable disposable;
    protected Repository repository;
    //protected BaseSchedulerProvider schedulerProvider;


    public MainPresenter(MainContract.View view, Repository repository) {
        this.view = view;
        this.repository = repository;
        disposable = new CompositeDisposable();
    }

    @Override
    public void loadPopularMeme(LanguageCode.iso languageCode, int page, MemePeriod.Time timePeriod) {
        view.showProgress();
        disposable.add(
                repository.getPopularMeme(LanguageCode.getIsoCode(languageCode), page, MemePeriod.getMemePreiode(timePeriod))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((memePopularRespond, throwable) -> {
                            if (throwable == null) {
                                view.onDataLoaded(memePopularRespond);
                                view.hideProgress();
                            }
                        })

        );
    }
}
