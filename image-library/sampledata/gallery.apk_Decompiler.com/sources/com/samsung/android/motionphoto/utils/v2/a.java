package com.samsung.android.motionphoto.utils.v2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Ae.a {
    public final /* synthetic */ int d;
    public final /* synthetic */ MPFile e;

    public /* synthetic */ a(int i2, MPFile mPFile) {
        this.d = i2;
        this.e = mPFile;
    }

    public final Object invoke() {
        long o2;
        int i2 = this.d;
        MPFile mPFile = this.e;
        switch (i2) {
            case 0:
                return MPFile.sampleCount_delegate$lambda$0(mPFile);
            case 1:
                return MPFile.mpInfo_delegate$lambda$3(mPFile);
            case 2:
                o2 = MPFile.durationMs_delegate$lambda$7(mPFile);
                break;
            case 3:
                o2 = MPFile.dateTimeMs_delegate$lambda$9(mPFile);
                break;
            case 4:
                o2 = MPFile.startTimeMs_delegate$lambda$10(mPFile);
                break;
            default:
                o2 = MPFile.endTimeMs_delegate$lambda$11(mPFile);
                break;
        }
        return Long.valueOf(o2);
    }
}
