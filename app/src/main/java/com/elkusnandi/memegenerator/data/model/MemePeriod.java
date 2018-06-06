package com.elkusnandi.memegenerator.data.model;

public class MemePeriod {

    public enum Time {
        ALL_TIME,
        TODAY,
        THIS_WEEK,
        THIS_MONTH
    }

    public static Integer getMemePreiode(MemePeriod.Time time) {
        switch (time) {
            case TODAY:
                return 1;
            case THIS_WEEK:
                return 7;
            case THIS_MONTH:
                return 30;
            default:
            case ALL_TIME:
                return null;
        }

    }
}
