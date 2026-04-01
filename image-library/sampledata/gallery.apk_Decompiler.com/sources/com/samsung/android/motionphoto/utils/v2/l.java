package com.samsung.android.motionphoto.utils.v2;

import Ae.a;
import android.media.MediaFormat;
import android.os.ConditionVariable;
import com.samsung.android.motionphoto.utils.v2.MotionScrap;
import java.util.concurrent.Callable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MotionScrap f3251a;
    public final /* synthetic */ a b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f3252c;
    public final /* synthetic */ ConditionVariable d;
    public final /* synthetic */ MotionScrap.FutureWrapper e;
    public final /* synthetic */ MediaFormat f;

    public /* synthetic */ l(MotionScrap motionScrap, a aVar, int i2, ConditionVariable conditionVariable, MotionScrap.FutureWrapper futureWrapper, MediaFormat mediaFormat) {
        this.f3251a = motionScrap;
        this.b = aVar;
        this.f3252c = i2;
        this.d = conditionVariable;
        this.e = futureWrapper;
        this.f = mediaFormat;
    }

    public final Object call() {
        return MotionScrap.executeTasks$lambda$54(this.f3251a, this.b, this.f3252c, this.d, this.e, this.f);
    }
}
