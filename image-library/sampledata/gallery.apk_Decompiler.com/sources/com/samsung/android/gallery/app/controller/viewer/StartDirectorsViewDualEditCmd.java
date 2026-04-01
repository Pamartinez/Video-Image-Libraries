package com.samsung.android.gallery.app.controller.viewer;

import N2.j;
import S3.d;
import U5.c;
import U9.b;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.abstraction.DownloadType;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.directorsview.DirectorsViewCache;
import com.samsung.android.gallery.module.service.download.DownloadSyncMgr;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringResources;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartDirectorsViewDualEditCmd extends BaseCommand {
    private DownloadSyncMgr mDownloadSyncMgr;
    private Consumer<MediaItem[]> mListener;

    private void executeDownload(boolean z, MediaItem[] mediaItemArr) {
        if (!z) {
            Log.w(this.TAG, "confirm canceled");
        } else {
            ThreadUtil.postOnBgThread(new c(15, this, (List) Arrays.stream(mediaItemArr).filter(new d(14)).collect(Collectors.toList())));
        }
    }

    private int findSeekPos(MediaItem mediaItem) {
        Integer num = (Integer) Optional.ofNullable(getHandler().getEventContextData("app_transition_seek_position")).orElse(0);
        int intValue = num.intValue();
        if (mediaItem != null) {
            long videoDuration = (long) VideoPropData.getVideoDuration(mediaItem);
            long j2 = videoDuration - ((long) intValue);
            Log.d(this.TAG, "dur/resume ", Long.valueOf(videoDuration), num, Long.valueOf(j2));
            if (j2 < 500) {
                return 0;
            }
        }
        return intValue;
    }

    private Intent getIntentForVideoTrimmer(MediaItem[] mediaItemArr) {
        ArrayList<Uri> uriList = getUriList(mediaItemArr);
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClassName("com.samsung.app.newtrim", "com.samsung.app.newtrim.multigrid.main.MultiGridActivity");
        intent.putExtra("CALLER_APP", "director_view_editor");
        intent.putExtra("selectedItems", uriList);
        int findSeekPos = findSeekPos(mediaItemArr[0]);
        if (findSeekPos >= 0) {
            intent.putExtra("video_seek_position", findSeekPos);
        }
        return intent;
    }

    private ArrayList<Uri> getUriList(MediaItem[] mediaItemArr) {
        ArrayList<Uri> arrayList = new ArrayList<>();
        arrayList.add(ContentUri.getUri(mediaItemArr[0]));
        return arrayList;
    }

    private boolean hasCloudOnlyItem(MediaItem[] mediaItemArr) {
        return Arrays.stream(mediaItemArr).anyMatch(new d(13));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$executeDownload$1(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isCloudOnly()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeDownload$3(List list) {
        if (this.mDownloadSyncMgr != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                MediaItem mediaItem = (MediaItem) it.next();
                if (!this.mDownloadSyncMgr.valid(mediaItem.getFileId())) {
                    String str = this.TAG;
                    Log.w(str, "skip downloading. [" + mediaItem.getFileId() + "] is not valid");
                    return;
                }
            }
        }
        String str2 = this.TAG;
        Log.w(str2, "executeDownload. cloudItems=" + list.size());
        new MultiDownloadForViewerCmd().execute(getHandler(), list.toArray(new MediaItem[0]), new b(7, this), DownloadType.EDIT, this.mDownloadSyncMgr);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$hasCloudOnlyItem$4(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isCloudOnly()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(MediaItem[] mediaItemArr, Boolean bool) {
        executeDownload(bool.booleanValue(), mediaItemArr);
    }

    /* access modifiers changed from: private */
    /* renamed from: onDownloaded */
    public void lambda$executeDownload$2(MediaItem[] mediaItemArr) {
        Log.w(this.TAG, "download complete");
        if (SeApiCompat.isActivityResumed(getActivity())) {
            startVideoTrimmer(mediaItemArr);
        } else {
            Log.w(this.TAG, "ignore activity launch due to paused");
        }
        for (MediaItem updateMediaItem : mediaItemArr) {
            DirectorsViewCache.getInstance().updateMediaItem(updateMediaItem);
        }
        Consumer<MediaItem[]> consumer = this.mListener;
        if (consumer != null) {
            consumer.accept(mediaItemArr);
        }
    }

    private void printItemInfo(MediaItem[] mediaItemArr) {
        for (MediaItem mediaItem : mediaItemArr) {
            Log.d(this.TAG, "Dual video edit", Long.valueOf(mediaItem.getFileId()), Long.valueOf(mediaItem.getMediaId()), Integer.valueOf(mediaItem.getBucketID()), Long.valueOf(mediaItem.getDirectorsViewGroupId()), mediaItem.getStorageType(), Integer.valueOf(mediaItem.getSefFileType()), Integer.valueOf(mediaItem.getSefFileSubType()));
        }
    }

    private boolean startVideoTrimmer(MediaItem[] mediaItemArr) {
        try {
            getActivity().startActivity(getIntentForVideoTrimmer(mediaItemArr));
            return true;
        } catch (ActivityNotFoundException e) {
            if (Features.isEnabled(Features.SUPPORT_DOWNLOAD_VIDEO_TRIMMER)) {
                guideDownloadPackage("com.samsung.app.newtrim", StringResources.getVideoTrimmerName());
                return false;
            }
            j.p(e, new StringBuilder("startVideoEdit failed e="), this.TAG);
            return false;
        }
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.Integer] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onExecute(com.samsung.android.gallery.app.controller.EventContext r6, java.lang.Object... r7) {
        /*
            r5 = this;
            boolean r0 = r5.isUpsm()
            if (r0 == 0) goto L_0x0021
            android.content.Context r6 = r5.getContext()
            java.lang.String r7 = com.samsung.android.gallery.support.utils.StringResources.getVideoTrimmerName()
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            r0 = 2131888900(0x7f120b04, float:1.9412448E38)
            java.lang.String r6 = r6.getString(r0, r7)
            java.lang.String r6 = com.samsung.android.gallery.support.library.SeApiCompat.naturalizeText(r6)
            r5.showToast((java.lang.String) r6)
            return
        L_0x0021:
            r0 = 0
            r0 = r7[r0]
            com.samsung.android.gallery.module.data.MediaItem[] r0 = (com.samsung.android.gallery.module.data.MediaItem[]) r0
            r1 = 0
            if (r0 == 0) goto L_0x005b
            int r2 = r0.length
            r3 = 2
            if (r2 >= r3) goto L_0x002e
            goto L_0x005b
        L_0x002e:
            int r2 = r7.length
            r4 = 1
            if (r2 <= r4) goto L_0x0037
            r2 = r7[r4]
            com.samsung.android.gallery.module.service.download.DownloadSyncMgr r2 = (com.samsung.android.gallery.module.service.download.DownloadSyncMgr) r2
            goto L_0x0038
        L_0x0037:
            r2 = r1
        L_0x0038:
            r5.mDownloadSyncMgr = r2
            int r2 = r7.length
            if (r2 <= r3) goto L_0x0042
            r7 = r7[r3]
            r1 = r7
            java.util.function.Consumer r1 = (java.util.function.Consumer) r1
        L_0x0042:
            r5.mListener = r1
            boolean r7 = r5.hasCloudOnlyItem(r0)
            if (r7 == 0) goto L_0x0054
            U5.b r7 = new U5.b
            r1 = 2
            r7.<init>(r1, r5, r0)
            r5.checkNetworkStatus(r6, r7)
            goto L_0x0057
        L_0x0054:
            r5.startVideoTrimmer(r0)
        L_0x0057:
            r5.printItemInfo(r0)
            return
        L_0x005b:
            java.lang.String r5 = r5.TAG
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "wrong items="
            r6.<init>(r7)
            if (r0 == 0) goto L_0x006b
            int r7 = r0.length
            java.lang.Integer r1 = java.lang.Integer.valueOf(r7)
        L_0x006b:
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            com.samsung.android.gallery.support.utils.Log.e(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.viewer.StartDirectorsViewDualEditCmd.onExecute(com.samsung.android.gallery.app.controller.EventContext, java.lang.Object[]):void");
    }
}
