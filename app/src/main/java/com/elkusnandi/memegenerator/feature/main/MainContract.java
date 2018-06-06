package com.elkusnandi.memegenerator.feature.main;

import com.elkusnandi.memegenerator.data.model.LanguageCode;
import com.elkusnandi.memegenerator.data.model.MemePeriod;
import com.elkusnandi.memegenerator.data.model.MemePopularRespond;

public interface MainContract {

    interface View {
        void showProgress();

        void hideProgress();

        void onDataLoaded(MemePopularRespond respond);
    }

    interface Presenter {
        void loadPopularMeme(LanguageCode.iso languageCode, int page, MemePeriod.Time timePeriod);
    }
}
