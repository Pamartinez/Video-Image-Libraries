package com.samsung.android.app.sdk.deepsky.barcode.action.common;

import L2.a;
import Tf.v;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.SemSystemProperties;
import com.samsung.android.app.sdk.deepsky.barcode.logger.LibLogger;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.k;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0010\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0012\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0014\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0014\u0010\u0013J\u0015\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0004¢\u0006\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/common/ActionUtil;", "", "<init>", "()V", "", "uri", "getLowerCaseProtocolUri", "(Ljava/lang/String;)Ljava/lang/String;", "Landroid/content/Context;", "context", "Landroid/content/Intent;", "intent", "", "isLauncherActivityAvailable", "(Landroid/content/Context;Landroid/content/Intent;)Z", "packageName", "isPackageInstalled", "(Landroid/content/Context;Ljava/lang/String;)Z", "isSamsungPayIndiaSupported", "(Landroid/content/Context;)Z", "isSamsungPayIndonesiaSupported", "uriString", "getLaunchBrowserIntent", "(Ljava/lang/String;)Landroid/content/Intent;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ActionUtil {
    public static final ActionUtil INSTANCE = new ActionUtil();

    private ActionUtil() {
    }

    private final String getLowerCaseProtocolUri(String str) {
        Locale locale = Locale.getDefault();
        j.d(locale, "getDefault(...)");
        String lowerCase = str.toLowerCase(locale);
        j.d(lowerCase, "toLowerCase(...)");
        if (v.t0(lowerCase, "http:")) {
            String substring = str.substring(5);
            j.d(substring, "substring(...)");
            return "http:".concat(substring);
        }
        Locale locale2 = Locale.getDefault();
        j.d(locale2, "getDefault(...)");
        String lowerCase2 = str.toLowerCase(locale2);
        j.d(lowerCase2, "toLowerCase(...)");
        if (!v.t0(lowerCase2, "https:")) {
            return str;
        }
        String substring2 = str.substring(6);
        j.d(substring2, "substring(...)");
        return "https:".concat(substring2);
    }

    public final Intent getLaunchBrowserIntent(String str) {
        j.e(str, "uriString");
        String lowerCaseProtocolUri = getLowerCaseProtocolUri(str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.setData(Uri.parse(lowerCaseProtocolUri));
        return intent;
    }

    public final boolean isLauncherActivityAvailable(Context context, Intent intent) {
        j.e(context, "context");
        j.e(intent, "intent");
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        j.d(queryIntentActivities, "queryIntentActivities(...)");
        return !queryIntentActivities.isEmpty();
    }

    public final boolean isPackageInstalled(Context context, String str) {
        j.e(context, "context");
        j.e(str, MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME);
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (Throwable th) {
            if (k.a(a.l(th)) == null) {
                return false;
            }
            LibLogger.i("ActionUtil", str.concat(" is not installed"));
            return false;
        }
    }

    public final boolean isSamsungPayIndiaSupported(Context context) {
        j.e(context, "context");
        if (!"IN".equals(SemSystemProperties.getCountryIso())) {
            return false;
        }
        if (isPackageInstalled(context, "com.samsung.android.spay") || isPackageInstalled(context, "com.samsung.android.spaymini")) {
            return true;
        }
        return false;
    }

    public final boolean isSamsungPayIndonesiaSupported(Context context) {
        j.e(context, "context");
        return isPackageInstalled(context, "com.samsung.android.rajaampat");
    }
}
