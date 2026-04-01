package com.samsung.android.sdk.spage.card;

import N2.j;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import com.samsung.android.sdk.spage.card.event.Event;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CardProvider extends ContentProvider {
    private static final String ACTION_AUTHORIZED = "com.samsung.android.app.spage.action.AUTHORIZED";
    private static final String ACTION_CARD_DISABLED = "com.samsung.android.app.spage.action.CARD_DISABLED";
    private static final String ACTION_CARD_ENABLED = "com.samsung.android.app.spage.action.CARD_ENABLED";
    private static final String ACTION_CARD_EVENT = "com.samsung.android.app.spage.action.CARD_EVENT";
    private static final String ACTION_CARD_INSTANT_UPDATE = "com.samsung.android.app.spage.action.CARD_INSTANT_UPDATE";
    private static final String ACTION_CARD_UPDATE = "com.samsung.android.app.spage.action.CARD_UPDATE";
    private static final String ACTION_MULTI_INSTANCE_PREFERENCE_UPDATE = "com.samsung.android.app.spage.action.MULTI_INSTANCE_PREFERENCE_UPDATE";
    private static final String ALLOWED_PACKAGE = "com.samsung.android.app.spage";
    private static final String EXTRA_CALL_INTENT = "callIntent";
    private static final String EXTRA_CARD_ID = "IdNo";
    private static final String EXTRA_EVENT = "event";
    private static final String EXTRA_UPDATE_CODE = "updateCode";
    private static final String PROVIDER_CALL_REQUEST = "callRequest";
    private static final int SYSTEM_APP_MASK = 129;
    private static final String TAG = "CardProvider";

    private static String getCallingPackageName(Context context) {
        String[] packagesForUid;
        int callingUid = Binder.getCallingUid();
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null || (packagesForUid = packageManager.getPackagesForUid(callingUid)) == null || packagesForUid.length <= 0) {
            return null;
        }
        return packagesForUid[0];
    }

    private boolean isAllowed(Context context, String str) {
        if (ALLOWED_PACKAGE.equals(str)) {
            String str2 = Build.TYPE;
            if ("eng".equals(str2) || "userdebug".equals(str2)) {
                try {
                    ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
                    if (applicationInfo == null || (applicationInfo.flags & 129) == 0) {
                        return false;
                    }
                    return true;
                } catch (PackageManager.NameNotFoundException unused) {
                    j.w("NameNotFoundException ", str, TAG);
                    return false;
                }
            } else {
                try {
                    for (Signature byteArray : context.getPackageManager().getPackageInfo(str, 64).signatures) {
                        MessageDigest instance = MessageDigest.getInstance("SHA");
                        instance.update(byteArray.toByteArray());
                        String encodeToString = Base64.encodeToString(instance.digest(), 2);
                        if ("nKUXDzgZGd/gRG/NqxixmhQ7MWM=".equals(encodeToString) || "KcZHy8yaX71sDJYeBXEr0VNSofU=".equals(encodeToString)) {
                            return true;
                        }
                    }
                } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException unused2) {
                    j.w("NameNotFoundException/NoSuchAlgorithmException", str, TAG);
                    return false;
                }
            }
        }
        j.w("Not allowed package ", str, TAG);
        return false;
    }

    public final Bundle call(String str, String str2, Bundle bundle) {
        if (PROVIDER_CALL_REQUEST.equals(str) && bundle != null) {
            Log.d(TAG, "onReceive executing call method");
            Context context = getContext();
            if (isAllowed(context, getCallingPackageName(context))) {
                Intent intent = (Intent) bundle.getParcelable(EXTRA_CALL_INTENT);
                if (intent == null) {
                    return null;
                }
                String action = intent.getAction();
                long clearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    if (ACTION_CARD_UPDATE.equals(action)) {
                        Log.d(TAG, "onReceive onUpdate");
                        onUpdate(context, CardContentManager.getInstance(), intent.getIntArrayExtra(EXTRA_CARD_ID));
                    } else if (ACTION_CARD_ENABLED.equals(action)) {
                        Log.d(TAG, "onReceive onEnabled");
                        onEnabled(context, intent.getIntArrayExtra(EXTRA_CARD_ID));
                    } else if (ACTION_CARD_DISABLED.equals(action)) {
                        Log.d(TAG, "onReceive onDisabled");
                        onDisabled(context, intent.getIntArrayExtra(EXTRA_CARD_ID));
                    } else if (ACTION_CARD_EVENT.equals(action)) {
                        Log.d(TAG, "onReceive newEvent");
                        Bundle extras = intent.getExtras();
                        if (extras == null) {
                            Binder.restoreCallingIdentity(clearCallingIdentity);
                            return null;
                        }
                        Event newEvent = Event.newEvent(extras);
                        if (newEvent != null) {
                            onReceiveEvent(context, CardContentManager.getInstance(), intent.getIntExtra(EXTRA_CARD_ID, -1), newEvent);
                        }
                    } else if (ACTION_CARD_INSTANT_UPDATE.equals(action)) {
                        Log.d(TAG, "onReceive Instant update");
                        int intExtra = intent.getIntExtra(EXTRA_UPDATE_CODE, 0);
                        if (intExtra != 0) {
                            onInstantUpdate(context, CardContentManager.getInstance(), intent.getIntExtra(EXTRA_CARD_ID, -1), intExtra);
                        } else {
                            Log.e(TAG, "wrong update code - zero");
                        }
                    } else if (ACTION_MULTI_INSTANCE_PREFERENCE_UPDATE.equals(action)) {
                        Log.d(TAG, "onReceive onPreferenceRequested");
                        onPreferenceRequested(context, CardContentManager.getInstance(), intent.getIntExtra(EXTRA_CARD_ID, -1));
                    }
                    return bundle;
                } finally {
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            }
        }
        return bundle;
    }

    public final int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public final String getType(Uri uri) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public final Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public final boolean onCreate() {
        return true;
    }

    public abstract void onUpdate(Context context, CardContentManager cardContentManager, int[] iArr);

    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void onDisabled(Context context, int[] iArr) {
    }

    public void onEnabled(Context context, int[] iArr) {
    }

    public void onPreferenceRequested(Context context, CardContentManager cardContentManager, int i2) {
    }

    public void onInstantUpdate(Context context, CardContentManager cardContentManager, int i2, int i7) {
    }

    public void onReceiveEvent(Context context, CardContentManager cardContentManager, int i2, Event event) {
    }
}
