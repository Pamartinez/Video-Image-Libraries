package com.samsung.android.gallery.app.ui.list.stories.highlight.theme;

import A4.Q;
import B8.e;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.module.story.FilterHolder;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import g6.f;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import o6.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesThemeViewAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private MediaItem mBaseItem;
    private int mFocusedPosition = -1;
    /* access modifiers changed from: private */
    public final ArrayList<ThemeItem> mItems = new ArrayList<>();
    private Consumer<Integer> mOnItemClickListener;
    private final int mRoundRadius;
    private final Drawable mThumbnailDrawable;
    private final IStoryHighlightView mView;

    public StoriesThemeViewAdapter(IStoryHighlightView iStoryHighlightView) {
        int i2;
        Drawable drawable;
        this.mView = iStoryHighlightView;
        Context context = iStoryHighlightView.getContext();
        if (context != null) {
            i2 = context.getResources().getDimensionPixelSize(R.dimen.stories_filter_item_round_radius);
        } else {
            i2 = 0;
        }
        this.mRoundRadius = i2;
        if (context != null) {
            drawable = context.getDrawable(R.drawable.stories_filter_thumbnail_border);
        } else {
            drawable = null;
        }
        this.mThumbnailDrawable = drawable;
        setHasStableIds(true);
    }

    private void bindThumbnail(ListViewHolder listViewHolder, ThemeItem themeItem) {
        MediaItem mediaItem = themeItem.getMediaItem();
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        listViewHolder.setThumbKind(thumbKind);
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            onBindThumbnail(listViewHolder, memCache, themeItem);
        } else {
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            Objects.requireNonNull(mediaItem);
            instance.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new Q((Object) this, (Object) listViewHolder, (Object) themeItem, 26));
        }
        listViewHolder.setThumbnailShape(1, (float) this.mRoundRadius);
    }

    private boolean equal(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || mediaItem.getFileId() != mediaItem2.getFileId()) {
            return false;
        }
        return true;
    }

    private int getIntensity(Filter filter) {
        return filter.getIntensity();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindThumbnail$0(ListViewHolder listViewHolder, ThemeItem themeItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        onBindThumbnail(listViewHolder, bitmap, themeItem);
    }

    private ThemeItem loadCustomThemeItem() {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.StoryDefaultTheme)) {
            return CustomThemeHelper.loadCustomThemeItemForDefaultTheme(this.mView, this.mBaseItem);
        }
        return CustomThemeHelper.loadCustomFilterItem(this.mBaseItem);
    }

    private void onBindThumbnail(ListViewHolder listViewHolder, Bitmap bitmap, ThemeItem themeItem) {
        this.mView.getEventHandler().lambda$postEvent$0(Event.APPLY_FILTER_TO_BITMAP, bitmap, Boolean.TRUE, new f(11, listViewHolder), themeItem.getFilter(), Integer.valueOf(getIntensity(themeItem.getFilter())));
    }

    /* access modifiers changed from: private */
    public void onItemClicked(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (this.mView.setInputBlock("StoriesThemeViewAdapter_onItemClicked", 500) && this.mFocusedPosition != i2) {
            ThemeItem themeItem = this.mItems.get(i2);
            boolean isCustom = this.mItems.get(0).isCustom();
            if (themeItem.isCustom() || themeItem.getFilter().noneFilter() || !isCustom) {
                updateFocusedViewPosition(i2);
            } else {
                CustomThemeHelper.reset();
                notifyUpdateCustomThemeItem(false);
                updateFocusedViewPosition(i2 - 1);
            }
            Consumer<Integer> consumer = this.mOnItemClickListener;
            if (consumer != null) {
                consumer.accept(Integer.valueOf(this.mFocusedPosition));
            }
        }
    }

    public BgmExtraInfo getBgmExtraInfo(int i2) {
        return this.mItems.get(i2).getBgmExtraInfo();
    }

    public ThemeItem getCustomThemeItem() {
        return loadCustomThemeItem();
    }

    public EffectTheme getEffectTheme(int i2) {
        return this.mItems.get(i2).getTheme();
    }

    public Filter getFilter(int i2) {
        return this.mItems.get(i2).getFilter();
    }

    public int getFocusedPosition() {
        return this.mFocusedPosition;
    }

    public ThemeItem getItem(int i2) {
        if (i2 < 0 || i2 >= this.mItems.size()) {
            return null;
        }
        return this.mItems.get(i2);
    }

    public int getItemCount() {
        return this.mItems.size();
    }

    public long getItemId(int i2) {
        return (long) this.mItems.get(i2).getUniqueId();
    }

    public boolean isCustom(int i2) {
        return this.mItems.get(i2).isCustom();
    }

    public void moveToCustomTheme() {
        updateFocusedViewPosition(0);
    }

    public void notifyUpdateCustomThemeItem(boolean z) {
        ThemeItem themeItem;
        if (z) {
            themeItem = getCustomThemeItem();
        } else {
            themeItem = null;
        }
        if (themeItem != null) {
            if (this.mItems.get(0).isCustom()) {
                this.mItems.remove(0);
            }
            this.mItems.add(0, themeItem);
            notifyItemChanged(0);
        } else if (this.mItems.get(0).isCustom()) {
            this.mItems.remove(0);
            notifyItemRemoved(0);
        }
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new StoryThemeViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_stories_filter_layout, viewGroup, false), 0) {
            public String getContentDescription() {
                return ((ThemeItem) StoriesThemeViewAdapter.this.mItems.get(getAbsoluteAdapterPosition())).getDisplayName();
            }

            public String getTitleText(MediaItem mediaItem) {
                return ((ThemeItem) StoriesThemeViewAdapter.this.mItems.get(getAbsoluteAdapterPosition())).getDisplayName();
            }
        };
    }

    public void reset() {
        this.mFocusedPosition = -1;
        CustomThemeHelper.reset();
    }

    public void setOnItemClickListener(Consumer<Integer> consumer) {
        this.mOnItemClickListener = consumer;
    }

    public void updateFocusedViewPosition(int i2) {
        this.mFocusedPosition = i2;
        notifyItemRangeChanged(0, this.mItems.size(), "update_border");
    }

    public void updateThemeItem(MediaItem mediaItem, boolean z) {
        if (!equal(mediaItem, this.mBaseItem) || z) {
            this.mBaseItem = mediaItem;
            this.mItems.clear();
            ThemeItem loadCustomThemeItem = loadCustomThemeItem();
            if (loadCustomThemeItem != null) {
                this.mItems.add(loadCustomThemeItem);
            }
            for (Filter next : FilterHolder.getFilters()) {
                this.mItems.add(new ThemeItem(mediaItem, next));
                if (this.mFocusedPosition == -1 && next.equals(this.mView.getEventHandler().getFilter())) {
                    updateFocusedViewPosition(this.mItems.size() - 1);
                }
            }
        }
    }

    public void onViewAttachedToWindow(ListViewHolder listViewHolder) {
        super.onViewAttachedToWindow(listViewHolder);
        if (listViewHolder.getAbsoluteAdapterPosition() == this.mFocusedPosition) {
            listViewHolder.addThumbnailBorder(this.mThumbnailDrawable);
        } else {
            listViewHolder.restoreThumbnailBorder();
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        MediaItem mediaItem;
        ThemeItem item = getItem(i2);
        if (item != null && (mediaItem = item.getMediaItem()) != null) {
            listViewHolder.bind(mediaItem);
            listViewHolder.setOnItemClickListener(new p(18, this));
            bindThumbnail(listViewHolder, item);
            listViewHolder.itemView.setContentDescription(item.getDisplayName());
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (!list.contains("update_border")) {
            super.onBindViewHolder(listViewHolder, i2, list);
        } else if (i2 == this.mFocusedPosition) {
            listViewHolder.addThumbnailBorder(this.mThumbnailDrawable);
        } else {
            listViewHolder.restoreThumbnailBorder();
        }
    }
}
