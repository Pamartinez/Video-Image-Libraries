package com.samsung.android.gallery.app.ui.list.albums.hide;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.viewholder.AlbumTitleCountSwitchHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FontUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AlbumsHideViewHolderFactory extends AlbumsViewHolderFactory {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class HideAlbumDividerViewHolder extends ListViewHolder {
        private TextView mTitle;

        public HideAlbumDividerViewHolder(View view, int i2) {
            super(view, i2);
        }

        public void bind(MediaItem mediaItem) {
            super.bind(mediaItem);
            ViewUtils.setText(this.mTitle, mediaItem.getTitle());
        }

        public void bindView(View view) {
            this.mTitle = (TextView) view.findViewById(R.id.title);
            FontUtils.resizeUpToLarge(view.getContext(), this.mTitle, (float) view.getResources().getDimensionPixelSize(R.dimen.mx_albums_divider_title_text_size));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class HideAlbumHeaderViewHolder extends ListViewHolder {
        public /* synthetic */ HideAlbumHeaderViewHolder(View view, int i2, int i7) {
            this(view, i2);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$bindView$0(TextView textView) {
            String knoxContainerName = KnoxUtil.getInstance().getKnoxContainerName(KnoxUtil.MoveType.MOVE_TO_SECURE_FOLDER);
            if (TextUtils.isEmpty(knoxContainerName) || Features.isEnabled(Features.IS_KNOX_MODE)) {
                textView.setText(AppResources.getString(R.string.hide_album_description_in_secure_folder));
            } else {
                textView.setText(AppResources.getString(R.string.hide_album_description, knoxContainerName));
            }
        }

        /* JADX WARNING: type inference failed for: r1v1, types: [java.util.function.Consumer, java.lang.Object] */
        public void bindView(View view) {
            Optional.ofNullable((TextView) view.findViewById(R.id.album_header_text)).ifPresent(new Object());
        }

        private HideAlbumHeaderViewHolder(View view, int i2) {
            super(view, i2);
        }
    }

    public AlbumsHideViewHolderFactory(Context context) {
        super(context);
    }

    private ListViewHolder createDividerViewHolder(ViewGroup viewGroup, int i2) {
        return new HideAlbumDividerViewHolder(this.mLayoutInflater.inflate(R.layout.recycler_item_hide_album_divider_layout, viewGroup, false), i2);
    }

    public ListViewHolder createHeaderViewHolder(ViewGroup viewGroup, int i2) {
        return new HideAlbumHeaderViewHolder(this.mLayoutInflater.inflate(this.mHeaderLayoutId, viewGroup, false), i2, 0);
    }

    public ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2) {
        AlbumTitleCountSwitchHolder albumTitleCountSwitchHolder = new AlbumTitleCountSwitchHolder(this.mLayoutInflater.inflate(this.mListLayoutId, viewGroup, false), i2);
        albumTitleCountSwitchHolder.setThumbnailShape(1, this.mListRoundRadius);
        return albumTitleCountSwitchHolder;
    }

    public ListViewHolder createViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 == -2) {
            return createDividerViewHolder(viewGroup, i2);
        }
        return super.createViewHolder(viewGroup, i2);
    }

    public int getHeaderLayoutId() {
        return R.layout.recycler_item_albums_hide_header_layout;
    }

    public int getListLayoutId() {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return R.layout.recycler_item_mx_albums_hide_image_layout;
        }
        return R.layout.recycler_item_albums_hide_image_layout;
    }
}
