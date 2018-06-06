package com.elkusnandi.memegenerator.data.model;

public abstract class LanguageCode {

//    Currently supported languages are English (en), Spanish (es), French (fr), Hebrew (he), Russian (ru), Other (--)
//    http://version1.api.memegenerator.net/#Instances_Select_ByPopular

    public enum iso {
        ENGLISH,
        SPANISH,
        FRENCH,
        HEBREW,
        RUSSIAN,
        OTHER
    }

    public static String getIsoCode(LanguageCode.iso languageCode) {
        switch (languageCode) {
            case ENGLISH:
                return ENGLISH_ISO_CODE;
            case SPANISH:
                return SPANISH_ISO_CODE;
            case FRENCH:
                return FRENCH_ISO_CODE;
            case HEBREW:
                return HEBREW_ISO_CODE;
            case RUSSIAN:
                return RUSSIAN_ISO_CODE;
            case OTHER:
                return OTHER_CODE;
            default:
                return ENGLISH_ISO_CODE;
        }
    }

    public static final String ENGLISH_ISO_CODE = "en";
    public static final String SPANISH_ISO_CODE = "es";
    public static final String FRENCH_ISO_CODE = "fr";
    public static final String HEBREW_ISO_CODE = "he";
    public static final String RUSSIAN_ISO_CODE = "ru";
    public static final String OTHER_CODE = "--";
}
