package com.samsung.android.gallery.app.ui.list.sharings.pinch;

import A.a;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.IAlbumBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.sharings.ISharingsView;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsViewAdapter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingPinchViewAdapter<V extends ISharingsView> extends SharingsViewAdapter<V> implements IAlbumBaseViewAdapter {
    private int mAlbumBorderThickness;
    private ArrayList<Integer> mAlbumExpiryMargin;
    private ArrayList<Integer> mAlbumTypeMarginBottom;
    private ArrayList<Integer> mAlbumTypeMarginEnd;
    private ArrayList<Integer> mAlbumTypeMarginStart;
    private int mBorderColor;
    private ArrayList<Float> mBorderRadius;
    private ArrayList<Integer> mEmptyAlbumViewSize;
    private final HashMap<String, Drawable> mSharingBorderDrawable = new HashMap<>();
    private final SharingPinchViewHolderFactory mViewHolderFactory = new SharingPinchViewHolderFactory(getContext());

    public SharingPinchViewAdapter(V v, String str) {
        super(v, str);
        initDimens();
    }

    private Drawable getBorderDrawable(float f, int i2, boolean z) {
        String str = f + "/" + i2 + "/" + z;
        Drawable drawable = this.mSharingBorderDrawable.get(str);
        if (drawable == null) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(f);
            gradientDrawable.setStroke(i2, this.mBorderColor);
            this.mSharingBorderDrawable.put(str, gradientDrawable);
        }
        return drawable;
    }

    private int getInvitationsHeight(int i2) {
        int i7 = 0;
        for (int i8 = 0; i8 < i2; i8++) {
            i7 += getItemHeight(i8);
        }
        return i7;
    }

    private int getSpacesHeight(int i2) {
        int i7;
        int gridSize = getGridSize();
        int itemCount = getItemCount() - i2;
        int i8 = itemCount / gridSize;
        if (itemCount % gridSize > 0) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        return getItemHeight(i2) * (i8 + i7);
    }

    private boolean isEmptyAlbum(MediaItem mediaItem) {
        if (mediaItem == null || !TextUtils.isEmpty(mediaItem.getPath())) {
            return false;
        }
        return true;
    }

    private void updateBottomStartAlbumTypeMargin(ListViewHolder listViewHolder, int i2, boolean z) {
        View decoView = listViewHolder.getDecoView(21);
        if (decoView == null) {
            return;
        }
        if (z || listViewHolder.hasAlbumTypeIcon()) {
            ((ViewGroup.MarginLayoutParams) decoView.getLayoutParams()).semSetMarginsRelative(((Integer) a.c(1, i2, this.mAlbumTypeMarginStart)).intValue(), 0, 0, ((Integer) a.c(1, i2, this.mAlbumTypeMarginBottom)).intValue());
            ViewUtils.setVisibleOrGone(decoView, true);
        }
    }

    private void updateEndAlbumExpiryMargin(ListViewHolder listViewHolder, int i2, boolean z) {
        View decoView = listViewHolder.getDecoView(45);
        if (decoView != null) {
            boolean z3 = true;
            ((ViewGroup.MarginLayoutParams) decoView.getLayoutParams()).semSetMarginsRelative(0, 0, ((Integer) a.c(1, i2, this.mAlbumTypeMarginEnd)).intValue(), 0);
            if (!z && MediaItemMde.getAlbumExpiry(listViewHolder.getMediaItem()) == 0) {
                z3 = false;
            }
            ViewUtils.setVisibleOrGone(decoView, z3);
        }
    }

    private void updateEndAlbumTypeMargin(ListViewHolder listViewHolder, int i2, boolean z) {
        View decoView = listViewHolder.getDecoView(21);
        if (decoView == null) {
            return;
        }
        if (z || listViewHolder.hasAlbumTypeIcon()) {
            ((ViewGroup.MarginLayoutParams) decoView.getLayoutParams()).semSetMarginsRelative(0, 0, ((Integer) a.c(1, i2, this.mAlbumTypeMarginEnd)).intValue(), 0);
            ViewUtils.setVisibleOrGone(decoView, true);
        }
    }

    private void updateTopEndAlbumExpiryMargin(ListViewHolder listViewHolder, int i2, boolean z) {
        View decoView = listViewHolder.getDecoView(45);
        if (decoView != null) {
            boolean z3 = true;
            Integer num = (Integer) a.c(1, i2, this.mAlbumExpiryMargin);
            ((ViewGroup.MarginLayoutParams) decoView.getLayoutParams()).semSetMarginsRelative(0, num.intValue(), num.intValue(), 0);
            if (!z && MediaItemMde.getAlbumExpiry(listViewHolder.getMediaItem()) == 0) {
                z3 = false;
            }
            ViewUtils.setVisibleOrGone(decoView, z3);
        }
    }

    public /* bridge */ /* synthetic */ ListViewHolder createViewHolder(ViewGroup viewGroup, int i2) {
        return (ListViewHolder) createViewHolder(viewGroup, i2);
    }

    public int getHintColumnSpan(int i2, int i7) {
        if (isInvitation(i2)) {
            return 0;
        }
        return (i2 - getInvitationDataCount()) % i7;
    }

    public int getHintItemViewType(int i2, int i7) {
        if (!PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS || !isInvitation(i2)) {
            if (i7 == 1) {
                return 1;
            }
            return 2;
        } else if (isLastInvitation(i2)) {
            return -1;
        } else {
            return -2;
        }
    }

    public int getItemViewType(int i2) {
        return getHintItemViewType(i2, getGridSize());
    }

    public int getMaxScroll() {
        int invitationDataCount = getInvitationDataCount();
        return getInvitationsHeight(invitationDataCount) + getSpacesHeight(invitationDataCount);
    }

    public void initDimens() {
        ArrayList<Float> arrayList = new ArrayList<>();
        this.mBorderRadius = arrayList;
        arrayList.add(Float.valueOf(getContext().getResources().getDimension(R.dimen.sharing_list_radius)));
        this.mBorderRadius.add(Float.valueOf(getContext().getResources().getDimension(R.dimen.sharing_grid_radius)));
        this.mBorderRadius.add(Float.valueOf(getContext().getResources().getDimension(R.dimen.sharing_grid_radius)));
        this.mAlbumBorderThickness = getContext().getResources().getDimensionPixelOffset(R.dimen.sharing_view_border_thickness);
        this.mBorderColor = getContext().getColor(R.color.album_border_color);
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        this.mAlbumExpiryMargin = arrayList2;
        arrayList2.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.sharing_view_grid_expiry_icon_margin_3x)));
        this.mAlbumExpiryMargin.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.sharing_view_grid_expiry_icon_margin_3x)));
        this.mAlbumExpiryMargin.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.sharing_view_grid_expiry_icon_margin_2x)));
        ArrayList<Integer> arrayList3 = new ArrayList<>();
        this.mAlbumTypeMarginStart = arrayList3;
        arrayList3.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.sharing_view_album_type_icon_margin_start_list)));
        this.mAlbumTypeMarginStart.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.sharing_view_album_type_icon_margin_start_grid)));
        this.mAlbumTypeMarginStart.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.sharing_view_album_type_icon_margin_start_grid)));
        ArrayList<Integer> arrayList4 = new ArrayList<>();
        this.mAlbumTypeMarginEnd = arrayList4;
        arrayList4.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.album_type_margin_start_list_mx)));
        this.mAlbumTypeMarginEnd.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.album_type_margin_start_list_mx)));
        this.mAlbumTypeMarginEnd.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.album_type_margin_start_list_mx)));
        ArrayList<Integer> arrayList5 = new ArrayList<>();
        this.mAlbumTypeMarginBottom = arrayList5;
        arrayList5.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.sharing_view_album_type_icon_margin_bottom_list)));
        this.mAlbumTypeMarginBottom.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.sharing_view_album_type_icon_margin_bottom_grid)));
        this.mAlbumTypeMarginBottom.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.sharing_view_album_type_icon_margin_bottom_grid)));
        ArrayList<Integer> arrayList6 = new ArrayList<>();
        this.mEmptyAlbumViewSize = arrayList6;
        arrayList6.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.album_empty_view_size_list)));
        this.mEmptyAlbumViewSize.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.album_empty_view_size_grid)));
        this.mEmptyAlbumViewSize.add(Integer.valueOf(getContext().getResources().getDimensionPixelSize(R.dimen.album_empty_view_size_grid_max)));
    }

    public boolean isInvitationViewType(int i2) {
        return ViewHolderValue.isDivider(i2);
    }

    public void setGroupCount(ListViewHolder listViewHolder, int i2) {
        ViewUtils.setText((TextView) listViewHolder.getDecoView(46), MdeGroupHelper.getCountString(getContext(), listViewHolder.getMediaItem(), i2));
    }

    public void updateAlbumBlurInfo(ListViewHolder listViewHolder, int i2, int i7) {
        listViewHolder.updateBlurInfo(i2, i7);
    }

    public void updateAlbumTypeMargin(ListViewHolder listViewHolder, int i2, boolean z) {
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            updateEndAlbumExpiryMargin(listViewHolder, i2, z);
            updateEndAlbumTypeMargin(listViewHolder, i2, z);
            return;
        }
        updateTopEndAlbumExpiryMargin(listViewHolder, i2, z);
        updateBottomStartAlbumTypeMargin(listViewHolder, i2, z);
    }

    public void updateBorders(ListViewHolder listViewHolder, int i2) {
        listViewHolder.setThumbnailShape(1, this.mBorderRadius.get(i2).floatValue());
        if (getAdvancedMouseFocusManager() == null || !getAdvancedMouseFocusManager().containsViewPosition(listViewHolder.getViewPosition())) {
            listViewHolder.addThumbnailBorder(getBorderDrawable(this.mBorderRadius.get(i2).floatValue(), this.mAlbumBorderThickness, listViewHolder.isFolder()));
        } else {
            listViewHolder.setFocusedFilterOnAdvancedMouseEvent(true);
        }
    }

    public void updateEmptyAlbumViewSize(ListViewHolder listViewHolder, int i2, boolean z) {
        MediaItem mediaItem = listViewHolder.getMediaItem();
        View decoView = listViewHolder.getDecoView(61);
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR || decoView == null || (!z && !isEmptyAlbum(mediaItem))) {
            ViewUtils.setVisibleOrGone(decoView, false);
            return;
        }
        Integer num = (Integer) a.c(1, i2, this.mEmptyAlbumViewSize);
        ViewUtils.setViewSize(decoView, num, num);
        ViewUtils.setVisibleOrGone(decoView, true);
    }

    public int getItemViewType(int i2, int i7) {
        return getHintItemViewType(i2, i7);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        if (!isInvitationViewType(listViewHolder.getViewType())) {
            updateBorders(listViewHolder, this.mGalleryListView.getDepth());
            updateAlbumTypeMargin(listViewHolder, this.mGalleryListView.getDepth(), false);
            updateAlbumBlurInfo(listViewHolder, this.mGalleryListView.getDepth(), this.mGalleryListView.getMaxDepth());
            updateEmptyAlbumViewSize(listViewHolder, this.mGalleryListView.getDepth(), false);
        }
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return this.mViewHolderFactory.createViewHolder(viewGroup, i2);
    }

    public void updateInvitationBottomMargin(ListViewHolder listViewHolder, int i2) {
    }
}
