package androidx.appfunctions.internal;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H@¢\u0006\u0004\b\u0003\u0010\u0004\u001a<\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000b\"\b\b\u0000\u0010\u0006*\u00020\u0005*\u00020\u00072\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00018\u00000\bH@¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroid/content/Context;", "context", "Landroidx/appsearch/app/GlobalSearchSession;", "createSearchSession", "(Landroid/content/Context;Lqe/c;)Ljava/lang/Object;", "", "T", "Landroidx/appsearch/app/SearchResults;", "Lkotlin/Function1;", "Landroidx/appsearch/app/SearchResult;", "transformToDocumentClassOrNull", "", "readAll", "(Landroidx/appsearch/app/SearchResults;LAe/b;Lqe/c;)Ljava/lang/Object;", "appfunctions"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AppSearchUtilsKt {
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object createSearchSession(android.content.Context r4, qe.C1227c r5) {
        /*
            boolean r0 = r5 instanceof androidx.appfunctions.internal.AppSearchUtilsKt$createSearchSession$1
            if (r0 == 0) goto L_0x0013
            r0 = r5
            androidx.appfunctions.internal.AppSearchUtilsKt$createSearchSession$1 r0 = (androidx.appfunctions.internal.AppSearchUtilsKt$createSearchSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            androidx.appfunctions.internal.AppSearchUtilsKt$createSearchSession$1 r0 = new androidx.appfunctions.internal.AppSearchUtilsKt$createSearchSession$1
            r0.<init>(r5)
        L_0x0018:
            java.lang.Object r5 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x002f
            if (r2 != r3) goto L_0x0027
            L2.a.A(r5)
            goto L_0x0057
        L_0x0027:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x002f:
            L2.a.A(r5)
            androidx.appsearch.platformstorage.PlatformStorage$GlobalSearchContext$Builder r5 = new androidx.appsearch.platformstorage.PlatformStorage$GlobalSearchContext$Builder
            r5.<init>(r4)
            j.b r4 = new j.b
            r2 = 0
            r4.<init>(r2)
            androidx.appsearch.platformstorage.PlatformStorage$GlobalSearchContext$Builder r4 = r5.setWorkerExecutor(r4)
            androidx.appsearch.platformstorage.PlatformStorage$GlobalSearchContext r4 = r4.build()
            com.google.common.util.concurrent.ListenableFuture r4 = androidx.appsearch.platformstorage.PlatformStorage.createGlobalSearchSessionAsync(r4)
            java.lang.String r5 = "createGlobalSearchSessionAsync(...)"
            kotlin.jvm.internal.j.d(r4, r5)
            r0.label = r3
            java.lang.Object r5 = D1.f.l(r4, r0)
            if (r5 != r1) goto L_0x0057
            return r1
        L_0x0057:
            java.lang.String r4 = "await(...)"
            kotlin.jvm.internal.j.d(r5, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.internal.AppSearchUtilsKt.createSearchSession(android.content.Context, qe.c):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object readAll(androidx.appsearch.app.SearchResults r9, Ae.b r10, qe.C1227c r11) {
        /*
            boolean r0 = r11 instanceof androidx.appfunctions.internal.AppSearchUtilsKt$readAll$1
            if (r0 == 0) goto L_0x0013
            r0 = r11
            androidx.appfunctions.internal.AppSearchUtilsKt$readAll$1 r0 = (androidx.appfunctions.internal.AppSearchUtilsKt$readAll$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            androidx.appfunctions.internal.AppSearchUtilsKt$readAll$1 r0 = new androidx.appfunctions.internal.AppSearchUtilsKt$readAll$1
            r0.<init>(r11)
        L_0x0018:
            java.lang.Object r11 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            java.lang.String r3 = "getNextPageAsync(...)"
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x005d
            if (r2 == r5) goto L_0x0045
            if (r2 != r4) goto L_0x003d
            java.lang.Object r9 = r0.L$3
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r10 = r0.L$2
            java.util.List r10 = (java.util.List) r10
            java.lang.Object r2 = r0.L$1
            Ae.b r2 = (Ae.b) r2
            java.lang.Object r5 = r0.L$0
            androidx.appsearch.app.SearchResults r5 = (androidx.appsearch.app.SearchResults) r5
            L2.a.A(r11)
            goto L_0x00e0
        L_0x003d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0045:
            java.lang.Object r9 = r0.L$3
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r10 = r0.L$2
            java.util.List r10 = (java.util.List) r10
            java.lang.Object r2 = r0.L$1
            Ae.b r2 = (Ae.b) r2
            java.lang.Object r5 = r0.L$0
            androidx.appsearch.app.SearchResults r5 = (androidx.appsearch.app.SearchResults) r5
            L2.a.A(r11)
            r8 = r11
            r11 = r9
            r9 = r5
            r5 = r8
            goto L_0x007f
        L_0x005d:
            L2.a.A(r11)
            oe.c r11 = o1.C0246a.R()
            com.google.common.util.concurrent.ListenableFuture r2 = r9.getNextPageAsync()
            kotlin.jvm.internal.j.d(r2, r3)
            r0.L$0 = r9
            r0.L$1 = r10
            r0.L$2 = r11
            r0.L$3 = r11
            r0.label = r5
            java.lang.Object r2 = D1.f.l(r2, r0)
            if (r2 != r1) goto L_0x007c
            goto L_0x00dc
        L_0x007c:
            r5 = r2
            r2 = r10
            r10 = r11
        L_0x007f:
            java.util.List r5 = (java.util.List) r5
            r8 = r11
            r11 = r9
            r9 = r8
        L_0x0084:
            kotlin.jvm.internal.j.b(r5)
            r6 = r5
            java.util.Collection r6 = (java.util.Collection) r6
            boolean r6 = r6.isEmpty()
            if (r6 != 0) goto L_0x00e6
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r6 = new java.util.ArrayList
            r7 = 10
            int r7 = ne.C1196n.w0(r5, r7)
            r6.<init>(r7)
            java.util.Iterator r5 = r5.iterator()
        L_0x00a1:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L_0x00b3
            java.lang.Object r7 = r5.next()
            java.lang.Object r7 = r2.invoke(r7)
            r6.add(r7)
            goto L_0x00a1
        L_0x00b3:
            java.util.Iterator r5 = r6.iterator()
        L_0x00b7:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x00c5
            java.lang.Object r6 = r5.next()
            r9.add(r6)
            goto L_0x00b7
        L_0x00c5:
            com.google.common.util.concurrent.ListenableFuture r5 = r11.getNextPageAsync()
            kotlin.jvm.internal.j.d(r5, r3)
            r0.L$0 = r11
            r0.L$1 = r2
            r0.L$2 = r10
            r0.L$3 = r9
            r0.label = r4
            java.lang.Object r5 = D1.f.l(r5, r0)
            if (r5 != r1) goto L_0x00dd
        L_0x00dc:
            return r1
        L_0x00dd:
            r8 = r5
            r5 = r11
            r11 = r8
        L_0x00e0:
            java.util.List r11 = (java.util.List) r11
            r8 = r5
            r5 = r11
            r11 = r8
            goto L_0x0084
        L_0x00e6:
            oe.c r9 = o1.C0246a.K(r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.internal.AppSearchUtilsKt.readAll(androidx.appsearch.app.SearchResults, Ae.b, qe.c):java.lang.Object");
    }
}
