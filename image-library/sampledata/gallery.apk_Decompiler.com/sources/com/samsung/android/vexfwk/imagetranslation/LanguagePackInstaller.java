package com.samsung.android.vexfwk.imagetranslation;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000bJ%\u0010\u000e\u001a\u00020\t2\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00060\fj\b\u0012\u0004\u0012\u00020\u0006`\r¢\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0010¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/vexfwk/imagetranslation/LanguagePackInstaller;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "locales", "label", "Lme/x;", "requestLangPackDownload", "(Ljava/lang/String;Ljava/lang/String;)V", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "goToLangPackDownload", "(Ljava/util/ArrayList;)V", "Landroid/content/Context;", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LanguagePackInstaller {
    public static final Companion Companion = new Companion((e) null);
    private static final String LANGUAGE_PACK_DOWNLOAD_ACTION = "com.samsung.android.settings.action.REQUEST_LANGUAGE_PACK_DOWNLOAD";
    public static final String LANGUAGE_PACK_SETTINGS_ACTION = "com.samsung.android.settings.action.LANGUAGE_PACK_SETTINGS";
    private static final String TAG = "LanguagePackInstaller";
    private final Context context;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/vexfwk/imagetranslation/LanguagePackInstaller$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "LANGUAGE_PACK_DOWNLOAD_ACTION", "LANGUAGE_PACK_SETTINGS_ACTION", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public LanguagePackInstaller(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    public final void goToLangPackDownload(ArrayList<String> arrayList) {
        j.e(arrayList, "locales");
        Context context2 = this.context;
        Intent intent = new Intent(LANGUAGE_PACK_SETTINGS_ACTION);
        intent.putExtra("package", this.context.getPackageName());
        intent.putStringArrayListExtra("locales", arrayList);
        context2.startActivity(intent);
        String str = TAG;
        Log.i(str, "requested goToLangPackDownload " + arrayList);
    }

    public final void requestLangPackDownload(String str, String str2) {
        j.e(str, "locales");
        j.e(str2, "label");
        Context context2 = this.context;
        Intent intent = new Intent(LANGUAGE_PACK_DOWNLOAD_ACTION);
        intent.putExtra("package", this.context.getPackageName());
        intent.putExtra("function", str2);
        intent.putExtra("locale", str);
        context2.startActivity(intent);
        Log.i(TAG, "requested LanguagePackDownload ".concat(str));
    }
}
