package com.samsung.android.motionphoto.utils.v2;

import android.media.MediaFormat;
import android.os.ConditionVariable;
import com.samsung.android.motionphoto.utils.v2.MotionScrap;
import com.samsung.android.sdk.scs.ai.text.category.DocumentCategoryClassifier;
import java.util.concurrent.Callable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3249a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f3250c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ k(Object obj, Object obj2, Object obj3, Object obj4, int i2) {
        this.f3249a = i2;
        this.b = obj;
        this.f3250c = obj2;
        this.d = obj3;
        this.e = obj4;
    }

    public final Object call() {
        switch (this.f3249a) {
            case 0:
                return MotionScrap.executeTasks$lambda$47((MotionScrap) this.b, (MotionScrap.FutureWrapper) this.f3250c, (ConditionVariable) this.d, (MediaFormat) this.e);
            default:
                return ((DocumentCategoryClassifier) this.b).lambda$isSupported$0((DocumentCategoryClassifier.RequestType) this.f3250c, (String) this.d, (String) this.e);
        }
    }
}
