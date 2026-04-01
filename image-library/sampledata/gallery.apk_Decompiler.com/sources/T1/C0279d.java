package t1;

import B1.a;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import c0.C0086a;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.sec.android.gallery3d.R;
import i.C0212a;
import v1.e;
import w1.k;
import w1.l;
import w1.r;

/* renamed from: t1.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0279d extends f {

    /* renamed from: c  reason: collision with root package name */
    public static final Object f1922c = new Object();
    public static final C0279d d = new Object();

    public static AlertDialog d(Activity activity, int i2, l lVar, DialogInterface.OnCancelListener onCancelListener) {
        AlertDialog.Builder builder = null;
        if (i2 == 0) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        activity.getTheme().resolveAttribute(16843529, typedValue, true);
        if ("Theme.Dialog.Alert".equals(activity.getResources().getResourceEntryName(typedValue.resourceId))) {
            builder = new AlertDialog.Builder(activity, 5);
        }
        if (builder == null) {
            builder = new AlertDialog.Builder(activity);
        }
        builder.setMessage(k.c(activity, i2));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        String b = k.b(activity, i2);
        if (b != null) {
            builder.setPositiveButton(b, lVar);
        }
        String d2 = k.d(activity, i2);
        if (d2 != null) {
            builder.setTitle(d2);
        }
        Log.w("GoogleApiAvailability", C0086a.i(i2, "Creating dialog for Google Play services availability issue. ConnectionResult="), new IllegalArgumentException());
        return builder.create();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [t1.b, android.app.DialogFragment] */
    public static void e(Activity activity, AlertDialog alertDialog, String str, DialogInterface.OnCancelListener onCancelListener) {
        try {
            if (activity instanceof FragmentActivity) {
                FragmentManager supportFragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
                j jVar = new j();
                r.c(alertDialog, "Cannot display null dialog");
                alertDialog.setOnCancelListener((DialogInterface.OnCancelListener) null);
                alertDialog.setOnDismissListener((DialogInterface.OnDismissListener) null);
                jVar.d = alertDialog;
                if (onCancelListener != null) {
                    jVar.e = onCancelListener;
                }
                jVar.show(supportFragmentManager, str);
                return;
            }
        } catch (NoClassDefFoundError unused) {
        }
        android.app.FragmentManager fragmentManager = activity.getFragmentManager();
        ? dialogFragment = new DialogFragment();
        r.c(alertDialog, "Cannot display null dialog");
        alertDialog.setOnCancelListener((DialogInterface.OnCancelListener) null);
        alertDialog.setOnDismissListener((DialogInterface.OnDismissListener) null);
        dialogFragment.d = alertDialog;
        if (onCancelListener != null) {
            dialogFragment.e = onCancelListener;
        }
        dialogFragment.show(fragmentManager, str);
    }

    public final void c(GoogleApiActivity googleApiActivity, int i2, GoogleApiActivity googleApiActivity2) {
        AlertDialog d2 = d(googleApiActivity, i2, new l(0, super.a(googleApiActivity, i2, "d"), googleApiActivity), googleApiActivity2);
        if (d2 != null) {
            e(googleApiActivity, d2, "GooglePlayServicesErrorDialog", googleApiActivity2);
        }
    }

    public final void f(Context context, int i2, PendingIntent pendingIntent) {
        String str;
        String str2;
        int i7;
        Log.w("GoogleApiAvailability", C0212a.j(i2, "GMS core API Availability. ConnectionResult=", ", tag=null"), new IllegalArgumentException());
        if (i2 == 18) {
            new k(this, context).sendEmptyMessageDelayed(1, 120000);
        } else if (pendingIntent != null) {
            if (i2 == 6) {
                str = k.f(context, "common_google_play_services_resolution_required_title");
            } else {
                str = k.d(context, i2);
            }
            if (str == null) {
                str = context.getResources().getString(R.string.common_google_play_services_notification_ticker);
            }
            if (i2 == 6 || i2 == 19) {
                str2 = k.e(context, "common_google_play_services_resolution_required_text", k.a(context));
            } else {
                str2 = k.c(context, i2);
            }
            Resources resources = context.getResources();
            Object systemService = context.getSystemService("notification");
            r.b(systemService);
            NotificationManager notificationManager = (NotificationManager) systemService;
            NotificationCompat.Builder style = new NotificationCompat.Builder(context).setLocalOnly(true).setAutoCancel(true).setContentTitle(str).setStyle(new NotificationCompat.BigTextStyle().bigText(str2));
            PackageManager packageManager = context.getPackageManager();
            if (a.f == null) {
                a.f = Boolean.valueOf(packageManager.hasSystemFeature("android.hardware.type.watch"));
            }
            if (a.f.booleanValue()) {
                style.setSmallIcon(context.getApplicationInfo().icon).setPriority(2);
                if (a.H(context)) {
                    style.addAction(R.drawable.common_full_open_on_phone, resources.getString(R.string.common_open_on_phone), pendingIntent);
                } else {
                    style.setContentIntent(pendingIntent);
                }
            } else {
                style.setSmallIcon(17301642).setTicker(resources.getString(R.string.common_google_play_services_notification_ticker)).setWhen(System.currentTimeMillis()).setContentIntent(pendingIntent).setContentText(str2);
            }
            synchronized (f1922c) {
            }
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel("com.google.android.gms.availability");
            String string = context.getResources().getString(R.string.common_google_play_services_notification_channel_name);
            if (notificationChannel == null) {
                notificationManager.createNotificationChannel(new NotificationChannel("com.google.android.gms.availability", string, 4));
            } else if (!string.contentEquals(notificationChannel.getName())) {
                notificationChannel.setName(string);
                notificationManager.createNotificationChannel(notificationChannel);
            }
            style.setChannelId("com.google.android.gms.availability");
            Notification build = style.build();
            if (i2 == 1 || i2 == 2 || i2 == 3) {
                h.f1924a.set(false);
                i7 = 10436;
            } else {
                i7 = 39789;
            }
            notificationManager.notify(i7, build);
        } else if (i2 == 6) {
            Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
        }
    }

    public final void g(Activity activity, e eVar, int i2, DialogInterface.OnCancelListener onCancelListener) {
        AlertDialog d2 = d(activity, i2, new l(1, super.a(activity, i2, "d"), eVar), onCancelListener);
        if (d2 != null) {
            e(activity, d2, "GooglePlayServicesErrorDialog", onCancelListener);
        }
    }
}
