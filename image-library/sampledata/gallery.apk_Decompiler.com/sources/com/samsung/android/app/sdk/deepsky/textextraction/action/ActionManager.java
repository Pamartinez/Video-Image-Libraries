package com.samsung.android.app.sdk.deepsky.textextraction.action;

import android.content.Context;
import android.net.Uri;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.samsung.android.app.sdk.deepsky.textextraction.action.data.Action;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1192j;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\n¢\u0006\u0004\b\u0012\u0010\u000eJ\r\u0010\u0013\u001a\u00020\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\n8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c¨\u0006\u001e"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/ActionManager;", "", "Landroid/content/Context;", "context", "Landroid/net/Uri;", "imageUri", "Lcom/google/gson/JsonObject;", "data", "<init>", "(Landroid/content/Context;Landroid/net/Uri;Lcom/google/gson/JsonObject;)V", "", "extractClassification", "(Lcom/google/gson/JsonObject;)Ljava/lang/String;", "getClassification", "()Ljava/lang/String;", "", "isSupportAction", "()Z", "getTitle", "getIconUri", "()Landroid/net/Uri;", "Lme/x;", "runAction", "()V", "classification", "Ljava/lang/String;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/Action;", "targetAction", "Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/Action;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ActionManager {
    public static final Companion Companion = new Companion((e) null);
    private final String classification;
    private final Action targetAction;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/ActionManager$Companion;", "", "<init>", "()V", "TAG", "", "JSON_KEY_CLASSIFICATION", "isValidClassification", "", "classification", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean isValidClassification(String str) {
            j.e(str, "classification");
            return C1192j.d0(ActionConstants.INSTANCE.getACTION_CATEGORY_LIST(), str);
        }

        private Companion() {
        }
    }

    public ActionManager(Context context, Uri uri, JsonObject jsonObject) {
        JsonObject jsonObject2;
        j.e(context, "context");
        j.e(uri, "imageUri");
        j.e(jsonObject, "data");
        String extractClassification = extractClassification(jsonObject);
        this.classification = extractClassification;
        if (Companion.isValidClassification(extractClassification)) {
            JsonElement jsonElement = jsonObject.get(extractClassification);
            if (jsonElement == null || (jsonObject2 = jsonElement.getAsJsonObject()) == null) {
                jsonObject2 = new JsonObject();
                LibLogger.e("ActionManager", "Extracted data is null : " + jsonObject);
            }
        } else {
            LibLogger.i("ActionManager", "Invalid classification : " + extractClassification);
            jsonObject2 = new JsonObject();
        }
        this.targetAction = ActionFactory.INSTANCE.getAction(extractClassification, context, uri, jsonObject2);
    }

    private final String extractClassification(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("Classification");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LibLogger.i("ActionManager", "Classification is null or json null");
            return "Null";
        }
        String asString = jsonElement.getAsString();
        j.d(asString, "getAsString(...)");
        return asString;
    }

    public final String getClassification() {
        return this.classification;
    }

    public final Uri getIconUri() {
        return this.targetAction.getIconUri();
    }

    public final String getTitle() {
        return this.targetAction.getTitle();
    }

    public final boolean isSupportAction() {
        return this.targetAction.isSupportAction();
    }

    public final void runAction() {
        this.targetAction.runAction();
    }
}
