package com.samsung.android.livetranslation.task;

import android.content.Context;
import com.samsung.android.livetranslation.text.KeyFrame;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TRLResultManager {
    private Context mContext;
    private KeyFrame mKeyFrame;
    private TRLResultOrganizer mOrganizer;
    private List<String> mTRLResult;

    public TRLResultManager(Context context, KeyFrame keyFrame, List<String> list) {
        this.mKeyFrame = keyFrame;
        this.mTRLResult = list;
        this.mContext = context;
    }

    public TRLResultOrganizer getOrganizer() {
        VisionTRLResultOrganizer visionTRLResultOrganizer = new VisionTRLResultOrganizer(this.mContext, this.mKeyFrame, this.mTRLResult);
        this.mOrganizer = visionTRLResultOrganizer;
        return visionTRLResultOrganizer;
    }
}
