package com.samsung.android.gallery.module.data;

import android.content.Context;
import android.net.Uri;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f2934a;

    public /* synthetic */ s(Context context) {
        this.f2934a = context;
    }

    public final Object apply(Object obj) {
        return UriItemLoader.getSharingUriItem(this.f2934a, (Uri) obj);
    }
}
