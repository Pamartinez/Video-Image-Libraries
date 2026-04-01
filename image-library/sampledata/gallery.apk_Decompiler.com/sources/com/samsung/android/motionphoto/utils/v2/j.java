package com.samsung.android.motionphoto.utils.v2;

import Ae.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements a {
    public final /* synthetic */ int d;
    public final /* synthetic */ MotionPhotoInfoImpl e;

    public /* synthetic */ j(MotionPhotoInfoImpl motionPhotoInfoImpl, int i2) {
        this.d = i2;
        this.e = motionPhotoInfoImpl;
    }

    public final Object invoke() {
        int i2 = this.d;
        MotionPhotoInfoImpl motionPhotoInfoImpl = this.e;
        switch (i2) {
            case 0:
                return MotionPhotoInfoImpl.sefInfo_delegate$lambda$0(motionPhotoInfoImpl);
            case 1:
                return MotionPhotoInfoImpl.exifIno_delegate$lambda$1(motionPhotoInfoImpl);
            case 2:
                return MotionPhotoInfoImpl.xmpInfo_delegate$lambda$2(motionPhotoInfoImpl);
            case 3:
                return MotionPhotoInfoImpl._videoInfo_delegate$lambda$3(motionPhotoInfoImpl);
            default:
                return MotionPhotoInfoImpl.autoPlayVideoInfo_delegate$lambda$5(motionPhotoInfoImpl);
        }
    }
}
