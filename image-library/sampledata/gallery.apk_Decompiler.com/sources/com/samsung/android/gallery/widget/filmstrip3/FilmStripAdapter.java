package com.samsung.android.gallery.widget.filmstrip3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.cache.LruCache;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.databinding.Filmstrip3GroupImageItemLayoutBinding;
import com.samsung.android.gallery.widget.databinding.Filmstrip3ImageItemLayoutBinding;
import com.samsung.android.gallery.widget.databinding.Filmstrip3MotionPhotoItemLayoutBinding;
import com.samsung.android.gallery.widget.databinding.Filmstrip3VideoItemLayoutBinding;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilmStripAdapter extends RecyclerView.Adapter<FilmStripViewHolder> {
    LruCache<Integer, MediaItem> mCache = new LruCache<>(128);
    private int mCount = -1;
    private final RecyclerView.AdapterDataObserver mDataChangeObserver;
    protected final LayoutInflater mLayoutInflater;
    private MediaData mMediaData;
    protected final OnFilmStripItemClickListener mOnItemClickListener = new OnFilmStripItemClickListener() {
        public void onItemClick(int i2, FilmStripViewHolder filmStripViewHolder) {
            Iterator it = FilmStripAdapter.this.mOnItemClickListenerList.iterator();
            while (it.hasNext()) {
                ((OnFilmStripItemClickListener) it.next()).onItemClick(i2, filmStripViewHolder);
            }
        }

        public void onItemExpanded(int i2, FilmStripViewHolder filmStripViewHolder) {
            Iterator it = FilmStripAdapter.this.mOnItemClickListenerList.iterator();
            while (it.hasNext()) {
                ((OnFilmStripItemClickListener) it.next()).onItemExpanded(i2, filmStripViewHolder);
            }
        }

        public void onItemRestored(int i2, FilmStripViewHolder filmStripViewHolder) {
            Iterator it = FilmStripAdapter.this.mOnItemClickListenerList.iterator();
            while (it.hasNext()) {
                ((OnFilmStripItemClickListener) it.next()).onItemRestored(i2, filmStripViewHolder);
            }
        }

        public void onRequestExpandSeeker(int i2, FilmStripViewHolder filmStripViewHolder) {
            Iterator it = FilmStripAdapter.this.mOnItemClickListenerList.iterator();
            while (it.hasNext()) {
                ((OnFilmStripItemClickListener) it.next()).onRequestExpandSeeker(i2, filmStripViewHolder);
            }
        }
    };
    /* access modifiers changed from: private */
    public final ArrayList<OnFilmStripItemClickListener> mOnItemClickListenerList = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ItemViewAccessibilityDelegate extends View.AccessibilityDelegate {
        final int mCount;
        final String mFormat;
        final int mPosition;

        public ItemViewAccessibilityDelegate(Context context, int i2, int i7) {
            this.mFormat = context.getResources().getString(R$string.film_strip);
            this.mPosition = i2 + 1;
            this.mCount = i7;
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            if (accessibilityEvent.getEventType() == 32768) {
                view.setContentDescription(String.format(this.mFormat, new Object[]{Integer.valueOf(this.mPosition), Integer.valueOf(this.mCount)}));
            }
        }
    }

    public FilmStripAdapter(Context context, MediaData mediaData) {
        this.mLayoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        setFilmStripData(mediaData, false);
        setHasStableIds(true);
        AnonymousClass2 r32 = new RecyclerView.AdapterDataObserver() {
            public void onChanged() {
                FilmStripAdapter.this.clearCache();
            }

            public void onItemRangeChanged(int i2, int i7) {
                FilmStripAdapter.this.clearCache();
            }
        };
        this.mDataChangeObserver = r32;
        registerAdapterDataObserver(r32);
    }

    private boolean isLiveEffectVideo(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_VIEWER) || !mediaItem.isLiveEffect() || !mediaItem.isVideo()) {
            return false;
        }
        return true;
    }

    private boolean isSingleTakenShot(MediaItem mediaItem) {
        return mediaItem.isSingleTakenShot();
    }

    public void addFilmStripItemClickListener(OnFilmStripItemClickListener onFilmStripItemClickListener) {
        if (!this.mOnItemClickListenerList.contains(onFilmStripItemClickListener)) {
            this.mOnItemClickListenerList.add(onFilmStripItemClickListener);
        }
    }

    public void clearCache() {
        this.mCache.evictAll();
        this.mCount = -1;
    }

    public int getItemCount() {
        if (this.mCount == -1) {
            this.mCount = this.mMediaData.getCount();
        }
        return this.mCount;
    }

    public long getItemId(int i2) {
        MediaItem mediaItem;
        if (i2 < 0 || (mediaItem = getMediaItem(i2)) == null) {
            return super.getItemId(i2);
        }
        return (long) ((mediaItem.isPostProcessing() ? 1 : 0) + mediaItem.getComplexHashCode());
    }

    public int getItemViewType(int i2) {
        MediaItem mediaItem = getMediaItem(i2);
        if (mediaItem == null) {
            return 1;
        }
        if (mediaItem.isLocal() || (mediaItem.isCloudOnly() && MediaItemUtil.supportCloudPreviewPlay(mediaItem))) {
            if (mediaItem.isMotionPhoto()) {
                return 2;
            }
            if (isLiveEffectVideo(mediaItem)) {
                return 21;
            }
        }
        if (mediaItem.isVideo() && !isSingleTakenShot(mediaItem)) {
            return 20;
        }
        if (PocFeatures.isEnabled(PocFeatures.BurstShotSeeker) && (mediaItem.isBurstShot() || mediaItem.isSimilarShot())) {
            return 3;
        }
        if (mediaItem.getMediaType() == MediaType.UnlockScreen) {
            return 100;
        }
        return 1;
    }

    public MediaData getMediaData() {
        return this.mMediaData;
    }

    public MediaItem getMediaItem(int i2) {
        MediaItem mediaItem = this.mCache.get(Integer.valueOf(i2));
        if (mediaItem == null && (mediaItem = this.mMediaData.read(i2)) != null) {
            this.mCache.put(Integer.valueOf(i2), mediaItem);
        }
        return mediaItem;
    }

    public void onDestroy() {
        this.mOnItemClickListenerList.clear();
        clearCache();
    }

    public void removeFilmStripItemClickListener(OnFilmStripItemClickListener onFilmStripItemClickListener) {
        this.mOnItemClickListenerList.remove(onFilmStripItemClickListener);
    }

    public void setFilmStripData(MediaData mediaData, boolean z) {
        if (this.mMediaData != mediaData) {
            this.mMediaData = mediaData;
            clearCache();
            Log.d("FilmAdapter", "setFilmStripData : " + mediaData.getDataVersion() + ", c=" + getItemCount());
        }
        if (z) {
            notifyDataSetChanged();
        }
    }

    public void onBindViewHolder(FilmStripViewHolder filmStripViewHolder, int i2) {
        filmStripViewHolder.itemView.setContentDescription(String.format(AppResources.getString(R$string.film_strip), new Object[]{Integer.valueOf(i2 + 1), Integer.valueOf(getItemCount())}));
        filmStripViewHolder.itemView.setAccessibilityDelegate(new ItemViewAccessibilityDelegate(filmStripViewHolder.itemView.getContext(), i2, getItemCount()));
        filmStripViewHolder.bindView(getMediaItem(i2), i2);
        filmStripViewHolder.addViewHolderListener(this.mOnItemClickListener);
    }

    public FilmStripViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 != 2) {
            if (i2 == 3) {
                return new FilmStripGroupViewHolder(Filmstrip3GroupImageItemLayoutBinding.inflate(this.mLayoutInflater, viewGroup, false));
            }
            if (i2 == 20) {
                return new FilmStripVideoViewHolder(Filmstrip3VideoItemLayoutBinding.inflate(this.mLayoutInflater, viewGroup, false));
            }
            if (i2 == 21) {
                return new FilmStripLiveEffectVideoViewHolder(Filmstrip3VideoItemLayoutBinding.inflate(this.mLayoutInflater, viewGroup, false));
            }
            if (i2 == 100) {
                return new FilmStripUnlockScreenViewHolder(Filmstrip3ImageItemLayoutBinding.inflate(this.mLayoutInflater, viewGroup, false));
            }
        } else if (PreferenceFeatures.OneUi40.MOTION_PHOTO_PLAYER) {
            return new FilmStripMotionPhotoViewHolder(Filmstrip3MotionPhotoItemLayoutBinding.inflate(this.mLayoutInflater, viewGroup, false));
        }
        return new FilmStripImageViewHolder(Filmstrip3ImageItemLayoutBinding.inflate(this.mLayoutInflater, viewGroup, false));
    }

    public void onViewAttachedToWindow(FilmStripViewHolder filmStripViewHolder) {
        filmStripViewHolder.onViewAttached();
    }

    public void onViewDetachedFromWindow(FilmStripViewHolder filmStripViewHolder) {
        filmStripViewHolder.onViewDetached();
    }

    public void onViewRecycled(FilmStripViewHolder filmStripViewHolder) {
        filmStripViewHolder.onViewRecycled();
    }
}
