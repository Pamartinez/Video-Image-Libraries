package com.samsung.android.gallery.app.service;

import A.a;
import android.content.Intent;
import bc.C0584a;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.service.abstraction.ProgressJob;
import com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuickDeleteService extends DeleteService {
    public QuickDeleteService() {
        super("QuickDeleteService", "com.samsung.android.gallery.app.service.QuickDeleteService");
        this.LATE_DELAY = 100;
    }

    private boolean appendJob(Intent intent) {
        Log.e("QuickDeleteService", "start append Job");
        MediaItem[] mediaItemArr = (MediaItem[]) this.mBlackboard.pop("data://user/selected");
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.w("QuickDeleteService", "appendJob : items are empty. adding failed.");
            return false;
        }
        boolean isAlbums = isAlbums(intent);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem != null) {
                if (MediaItemUtil.isGroupedAlbum(mediaItem)) {
                    addFolderItem(mediaItem, arrayList);
                } else if (isAlbums) {
                    if (isValidAlbum(mediaItem)) {
                        arrayList.add(Integer.valueOf(mediaItem.getAlbumID()));
                        appendToQueue(new ProgressJob(mediaItem, Boolean.TRUE, Boolean.FALSE, Integer.valueOf(mediaItem.getAlbumType().toInt())));
                    }
                } else if (isNormalPpp(mediaItem)) {
                    arrayList3.add(mediaItem);
                } else {
                    Boolean bool = Boolean.FALSE;
                    appendToQueue(new ProgressJob(mediaItem, bool, bool, Integer.valueOf(AlbumType.None.toInt()), Boolean.valueOf(isBurstShotSelection(intent))));
                    arrayList2.add(mediaItem);
                }
            }
        }
        quickDelete(this.mAppendQueue, arrayList2, arrayList3, intent);
        if (this.mAppendQueue.isEmpty()) {
            Log.w("QuickDeleteService", "queue is empty. adding failed.");
            return false;
        }
        appendProgressInfo(intent, mediaItemArr, arrayList);
        appendToQueue(this.mAppendQueue);
        this.mAppendQueue.clear();
        return true;
    }

    private void appendProgressInfo(Intent intent, MediaItem[] mediaItemArr, ArrayList<Integer> arrayList) {
        updateTotalCount(this.mAppendQueue.size());
        this.mHelper.updateTotalDeleteCount(getTotalCount(intent, mediaItemArr, isAlbums(), arrayList));
    }

    private void appendToQueue(ProgressJob progressJob) {
        this.mAppendQueue.add(progressJob);
    }

    private boolean isAlbums(Intent intent) {
        String stringExtra = intent.getStringExtra("location_key");
        if ("location://albums".equals(stringExtra) || LocationKey.isFolder(stringExtra) || LocationKey.isAllAlbumsMatch(stringExtra)) {
            return true;
        }
        return false;
    }

    private boolean isNormalPpp(MediaItem mediaItem) {
        if (mediaItem.getSefFileType() != 2928 || !FileUtils.isPostProcessingFile(mediaItem.getPath()) || !FileUtils.exists(mediaItem.getPath())) {
            return false;
        }
        return true;
    }

    private boolean isViewerGroupShotDeleted(Intent intent) {
        if (!isViewer() || !isBurstShotSelection(intent)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$quickDelete$0() {
        this.mBlackboard.post("command://event/DataChanged", EventMessage.obtain(101, 1, 0, (Object) null));
    }

    private void quickDelete(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2, Intent intent) {
        quickDelete(getUnFinishedJob(), arrayList, arrayList2, intent);
    }

    private void revertTrashedItem() {
        ThreadUtil.assertBgThread("revertTrashedItem should be on worker thread");
        long currentTimeMillis = System.currentTimeMillis();
        Queue<ProgressJob> unFinishedJob = getUnFinishedJob();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Log.d("QuickDeleteService", "revertTrashedItem", Integer.valueOf(unFinishedJob.size()));
        for (ProgressJob next : unFinishedJob) {
            MediaItem mediaItem = (MediaItem) next.getParam(0);
            boolean booleanValue = ((Boolean) next.getParam(1)).booleanValue();
            boolean booleanValue2 = ((Boolean) next.getParam(2)).booleanValue();
            if (booleanValue) {
                arrayList2.add(mediaItem);
            } else if (booleanValue2) {
                arrayList3.add(mediaItem);
            } else {
                arrayList.add(mediaItem);
            }
        }
        ProgressJob progressJob = this.mCurrentJob;
        if (progressJob != null) {
            MediaItem mediaItem2 = (MediaItem) progressJob.getParam(0);
            boolean booleanValue3 = ((Boolean) this.mCurrentJob.getParam(1)).booleanValue();
            boolean booleanValue4 = ((Boolean) this.mCurrentJob.getParam(2)).booleanValue();
            if (booleanValue3) {
                arrayList2.add(mediaItem2);
            } else if (booleanValue4) {
                arrayList3.add(mediaItem2);
            } else if (FileUtils.exists(mediaItem2.getPath())) {
                arrayList.add(mediaItem2);
            }
        }
        int revertTrashedItem = this.mHelper.revertTrashedItem(arrayList, arrayList2, arrayList3);
        if (revertTrashedItem > 0) {
            this.mBlackboard.post("command://event/DataChanged", EventMessage.obtain(101, 1, 0, (Object) null));
        }
        a.A(new Object[]{Integer.valueOf(arrayList.size()), Integer.valueOf(revertTrashedItem), Integer.valueOf(TrashDeleteHelper.queryTrashedCount()), Long.valueOf(currentTimeMillis)}, new StringBuilder("revertTrashedItem#result"), "QuickDeleteService");
    }

    private void startDeleteAnimationForDeleteImmediately(Intent intent) {
        if (!isViewer() || isBurstShotSelection(intent)) {
            super.startDeleteAnimation();
        } else {
            this.mBlackboard.postEvent(EventMessage.obtain(3015));
        }
    }

    public boolean addJobs(Intent intent) {
        boolean z;
        MediaItem[] mediaItemArr = (MediaItem[]) this.mBlackboard.pop("data://user/selected");
        int i2 = 0;
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.w("QuickDeleteService", "items are empty. adding failed.");
            return false;
        }
        boolean isAlbums = isAlbums();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int length = mediaItemArr.length;
        int i7 = 0;
        boolean z3 = false;
        boolean z7 = isAlbums;
        int i8 = 0;
        while (i7 < length) {
            MediaItem mediaItem = mediaItemArr[i7];
            if (mediaItem != null) {
                if (MediaItemUtil.isGroupedAlbum(mediaItem)) {
                    addFolderItem(mediaItem, arrayList);
                } else {
                    if (!z7) {
                        z = z3;
                        if (mediaItem.isVideo()) {
                            if (isNormalPpp(mediaItem)) {
                                arrayList3.add(mediaItem);
                            } else {
                                i8++;
                                Boolean bool = Boolean.FALSE;
                                addToQueue(new ProgressJob(mediaItem, bool, bool, Integer.valueOf(AlbumType.None.toInt()), Boolean.valueOf(isBurstShotSelection(intent))));
                                arrayList2.add(mediaItem);
                            }
                        } else if (isNormalPpp(mediaItem)) {
                            arrayList3.add(mediaItem);
                        } else {
                            i2++;
                            Boolean bool2 = Boolean.FALSE;
                            addToQueue(new ProgressJob(mediaItem, bool2, bool2, Integer.valueOf(AlbumType.None.toInt()), Boolean.valueOf(isBurstShotSelection(intent))));
                            arrayList2.add(mediaItem);
                        }
                    } else if (isValidAlbum(mediaItem)) {
                        arrayList.add(Integer.valueOf(mediaItem.getAlbumID()));
                        z = z3;
                        addToQueue(new ProgressJob(mediaItem, Boolean.TRUE, Boolean.FALSE, Integer.valueOf(mediaItem.getAlbumType().toInt())));
                    }
                    i7++;
                    z3 = z;
                }
            }
            z = z3;
            i7++;
            z3 = z;
        }
        Intent intent2 = intent;
        boolean z9 = z3;
        quickDelete(arrayList2, arrayList3, intent2);
        if (isQueueEmpty()) {
            Log.w("QuickDeleteService", "queue is empty. adding failed.");
            return z9;
        }
        addProgressInfo(intent2, i2, i8, mediaItemArr, arrayList);
        return true;
    }

    public void onAppendInternal(Intent intent) {
        appendJob(intent);
    }

    public void onCleanInternal() {
        super.onCleanInternal();
        PreferenceCache.QuickDeleteService.decrementAndGet();
        int queryTrashedCount = TrashDeleteHelper.queryTrashedCount();
        if (queryTrashedCount > 0) {
            a.B(queryTrashedCount, "onCleanInternal with wrong remained count=", "QuickDeleteService");
        }
    }

    public boolean onInterruptInternal(Intent intent) {
        super.onInterruptInternal(intent);
        try {
            PreferenceCache.QuickDeleteService.incrementAndGet();
            revertTrashedItem();
            return true;
        } catch (Exception unused) {
            return true;
        }
    }

    public boolean onPrepareInternal(Intent intent) {
        boolean onPrepareInternal = super.onPrepareInternal(intent);
        collectDataChange(true);
        this.mHelper.setQuickDelete(true);
        this.mHelper.setFailCollect(true);
        PreferenceCache.QuickDeleteService.incrementAndGet();
        return onPrepareInternal;
    }

    private void quickDelete(Queue<ProgressJob> queue, ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2, Intent intent) {
        int i2;
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        if (!arrayList2.isEmpty()) {
            Iterator<MediaItem> it = arrayList2.iterator();
            while (it.hasNext()) {
                this.mHelper.deleteItem(it.next());
            }
            this.mHelper.done();
            Log.d("QuickDeleteService", "quickDelete : delete first ppp items size=" + arrayList2.size());
        }
        if (isViewerGroupShotDeleted(intent)) {
            Iterator<MediaItem> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                this.mHelper.deleteItem(it2.next());
            }
            this.mHelper.done();
            Log.d("QuickDeleteService", "quickDelete : delete viewer groupShot=" + arrayList.size());
            i2 = 0;
        } else {
            i2 = this.mHelper.quickDelete(arrayList, !isBurstShotSelection(intent));
        }
        if (isAlbums()) {
            for (ProgressJob next : queue) {
                MediaItem mediaItem = (MediaItem) next.getParam(0);
                boolean booleanValue = ((Boolean) next.getParam(1)).booleanValue();
                boolean booleanValue2 = ((Boolean) next.getParam(2)).booleanValue();
                if (booleanValue) {
                    arrayList3.add(Integer.valueOf(mediaItem.getAlbumID()));
                    arrayList4.add(Integer.valueOf(mediaItem.getAlbumID()));
                } else if (booleanValue2) {
                    arrayList4.add(Integer.valueOf(mediaItem.getAlbumID()));
                }
            }
            i2 += this.mHelper.quickAlbumDelete(arrayList3, arrayList4);
        }
        Log.i("QuickDeleteService", "quickDelete" + Logger.vt(Integer.valueOf(i2), Long.valueOf(currentTimeMillis)));
        if (i2 > 0) {
            startDeleteAnimationForDeleteImmediately(intent);
            ThreadUtil.postOnBgThreadDelayed(new C0584a(9, this), isAlbums() ? 500 : 10);
        }
    }

    public void startDeleteAnimation() {
    }

    public void updateDialog(int i2, int i7, int i8) {
    }

    public void showDialog(String str, int i2, int i7, int i8) {
    }
}
