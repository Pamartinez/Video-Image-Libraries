package com.samsung.android.sdk.moneta.travel.service;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J&\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H@¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/service/TravelContentProviderClient;", "Lcom/samsung/android/sdk/moneta/travel/service/TravelService;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "startTime", "endTime", "", "Lcom/samsung/android/sdk/moneta/travel/model/TravelPlan;", "searchTravelPlan", "(JJLqe/c;)Ljava/lang/Object;", "Landroid/content/Context;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TravelContentProviderClient implements TravelService {
    public static final Companion Companion = new Companion((e) null);
    private static final String PATH_TRAVEL_PLAN = "search_travel_plan";
    private static final String TAG = "TravelContentProviderClient";
    private static final String TRAVEL_PLAN_CURSOR_COLUMN_NAME = "travelPlan";
    private static final String URI_AUTHORITY = "com.samsung.android.pde.fusioninference";
    private static final String URI_TRAVEL_PLAN = "content://com.samsung.android.pde.fusioninference/search_travel_plan";
    private final Context context;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/service/TravelContentProviderClient$Companion;", "", "<init>", "()V", "TAG", "", "URI_AUTHORITY", "PATH_TRAVEL_PLAN", "URI_TRAVEL_PLAN", "TRAVEL_PLAN_CURSOR_COLUMN_NAME", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public TravelContentProviderClient(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00bf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object searchTravelPlan(long r11, long r13, qe.C1227c r15) {
        /*
            r10 = this;
            java.lang.String r15 = "travelPlan"
            java.lang.String r0 = "searchTravelPlan cursor count = "
            com.samsung.android.sdk.moneta.common.Logger r1 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.String r2 = "searchTravelPlan"
            java.lang.String r3 = "TravelContentProviderClient"
            r1.i$pde_sdk_1_0_40_release(r3, r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            android.content.Context r10 = r10.context     // Catch:{ all -> 0x00a9 }
            android.content.ContentResolver r4 = r10.getContentResolver()     // Catch:{ all -> 0x00a9 }
            if (r4 == 0) goto L_0x00b3
            java.lang.String r10 = "content://com.samsung.android.pde.fusioninference/search_travel_plan"
            android.net.Uri r5 = android.net.Uri.parse(r10)     // Catch:{ all -> 0x00a9 }
            java.lang.String[] r6 = new java.lang.String[]{r15}     // Catch:{ all -> 0x00a9 }
            java.lang.String r10 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x00a9 }
            java.lang.String r11 = java.lang.String.valueOf(r13)     // Catch:{ all -> 0x00a9 }
            java.lang.String[] r8 = new java.lang.String[]{r10, r11}     // Catch:{ all -> 0x00a9 }
            r9 = 0
            r7 = 0
            android.database.Cursor r10 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00a9 }
            if (r10 == 0) goto L_0x00b3
            java.io.Closeable r10 = (java.io.Closeable) r10     // Catch:{ all -> 0x00a9 }
            r11 = r10
            android.database.Cursor r11 = (android.database.Cursor) r11     // Catch:{ all -> 0x0071 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0071 }
            r12.<init>(r0)     // Catch:{ all -> 0x0071 }
            int r13 = r11.getCount()     // Catch:{ all -> 0x0071 }
            r12.append(r13)     // Catch:{ all -> 0x0071 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0071 }
            r1.d$pde_sdk_1_0_40_release(r3, r12)     // Catch:{ all -> 0x0071 }
            int r12 = r11.getColumnIndexOrThrow(r15)     // Catch:{ Exception -> 0x0074 }
            boolean r13 = r11.moveToFirst()     // Catch:{ Exception -> 0x0074 }
            if (r13 == 0) goto L_0x007e
        L_0x005d:
            java.lang.String r13 = r11.getString(r12)     // Catch:{ Exception -> 0x0074 }
            if (r13 == 0) goto L_0x0077
            com.samsung.android.sdk.moneta.travel.internal.model.TravelPlanInternal r13 = com.samsung.android.sdk.moneta.travel.internal.model.TravelPlanInternalKt.toTravelPlan(r13)     // Catch:{ Exception -> 0x0074 }
            if (r13 == 0) goto L_0x0077
            com.samsung.android.sdk.moneta.travel.model.TravelPlan r13 = com.samsung.android.sdk.moneta.travel.mapper.MappersKt.toExternal((com.samsung.android.sdk.moneta.travel.internal.model.TravelPlanInternal) r13)     // Catch:{ Exception -> 0x0074 }
            r2.add(r13)     // Catch:{ Exception -> 0x0074 }
            goto L_0x0077
        L_0x0071:
            r0 = move-exception
            r11 = r0
            goto L_0x00ac
        L_0x0074:
            r0 = move-exception
            r11 = r0
            goto L_0x0086
        L_0x0077:
            boolean r13 = r11.moveToNext()     // Catch:{ Exception -> 0x0074 }
            if (r13 != 0) goto L_0x005d
            goto L_0x0083
        L_0x007e:
            java.lang.String r11 = "No data found in the cursor"
            r1.e$pde_sdk_1_0_40_release(r3, r11)     // Catch:{ Exception -> 0x0074 }
        L_0x0083:
            me.x r11 = me.x.f4917a     // Catch:{ Exception -> 0x0074 }
            goto L_0x00a5
        L_0x0086:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0071 }
            r12.<init>()     // Catch:{ all -> 0x0071 }
            java.lang.String r13 = "Error while parsing JSON result: "
            r12.append(r13)     // Catch:{ all -> 0x0071 }
            java.lang.String r11 = r11.getMessage()     // Catch:{ all -> 0x0071 }
            r12.append(r11)     // Catch:{ all -> 0x0071 }
            java.lang.String r11 = r12.toString()     // Catch:{ all -> 0x0071 }
            int r11 = android.util.Log.e(r3, r11)     // Catch:{ all -> 0x0071 }
            java.lang.Integer r12 = new java.lang.Integer     // Catch:{ all -> 0x0071 }
            r12.<init>(r11)     // Catch:{ all -> 0x0071 }
            r11 = r12
        L_0x00a5:
            r10.close()     // Catch:{ all -> 0x00a9 }
            goto L_0x00b9
        L_0x00a9:
            r0 = move-exception
            r10 = r0
            goto L_0x00b5
        L_0x00ac:
            throw r11     // Catch:{ all -> 0x00ad }
        L_0x00ad:
            r0 = move-exception
            r12 = r0
            B1.a.g(r10, r11)     // Catch:{ all -> 0x00a9 }
            throw r12     // Catch:{ all -> 0x00a9 }
        L_0x00b3:
            r11 = 0
            goto L_0x00b9
        L_0x00b5:
            me.j r11 = L2.a.l(r10)
        L_0x00b9:
            java.lang.Throwable r10 = me.k.a(r11)
            if (r10 == 0) goto L_0x00d7
            com.samsung.android.sdk.moneta.common.Logger r11 = com.samsung.android.sdk.moneta.common.Logger.INSTANCE
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "searchTravelPlan e = "
            r12.<init>(r13)
            java.lang.String r10 = r10.getMessage()
            r12.append(r10)
            java.lang.String r10 = r12.toString()
            r11.e$pde_sdk_1_0_40_release(r3, r10)
        L_0x00d7:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.travel.service.TravelContentProviderClient.searchTravelPlan(long, long, qe.c):java.lang.Object");
    }
}
