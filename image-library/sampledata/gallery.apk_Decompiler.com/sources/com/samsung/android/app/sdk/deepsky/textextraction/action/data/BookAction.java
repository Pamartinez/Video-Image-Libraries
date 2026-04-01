package com.samsung.android.app.sdk.deepsky.textextraction.action.data;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.samsung.android.app.sdk.deepsky.textextraction.R$drawable;
import com.samsung.android.app.sdk.deepsky.textextraction.R$string;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0001\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0014\u0010\n\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/BookAction;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/Action;", "context", "Landroid/content/Context;", "imageUri", "Landroid/net/Uri;", "info", "Lcom/google/gson/JsonObject;", "<init>", "(Landroid/content/Context;Landroid/net/Uri;Lcom/google/gson/JsonObject;)V", "iconUri", "getIconUri", "()Landroid/net/Uri;", "title", "", "getTitle", "()Ljava/lang/String;", "runAction", "", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BookAction extends Action {
    public static final Companion Companion = new Companion((e) null);
    private final Uri iconUri;
    private final String title;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/BookAction$Companion;", "", "<init>", "()V", "TAG", "", "JSON_KEY_NAME", "GOOGLE_SEARCH_QUERY", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BookAction(Context context, Uri uri, JsonObject jsonObject) {
        super(context, uri, jsonObject);
        j.e(context, "context");
        j.e(uri, "imageUri");
        j.e(jsonObject, "info");
        this.iconUri = getResourceUri(R$drawable.capsule_book, context);
        String string = context.getString(R$string.add_to_internet);
        j.d(string, "getString(...)");
        this.title = string;
    }

    public Uri getIconUri() {
        return this.iconUri;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean runAction() {
        String str;
        LibLogger.i("BookAction", "runAction");
        JsonElement jsonElement = getInfo().get("Name");
        if (jsonElement == null || (str = jsonElement.getAsString()) == null) {
            str = "책 정보";
        }
        getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.google.com/search?q=".concat(str))));
        return true;
    }
}
