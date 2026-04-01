package com.samsung.android.app.sdk.deepsky.textextraction.action.data;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.samsung.android.app.sdk.deepsky.textextraction.R$drawable;
import com.samsung.android.app.sdk.deepsky.textextraction.R$string;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.util.PackageHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0001\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0014\u0010\n\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/ShoppingAction;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/Action;", "context", "Landroid/content/Context;", "imageUri", "Landroid/net/Uri;", "info", "Lcom/google/gson/JsonObject;", "<init>", "(Landroid/content/Context;Landroid/net/Uri;Lcom/google/gson/JsonObject;)V", "iconUri", "getIconUri", "()Landroid/net/Uri;", "title", "", "getTitle", "()Ljava/lang/String;", "targetPackage", "runAction", "", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ShoppingAction extends Action {
    public static final Companion Companion = new Companion((e) null);
    private final Uri iconUri;
    private final String targetPackage;
    private final String title;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/ShoppingAction$Companion;", "", "<init>", "()V", "TAG", "", "REMINDER_PACKAGE_NAME", "JSON_KEY_BRAND_NAME", "JSON_KEY_PRODUCT_NAME", "JSON_KEY_OFFERING_PRICE", "JSON_KEY_WHERE_TO_BUY_IT", "TYPE_TEXT_PLAIN", "NA", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ShoppingAction(Context context, Uri uri, JsonObject jsonObject) {
        super(context, uri, jsonObject);
        j.e(context, "context");
        j.e(uri, "imageUri");
        j.e(jsonObject, "info");
        this.iconUri = getResourceUri(R$drawable.capsule_shopping, context);
        String string = context.getString(R$string.entity_capsule_title_add_reminder);
        j.d(string, "getString(...)");
        this.title = string;
        String str = "com.samsung.android.app.reminder";
        this.targetPackage = !PackageHelper.INSTANCE.isPackageExists(context, str) ? "" : str;
    }

    public Uri getIconUri() {
        return this.iconUri;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean runAction() {
        String asString;
        String asString2;
        String asString3;
        String asString4;
        LibLogger.i("ShoppingAction", "runAction");
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        JsonObject info = getInfo();
        JsonElement jsonElement = info.get("BrandName");
        if (jsonElement != null && (asString4 = jsonElement.getAsString()) != null && asString4.length() > 0 && !asString4.equals("N/A")) {
            sb2.append(asString4);
        }
        JsonElement jsonElement2 = info.get("ProductName");
        if (!(jsonElement2 == null || (asString3 = jsonElement2.getAsString()) == null)) {
            if (sb2.length() > 0) {
                sb2.append(" ");
            }
            if (asString3.length() > 0 && !asString3.equals("N/A")) {
                sb2.append(asString3);
            }
        }
        JsonElement jsonElement3 = info.get("OfferingPrice");
        if (jsonElement3 != null && (asString2 = jsonElement3.getAsString()) != null && asString2.length() > 0 && !asString2.equals("N/A")) {
            sb3.append("Price: ");
            sb3.append(asString2);
        }
        JsonElement jsonElement4 = info.get("WhereToBuyIt");
        if (!(jsonElement4 == null || (asString = jsonElement4.getAsString()) == null)) {
            if (sb3.length() > 0) {
                sb3.append("\n");
            }
            if (asString.length() > 0 && !asString.equals("N/A")) {
                sb3.append("Where to buy: ");
                sb3.append(asString);
            }
        }
        if (sb3.length() > 0) {
            sb3.append("\n\n");
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.setFlags(268435456);
        if (sb2.length() > 0) {
            intent.putExtra("android.intent.extra.SUBJECT", sb2.toString());
            intent.putExtra("android.intent.extra.TEXT", sb3.toString());
        } else {
            intent.putExtra("android.intent.extra.TEXT", sb3.toString());
        }
        intent.putExtra("android.intent.extra.STREAM", getImageUri());
        intent.setPackage(this.targetPackage);
        getContext().startActivity(intent);
        return true;
    }
}
