package com.samsung.android.gallery.support.utils;

import A.a;
import N2.j;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AndroidCompat {
    private static final AndroidImpl sImpl;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AndroidImpl {
        public AndroidImpl() {
            Log.d("AndroidCompat", "construct ".concat(getClass().getSimpleName()));
        }

        public void registerReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
            context.registerReceiver(broadcastReceiver, intentFilter);
        }

        public void registerSystemReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
            context.registerReceiver(broadcastReceiver, intentFilter);
        }

        public void registerReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler) {
            context.registerReceiver(broadcastReceiver, intentFilter, str, handler);
        }
    }

    static {
        AndroidImpl androidImpl;
        if (Build.VERSION.SDK_INT >= 34) {
            androidImpl = new AndroidImpl34();
        } else {
            androidImpl = new AndroidImpl();
        }
        sImpl = androidImpl;
    }

    public static Bundle callSafe(Context context, Uri uri, String str, String str2, Bundle bundle) {
        ContentProviderClient acquireUnstableContentProviderClient;
        Bundle bundle2;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            acquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(uri);
            if (acquireUnstableContentProviderClient == null) {
                bundle2 = null;
            } else {
                bundle2 = acquireUnstableContentProviderClient.call(str, str2, bundle);
            }
            Log.d("AndroidCompat", "callSafe [" + str + "] +" + (System.currentTimeMillis() - currentTimeMillis) + "");
            if (acquireUnstableContentProviderClient == null) {
                return bundle2;
            }
            acquireUnstableContentProviderClient.close();
            return bundle2;
        } catch (Error | Exception e) {
            a.z(e, j.k("callSafe [", str, "] failed e="), "AndroidCompat");
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static PendingIntent createActivityPendingIntent(Context context, int i2, Intent intent, int i7) {
        return PendingIntentCompat.getActivity(context, i2, intent, i7);
    }

    public static PendingIntent createBroadcastPendingIntent(Context context, int i2, Intent intent, int i7) {
        return PendingIntentCompat.getBroadcast(context, i2, intent, i7);
    }

    public static PendingIntent createServicePendingIntent(Context context, int i2, Intent intent, int i7) {
        return PendingIntentCompat.getService(context, i2, intent, i7);
    }

    public static void registerReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        try {
            sImpl.registerReceiver(context, broadcastReceiver, intentFilter);
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder("registerReceiver failed ");
            boolean z = false;
            Boolean valueOf = Boolean.valueOf(context != null);
            if (broadcastReceiver != null) {
                z = true;
            }
            sb2.append(Logger.v(valueOf, Boolean.valueOf(z)));
            sb2.append(" ");
            sb2.append(ThreadUtil.getCaller());
            Log.e((CharSequence) "AndroidCompat", sb2.toString(), (Throwable) e);
            throw e;
        }
    }

    public static void registerSystemReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        boolean z;
        try {
            sImpl.registerSystemReceiver(context, broadcastReceiver, intentFilter);
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder("registerSystemReceiver failed ");
            boolean z3 = false;
            if (context != null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (broadcastReceiver != null) {
                z3 = true;
            }
            sb2.append(Logger.v(valueOf, Boolean.valueOf(z3)));
            sb2.append(" ");
            sb2.append(ThreadUtil.getCaller());
            Log.e((CharSequence) "AndroidCompat", sb2.toString(), (Throwable) e);
            throw e;
        }
    }

    public static void unregisterReceiver(Context context, BroadcastReceiver broadcastReceiver) {
        if (context != null && broadcastReceiver != null) {
            try {
                context.unregisterReceiver(broadcastReceiver);
            } catch (Exception e) {
                Log.e((CharSequence) "AndroidCompat", "registerReceiver failed " + ThreadUtil.getCaller(), (Throwable) e);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AndroidImpl34 extends AndroidImpl {
        public int getNonSystemFilterCount(IntentFilter intentFilter) {
            Iterator<String> actionsIterator = intentFilter.actionsIterator();
            ArrayList arrayList = new ArrayList();
            while (actionsIterator.hasNext()) {
                String next = actionsIterator.next();
                if (next == null || !next.startsWith("android.intent.action.")) {
                    if (next == null) {
                        next = "";
                    }
                    arrayList.add(next);
                }
            }
            return arrayList.size();
        }

        public void registerReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
            if (getNonSystemFilterCount(intentFilter) != 0) {
                context.registerReceiver(broadcastReceiver, intentFilter, 2);
                return;
            }
            throw new AssertionError("registerReceiver failed. all actions being system");
        }

        public void registerSystemReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
            if (getNonSystemFilterCount(intentFilter) <= 0) {
                context.registerReceiver(broadcastReceiver, intentFilter);
                return;
            }
            throw new AssertionError("registerSystemReceiver failed. non system actions exist");
        }

        public void registerReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler) {
            if (getNonSystemFilterCount(intentFilter) != 0) {
                context.registerReceiver(broadcastReceiver, intentFilter, str, handler, 2);
                return;
            }
            throw new AssertionError("registerReceiver failed. all actions being system");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x001d  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x001f  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void registerReceiver(android.content.Context r6, android.content.BroadcastReceiver r7, android.content.IntentFilter r8, java.lang.String r9, android.os.Handler r10) {
        /*
            com.samsung.android.gallery.support.utils.AndroidCompat$AndroidImpl r0 = sImpl     // Catch:{ Exception -> 0x000e }
            r1 = r6
            r2 = r7
            r3 = r8
            r4 = r9
            r5 = r10
            r0.registerReceiver(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x000b }
            return
        L_0x000b:
            r0 = move-exception
        L_0x000c:
            r6 = r0
            goto L_0x0012
        L_0x000e:
            r0 = move-exception
            r1 = r6
            r2 = r7
            goto L_0x000c
        L_0x0012:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "registerReceiver failed "
            r7.<init>(r8)
            r8 = 0
            r9 = 1
            if (r1 == 0) goto L_0x001f
            r10 = r9
            goto L_0x0020
        L_0x001f:
            r10 = r8
        L_0x0020:
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)
            if (r2 == 0) goto L_0x0027
            r8 = r9
        L_0x0027:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)
            java.lang.Object[] r8 = new java.lang.Object[]{r10, r8}
            java.lang.String r8 = com.samsung.android.gallery.support.utils.Logger.v(r8)
            r7.append(r8)
            java.lang.String r8 = " "
            r7.append(r8)
            java.lang.String r8 = com.samsung.android.gallery.support.utils.ThreadUtil.getCaller()
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            java.lang.String r8 = "AndroidCompat"
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r8, (java.lang.String) r7, (java.lang.Throwable) r6)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.utils.AndroidCompat.registerReceiver(android.content.Context, android.content.BroadcastReceiver, android.content.IntentFilter, java.lang.String, android.os.Handler):void");
    }
}
