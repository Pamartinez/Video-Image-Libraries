package com.samsung.android.gallery.module.bgm;

import A.a;
import android.net.Uri;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.module.bgm.bgmdata.BgmInfo;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmOptionBuilder {
    protected BgmInfo mBgmInfo;
    protected String mBgmName;
    protected Integer mDuration;

    private void validateBgm(BgmInfo bgmInfo) {
        if (bgmInfo.size() == 1) {
            Uri uri = bgmInfo.getUri(0);
            bgmInfo.add(uri.toString());
            bgmInfo.add(uri.toString());
            Log.w("BgmOptionBuilder", "there is no bgm body and outro, copy intro");
        } else if (bgmInfo.size() == 2) {
            bgmInfo.add(bgmInfo.getUri(1).toString());
            Log.w("BgmOptionBuilder", "there is no bgm outro, copy body");
        }
    }

    public BgmOptions build() {
        boolean z;
        boolean z3;
        if (TextUtils.isEmpty(this.mBgmName) || this.mDuration == null || this.mBgmInfo == null) {
            throw new IllegalArgumentException("missing argument");
        }
        long currentTimeMillis = System.currentTimeMillis();
        validateBgm(this.mBgmInfo);
        int size = this.mBgmInfo.size() - 1;
        int max = Math.max(this.mDuration.intValue() - this.mBgmInfo.getDuration(0), 0);
        int i2 = 1;
        int i7 = 0;
        while (true) {
            if (max < 0) {
                break;
            } else if (max > this.mBgmInfo.getDuration(i2) + 200) {
                max -= this.mBgmInfo.getDuration(i2);
                i2++;
                if (i2 == size) {
                    i7++;
                    i2 = 1;
                }
            } else if (max >= 200) {
                max -= this.mBgmInfo.getDuration(size);
                z = true;
            }
        }
        z = false;
        int i8 = i2 - 1;
        if (z && i7 == 0 && i8 == 0) {
            i8 = 1;
            z3 = true;
            z = false;
        } else {
            z3 = false;
        }
        if (max < 0) {
            z3 = true;
        }
        BgmOptions bgmOptions = new BgmOptions(this.mBgmName, this.mDuration.intValue());
        bgmOptions.add(this.mBgmInfo.getUri(0), this.mBgmInfo.getParcelFileDescriptor(0), this.mBgmInfo.getDuration(0));
        for (int i10 = 1; i10 < size; i10++) {
            bgmOptions.add(this.mBgmInfo.getUri(i10), this.mBgmInfo.getParcelFileDescriptor(i10), this.mBgmInfo.getDuration(i10));
        }
        bgmOptions.add(this.mBgmInfo.getUri(size), this.mBgmInfo.getParcelFileDescriptor(size), this.mBgmInfo.getDuration(size));
        bgmOptions.setPlaybackRule(i7, i8, z, z3);
        a.A(new Object[]{C0086a.i(i7, "repeat="), C0086a.i(i8, "last="), "outro=" + z, Long.valueOf(currentTimeMillis)}, new StringBuilder("createBgmOptions"), "BgmOptionBuilder");
        return bgmOptions;
    }

    public BgmOptionBuilder setBgmInfo(BgmInfo bgmInfo) {
        this.mBgmInfo = bgmInfo;
        return this;
    }

    public BgmOptionBuilder setBgmName(String str) {
        this.mBgmName = str;
        return this;
    }

    public BgmOptionBuilder setDuration(int i2) {
        this.mDuration = Integer.valueOf(i2);
        return this;
    }
}
