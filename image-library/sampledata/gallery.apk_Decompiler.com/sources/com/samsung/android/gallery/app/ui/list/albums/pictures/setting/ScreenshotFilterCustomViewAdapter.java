package com.samsung.android.gallery.app.ui.list.albums.pictures.setting;

import android.content.Context;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.IScreenshotFilterCustomView;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TriConsumer;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ScreenshotFilterCustomViewAdapter<V extends IScreenshotFilterCustomView> extends BaseListViewAdapter<V> {
    private final String TAG = getClass().getSimpleName();
    private boolean mIsReorderMode;
    private final TriConsumer<RecyclerView.ViewHolder, View, MotionEvent> mTouchCallback;

    public ScreenshotFilterCustomViewAdapter(V v, String str, TriConsumer<RecyclerView.ViewHolder, View, MotionEvent> triConsumer) {
        super(v, str);
        this.mTouchCallback = triConsumer;
    }

    /* access modifiers changed from: private */
    public void onReorderItemTouch(RecyclerView.ViewHolder viewHolder, View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mTouchCallback.accept(viewHolder, view, motionEvent);
            setHighlightedBorder(view);
        }
    }

    private void setHighlightedBorder(View view) {
        if (view != null) {
            ((CardView) view).setCardBackgroundColor(AppResources.getColor(R.color.recommended_bg_color));
            view.setForeground(AppResources.getAppContext().getDrawable(R.drawable.search_custom_highlighted_border));
        }
    }

    public void changeReorderState(boolean z) {
        this.mIsReorderMode = z;
        notifyDataSetChanged();
    }

    public boolean isReorderMode() {
        return this.mIsReorderMode;
    }

    public ScreenshotFilterCustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new ScreenshotFilterCustomViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_screenshot_customize, viewGroup, false), i2, new b(this));
    }

    public void resetHighlightedBorder(RecyclerView.ViewHolder viewHolder) {
        Context context = viewHolder.itemView.getContext();
        if (context == null) {
            Log.e(this.TAG, "context is null");
            return;
        }
        ((CardView) viewHolder.itemView).setCardBackgroundColor(context.getColor(R.color.settings_preference_list_item_background));
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843534, typedValue, true);
        viewHolder.itemView.setForeground(context.getDrawable(typedValue.resourceId));
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        ((ScreenshotFilterCustomViewHolder) listViewHolder).setReorderMode(isReorderMode());
    }
}
