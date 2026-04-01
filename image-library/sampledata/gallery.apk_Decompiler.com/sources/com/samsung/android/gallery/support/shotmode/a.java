package com.samsung.android.gallery.support.shotmode;

import com.samsung.android.gallery.support.shotmode.ShotMode;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3153a;
    public final /* synthetic */ ShotMode b;

    public /* synthetic */ a(ShotMode shotMode, int i2) {
        this.f3153a = i2;
        this.b = shotMode;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f3153a;
        Integer num = (Integer) obj;
        Boolean bool = (Boolean) obj2;
        ShotMode shotMode = this.b;
        switch (i2) {
            case 0:
                ShotMode.Builder.lambda$build$0(shotMode, num, bool);
                return;
            default:
                ShotMode.Builder.lambda$build$1(shotMode, num, bool);
                return;
        }
    }
}
