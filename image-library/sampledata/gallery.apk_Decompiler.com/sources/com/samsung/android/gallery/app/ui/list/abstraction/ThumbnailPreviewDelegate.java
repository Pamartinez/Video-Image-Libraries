package com.samsung.android.gallery.app.ui.list.abstraction;

import A2.d;
import A4.C0367b;
import A4.I;
import A4.a0;
import A4.b0;
import A4.c0;
import A4.d0;
import android.util.Range;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.support.utils.LazyAlarmManager;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThumbnailPreviewDelegate {
    private static final Semaphore sPreviewTicket = new Semaphore(1);
    private final List<PreviewViewHolder> mCandidateList = new LinkedList();
    private int mLastPlayingPos = -1;
    private final LazyAlarmManager mLazyAlarmManager = new LazyAlarmManager(new a0(this, 0));
    private int mLowerLimitPos;
    private final Set<PreviewViewHolder> mPlayedSet = new HashSet();
    private final Set<PreviewViewHolder> mPlayingSet = new HashSet();
    private final Map<PreviewViewHolder, Runnable> mPreviewTimerMap = new HashMap();
    private final ThumbnailPreview<?> mThumbnailPreview;
    private int mUpperLimitPos;
    private Range<Integer> mViewableRange;

    public ThumbnailPreviewDelegate(ThumbnailPreview<?> thumbnailPreview) {
        this.mThumbnailPreview = thumbnailPreview;
    }

    private void calcViewHolderList() {
        int size = this.mCandidateList.size();
        this.mCandidateList.clear();
        Stream<PreviewViewHolder> filter = this.mThumbnailPreview.listOf().stream().filter(new I(1, this));
        List<PreviewViewHolder> list = this.mCandidateList;
        Objects.requireNonNull(list);
        filter.forEach(new c0(0, list));
        LinkedList linkedList = new LinkedList();
        Stream<PreviewViewHolder> stream = this.mPlayedSet.stream();
        List<PreviewViewHolder> list2 = this.mCandidateList;
        Objects.requireNonNull(list2);
        stream.filter(new d0(0, list2)).forEach(new C0367b(4, linkedList));
        this.mPlayedSet.retainAll(linkedList);
        int size2 = this.mCandidateList.size();
        if (size2 > 0) {
            this.mViewableRange = Range.create(Integer.valueOf(getLayoutPosition(this.mCandidateList.get(0))), Integer.valueOf(getLayoutPosition(this.mCandidateList.get(size2 - 1))));
        }
        if (size <= 1 && this.mCandidateList.size() > 1) {
            for (PreviewViewHolder previewTimer : this.mPlayingSet) {
                setPreviewTimer(previewTimer);
            }
        } else if (size > 1 && this.mCandidateList.size() <= 1) {
            for (PreviewViewHolder resetPreviewTimer : this.mPlayingSet) {
                resetPreviewTimer(resetPreviewTimer);
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r4 = (com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder) r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean canPlayback(androidx.recyclerview.widget.RecyclerView.ViewHolder r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder
            r1 = 0
            if (r0 == 0) goto L_0x0027
            com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailPreview<?> r0 = r3.mThumbnailPreview
            com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder r4 = (com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder) r4
            int r0 = r0.getViewHolderPosition(r4)
            int r2 = r3.mUpperLimitPos
            if (r0 < r2) goto L_0x0027
            int r3 = r3.mLowerLimitPos
            if (r0 > r3) goto L_0x0027
            com.samsung.android.gallery.module.data.MediaItem r3 = r4.getPreviewItem()
            boolean r3 = com.samsung.android.gallery.widget.previewable.PreviewFactory.isPreviewableFormat(r3)
            if (r3 == 0) goto L_0x0027
            boolean r3 = r4.isPreviewError()
            if (r3 != 0) goto L_0x0027
            r3 = 1
            return r3
        L_0x0027:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailPreviewDelegate.canPlayback(androidx.recyclerview.widget.RecyclerView$ViewHolder):boolean");
    }

    /* access modifiers changed from: private */
    public void checkCandidateInternal() {
        this.mUpperLimitPos = this.mThumbnailPreview.upperLimit();
        this.mLowerLimitPos = this.mThumbnailPreview.lowerLimit();
        calcViewHolderList();
        stopPreviewOutOfRange();
        startPreviewViewHolders();
    }

    private int getLayoutPosition(PreviewViewHolder previewViewHolder) {
        return this.mThumbnailPreview.getLayoutPosition(previewViewHolder);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPreviewTimer$1(PreviewViewHolder previewViewHolder) {
        if (getLayoutPosition(previewViewHolder) == this.mViewableRange.getUpper().intValue()) {
            this.mPlayedSet.clear();
            this.mLastPlayingPos = -1;
        } else {
            this.mPlayedSet.add(previewViewHolder);
        }
        previewViewHolder.stopPreview();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startPreviewViewHolders$0(PreviewViewHolder previewViewHolder, boolean z) {
        Semaphore semaphore = sPreviewTicket;
        semaphore.release();
        int availablePermits = semaphore.availablePermits();
        if (availablePermits < 1 || availablePermits > 1) {
            Log.e("ThumbnailPreviewHelper", "previewSemaphore unexpected release {" + getLayoutPosition(previewViewHolder) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + availablePermits + "}");
        }
        if (!z) {
            this.mCandidateList.remove(previewViewHolder);
        }
        this.mPlayingSet.remove(previewViewHolder);
        if (previewViewHolder.isPreviewDone()) {
            this.mPlayedSet.add(previewViewHolder);
        }
        int size = this.mCandidateList.size();
        if (size <= 0 || this.mCandidateList.get(size - 1) != previewViewHolder) {
            this.mLazyAlarmManager.post(new a0(this, 1));
        } else {
            this.mLazyAlarmManager.postDelayed(new a0(this, 1), 1000);
        }
        resetPreviewTimer(previewViewHolder);
    }

    private void resetPreviewTimer(PreviewViewHolder previewViewHolder) {
        this.mLazyAlarmManager.removeCallbacks(this.mPreviewTimerMap.remove(previewViewHolder));
    }

    private void setPreviewTimer(PreviewViewHolder previewViewHolder) {
        d dVar = new d(5, this, previewViewHolder);
        this.mLazyAlarmManager.postDelayed(dVar, (long) previewViewHolder.getPreviewDuration());
        this.mPreviewTimerMap.put(previewViewHolder, dVar);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003f, code lost:
        if (r0.contains(r3.get(r3.size() - 1)) != false) goto L_0x0041;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void startPreviewViewHolders() {
        /*
            r8 = this;
            java.util.List<com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder> r0 = r8.mCandidateList
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00cd
            com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailPreview<?> r0 = r8.mThumbnailPreview
            boolean r0 = r0.isViewAnimating()
            if (r0 == 0) goto L_0x0012
            goto L_0x00cd
        L_0x0012:
            android.util.Range<java.lang.Integer> r0 = r8.mViewableRange
            int r1 = r8.mLastPlayingPos
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            boolean r0 = r0.contains(r1)
            r1 = -1
            if (r0 != 0) goto L_0x0023
            r8.mLastPlayingPos = r1
        L_0x0023:
            java.util.Set<com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder> r0 = r8.mPlayedSet
            java.util.List<com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder> r2 = r8.mCandidateList
            boolean r0 = r0.containsAll(r2)
            r2 = 1
            if (r0 != 0) goto L_0x0041
            java.util.Set<com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder> r0 = r8.mPlayedSet
            java.util.List<com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder> r3 = r8.mCandidateList
            int r4 = r3.size()
            int r4 = r4 - r2
            java.lang.Object r3 = r3.get(r4)
            boolean r0 = r0.contains(r3)
            if (r0 == 0) goto L_0x0048
        L_0x0041:
            java.util.Set<com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder> r0 = r8.mPlayedSet
            r0.clear()
            r8.mLastPlayingPos = r1
        L_0x0048:
            java.util.List<com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder> r0 = r8.mCandidateList
            java.util.Iterator r0 = r0.iterator()
        L_0x004e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00cd
            java.lang.Object r1 = r0.next()
            com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder r1 = (com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder) r1
            int r3 = r8.getLayoutPosition(r1)
            com.samsung.android.gallery.module.data.MediaItem r4 = r1.getMediaItem()
            if (r4 == 0) goto L_0x004e
            android.util.Range<java.lang.Integer> r4 = r8.mViewableRange
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)
            boolean r4 = r4.contains(r5)
            if (r4 == 0) goto L_0x004e
            int r4 = r8.mLastPlayingPos
            if (r3 >= r4) goto L_0x0075
            goto L_0x004e
        L_0x0075:
            java.util.Set<com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder> r4 = r8.mPlayedSet
            boolean r4 = r4.contains(r1)
            if (r4 != 0) goto L_0x004e
            java.util.Set<com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder> r4 = r8.mPlayingSet
            boolean r4 = r4.contains(r1)
            if (r4 == 0) goto L_0x0086
            goto L_0x004e
        L_0x0086:
            java.util.concurrent.Semaphore r4 = sPreviewTicket
            boolean r5 = r4.tryAcquire()
            if (r5 != 0) goto L_0x008f
            goto L_0x00cd
        L_0x008f:
            int r4 = r4.availablePermits()
            if (r4 < 0) goto L_0x0097
            if (r4 < r2) goto L_0x00a6
        L_0x0097:
            java.lang.String r5 = ","
            java.lang.String r6 = "}"
            java.lang.String r7 = "previewSemaphore unexpected acquire {"
            java.lang.String r4 = A.a.d(r3, r4, r7, r5, r6)
            java.lang.String r5 = "ThumbnailPreviewHelper"
            com.samsung.android.gallery.support.utils.Log.e(r5, r4)
        L_0x00a6:
            A4.O r4 = new A4.O
            r5 = 3
            r4.<init>(r5, r8)
            r1.preparePreview(r4)
            java.util.Set<com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder> r4 = r8.mPlayingSet
            r4.add(r1)
            r1.startPreview()
            r8.mLastPlayingPos = r3
            java.util.List<com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder> r3 = r8.mCandidateList
            int r3 = r3.size()
            if (r3 > r2) goto L_0x00c9
            com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailPreview<?> r3 = r8.mThumbnailPreview
            boolean r3 = r3.limitDuration()
            if (r3 == 0) goto L_0x004e
        L_0x00c9:
            r8.setPreviewTimer(r1)
            goto L_0x004e
        L_0x00cd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailPreviewDelegate.startPreviewViewHolders():void");
    }

    private void stopPreviewOutOfRange() {
        for (PreviewViewHolder next : this.mPlayingSet) {
            if (!canPlayback(next) || !this.mCandidateList.contains(next)) {
                LazyAlarmManager lazyAlarmManager = this.mLazyAlarmManager;
                Objects.requireNonNull(next);
                lazyAlarmManager.post(new b0(next, 0));
            }
        }
    }

    public void checkCandidate() {
        this.mLazyAlarmManager.postLazy(0);
    }

    public void checkCandidateDelayed(long j2) {
        this.mLazyAlarmManager.postLazy(j2);
    }

    public void onPreviewHolderRecycled(PreviewViewHolder previewViewHolder) {
        this.mCandidateList.remove(previewViewHolder);
    }

    public void stopAllPreview(boolean z) {
        this.mLazyAlarmManager.removeLazy();
        this.mCandidateList.clear();
        Iterator it = new LinkedList(this.mPlayingSet).iterator();
        while (it.hasNext()) {
            ((PreviewViewHolder) it.next()).stopPreview(z);
        }
        this.mPlayingSet.clear();
        this.mLastPlayingPos = -1;
    }
}
