package com.samsung.android.motionphoto.utils.v2;

import Ae.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ MotionPhotoEditImpl e;
    public final /* synthetic */ byte[] f;

    public /* synthetic */ f(MotionPhotoEditImpl motionPhotoEditImpl, byte[] bArr, int i2) {
        this.d = i2;
        this.e = motionPhotoEditImpl;
        this.f = bArr;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                return MotionPhotoEditImpl.addSEFData$lambda$3(this.e, this.f, (MediaFile) obj);
            default:
                return MotionPhotoEditImpl.prepareOutputFile$lambda$31$lambda$30(this.e, this.f, (MediaFile) obj);
        }
    }
}
