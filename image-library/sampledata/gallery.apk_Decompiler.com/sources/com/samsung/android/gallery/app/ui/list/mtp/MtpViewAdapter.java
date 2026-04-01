package com.samsung.android.gallery.app.ui.list.mtp;

import V3.b;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MtpViewAdapter extends BaseListViewAdapter<IBaseListView> {
    private final Handler mHandler = new Handler();

    public MtpViewAdapter(IBaseListView iBaseListView, String str) {
        super(iBaseListView, str);
    }

    private int getItemViewLayoutId() {
        return R.layout.recycler_item_mtp_image_layout;
    }

    public void requestThumbnail(ListViewHolder listViewHolder, int i2) {
        this.mHandler.post(new b(12, listViewHolder));
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        float f;
        ImageTitleViewHolder imageTitleViewHolder = new ImageTitleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getItemViewLayoutId(), viewGroup, false), i2);
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            f = getContext().getResources().getDimension(R.dimen.album_view_corner_radius_list_blur);
        } else {
            f = getContext().getResources().getDimension(R.dimen.album_view_corner_radius_list);
        }
        imageTitleViewHolder.setThumbnailShape(1, f);
        return imageTitleViewHolder;
    }
}
