package com.samsung.android.motionphoto.utils.v2;

import Ae.a;
import com.samsung.android.motionphoto.utils.v2.VideoInfoImpl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements a {
    public final /* synthetic */ int d;
    public final /* synthetic */ VideoInfoImpl.TrackInfo e;

    public /* synthetic */ o(VideoInfoImpl.TrackInfo trackInfo, int i2) {
        this.d = i2;
        this.e = trackInfo;
    }

    public final Object invoke() {
        long c5;
        int i2 = this.d;
        VideoInfoImpl.TrackInfo trackInfo = this.e;
        switch (i2) {
            case 0:
                return VideoInfoImpl.TrackInfo.mimeType_delegate$lambda$0(trackInfo);
            case 1:
                c5 = VideoInfoImpl.TrackInfo.durationUs_delegate$lambda$1(trackInfo);
                break;
            default:
                c5 = VideoInfoImpl.TrackInfo.bitrate_delegate$lambda$2(trackInfo);
                break;
        }
        return Long.valueOf(c5);
    }
}
