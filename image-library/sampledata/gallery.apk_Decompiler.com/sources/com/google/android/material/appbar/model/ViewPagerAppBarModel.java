package com.google.android.material.appbar.model;

import He.C0748d;
import T1.a;
import android.content.Context;
import com.google.android.material.appbar.model.view.ViewPagerAppBarView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003:\u0001\u0011B5\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0016\b\u0002\u0010\n\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\t0\u00030\b¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00028\u00002\u0006\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\"\u0010\n\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\t0\u00030\b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0010¨\u0006\u0012"}, d2 = {"Lcom/google/android/material/appbar/model/ViewPagerAppBarModel;", "Lcom/google/android/material/appbar/model/view/ViewPagerAppBarView;", "T", "Lcom/google/android/material/appbar/model/AppBarModel;", "LHe/d;", "kclazz", "Landroid/content/Context;", "context", "", "LT1/a;", "appBarModels", "<init>", "(LHe/d;Landroid/content/Context;Ljava/util/List;)V", "moduleView", "init", "(Lcom/google/android/material/appbar/model/view/ViewPagerAppBarView;)Lcom/google/android/material/appbar/model/view/ViewPagerAppBarView;", "Ljava/util/List;", "Builder", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewPagerAppBarModel<T extends ViewPagerAppBarView> extends AppBarModel<T> {
    private final List<AppBarModel<? extends a>> appBarModels;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/google/android/material/appbar/model/ViewPagerAppBarModel$Builder;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        public Builder(Context context) {
            j.e(context, "context");
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ViewPagerAppBarModel(C0748d dVar, Context context, List list, int i2, e eVar) {
        this(dVar, context, (i2 & 4) != 0 ? C1202t.d : list);
    }

    public T init(T t) {
        j.e(t, "moduleView");
        return t;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ViewPagerAppBarModel(C0748d dVar, Context context, List<? extends AppBarModel<? extends a>> list) {
        super(dVar, context);
        j.e(dVar, "kclazz");
        j.e(context, "context");
        j.e(list, "appBarModels");
        this.appBarModels = list;
    }
}
