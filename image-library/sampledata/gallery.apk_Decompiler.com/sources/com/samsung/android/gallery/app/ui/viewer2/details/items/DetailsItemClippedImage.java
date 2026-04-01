package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Pair;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartBrowserByPathCmd;
import com.samsung.android.gallery.app.controller.viewer.StartCapturedFileViewCmd;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DetailsItemClippedImage extends DetailsItem implements DetailsViewUpdater {
    public DetailsItemClippedImage(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
    }

    /* access modifiers changed from: private */
    public void onPathClicked(View view) {
        Pair<String, Boolean> pair;
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem != null) {
            pair = DetailsData.of(currentItem).getClippedData();
        } else {
            pair = null;
        }
        if (pair == null) {
            Log.e(this.TAG, "clippedData is null when onPathClicked");
        } else if (((Boolean) pair.second).booleanValue()) {
            new StartCapturedFileViewCmd().execute(this.mEventContext, currentItem);
        } else {
            new StartBrowserByPathCmd().execute(this.mEventContext, currentItem);
        }
    }

    public int getLayoutId() {
        return R.id.moreinfo_captured_path;
    }

    public void onViewInflate(ViewStub viewStub, View view) {
        getView(view, (int) R.id.captured_path_detail).setOnClickListener(new g(1, this));
    }

    public void registerViewUpdater() {
        this.mViewUpdaterMap.put(DetailsUpdateKey.CLIPPED_DATA, this);
    }

    public boolean supportItem(MediaItem mediaItem) {
        if (DetailsData.of(mediaItem).getClippedData() != null) {
            return true;
        }
        return false;
    }

    public void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        int i2;
        Pair<String, Boolean> clippedData = DetailsData.of(mediaItem).getClippedData();
        if (clippedData == null) {
            Log.e(this.TAG, "clippedData is null when updateViewInternal");
            return;
        }
        String str = (String) clippedData.first;
        if (((Boolean) clippedData.second).booleanValue()) {
            i2 = R.string.original;
        } else {
            i2 = R.string.more_info_capture_from;
        }
        setTextAndVisibility((int) R.id.captured_path_title, i2);
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
        ((TextView) getView(R.id.captured_path_detail)).setText(spannableString);
    }
}
