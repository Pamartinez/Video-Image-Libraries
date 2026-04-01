package com.samsung.android.sdk.moneta.memory.util;

import android.os.Bundle;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PlaceBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.content.Content;
import com.samsung.android.sdk.moneta.memory.entity.context.Place;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1196n;
import ne.C1202t;

@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0019\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005\u001a\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\b\u001a\u0019\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u000b\u001a\u0019\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u000e\u001a\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0011\u001a\u0010\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013*\u00020\u0002\u001a\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0017"}, d2 = {"getBooleanOrNull", "", "Landroid/os/Bundle;", "key", "", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/Boolean;", "getIntOrNull", "", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/Integer;", "getLongOrNull", "", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/Long;", "getFloatOrNull", "", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/Float;", "getDoubleOrNull", "", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/Double;", "getContents", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "getPlaceOrNull", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "pde-sdk-1.0.40_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BundleGetExtensionKt {
    public static final Boolean getBooleanOrNull(Bundle bundle, String str) {
        j.e(bundle, "<this>");
        j.e(str, "key");
        if (bundle.containsKey(str)) {
            return Boolean.valueOf(bundle.getBoolean(str));
        }
        return null;
    }

    public static final List<Content> getContents(Bundle bundle) {
        j.e(bundle, "<this>");
        ArrayList<ContentWrapper> C5 = bundle.getParcelableArrayList("contents", ContentWrapper.class);
        if (C5 == null) {
            return C1202t.d;
        }
        ArrayList arrayList = new ArrayList(C1196n.w0(C5, 10));
        for (ContentWrapper content : C5) {
            arrayList.add(content.toContent());
        }
        return arrayList;
    }

    public static final Double getDoubleOrNull(Bundle bundle, String str) {
        j.e(bundle, "<this>");
        j.e(str, "key");
        if (bundle.containsKey(str)) {
            return Double.valueOf(bundle.getDouble(str));
        }
        return null;
    }

    public static final Float getFloatOrNull(Bundle bundle, String str) {
        j.e(bundle, "<this>");
        j.e(str, "key");
        if (bundle.containsKey(str)) {
            return Float.valueOf(bundle.getFloat(str));
        }
        return null;
    }

    public static final Integer getIntOrNull(Bundle bundle, String str) {
        j.e(bundle, "<this>");
        j.e(str, "key");
        if (bundle.containsKey(str)) {
            return Integer.valueOf(bundle.getInt(str));
        }
        return null;
    }

    public static final Long getLongOrNull(Bundle bundle, String str) {
        j.e(bundle, "<this>");
        j.e(str, "key");
        if (bundle.containsKey(str)) {
            return Long.valueOf(bundle.getLong(str));
        }
        return null;
    }

    public static final Place getPlaceOrNull(Bundle bundle, String str) {
        j.e(bundle, "<this>");
        j.e(str, "key");
        PlaceBundleWrapper placeBundleWrapper = (PlaceBundleWrapper) bundle.getParcelable(str, PlaceBundleWrapper.class);
        if (placeBundleWrapper != null) {
            return placeBundleWrapper.toContext();
        }
        return null;
    }
}
