package com.samsung.android.gallery.app.ui.list.pictures;

import A4.C0368c;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.viewholders.ConcatImageViewHolder;
import com.samsung.android.gallery.app.ui.viewholders.DateLocationViewHolder;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PicturesViewHolderFactory {
    protected int mDataLayoutId;
    protected final int mFirstTimelineLayoutId;
    protected final int mHeaderLayoutId;
    protected final LayoutInflater mLayoutInflater;
    protected final int mTimelineLayoutId;

    public PicturesViewHolderFactory(Context context) {
        this(LayoutInflater.from(context));
    }

    private ListViewHolder createFirstTimelineViewHolder(ViewGroup viewGroup, int i2) {
        return getDateLocationViewHolder(this.mLayoutInflater.inflate(this.mFirstTimelineLayoutId, viewGroup, false), i2);
    }

    private ListViewHolder createHeaderViewHolder(ViewGroup viewGroup, int i2) {
        return new HeaderViewHolder(this.mLayoutInflater.inflate(this.mHeaderLayoutId, viewGroup, false), i2);
    }

    private ListViewHolder createTimelineViewHolder(ViewGroup viewGroup, int i2) {
        return getDateLocationViewHolder(this.mLayoutInflater.inflate(this.mTimelineLayoutId, viewGroup, false), i2);
    }

    private int getSimpleLayoutId() {
        return R.layout.recycler_item_pictures_simple_image_layout;
    }

    public static void updateViewSize(ListViewHolder listViewHolder, int i2, Function<Integer, Integer> function, Function<Integer, Integer> function2) {
        int intValue = function2.apply(Integer.valueOf(i2)).intValue();
        if (listViewHolder.itemView.getWidth() > 0) {
            int intValue2 = function.apply(Integer.valueOf(i2)).intValue();
            ImageView image = listViewHolder.getImage();
            ViewGroup.LayoutParams layoutParams = image.getLayoutParams();
            if (!(layoutParams.width == -1 && layoutParams.height == -1)) {
                ViewGroup.LayoutParams layoutParams2 = image.getLayoutParams();
                layoutParams2.width = -1;
                layoutParams2.height = -1;
            }
            image.setVisibility(0);
            listViewHolder.itemView.measure(View.MeasureSpec.makeMeasureSpec(intValue2, 1073741824), View.MeasureSpec.makeMeasureSpec(intValue, 1073741824));
            ViewGroup.LayoutParams layoutParams3 = listViewHolder.itemView.getLayoutParams();
            layoutParams3.width = intValue2;
            layoutParams3.height = intValue;
            return;
        }
        ViewGroup.LayoutParams layoutParams4 = listViewHolder.itemView.getLayoutParams();
        if (layoutParams4 != null) {
            layoutParams4.height = intValue;
        } else {
            listViewHolder.itemView.post(new C0368c(listViewHolder, intValue, 27));
        }
    }

    public ListViewHolder createDataViewHolder(ViewGroup viewGroup, int i2, int i7) {
        View inflate = this.mLayoutInflater.inflate(getDataLayoutId(i2), viewGroup, false);
        if (i7 > 1 && viewGroup != null) {
            resizeDataView(viewGroup, inflate, i7);
        }
        return getDataViewHolder(inflate, i2);
    }

    public ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2, int i7) {
        if (i2 == 0) {
            return createDataViewHolder(viewGroup, i2, i7);
        }
        if (ViewHolderValue.isFirstDivider(i2)) {
            return createFirstTimelineViewHolder(viewGroup, i2);
        }
        if (ViewHolderValue.isDivider(i2)) {
            return createTimelineViewHolder(viewGroup, i2);
        }
        if (ViewHolderValue.isHeader(i2)) {
            return createHeaderViewHolder(viewGroup, i2);
        }
        return createDataViewHolder(viewGroup, i2, i7);
    }

    public int getDataLayoutId() {
        return R.layout.recycler_item_pictures_previewable_image_layout;
    }

    public ListViewHolder getDataViewHolder(View view, int i2) {
        if (useConcatThumbnail(i2)) {
            return new ConcatImageViewHolder(view, i2);
        }
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW) {
            return new PreviewViewHolder(view, i2);
        }
        return new ImageViewHolder(view, i2);
    }

    public ListViewHolder getDateLocationViewHolder(View view, int i2) {
        return new DateLocationViewHolder(view, i2);
    }

    public int getFirstTimelineLayoutId() {
        return R.layout.recycler_item_pictures_timeline_first_layout;
    }

    public int getHeaderContainerId() {
        return R.layout.recycler_item_empty_container_layout;
    }

    public int getMonthForViewingLayoutId() {
        return R.layout.recycler_item_pictures_month_for_viewing_image_layout;
    }

    public int getTimelineLayoutId() {
        return R.layout.recycler_item_pictures_timeline_body_layout;
    }

    public void resizeDataView(ViewGroup viewGroup, View view, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            int width = viewGroup.getWidth() / i2;
            layoutParams.width = width;
            layoutParams.height = width;
            view.setLayoutParams(layoutParams);
        }
    }

    public boolean useConcatThumbnail(int i2) {
        return ViewHolderValue.useConcatThumbnail(i2);
    }

    public PicturesViewHolderFactory(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
        this.mHeaderLayoutId = getHeaderContainerId();
        this.mFirstTimelineLayoutId = getFirstTimelineLayoutId();
        this.mTimelineLayoutId = getTimelineLayoutId();
        this.mDataLayoutId = getDataLayoutId();
    }

    public int getDataLayoutId(int i2) {
        if (useConcatThumbnail(i2)) {
            return getSimpleLayoutId();
        }
        return ViewHolderValue.isMonthForViewing(i2) ? getMonthForViewingLayoutId() : this.mDataLayoutId;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class HeaderViewHolder extends ListViewHolder {
        public HeaderViewHolder(View view, int i2) {
            super(view, i2);
        }

        public void recycle() {
            super.recycle();
            ViewUtils.removeAllViews((ViewGroup) this.itemView);
        }

        public void bindView(View view) {
        }
    }
}
