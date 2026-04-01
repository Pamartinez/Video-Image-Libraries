package com.samsung.android.app.sdk.deepsky.objectcapture.impl;

import android.graphics.Bitmap;
import kotlin.Metadata;
import srib.vizinsight.dvs.UNF_Object_Segment_Info;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u001f\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H&¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0002H&¢\u0006\u0004\b\f\u0010\u0004J\u000f\u0010\u000e\u001a\u00020\rH&¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/impl/SribCaptureWrapper;", "", "", "init", "()I", "Landroid/graphics/Bitmap;", "bitmap", "Lsrib/vizinsight/dvs/UNF_Object_Segment_Info;", "result", "Lme/x;", "capture", "(Landroid/graphics/Bitmap;Lsrib/vizinsight/dvs/UNF_Object_Segment_Info;)V", "release", "", "getVersion", "()Ljava/lang/String;", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SribCaptureWrapper {
    void capture(Bitmap bitmap, UNF_Object_Segment_Info uNF_Object_Segment_Info);

    String getVersion();

    int init();

    int release();
}
