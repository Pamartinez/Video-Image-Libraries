package com.samsung.android.app.sdk.deepsky.textextraction.action.data;

import android.content.Intent;
import android.net.Uri;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.gson.JsonElement;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0014\u0010\n\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/LocationAction;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/Action;", "context", "Landroid/content/Context;", "imageUri", "Landroid/net/Uri;", "info", "Lcom/google/gson/JsonObject;", "<init>", "(Landroid/content/Context;Landroid/net/Uri;Lcom/google/gson/JsonObject;)V", "iconUri", "getIconUri", "()Landroid/net/Uri;", "title", "", "getTitle", "()Ljava/lang/String;", "placeName", "runAction", "", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LocationAction extends Action {
    public static final Companion Companion = new Companion((e) null);
    private final Uri iconUri;
    private final String placeName;
    private final String title;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/LocationAction$Companion;", "", "<init>", "()V", "TAG", "", "JSON_KEY_ADDRESS", "JSON_KEY_PLACE_NAME", "MAP_URI", "NA", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0049, code lost:
        if (r2 == null) goto L_0x004b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LocationAction(android.content.Context r2, android.net.Uri r3, com.google.gson.JsonObject r4) {
        /*
            r1 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.j.e(r2, r0)
            java.lang.String r0 = "imageUri"
            kotlin.jvm.internal.j.e(r3, r0)
            java.lang.String r0 = "info"
            kotlin.jvm.internal.j.e(r4, r0)
            r1.<init>(r2, r3, r4)
            int r3 = com.samsung.android.app.sdk.deepsky.textextraction.R$drawable.capsule_location
            android.net.Uri r3 = r1.getResourceUri(r3, r2)
            r1.iconUri = r3
            int r3 = com.samsung.android.app.sdk.deepsky.textextraction.R$string.show_on_map
            java.lang.String r2 = r2.getString(r3)
            java.lang.String r3 = "getString(...)"
            kotlin.jvm.internal.j.d(r2, r3)
            r1.title = r2
            java.lang.String r2 = "PlaceName"
            com.google.gson.JsonElement r2 = r4.get(r2)
            if (r2 == 0) goto L_0x004b
            java.lang.String r2 = r2.getAsString()
            if (r2 == 0) goto L_0x004b
            java.lang.CharSequence r2 = Tf.n.R0(r2)
            java.lang.String r2 = r2.toString()
            if (r2 == 0) goto L_0x004b
            java.lang.String r3 = "N/A"
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto L_0x0048
            goto L_0x0049
        L_0x0048:
            r2 = 0
        L_0x0049:
            if (r2 != 0) goto L_0x004d
        L_0x004b:
            java.lang.String r2 = ""
        L_0x004d:
            r1.placeName = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.action.data.LocationAction.<init>(android.content.Context, android.net.Uri, com.google.gson.JsonObject):void");
    }

    public Uri getIconUri() {
        return this.iconUri;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean runAction() {
        String asString;
        LibLogger.i("LocationAction", "runAction");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.placeName);
        JsonElement jsonElement = getInfo().get("Address");
        if (jsonElement != null && (asString = jsonElement.getAsString()) != null && asString.length() > 0 && !asString.equals("N/A")) {
            if (sb2.length() > 0) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
            sb2.append(asString);
        }
        if (sb2.length() <= 0) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("geo:0,0?q=" + sb2));
        intent.setFlags(268435456);
        getContext().startActivity(intent);
        return true;
    }
}
