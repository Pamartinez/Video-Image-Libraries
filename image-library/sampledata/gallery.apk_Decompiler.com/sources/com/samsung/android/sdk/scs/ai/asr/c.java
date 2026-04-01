package com.samsung.android.sdk.scs.ai.asr;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1638a;
    public final /* synthetic */ Uri b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f1639c;
    public final /* synthetic */ String d;
    public final /* synthetic */ Bundle e;

    public /* synthetic */ c(Uri uri, String str, String str2, Bundle bundle, int i2) {
        this.f1638a = i2;
        this.b = uri;
        this.f1639c = str;
        this.d = str2;
        this.e = bundle;
    }

    public final Object apply(Object obj) {
        switch (this.f1638a) {
            case 0:
                return ((ContentResolver) obj).call(this.b, this.f1639c, this.d, this.e);
            default:
                return ((ContentResolver) obj).call(this.b, this.f1639c, this.d, this.e);
        }
    }
}
