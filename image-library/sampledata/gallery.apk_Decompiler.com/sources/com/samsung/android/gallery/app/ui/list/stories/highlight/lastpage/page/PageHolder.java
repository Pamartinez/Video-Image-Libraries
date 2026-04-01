package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.EntryEffectHelper;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.LastPageDataHolder;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import o6.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PageHolder extends RecyclerView.ViewHolder {
    protected final String TAG = getClass().getSimpleName();
    protected EntryEffectHelper mEntryEffectHelper;
    protected final EventHandler mEventHandler;
    protected LastPageDataHolder mLastPageDataHolder;
    protected boolean mPreviewRequested;
    protected final View mRootParent;
    private final LastPageDataHolder.UpdateListener mUpdateListener = new LastPageDataHolder.UpdateListener() {
        public void focusPositionChanged() {
            PageHolder pageHolder = PageHolder.this;
            pageHolder.onFocusPositionChanged(pageHolder.mLastPageDataHolder.getFocusPosition());
        }

        public void resetCountDown() {
            PageHolder.this.onResetCountDown();
        }
    };
    protected final IStoryHighlightView mView;

    public PageHolder(View view, IStoryHighlightView iStoryHighlightView, View view2) {
        super(view);
        this.mView = iStoryHighlightView;
        this.mRootParent = view2;
        EventHandler eventHandler = iStoryHighlightView.getEventHandler();
        this.mEventHandler = eventHandler;
        this.mEntryEffectHelper = (EntryEffectHelper) eventHandler.requestData(DataRequest.REQ_LAST_PAGE_ENTRY_EFFECT_HELPER);
        bindView(view);
    }

    private void initFocusHolder() {
        LastPageDataHolder lastPageDataHolder = (LastPageDataHolder) this.mEventHandler.requestData(DataRequest.REQ_LAST_PAGE_DATA_HOLDER);
        this.mLastPageDataHolder = lastPageDataHolder;
        lastPageDataHolder.addUpdateListener(this.mUpdateListener);
    }

    public void bind(PageItem pageItem) {
        initFocusHolder();
        this.itemView.post(new t(18, this));
    }

    public View getContentView() {
        return null;
    }

    public Context getContext() {
        return this.mRootParent.getContext();
    }

    public float getFloatDimen(int i2) {
        return ResourceCompat.getFloatFromDimension(getContext(), i2);
    }

    public int getIntDimen(int i2) {
        return ResourceCompat.getDimensionPixelOffset(getContext(), i2, 0);
    }

    public int getSizeWithBase(int i2, float f) {
        return (int) (getFloatDimen(i2) * f);
    }

    public boolean isLandscape() {
        return ResourceCompat.isLandscape(this.mRootParent);
    }

    public void recycle() {
        this.mLastPageDataHolder.removeUpdateListener(this.mUpdateListener);
        this.mPreviewRequested = false;
    }

    public void startPreview() {
        this.mPreviewRequested = true;
    }

    public void stopPreview() {
        this.mPreviewRequested = false;
    }

    public void clear() {
    }

    public void onBindItem() {
    }

    public void onResetCountDown() {
    }

    public void pause() {
    }

    public void prepareResolutionChange() {
    }

    public void resume() {
    }

    public void updateTitle() {
    }

    public void bindView(View view) {
    }

    public void onFocusPositionChanged(int i2) {
    }

    public void onLastPageShow(boolean z) {
    }
}
