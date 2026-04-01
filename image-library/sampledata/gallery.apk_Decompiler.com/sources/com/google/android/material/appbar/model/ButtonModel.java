package com.google.android.material.appbar.model;

import com.google.android.material.appbar.model.AppBarModel;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B-\b\u0007\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/google/android/material/appbar/model/ButtonModel;", "", "", "text", "Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;", "clickListener", "contentDescription", "<init>", "(Ljava/lang/String;Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;Ljava/lang/String;)V", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ButtonModel {

    /* renamed from: a  reason: collision with root package name */
    public final String f1396a;
    public final AppBarModel.OnClickListener b;

    /* renamed from: c  reason: collision with root package name */
    public final String f1397c;

    public ButtonModel() {
        this((String) null, (AppBarModel.OnClickListener) null, (String) null, 7, (e) null);
    }

    public ButtonModel(String str) {
        this(str, (AppBarModel.OnClickListener) null, (String) null, 6, (e) null);
    }

    public ButtonModel(String str, AppBarModel.OnClickListener onClickListener) {
        this(str, onClickListener, (String) null, 4, (e) null);
    }

    public ButtonModel(String str, AppBarModel.OnClickListener onClickListener, String str2) {
        this.f1396a = str;
        this.b = onClickListener;
        this.f1397c = str2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ButtonModel(String str, AppBarModel.OnClickListener onClickListener, String str2, int i2, e eVar) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : onClickListener, (i2 & 4) != 0 ? null : str2);
    }
}
