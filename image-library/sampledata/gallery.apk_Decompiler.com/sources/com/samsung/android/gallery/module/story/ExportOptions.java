package com.samsung.android.gallery.module.story;

import android.graphics.Bitmap;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ExportOptions {
    private Bitmap backgroundBitmap;
    private BgmExtraInfo bgmExtraInfo;
    private String cornerRadius;
    private String displayPositionRect;
    private String filterName;
    private Bitmap foregroundBitmap;
    private int height;
    private Object kenBurnsMap;
    private String outPath;
    private int playTimeMs;
    private int ratio;
    private int requestCode;
    private int storyId;
    private String subTitle;
    private EffectTheme theme;
    private String title;
    private int uniqueKey;
    private int width;

    private BgmExtraInfo createDefaultBgm() {
        BgmExtraInfo bgmExtraInfo2 = new BgmExtraInfo();
        bgmExtraInfo2.bgmName = EffectTheme.Relaxing.getPreloadBgmList()[0];
        Log.e((CharSequence) "ExportOptions", "createDefaultBgm, bgm was not set", bgmExtraInfo2);
        return bgmExtraInfo2;
    }

    public Bitmap getBackgroundBitmap() {
        return this.backgroundBitmap;
    }

    public BgmExtraInfo getBgmExtraInfo() {
        BgmExtraInfo bgmExtraInfo2 = this.bgmExtraInfo;
        if (bgmExtraInfo2 != null) {
            return bgmExtraInfo2;
        }
        return createDefaultBgm();
    }

    public String getCornerRadius() {
        return this.cornerRadius;
    }

    public String getDisplayPositionRect() {
        return this.displayPositionRect;
    }

    public String getFilterName() {
        return this.filterName;
    }

    public Bitmap getForegroundBitmap() {
        return this.foregroundBitmap;
    }

    public Object getKenBurnsMap() {
        return this.kenBurnsMap;
    }

    public String getOutPath() {
        return this.outPath;
    }

    public int getPlayTimeMs() {
        return this.playTimeMs;
    }

    public int getRequestCode() {
        return this.requestCode;
    }

    public int getStoryId() {
        return this.storyId;
    }

    public String getSubTitle() {
        String str = this.subTitle;
        if (str != null) {
            return str;
        }
        return "";
    }

    public int getTargetHeight() {
        return this.height;
    }

    public int getTargetWidth() {
        return this.width;
    }

    public EffectTheme getTheme() {
        if (this.theme == null) {
            Log.e("ExportOptions", "createDefaultBgm, theme was not set");
        }
        EffectTheme effectTheme = this.theme;
        if (effectTheme != null) {
            return effectTheme;
        }
        return EffectTheme.Relaxing;
    }

    public String getTitle() {
        String str = this.title;
        if (str != null) {
            return str;
        }
        return "";
    }

    public int getUniqueKey() {
        return this.uniqueKey;
    }

    public ExportOptions setBackgroundBitmap(Bitmap bitmap) {
        this.backgroundBitmap = bitmap;
        return this;
    }

    public ExportOptions setBgmExtraInfo(BgmExtraInfo bgmExtraInfo2) {
        this.bgmExtraInfo = bgmExtraInfo2;
        return this;
    }

    public ExportOptions setCornerRadius(String str) {
        this.cornerRadius = str;
        return this;
    }

    public ExportOptions setDisplayPositionRect(String str) {
        this.displayPositionRect = str;
        return this;
    }

    public ExportOptions setFilterName(String str) {
        this.filterName = str;
        return this;
    }

    public ExportOptions setForegroundBitmap(Bitmap bitmap) {
        this.foregroundBitmap = bitmap;
        return this;
    }

    public ExportOptions setKenBurnsMap(Object obj) {
        if (obj instanceof HashMap) {
            this.kenBurnsMap = obj;
            return this;
        }
        throw new IllegalArgumentException("setKenBurnsInfo, wrong arg" + obj);
    }

    public ExportOptions setOutPath(String str) {
        this.outPath = str;
        return this;
    }

    public ExportOptions setPlayTimeMs(int i2) {
        this.playTimeMs = i2;
        return this;
    }

    public ExportOptions setRatio(int i2) {
        this.ratio = i2;
        return this;
    }

    public ExportOptions setRequestCode(int i2) {
        this.requestCode = i2;
        return this;
    }

    public ExportOptions setSize(int[] iArr) {
        this.width = iArr[0];
        this.height = iArr[1];
        return this;
    }

    public ExportOptions setStoryId(int i2) {
        this.storyId = i2;
        return this;
    }

    public ExportOptions setSubTitle(String str) {
        this.subTitle = str;
        return this;
    }

    public ExportOptions setTheme(EffectTheme effectTheme) {
        this.theme = effectTheme;
        return this;
    }

    public ExportOptions setTitle(String str) {
        this.title = str;
        return this;
    }

    public ExportOptions setUniqueKey(int i2) {
        this.uniqueKey = i2;
        return this;
    }

    public String toString() {
        return "ExportOptions {" + ExportType.getType(this.requestCode) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.theme + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.filterName + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.ratio + ArcCommonLog.TAG_COMMA + this.bgmExtraInfo + "}";
    }
}
