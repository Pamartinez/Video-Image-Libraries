package com.samsung.android.sum.core.buffer;

import com.samsung.android.sum.core.format.MediaFormat;
import java.util.function.BiFunction;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class z implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4061a;
    public final /* synthetic */ MediaFormat b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f4062c;

    public /* synthetic */ z(MediaFormat mediaFormat, String str, int i2) {
        this.f4061a = i2;
        this.b = mediaFormat;
        this.f4062c = str;
    }

    public final Object apply(Object obj) {
        switch (this.f4061a) {
            case 0:
                return MediaBufferFileReader.lambda$read$3(this.b, this.f4062c, (BiFunction) obj);
            default:
                return MediaBufferFileReader.lambda$read$5(this.b, this.f4062c, (BiFunction) obj);
        }
    }
}
