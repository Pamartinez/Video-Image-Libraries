package com.samsung.android.gallery.app.ui.list.search.creaturecoverchoice;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesHeaderViewAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.search.creaturecoverchoice.ICreatureCoverChoiceView;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureCoverChoiceAdapter<V extends ICreatureCoverChoiceView> extends PicturesHeaderViewAdapter<V> {
    public CreatureCoverChoiceAdapter(V v, String str, View view, boolean z) {
        super(v, str, view, z);
    }

    private void updateVisualCue(ListViewHolder listViewHolder, int i2) {
        MediaItem mediaItemFromCache = getMediaItemFromCache(i2);
        if (mediaItemFromCache == null) {
            return;
        }
        if (((ICreatureCoverChoiceView) this.mView).getCurrentCoverId() == null || !((ICreatureCoverChoiceView) this.mView).getCurrentCoverId().equals(String.valueOf(mediaItemFromCache.getFileId()))) {
            ((ImageViewHolder) listViewHolder).eraseVisualCue();
        } else {
            ((ImageViewHolder) listViewHolder).drawVisualCue();
        }
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return new CreatureCoverChoiceViewHolderFactory(context);
    }

    public int getDecoItemViewType() {
        return 0;
    }

    public int getItemViewType(int i2) {
        if (i2 == 0) {
            return -3;
        }
        return 0;
    }

    public ThumbKind getThumbnailKind() {
        return ThumbKind.LARGE_KIND;
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        super.onListItemClickInternal(listViewHolder, i2, mediaItem, i7);
        String valueOf = String.valueOf(mediaItem.getFileId());
        String valueOf2 = String.valueOf(CreatureData.of(mediaItem).faceGroupId);
        if (!TextUtils.equals(((ICreatureCoverChoiceView) this.mView).getCurrentCoverId(), valueOf) || !TextUtils.equals(((ICreatureCoverChoiceView) this.mView).getCurrentCoverFaceGroupId(), valueOf2)) {
            ((ICreatureCoverChoiceView) this.mView).setCurrentCoverId(valueOf);
            ((ICreatureCoverChoiceView) this.mView).setCurrentCoverFaceGroupId(valueOf2);
            notifyItemRangeChanged("update_cue");
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        updateVisualCue(listViewHolder, i2);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (list.contains("update_cue")) {
            updateVisualCue(listViewHolder, i2);
        } else {
            super.onBindViewHolder(listViewHolder, i2, list);
        }
    }
}
