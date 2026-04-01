package com.samsung.android.gallery.app.ui.viewer2.selection;

import G7.e;
import android.os.Bundle;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemView;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SelectionViewPresenter extends MvpBasePresenter<ISelectionView> {
    private int mChangedBestItemIndex = -1;
    private final FastOptionItemHandler mFastOptionViewListener;

    public SelectionViewPresenter(Blackboard blackboard, ISelectionView iSelectionView) {
        super(blackboard, iSelectionView);
        this.mFastOptionViewListener = new FastOptionItemHandler(blackboard, iSelectionView, this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleEvent$0(Object obj, Bundle bundle) {
        Log.d(this.TAG, "EVENT_STOP_SELECTION :  paused-->resumed");
        ((ISelectionView) this.mView).finish();
    }

    public MediaItem[] getAllItems() {
        return ((ISelectionView) this.mView).getAllItems();
    }

    public MediaItem getBestItem() {
        V v = this.mView;
        if (v != null) {
            return ((ISelectionView) v).getBestItem();
        }
        return null;
    }

    public FastOptionItemView.ItemSelectedListener getFastOptionViewListener() {
        return this.mFastOptionViewListener;
    }

    public MediaItem[] getSelectedItems() {
        return ((ISelectionView) this.mView).getSelectedItems();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 != 1003) {
            if (i2 == 3032) {
                Integer[] selectedPositions = ((ISelectionView) this.mView).getSelectedPositions();
                if (selectedPositions == null || selectedPositions.length > 1) {
                    Log.d(this.TAG, "EVENT_UPDATE_BEST_ITEM : wrong selected item");
                } else {
                    this.mChangedBestItemIndex = selectedPositions[0].intValue();
                }
            }
            return super.handleEvent(eventMessage);
        } else if (((ISelectionView) this.mView).isViewResumed()) {
            Log.d(this.TAG, "EVENT_STOP_SELECTION :  resumed");
            this.mBlackboard.postEvent(EventMessage.obtain(20001, this.mChangedBestItemIndex, Boolean.FALSE));
            ((ISelectionView) this.mView).finish();
            return true;
        } else {
            Log.d(this.TAG, "EVENT_STOP_SELECTION :  paused");
            subscribeInstant("lifecycle://on_activity_resume", new e(3, this));
            return true;
        }
    }

    public boolean isSelectionMode() {
        return true;
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
    }
}
