package com.samsung.android.gallery.app.ui.list.stories.category.category;

import H7.A;
import V3.b;
import W5.a;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.EventListener;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.IStoriesCategoryView;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.abstraction.StoryCategory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.TextViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoriesCatBaseViewHolder extends ListViewHolder implements EventListener {
    private static final int[] TEXT_GRADIENT_COLORS = {Color.parseColor("#954AE1"), Color.parseColor("#3DB9DB"), Color.parseColor("#32ACFF")};
    protected ImageView mArrow;
    protected TextView mCount;
    private volatile MediaData.SimpleOnDataChangeListener mDataChangeListener;
    protected String mDataKey = "";
    protected View mHeader;
    protected MediaData mMediaData;
    protected TextView mTitle;
    protected final IStoriesCategoryView mView;

    public StoriesCatBaseViewHolder(IStoriesCategoryView iStoriesCategoryView, View view, int i2) {
        super(view, i2);
        this.mView = iStoriesCategoryView;
    }

    private String getTitle() {
        return this.itemView.getContext().getString(StoryCategory.getCategoryTitle(this.mDataKey));
    }

    /* access modifiers changed from: private */
    public void onDataChanged() {
        ThreadUtil.runOnUiThread(new b(9, this));
    }

    private void register(MediaData mediaData) {
        unregister(mediaData);
        if (mediaData != null) {
            this.mDataChangeListener = new a(this);
            mediaData.register(this.mDataChangeListener);
        }
    }

    private void unregister(MediaData mediaData) {
        if (this.mDataChangeListener != null) {
            if (mediaData != null) {
                mediaData.unregister(this.mDataChangeListener);
            }
            this.mDataChangeListener = null;
        }
    }

    public void bindData(MediaData mediaData, Bundle bundle) {
        this.mMediaData = mediaData;
        register(mediaData);
        this.mDataKey = this.mMediaData.getLocationKey();
        bindInfo();
    }

    public void bindInfo() {
        this.mTitle.setText(getTitle());
        this.mCount.setText(StringCompat.toReadableNumber((long) this.mMediaData.getCount()));
        if (PreferenceFeatures.OneUi8x.SUPPORT_TRANSITORY_ON_DEMAND_STORY && isCreationCat() && !TextUtils.isEmpty(this.mTitle.getText())) {
            TextViewUtils.applyGradientText(this.mTitle, TEXT_GRADIENT_COLORS);
        }
    }

    public void bindView(View view) {
        View findViewById = view.findViewById(R.id.header_layout);
        this.mHeader = findViewById;
        if (findViewById != null) {
            findViewById.setOnClickListener(new A(24, this));
            this.mTitle = (TextView) this.mHeader.findViewById(R.id.item_header_name);
            this.mCount = (TextView) this.mHeader.findViewById(R.id.item_header_count);
            this.mArrow = (ImageView) this.mHeader.findViewById(R.id.item_arrow);
        }
    }

    public void destroy() {
        unregister(this.mMediaData);
        clear();
    }

    public RecyclerView.ViewHolder getChildViewHolder(int i2) {
        RecyclerView listView = getListView();
        if (listView != null) {
            return listView.findViewHolderForAdapterPosition(i2);
        }
        return null;
    }

    public String getDataKey() {
        return this.mDataKey;
    }

    public abstract RecyclerView getListView();

    public ArrayList<PreviewViewHolder> getPlayableVH() {
        return new ArrayList<>();
    }

    public void handleDensityChange() {
        unregister(this.mMediaData);
        clear();
    }

    public void handleEvent(Event event, Object... objArr) {
        if (event == Event.UPDATE_BADGE) {
            updateBadge();
        }
    }

    public boolean isCreationCat() {
        return "location://stories/category/creation".equals(this.mDataKey);
    }

    public void onDataChangedOnUi() {
        bindInfo();
    }

    public boolean onHandleInternalEvent(InternalEvent internalEvent, Object... objArr) {
        return false;
    }

    public void onHeaderClicked(View view) {
        int viewPosition;
        if (this.mOnItemClickListener != null && (viewPosition = getViewPosition()) != -1) {
            this.mOnItemClickListener.onItemClick(this, viewPosition, (MediaItem) null, 1001);
        }
    }

    public void recycle() {
        super.recycle();
        unregister(this.mMediaData);
    }

    public abstract void updateBadge();

    public void clear() {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void stop() {
    }

    public void saveState(Bundle bundle) {
    }
}
