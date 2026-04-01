package com.samsung.android.gallery.module.fileio.compat;

import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadParams;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.File;
import java.util.HashMap;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileOpApi {
    final String TAG = getClass().getSimpleName();
    volatile HashMap<StorageType, FileOpApi> mApiMap;
    BooleanSupplier mCancelSignal;
    volatile FileOpApi mGroupApi;
    Consumer<Float> mProgressListener;

    public boolean copy(FileOpJob fileOpJob) {
        String str = this.TAG;
        Log.e(str, "copy not supported " + fileOpJob);
        return false;
    }

    public boolean delete(FileOpJob fileOpJob) {
        String str = this.TAG;
        Log.e(str, "delete not supported " + fileOpJob);
        return false;
    }

    public final boolean downloadCloudFile(FileOpJob fileOpJob) {
        FileItemInterface fileItemInterface = fileOpJob.source;
        return SamsungCloudCompat.downloadFileDirectly(AppResources.getAppContext(), DownloadParams.builder().setFileId(fileItemInterface.getFileId()).setCloudServerId(fileItemInterface.getCloudServerId()).setTargetPath(fileOpJob.target).setMediaType(fileItemInterface.getMediaType().toInt()).setMimeType(fileItemInterface.getMimeType()).setDateTaken(fileItemInterface.getDateTaken()).build());
    }

    public boolean execute(FileOpJob fileOpJob) {
        if (fileOpJob.groupOp) {
            return getGroupApi().execute(fileOpJob);
        }
        if (fileOpJob.operation == 0) {
            fileOpJob.errorCode = FileOpError.SkipDuplication;
            Log.d(this.TAG, "execute skip none operation");
            return true;
        } else if (TextUtils.equals(fileOpJob.source.getPath(), fileOpJob.target)) {
            fileOpJob.errorCode = FileOpError.SkipSameFile;
            String str = this.TAG;
            Log.d(str, "execute skip same file " + fileOpJob);
            return true;
        } else {
            FileOpApi api = getApi(fileOpJob.source.getStorageType());
            if (api == null) {
                String str2 = this.TAG;
                FileOpError fileOpError = FileOpError.StorageTypeUnsupported;
                fileOpJob.e(str2, fileOpError, "execute failed. unsupported storage-type=" + fileOpJob.source.getStorageType());
                return false;
            }
            if (PocFeatures.SUPPORT_PRIVATE_ALBUM && fileOpJob.isSecured()) {
                api = new FileOpApiSecured(api);
            }
            try {
                int i2 = fileOpJob.operation & ScoverState.TYPE_NFC_SMART_COVER;
                if (i2 == 1) {
                    return api.copy(fileOpJob);
                }
                if (i2 == 2) {
                    return api.rename(fileOpJob);
                }
                if (i2 == 4) {
                    return api.delete(fileOpJob);
                }
                if (i2 == 5) {
                    return api.move(fileOpJob);
                }
                String str3 = this.TAG;
                Log.e(str3, "execute failed. not supported operation=" + fileOpJob.operation + GlobalPostProcInternalPPInterface.SPLIT_REGEX + fileOpJob);
                return false;
            } catch (Error | Exception e) {
                String str4 = this.TAG;
                FileOpError fileOpError2 = FileOpError.FatalException;
                fileOpJob.e(str4, fileOpError2, "execute failed. e=" + e.getMessage());
            }
        }
    }

    public final FileOpApi getApi(StorageType storageType) {
        if (this.mApiMap == null) {
            this.mApiMap = new HashMap<StorageType, FileOpApi>() {
                {
                    put(StorageType.Local, new FileOpApiLocal().setProgress(FileOpApi.this.mProgressListener).setCancelSignal(FileOpApi.this.mCancelSignal));
                    put(StorageType.LocalCloud, new FileOpApiLocalCloud().setProgress(FileOpApi.this.mProgressListener).setCancelSignal(FileOpApi.this.mCancelSignal));
                    put(StorageType.Cloud, new FileOpApiCloud().setProgress(FileOpApi.this.mProgressListener).setCancelSignal(FileOpApi.this.mCancelSignal));
                    if (PocFeatures.SUPPORT_PRIVATE_ALBUM) {
                        put(StorageType.PrivateItem, new FileOpApiLocal().setProgress(FileOpApi.this.mProgressListener).setCancelSignal(FileOpApi.this.mCancelSignal));
                    }
                }
            };
        }
        return this.mApiMap.get(storageType);
    }

    public final FileOpApi getGroupApi() {
        if (this.mGroupApi == null) {
            this.mGroupApi = new FileOpApiGroup(this.mProgressListener, this.mCancelSignal);
        }
        return this.mGroupApi;
    }

    public boolean move(FileOpJob fileOpJob) {
        String str = this.TAG;
        Log.e(str, "move not supported " + fileOpJob);
        return false;
    }

    public boolean rename(FileOpJob fileOpJob) {
        String str = this.TAG;
        Log.e(str, "rename not supported " + fileOpJob);
        return false;
    }

    public final boolean requireFileExistent(String str) {
        if (str == null || !FileUtils.exists(str)) {
            return false;
        }
        return true;
    }

    public final boolean requireFileNotExistent(String str) {
        if (str == null || !str.contains(".") || FileUtils.exists(str)) {
            return false;
        }
        return true;
    }

    public final boolean requireFilesIdentical(File file, File file2) {
        long length = file.length();
        long length2 = file2.length();
        if (length == length2 && length2 != 0) {
            return true;
        }
        String str = "copy failed by requireFilesIdentical source=" + FileUtils.info(file) + ", target=" + FileUtils.info(file2);
        Log.e(this.TAG, str);
        DebugLogger.getDeleteInstance().insertLog("FileOpLog2024] " + str);
        return false;
    }

    public final FileOpApi setCancelSignal(BooleanSupplier booleanSupplier) {
        this.mCancelSignal = booleanSupplier;
        return this;
    }

    public final FileOpApi setProgress(Consumer<Float> consumer) {
        this.mProgressListener = consumer;
        return this;
    }

    public final boolean copy(File file, File file2) {
        return copy(file, file2, this.mProgressListener);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x00f6, code lost:
        r18 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x00fa, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x00fb, code lost:
        r9 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x010b, code lost:
        if (r11.delete() != false) goto L_0x010d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x010e, code lost:
        r5 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0121, code lost:
        r4 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x013f, code lost:
        if (r11.exists() != false) goto L_0x0141;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0145, code lost:
        if (r11.delete() != false) goto L_0x0147;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0148, code lost:
        r5 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x014b, code lost:
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x014c, code lost:
        r1 = r1.TAG;
        r3 = new java.lang.StringBuilder();
        r3.append("copy canceled. [clean-up=");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0158, code lost:
        if (r5 != false) goto L_0x015a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x015b, code lost:
        r4 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x015d, code lost:
        r3.append(r4);
        r3.append("] e=");
        r3.append(r0.getMessage());
        com.samsung.android.gallery.support.utils.Log.e(r1, r3.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0172, code lost:
        r1 = r1.TAG;
        com.samsung.android.gallery.support.utils.Log.e(r1, "copy failed. file not found e=" + r0.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0070, code lost:
        r19 = r3;
        com.samsung.android.gallery.support.utils.FileUtils.copyFileTime(r21, r22);
        r0 = requireFilesIdentical(r21, r22);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0079, code lost:
        if (r13 == null) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x007f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r12.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x008b, code lost:
        com.samsung.android.gallery.support.utils.ByteBufferHolder.recycle(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x008e, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0092, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0095, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0098, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x009b, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x009e, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00ad, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00be, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x00cd, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:?, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x00ed, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x00ee, code lost:
        r11 = r22;
        r19 = r3;
        r18 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x00f5, code lost:
        r0 = e;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:39:0x007b, B:62:0x00a9] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:43:0x0082, B:71:0x00ba] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:45:0x0085, B:79:0x00c9] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0107 A[Catch:{ all -> 0x008f }] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0120 A[Catch:{ all -> 0x008f }] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0121 A[Catch:{ all -> 0x008f }] */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0141 A[Catch:{ all -> 0x008f }] */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x015a A[Catch:{ all -> 0x008f }] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x015b A[Catch:{ all -> 0x008f }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00a9 A[SYNTHETIC, Splitter:B:62:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x00ed A[ExcHandler: CancellationException (e java.util.concurrent.CancellationException), Splitter:B:1:0x000b] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x00f5 A[ExcHandler: FileNotFoundException (e java.io.FileNotFoundException), Splitter:B:1:0x000b] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:117:0x013b=Splitter:B:117:0x013b, B:103:0x00fe=Splitter:B:103:0x00fe} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean copy(java.io.File r21, java.io.File r22, java.util.function.Consumer<java.lang.Float> r23) {
        /*
            r20 = this;
            r1 = r20
            r0 = r23
            java.lang.String r2 = "] e="
            java.lang.String r3 = "nok"
            java.lang.String r4 = "ok"
            r7 = 0
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x00f5, CancellationException -> 0x00ed, Exception -> 0x00fa }
            r9 = r21
            r8.<init>(r9)     // Catch:{ FileNotFoundException -> 0x00f5, CancellationException -> 0x00ed, Exception -> 0x00e5 }
            java.io.FileOutputStream r10 = new java.io.FileOutputStream     // Catch:{ all -> 0x00d8 }
            r11 = r22
            r10.<init>(r11)     // Catch:{ all -> 0x00d2 }
            java.nio.channels.FileChannel r12 = r8.getChannel()     // Catch:{ all -> 0x00c3 }
            java.nio.channels.FileChannel r13 = r10.getChannel()     // Catch:{ all -> 0x00b2 }
            long r14 = r12.size()     // Catch:{ all -> 0x00a1 }
            java.nio.ByteBuffer r7 = com.samsung.android.gallery.support.utils.ByteBufferHolder.computeIfAbsent()     // Catch:{ all -> 0x00a1 }
            r16 = 0
        L_0x002b:
            int r5 = r12.read(r7)     // Catch:{ all -> 0x00a1 }
            r18 = 0
            r6 = -1
            if (r5 == r6) goto L_0x0070
            r7.flip()     // Catch:{ all -> 0x0050 }
            r13.write(r7)     // Catch:{ all -> 0x0050 }
            r7.clear()     // Catch:{ all -> 0x0050 }
            java.util.function.BooleanSupplier r6 = r1.mCancelSignal     // Catch:{ all -> 0x0050 }
            if (r6 == 0) goto L_0x0055
            boolean r6 = r6.getAsBoolean()     // Catch:{ all -> 0x0050 }
            if (r6 != 0) goto L_0x0048
            goto L_0x0055
        L_0x0048:
            java.util.concurrent.CancellationException r0 = new java.util.concurrent.CancellationException     // Catch:{ all -> 0x0050 }
            java.lang.String r5 = "CancelSignal detected while copying"
            r0.<init>(r5)     // Catch:{ all -> 0x0050 }
            throw r0     // Catch:{ all -> 0x0050 }
        L_0x0050:
            r0 = move-exception
            r19 = r3
        L_0x0053:
            r3 = r0
            goto L_0x00a7
        L_0x0055:
            if (r0 == 0) goto L_0x002b
            long r5 = (long) r5
            long r5 = r16 + r5
            r19 = r3
            float r3 = (float) r5
            r16 = r3
            float r3 = (float) r14
            float r3 = r16 / r3
            java.lang.Float r3 = java.lang.Float.valueOf(r3)     // Catch:{ all -> 0x006e }
            r0.accept(r3)     // Catch:{ all -> 0x006e }
            r16 = r5
            r3 = r19
            goto L_0x002b
        L_0x006e:
            r0 = move-exception
            goto L_0x0053
        L_0x0070:
            r19 = r3
            com.samsung.android.gallery.support.utils.FileUtils.copyFileTime(r21, r22)     // Catch:{ all -> 0x006e }
            boolean r0 = r20.requireFilesIdentical(r21, r22)     // Catch:{ all -> 0x006e }
            if (r13 == 0) goto L_0x0082
            r13.close()     // Catch:{ all -> 0x007f }
            goto L_0x0082
        L_0x007f:
            r0 = move-exception
        L_0x0080:
            r3 = r0
            goto L_0x00b8
        L_0x0082:
            r12.close()     // Catch:{ all -> 0x009e }
            r10.close()     // Catch:{ all -> 0x009b }
            r8.close()     // Catch:{ FileNotFoundException -> 0x0098, CancellationException -> 0x0095, Exception -> 0x0092 }
            com.samsung.android.gallery.support.utils.ByteBufferHolder.recycle(r7)
            return r0
        L_0x008f:
            r0 = move-exception
            goto L_0x018e
        L_0x0092:
            r0 = move-exception
            goto L_0x00fe
        L_0x0095:
            r0 = move-exception
            goto L_0x013b
        L_0x0098:
            r0 = move-exception
            goto L_0x0172
        L_0x009b:
            r0 = move-exception
        L_0x009c:
            r3 = r0
            goto L_0x00dc
        L_0x009e:
            r0 = move-exception
        L_0x009f:
            r3 = r0
            goto L_0x00c9
        L_0x00a1:
            r0 = move-exception
            r19 = r3
            r18 = 0
            goto L_0x0053
        L_0x00a7:
            if (r13 == 0) goto L_0x00b1
            r13.close()     // Catch:{ all -> 0x00ad }
            goto L_0x00b1
        L_0x00ad:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch:{ all -> 0x007f }
        L_0x00b1:
            throw r3     // Catch:{ all -> 0x007f }
        L_0x00b2:
            r0 = move-exception
            r19 = r3
            r18 = 0
            goto L_0x0080
        L_0x00b8:
            if (r12 == 0) goto L_0x00c2
            r12.close()     // Catch:{ all -> 0x00be }
            goto L_0x00c2
        L_0x00be:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch:{ all -> 0x009e }
        L_0x00c2:
            throw r3     // Catch:{ all -> 0x009e }
        L_0x00c3:
            r0 = move-exception
            r19 = r3
            r18 = 0
            goto L_0x009f
        L_0x00c9:
            r10.close()     // Catch:{ all -> 0x00cd }
            goto L_0x00d1
        L_0x00cd:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch:{ all -> 0x009b }
        L_0x00d1:
            throw r3     // Catch:{ all -> 0x009b }
        L_0x00d2:
            r0 = move-exception
        L_0x00d3:
            r19 = r3
            r18 = 0
            goto L_0x009c
        L_0x00d8:
            r0 = move-exception
            r11 = r22
            goto L_0x00d3
        L_0x00dc:
            r8.close()     // Catch:{ all -> 0x00e0 }
            goto L_0x00e4
        L_0x00e0:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch:{ FileNotFoundException -> 0x0098, CancellationException -> 0x0095, Exception -> 0x0092 }
        L_0x00e4:
            throw r3     // Catch:{ FileNotFoundException -> 0x0098, CancellationException -> 0x0095, Exception -> 0x0092 }
        L_0x00e5:
            r0 = move-exception
        L_0x00e6:
            r11 = r22
            r19 = r3
            r18 = 0
            goto L_0x00fe
        L_0x00ed:
            r0 = move-exception
            r11 = r22
            r19 = r3
            r18 = 0
            goto L_0x013b
        L_0x00f5:
            r0 = move-exception
            r18 = 0
            goto L_0x0172
        L_0x00fa:
            r0 = move-exception
            r9 = r21
            goto L_0x00e6
        L_0x00fe:
            r20.requireFilesIdentical(r21, r22)     // Catch:{ all -> 0x008f }
            boolean r3 = r11.exists()     // Catch:{ all -> 0x008f }
            if (r3 == 0) goto L_0x0111
            boolean r3 = r11.delete()     // Catch:{ all -> 0x008f }
            if (r3 == 0) goto L_0x010e
            goto L_0x0111
        L_0x010e:
            r5 = r18
            goto L_0x0112
        L_0x0111:
            r5 = 1
        L_0x0112:
            java.lang.String r1 = r1.TAG     // Catch:{ all -> 0x008f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x008f }
            r3.<init>()     // Catch:{ all -> 0x008f }
            java.lang.String r6 = "copy failed. [clean-up="
            r3.append(r6)     // Catch:{ all -> 0x008f }
            if (r5 == 0) goto L_0x0121
            goto L_0x0123
        L_0x0121:
            r4 = r19
        L_0x0123:
            r3.append(r4)     // Catch:{ all -> 0x008f }
            r3.append(r2)     // Catch:{ all -> 0x008f }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x008f }
            r3.append(r0)     // Catch:{ all -> 0x008f }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x008f }
            com.samsung.android.gallery.support.utils.Log.e(r1, r0)     // Catch:{ all -> 0x008f }
        L_0x0137:
            com.samsung.android.gallery.support.utils.ByteBufferHolder.recycle(r7)
            goto L_0x018d
        L_0x013b:
            boolean r3 = r11.exists()     // Catch:{ all -> 0x008f }
            if (r3 == 0) goto L_0x014b
            boolean r3 = r11.delete()     // Catch:{ all -> 0x008f }
            if (r3 == 0) goto L_0x0148
            goto L_0x014b
        L_0x0148:
            r5 = r18
            goto L_0x014c
        L_0x014b:
            r5 = 1
        L_0x014c:
            java.lang.String r1 = r1.TAG     // Catch:{ all -> 0x008f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x008f }
            r3.<init>()     // Catch:{ all -> 0x008f }
            java.lang.String r6 = "copy canceled. [clean-up="
            r3.append(r6)     // Catch:{ all -> 0x008f }
            if (r5 == 0) goto L_0x015b
            goto L_0x015d
        L_0x015b:
            r4 = r19
        L_0x015d:
            r3.append(r4)     // Catch:{ all -> 0x008f }
            r3.append(r2)     // Catch:{ all -> 0x008f }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x008f }
            r3.append(r0)     // Catch:{ all -> 0x008f }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x008f }
            com.samsung.android.gallery.support.utils.Log.e(r1, r0)     // Catch:{ all -> 0x008f }
            goto L_0x0137
        L_0x0172:
            java.lang.String r1 = r1.TAG     // Catch:{ all -> 0x008f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x008f }
            r2.<init>()     // Catch:{ all -> 0x008f }
            java.lang.String r3 = "copy failed. file not found e="
            r2.append(r3)     // Catch:{ all -> 0x008f }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x008f }
            r2.append(r0)     // Catch:{ all -> 0x008f }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x008f }
            com.samsung.android.gallery.support.utils.Log.e(r1, r0)     // Catch:{ all -> 0x008f }
            goto L_0x0137
        L_0x018d:
            return r18
        L_0x018e:
            com.samsung.android.gallery.support.utils.ByteBufferHolder.recycle(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.fileio.compat.FileOpApi.copy(java.io.File, java.io.File, java.util.function.Consumer):boolean");
    }
}
