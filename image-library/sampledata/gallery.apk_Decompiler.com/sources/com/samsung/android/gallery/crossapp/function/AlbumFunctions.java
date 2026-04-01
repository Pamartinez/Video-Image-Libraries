package com.samsung.android.gallery.crossapp.function;

import A.a;
import Tf.n;
import Tf.v;
import Vf.D;
import Vf.M;
import android.content.Context;
import android.database.Cursor;
import androidx.appfunctions.AppFunctionElementAlreadyExistsException;
import androidx.appfunctions.AppFunctionLimitExceededException;
import com.google.android.appfunctions.schema.photos.v1.Album;
import com.google.android.appfunctions.schema.photos.v1.FindAlbumsParams;
import com.samsung.android.gallery.database.dal.local.LocalAlbumsApi;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.StorageUtil;
import eg.f;
import i.C0212a;
import java.io.Closeable;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import ne.C1195m;
import qe.C1227c;
import re.C1245a;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \"2\u00020\u00012\u00020\u00012\u00020\u0001:\u0001\"B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H@¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\f\u0010\rJ)\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J \u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u0004H@¢\u0006\u0004\b\u001a\u0010\u001bJ&\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\tH@¢\u0006\u0004\b\u001c\u0010\u001dJ \u0010 \u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u001eH@¢\u0006\u0004\b \u0010!¨\u0006#"}, d2 = {"Lcom/samsung/android/gallery/crossapp/function/AlbumFunctions;", "", "<init>", "()V", "", "albumId", "Lme/x;", "checkValidAlbum", "(Ljava/lang/String;Lqe/c;)Ljava/lang/Object;", "Lcom/google/android/appfunctions/schema/photos/v1/FindAlbumsParams;", "findAlbumsParams", "", "isValidParams", "(Lcom/google/android/appfunctions/schema/photos/v1/FindAlbumsParams;)Z", "Landroid/content/Context;", "context", "", "Lcom/google/android/appfunctions/schema/photos/v1/Album;", "queryAlbum", "(Landroid/content/Context;Lcom/google/android/appfunctions/schema/photos/v1/FindAlbumsParams;)Ljava/util/List;", "albumName", "createAlbums", "(Ljava/lang/String;)Lcom/google/android/appfunctions/schema/photos/v1/Album;", "Landroidx/appfunctions/AppFunctionContext;", "appFunctionContext", "Landroid/app/PendingIntent;", "showAlbum", "(Landroidx/appfunctions/AppFunctionContext;Ljava/lang/String;Lqe/c;)Ljava/lang/Object;", "findAlbums", "(Landroidx/appfunctions/AppFunctionContext;Lcom/google/android/appfunctions/schema/photos/v1/FindAlbumsParams;Lqe/c;)Ljava/lang/Object;", "Lcom/google/android/appfunctions/schema/photos/v1/CreateAlbumParams;", "createAlbumParams", "createAlbum", "(Landroidx/appfunctions/AppFunctionContext;Lcom/google/android/appfunctions/schema/photos/v1/CreateAlbumParams;Lqe/c;)Ljava/lang/Object;", "Companion", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AlbumFunctions {
    private static final HashSet<Integer> ALBUM_TYPE_SET = new HashSet<>(C1195m.q0(Integer.valueOf(AlbumType.None.toInt()), Integer.valueOf(AlbumType.Virtual.toInt()), Integer.valueOf(AlbumType.MyAlbum.toInt())));
    public static final Companion Companion = new Companion((e) null);
    private static final String DEFAULT_PATH = C0212a.A(StorageInfo.getDefault().dcim, File.separator);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006XT¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0006XT¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/gallery/crossapp/function/AlbumFunctions$Companion;", "", "<init>", "()V", "", "TAG", "Ljava/lang/String;", "", "MAX_LIMIT", "I", "DEFAULT_PATH", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    private final Object checkValidAlbum(String str, C1227c cVar) {
        f fVar = M.f3843a;
        Object w = D.w(eg.e.d, new AlbumFunctions$checkValidAlbum$2(str, (C1227c) null), cVar);
        if (w == C1245a.COROUTINE_SUSPENDED) {
            return w;
        }
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public final Album createAlbums(String str) {
        String path = new File(C0212a.A(DEFAULT_PATH, str)).getPath();
        if (FileUtils.exists(path)) {
            throw new AppFunctionElementAlreadyExistsException((String) null, 1, (e) null);
        } else if (!StorageUtil.isLowStorage()) {
            int addNewEmptyAlbum = AlbumHelper.getInstance().addNewEmptyAlbum(path, str, 0, (String) null);
            if (addNewEmptyAlbum != 1) {
                a.k(addNewEmptyAlbum, "createAlbums failed : result = ", "CrossApp#Album");
                return null;
            }
            Blackboard.publishGlobal("data://usernew_item_creation", new Object[]{str, path, 0, null});
            Blackboard.postEventGlobal(EventMessage.obtain(2004, FileUtils.getBucketId(path), new Object[]{Boolean.FALSE, path}));
            return new Album(String.valueOf(FileUtils.getBucketId(path)), str, CrossAppUtils.Companion.buildDateTime(System.currentTimeMillis()));
        } else {
            throw new AppFunctionLimitExceededException((String) null, 1, (e) null);
        }
    }

    private final boolean isValidParams(FindAlbumsParams findAlbumsParams) {
        String str = findAlbumsParams.f1295a;
        return true;
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: IF instruction can be used only in fallback mode
        jadx.core.utils.exceptions.CodegenException: IF instruction can be used only in fallback mode
        	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:579)
        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:485)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:205)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:311)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:68)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:311)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:68)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.util.ArrayList.forEach(ArrayList.java:1259)
        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
        	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
        	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
        	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
        	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
        */
    public final java.util.List<com.google.android.appfunctions.schema.photos.v1.Album> queryAlbum(android.content.Context r13, com.google.android.appfunctions.schema.photos.v1.FindAlbumsParams r14) {
        /*
            r12 = this;
            java.lang.String r12 = "toLowerCase(...)"
            java.lang.String r0 = "findAlbums#query (all albums) "
            java.lang.String r1 = "CrossApp#Album"
            r2 = 0
            if (r13 != 0) goto L_0x000f
            java.lang.String r12 = "findAlbums#query skip. null context"
            com.samsung.android.gallery.support.utils.Log.e(r1, r12)
            return r2
        L_0x000f:
            java.lang.String r13 = r14.f1295a
            if (r13 == 0) goto L_0x00f9
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            int r14 = r14.b
            r4 = 30
            if (r14 != 0) goto L_0x001f
            goto L_0x0023
        L_0x001f:
            if (r14 <= r4) goto L_0x0022
            r14 = r4
        L_0x0022:
            r4 = r14
        L_0x0023:
            com.samsung.android.gallery.database.dal.local.LocalAlbumsApi r14 = new com.samsung.android.gallery.database.dal.local.LocalAlbumsApi     // Catch:{ Exception -> 0x00e6 }
            r14.<init>()     // Catch:{ Exception -> 0x00e6 }
            r5 = 0
            android.database.Cursor r14 = r14.getAlbumCursor(r5, r5, r5, r5)     // Catch:{ Exception -> 0x00e6 }
            java.io.Closeable r14 = (java.io.Closeable) r14     // Catch:{ Exception -> 0x00e6 }
            r6 = r14
            android.database.Cursor r6 = (android.database.Cursor) r6     // Catch:{ all -> 0x00b1 }
            java.lang.String r7 = com.samsung.android.gallery.support.utils.Logger.getCursorInfo(r6)     // Catch:{ all -> 0x00b1 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b1 }
            r8.<init>(r0)     // Catch:{ all -> 0x00b1 }
            r8.append(r7)     // Catch:{ all -> 0x00b1 }
            java.lang.String r0 = r8.toString()     // Catch:{ all -> 0x00b1 }
            java.lang.String r7 = ""
            java.lang.Object[] r7 = new java.lang.Object[]{r7}     // Catch:{ all -> 0x00b1 }
            com.samsung.android.gallery.support.utils.Log.d(r1, r0, r7)     // Catch:{ all -> 0x00b1 }
            boolean r0 = r6.moveToFirst()     // Catch:{ all -> 0x00b1 }
            if (r0 == 0) goto L_0x00c0
        L_0x0051:
            java.lang.String r0 = "__Title"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ all -> 0x00b1 }
            java.lang.String r0 = r6.getString(r0)     // Catch:{ all -> 0x00b1 }
            java.lang.String r7 = "__albumType"
            int r7 = r6.getColumnIndex(r7)     // Catch:{ all -> 0x00b1 }
            int r7 = r6.getInt(r7)     // Catch:{ all -> 0x00b1 }
            kotlin.jvm.internal.j.b(r0)     // Catch:{ all -> 0x00b1 }
            java.util.Locale r8 = java.util.Locale.ROOT     // Catch:{ all -> 0x00b1 }
            java.lang.String r9 = r0.toLowerCase(r8)     // Catch:{ all -> 0x00b1 }
            kotlin.jvm.internal.j.d(r9, r12)     // Catch:{ all -> 0x00b1 }
            java.lang.String r8 = r13.toLowerCase(r8)     // Catch:{ all -> 0x00b1 }
            kotlin.jvm.internal.j.d(r8, r12)     // Catch:{ all -> 0x00b1 }
            boolean r8 = Tf.n.u0(r9, r8)     // Catch:{ all -> 0x00b1 }
            if (r8 == 0) goto L_0x00b3
            java.util.HashSet<java.lang.Integer> r8 = ALBUM_TYPE_SET     // Catch:{ all -> 0x00b1 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x00b1 }
            boolean r7 = r8.contains(r7)     // Catch:{ all -> 0x00b1 }
            if (r7 == 0) goto L_0x00b3
            java.lang.String r7 = "__bucketID"
            int r7 = r6.getColumnIndex(r7)     // Catch:{ all -> 0x00b1 }
            int r7 = r6.getInt(r7)     // Catch:{ all -> 0x00b1 }
            java.lang.String r8 = "__dateModified"
            int r8 = r6.getColumnIndex(r8)     // Catch:{ all -> 0x00b1 }
            long r8 = r6.getLong(r8)     // Catch:{ all -> 0x00b1 }
            com.google.android.appfunctions.schema.photos.v1.Album r10 = new com.google.android.appfunctions.schema.photos.v1.Album     // Catch:{ all -> 0x00b1 }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x00b1 }
            com.samsung.android.gallery.crossapp.function.CrossAppUtils$Companion r11 = com.samsung.android.gallery.crossapp.function.CrossAppUtils.Companion     // Catch:{ all -> 0x00b1 }
            com.google.android.appfunctions.schema.types.v1.DateTime r8 = r11.buildDateTime(r8)     // Catch:{ all -> 0x00b1 }
            r10.<init>(r7, r0, r8)     // Catch:{ all -> 0x00b1 }
            r3.add(r10)     // Catch:{ all -> 0x00b1 }
            goto L_0x00b3
        L_0x00b1:
            r12 = move-exception
            goto L_0x00e9
        L_0x00b3:
            int r0 = r3.size()     // Catch:{ all -> 0x00b1 }
            if (r0 < r4) goto L_0x00ba
            goto L_0x00c0
        L_0x00ba:
            boolean r0 = r6.moveToNext()     // Catch:{ all -> 0x00b1 }
            if (r0 != 0) goto L_0x0051
        L_0x00c0:
            B1.a.g(r14, r2)     // Catch:{ Exception -> 0x00e6 }
            int r12 = r3.size()     // Catch:{ Exception -> 0x00e6 }
            if (r12 <= 0) goto L_0x00e8
            h3.a r12 = new h3.a     // Catch:{ Exception -> 0x00e6 }
            r14 = 2
            r12.<init>(r13, r14)     // Catch:{ Exception -> 0x00e6 }
            com.samsung.android.vexfwk.sdk.docscan.b r13 = new com.samsung.android.vexfwk.sdk.docscan.b     // Catch:{ Exception -> 0x00e6 }
            r0 = 3
            r13.<init>(r0)     // Catch:{ Exception -> 0x00e6 }
            Ae.b[] r14 = new Ae.b[r14]     // Catch:{ Exception -> 0x00e6 }
            r14[r5] = r12     // Catch:{ Exception -> 0x00e6 }
            r12 = 1
            r14[r12] = r13     // Catch:{ Exception -> 0x00e6 }
            Z8.c r12 = new Z8.c     // Catch:{ Exception -> 0x00e6 }
            r13 = 6
            r12.<init>(r13, r14)     // Catch:{ Exception -> 0x00e6 }
            ne.C1199q.z0(r3, r12)     // Catch:{ Exception -> 0x00e6 }
            return r3
        L_0x00e6:
            r12 = move-exception
            goto L_0x00ef
        L_0x00e8:
            return r3
        L_0x00e9:
            throw r12     // Catch:{ all -> 0x00ea }
        L_0x00ea:
            r13 = move-exception
            B1.a.g(r14, r12)     // Catch:{ Exception -> 0x00e6 }
            throw r13     // Catch:{ Exception -> 0x00e6 }
        L_0x00ef:
            java.lang.String r12 = r12.getMessage()
            java.lang.String r13 = "findAlbums#query failed."
            A.a.u(r13, r12, r1)
            return r3
        L_0x00f9:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.crossapp.function.AlbumFunctions.queryAlbum(android.content.Context, com.google.android.appfunctions.schema.photos.v1.FindAlbumsParams):java.util.List");
    }

    /* access modifiers changed from: private */
    public static final Comparable queryAlbum$lambda$3$lambda$1(String str, Album album) {
        j.e(album, "it");
        return Boolean.valueOf(!v.t0(album.b, str));
    }

    /* access modifiers changed from: private */
    public static final Comparable queryAlbum$lambda$3$lambda$2(Album album) {
        j.e(album, "it");
        return album.b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object createAlbum(androidx.appfunctions.AppFunctionContext r6, com.google.android.appfunctions.schema.photos.v1.CreateAlbumParams r7, qe.C1227c r8) {
        /*
            r5 = this;
            boolean r6 = r8 instanceof com.samsung.android.gallery.crossapp.function.AlbumFunctions$createAlbum$1
            if (r6 == 0) goto L_0x0013
            r6 = r8
            com.samsung.android.gallery.crossapp.function.AlbumFunctions$createAlbum$1 r6 = (com.samsung.android.gallery.crossapp.function.AlbumFunctions$createAlbum$1) r6
            int r0 = r6.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r0 & r1
            if (r2 == 0) goto L_0x0013
            int r0 = r0 - r1
            r6.label = r0
            goto L_0x0018
        L_0x0013:
            com.samsung.android.gallery.crossapp.function.AlbumFunctions$createAlbum$1 r6 = new com.samsung.android.gallery.crossapp.function.AlbumFunctions$createAlbum$1
            r6.<init>(r5, r8)
        L_0x0018:
            java.lang.Object r8 = r6.result
            re.a r0 = re.C1245a.COROUTINE_SUSPENDED
            int r1 = r6.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0034
            if (r1 != r3) goto L_0x002c
            java.lang.Object r5 = r6.L$0
            java.util.List r5 = (java.util.List) r5
            L2.a.A(r8)
            goto L_0x0051
        L_0x002c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0034:
            L2.a.A(r8)
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            eg.f r1 = Vf.M.f3843a
            eg.e r1 = eg.e.d
            com.samsung.android.gallery.crossapp.function.AlbumFunctions$createAlbum$2 r4 = new com.samsung.android.gallery.crossapp.function.AlbumFunctions$createAlbum$2
            r4.<init>(r7, r5, r8, r2)
            r6.L$0 = r8
            r6.label = r3
            java.lang.Object r5 = Vf.D.w(r1, r4, r6)
            if (r5 != r0) goto L_0x0050
            return r0
        L_0x0050:
            r5 = r8
        L_0x0051:
            int r6 = r5.size()
            if (r6 == 0) goto L_0x005d
            r6 = 0
            java.lang.Object r5 = r5.get(r6)
            return r5
        L_0x005d:
            androidx.appfunctions.AppFunctionAppUnknownException r5 = new androidx.appfunctions.AppFunctionAppUnknownException
            r5.<init>(r2, r3, r2)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.crossapp.function.AlbumFunctions.createAlbum(androidx.appfunctions.AppFunctionContext, com.google.android.appfunctions.schema.photos.v1.CreateAlbumParams, qe.c):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [kotlin.jvm.internal.u, java.lang.Object] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object findAlbums(androidx.appfunctions.AppFunctionContext r11, com.google.android.appfunctions.schema.photos.v1.FindAlbumsParams r12, qe.C1227c r13) {
        /*
            r10 = this;
            boolean r0 = r13 instanceof com.samsung.android.gallery.crossapp.function.AlbumFunctions$findAlbums$1
            if (r0 == 0) goto L_0x0013
            r0 = r13
            com.samsung.android.gallery.crossapp.function.AlbumFunctions$findAlbums$1 r0 = (com.samsung.android.gallery.crossapp.function.AlbumFunctions$findAlbums$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.gallery.crossapp.function.AlbumFunctions$findAlbums$1 r0 = new com.samsung.android.gallery.crossapp.function.AlbumFunctions$findAlbums$1
            r0.<init>(r10, r13)
        L_0x0018:
            java.lang.Object r13 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0033
            if (r2 != r3) goto L_0x002b
            java.lang.Object r10 = r0.L$0
            kotlin.jvm.internal.u r10 = (kotlin.jvm.internal.u) r10
            L2.a.A(r13)
            goto L_0x006e
        L_0x002b:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0033:
            L2.a.A(r13)
            if (r11 == 0) goto L_0x0071
            if (r12 == 0) goto L_0x0071
            boolean r13 = r10.isValidParams(r12)
            if (r13 == 0) goto L_0x0071
            com.samsung.android.gallery.crossapp.function.CrossAppUtils$Companion r13 = com.samsung.android.gallery.crossapp.function.CrossAppUtils.Companion
            android.content.Context r2 = r11.getContext()
            r13.checkMediaAccessPermission(r2)
            kotlin.jvm.internal.u r8 = new kotlin.jvm.internal.u
            r8.<init>()
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            r8.d = r13
            eg.f r13 = Vf.M.f3843a
            eg.e r13 = eg.e.d
            com.samsung.android.gallery.crossapp.function.AlbumFunctions$findAlbums$2 r4 = new com.samsung.android.gallery.crossapp.function.AlbumFunctions$findAlbums$2
            r9 = 0
            r6 = r10
            r7 = r11
            r5 = r12
            r4.<init>(r5, r6, r7, r8, r9)
            r0.L$0 = r8
            r0.label = r3
            java.lang.Object r10 = Vf.D.w(r13, r4, r0)
            if (r10 != r1) goto L_0x006d
            return r1
        L_0x006d:
            r10 = r8
        L_0x006e:
            java.lang.Object r10 = r10.d
            return r10
        L_0x0071:
            androidx.appfunctions.AppFunctionInvalidArgumentException r10 = new androidx.appfunctions.AppFunctionInvalidArgumentException
            r11 = 0
            r10.<init>(r11, r3, r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.crossapp.function.AlbumFunctions.findAlbums(androidx.appfunctions.AppFunctionContext, com.google.android.appfunctions.schema.photos.v1.FindAlbumsParams, qe.c):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object showAlbum(androidx.appfunctions.AppFunctionContext r7, java.lang.String r8, qe.C1227c r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.samsung.android.gallery.crossapp.function.AlbumFunctions$showAlbum$1
            if (r0 == 0) goto L_0x0013
            r0 = r9
            com.samsung.android.gallery.crossapp.function.AlbumFunctions$showAlbum$1 r0 = (com.samsung.android.gallery.crossapp.function.AlbumFunctions$showAlbum$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.gallery.crossapp.function.AlbumFunctions$showAlbum$1 r0 = new com.samsung.android.gallery.crossapp.function.AlbumFunctions$showAlbum$1
            r0.<init>(r6, r9)
        L_0x0018:
            java.lang.Object r9 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r4) goto L_0x0030
            java.lang.Object r6 = r0.L$1
            android.content.Intent r6 = (android.content.Intent) r6
            java.lang.Object r7 = r0.L$0
            androidx.appfunctions.AppFunctionContext r7 = (androidx.appfunctions.AppFunctionContext) r7
            L2.a.A(r9)
            goto L_0x007d
        L_0x0030:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0038:
            L2.a.A(r9)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r2 = "showAlbum "
            r9.<init>(r2)
            r9.append(r8)
            java.lang.String r9 = r9.toString()
            java.lang.String r2 = "CrossApp#Album"
            com.samsung.android.gallery.support.utils.Log.d(r2, r9)
            android.content.Intent r9 = new android.content.Intent
            java.lang.String r2 = "com.android.gallery.action.SHORTCUT_ALBUM_VIEW"
            r9.<init>(r2)
            java.lang.String r2 = "com.sec.android.gallery3d"
            java.lang.String r5 = "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity"
            r9.setClassName(r2, r5)
            java.lang.String r2 = "ALBUM_ID"
            int r5 = java.lang.Integer.parseInt(r8)
            r9.putExtra(r2, r5)
            java.lang.String r2 = "key-album-type"
            r9.putExtra(r2, r3)
            r2 = 268435456(0x10000000, float:2.5243549E-29)
            r9.addFlags(r2)
            r0.L$0 = r7
            r0.L$1 = r9
            r0.label = r4
            java.lang.Object r6 = r6.checkValidAlbum(r8, r0)
            if (r6 != r1) goto L_0x007c
            return r1
        L_0x007c:
            r6 = r9
        L_0x007d:
            android.content.Context r7 = r7.getContext()
            r8 = 335544320(0x14000000, float:6.4623485E-27)
            android.app.PendingIntent r6 = android.app.PendingIntent.getActivity(r7, r3, r6, r8)
            java.lang.String r7 = "getActivity(...)"
            kotlin.jvm.internal.j.d(r6, r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.crossapp.function.AlbumFunctions.showAlbum(androidx.appfunctions.AppFunctionContext, java.lang.String, qe.c):java.lang.Object");
    }
}
