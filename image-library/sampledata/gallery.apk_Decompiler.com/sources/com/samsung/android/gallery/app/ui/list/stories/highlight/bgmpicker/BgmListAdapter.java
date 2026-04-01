package com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import g6.g;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmListAdapter extends RecyclerView.Adapter<BgmItemViewHolder> {
    private String mFocusBgmName;
    private final ArrayList<String> mItems = new ArrayList<>();
    private final Listener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
        boolean isDownloaded(String str);

        boolean isDownloading(String str);

        boolean isPlayable();

        void onSelect(BgmItemViewHolder bgmItemViewHolder);
    }

    public BgmListAdapter(Listener listener) {
        this.mListener = listener;
    }

    private int getItemResId() {
        return R.layout.story_bgm_list_item_layout;
    }

    /* access modifiers changed from: private */
    public void onSelect(BgmItemViewHolder bgmItemViewHolder) {
        Log.d("BgmPickerAdapter", "onSelect", bgmItemViewHolder);
        this.mListener.onSelect(bgmItemViewHolder);
    }

    public String getFocusedBgm() {
        return this.mFocusBgmName;
    }

    public int getFocusedIndex() {
        return this.mItems.indexOf(this.mFocusBgmName);
    }

    public int getItemCount() {
        return this.mItems.size();
    }

    public void notifyItemChanged(String str) {
        if (this.mItems.contains(str)) {
            notifyItemChanged(this.mItems.indexOf(str));
        }
    }

    public void setFocus(String str) {
        this.mFocusBgmName = str;
        notifyItemRangeChanged(0, this.mItems.size(), "UPDATE_FOCUS");
    }

    public void setTheme(EffectTheme effectTheme) {
        this.mItems.clear();
        for (EffectTheme effectTheme2 : EffectTheme.values()) {
            if (effectTheme2.equals(effectTheme)) {
                this.mItems.addAll(Arrays.asList(effectTheme2.getBgmList()));
                notifyDataSetChanged();
                return;
            }
        }
    }

    public BgmItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new BgmItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getItemResId(), viewGroup, false));
    }

    public void onViewRecycled(BgmItemViewHolder bgmItemViewHolder) {
        bgmItemViewHolder.recycle();
    }

    public void onBindViewHolder(BgmItemViewHolder bgmItemViewHolder, int i2) {
        bgmItemViewHolder.setTitle(this.mItems.get(i2));
        int i7 = 0;
        if (this.mListener.isDownloading(bgmItemViewHolder.getTitle())) {
            bgmItemViewHolder.enableProgressCircle(true);
        } else if (!this.mListener.isDownloaded(bgmItemViewHolder.getTitle())) {
            bgmItemViewHolder.enableDownloadIcon(true);
        } else {
            bgmItemViewHolder.enableDownloadIcon(false);
            bgmItemViewHolder.enableProgressCircle(false);
        }
        bgmItemViewHolder.setOnClickListener(new g(11, this));
        bgmItemViewHolder.setFocused(bgmItemViewHolder.getTitle().equals(this.mFocusBgmName), this.mListener.isPlayable());
        View findViewById = bgmItemViewHolder.itemView.findViewById(R.id.divider);
        if (this.mItems.size() - 1 == i2) {
            i7 = 4;
        }
        ViewUtils.setVisibility(findViewById, i7);
    }

    public void onBindViewHolder(BgmItemViewHolder bgmItemViewHolder, int i2, List<Object> list) {
        if (list.contains("UPDATE_FOCUS")) {
            bgmItemViewHolder.setFocused(bgmItemViewHolder.getTitle().equals(this.mFocusBgmName), this.mListener.isPlayable());
        } else {
            super.onBindViewHolder(bgmItemViewHolder, i2, list);
        }
    }
}
