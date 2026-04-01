package com.samsung.android.app.sdk.deepsky.textextraction.action.data;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import com.google.gson.JsonObject;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b \u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0019\u001a\u00020\u0017H&J\u0014\u0010\u001a\u001a\u00020\u0005*\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\rR\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u0017XD¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0018¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/Action;", "", "context", "Landroid/content/Context;", "imageUri", "Landroid/net/Uri;", "info", "Lcom/google/gson/JsonObject;", "<init>", "(Landroid/content/Context;Landroid/net/Uri;Lcom/google/gson/JsonObject;)V", "getContext", "()Landroid/content/Context;", "getImageUri", "()Landroid/net/Uri;", "getInfo", "()Lcom/google/gson/JsonObject;", "iconUri", "getIconUri", "title", "", "getTitle", "()Ljava/lang/String;", "isSupportAction", "", "()Z", "runAction", "getResourceUri", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Action {
    private final Context context;
    private final Uri imageUri;
    private final JsonObject info;
    private final boolean isSupportAction = true;

    public Action(Context context2, Uri uri, JsonObject jsonObject) {
        j.e(context2, "context");
        j.e(uri, "imageUri");
        j.e(jsonObject, "info");
        this.context = context2;
        this.imageUri = uri;
        this.info = jsonObject;
    }

    public final Context getContext() {
        return this.context;
    }

    public abstract Uri getIconUri();

    public final Uri getImageUri() {
        return this.imageUri;
    }

    public final JsonObject getInfo() {
        return this.info;
    }

    public final Uri getResourceUri(int i2, Context context2) {
        Uri build;
        j.e(context2, "context");
        Resources resources = context2.getResources();
        if (resources != null && (build = new Uri.Builder().scheme("android.resource").authority(resources.getResourcePackageName(i2)).appendPath(resources.getResourceTypeName(i2)).appendPath(resources.getResourceEntryName(i2)).build()) != null) {
            return build;
        }
        Uri uri = Uri.EMPTY;
        j.d(uri, "EMPTY");
        return uri;
    }

    public abstract String getTitle();

    public boolean isSupportAction() {
        return this.isSupportAction;
    }

    public abstract boolean runAction();
}
