package com.samsung.android.gallery.module.story.transcode.encoder.video;

import com.samsung.android.gallery.module.story.transcode.config.EncodeInfo;
import com.samsung.android.gallery.module.story.transcode.config.FrameProperty;
import com.samsung.android.gallery.module.story.transcode.config.KenBurnsInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FrameProvider {
    private Data mCurrent;
    private final Data[] mDataList;
    private final HashMap<ThumbnailInterface, KenBurnsInfo> mKenBurnsMap;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Data {
        int duration;
        int endTime;
        float fadeOut;
        int index;
        KenBurnsInfo kenBurnsInfo;
        int startTime;

        public /* synthetic */ Data(int i2) {
            this();
        }

        private Data() {
        }
    }

    public FrameProvider(EncodeInfo encodeInfo) {
        HashMap<ThumbnailInterface, KenBurnsInfo> hashMap = encodeInfo.kenBurnsMap;
        this.mKenBurnsMap = hashMap;
        Data[] dataArr = new Data[hashMap.size()];
        this.mDataList = dataArr;
        init(encodeInfo);
        Log.d("FrameProvider", "kenBurnsInfo", Integer.valueOf(dataArr.length));
    }

    private Data findData(long j2) {
        for (Data data : this.mDataList) {
            if (j2 >= ((long) data.startTime) && j2 < ((long) data.endTime)) {
                return data;
            }
        }
        return null;
    }

    private FrameProperty getTransform(int i2, float f, boolean z) {
        KenBurnsInfo.Property transformProperty = this.mDataList[i2].kenBurnsInfo.getTransformProperty();
        transformProperty.setProgress(f);
        return new FrameProperty.Builder(z).setIndex(this.mDataList[i2].index).setAlpha(transformProperty.getAlpha()).setScaleX(transformProperty.getScaleX()).setScaleY(transformProperty.getScaleY()).setTranslateX(transformProperty.getTranslateX()).setTranslateY(transformProperty.getTranslateY()).setPivotX(transformProperty.getPivotX()).setPivotY(transformProperty.getPivotY()).setProgress(transformProperty.getProgress()).build();
    }

    private FrameProperty getTransition(int i2, float f) {
        KenBurnsInfo.Property transitionInProperty = this.mDataList[i2].kenBurnsInfo.getTransitionInProperty();
        if (f < transitionInProperty.getDelay()) {
            return null;
        }
        transitionInProperty.setProgress(f);
        return new FrameProperty.Builder().setIndex(this.mDataList[i2 + 1].index).setAlpha(transitionInProperty.getAlpha()).setScaleX(transitionInProperty.getScaleX()).setScaleY(transitionInProperty.getScaleY()).setTranslateX(transitionInProperty.getTranslateX()).setTranslateY(transitionInProperty.getTranslateY()).setPivotX(transitionInProperty.getPivotX()).setPivotY(transitionInProperty.getPivotY()).build();
    }

    private void init(EncodeInfo encodeInfo) {
        int i2 = 0;
        int i7 = 0;
        for (Map.Entry<ThumbnailInterface, KenBurnsInfo> key : this.mKenBurnsMap.entrySet()) {
            ThumbnailInterface thumbnailInterface = (ThumbnailInterface) key.getKey();
            Data data = new Data(0);
            this.mDataList[i2] = data;
            int itemDuration = encodeInfo.getItemDuration(thumbnailInterface);
            KenBurnsInfo kenBurnsInfo = this.mKenBurnsMap.get(thumbnailInterface);
            data.index = i2;
            data.kenBurnsInfo = kenBurnsInfo;
            data.startTime = i7;
            i7 += itemDuration;
            data.endTime = i7;
            data.duration = itemDuration;
            if (kenBurnsInfo != null) {
                data.fadeOut = ((float) itemDuration) * kenBurnsInfo.getTransitionOutProperty().mDelay;
            }
            i2++;
        }
    }

    private boolean isExist(int i2) {
        if (i2 < 0 || i2 >= this.mDataList.length) {
            return false;
        }
        return true;
    }

    public ArrayList<FrameProperty> getFrameProperties(long j2) {
        float f;
        int i2;
        FrameProperty frameProperty;
        Data data = this.mCurrent;
        if (data == null || ((long) data.startTime) > j2 || ((long) data.endTime) <= j2) {
            this.mCurrent = findData(j2);
        }
        Data data2 = this.mCurrent;
        boolean z = true;
        if (data2 == null) {
            Data[] dataArr = this.mDataList;
            Data data3 = dataArr[dataArr.length - 1];
        }
        if (data2 != null) {
            f = ((float) (j2 - ((long) data2.startTime))) / ((float) data2.duration);
        } else {
            f = 1.0f;
        }
        if (data2 != null) {
            i2 = data2.index;
        } else {
            i2 = this.mDataList.length - 1;
        }
        ArrayList<FrameProperty> arrayList = new ArrayList<>();
        if (isExist(i2 + 1)) {
            frameProperty = getTransition(i2, f);
        } else {
            frameProperty = null;
        }
        if (frameProperty == null) {
            z = false;
        }
        FrameProperty transform = getTransform(i2, f, z);
        if (transform != null) {
            arrayList.add(transform);
        }
        if (frameProperty != null) {
            arrayList.add(frameProperty);
        }
        return arrayList;
    }
}
