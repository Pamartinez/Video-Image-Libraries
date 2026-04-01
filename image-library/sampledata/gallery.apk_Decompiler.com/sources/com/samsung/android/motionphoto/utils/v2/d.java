package com.samsung.android.motionphoto.utils.v2;

import Ae.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements a {
    public final /* synthetic */ int d;
    public final /* synthetic */ MotionPhotoEditImpl e;

    public /* synthetic */ d(MotionPhotoEditImpl motionPhotoEditImpl, int i2) {
        this.d = i2;
        this.e = motionPhotoEditImpl;
    }

    public final Object invoke() {
        int i2 = this.d;
        MotionPhotoEditImpl motionPhotoEditImpl = this.e;
        switch (i2) {
            case 0:
                return MotionPhotoEditImpl.sefEdit_delegate$lambda$0(motionPhotoEditImpl);
            default:
                return MotionPhotoEditImpl.xmpEdit_delegate$lambda$1(motionPhotoEditImpl);
        }
    }
}
