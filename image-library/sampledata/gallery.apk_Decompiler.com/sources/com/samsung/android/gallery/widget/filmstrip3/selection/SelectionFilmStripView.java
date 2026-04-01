package com.samsung.android.gallery.widget.filmstrip3.selection;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripView;
import com.samsung.android.gallery.widget.filmstrip3.OnFilmStripItemClickListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SelectionFilmStripView extends FilmStripView {
    private SelectionFilmStripAdapter mFilmStripAdapter;

    public SelectionFilmStripView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void addFilmStripItemListener(OnFilmStripItemClickListener onFilmStripItemClickListener) {
        SelectionFilmStripAdapter selectionFilmStripAdapter = this.mFilmStripAdapter;
        if (selectionFilmStripAdapter != null) {
            selectionFilmStripAdapter.addFilmStripItemClickListener(onFilmStripItemClickListener);
        }
    }

    public boolean isScrolling() {
        if (getScrollState() != 0) {
            return true;
        }
        return false;
    }

    public void notifyDataChanged() {
        if (this.mFilmStripAdapter != null) {
            getRecycledViewPool().clear();
            this.mFilmStripAdapter.notifyDataSetChanged();
        }
    }

    public void removeFilmStripItemListener(OnFilmStripItemClickListener onFilmStripItemClickListener) {
        SelectionFilmStripAdapter selectionFilmStripAdapter = this.mFilmStripAdapter;
        if (selectionFilmStripAdapter != null) {
            selectionFilmStripAdapter.removeFilmStripItemClickListener(onFilmStripItemClickListener);
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.mFilmStripAdapter = (SelectionFilmStripAdapter) adapter;
        super.setAdapter(adapter);
    }

    public void setFilmStripData(MediaData mediaData) {
        SelectionFilmStripAdapter selectionFilmStripAdapter = new SelectionFilmStripAdapter(getContext(), mediaData);
        this.mFilmStripAdapter = selectionFilmStripAdapter;
        setAdapter(selectionFilmStripAdapter);
    }

    public SelectionFilmStripAdapter getAdapter() {
        return (SelectionFilmStripAdapter) super.getAdapter();
    }
}
