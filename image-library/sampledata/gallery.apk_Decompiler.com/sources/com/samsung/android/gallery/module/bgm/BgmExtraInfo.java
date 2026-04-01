package com.samsung.android.gallery.module.bgm;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.ArrayList;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmExtraInfo {
    public String bgmName;
    public boolean isFragmentedBgm = true;
    public boolean isMyMusic;
    public String path;
    public ArrayList<Uri> uris;

    public static String getBgmExtraInfoString(BgmExtraInfo bgmExtraInfo) {
        StringJoiner stringJoiner = new StringJoiner(";");
        if (bgmExtraInfo != null) {
            stringJoiner.add(bgmExtraInfo.bgmName);
            stringJoiner.add(bgmExtraInfo.path);
            boolean z = bgmExtraInfo.isFragmentedBgm;
            String str = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE;
            stringJoiner.add(z ? SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE : str);
            if (bgmExtraInfo.isMyMusic) {
                str = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE;
            }
            stringJoiner.add(str);
        }
        return stringJoiner.toString();
    }

    public static BgmExtraInfo parse(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        BgmExtraInfo bgmExtraInfo = new BgmExtraInfo();
        bgmExtraInfo.bgmName = bundle.getString("bgm_name");
        bgmExtraInfo.path = bundle.getString("file_path");
        bgmExtraInfo.isFragmentedBgm = bundle.getBoolean("isBgmFragmented", false);
        bgmExtraInfo.isMyMusic = bundle.getBoolean("is_mymusic", false);
        bgmExtraInfo.uris = (ArrayList) bundle.getSerializable("bgm_data");
        return bgmExtraInfo;
    }

    public boolean equals(Object obj) {
        String str;
        boolean z;
        if (obj instanceof BgmExtraInfo) {
            BgmExtraInfo bgmExtraInfo = (BgmExtraInfo) obj;
            String str2 = this.bgmName;
            if (str2 != null && (str = bgmExtraInfo.bgmName) != null && str2.equals(str) && (z = this.isMyMusic) == bgmExtraInfo.isMyMusic) {
                if (!z) {
                    return true;
                }
                if (TextUtils.isEmpty(this.path) || TextUtils.isEmpty(bgmExtraInfo.path) || !this.path.equals(bgmExtraInfo.path)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public boolean hasUris() {
        ArrayList<Uri> arrayList = this.uris;
        if (arrayList == null || arrayList.isEmpty()) {
            return false;
        }
        return true;
    }

    public String toString() {
        String str;
        String str2;
        int i2;
        StringBuilder sb2 = new StringBuilder("BgmExtraInfo{");
        sb2.append(this.bgmName);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (this.isFragmentedBgm) {
            str = "B";
        } else {
            str = "b";
        }
        sb2.append(str);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (this.isMyMusic) {
            str2 = "M";
        } else {
            str2 = "m";
        }
        sb2.append(str2);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        ArrayList<Uri> arrayList = this.uris;
        if (arrayList != null) {
            i2 = arrayList.size();
        } else {
            i2 = -1;
        }
        sb2.append(i2);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(Logger.getEncodedString(this.path));
        sb2.append("}");
        return sb2.toString();
    }

    public static BgmExtraInfo parse(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(";");
        BgmExtraInfo bgmExtraInfo = new BgmExtraInfo();
        bgmExtraInfo.bgmName = split[0];
        if ("null".equalsIgnoreCase(split[1]) || (str2 = split[1]) == null) {
            str2 = "";
        }
        bgmExtraInfo.path = str2;
        bgmExtraInfo.isFragmentedBgm = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(split[2]);
        bgmExtraInfo.isMyMusic = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(split[3]);
        return bgmExtraInfo;
    }
}
