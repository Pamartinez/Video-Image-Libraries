package com.samsung.android.gallery.module.lottie.recap.data;

import android.graphics.RectF;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.lottie.recap.data.parser.AnalyzedData;
import com.samsung.android.gallery.module.lottie.recap.data.parser.FaceData;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapImageCandidate {
    public String cityName;
    public boolean hasPeoplePetTag;
    public boolean hasSceneryTag;
    public int height;
    public boolean isNewLocation;
    public boolean isVertical;
    public MediaItem item;
    public final ArrayList<FaceData> mFaces = new ArrayList<>();
    public String mSourceTag;
    long secId;
    public int uniqueDays;
    public boolean used;
    public int width;
    public boolean wrongRatio;

    public RecapImageCandidate(MediaItem mediaItem, String str) {
        boolean z;
        int i2;
        int i7;
        this.item = mediaItem;
        int orientation = mediaItem.getOrientation();
        boolean z3 = true;
        if (orientation == 90 || orientation == 270) {
            z = true;
        } else {
            z = false;
        }
        this.secId = this.item.getFileId();
        MediaItem mediaItem2 = this.item;
        if (z) {
            i2 = mediaItem2.getHeight();
        } else {
            i2 = mediaItem2.getWidth();
        }
        this.width = i2;
        if (z) {
            i7 = this.item.getWidth();
        } else {
            i7 = this.item.getHeight();
        }
        this.height = i7;
        this.isVertical = i7 < this.width ? false : z3;
        this.mSourceTag = str;
    }

    public RecapImageCandidate applyPreLoaded(AnalyzedData.JSON_CONTENTS json_contents) {
        if (json_contents != null) {
            this.mFaces.addAll(json_contents.mFaces);
            this.cityName = json_contents.city;
            this.uniqueDays = json_contents.uniqueDays;
            this.isNewLocation = json_contents.isNew;
            this.hasPeoplePetTag = json_contents.hasPeoplePetTag;
            this.hasSceneryTag = json_contents.hasSceneryTag;
        }
        return this;
    }

    public boolean canUse() {
        return !this.used;
    }

    public RectF getFirstFaceRectF() {
        if (this.mFaces.isEmpty()) {
            return null;
        }
        return this.mFaces.get(0).getFaceRectF();
    }

    public String getFirstPeopleName() {
        if (this.mFaces.isEmpty()) {
            return "";
        }
        return this.mFaces.get(0).name;
    }

    public boolean isVertical() {
        return this.isVertical;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("RecapImageCandidate{used=");
        sb2.append(this.used);
        sb2.append(", item=");
        Long valueOf = Long.valueOf(this.item.getMediaId());
        Long valueOf2 = Long.valueOf(this.item.getFileId());
        sb2.append(Logger.v(valueOf, valueOf2, this.item.getWidth() + "x" + this.item.getHeight() + Log.TAG_SEPARATOR + this.item.getOrientation()));
        sb2.append(", width=");
        sb2.append(this.width);
        sb2.append(", height=");
        sb2.append(this.height);
        sb2.append(", isVertical=");
        return C0086a.n(sb2, this.isVertical, '}');
    }
}
