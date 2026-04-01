package com.samsung.android.gallery.app.ui.list.suggestions.cleanout;

import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DuplicateItemViewHolder extends PreviewViewHolder {
    TextView mAlbumName;
    TextView mFileSize;
    float mRoundRadius;

    public DuplicateItemViewHolder(View view, int i2) {
        super(view, i2);
        this.mRoundRadius = view.getResources().getDimension(R.dimen.suggestions_item_round_radius);
    }

    private void updateAlbumName(MediaItem mediaItem) {
        String directoryFromPath = FileUtils.getDirectoryFromPath(mediaItem.getReferencePath(), false);
        this.mAlbumName.setText(AlbumTitleHelper.getAlbumTitle(FileUtils.getBucketId(directoryFromPath), FileUtils.getNameFromPath(directoryFromPath)));
        this.mFileSize.setText(StringCompat.toReadableSize((double) mediaItem.getFileSize()));
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        updateAlbumName(mediaItem);
        setThumbnailShape(1, this.mRoundRadius);
        addThumbnailBorder(getContext().getDrawable(R.drawable.sharings_thumbnail_border));
        ViewUtils.setViewShape(this.mDecoViewLayout, 1, this.mRoundRadius);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mAlbumName = (TextView) view.findViewById(R.id.album_name);
        this.mFileSize = (TextView) view.findViewById(R.id.file_size);
    }
}
