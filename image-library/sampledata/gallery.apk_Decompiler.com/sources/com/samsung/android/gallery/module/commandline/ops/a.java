package com.samsung.android.gallery.module.commandline.ops;

import android.content.Context;
import com.samsung.android.gallery.module.commandline.ops.ApplyUxTune;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2922a;
    public final /* synthetic */ ApplyUxTune b;

    public /* synthetic */ a(ApplyUxTune applyUxTune, int i2) {
        this.f2922a = i2;
        this.b = applyUxTune;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f2922a;
        ApplyUxTune applyUxTune = this.b;
        Context context = (Context) obj;
        String str = (String) obj2;
        switch (i2) {
            case 0:
                int i7 = ApplyUxTune.AnonymousClass1.d;
                applyUxTune.uxtune_interpolator(context, str);
                return;
            case 1:
                int i8 = ApplyUxTune.AnonymousClass1.d;
                applyUxTune.uxtune_set_int(context, str);
                return;
            default:
                int i10 = ApplyUxTune.AnonymousClass1.d;
                applyUxTune.uxtune_set_float(context, str);
                return;
        }
    }
}
