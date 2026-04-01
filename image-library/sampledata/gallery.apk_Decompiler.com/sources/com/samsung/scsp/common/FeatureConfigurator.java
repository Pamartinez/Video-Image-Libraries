package com.samsung.scsp.common;

import D9.b;
import H3.i;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.samsung.scsp.error.FaultBarrier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FeatureConfigurator {
    private static final String CONFIGURATOR_PACKAGE = "com.samsung.android.scsp.configurator";
    private static final String HASH_VALUE = "7d0c5d6752ae6c77f730eb67179c4066c11a6523011a8b00d7b5d36240268d38";
    private static final Uri URI = Uri.parse("content://com.samsung.android.scsp.configurator/");

    private static boolean checkConfigurator(Context context) {
        return HASH_VALUE.equals((String) FaultBarrier.get(new b(context, 7), "", true).obj);
    }

    public static Bundle get(Context context, String str) {
        if (checkConfigurator(context)) {
            return (Bundle) FaultBarrier.get(new c(context, str, 0), Bundle.EMPTY, true).obj;
        }
        return Bundle.EMPTY;
    }

    public static JsonObject getJson(Context context, String str) {
        if (checkConfigurator(context)) {
            return (JsonObject) FaultBarrier.get(new c(context, str, 1), new JsonObject(), true).obj;
        }
        return new JsonObject();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$get$1(Context context, String str, boolean z) {
        Bundle call = context.getContentResolver().call(URI, "get", str, new Bundle());
        if (call != null) {
            return Boolean.valueOf(call.getBoolean(str, z));
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ JsonObject lambda$getJson$2(Context context, String str) {
        String string = context.getContentResolver().call(URI, "get_json", str, Bundle.EMPTY).getString("json");
        if (string != null) {
            return (JsonObject) new Gson().fromJson(string, JsonObject.class);
        }
        return new JsonObject();
    }

    public static boolean get(Context context, String str, boolean z) {
        return checkConfigurator(context) ? ((Boolean) FaultBarrier.get(new i((Object) context, (Object) str, z), Boolean.valueOf(z), true).obj).booleanValue() : z;
    }
}
