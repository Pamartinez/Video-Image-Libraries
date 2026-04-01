package com.samsung.android.app.sdk.deepsky.textextraction.action.data;

import android.content.Context;
import android.net.Uri;
import com.google.gson.JsonObject;
import com.samsung.android.sdk.moneta.memory.entity.content.KeywordInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u00048\u0016X\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\b8\u0016XD¢\u0006\f\n\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0018\u001a\u00020\f8\u0016XD¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0018\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/UnclassifiedAction;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/Action;", "Landroid/content/Context;", "context", "Landroid/net/Uri;", "imageUri", "Lcom/google/gson/JsonObject;", "info", "", "reason", "<init>", "(Landroid/content/Context;Landroid/net/Uri;Lcom/google/gson/JsonObject;Ljava/lang/String;)V", "", "runAction", "()Z", "Ljava/lang/String;", "getReason", "()Ljava/lang/String;", "iconUri", "Landroid/net/Uri;", "getIconUri", "()Landroid/net/Uri;", "title", "getTitle", "isSupportAction", "Z", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UnclassifiedAction extends Action {
    private final Uri iconUri;
    private final boolean isSupportAction;
    private final String reason;
    private final String title = "";

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UnclassifiedAction(Context context, Uri uri, JsonObject jsonObject, String str) {
        super(context, uri, jsonObject);
        j.e(context, "context");
        j.e(uri, "imageUri");
        j.e(jsonObject, "info");
        j.e(str, KeywordInfo.EXTRA_BUNDLE_KEY_REASON);
        this.reason = str;
        Uri uri2 = Uri.EMPTY;
        j.d(uri2, "EMPTY");
        this.iconUri = uri2;
    }

    public Uri getIconUri() {
        return this.iconUri;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isSupportAction() {
        return this.isSupportAction;
    }

    public boolean runAction() {
        throw new UnsupportedOperationException("Not supported");
    }
}
