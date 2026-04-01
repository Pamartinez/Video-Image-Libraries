package com.samsung.android.motionphoto.utils.v2.video;

import android.media.MediaCodec;
import com.samsung.android.motionphoto.utils.v2.MPFile;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J'\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u000b\u0010\fJ'\u0010\r\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\r\u0010\nJ\u0017\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/EventListener;", "", "Lcom/samsung/android/motionphoto/utils/v2/MPFile;", "mpFile", "", "mimeType", "Landroid/media/MediaCodec$BufferInfo;", "bufferInfo", "Lme/x;", "onEachFrameTransferred", "(Lcom/samsung/android/motionphoto/utils/v2/MPFile;Ljava/lang/String;Landroid/media/MediaCodec$BufferInfo;)V", "onTransferComplete", "(Lcom/samsung/android/motionphoto/utils/v2/MPFile;)V", "onEachFrameTranscoded", "onTranscodingComplete", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface EventListener {
    void onEachFrameTranscoded(MPFile mPFile, String str, MediaCodec.BufferInfo bufferInfo);

    void onEachFrameTransferred(MPFile mPFile, String str, MediaCodec.BufferInfo bufferInfo);

    void onTranscodingComplete(MPFile mPFile);

    void onTransferComplete(MPFile mPFile);
}
