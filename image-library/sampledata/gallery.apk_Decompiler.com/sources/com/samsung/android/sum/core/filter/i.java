package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.types.MediaType;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4092a;
    public final /* synthetic */ MediaType b;

    public /* synthetic */ i(MediaType mediaType, int i2) {
        this.f4092a = i2;
        this.b = mediaType;
    }

    public final boolean test(Object obj) {
        int i2 = this.f4092a;
        MediaType mediaType = this.b;
        MediaType mediaType2 = (MediaType) obj;
        switch (i2) {
            case 0:
                return MediaMuxerFilter.lambda$onReceiveCodecFormatData$6(mediaType, mediaType2);
            case 1:
                return MediaMuxerFilter.lambda$onReceiveCodecFormatData$7(mediaType, mediaType2);
            default:
                return MediaMuxerFilter.lambda$isDirectMuxingTrack$3(mediaType, mediaType2);
        }
    }
}
