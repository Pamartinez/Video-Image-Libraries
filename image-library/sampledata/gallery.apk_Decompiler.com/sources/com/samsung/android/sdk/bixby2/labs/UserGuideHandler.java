package com.samsung.android.sdk.bixby2.labs;

import D1.f;
import Tf.a;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.sdk.bixby2.LogUtil;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.k;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/sdk/bixby2/labs/UserGuideHandler;", "", "()V", "ACTION_GET_USER_GUIDE", "", "DEFAULT_FILE_NAME", "KEY_METADATA_FILE_NAME", "KEY_USER_GUIDE_DATA", "TAG", "getUserGuide", "context", "Landroid/content/Context;", "extras", "Landroid/os/Bundle;", "getUserGuideFileName", "bixbyappsdk_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UserGuideHandler {
    public static final String ACTION_GET_USER_GUIDE = "bixby_getUserGuide";
    private static final String DEFAULT_FILE_NAME = "interactive_user_guide.json";
    public static final UserGuideHandler INSTANCE = new UserGuideHandler();
    private static final String KEY_METADATA_FILE_NAME = "com.samsung.android.sdk.bixby2.userGuideFileName";
    public static final String KEY_USER_GUIDE_DATA = "userGuideData";
    private static final String TAG = "UserGuideHandler";

    private UserGuideHandler() {
    }

    private final String getUserGuideFileName(Context context) {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        j.d(applicationInfo, "getApplicationInfo(...)");
        String string = applicationInfo.metaData.getString(KEY_METADATA_FILE_NAME, DEFAULT_FILE_NAME);
        LogUtil.i(TAG, "UserGuide fileName : " + string);
        j.d(string, "also(...)");
        return string;
    }

    public final String getUserGuide(Context context, Bundle bundle) {
        Object obj;
        j.e(context, "context");
        j.e(bundle, "extras");
        LogUtil.i(TAG, "getUserGuide");
        try {
            InputStream open = context.getAssets().open(getUserGuideFileName(context));
            j.d(open, "open(...)");
            obj = f.K(new BufferedReader(new InputStreamReader(open, a.f3815a), SerializeOptions.SORT));
        } catch (Throwable th) {
            obj = L2.a.l(th);
        }
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            LogUtil.e(TAG, "exception - " + a7);
        }
        if (obj instanceof me.j) {
            obj = "";
        }
        return (String) obj;
    }
}
