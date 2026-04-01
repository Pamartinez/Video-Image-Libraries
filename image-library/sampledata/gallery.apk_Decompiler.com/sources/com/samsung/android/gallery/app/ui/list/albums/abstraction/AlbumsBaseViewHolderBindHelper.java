package com.samsung.android.gallery.app.ui.list.albums.abstraction;

import A.a;
import A4.C0375j;
import A4.O;
import C4.o;
import C4.p;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.data.CloudData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsBaseViewHolderBindHelper {
    private final HashMap<String, Drawable> mAlbumBorderDrawable = new HashMap<>();
    private int mAlbumBorderThickness;
    private ArrayList<Integer> mAlbumSyncMarginHorizontal;
    private ArrayList<Integer> mAlbumSyncMarginVertical;
    private ArrayList<Integer> mAlbumTypeMarginHorizontal;
    private ArrayList<Integer> mAlbumTypeMarginVertical;
    private int mBorderColor;
    private ArrayList<Float> mBorderRadius;
    private ArrayList<Integer> mEmptyAlbumViewSize;
    private int mFolderBorderThickness;
    private int mFolderEmptyBorderColor;
    private int mFolderEmptyBorderThickness;
    private ArrayList<Integer> mFolderEmptyViewIconSize;
    private ArrayList<Integer> mFolderEmptyViewMargin;
    private ArrayList<Integer> mFolderEmptyViewPaddingBottom;
    private ArrayList<Integer> mFolderEmptyViewPaddingHorizontal;
    private ArrayList<Integer> mFolderEmptyViewPaddingTop;
    private ArrayList<Integer> mFolderEmptyViewRadius;
    private final FolderImageBindHelper mFolderImageBindHelper = new FolderImageBindHelper();
    private int mFolderImageBorderThickness;
    private ArrayList<Integer> mFolderImagePadding;
    private ArrayList<Float> mFolderImageRadius;
    private ArrayList<Integer> mFolderPadding;
    private final AdvancedMouseFocusManagerProvider mProvider;
    private boolean mSupportListView;
    private ArrayList<Integer> mTileContainerMargin;
    private int mTitleContainerDefaultMargin;
    private ArrayList<Integer> mVirtualAlbumItemMargin;
    private ArrayList<Integer> mVirtualAlbumPaddingBottom;
    private ArrayList<Integer> mVirtualAlbumPaddingHorizontal;
    private ArrayList<Integer> mVirtualAlbumPaddingTop;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AdvancedMouseFocusManagerProvider {
    }

    public AlbumsBaseViewHolderBindHelper(AdvancedMouseFocusManagerProvider advancedMouseFocusManagerProvider) {
        this.mProvider = advancedMouseFocusManagerProvider;
    }

    private boolean isAlbumSyncIconEnabled(ListViewHolder listViewHolder) {
        if (!Features.isEnabled(Features.SUPPORT_CLOUD_SYNC_STATUS) || !CloudData.isAlbumCloudSync(listViewHolder.getMediaItem())) {
            return false;
        }
        return true;
    }

    private boolean isEmptyAlbum(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        if (mediaItem.isEmptyAlbum()) {
            return true;
        }
        if (!mediaItem.isSharing()) {
            return false;
        }
        if (mediaItem.getCount() == 0 || TextUtils.isEmpty(mediaItem.getPath())) {
            return true;
        }
        return false;
    }

    private boolean isListView(int i2) {
        if (!this.mSupportListView || i2 != 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateTitleContainerMargin$3(boolean z, MediaItem mediaItem, int i2, View view) {
        if (z || (mediaItem != null && mediaItem.isFolder())) {
            ViewMarginUtils.setBottomMargin(view, ((Integer) a.c(1, i2, this.mTileContainerMargin)).intValue());
        } else {
            ViewMarginUtils.setBottomMargin(view, this.mTitleContainerDefaultMargin);
        }
    }

    private void prefetchBorderDrawables() {
        Iterator<Float> it = this.mBorderRadius.iterator();
        while (it.hasNext()) {
            float floatValue = it.next().floatValue();
            getBorderDrawable(floatValue, this.mAlbumBorderThickness, false);
            getBorderDrawable(floatValue, this.mFolderBorderThickness, true);
        }
        Iterator<Float> it2 = this.mFolderImageRadius.iterator();
        while (it2.hasNext()) {
            float floatValue2 = it2.next().floatValue();
            getBorderDrawable(floatValue2, this.mAlbumBorderThickness, false);
            getBorderDrawable(floatValue2, this.mFolderImageBorderThickness, false);
        }
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            Iterator<Integer> it3 = this.mFolderEmptyViewRadius.iterator();
            while (it3.hasNext()) {
                getBorderDrawable((float) it3.next().intValue(), this.mFolderEmptyBorderThickness, true, this.mFolderEmptyBorderColor);
            }
        }
    }

    private void updateBottomEndAlbumSyncMargin(View view, int i2) {
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).semSetMarginsRelative(0, 0, ((Integer) a.c(1, i2, this.mAlbumSyncMarginHorizontal)).intValue(), ((Integer) a.c(1, i2, this.mAlbumSyncMarginVertical)).intValue());
    }

    private void updateBottomStartAlbumSyncMargin(View view, int i2) {
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).semSetMarginsRelative(((Integer) a.c(1, i2, this.mAlbumSyncMarginHorizontal)).intValue(), 0, 0, ((Integer) a.c(1, i2, this.mAlbumSyncMarginVertical)).intValue());
    }

    private void updateBottomStartAlbumTypeMargin(View view, int i2) {
        SeApiCompat.setMarginsRelative(view, ((Integer) a.c(1, i2, this.mAlbumTypeMarginHorizontal)).intValue(), 0, 0, ((Integer) a.c(1, i2, this.mAlbumTypeMarginVertical)).intValue());
    }

    private void updateEndAlbumSyncMargin(View view, int i2) {
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).semSetMarginsRelative(0, 0, ((Integer) a.c(1, i2, this.mAlbumSyncMarginHorizontal)).intValue(), 0);
    }

    private void updateEndAlbumTypeMargin(View view, int i2) {
        SeApiCompat.setMarginsRelative(view, 0, 0, ((Integer) a.c(1, i2, this.mAlbumTypeMarginHorizontal)).intValue(), 0);
    }

    /* access modifiers changed from: private */
    /* renamed from: updateFolderEmptyViewIconSize */
    public void lambda$updateFolderViewSize$1(View view, int i2) {
        Integer num = (Integer) a.c(1, i2, this.mFolderEmptyViewIconSize);
        ViewUtils.setViewSize(view, num, num);
    }

    /* access modifiers changed from: private */
    /* renamed from: updateFolderEmptyViewPadding */
    public void lambda$updateFolderViewSize$0(View view, int i2) {
        Integer num = (Integer) a.c(1, i2, this.mFolderEmptyViewPaddingHorizontal);
        ViewMarginUtils.setPadding(view, num.intValue(), ((Integer) a.c(1, i2, this.mFolderEmptyViewPaddingTop)).intValue(), num.intValue(), ((Integer) a.c(1, i2, this.mFolderEmptyViewPaddingBottom)).intValue());
        Integer num2 = (Integer) a.c(1, i2, this.mFolderEmptyViewMargin);
        Integer num3 = (Integer) a.c(1, i2, this.mFolderEmptyViewRadius);
        View findViewById = view.findViewById(R.id.front_layer);
        ViewMarginUtils.setMargin(findViewById, 0, num2, num2, 0);
        ViewUtils.setViewShape(findViewById, 1, (float) num3.intValue());
        View findViewById2 = view.findViewById(R.id.back_layer);
        ViewMarginUtils.setMargin(findViewById2, num2, 0, 0, num2);
        ViewUtils.setViewShape(findViewById2, 1, (float) num3.intValue());
        ViewUtils.setForeground(findViewById2, getBorderDrawable((float) num3.intValue(), this.mFolderEmptyBorderThickness, true, this.mFolderEmptyBorderColor));
    }

    private void updateFolderViewSizeLegacy(ListViewHolder listViewHolder, int i2) {
        int intValue = this.mFolderPadding.get(i2).intValue();
        int intValue2 = this.mFolderImagePadding.get(i2).intValue();
        float floatValue = this.mFolderImageRadius.get(i2).floatValue();
        float floatValue2 = this.mBorderRadius.get(i2).floatValue();
        if (listViewHolder.getDecoView(23) != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) listViewHolder.getDecoView(23).getLayoutParams();
            marginLayoutParams.setMargins(intValue, intValue, intValue, intValue);
            listViewHolder.getDecoView(23).setLayoutParams(marginLayoutParams);
        }
        if (listViewHolder.getDecoView(24) != null) {
            if (((AlbumsBaseViewAdapter) ((O) this.mProvider).e).getAdvancedMouseFocusManager() == null || !((AlbumsBaseViewAdapter) ((O) this.mProvider).e).getAdvancedMouseFocusManager().containsViewPosition(listViewHolder.getViewPosition())) {
                listViewHolder.addThumbnailBorder(getBorderDrawable(floatValue2, this.mFolderBorderThickness, listViewHolder.isFolder()));
            } else {
                listViewHolder.setFocusedFilterOnAdvancedMouseEvent(true);
            }
        }
        if (listViewHolder.getFolderImageHolders() != null) {
            for (ListViewHolder listViewHolder2 : listViewHolder.getFolderImageHolders()) {
                if (listViewHolder2 != null) {
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) listViewHolder2.getRootView().getLayoutParams();
                    marginLayoutParams2.setMargins(intValue2, intValue2, intValue2, intValue2);
                    listViewHolder2.getRootView().setLayoutParams(marginLayoutParams2);
                    listViewHolder2.setThumbnailShape(1, floatValue);
                    updateGroupBorders(listViewHolder2, i2);
                }
            }
        }
    }

    private void updateGroupBorders(ListViewHolder listViewHolder, int i2) {
        int i7;
        if (listViewHolder.isFolder()) {
            i7 = this.mFolderImageBorderThickness;
        } else {
            i7 = this.mAlbumBorderThickness;
        }
        listViewHolder.addThumbnailBorder(getBorderDrawable(this.mFolderImageRadius.get(i2).floatValue(), i7, listViewHolder.isFolder()));
    }

    private void updateVirtualAlbumEmptyIconSize(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
        View decoView = listViewHolder.getDecoView(65);
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR || decoView == null || (!z && !MediaItemUtil.isVirtualEmptyAlbum(mediaItem))) {
            ViewUtils.setVisibleOrGone(decoView, false);
            return;
        }
        Integer num = (Integer) a.c(1, i2, this.mFolderEmptyViewIconSize);
        ViewUtils.setViewSize(decoView, num, num);
        ViewUtils.setVisibleOrGone(decoView, true);
    }

    public void bindNewAlbumLabel(ListViewHolder listViewHolder, int i2) {
        listViewHolder.setNewLabel(i2);
    }

    public Drawable getBorderDrawable(int i2, boolean z) {
        return getBorderDrawable(this.mBorderRadius.get(i2).floatValue(), z ? this.mFolderBorderThickness : this.mAlbumBorderThickness, z);
    }

    public void initResources(Context context, boolean z) {
        Resources resources = context.getResources();
        this.mSupportListView = z;
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.mFolderPadding = arrayList;
        if (z) {
            arrayList.add(Integer.valueOf(resources.getDimensionPixelOffset(R.dimen.folder_padding_list)));
        }
        this.mFolderPadding.add(Integer.valueOf(resources.getDimensionPixelOffset(R.dimen.folder_padding_grid)));
        this.mFolderPadding.add(Integer.valueOf(resources.getDimensionPixelOffset(R.dimen.folder_padding_grid_max)));
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        this.mFolderImagePadding = arrayList2;
        if (z) {
            arrayList2.add(Integer.valueOf(resources.getDimensionPixelOffset(R.dimen.folder_image_padding_list)));
        }
        this.mFolderImagePadding.add(Integer.valueOf(resources.getDimensionPixelOffset(R.dimen.folder_image_padding_grid)));
        this.mFolderImagePadding.add(Integer.valueOf(resources.getDimensionPixelOffset(R.dimen.folder_image_padding_grid_max)));
        ArrayList<Float> arrayList3 = new ArrayList<>();
        this.mFolderImageRadius = arrayList3;
        boolean z3 = PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR;
        if (z3) {
            if (z) {
                arrayList3.add(Float.valueOf(resources.getDimension(R.dimen.folder_image_radius_list_blur)));
            }
            this.mFolderImageRadius.add(Float.valueOf(resources.getDimension(R.dimen.folder_image_radius_grid_blur)));
            this.mFolderImageRadius.add(Float.valueOf(resources.getDimension(R.dimen.folder_image_radius_grid_max_blur)));
        } else {
            if (z) {
                arrayList3.add(Float.valueOf(resources.getDimension(R.dimen.folder_image_radius_list)));
            }
            this.mFolderImageRadius.add(Float.valueOf(resources.getDimension(R.dimen.folder_image_radius_grid)));
            this.mFolderImageRadius.add(Float.valueOf(resources.getDimension(R.dimen.folder_image_radius_grid_max)));
        }
        ArrayList<Float> arrayList4 = new ArrayList<>();
        this.mBorderRadius = arrayList4;
        if (z3) {
            if (z) {
                arrayList4.add(Float.valueOf(resources.getDimension(R.dimen.album_view_corner_radius_list_blur)));
            }
            this.mBorderRadius.add(Float.valueOf(resources.getDimension(R.dimen.album_view_corner_radius_grid_blur)));
            this.mBorderRadius.add(Float.valueOf(resources.getDimension(R.dimen.album_view_corner_radius_grid_max_blur)));
        } else {
            if (z) {
                arrayList4.add(Float.valueOf(resources.getDimension(R.dimen.album_view_corner_radius_list)));
            }
            this.mBorderRadius.add(Float.valueOf(resources.getDimension(R.dimen.album_view_corner_radius_grid)));
            this.mBorderRadius.add(Float.valueOf(resources.getDimension(R.dimen.album_view_corner_radius_grid_max)));
        }
        this.mAlbumBorderThickness = resources.getDimensionPixelOffset(R.dimen.album_view_border_thickness);
        this.mFolderBorderThickness = resources.getDimensionPixelOffset(R.dimen.folder_border_thickness);
        this.mFolderImageBorderThickness = resources.getDimensionPixelOffset(R.dimen.folder_image_border_thickness);
        this.mFolderEmptyBorderThickness = resources.getDimensionPixelOffset(R.dimen.album_view_sub_album_border_thickness);
        this.mBorderColor = context.getColor(R.color.album_border_color);
        this.mFolderEmptyBorderColor = context.getColor(R.color.album_view_sub_album_border_color);
        ArrayList<Integer> arrayList5 = new ArrayList<>();
        this.mAlbumTypeMarginHorizontal = arrayList5;
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            if (z) {
                a.m(resources, R.dimen.album_type_margin_start_list_mx, arrayList5);
            }
            a.m(resources, R.dimen.album_type_margin_start_grid_mx, this.mAlbumTypeMarginHorizontal);
            a.m(resources, R.dimen.album_type_margin_start_grid_max_mx, this.mAlbumTypeMarginHorizontal);
        } else {
            if (z) {
                a.m(resources, R.dimen.album_type_margin_start_list, arrayList5);
            }
            a.m(resources, R.dimen.album_type_margin_start_grid, this.mAlbumTypeMarginHorizontal);
            a.m(resources, R.dimen.album_type_margin_start_grid_max, this.mAlbumTypeMarginHorizontal);
        }
        ArrayList<Integer> arrayList6 = new ArrayList<>();
        this.mAlbumTypeMarginVertical = arrayList6;
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            if (z) {
                a.m(resources, R.dimen.album_type_margin_bottom_list_mx, arrayList6);
            }
            a.m(resources, R.dimen.album_type_margin_bottom_grid_mx, this.mAlbumTypeMarginVertical);
            a.m(resources, R.dimen.album_type_margin_bottom_grid_max_mx, this.mAlbumTypeMarginVertical);
        } else {
            if (z) {
                a.m(resources, R.dimen.album_type_margin_bottom_list, arrayList6);
            }
            a.m(resources, R.dimen.album_type_margin_bottom_grid, this.mAlbumTypeMarginVertical);
            a.m(resources, R.dimen.album_type_margin_bottom_grid_max, this.mAlbumTypeMarginVertical);
        }
        this.mAlbumSyncMarginHorizontal = new ArrayList<>(this.mAlbumTypeMarginHorizontal);
        this.mAlbumSyncMarginVertical = new ArrayList<>(this.mAlbumTypeMarginVertical);
        if (z3) {
            ArrayList<Integer> arrayList7 = new ArrayList<>();
            this.mFolderEmptyViewPaddingTop = arrayList7;
            if (z) {
                a.m(resources, R.dimen.folder_empty_view_padding_top_list, arrayList7);
            }
            a.m(resources, R.dimen.folder_empty_view_padding_top_grid, this.mFolderEmptyViewPaddingTop);
            this.mFolderEmptyViewPaddingTop.add(Integer.valueOf(resources.getDimensionPixelSize(R.dimen.folder_empty_view_padding_top_grid_max)));
            ArrayList<Integer> arrayList8 = new ArrayList<>();
            this.mFolderEmptyViewPaddingBottom = arrayList8;
            if (z) {
                a.m(resources, R.dimen.folder_empty_view_padding_bottom_list, arrayList8);
            }
            a.m(resources, R.dimen.folder_empty_view_padding_bottom_grid, this.mFolderEmptyViewPaddingBottom);
            this.mFolderEmptyViewPaddingBottom.add(Integer.valueOf(resources.getDimensionPixelSize(R.dimen.folder_empty_view_padding_bottom_grid_max)));
            ArrayList<Integer> arrayList9 = new ArrayList<>();
            this.mFolderEmptyViewPaddingHorizontal = arrayList9;
            if (z) {
                a.m(resources, R.dimen.folder_empty_view_padding_horizontal_list, arrayList9);
            }
            a.m(resources, R.dimen.folder_empty_view_padding_horizontal_grid, this.mFolderEmptyViewPaddingHorizontal);
            this.mFolderEmptyViewPaddingHorizontal.add(Integer.valueOf(resources.getDimensionPixelSize(R.dimen.folder_empty_view_padding_horizontal_grid_max)));
            ArrayList<Integer> arrayList10 = new ArrayList<>();
            this.mFolderEmptyViewMargin = arrayList10;
            if (z) {
                a.m(resources, R.dimen.folder_empty_view_margin_list, arrayList10);
            }
            a.m(resources, R.dimen.folder_empty_view_margin_grid, this.mFolderEmptyViewMargin);
            this.mFolderEmptyViewMargin.add(Integer.valueOf(resources.getDimensionPixelSize(R.dimen.folder_empty_view_margin_grid_max)));
            ArrayList<Integer> arrayList11 = new ArrayList<>();
            this.mFolderEmptyViewRadius = arrayList11;
            if (z) {
                a.m(resources, R.dimen.folder_empty_view_corner_radius_list, arrayList11);
            }
            a.m(resources, R.dimen.folder_empty_view_corner_radius_grid, this.mFolderEmptyViewRadius);
            this.mFolderEmptyViewRadius.add(Integer.valueOf(resources.getDimensionPixelSize(R.dimen.folder_empty_view_corner_radius_grid_max)));
            ArrayList<Integer> arrayList12 = new ArrayList<>();
            this.mFolderEmptyViewIconSize = arrayList12;
            if (z) {
                a.m(resources, R.dimen.folder_empty_view_icon_size_list, arrayList12);
            }
            a.m(resources, R.dimen.folder_empty_view_icon_size_grid, this.mFolderEmptyViewIconSize);
            this.mFolderEmptyViewIconSize.add(Integer.valueOf(resources.getDimensionPixelSize(R.dimen.folder_empty_view_icon_size_grid_max)));
            ArrayList<Integer> arrayList13 = new ArrayList<>();
            this.mEmptyAlbumViewSize = arrayList13;
            if (z) {
                a.m(resources, R.dimen.album_empty_view_size_list, arrayList13);
            }
            a.m(resources, R.dimen.album_empty_view_size_grid, this.mEmptyAlbumViewSize);
            this.mEmptyAlbumViewSize.add(Integer.valueOf(resources.getDimensionPixelSize(R.dimen.album_empty_view_size_grid_max)));
            ArrayList<Integer> arrayList14 = new ArrayList<>();
            this.mVirtualAlbumPaddingTop = arrayList14;
            if (z) {
                a.m(resources, R.dimen.album_virtual_layout_margin_top_list, arrayList14);
            }
            a.m(resources, R.dimen.album_virtual_layout_margin_top_grid, this.mVirtualAlbumPaddingTop);
            this.mVirtualAlbumPaddingTop.add(Integer.valueOf(resources.getDimensionPixelSize(R.dimen.album_virtual_layout_margin_top_grid_max)));
            ArrayList<Integer> arrayList15 = new ArrayList<>();
            this.mVirtualAlbumPaddingBottom = arrayList15;
            if (z) {
                a.m(resources, R.dimen.album_virtual_layout_margin_bottom_list, arrayList15);
            }
            a.m(resources, R.dimen.album_virtual_layout_margin_bottom_grid, this.mVirtualAlbumPaddingBottom);
            this.mVirtualAlbumPaddingBottom.add(Integer.valueOf(resources.getDimensionPixelSize(R.dimen.album_virtual_layout_margin_bottom_grid_max)));
            ArrayList<Integer> arrayList16 = new ArrayList<>();
            this.mVirtualAlbumPaddingHorizontal = arrayList16;
            if (z) {
                a.m(resources, R.dimen.album_virtual_layout_margin_horizontal_list, arrayList16);
            }
            a.m(resources, R.dimen.album_virtual_layout_margin_horizontal_grid, this.mVirtualAlbumPaddingHorizontal);
            this.mVirtualAlbumPaddingHorizontal.add(Integer.valueOf(resources.getDimensionPixelSize(R.dimen.album_virtual_layout_margin_horizontal_grid_max)));
            ArrayList<Integer> arrayList17 = new ArrayList<>();
            this.mVirtualAlbumItemMargin = arrayList17;
            if (z) {
                a.m(resources, R.dimen.album_virtual_layout_item_margin_list, arrayList17);
            }
            a.m(resources, R.dimen.album_virtual_layout_item_margin_grid, this.mVirtualAlbumItemMargin);
            this.mVirtualAlbumItemMargin.add(Integer.valueOf(resources.getDimensionPixelSize(R.dimen.album_virtual_layout_item_margin_grid_max)));
            ArrayList<Integer> arrayList18 = new ArrayList<>();
            this.mTileContainerMargin = arrayList18;
            if (z) {
                arrayList18.add(0);
            }
            a.m(resources, R.dimen.album_grid_title_container_bottom_margin, this.mTileContainerMargin);
            this.mTileContainerMargin.add(Integer.valueOf(resources.getDimensionPixelSize(R.dimen.album_grid_title_container_bottom_margin_max)));
            this.mTitleContainerDefaultMargin = resources.getDimensionPixelSize(R.dimen.album_grid_title_container_bottom_margin);
        }
        prefetchBorderDrawables();
    }

    public void updateAlbumSyncMargin(ListViewHolder listViewHolder, int i2, boolean z) {
        View decoView;
        boolean z3;
        if ((z || isAlbumSyncIconEnabled(listViewHolder)) && (decoView = listViewHolder.getDecoView(26)) != null) {
            if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
                    updateEndAlbumSyncMargin(decoView, i2);
                } else {
                    updateBottomStartAlbumSyncMargin(decoView, i2);
                }
            } else if (!isListView(i2)) {
                updateBottomEndAlbumSyncMargin(decoView, i2);
            }
            if (z || !listViewHolder.isFolder()) {
                z3 = true;
            } else {
                z3 = false;
            }
            ViewUtils.setVisibleOrGone(decoView, z3);
        }
    }

    public void updateAlbumTypeMargin(ListViewHolder listViewHolder, int i2, boolean z) {
        boolean z3;
        View decoView = listViewHolder.getDecoView(21);
        if (decoView == null) {
            return;
        }
        if (z || listViewHolder.hasAlbumTypeIcon()) {
            if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
                updateEndAlbumTypeMargin(decoView, i2);
            } else {
                updateBottomStartAlbumTypeMargin(decoView, i2);
            }
            if (z || !listViewHolder.isFolder()) {
                z3 = true;
            } else {
                z3 = false;
            }
            ViewUtils.setVisibleOrGone(decoView, z3);
        }
    }

    public void updateBorders(ListViewHolder listViewHolder, int i2) {
        listViewHolder.setThumbnailShape(1, this.mBorderRadius.get(i2).floatValue());
        if (((AlbumsBaseViewAdapter) ((O) this.mProvider).e).getAdvancedMouseFocusManager() == null || !((AlbumsBaseViewAdapter) ((O) this.mProvider).e).getAdvancedMouseFocusManager().containsViewPosition(listViewHolder.getViewPosition())) {
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

    public void updateFolderChildViewSize(ListViewHolder[] listViewHolderArr, int i2, int i7) {
        int i8;
        boolean z;
        if (listViewHolderArr != null && listViewHolderArr.length > 0) {
            float floatValue = this.mBorderRadius.get(i2).floatValue();
            if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
                i8 = 0;
            } else {
                i8 = this.mFolderImagePadding.get(i2).intValue();
            }
            for (int i10 = 0; i10 < listViewHolderArr.length; i10++) {
                ListViewHolder listViewHolder = listViewHolderArr[i10];
                if (listViewHolder != null) {
                    if (i10 < i7) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        this.mFolderImageBindHelper.updateRoundedCorner(listViewHolder.getImage(), i10, i7, floatValue);
                        this.mFolderImageBindHelper.updateMargin(listViewHolder.getImage(), i10, i7, i8 / 2);
                    }
                    ViewUtils.setVisibleOrGone(listViewHolder.getRootView(), z);
                }
            }
        }
    }

    public void updateFolderViewSize(ListViewHolder listViewHolder, int i2, boolean z) {
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (z || (mediaItem != null && mediaItem.isFolder())) {
            if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                updateFolderViewSize(listViewHolder, i2);
                if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
                    Optional.ofNullable(listViewHolder.getDecoView(62)).ifPresent(new p(this, i2, 0));
                    Optional.ofNullable(listViewHolder.getDecoView(63)).ifPresent(new p(this, i2, 1));
                }
            } else {
                updateFolderViewSizeLegacy(listViewHolder, i2);
            }
        }
        ViewUtils.setVisibleOrGone(listViewHolder.getImage(), mediaItem == null || !mediaItem.isFolder() || PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR);
    }

    public void updateTitleContainerMargin(ListViewHolder listViewHolder, int i2, boolean z) {
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            boolean z3 = z;
            Optional.ofNullable(listViewHolder.getDecoView(29)).ifPresent(new o(this, z3, listViewHolder.getMediaItem(), i2, 1));
        }
    }

    public void updateVirtualAlbumViewSize(ListViewHolder listViewHolder, int i2, boolean z) {
        MediaItem mediaItem = listViewHolder.getMediaItem();
        View decoView = listViewHolder.getDecoView(64);
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR || decoView == null || (!z && !MediaItemUtil.isVirtualAlbum(mediaItem))) {
            ViewUtils.setVisibleOrGone(decoView, false);
        } else {
            Integer num = (Integer) a.c(1, i2, this.mVirtualAlbumPaddingHorizontal);
            ViewMarginUtils.setPadding(decoView, num.intValue(), ((Integer) a.c(1, i2, this.mVirtualAlbumPaddingTop)).intValue(), num.intValue(), ((Integer) a.c(1, i2, this.mVirtualAlbumPaddingBottom)).intValue());
            if (i2 == 0) {
                Integer num2 = (Integer) a.c(1, i2, this.mVirtualAlbumItemMargin);
                ViewMarginUtils.setMargin(decoView.findViewById(R.id.first_layer), 0, num2, num2, 0);
                ViewMarginUtils.setMargin(decoView.findViewById(R.id.second_layer), num2, 0, 0, num2);
                ViewMarginUtils.setMargin(decoView.findViewById(R.id.third_layer), num2, 0, 0, num2);
            } else {
                Integer num3 = (Integer) a.c(1, i2, this.mVirtualAlbumItemMargin);
                ViewMarginUtils.setMargin(decoView.findViewById(R.id.first_layer), 0, Integer.valueOf(num3.intValue() * 2), Integer.valueOf(num3.intValue() * 2), 0);
                ViewMarginUtils.setMargin(decoView.findViewById(R.id.second_layer), num3, num3, num3, num3);
                ViewMarginUtils.setMargin(decoView.findViewById(R.id.third_layer), Integer.valueOf(num3.intValue() * 2), 0, 0, Integer.valueOf(num3.intValue() * 2));
            }
            ViewUtils.setVisibleOrGone(decoView, true);
        }
        updateVirtualAlbumEmptyIconSize(listViewHolder, mediaItem, i2, z);
    }

    public Drawable getBorderDrawable(float f, int i2, boolean z) {
        return getBorderDrawable(f, i2, z, this.mBorderColor);
    }

    public Drawable getBorderDrawable(float f, int i2, boolean z, int i7) {
        String str = f + "/" + i2 + "/" + z;
        Drawable drawable = this.mAlbumBorderDrawable.get(str);
        if (drawable == null) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(f);
            gradientDrawable.setStroke(i2, i7);
            this.mAlbumBorderDrawable.put(str, gradientDrawable);
        }
        return drawable;
    }

    private void updateFolderViewSize(ListViewHolder listViewHolder, int i2) {
        if (listViewHolder.getDecoView(24) != null) {
            if (((AlbumsBaseViewAdapter) ((O) this.mProvider).e).getAdvancedMouseFocusManager() == null || !((AlbumsBaseViewAdapter) ((O) this.mProvider).e).getAdvancedMouseFocusManager().containsViewPosition(listViewHolder.getViewPosition())) {
                listViewHolder.addThumbnailBorder(getBorderDrawable(this.mBorderRadius.get(i2).floatValue(), this.mAlbumBorderThickness, listViewHolder.isFolder()));
            } else {
                listViewHolder.setFocusedFilterOnAdvancedMouseEvent(true);
            }
        }
        if (listViewHolder.getFolderImageHolders() != null) {
            updateFolderChildViewSize(listViewHolder.getFolderImageHolders(), i2, (int) Arrays.stream(listViewHolder.getFolderImageHolders()).filter(new C0375j(8)).count());
        }
    }
}
