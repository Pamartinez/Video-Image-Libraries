package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.filter.StoryFilterApplier;
import java.util.function.BiConsumer;
import o6.m;
import o6.n;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilterApplyDelegate extends Delegate {
    private final StoryFilterApplier mStoryFilterApplier;

    public FilterApplyDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
        this.mStoryFilterApplier = new StoryFilterApplier(iStoryHighlightView);
    }

    /* access modifiers changed from: private */
    public void applyFilterToBitmap(Object... objArr) {
        if (objArr.length >= 3) {
            Bitmap bitmap = objArr[0];
            boolean booleanValue = objArr[1].booleanValue();
            BiConsumer biConsumer = objArr[2];
            if (objArr.length < 4) {
                this.mStoryFilterApplier.apply(bitmap, booleanValue, biConsumer);
            } else if (objArr.length < 6) {
                int intValue = objArr[4].intValue();
                this.mStoryFilterApplier.apply(bitmap, objArr[3], intValue, booleanValue, biConsumer);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$0(Object[] objArr) {
        setFilter(this.mEventHandler.getFilter());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$1(Object[] objArr) {
        setFilter(this.mEventHandler.getFilter());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$2(DataRequest dataRequest, Object[] objArr) {
        return this.mStoryFilterApplier.getFilterPath();
    }

    /* access modifiers changed from: private */
    public void setFilter(Object... objArr) {
        this.mStoryFilterApplier.setImageFilter(objArr[0]);
        if (this.mView.getOptions().supportFilter()) {
            this.mEventHandler.postEvent(Event.ON_FILTER_CHANGED, new Object[0]);
        }
    }

    public void addListenEvent() {
        addEvent(Event.CHANGE_FILTER, new n(this, 0));
        addEvent(Event.APPLY_FILTER_TO_BITMAP, new n(this, 1));
        addEvent(Event.ON_THEME_INITIALIZED, new n(this, 2));
        addEvent(Event.ON_THEME_CHANGED, new n(this, 3));
    }

    public void addRequestProvider() {
        addRequestProvider(DataRequest.FILTER_PATH, new m(0, this));
    }

    public void onDestroy() {
        this.mStoryFilterApplier.destroy();
    }
}
