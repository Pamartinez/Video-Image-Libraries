package com.samsung.android.gallery.module.bgm.bgmlist.story;

import com.samsung.android.gallery.support.config.SdkConfig;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BgmListCompat {
    protected HashMap<String, Boolean> mComic = new LinkedHashMap();
    protected HashMap<String, Boolean> mDynamic = new LinkedHashMap();
    protected HashMap<String, Boolean> mHappy = new LinkedHashMap();
    protected HashMap<String, Boolean> mIntense = new LinkedHashMap();
    protected HashMap<String, Boolean> mLounge = new LinkedHashMap();
    protected HashMap<String, Boolean> mLovely = new LinkedHashMap();
    protected HashMap<String, Boolean> mMystery = new LinkedHashMap();
    protected HashMap<String, Boolean> mRelaxing = new LinkedHashMap();
    protected HashMap<String, Boolean> mSentimental = new LinkedHashMap();
    protected HashMap<String, Boolean> mUpbeat = new LinkedHashMap();

    public static BgmListCompat create() {
        if (SdkConfig.atLeast(SdkConfig.SEM.V)) {
            return new BgmListV70();
        }
        if (SdkConfig.atLeast(SdkConfig.SEM.U_MR1)) {
            return new BgmListV61();
        }
        if (SdkConfig.atLeast(SdkConfig.SEM.T_MR1)) {
            return new BgmListV51();
        }
        return new BgmListV50();
    }

    public HashMap<String, Boolean> getComic() {
        return this.mComic;
    }

    public HashMap<String, Boolean> getDynamic() {
        return this.mDynamic;
    }

    public HashMap<String, Boolean> getHappy() {
        return this.mHappy;
    }

    public HashMap<String, Boolean> getIntense() {
        return this.mIntense;
    }

    public HashMap<String, Boolean> getLounge() {
        return this.mLounge;
    }

    public HashMap<String, Boolean> getLovely() {
        return this.mLovely;
    }

    public HashMap<String, Boolean> getMystery() {
        return this.mMystery;
    }

    public HashMap<String, Boolean> getRelaxing() {
        return this.mRelaxing;
    }

    public HashMap<String, Boolean> getSentimental() {
        return this.mSentimental;
    }

    public HashMap<String, Boolean> getUpbeat() {
        return this.mUpbeat;
    }
}
