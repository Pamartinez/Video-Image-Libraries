package com.samsung.android.gallery.plugins.filebrowser;

import B8.b;
import B8.d;
import C3.p;
import K7.a;
import O3.u;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.fileio.compat.MediaStoreApi;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.plugins.R$layout;
import com.samsung.android.gallery.plugins.R$string;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sum.core.types.NumericEnum;
import com.samsung.scsp.common.ContentType;
import com.samsung.scsp.media.file.FileApiContract;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;
import ta.C0708b;
import x6.f;
import y7.j;
import za.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileListFragment extends FileBaseFragment {
    protected final String ROOT;
    protected final String ROOT_NAME;
    private final HashMap<String, FolderInfo> mFolderMap = new HashMap<>();
    protected RecyclerView mHeaderView;
    protected RecyclerView mListView;
    protected final HashMap<Integer, int[]> mPosMap = new HashMap<>();
    private final AtomicBoolean mProcessing = new AtomicBoolean(false);
    protected String mRelativePath = "";

    public FileListFragment() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(StorageInfo.getDefault().media);
        String str = File.separator;
        String p6 = C0212a.p(sb2, str, "com.sec.android.gallery3d");
        this.ROOT_NAME = p6;
        this.ROOT = C0212a.A(p6, str);
    }

    private int getLayoutId() {
        return R$layout.fragment_file_list_layout;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$computePositionMap$5(int i2, int i7, LinearLayoutManager linearLayoutManager) {
        int findFirstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
        int intValue = ((Integer) Optional.ofNullable(linearLayoutManager.findViewByPosition(findFirstCompletelyVisibleItemPosition)).map(new j(2)).orElse(0)).intValue();
        this.mPosMap.put(Integer.valueOf(i2), new int[]{findFirstCompletelyVisibleItemPosition, intValue});
        Log.d(this.TAG, "computePositionMap#add", Integer.valueOf(i2), Integer.valueOf(i7), N2.j.b(findFirstCompletelyVisibleItemPosition, intValue, "", NumericEnum.SEP));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$computePositionMap$6(int i2, Integer num) {
        if (num.intValue() > i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadAndUpdate$7(String str) {
        FolderInfo computeIfFolder = new FolderInfo(str).computeIfFolder();
        this.mFolderMap.put(str, computeIfFolder);
        update(computeIfFolder);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindView$0(View view) {
        loadAndUpdate("");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindView$2() {
        if (!TextUtils.isEmpty(this.mPath)) {
            initData(this.mPath);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onClick$16(Fragment fragment) {
        commitFragment(fragment);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onClick$17() {
        this.mProcessing.set(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onClick$18(FileViewHolder fileViewHolder) {
        loadAndStartViewer(getContext(), this.mRelativePath, fileViewHolder.data);
        ThreadUtil.postOnBgThreadDelayed(new c(this, 0), 500);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onClick$19(String str, Uri uri) {
        openFile(uri);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onLongClick$12(Context context, FileViewHolder fileViewHolder, DialogInterface dialogInterface, int i2) {
        onMenuSelected(context, R$string.share, fileViewHolder.data);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onLongClick$13(Context context, FileViewHolder fileViewHolder, DialogInterface dialogInterface, int i2) {
        onMenuSelected(context, R$string.delete, fileViewHolder.data);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onLongClick$14(Context context, FileViewHolder fileViewHolder, DialogInterface dialogInterface, int i2) {
        onMenuSelected(context, R$string.share, fileViewHolder.data);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onLongClick$15(Context context, FileViewHolder fileViewHolder, DialogInterface dialogInterface, int i2) {
        onMenuSelected(context, R$string.delete, fileViewHolder.data);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$update$10(PathListAdapter pathListAdapter) {
        pathListAdapter.update(this.mRelativePath);
        int depth = getDepth(this.mRelativePath) - 1;
        RecyclerView recyclerView = this.mHeaderView;
        if (recyclerView != null && depth > 0) {
            recyclerView.smoothScrollToPosition(depth);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$update$11(PathListAdapter pathListAdapter) {
        ThreadUtil.runOnUiThread(new h(this, pathListAdapter, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$update$8(FileListAdapter fileListAdapter, FileInfo fileInfo) {
        fileListAdapter.update(fileInfo.list());
        scrollToPosition(this.mRelativePath);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$update$9(FileInfo fileInfo, FileListAdapter fileListAdapter) {
        ThreadUtil.runOnUiThread(new j((Object) this, (Object) fileListAdapter, (Object) fileInfo, 0));
    }

    public void computePositionMap(String str) {
        RecyclerView recyclerView = this.mListView;
        if (recyclerView != null) {
            int depth = getDepth(this.mRelativePath);
            int depth2 = getDepth(str);
            if (depth2 > depth) {
                Optional.ofNullable((LinearLayoutManager) recyclerView.getLayoutManager()).ifPresent(new a(this, depth, depth2, 4));
                return;
            }
            Stream filter = new ArrayList(this.mPosMap.keySet()).stream().filter(new b(depth2, 27));
            HashMap<Integer, int[]> hashMap = this.mPosMap;
            Objects.requireNonNull(hashMap);
            filter.forEach(new p(12, hashMap));
            Log.d(this.TAG, "computePositionMap#clear", Integer.valueOf(depth), Integer.valueOf(depth2), Integer.valueOf(this.mPosMap.size()));
        }
    }

    public int getDepth(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return str.split(File.separator).length;
    }

    public String getTitle() {
        return "File Browser";
    }

    public View inflateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(getLayoutId(), viewGroup, false);
    }

    public void initData(String str) {
        loadAndUpdate(str.replace(this.ROOT, ""));
    }

    public boolean isBackAllowed() {
        return this.mRelativePath.isEmpty();
    }

    public void loadAndStartViewer(Context context, String str, FileInfo fileInfo) {
        int i2;
        long currentTimeMillis = System.currentTimeMillis();
        String str2 = fileInfo.name;
        String parent = FileUtils.getParent(str2);
        int bucketId = FileUtils.getBucketId(parent);
        ArrayList arrayList = new ArrayList();
        if (str2.startsWith("/data/sec/")) {
            MediaItem loadSecuredFile = loadSecuredFile(context, fileInfo);
            if (loadSecuredFile != null) {
                arrayList.add(loadSecuredFile);
                i2 = 0;
            } else {
                return;
            }
        } else {
            HashMap<String, MediaItem> loadGedDatabase = loadGedDatabase(context, bucketId);
            FolderInfo folderInfo = this.mFolderMap.get(parent);
            if (folderInfo != null) {
                Iterator<FileInfo> it = folderInfo.files.iterator();
                while (it.hasNext()) {
                    Optional.ofNullable(loadGedDatabase.get(it.next().name)).ifPresent(new d(arrayList, 23));
                }
            }
            i2 = ((Integer) Optional.ofNullable(loadGedDatabase.get(str2)).map(new A5.a(13, arrayList)).orElse(0)).intValue();
        }
        Log.e(this.TAG, "loadAndStartViewer" + Logger.vt(Integer.valueOf(arrayList.size()), Integer.valueOf(i2), Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis)));
        Blackboard.getApplicationInstance().publish("data://fm/dir/array", (MediaItem[]) arrayList.toArray(new MediaItem[0]));
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setClassName("com.sec.android.gallery3d", "com.sec.android.gallery3d.app.GalleryActivity");
            intent.setType("image/*");
            intent.putExtra("quick-view-internal", true);
            intent.putExtra("quick-view-data-location", "data://fm/dir/array");
            intent.putExtra("quick-view-data-position", i2);
            context.startActivity(intent);
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("loadAndStartViewer failed. e="), this.TAG);
        }
    }

    public void loadAndUpdate(String str) {
        String str2;
        Log.i(this.TAG, "loadAndUpdate", str);
        computePositionMap(str);
        this.mRelativePath = str;
        notifyBackAllowStateChange();
        if (TextUtils.isEmpty(str)) {
            str2 = this.ROOT_NAME;
        } else {
            str2 = C0212a.p(new StringBuilder(), this.ROOT, str);
        }
        FolderInfo folderInfo = this.mFolderMap.get(str2);
        if (folderInfo != null) {
            update(folderInfo);
        } else {
            SimpleThreadPool.getInstance().execute(new f(12, this, str2));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00aa, code lost:
        if (r15.startsWith("video/") == false) goto L_0x00ac;
     */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0185 A[LOOP:0: B:6:0x0091->B:42:0x0185, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01ad  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x01ab A[EDGE_INSN: B:52:0x01ab->B:49:0x01ab ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.HashMap<java.lang.String, com.samsung.android.gallery.module.data.MediaItem> loadGedDatabase(android.content.Context r38, int r39) {
        /*
            r37 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.String r1 = "external"
            android.net.Uri r3 = android.provider.MediaStore.Files.getContentUri(r1)
            java.lang.String r14 = "_size"
            java.lang.String r15 = "volume_name"
            java.lang.String r4 = "_id"
            java.lang.String r5 = "_data"
            java.lang.String r6 = "title"
            java.lang.String r7 = "media_type"
            java.lang.String r8 = "mime_type"
            java.lang.String r9 = "width"
            java.lang.String r10 = "height"
            java.lang.String r11 = "orientation"
            java.lang.String r12 = "date_modified"
            java.lang.String r13 = "datetaken"
            java.lang.String[] r4 = new java.lang.String[]{r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15}
            java.lang.String r2 = "bucket_id="
            java.lang.String r5 = " AND is_drm=0 AND is_drm!=1 AND is_trashed!=1 AND media_type in (0,1,3)"
            r6 = r39
            java.lang.String r5 = i.C0212a.j(r6, r2, r5)
            android.content.ContentResolver r2 = r38.getContentResolver()
            r6 = 0
            r7 = 0
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7)
            if (r2 == 0) goto L_0x019f
            boolean r3 = r2.moveToFirst()     // Catch:{ all -> 0x0176 }
            if (r3 == 0) goto L_0x019f
            java.lang.String r3 = "_id"
            int r3 = r2.getColumnIndex(r3)     // Catch:{ all -> 0x0176 }
            java.lang.String r4 = "_data"
            int r4 = r2.getColumnIndex(r4)     // Catch:{ all -> 0x0176 }
            java.lang.String r5 = "title"
            int r5 = r2.getColumnIndex(r5)     // Catch:{ all -> 0x0176 }
            java.lang.String r6 = "mime_type"
            int r6 = r2.getColumnIndex(r6)     // Catch:{ all -> 0x0176 }
            java.lang.String r7 = "media_type"
            int r7 = r2.getColumnIndex(r7)     // Catch:{ all -> 0x0176 }
            java.lang.String r8 = "width"
            int r8 = r2.getColumnIndex(r8)     // Catch:{ all -> 0x0176 }
            java.lang.String r9 = "height"
            int r9 = r2.getColumnIndex(r9)     // Catch:{ all -> 0x0176 }
            java.lang.String r10 = "orientation"
            int r10 = r2.getColumnIndex(r10)     // Catch:{ all -> 0x0176 }
            java.lang.String r11 = "date_modified"
            int r11 = r2.getColumnIndex(r11)     // Catch:{ all -> 0x0176 }
            java.lang.String r12 = "datetaken"
            int r12 = r2.getColumnIndex(r12)     // Catch:{ all -> 0x0176 }
            java.lang.String r13 = "_size"
            int r13 = r2.getColumnIndex(r13)     // Catch:{ all -> 0x0176 }
            java.lang.String r14 = "volume_name"
            int r14 = r2.getColumnIndex(r14)     // Catch:{ all -> 0x0176 }
        L_0x0091:
            java.lang.String r15 = r2.getString(r6)     // Catch:{ all -> 0x0176 }
            if (r15 == 0) goto L_0x017a
            r37 = r1
            java.lang.String r1 = "image/"
            boolean r1 = r15.startsWith(r1)     // Catch:{ all -> 0x0176 }
            r38 = r1
            java.lang.String r1 = "video/"
            if (r38 != 0) goto L_0x00ca
            boolean r16 = r15.startsWith(r1)     // Catch:{ all -> 0x00c4 }
            if (r16 != 0) goto L_0x00ca
        L_0x00ac:
            r35 = r2
            r38 = r3
            r39 = r4
            r32 = r5
            r33 = r6
            r34 = r7
            r16 = r8
            r36 = r9
            r17 = r10
            r18 = r11
            r20 = r12
            goto L_0x017e
        L_0x00c4:
            r0 = move-exception
            r1 = r0
            r35 = r2
            goto L_0x01a2
        L_0x00ca:
            int r16 = r2.getInt(r7)     // Catch:{ all -> 0x0176 }
            long r21 = r2.getLong(r3)     // Catch:{ all -> 0x0176 }
            java.lang.String r18 = r2.getString(r4)     // Catch:{ all -> 0x0176 }
            r38 = r3
            java.lang.String r3 = r2.getString(r5)     // Catch:{ all -> 0x0176 }
            int r30 = r2.getInt(r8)     // Catch:{ all -> 0x0176 }
            int r31 = r2.getInt(r9)     // Catch:{ all -> 0x0176 }
            int r26 = r2.getInt(r10)     // Catch:{ all -> 0x0176 }
            r39 = r4
            r32 = r5
            long r4 = r2.getLong(r11)     // Catch:{ all -> 0x0176 }
            long r24 = r2.getLong(r12)     // Catch:{ all -> 0x0176 }
            r33 = r6
            int r6 = r2.getInt(r13)     // Catch:{ all -> 0x0176 }
            r34 = r7
            java.lang.String r7 = r2.getString(r14)     // Catch:{ all -> 0x0176 }
            if (r16 != 0) goto L_0x0109
            com.samsung.android.gallery.database.dbtype.StorageType r17 = com.samsung.android.gallery.database.dbtype.StorageType.UriItem     // Catch:{ all -> 0x00c4 }
        L_0x0104:
            r35 = r2
            r2 = r17
            goto L_0x010c
        L_0x0109:
            com.samsung.android.gallery.database.dbtype.StorageType r17 = com.samsung.android.gallery.database.dbtype.StorageType.Local     // Catch:{ all -> 0x0176 }
            goto L_0x0104
        L_0x010c:
            com.samsung.android.gallery.module.data.MediaItem r17 = new com.samsung.android.gallery.module.data.MediaItem     // Catch:{ all -> 0x0161 }
            com.samsung.android.gallery.database.dbtype.MediaType r23 = com.samsung.android.gallery.database.dbtype.MediaType.valueOf((int) r16)     // Catch:{ all -> 0x0161 }
            r27 = -1
            r16 = r8
            r36 = r9
            long r8 = (long) r6     // Catch:{ all -> 0x0161 }
            r19 = -1
            r28 = r8
            r17.<init>(r18, r19, r21, r23, r24, r26, r27, r28, r30, r31)     // Catch:{ all -> 0x0161 }
            r6 = r17
            r17 = r10
            r10 = r6
            r6 = r18
            r8 = r21
            r18 = r11
            com.samsung.android.gallery.database.dbtype.StorageType r11 = com.samsung.android.gallery.database.dbtype.StorageType.UriItem     // Catch:{ all -> 0x0161 }
            if (r2 != r11) goto L_0x0164
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0161 }
            r11.<init>()     // Catch:{ all -> 0x0161 }
            android.net.Uri r19 = android.provider.MediaStore.Files.getContentUri(r37)     // Catch:{ all -> 0x0161 }
            r20 = r12
            java.lang.String r12 = r19.toString()     // Catch:{ all -> 0x0161 }
            r11.append(r12)     // Catch:{ all -> 0x0161 }
            java.lang.String r12 = "/"
            r11.append(r12)     // Catch:{ all -> 0x0161 }
            r11.append(r8)     // Catch:{ all -> 0x0161 }
            java.lang.String r8 = r11.toString()     // Catch:{ all -> 0x0161 }
            r10.setPath(r8)     // Catch:{ all -> 0x0161 }
            boolean r1 = r15.startsWith(r1)     // Catch:{ all -> 0x0161 }
            if (r1 == 0) goto L_0x0158
            r1 = 3
            goto L_0x0159
        L_0x0158:
            r1 = 1
        L_0x0159:
            com.samsung.android.gallery.database.dbtype.MediaType r1 = com.samsung.android.gallery.database.dbtype.MediaType.valueOf((int) r1)     // Catch:{ all -> 0x0161 }
            r10.setMediaType(r1)     // Catch:{ all -> 0x0161 }
            goto L_0x0166
        L_0x0161:
            r0 = move-exception
        L_0x0162:
            r1 = r0
            goto L_0x01a2
        L_0x0164:
            r20 = r12
        L_0x0166:
            r10.setStorageType(r2)     // Catch:{ all -> 0x0161 }
            r10.setDateModified(r4)     // Catch:{ all -> 0x0161 }
            r10.setTitle(r3)     // Catch:{ all -> 0x0161 }
            r10.setVolumeName(r7)     // Catch:{ all -> 0x0161 }
            r0.put(r6, r10)     // Catch:{ all -> 0x0161 }
            goto L_0x017e
        L_0x0176:
            r0 = move-exception
            r35 = r2
            goto L_0x0162
        L_0x017a:
            r37 = r1
            goto L_0x00ac
        L_0x017e:
            boolean r1 = r35.moveToNext()     // Catch:{ all -> 0x0161 }
            if (r1 != 0) goto L_0x0185
            goto L_0x01ab
        L_0x0185:
            r1 = r37
            r3 = r38
            r4 = r39
            r8 = r16
            r10 = r17
            r11 = r18
            r12 = r20
            r5 = r32
            r6 = r33
            r7 = r34
            r2 = r35
            r9 = r36
            goto L_0x0091
        L_0x019f:
            r35 = r2
            goto L_0x01ab
        L_0x01a2:
            r35.close()     // Catch:{ all -> 0x01a6 }
            goto L_0x01aa
        L_0x01a6:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x01aa:
            throw r1
        L_0x01ab:
            if (r35 == 0) goto L_0x01b0
            r35.close()
        L_0x01b0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.plugins.filebrowser.FileListFragment.loadGedDatabase(android.content.Context, int):java.util.HashMap");
    }

    public MediaItem loadSecuredFile(Context context, FileInfo fileInfo) {
        MediaType mediaType;
        try {
            if (!fileInfo.isMedia()) {
                return null;
            }
            Uri fileProviderUri = FileProviderUtil.getFileProviderUri(context, fileInfo.name);
            if (fileInfo.getMimeType().startsWith("image/")) {
                mediaType = MediaType.Image;
            } else {
                mediaType = MediaType.Video;
            }
            MediaItem mediaItem = new MediaItem(fileInfo.getMimeType(), mediaType, StorageType.UriItem);
            MediaInfo mediaInfo = fileInfo.mediaInfo;
            if (mediaInfo != null) {
                mediaItem.setOrientation(mediaInfo.orientation);
                mediaItem.setOrientationTag(fileInfo.mediaInfo.orientationTag);
                MediaInfo mediaInfo2 = fileInfo.mediaInfo;
                mediaItem.setSize(mediaInfo2.width, mediaInfo2.height);
            }
            mediaItem.setFileSize(fileInfo.size);
            mediaItem.setDisplayName(fileInfo.getDisplayName());
            mediaItem.setPath(fileProviderUri.toString());
            return mediaItem;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public /* bridge */ /* synthetic */ void onAttach(Context context) {
        super.onAttach(context);
    }

    public boolean onBackPressed() {
        String str;
        if (this.mRelativePath.isEmpty()) {
            return false;
        }
        int lastIndexOf = this.mRelativePath.lastIndexOf("/");
        if (lastIndexOf < 0) {
            str = "";
        } else {
            str = this.mRelativePath.substring(0, lastIndexOf);
        }
        this.mRelativePath = str;
        Log.d(this.TAG, "onBackPressed", str);
        loadAndUpdate(this.mRelativePath);
        return true;
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [java.util.function.Consumer, java.lang.Object] */
    public void onBindView(ViewGroup viewGroup) {
        super.onBindView(viewGroup);
        viewGroup.findViewById(R$id.home).setOnClickListener(new z5.f(3, this));
        RecyclerView recyclerView = (RecyclerView) viewGroup.findViewById(R$id.header);
        this.mHeaderView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(viewGroup.getContext(), 0, false));
        this.mHeaderView.setAdapter(new PathListAdapter(new y5.a(6, this)));
        Optional.ofNullable((PathListAdapter) this.mHeaderView.getAdapter()).ifPresent(new Object());
        RecyclerView recyclerView2 = (RecyclerView) viewGroup.findViewById(R$id.listview);
        this.mListView = recyclerView2;
        recyclerView2.setLayoutManager(new LinearLayoutManager(viewGroup.getContext(), 1, false));
        this.mListView.setAdapter(new FileListAdapter().setClickListener(new l(this, 0)).setLongClickListener(new l(this, 1)));
        SimpleThreadPool.getInstance().execute(new c(this, 1));
    }

    public void onClick(String str) {
        Log.i(this.TAG, "onClick", str);
        if (!str.equals(this.mRelativePath)) {
            loadAndUpdate(str);
        }
    }

    public /* bridge */ /* synthetic */ View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public /* bridge */ /* synthetic */ void onDestroyView() {
        super.onDestroyView();
    }

    public /* bridge */ /* synthetic */ void onDetach() {
        super.onDetach();
    }

    public void onLongClick(FileViewHolder fileViewHolder) {
        String str;
        String str2 = this.TAG;
        StringBuilder sb2 = new StringBuilder("onLongClick#");
        if (fileViewHolder.data.isDir()) {
            str = "folder";
        } else {
            str = "file";
        }
        sb2.append(str);
        sb2.append(" ");
        sb2.append(fileViewHolder);
        Log.d(str2, sb2.toString());
        Context context = getContext();
        if (context != null && fileViewHolder.data.isFile()) {
            AlertDialog.Builder title = new AlertDialog.Builder(context).setTitle(FileUtils.getNameFromPath(fileViewHolder.data.getDisplayName()));
            if (fileViewHolder.data.isCompressed()) {
                title.setNegativeButton(R$string.share, new i(this, context, fileViewHolder, 0));
            } else if (fileViewHolder.data.isSecured()) {
                title.setMessage("Once the file is deleted, you cannot revert the edited file to original in editors").setPositiveButton(R$string.delete, new i(this, context, fileViewHolder, 1));
            } else {
                title.setNegativeButton(R$string.share, new i(this, context, fileViewHolder, 2)).setPositiveButton(R$string.delete, new i(this, context, fileViewHolder, 3));
            }
            title.create().show();
        }
    }

    public void onMenuSelected(Context context, int i2, FileInfo fileInfo) {
        String str;
        String str2 = fileInfo.name;
        if (i2 == R$string.delete) {
            Log.d(this.TAG, "onMenuSelected#delete", fileInfo);
            if (FileUtils.delete(str2)) {
                if (!fileInfo.isSecured()) {
                    MediaScanner.scanFile(str2, (MediaScannerListener) null);
                }
                HashMap<String, FolderInfo> hashMap = this.mFolderMap;
                hashMap.remove(this.ROOT + this.mRelativePath);
                loadAndUpdate(this.mRelativePath);
            }
        } else if (i2 == R$string.share) {
            if (fileInfo.isCompressed()) {
                str2 = new File(FileUtils.getExternalFilesDir(".share"), str2).getPath();
                byte[] read = fileInfo.entry.read();
                FileUtils.delete(str2);
                FileUtils.writeFile(str2, read);
            }
            Uri uri = FileProviderUtil.getUri(context, str2);
            Log.d(this.TAG, "onMenuSelected#share", fileInfo, Long.valueOf(FileUtils.length(str2)));
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.STREAM", uri);
            if (fileInfo.isMedia()) {
                str = fileInfo.getMimeType();
            } else {
                str = ContentType.OCTET_STREAM;
            }
            intent.setType(str);
            context.startActivity(Intent.createChooser(intent, "Share file"));
        }
    }

    public /* bridge */ /* synthetic */ void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void openFile(Uri uri) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(uri, "text/html");
            intent.addFlags(268435457);
            Log.d(this.TAG, "openFile", uri);
            getContext().startActivity(intent);
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("openFile failed. e="), this.TAG);
        }
    }

    public void scrollToPosition(String str) {
        RecyclerView recyclerView = this.mListView;
        if (recyclerView != null) {
            int[] iArr = this.mPosMap.get(Integer.valueOf(getDepth(str)));
            if (iArr == null || iArr[0] == 0) {
                recyclerView.scrollToPosition(0);
            } else {
                Optional.ofNullable((LinearLayoutManager) recyclerView.getLayoutManager()).ifPresent(new C0708b(iArr, 2));
            }
        }
    }

    public void update(FileInfo fileInfo) {
        Optional.ofNullable((FileListAdapter) this.mListView.getAdapter()).ifPresent(new m(0, this, fileInfo));
        Optional.ofNullable((PathListAdapter) this.mHeaderView.getAdapter()).ifPresent(new l(this, 2));
    }

    public void onClick(FileViewHolder fileViewHolder) {
        String str = fileViewHolder.data.name;
        String str2 = this.TAG;
        StringBuilder sb2 = new StringBuilder("onClick#");
        sb2.append(fileViewHolder.data.isDir() ? "folder" : "file");
        sb2.append(" ");
        sb2.append(fileViewHolder);
        Log.d(str2, sb2.toString());
        if (fileViewHolder.data.isDir()) {
            loadAndUpdate(str.replace(this.ROOT, ""));
        } else if (str.endsWith(".zip")) {
            FilePreviewFragment filePreviewFragment = new FilePreviewFragment();
            Bundle bundle = new Bundle();
            bundle.putString(FileApiContract.Parameter.PATH, str);
            filePreviewFragment.setArguments(bundle);
            this.mListView.postDelayed(new f(11, this, filePreviewFragment), 120);
        } else if (!fileViewHolder.data.isMedia()) {
            String lowerCase = FileUtils.getExtension(str).toLowerCase();
            if ("log".equals(lowerCase)) {
                LogViewFragment logViewFragment = new LogViewFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString(FileApiContract.Parameter.PATH, str);
                logViewFragment.setArguments(bundle2);
                commitFragment(logViewFragment);
            } else if ("txt".equals(lowerCase) || "pdf".equals(lowerCase)) {
                Uri uri = MediaStoreApi.toUri(str);
                if (uri == null) {
                    MediaScannerConnection.scanFile(getContext(), new String[]{str}, (String[]) null, new u(3, this));
                } else {
                    openFile(uri);
                }
            }
        } else if (this.mProcessing.compareAndSet(false, true)) {
            SimpleThreadPool.getInstance().execute(new h(this, fileViewHolder, 1));
        } else {
            Log.w(this.TAG, "onClick skip. under processing");
        }
    }
}
