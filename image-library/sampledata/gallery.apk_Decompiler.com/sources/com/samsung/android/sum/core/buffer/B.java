package com.samsung.android.sum.core.buffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class B implements MediaBufferReader {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4038a;
    public final /* synthetic */ MediaBuffer b;

    public /* synthetic */ B(int i2, MediaBuffer mediaBuffer) {
        this.f4038a = i2;
        this.b = mediaBuffer;
    }

    public final Object get() {
        int i2 = this.f4038a;
        MediaBuffer mediaBuffer = this.b;
        switch (i2) {
            case 0:
                return mediaBuffer.getFormat().getShape();
            default:
                return mediaBuffer.getFormat().getMediaType();
        }
    }
}
