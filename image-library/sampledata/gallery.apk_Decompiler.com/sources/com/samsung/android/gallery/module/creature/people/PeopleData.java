package com.samsung.android.gallery.module.creature.people;

import android.graphics.RectF;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PeopleData implements Cloneable {
    private RectF mConvertFaceRectF = new RectF();
    private RectF mDisplayedRectF = new RectF();
    private final long mFaceId;
    private long mGroupId;
    private String mName;
    private long mPersonId;
    private RectF mPosRectF;
    private long mRecommendedId;

    public PeopleData(RectF rectF, String str, long j2, long j3, long j8, long j10) {
        this.mPosRectF = rectF;
        this.mName = str;
        this.mPersonId = j2;
        this.mGroupId = j3;
        this.mRecommendedId = j8;
        this.mFaceId = j10;
    }

    public RectF getFaceRectF() {
        return this.mConvertFaceRectF;
    }

    public long getGroupId() {
        return this.mGroupId;
    }

    public long getPersonId() {
        return this.mPersonId;
    }

    public RectF getPosRectF() {
        return this.mPosRectF;
    }

    public long getRecommendedId() {
        return this.mRecommendedId;
    }

    public void setDisplayedRectF(RectF rectF) {
        this.mDisplayedRectF = rectF;
    }

    public void setFaceRectF(RectF rectF, int i2) {
        RectF posRectF = getPosRectF();
        RectF rectF2 = new RectF();
        float f = rectF.right;
        float f5 = rectF.left;
        float f8 = f - f5;
        float f10 = rectF.bottom;
        float f11 = rectF.top;
        float f12 = f10 - f11;
        if (i2 == 0) {
            rectF2.left = (posRectF.left * f8) + f5;
            rectF2.right = (f8 * posRectF.right) + rectF.left;
            rectF2.top = (posRectF.top * f12) + f11;
            rectF2.bottom = (f12 * posRectF.bottom) + rectF.top;
        } else if (i2 == 90) {
            rectF2.left = C0212a.a(1.0f, posRectF.bottom, f8, f5);
            rectF2.right = ((1.0f - posRectF.top) * f8) + rectF.left;
            rectF2.top = (posRectF.left * f12) + f11;
            rectF2.bottom = (f12 * posRectF.right) + rectF.top;
        } else if (i2 != 180) {
            rectF2.left = (posRectF.top * f8) + f5;
            rectF2.right = (f8 * posRectF.bottom) + rectF.left;
            rectF2.top = C0212a.a(1.0f, posRectF.right, f12, f11);
            rectF2.bottom = ((1.0f - posRectF.left) * f12) + rectF.top;
        } else {
            rectF2.left = C0212a.a(1.0f, posRectF.right, f8, f5);
            rectF2.right = ((1.0f - posRectF.left) * f8) + rectF.left;
            rectF2.top = C0212a.a(1.0f, posRectF.bottom, f12, f11);
            rectF2.bottom = ((1.0f - posRectF.top) * f12) + rectF.top;
        }
        setDisplayedRectF(rectF);
        this.mConvertFaceRectF = rectF2;
    }

    public void update(String str, long j2, long j3) {
        this.mName = str;
        if (j2 != -1) {
            this.mPersonId = j2;
            if (j2 > 1) {
                this.mRecommendedId = j2;
            }
        }
        if (j3 != -1) {
            this.mGroupId = j3;
            if (j2 == 1) {
                this.mRecommendedId = j3 + 100000;
            }
        }
    }

    public PeopleData clone() {
        return new PeopleData(this.mPosRectF, this.mName, this.mPersonId, this.mGroupId, this.mRecommendedId, this.mFaceId);
    }
}
