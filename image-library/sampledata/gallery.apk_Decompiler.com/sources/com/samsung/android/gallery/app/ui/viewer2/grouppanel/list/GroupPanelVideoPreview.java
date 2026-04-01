package com.samsung.android.gallery.app.ui.viewer2.grouppanel.list;

import A4.J;
import A4.b0;
import K5.a;
import M9.o;
import O3.y;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupPanelVideoPreview {
    private final GroupPanelListAdapterListener mAdapterListener;
    private final List<PreviewViewHolder> mPreviewCandidates = Collections.synchronizedList(new LinkedList());
    private final Semaphore mPreviewTicket = new Semaphore(1);
    private int mVisibleEndPos = -1;
    private int mVisibleStartPos = -1;

    public GroupPanelVideoPreview(GroupPanelListAdapterListener groupPanelListAdapterListener) {
        this.mAdapterListener = groupPanelListAdapterListener;
    }

    private PreviewViewHolder getPlayingPreviewHolder() {
        for (PreviewViewHolder next : this.mPreviewCandidates) {
            if (next.isPreviewing()) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$makePreviewCandidates$0(int i2, List list, PreviewViewHolder previewViewHolder) {
        if (i2 > previewViewHolder.getBindingAdapterPosition()) {
            list.add(previewViewHolder);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$makePreviewCandidates$1(PreviewViewHolder previewViewHolder) {
        this.mPreviewCandidates.remove(previewViewHolder);
        this.mPreviewCandidates.add(previewViewHolder);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startPreviewViewHolders$3(PreviewViewHolder previewViewHolder, boolean z) {
        this.mPreviewTicket.release();
        if (previewViewHolder != null) {
            int bindingAdapterPosition = previewViewHolder.getBindingAdapterPosition();
            this.mPreviewCandidates.remove(previewViewHolder);
            if (z && bindingAdapterPosition >= this.mVisibleStartPos && bindingAdapterPosition <= this.mVisibleEndPos) {
                this.mPreviewCandidates.add(previewViewHolder);
            }
            if (this.mAdapterListener.isAvailableToPlay()) {
                ThreadUtil.postOnUiThreadDelayed(new o(21, this), 200);
            }
        }
    }

    private void makePreviewCandidates(int i2, int i7) {
        PreviewViewHolder playingPreviewHolder = getPlayingPreviewHolder();
        this.mPreviewCandidates.clear();
        GroupPanelListAdapterListener groupPanelListAdapterListener = this.mAdapterListener;
        if (groupPanelListAdapterListener == null || !groupPanelListAdapterListener.isAvailableToPlay()) {
            Log.d("SingleTakenVideoPreview", "makePreviewCandidates : unavailable to play");
            if (playingPreviewHolder != null) {
                playingPreviewHolder.stopPreview();
                return;
            }
            return;
        }
        for (int i8 = i2; i8 <= i7; i8++) {
            PreviewViewHolder previewViewHolder = (PreviewViewHolder) this.mAdapterListener.getListView().findViewHolderForLayoutPosition(i8);
            if (!(previewViewHolder == null || previewViewHolder.getMediaItem() == null || !previewViewHolder.getMediaItem().isVideo())) {
                this.mPreviewCandidates.add(previewViewHolder);
            }
        }
        if (playingPreviewHolder != null) {
            int bindingAdapterPosition = playingPreviewHolder.getBindingAdapterPosition();
            LinkedList linkedList = new LinkedList();
            this.mPreviewCandidates.forEach(new J(bindingAdapterPosition, (Object) linkedList, 3));
            linkedList.forEach(new a(24, this));
            if (i2 > bindingAdapterPosition || bindingAdapterPosition > i7) {
                playingPreviewHolder.stopPreview();
            }
        }
    }

    public void setVisibleArea(int i2, int i7) {
        this.mVisibleStartPos = i2;
        this.mVisibleEndPos = i7;
        makePreviewCandidates(i2, i7);
        startPreviewViewHolders();
    }

    public void startPreviewViewHolders() {
        if (this.mPreviewCandidates.isEmpty()) {
            Log.d("SingleTakenVideoPreview", "startPreviewViewHolders skip : no candidate");
            return;
        }
        PreviewViewHolder previewViewHolder = this.mPreviewCandidates.get(0);
        if (previewViewHolder.isPreviewing()) {
            Log.d("SingleTakenVideoPreview", "startPreviewViewHolders skip : already playing : " + previewViewHolder.getBindingAdapterPosition());
            return;
        }
        try {
            if (this.mPreviewTicket.tryAcquire()) {
                previewViewHolder.resetPreviewError();
                previewViewHolder.preparePreview(new y(6, this));
                ThreadUtil.postOnUiThread(new b0(previewViewHolder, 1));
            }
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("startPreviewViewHolders failed e="), "SingleTakenVideoPreview");
        }
    }
}
