package w1;

import B1.a;
import B1.c;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import androidx.core.os.ConfigurationCompat;
import com.sec.android.gallery3d.R;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import t1.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class k {

    /* renamed from: a  reason: collision with root package name */
    public static final SimpleArrayMap f2009a = new SimpleArrayMap();
    public static Locale b;

    public static String a(Context context) {
        String packageName = context.getPackageName();
        try {
            Context context2 = (Context) c.a(context).e;
            return context2.getPackageManager().getApplicationLabel(context2.getPackageManager().getApplicationInfo(packageName, 0)).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            String str = context.getApplicationInfo().name;
            if (TextUtils.isEmpty(str)) {
                return packageName;
            }
            return str;
        }
    }

    public static String b(Context context, int i2) {
        Resources resources = context.getResources();
        if (i2 == 1) {
            return resources.getString(R.string.common_google_play_services_install_button);
        }
        if (i2 == 2) {
            return resources.getString(R.string.common_google_play_services_update_button);
        }
        if (i2 != 3) {
            return resources.getString(17039370);
        }
        return resources.getString(R.string.common_google_play_services_enable_button);
    }

    public static String c(Context context, int i2) {
        Resources resources = context.getResources();
        String a7 = a(context);
        if (i2 == 1) {
            return resources.getString(R.string.common_google_play_services_install_text, new Object[]{a7});
        }
        if (i2 != 2) {
            if (i2 == 3) {
                return resources.getString(R.string.common_google_play_services_enable_text, new Object[]{a7});
            }
            if (i2 == 5) {
                return e(context, "common_google_play_services_invalid_account_text", a7);
            }
            if (i2 == 7) {
                return e(context, "common_google_play_services_network_error_text", a7);
            }
            if (i2 == 9) {
                return resources.getString(R.string.common_google_play_services_unsupported_text, new Object[]{a7});
            }
            if (i2 == 20) {
                return e(context, "common_google_play_services_restricted_profile_text", a7);
            }
            switch (i2) {
                case 16:
                    return e(context, "common_google_play_services_api_unavailable_text", a7);
                case 17:
                    return e(context, "common_google_play_services_sign_in_failed_text", a7);
                case 18:
                    return resources.getString(R.string.common_google_play_services_updating_text, new Object[]{a7});
                default:
                    return resources.getString(R.string.common_google_play_services_unknown_issue, new Object[]{a7});
            }
        } else if (a.H(context)) {
            return resources.getString(R.string.common_google_play_services_wear_update_text);
        } else {
            return resources.getString(R.string.common_google_play_services_update_text, new Object[]{a7});
        }
    }

    public static String d(Context context, int i2) {
        Resources resources = context.getResources();
        switch (i2) {
            case 1:
                return resources.getString(R.string.common_google_play_services_install_title);
            case 2:
                return resources.getString(R.string.common_google_play_services_update_title);
            case 3:
                return resources.getString(R.string.common_google_play_services_enable_title);
            case 4:
            case 6:
            case 18:
                return null;
            case 5:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return f(context, "common_google_play_services_invalid_account_title");
            case 7:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return f(context, "common_google_play_services_network_error_title");
            case 8:
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            case 9:
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return null;
            case 10:
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            case 11:
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            case 16:
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            case 17:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return f(context, "common_google_play_services_sign_in_failed_title");
            case 20:
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return f(context, "common_google_play_services_restricted_profile_title");
            default:
                Log.e("GoogleApiAvailability", "Unexpected error code " + i2);
                return null;
        }
    }

    public static String e(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String f = f(context, str);
        if (f == null) {
            f = resources.getString(R.string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, f, new Object[]{str2});
    }

    public static String f(Context context, String str) {
        Resources resources;
        SimpleArrayMap simpleArrayMap = f2009a;
        synchronized (simpleArrayMap) {
            try {
                Locale locale = ConfigurationCompat.getLocales(context.getResources().getConfiguration()).get(0);
                if (!locale.equals(b)) {
                    simpleArrayMap.clear();
                    b = locale;
                }
                String str2 = (String) simpleArrayMap.get(str);
                if (str2 != null) {
                    return str2;
                }
                AtomicBoolean atomicBoolean = h.f1924a;
                try {
                    resources = context.getPackageManager().getResourcesForApplication("com.google.android.gms");
                } catch (PackageManager.NameNotFoundException unused) {
                    resources = null;
                }
                if (resources != null) {
                    int identifier = resources.getIdentifier(str, "string", "com.google.android.gms");
                    if (identifier == 0) {
                        Log.w("GoogleApiAvailability", "Missing resource: ".concat(str));
                    } else {
                        String string = resources.getString(identifier);
                        if (TextUtils.isEmpty(string)) {
                            Log.w("GoogleApiAvailability", "Got empty resource: ".concat(str));
                        } else {
                            f2009a.put(str, string);
                            return string;
                        }
                    }
                }
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
