package com.samsung.android.gallery.widget.filmstrip3.stories;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.databinding.Filmstrip3ImageItemLayoutBinding;
import com.samsung.android.gallery.widget.databinding.Filmstrip3VideoItemLayoutBinding;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripAdapter;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripImageViewHolder;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripVideoViewHolder;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import java.util.List;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesFilmStripViewAdapter extends FilmStripAdapter {
    /* access modifiers changed from: private */
    public BiConsumer<Bitmap, BiConsumer<Bitmap, Filter>> mBitmapFilter;
    private boolean mGeneralSlideshow;
    private int mPositionOffset;

    public StoriesFilmStripViewAdapter(Context context, MediaData mediaData, BiConsumer<Bitmap, BiConsumer<Bitmap, Filter>> biConsumer) {
        super(context, mediaData);
        this.mBitmapFilter = biConsumer;
    }

    public int adjustPosition(int i2) {
        return (i2 + this.mPositionOffset) % getItemCount();
    }

    public long getItemId(int i2) {
        return super.getItemId(adjustPosition(i2));
    }

    public int getItemViewType(int i2) {
        boolean z;
        MediaItem mediaItem = getMediaItem(i2);
        if (this.mGeneralSlideshow) {
            z = StoryHelper.isGeneralSlideshowVideoFormat(mediaItem);
        } else {
            z = StoryHelper.isVideoItem(mediaItem);
        }
        if (z) {
            return 20;
        }
        return 1;
    }

    public MediaItem getMediaItem(int i2) {
        return super.getMediaItem(adjustPosition(i2));
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBitmapFilter = null;
    }

    public void setGeneralSlideshow(boolean z) {
        this.mGeneralSlideshow = z;
    }

    public FilmStripViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 == 20) {
            return new FilmStripVideoViewHolder(Filmstrip3VideoItemLayoutBinding.inflate(this.mLayoutInflater, viewGroup, false)) {
                public void setDefaultImage(ImageView imageView) {
                    if (StoriesFilmStripViewAdapter.this.mBitmapFilter != null) {
                        setDefaultImageWithFilter(imageView, StoriesFilmStripViewAdapter.this.mBitmapFilter);
                    } else {
                        super.setDefaultImage(imageView);
                    }
                }

                public boolean supportVideoIcon() {
                    return false;
                }
            };
        }
        return new FilmStripImageViewHolder(Filmstrip3ImageItemLayoutBinding.inflate(this.mLayoutInflater, viewGroup, false)) {
            public void setDefaultImage(ImageView imageView) {
                if (StoriesFilmStripViewAdapter.this.mBitmapFilter != null) {
                    setDefaultImageWithFilter(imageView, StoriesFilmStripViewAdapter.this.mBitmapFilter);
                } else {
                    super.setDefaultImage(imageView);
                }
            }
        };
    }

    public void onBindViewHolder(FilmStripViewHolder filmStripViewHolder, int i2) {
        super.onBindViewHolder(filmStripViewHolder, adjustPosition(i2));
    }

    public void onBindViewHolder(FilmStripViewHolder filmStripViewHolder, int i2, List<Object> list) {
        if (list.contains("PAYLOAD_FILTER")) {
            filmStripViewHolder.setDefaultImageWithFilter((ImageView) filmStripViewHolder.itemView.findViewById(R$id.film_strip_view_image), this.mBitmapFilter);
        } else {
            super.onBindViewHolder(filmStripViewHolder, i2, list);
        }
    }
}
