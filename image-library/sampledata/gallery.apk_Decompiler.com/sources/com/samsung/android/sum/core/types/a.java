package com.samsung.android.sum.core.types;

import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import java.lang.reflect.Method;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4144a;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f4145c;

    public /* synthetic */ a(Object obj, int i2, int i7) {
        this.f4144a = i7;
        this.f4145c = obj;
        this.b = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f4144a) {
            case 0:
                return NumericEnum.lambda$fromValue$0((Method) this.f4145c, this.b, obj);
            default:
                return ((PicturesViewAdapter) this.f4145c).lambda$backupSelectedPositions$2(this.b, (Integer) obj);
        }
    }
}
