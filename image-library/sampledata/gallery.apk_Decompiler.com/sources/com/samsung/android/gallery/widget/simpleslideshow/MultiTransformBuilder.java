package com.samsung.android.gallery.widget.simpleslideshow;

import com.samsung.android.gallery.module.creature.people.PeopleData;
import com.samsung.android.gallery.support.utils.RandomNumber;
import com.samsung.android.gallery.widget.simpleslideshow.transform.PageTransform;
import com.samsung.android.gallery.widget.simpleslideshow.transform.PageTransformAlpha;
import com.samsung.android.gallery.widget.simpleslideshow.transform.PageTransformFaceCircle;
import com.samsung.android.gallery.widget.simpleslideshow.transform.PageTransformScale;
import com.samsung.android.gallery.widget.simpleslideshow.transform.PageTransformTranslate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MultiTransformBuilder implements TransformBuilder {
    private final AtomicBoolean mFaceCircleEnabled = new AtomicBoolean(false);
    private final AtomicInteger mFaceCircleRepeated = new AtomicInteger(0);
    private int mOrientation;
    private PeopleData mPeopleData;
    private TYPE mType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum TYPE {
        SCALE_UP,
        SCALE_DOWN,
        SCALE_UP_1,
        SCALE_DOWN_1,
        TRANSLATE_LEFT,
        TRANSLATE_RIGHT,
        TRANSLATE_UP,
        TRANSLATE_DOWN
    }

    private boolean allowFaceCircle() {
        boolean z;
        int i2 = this.mFaceCircleRepeated.get();
        int i7 = 0;
        if (this.mPeopleData == null || i2 >= 2 || RandomNumber.nextInt(2) != 0) {
            z = false;
        } else {
            z = true;
        }
        AtomicInteger atomicInteger = this.mFaceCircleRepeated;
        if (z) {
            i7 = i2 + 1;
        }
        atomicInteger.set(i7);
        return z;
    }

    private void buildIncomingPageTransform(List<PageTransform> list) {
        PeopleData peopleData;
        TYPE type = this.mType;
        if (type == TYPE.SCALE_UP || type == TYPE.SCALE_UP_1) {
            list.add(new PageTransformScale(1).setPivotX(getPivot()).setPivotY(getPivot()).setInitialValue(1.0f).setTargetValue(1.0f));
        } else if (type == TYPE.SCALE_DOWN || type == TYPE.SCALE_DOWN_1) {
            list.add(new PageTransformScale(1).setPivotX(getPivot()).setPivotY(getPivot()).setInitialValue(1.1f).setTargetValue(1.1f));
        } else {
            if (type == TYPE.TRANSLATE_LEFT) {
                list.add(new PageTransformTranslate(1).setInitialDeltaX(-0.100000024f).setTargetDeltaX(-0.100000024f));
            } else if (type == TYPE.TRANSLATE_RIGHT) {
                list.add(new PageTransformTranslate(1).setInitialDeltaX(0.100000024f).setTargetDeltaX(0.100000024f));
            } else if (type == TYPE.TRANSLATE_UP) {
                list.add(new PageTransformTranslate(1).setInitialDeltaY(-0.100000024f).setTargetDeltaY(-0.100000024f));
            } else if (type == TYPE.TRANSLATE_DOWN) {
                list.add(new PageTransformTranslate(1).setInitialDeltaY(0.100000024f).setTargetDeltaY(0.100000024f));
            }
            list.add(new PageTransformScale(1).setInitialValue(1.2f).setTargetValue(1.2f));
        }
        list.add(new PageTransformAlpha(1).setDelay(getPageInOutDelay()).setInitialValue(0.0f).setTargetValue(1.0f));
        if (this.mFaceCircleEnabled.get() && (peopleData = this.mPeopleData) != null) {
            list.add(new PageTransformFaceCircle(1, peopleData, this.mOrientation).setDurationTime((int) ((1.0f - getPageInOutDelay()) * ((float) getDurationTime()))).setDelay(getPageInOutDelay()));
        }
    }

    private void buildOutgoingPageTransform(List<PageTransform> list) {
        TYPE type = this.mType;
        if (type == TYPE.SCALE_UP || type == TYPE.SCALE_UP_1) {
            list.add(new PageTransformScale(0).setInitialValue(1.0f).setTargetValue(1.1f));
        } else if (type == TYPE.SCALE_DOWN || type == TYPE.SCALE_DOWN_1) {
            list.add(new PageTransformScale(0).setInitialValue(1.1f).setTargetValue(1.0f));
        } else {
            list.add(new PageTransformScale(0).setInitialValue(1.2f).setTargetValue(1.2f));
            TYPE type2 = this.mType;
            if (type2 == TYPE.TRANSLATE_LEFT) {
                list.add(new PageTransformTranslate(0).setInitialDeltaX(0.100000024f).setTargetDeltaX(0.0f));
            } else if (type2 == TYPE.TRANSLATE_RIGHT) {
                list.add(new PageTransformTranslate(0).setInitialDeltaX(-0.100000024f).setTargetDeltaX(0.0f));
            } else if (type2 == TYPE.TRANSLATE_UP) {
                list.add(new PageTransformTranslate(0).setInitialDeltaY(0.100000024f).setTargetDeltaY(0.0f));
            } else if (type2 == TYPE.TRANSLATE_DOWN) {
                list.add(new PageTransformTranslate(0).setInitialDeltaY(-0.100000024f).setTargetDeltaY(0.0f));
            }
        }
        if (this.mFaceCircleEnabled.get()) {
            list.add(new PageTransformAlpha(0).setDelay(getPageInOutDelay()).setDuration(0.4f).setInitialValue(1.0f).setTargetValue(0.0f));
        } else {
            list.add(new PageTransformAlpha(0).setDelay(getPageInOutDelay()).setInitialValue(1.0f).setTargetValue(0.0f));
        }
    }

    private TYPE getNextTransform() {
        return TYPE.values()[RandomNumber.nextInt(TYPE.values().length)];
    }

    private float getPivot() {
        int nextInt = RandomNumber.nextInt(3);
        if (nextInt == 0) {
            return 0.15f;
        }
        if (nextInt == 1) {
            return 0.85f;
        }
        return 0.5f;
    }

    public List<PageTransform> buildNext() {
        if (this.mType == null) {
            this.mType = TYPE.SCALE_UP;
        }
        this.mFaceCircleEnabled.set(allowFaceCircle());
        ArrayList arrayList = new ArrayList();
        buildOutgoingPageTransform(arrayList);
        this.mType = getNextTransform();
        buildIncomingPageTransform(arrayList);
        return arrayList;
    }

    public float getPageInOutDelay() {
        if (!this.mFaceCircleEnabled.get() || this.mPeopleData == null) {
            return 0.7f;
        }
        return 0.5f;
    }

    public void recycle() {
        this.mType = null;
        this.mPeopleData = null;
        this.mOrientation = 0;
        this.mFaceCircleRepeated.set(0);
    }

    public void setNextPeopleData(PeopleData peopleData, int i2) {
        this.mPeopleData = peopleData;
        this.mOrientation = i2;
    }
}
