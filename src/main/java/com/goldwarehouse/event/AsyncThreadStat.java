package com.goldwarehouse.event;

/**
 * Created by David on 2016/12/20.
 */
public class AsyncThreadStat {
    public static boolean showStat;

    public static boolean isShowStat() {
        return showStat;
    }

    public static void setShowStat(boolean showStat) {
        AsyncThreadStat.showStat = showStat;
    }
}
