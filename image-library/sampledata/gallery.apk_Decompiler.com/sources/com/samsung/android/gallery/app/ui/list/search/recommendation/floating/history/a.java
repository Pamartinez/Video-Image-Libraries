package com.samsung.android.gallery.app.ui.list.search.recommendation.floating.history;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ThumbnailLoadedListener {
    public final /* synthetic */ FloatingHistoryViewHolder d;

    public /* synthetic */ a(FloatingHistoryViewHolder floatingHistoryViewHolder) {
        this.d = floatingHistoryViewHolder;
    }

    public final void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.postOnUiThread(new d(this.d, bitmap));
    }
}
