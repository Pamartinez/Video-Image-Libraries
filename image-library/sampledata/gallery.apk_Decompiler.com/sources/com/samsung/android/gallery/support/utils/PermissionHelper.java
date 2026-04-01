package com.samsung.android.gallery.support.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import com.samsung.android.gallery.support.R$string;
import com.samsung.android.gallery.support.config.SdkConfig;
import java.lang.ref.WeakReference;
import z2.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PermissionHelper {
    private static boolean sIsNotiPermission;
    private static WeakReference<r> sSnackBarReference = new WeakReference<>((Object) null);
    private static Runnable sSnackBarRunnable;

    public static boolean hasUriPermission(Context context, Uri uri, int i2) {
        try {
            if (context.checkUriPermission(uri, Process.myPid(), Process.myUid(), i2) == 0) {
                return true;
            }
            return false;
        } catch (Exception unused) {
        }
    }

    public static boolean isUriWritable(Uri uri) {
        return hasUriPermission(AppResources.getAppContext(), uri, 2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$showSnackBar$0(Context context, View view) {
        showSnackBarInternal(context, view);
        sSnackBarRunnable = null;
    }

    /* access modifiers changed from: private */
    public static void launchApplicationDetailSettings(View view) {
        launchApplicationDetailSettings(view.getContext());
    }

    public static void showSnackBar(Context context, View view, boolean z) {
        sIsNotiPermission = z;
        showSnackBar(context, view);
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [android.view.View$OnClickListener, java.lang.Object] */
    private static void showSnackBarInternal(Context context, View view) {
        r rVar = sSnackBarReference.get();
        if (rVar != null) {
            rVar.a(3);
        }
        Activity activity = Utils.getActivity(context);
        if (activity != null) {
            if (view == null) {
                view = activity.findViewById(16908290);
            }
            r j2 = r.j(view, 0, 0, false, context.getString(R$string.permission_rationale_toast_description));
            j2.k(context.getString(R$string.settings), new Object());
            sSnackBarReference = new WeakReference<>(j2);
            j2.l();
        }
    }

    public static void launchApplicationDetailSettings(Context context) {
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.addFlags(268468224);
            intent.setData(Uri.fromParts("package", "com.sec.android.gallery3d", (String) null));
            Bundle bundle = new Bundle();
            bundle.putString(":settings:fragment_args_key", sIsNotiPermission ? "notification_settings" : "permission_settings");
            intent.putExtra(":settings:show_fragment_args", bundle);
            context.startActivity(intent);
            sIsNotiPermission = false;
        } catch (Exception e) {
            Log.e((CharSequence) "PermissionHelper", "failed to launch location detail settings", e.getMessage());
            sIsNotiPermission = false;
        } catch (Throwable th) {
            sIsNotiPermission = false;
            throw th;
        }
    }

    public static void showSnackBar(Context context) {
        showSnackBar(context, (View) null);
    }

    private static void showSnackBar(Context context, View view) {
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            Runnable runnable = sSnackBarRunnable;
            if (runnable != null) {
                ThreadUtil.removeCallbackOnUiThread(runnable);
            }
            C0685x xVar = new C0685x(1, context, view);
            sSnackBarRunnable = xVar;
            ThreadUtil.postOnUiThreadDelayed(xVar, 100);
            return;
        }
        Log.w("PermissionHelper", "not support");
    }
}
