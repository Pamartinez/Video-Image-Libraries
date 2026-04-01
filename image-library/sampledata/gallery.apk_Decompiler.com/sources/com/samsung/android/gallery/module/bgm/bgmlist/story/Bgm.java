package com.samsung.android.gallery.module.bgm.bgmlist.story;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Bgm {
    static BgmListCompat sBgmList = BgmListCompat.create();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Theme {
        RELAXING(Bgm.sBgmList.getRelaxing()),
        LOUNGE(Bgm.sBgmList.getLounge()),
        HAPPY(Bgm.sBgmList.getHappy()),
        COMIC(Bgm.sBgmList.getComic()),
        UPBEAT(Bgm.sBgmList.getUpbeat()),
        SENTIMENTAL(Bgm.sBgmList.getSentimental()),
        MYSTERY(Bgm.sBgmList.getMystery()),
        LOVELY(Bgm.sBgmList.getLovely()),
        INTENSE(Bgm.sBgmList.getIntense()),
        DYNAMIC(Bgm.sBgmList.getDynamic());
        
        /* access modifiers changed from: private */
        public final HashMap<String, Boolean> bgmList;
        private String[] preloadBgmList;

        private Theme(HashMap<String, Boolean> hashMap) {
            this.bgmList = hashMap;
        }

        public String[] getPreloadBgmList() {
            if (this.preloadBgmList == null) {
                ArrayList arrayList = new ArrayList();
                this.bgmList.forEach(new a(arrayList));
                this.preloadBgmList = (String[]) arrayList.toArray(new String[0]);
            }
            return this.preloadBgmList;
        }
    }

    public static List<String> getAllPreloadedBgm() {
        ArrayList arrayList = new ArrayList();
        for (Theme name : Theme.values()) {
            arrayList.addAll(new ArrayList(Arrays.asList(getPreloadBgmList(name.name()))));
        }
        return arrayList;
    }

    public static List<String> getBelongThemes(String str) {
        ArrayList arrayList = new ArrayList();
        for (Theme theme : Theme.values()) {
            if (theme.bgmList.containsKey(str)) {
                arrayList.add(theme.name());
            }
        }
        return arrayList;
    }

    public static String[] getBgmList(String str) {
        for (Theme theme : Theme.values()) {
            if (theme.name().equalsIgnoreCase(str)) {
                return (String[]) theme.bgmList.keySet().toArray(new String[0]);
            }
        }
        return (String[]) Theme.RELAXING.bgmList.keySet().toArray(new String[0]);
    }

    public static String[] getPreloadBgmList(String str) {
        for (Theme theme : Theme.values()) {
            if (theme.name().equalsIgnoreCase(str)) {
                return theme.getPreloadBgmList();
            }
        }
        return Theme.RELAXING.getPreloadBgmList();
    }

    public static boolean isBgm(String str) {
        for (Theme b : Theme.values()) {
            if (b.bgmList.containsKey(str)) {
                return true;
            }
        }
        return false;
    }
}
