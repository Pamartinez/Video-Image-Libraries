package com.samsung.android.gallery.module.dataset;

import A.a;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.trash.factory.TrashProviderFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.Def;
import i.C0212a;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PppUpdater {
    protected final ConcurrentHashMap<Long, MediaItem> mPppUpdateMap = new ConcurrentHashMap<>();

    private static boolean compareInt(int i2, int i7, String str) {
        if (i2 == i7) {
            return true;
        }
        StringBuilder u = C0212a.u("ppp completed file ", str, " from db not matched with temp file. this cause image display error : ", i2, "->");
        u.append(i7);
        String sb2 = u.toString();
        if (!DeviceConfig.DEBUG_BINARY) {
            Log.e("PppUpdater", sb2);
            return false;
        }
        throw new IllegalStateException(sb2);
    }

    private boolean isItemInTrash(Context context, long j2) {
        Cursor trashCursor = TrashProviderFactory.getInstance(context).getTrashCursor(j2);
        if (trashCursor != null) {
            try {
                if (trashCursor.moveToFirst()) {
                    trashCursor.close();
                    return true;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (trashCursor == null) {
            return false;
        }
        trashCursor.close();
        return false;
        throw th;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onUpdatePppMediaItem$2(MediaItem mediaItem, MediaItem mediaItem2) {
        if (!mediaItem2.isCommonPostProcessing() || mediaItem.getFileId() != mediaItem2.getFileId()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onUpdatePppMediaItem$3(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem.getFileId() == mediaItem2.getFileId()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onUpdatePppMediaItem$4(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem.getMediaId() == mediaItem2.getMediaId()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onUpdatePppMediaItem$5(String str, MediaItem mediaItem, MediaDataRef mediaDataRef) {
        StringBuilder t = C0212a.t(str, "onUpdatePppMediaItem2 matching failed Retry {");
        t.append(mediaItem.getFileId());
        t.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        t.append(mediaDataRef.isDataAvailable());
        t.append("}");
        Log.w("PppUpdater", t.toString());
        mediaDataRef.requestData(mediaDataRef.getLocationReference());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
        r7.releaseWriteLock((java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) "PppUpdater", "fail replace ppp : ".concat(r7.getClass().getSimpleName()), r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002d, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0017 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$replaceItem$0(com.samsung.android.gallery.module.data.MediaItem r5, com.samsung.android.gallery.module.data.MediaItem r6, com.samsung.android.gallery.module.dataset.MediaDataRef r7, int r8) {
        /*
            r4 = this;
            java.lang.String r0 = "fail replace ppp : "
            verifyPpp(r5, r6)
            r1 = 0
            boolean r2 = r7.acquireWriteLock(r1)
            java.lang.String r3 = "PppUpdater"
            if (r2 == 0) goto L_0x0032
            r7.replaceItemAt(r8, r6)     // Catch:{ UnsupportedOperationException -> 0x0017 }
            r7.releaseWriteLock(r1)
            goto L_0x0032
        L_0x0015:
            r4 = move-exception
            goto L_0x002e
        L_0x0017:
            java.lang.Class r4 = r7.getClass()     // Catch:{ all -> 0x0015 }
            java.lang.String r4 = r4.getSimpleName()     // Catch:{ all -> 0x0015 }
            java.lang.String r4 = r0.concat(r4)     // Catch:{ all -> 0x0015 }
            java.lang.Object[] r5 = new java.lang.Object[]{r6}     // Catch:{ all -> 0x0015 }
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r3, (java.lang.String) r4, (java.lang.Object[]) r5)     // Catch:{ all -> 0x0015 }
            r7.releaseWriteLock(r1)
            return
        L_0x002e:
            r7.releaseWriteLock(r1)
            throw r4
        L_0x0032:
            r7.updateDataStampByPpp(r6)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "notifyMediaItemChanged(R) > notify, position="
            r0.<init>(r1)
            r0.append(r8)
            java.lang.String r1 = ", "
            r0.append(r1)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.samsung.android.gallery.support.utils.Log.d(r3, r0, r6)
            java.util.concurrent.ConcurrentHashMap<java.lang.Long, com.samsung.android.gallery.module.data.MediaItem> r4 = r4.mPppUpdateMap
            long r0 = r5.getFileId()
            java.lang.Long r6 = java.lang.Long.valueOf(r0)
            r4.put(r6, r5)
            r4 = 1
            r7.notifyDataRangeChanged(r8, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.PppUpdater.lambda$replaceItem$0(com.samsung.android.gallery.module.data.MediaItem, com.samsung.android.gallery.module.data.MediaItem, com.samsung.android.gallery.module.dataset.MediaDataRef, int):void");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateItem$1(MediaDataRef mediaDataRef, MediaItem mediaItem, MediaItem mediaItem2, int i2) {
        if (mediaDataRef.acquireWriteLock((String) null)) {
            try {
                MediaItemUtil.updatePpp(mediaItem, mediaItem2);
            } finally {
                mediaDataRef.releaseWriteLock((String) null);
            }
        }
        Log.d("PppUpdater", "notifyMediaItemChanged(U) > notify, position=" + i2 + ArcCommonLog.TAG_COMMA + mediaItem, mediaItem2);
        this.mPppUpdateMap.put(Long.valueOf(mediaItem.getFileId()), mediaItem);
        mediaDataRef.notifyChanged();
    }

    private void notifyMediaItemChanged(MediaDataRef mediaDataRef, MediaItem mediaItem, MediaItem mediaItem2, int i2) {
        if (!Features.isEnabled(Features.SUPPORT_PPP_V2) && !Features.isEnabled(Features.SUPPORT_PPP_V3)) {
            return;
        }
        if (!mediaItem.isPostProcessing()) {
            Log.e("PppUpdater", "notifyMediaItemChanged > skip non-ppp item, position=" + i2 + ArcCommonLog.TAG_COMMA + mediaItem);
            return;
        }
        String path = mediaItem.getPath();
        if (!FileUtils.exists(path)) {
            a.u("notifyMediaItemChanged > failed invalid path=", path, "PppUpdater");
        } else if (Features.isEnabled(Features.SUPPORT_PPP_V3)) {
            replaceItem(mediaDataRef, mediaItem, mediaItem2, i2);
        } else {
            updateItem(mediaDataRef, mediaItem, mediaItem2, i2);
        }
    }

    private void replaceItem(MediaDataRef mediaDataRef, MediaItem mediaItem, MediaItem mediaItem2, int i2) {
        ThreadUtil.postOnUiThread(new F0(this, mediaItem, mediaItem2, mediaDataRef, i2));
    }

    private void requestBitmap(MediaItem mediaItem, Blackboard blackboard) {
        mediaItem.setFileSize(mediaItem.getFileSize());
        if (blackboard.publishIfEmpty(CommandKey.DATA_REQUEST(MediaItemUtil.getViewerBitmapKey(mediaItem, -1)), mediaItem.clone())) {
            Log.p("PppUpdater", "requestOriginalBitmap" + Logger.v(mediaItem.getPath(), Long.valueOf(mediaItem.getFileId()), Integer.valueOf(mediaItem.getSimpleHashCode())));
            return;
        }
        Log.e((CharSequence) "PppUpdater", "requestOriginalBitmap failed", -1);
    }

    private void updateItem(MediaDataRef mediaDataRef, MediaItem mediaItem, MediaItem mediaItem2, int i2) {
        ThreadUtil.postOnUiThread(new F0(this, mediaDataRef, mediaItem, mediaItem2, i2));
    }

    private static void verifyPpp(MediaItem mediaItem, MediaItem mediaItem2) {
        boolean z;
        String str;
        if (!compareInt(mediaItem.getWidth(), mediaItem2.getWidth(), "width") || !compareInt(mediaItem.getHeight(), mediaItem2.getHeight(), "height") || !compareInt(mediaItem.getOrientation(), mediaItem2.getOrientation(), "orientation")) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            str = "success";
        } else {
            str = "fail";
        }
        Log.d("PppUpdater", "verify ppp ".concat(str));
    }

    public void clear() {
        this.mPppUpdateMap.clear();
    }

    public boolean isFilteredEvent(EventMessage eventMessage) {
        String str;
        Uri uri = (Uri) eventMessage.obj;
        if (uri != null) {
            str = uri.toString();
        } else {
            str = null;
        }
        if (str != null && str.startsWith("content://media/external/images/media")) {
            long j2 = UnsafeCast.toLong(uri.getLastPathSegment(), 0);
            if (this.mPppUpdateMap.containsKey(Long.valueOf(j2))) {
                Log.e("PppUpdater", "isFilteredEvent filtered uri=".concat(str));
                this.mPppUpdateMap.remove(Long.valueOf(j2));
                return true;
            }
        }
        this.mPppUpdateMap.clear();
        return false;
    }

    public void onUpdatePppMediaItem(MediaDataRef mediaDataRef, MediaItem mediaItem, Blackboard blackboard) {
        C0591a0 a0Var;
        if (Features.isEnabled(Features.SUPPORT_PPP_V2) || Features.isEnabled(Features.SUPPORT_PPP_V3)) {
            String concat = mediaDataRef.getClass().getSimpleName().concat(". ");
            if (!DeviceConfig.UNIT_TEST) {
                if (isItemInTrash(AppResources.getAppContext(), mediaItem.getFileId())) {
                    Log.e((CharSequence) "PppUpdater", C0212a.A(concat, "onUpdatePppMediaItem2 fail. Trashed item "), Long.valueOf(mediaItem.getFileId()));
                    return;
                } else if (!FileUtils.exists(mediaItem.getPath())) {
                    Log.e((CharSequence) "PppUpdater", C0212a.A(concat, "onUpdatePppMediaItem2 fail. FileNotExist"), Long.valueOf(mediaItem.getFileId()), Logger.getEncodedString(mediaItem.getPath()));
                    return;
                }
            }
            if (Features.isEnabled(Features.SUPPORT_PPP_V3)) {
                a0Var = new C0591a0(mediaItem, 1);
            } else if (mediaItem.getFileId() > 0) {
                a0Var = new C0591a0(mediaItem, 2);
            } else {
                a0Var = new C0591a0(mediaItem, 3);
            }
            int min = Math.min(1000, mediaDataRef.getCount());
            int i2 = 0;
            while (true) {
                if (i2 >= min) {
                    break;
                }
                MediaItem read = mediaDataRef.read(i2);
                if (read == null) {
                    Log.e("PppUpdater", concat + "onUpdatePppMediaItem2 null item {" + i2 + "}");
                    break;
                } else if (a0Var.test(read)) {
                    Log.d("PppUpdater", concat + "onUpdatePppMediaItem2 match {" + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.getMediaId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.getFileId() + "}");
                    if (read.getSefFileType() == 2784) {
                        blackboard.publish(CommandKey.DATA_REQUEST("data://bitmap/viewer/" + read.getSimpleHashCode()), read.clone());
                        ThumbnailLoader.getInstance().removeCache(read);
                    } else {
                        requestBitmap(mediaItem, blackboard);
                    }
                    notifyMediaItemChanged(mediaDataRef, read, mediaItem, i2);
                    return;
                } else {
                    i2++;
                }
            }
            StringBuilder t = C0212a.t(concat, "onUpdatePppMediaItem2 matching failed");
            t.append(Logger.v(Long.valueOf(mediaItem.getFileId()), Integer.valueOf(min), Boolean.valueOf(mediaDataRef.isDataAvailable())));
            Log.w("PppUpdater", t.toString());
            if (Features.isEnabled(Features.SUPPORT_PPP_V2) && !mediaDataRef.isDataAvailable()) {
                ThreadUtil.postOnBgThreadDelayed(new C0620z(concat, mediaItem, mediaDataRef, 1), Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS);
            }
        }
    }
}
