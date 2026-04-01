package com.samsung.android.gallery.app.ui.viewholders;

import android.view.View;
import android.view.ViewStub;
import com.samsung.android.gallery.widget.cache.ViewPoolCache;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TimelineImageViewHolder extends ImageViewHolder {
    public TimelineImageViewHolder(View view, int i2, boolean z) {
        super(view, i2, z);
    }

    public View inflateStub(ViewStub viewStub) {
        View cachedContentTypeView;
        if (viewStub != this.mContentTypeViewStub || (cachedContentTypeView = ViewPoolCache.getInstance().getCachedContentTypeView()) == null) {
            return super.inflateStub(viewStub);
        }
        ViewUtils.replaceView(viewStub, cachedContentTypeView);
        return cachedContentTypeView;
    }

    public TimelineImageViewHolder(View view, int i2) {
        this(view, i2, true);
    }
}
