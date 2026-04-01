package com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction;

import bc.d;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.livemotion.DurationMeasure;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EventHandler {
    Delegate mDelegate;
    IStoryHighlightView mView;

    public EventHandler(IStoryHighlightView iStoryHighlightView) {
        this.mView = iStoryHighlightView;
    }

    private int getBottomSheetState() {
        return ((Integer) requestData(DataRequest.BOTTOM_SHEET_STATE, 5)).intValue();
    }

    private Object handleRequestData(DataRequest dataRequest, Object... objArr) {
        if (this.mView.isDestroyed()) {
            return null;
        }
        return this.mDelegate.handleRequestData(dataRequest, objArr);
    }

    public void attach(Delegate delegate) {
        this.mDelegate = delegate;
    }

    public BgmExtraInfo getBgmExtraInfo() {
        return (BgmExtraInfo) requestData(DataRequest.REQ_BGM_EXTRA_INFO);
    }

    public MediaItem getCurrentMediaItem() {
        PreviewViewHolder previewViewHolder = (PreviewViewHolder) requestData(DataRequest.CURRENT_VIEW_HOLDER);
        if (previewViewHolder != null) {
            return previewViewHolder.getMediaItem();
        }
        return null;
    }

    public PreviewViewHolder getCurrentViewHolder() {
        return (PreviewViewHolder) requestData(DataRequest.CURRENT_VIEW_HOLDER);
    }

    public DurationMeasure getDurationMeasure() {
        return (DurationMeasure) requestData(DataRequest.DURATION_MEASURE, new DurationMeasure());
    }

    public EffectTheme getEffectTheme() {
        return (EffectTheme) requestData(DataRequest.REQ_EFFECT_THEME, EffectTheme.Relaxing);
    }

    public Filter getFilter() {
        return (Filter) requestData(DataRequest.REQ_FILTER_CURRENT, Filter.NONE);
    }

    /* renamed from: handleEvent */
    public final void lambda$postEvent$0(Event event, Object... objArr) {
        if (!this.mView.isDestroyed()) {
            this.mDelegate.handlePostEvent(event, objArr);
            this.mView.handlePostEvent(event, objArr);
        }
    }

    public boolean isActiveZoom() {
        return ((Boolean) requestData(DataRequest.IS_ACTIVE_ZOOM, Boolean.FALSE)).booleanValue();
    }

    public boolean isBgmPickerVisible() {
        return ((Boolean) requestData(DataRequest.BGM_PICKER_VISIBLE, Boolean.FALSE)).booleanValue();
    }

    public boolean isBottomSheetHidden() {
        return SheetBehaviorCompat.isHidden(getBottomSheetState());
    }

    public boolean isBottomSheetVisible() {
        return SheetBehaviorCompat.isInExpanded(getBottomSheetState());
    }

    public boolean isCustomTheme() {
        return ((Boolean) requestData(DataRequest.IS_CUSTOM_THEME, Boolean.FALSE)).booleanValue();
    }

    public boolean isFilterViewVisible() {
        return ((Boolean) requestData(DataRequest.THEME_VIEW_VISIBLE, Boolean.FALSE)).booleanValue();
    }

    public boolean isKeepPaused() {
        return ((Boolean) requestData(DataRequest.PLAYER_KEEP_PAUSED, Boolean.FALSE)).booleanValue();
    }

    public boolean isShowingBottomDecoView() {
        return ((Boolean) requestData(DataRequest.BOTTOM_DECO_VISIBLE, Boolean.FALSE)).booleanValue();
    }

    public boolean isShowingRelatedView() {
        return ((Boolean) requestData(DataRequest.RELATED_VIEW_VISIBLE, Boolean.FALSE)).booleanValue();
    }

    public final void postEvent(Event event, Object... objArr) {
        ThreadUtil.postOnUiThread(new d((Object) this, (Object) event, (Object) objArr, 18));
    }

    public Object requestData(DataRequest dataRequest) {
        return handleRequestData(dataRequest, new Object[0]);
    }

    public <T> T requestData(DataRequest dataRequest, T t) {
        T handleRequestData = handleRequestData(dataRequest, new Object[0]);
        return handleRequestData != null ? handleRequestData : t;
    }

    public <T> T requestData(DataRequest dataRequest, Object[] objArr, T t) {
        T handleRequestData = handleRequestData(dataRequest, objArr);
        return handleRequestData != null ? handleRequestData : t;
    }
}
