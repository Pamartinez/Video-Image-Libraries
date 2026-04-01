package com.samsung.android.app.sdk.deepsky.common;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.samsung.android.app.sdk.deepsky.contract.DeepSkyMethod;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\fH\u0016J\"\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\n \t*\u0004\u0018\u00010\u00030\u0003X\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/common/ContentProviderCallerImpl;", "Lcom/samsung/android/app/sdk/deepsky/common/ContentProviderCaller;", "context", "Landroid/content/Context;", "tag", "", "<init>", "(Landroid/content/Context;Ljava/lang/String;)V", "applicationContext", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "sendCommand", "Landroid/os/Bundle;", "method", "bundle", "uri", "Landroid/net/Uri;", "isContentProviderSupported", "", "Companion", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ContentProviderCallerImpl implements ContentProviderCaller {
    public static final Companion Companion = new Companion((e) null);
    private final Context applicationContext;
    private final String tag;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/common/ContentProviderCallerImpl$Companion;", "", "<init>", "()V", "TAG", "", "URI", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public ContentProviderCallerImpl(Context context, String str) {
        j.e(context, "context");
        j.e(str, "tag");
        this.tag = str;
        this.applicationContext = context.getApplicationContext();
    }

    public boolean isContentProviderSupported() {
        try {
            ContentProviderClient acquireUnstableContentProviderClient = this.applicationContext.getContentResolver().acquireUnstableContentProviderClient(Uri.parse(DeepSkyMethod.URI));
            if (acquireUnstableContentProviderClient == null) {
                return false;
            }
            Log.i("Requester", "isContentProviderSupported = true");
            acquireUnstableContentProviderClient.close();
            return true;
        } catch (Exception e) {
            Log.e("Requester", e.toString());
            return false;
        }
    }

    public Bundle sendCommand(String str, Bundle bundle) {
        j.e(str, "method");
        j.e(bundle, "bundle");
        Uri parse = Uri.parse(DeepSkyMethod.URI);
        j.d(parse, "parse(...)");
        return sendCommand(parse, str, bundle);
    }

    public Bundle sendCommand(Uri uri, String str, Bundle bundle) {
        j.e(uri, OCRServiceConstant.KEY_PARAM_URI);
        j.e(str, "method");
        j.e(bundle, "bundle");
        try {
            ContentProviderClient acquireUnstableContentProviderClient = this.applicationContext.getContentResolver().acquireUnstableContentProviderClient(uri);
            if (acquireUnstableContentProviderClient == null) {
                return null;
            }
            String str2 = this.tag;
            String packageName = this.applicationContext.getPackageName();
            Log.d("Requester", "[" + str2 + "] packageName = " + packageName);
            return acquireUnstableContentProviderClient.call(str, this.applicationContext.getPackageName(), bundle);
        } catch (Exception e) {
            String str3 = this.tag;
            Log.e("Requester", "[" + str3 + "] sendCommand = " + e);
            return null;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ContentProviderCallerImpl(Context context, String str, int i2, e eVar) {
        this(context, (i2 & 2) != 0 ? "" : str);
    }
}
