package com.samsung.android.gallery.crossapp.function;

import androidx.appfunctions.service.internal.AppFunctionInvoker;
import java.util.Set;
import kotlin.Metadata;
import ne.C1192j;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J8\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH@¢\u0006\u0004\b\u000b\u0010\fR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\r8\u0016X\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"com/samsung/android/gallery/crossapp/function/$AlbumFunctions_AppFunctionInvoker", "Landroidx/appfunctions/service/internal/AppFunctionInvoker;", "<init>", "()V", "Landroidx/appfunctions/AppFunctionContext;", "appFunctionContext", "", "functionIdentifier", "", "", "parameters", "unsafeInvoke", "(Landroidx/appfunctions/AppFunctionContext;Ljava/lang/String;Ljava/util/Map;Lqe/c;)Ljava/lang/Object;", "", "supportedFunctionIds", "Ljava/util/Set;", "getSupportedFunctionIds", "()Ljava/util/Set;", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* renamed from: com.samsung.android.gallery.crossapp.function.$AlbumFunctions_AppFunctionInvoker  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$AlbumFunctions_AppFunctionInvoker implements AppFunctionInvoker {
    private final Set<String> supportedFunctionIds = C1192j.z0(new String[]{"com.samsung.android.gallery.crossapp.function.AlbumFunctions#showAlbum", "com.samsung.android.gallery.crossapp.function.AlbumFunctions#findAlbums", "com.samsung.android.gallery.crossapp.function.AlbumFunctions#createAlbum"});

    /* access modifiers changed from: private */
    public static final AlbumFunctions unsafeInvoke$lambda$0() {
        return new AlbumFunctions();
    }

    /* access modifiers changed from: private */
    public static final AlbumFunctions unsafeInvoke$lambda$1() {
        return new AlbumFunctions();
    }

    /* access modifiers changed from: private */
    public static final AlbumFunctions unsafeInvoke$lambda$2() {
        return new AlbumFunctions();
    }

    public Set<String> getSupportedFunctionIds() {
        return this.supportedFunctionIds;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f2 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object unsafeInvoke(androidx.appfunctions.AppFunctionContext r7, java.lang.String r8, java.util.Map<java.lang.String, ? extends java.lang.Object> r9, qe.C1227c r10) {
        /*
            r6 = this;
            boolean r0 = r10 instanceof com.samsung.android.gallery.crossapp.function.C$AlbumFunctions_AppFunctionInvoker$unsafeInvoke$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.samsung.android.gallery.crossapp.function.$AlbumFunctions_AppFunctionInvoker$unsafeInvoke$1 r0 = (com.samsung.android.gallery.crossapp.function.C$AlbumFunctions_AppFunctionInvoker$unsafeInvoke$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.gallery.crossapp.function.$AlbumFunctions_AppFunctionInvoker$unsafeInvoke$1 r0 = new com.samsung.android.gallery.crossapp.function.$AlbumFunctions_AppFunctionInvoker$unsafeInvoke$1
            r0.<init>(r6, r10)
        L_0x0018:
            java.lang.Object r6 = r0.result
            re.a r10 = re.C1245a.COROUTINE_SUSPENDED
            int r1 = r0.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x003d
            if (r1 == r4) goto L_0x0039
            if (r1 == r3) goto L_0x0035
            if (r1 != r2) goto L_0x002d
            L2.a.A(r6)
            return r6
        L_0x002d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0035:
            L2.a.A(r6)
            return r6
        L_0x0039:
            L2.a.A(r6)
            return r6
        L_0x003d:
            L2.a.A(r6)
            int r6 = r8.hashCode()
            r1 = -1776961472(0xffffffff9615b840, float:-1.2094263E-25)
            java.lang.Class<com.samsung.android.gallery.crossapp.function.AlbumFunctions> r5 = com.samsung.android.gallery.crossapp.function.AlbumFunctions.class
            if (r6 == r1) goto L_0x00bf
            r1 = 70479343(0x4336def, float:2.1091828E-36)
            if (r6 == r1) goto L_0x008a
            r1 = 1747738145(0x682c5e21, float:3.2559336E24)
            if (r6 != r1) goto L_0x00f4
            java.lang.String r6 = "com.samsung.android.gallery.crossapp.function.AlbumFunctions#createAlbum"
            boolean r6 = r8.equals(r6)
            if (r6 == 0) goto L_0x00f4
            androidx.appfunctions.service.internal.ConfigurableAppFunctionFactory r6 = new androidx.appfunctions.service.internal.ConfigurableAppFunctionFactory
            android.content.Context r8 = r7.getContext()
            Ad.b r1 = new Ad.b
            r3 = 22
            r1.<init>(r3)
            r6.<init>(r8, r1)
            java.lang.Object r6 = r6.createEnclosingClass(r5)
            com.samsung.android.gallery.crossapp.function.AlbumFunctions r6 = (com.samsung.android.gallery.crossapp.function.AlbumFunctions) r6
            java.lang.String r8 = "createAlbumParams"
            java.lang.Object r8 = r9.get(r8)
            java.lang.String r9 = "null cannot be cast to non-null type com.google.android.appfunctions.schema.photos.v1.CreateAlbumParams"
            kotlin.jvm.internal.j.c(r8, r9)
            com.google.android.appfunctions.schema.photos.v1.CreateAlbumParams r8 = (com.google.android.appfunctions.schema.photos.v1.CreateAlbumParams) r8
            r0.label = r2
            java.lang.Object r6 = r6.createAlbum(r7, r8, r0)
            if (r6 != r10) goto L_0x0089
            goto L_0x00f2
        L_0x0089:
            return r6
        L_0x008a:
            java.lang.String r6 = "com.samsung.android.gallery.crossapp.function.AlbumFunctions#findAlbums"
            boolean r6 = r8.equals(r6)
            if (r6 == 0) goto L_0x00f4
            androidx.appfunctions.service.internal.ConfigurableAppFunctionFactory r6 = new androidx.appfunctions.service.internal.ConfigurableAppFunctionFactory
            android.content.Context r8 = r7.getContext()
            Ad.b r1 = new Ad.b
            r2 = 21
            r1.<init>(r2)
            r6.<init>(r8, r1)
            java.lang.Object r6 = r6.createEnclosingClass(r5)
            com.samsung.android.gallery.crossapp.function.AlbumFunctions r6 = (com.samsung.android.gallery.crossapp.function.AlbumFunctions) r6
            java.lang.String r8 = "findAlbumsParams"
            java.lang.Object r8 = r9.get(r8)
            java.lang.String r9 = "null cannot be cast to non-null type com.google.android.appfunctions.schema.photos.v1.FindAlbumsParams"
            kotlin.jvm.internal.j.c(r8, r9)
            com.google.android.appfunctions.schema.photos.v1.FindAlbumsParams r8 = (com.google.android.appfunctions.schema.photos.v1.FindAlbumsParams) r8
            r0.label = r3
            java.lang.Object r6 = r6.findAlbums(r7, r8, r0)
            if (r6 != r10) goto L_0x00be
            goto L_0x00f2
        L_0x00be:
            return r6
        L_0x00bf:
            java.lang.String r6 = "com.samsung.android.gallery.crossapp.function.AlbumFunctions#showAlbum"
            boolean r6 = r8.equals(r6)
            if (r6 == 0) goto L_0x00f4
            androidx.appfunctions.service.internal.ConfigurableAppFunctionFactory r6 = new androidx.appfunctions.service.internal.ConfigurableAppFunctionFactory
            android.content.Context r8 = r7.getContext()
            Ad.b r1 = new Ad.b
            r2 = 20
            r1.<init>(r2)
            r6.<init>(r8, r1)
            java.lang.Object r6 = r6.createEnclosingClass(r5)
            com.samsung.android.gallery.crossapp.function.AlbumFunctions r6 = (com.samsung.android.gallery.crossapp.function.AlbumFunctions) r6
            java.lang.String r8 = "albumId"
            java.lang.Object r8 = r9.get(r8)
            java.lang.String r9 = "null cannot be cast to non-null type kotlin.String"
            kotlin.jvm.internal.j.c(r8, r9)
            java.lang.String r8 = (java.lang.String) r8
            r0.label = r4
            java.lang.Object r6 = r6.showAlbum(r7, r8, r0)
            if (r6 != r10) goto L_0x00f3
        L_0x00f2:
            return r10
        L_0x00f3:
            return r6
        L_0x00f4:
            androidx.appfunctions.AppFunctionFunctionNotFoundException r6 = new androidx.appfunctions.AppFunctionFunctionNotFoundException
            java.lang.String r7 = "Unable to find "
            java.lang.String r7 = r7.concat(r8)
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.crossapp.function.C$AlbumFunctions_AppFunctionInvoker.unsafeInvoke(androidx.appfunctions.AppFunctionContext, java.lang.String, java.util.Map, qe.c):java.lang.Object");
    }
}
