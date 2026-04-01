package com.samsung.android.sdk.moneta.lifestyle.notification.service;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.android.sdk.moneta.lifestyle.common.TimeRange;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\b\u001a\u00020\tH\u0016J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\b\u001a\u00020\tH\u0016J\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/notification/service/NotificationServiceImpl;", "Lcom/samsung/android/sdk/moneta/lifestyle/notification/service/NotificationService;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getAverageDailyNotificationCount", "", "timeRange", "Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;", "getAverageRemainingNotificationCount", "getFastestReactionNotifications", "", "", "getTopReactionNotifications", "callProviderUnstable", "Landroid/os/Bundle;", "method", "extras", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class NotificationServiceImpl implements NotificationService {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "Lifestyle-NotificationServiceImpl";
    private static final Uri URI;
    private final Context context;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/notification/service/NotificationServiceImpl$Companion;", "", "<init>", "()V", "TAG", "", "URI", "Landroid/net/Uri;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        Uri parse = Uri.parse("content://com.samsung.android.moneta.feature.preference.LifeStyleProvider");
        j.d(parse, "parse(...)");
        URI = parse;
    }

    public NotificationServiceImpl(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:33|34|35|36|(3:40|41|42)(7:44|45|46|47|57|58|59)) */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0077, code lost:
        r8 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0067 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x006a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x00c1 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x00c8 */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007a A[SYNTHETIC, Splitter:B:40:0x007a] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x007e A[SYNTHETIC, Splitter:B:44:0x007e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final android.os.Bundle callProviderUnstable(java.lang.String r9, android.os.Bundle r10) {
        /*
            r8 = this;
            java.lang.String r0 = "Lifestyle-NotificationServiceImpl"
            java.lang.String r1 = "provider call not permitted: "
            java.lang.String r2 = "provider call remote exception: "
            java.lang.String r3 = "provider call failed (retry): "
            java.lang.String r4 = "provider call failed: "
            r5 = 0
            android.content.Context r6 = r8.context     // Catch:{ IllegalArgumentException | SecurityException -> 0x00e1, all -> 0x00cd }
            android.content.ContentResolver r6 = r6.getContentResolver()     // Catch:{ IllegalArgumentException | SecurityException -> 0x00e1, all -> 0x00cd }
            android.net.Uri r7 = URI     // Catch:{ IllegalArgumentException | SecurityException -> 0x00e1, all -> 0x00cd }
            android.content.ContentProviderClient r6 = r6.acquireUnstableContentProviderClient(r7)     // Catch:{ IllegalArgumentException | SecurityException -> 0x00e1, all -> 0x00cd }
            if (r6 != 0) goto L_0x001a
            return r5
        L_0x001a:
            android.os.Bundle r8 = r6.call(r9, r5, r10)     // Catch:{ DeadObjectException -> 0x0067, SecurityException -> 0x0051, RemoteException -> 0x003b, all -> 0x0022 }
            r6.close()     // Catch:{ all -> 0x0021 }
        L_0x0021:
            return r8
        L_0x0022:
            r8 = move-exception
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ all -> 0x0038 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0038 }
            r10.<init>(r4)     // Catch:{ all -> 0x0038 }
            r10.append(r8)     // Catch:{ all -> 0x0038 }
            java.lang.String r8 = r10.toString()     // Catch:{ all -> 0x0038 }
            r9.e$pde_sdk_1_0_40_release(r0, r8)     // Catch:{ all -> 0x0038 }
            r6.close()     // Catch:{ all -> 0x0037 }
        L_0x0037:
            return r5
        L_0x0038:
            r8 = move-exception
            goto L_0x00c9
        L_0x003b:
            r8 = move-exception
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ all -> 0x0038 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0038 }
            r10.<init>(r2)     // Catch:{ all -> 0x0038 }
            r10.append(r8)     // Catch:{ all -> 0x0038 }
            java.lang.String r8 = r10.toString()     // Catch:{ all -> 0x0038 }
            r9.e$pde_sdk_1_0_40_release(r0, r8)     // Catch:{ all -> 0x0038 }
            r6.close()     // Catch:{ all -> 0x0050 }
        L_0x0050:
            return r5
        L_0x0051:
            r8 = move-exception
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ all -> 0x0038 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0038 }
            r10.<init>(r1)     // Catch:{ all -> 0x0038 }
            r10.append(r8)     // Catch:{ all -> 0x0038 }
            java.lang.String r8 = r10.toString()     // Catch:{ all -> 0x0038 }
            r9.e$pde_sdk_1_0_40_release(r0, r8)     // Catch:{ all -> 0x0038 }
            r6.close()     // Catch:{ all -> 0x0066 }
        L_0x0066:
            return r5
        L_0x0067:
            r6.close()     // Catch:{ all -> 0x006a }
        L_0x006a:
            android.content.Context r8 = r8.context     // Catch:{ all -> 0x0077 }
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch:{ all -> 0x0077 }
            android.net.Uri r4 = URI     // Catch:{ all -> 0x0077 }
            android.content.ContentProviderClient r8 = r8.acquireUnstableContentProviderClient(r4)     // Catch:{ all -> 0x0077 }
            goto L_0x0078
        L_0x0077:
            r8 = r5
        L_0x0078:
            if (r8 != 0) goto L_0x007e
            r6.close()     // Catch:{ all -> 0x007d }
        L_0x007d:
            return r5
        L_0x007e:
            android.os.Bundle r5 = r8.call(r9, r5, r10)     // Catch:{ SecurityException -> 0x00ae, RemoteException -> 0x009b, all -> 0x0086 }
        L_0x0082:
            r8.close()     // Catch:{ all -> 0x00c1 }
            goto L_0x00c1
        L_0x0086:
            r9 = move-exception
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ all -> 0x0099 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0099 }
            r1.<init>(r3)     // Catch:{ all -> 0x0099 }
            r1.append(r9)     // Catch:{ all -> 0x0099 }
            java.lang.String r9 = r1.toString()     // Catch:{ all -> 0x0099 }
            r10.e$pde_sdk_1_0_40_release(r0, r9)     // Catch:{ all -> 0x0099 }
            goto L_0x0082
        L_0x0099:
            r9 = move-exception
            goto L_0x00c5
        L_0x009b:
            r9 = move-exception
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ all -> 0x0099 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0099 }
            r1.<init>(r2)     // Catch:{ all -> 0x0099 }
            r1.append(r9)     // Catch:{ all -> 0x0099 }
            java.lang.String r9 = r1.toString()     // Catch:{ all -> 0x0099 }
            r10.e$pde_sdk_1_0_40_release(r0, r9)     // Catch:{ all -> 0x0099 }
            goto L_0x0082
        L_0x00ae:
            r9 = move-exception
            com.samsung.android.sdk.moneta.common.Logger r10 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE     // Catch:{ all -> 0x0099 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0099 }
            r2.<init>(r1)     // Catch:{ all -> 0x0099 }
            r2.append(r9)     // Catch:{ all -> 0x0099 }
            java.lang.String r9 = r2.toString()     // Catch:{ all -> 0x0099 }
            r10.e$pde_sdk_1_0_40_release(r0, r9)     // Catch:{ all -> 0x0099 }
            goto L_0x0082
        L_0x00c1:
            r6.close()     // Catch:{ all -> 0x00c4 }
        L_0x00c4:
            return r5
        L_0x00c5:
            r8.close()     // Catch:{ all -> 0x00c8 }
        L_0x00c8:
            throw r9     // Catch:{ all -> 0x0038 }
        L_0x00c9:
            r6.close()     // Catch:{ all -> 0x00cc }
        L_0x00cc:
            throw r8
        L_0x00cd:
            r8 = move-exception
            com.samsung.android.sdk.moneta.common.Logger r9 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r1 = "acquire client failed: "
            r10.<init>(r1)
            r10.append(r8)
            java.lang.String r8 = r10.toString()
            r9.e$pde_sdk_1_0_40_release(r0, r8)
        L_0x00e1:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.lifestyle.notification.service.NotificationServiceImpl.callProviderUnstable(java.lang.String, android.os.Bundle):android.os.Bundle");
    }

    public int getAverageDailyNotificationCount(TimeRange timeRange) {
        Integer num;
        j.e(timeRange, "timeRange");
        Bundle bundle = new Bundle();
        bundle.putLong("start_timestamp", timeRange.getStartTime());
        bundle.putLong("end_timestamp", timeRange.getEndTime());
        Bundle callProviderUnstable = callProviderUnstable(ContentProviderConstants.Notification.Method.GET_AVERAGE_DAILY_NOTIFICATION_COUNT, bundle);
        if (callProviderUnstable != null) {
            num = Integer.valueOf(callProviderUnstable.getInt(ContentProviderConstants.Notification.ResultKey.DAILY_NOTIFICATION_COUNT));
        } else {
            num = null;
        }
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "averageDailyNotificationCount : " + num);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    public int getAverageRemainingNotificationCount(TimeRange timeRange) {
        Integer num;
        j.e(timeRange, "timeRange");
        Bundle bundle = new Bundle();
        bundle.putLong("start_timestamp", timeRange.getStartTime());
        bundle.putLong("end_timestamp", timeRange.getEndTime());
        Bundle callProviderUnstable = callProviderUnstable(ContentProviderConstants.Notification.Method.GET_AVERAGE_REMAINING_NOTIFICATION_COUNT, bundle);
        if (callProviderUnstable != null) {
            num = Integer.valueOf(callProviderUnstable.getInt(ContentProviderConstants.Notification.ResultKey.REMAINING_NOTIFICATION_COUNT));
        } else {
            num = null;
        }
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "averageRemainingNotificationCount : " + num);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    public List<String> getFastestReactionNotifications(TimeRange timeRange) {
        ArrayList<String> arrayList;
        j.e(timeRange, "timeRange");
        Bundle bundle = new Bundle();
        bundle.putLong("start_timestamp", timeRange.getStartTime());
        bundle.putLong("end_timestamp", timeRange.getEndTime());
        Bundle callProviderUnstable = callProviderUnstable(ContentProviderConstants.Notification.Method.GET_FASTEST_REACTION_NOTIFICATIONS, bundle);
        if (callProviderUnstable != null) {
            arrayList = callProviderUnstable.getStringArrayList(ContentProviderConstants.Notification.ResultKey.FASTEST_REACTION_NOTIFICATION_KEY_LIST);
        } else {
            arrayList = null;
        }
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "fastestReactionNotificationKeys : " + arrayList);
        if (arrayList != null) {
            return arrayList;
        }
        return C1202t.d;
    }

    public List<String> getTopReactionNotifications(TimeRange timeRange) {
        ArrayList<String> arrayList;
        j.e(timeRange, "timeRange");
        Bundle bundle = new Bundle();
        bundle.putLong("start_timestamp", timeRange.getStartTime());
        bundle.putLong("end_timestamp", timeRange.getEndTime());
        Bundle callProviderUnstable = callProviderUnstable(ContentProviderConstants.Notification.Method.GET_TOP_REACTION_NOTIFICATIONS, bundle);
        if (callProviderUnstable != null) {
            arrayList = callProviderUnstable.getStringArrayList(ContentProviderConstants.Notification.ResultKey.TOP_REACTION_NOTIFICATION_KEY_LIST);
        } else {
            arrayList = null;
        }
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release(TAG, "topReactionNotificationKeys : " + arrayList);
        if (arrayList != null) {
            return arrayList;
        }
        return C1202t.d;
    }
}
