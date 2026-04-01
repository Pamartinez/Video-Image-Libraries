package com.google.android.material.appbar.model;

import He.C0748d;
import He.C0751g;
import T1.a;
import android.content.Context;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1194l;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003:\u0001\u000eB\u001d\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\fR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/google/android/material/appbar/model/AppBarModel;", "LT1/a;", "T", "", "LHe/d;", "kclazz", "Landroid/content/Context;", "context", "<init>", "(LHe/d;Landroid/content/Context;)V", "create", "()LT1/a;", "LHe/d;", "Landroid/content/Context;", "OnClickListener", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AppBarModel<T extends a> {
    private final Context context;
    private final C0748d kclazz;

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002À\u0006\u0001"}, d2 = {"Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;", "", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnClickListener {
        void a(View view, AppBarModel appBarModel);
    }

    public AppBarModel(C0748d dVar, Context context2) {
        j.e(dVar, "kclazz");
        j.e(context2, "context");
        this.kclazz = dVar;
        this.context = context2;
    }

    public T create() {
        return init((a) ((C0751g) C1194l.K0(this.kclazz.d())).call(this.context, null));
    }

    public a init(a aVar) {
        j.e(aVar, "moduleView");
        return aVar;
    }
}
