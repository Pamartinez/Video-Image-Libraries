package com.samsung.android.gallery.widget.filmstrip3.selection;

import android.content.Context;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.widget.databinding.SelectionFilmStripImageItemLayoutBinding;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripAdapter;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import i.C0212a;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SelectionFilmStripAdapter extends FilmStripAdapter {
    private final HashSet<Integer> mSelectedList = new HashSet<>();

    public SelectionFilmStripAdapter(Context context, MediaData mediaData) {
        super(context, mediaData);
    }

    private void updateChecked(SelectionFilmStripViewHolder selectionFilmStripViewHolder, int i2) {
        selectionFilmStripViewHolder.setChecked(this.mSelectedList.contains(Integer.valueOf(i2)));
    }

    private void updateSavedIcon(SelectionFilmStripViewHolder selectionFilmStripViewHolder) {
        MediaItem mediaItem = selectionFilmStripViewHolder.getMediaItem();
        if (mediaItem != null) {
            StringBuilder s = C0212a.s(FileUtils.getDirectoryFromPath(mediaItem.getPath(), true));
            s.append(FileUtils.getBaseName(mediaItem.getTitle()));
            s.append("_saved.jpg");
            selectionFilmStripViewHolder.setSaved(FileUtils.exists(s.toString()));
        }
    }

    public int getSelectedCount() {
        return this.mSelectedList.size();
    }

    public boolean isBestItemSelected(long j2, int i2, int i7) {
        if (i2 != -1 && getMediaItem(i2) != null && j2 == getMediaItem(i2).getFileId()) {
            return true;
        }
        Iterator<Integer> it = this.mSelectedList.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (i7 != intValue && getMediaItem(intValue) != null && j2 == getMediaItem(intValue).getFileId()) {
                return true;
            }
        }
        return false;
    }

    public void selectAll(boolean z) {
        int itemCount = getItemCount();
        this.mSelectedList.clear();
        if (z) {
            for (int i2 = 0; i2 < itemCount; i2++) {
                this.mSelectedList.add(Integer.valueOf(i2));
            }
        }
        notifyItemRangeChanged(0, itemCount, "select=" + z);
    }

    public FilmStripViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new SelectionFilmStripViewHolder(SelectionFilmStripImageItemLayoutBinding.inflate(this.mLayoutInflater, viewGroup, false));
    }

    public void onBindViewHolder(FilmStripViewHolder filmStripViewHolder, int i2) {
        super.onBindViewHolder(filmStripViewHolder, i2);
        SelectionFilmStripViewHolder selectionFilmStripViewHolder = (SelectionFilmStripViewHolder) filmStripViewHolder;
        updateSavedIcon(selectionFilmStripViewHolder);
        updateChecked(selectionFilmStripViewHolder, i2);
    }

    public void onBindViewHolder(FilmStripViewHolder filmStripViewHolder, int i2, List<Object> list) {
        if (list.size() <= 0 || !list.get(0).toString().startsWith("select=")) {
            super.onBindViewHolder(filmStripViewHolder, i2, list);
            return;
        }
        boolean z = UnsafeCast.toBoolean(list.get(0).toString().replace("select=", ""), false);
        ((SelectionFilmStripViewHolder) filmStripViewHolder).setChecked(z);
        if (z) {
            this.mSelectedList.add(Integer.valueOf(i2));
        } else {
            this.mSelectedList.remove(Integer.valueOf(i2));
        }
    }
}
