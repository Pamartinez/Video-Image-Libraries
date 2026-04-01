package com.samsung.android.gallery.app.ui.list.search.category.people;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemHeaderAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import com.samsung.android.gallery.app.ui.list.search.category.people.ICreatureCategoryView;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryPeopleItemViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import n5.C0493b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureCategoryItemAdapter<V extends ICreatureCategoryView> extends CategoryItemHeaderAdapter<V> {
    private int mFooterHeight;
    private boolean mIsFooterVisible;
    private int mMeTitleTextSize;
    private final AtomicBoolean mShowAllContents = new AtomicBoolean(GalleryPreference.getInstance().loadBoolean(PreferenceName.SEARCH_CREATURE_VIEW_ALL, false));
    private int mTitleTextSize;

    public CreatureCategoryItemAdapter(V v, String str, View view, GalleryListView galleryListView, CategoryPropertyHelper categoryPropertyHelper, boolean z) {
        super(v, str, view, galleryListView, categoryPropertyHelper, z);
        updateFooterVisible();
        initDimens();
    }

    private int getLimitContentsCount() {
        return getTopPeopleCount() + 15;
    }

    private int getRemoveIconGravity(int i2) {
        int i7;
        if (i2 - 1 <= getCreatureLayoutManager().getTopPeopleCount() / 2) {
            i7 = 8388611;
        } else {
            i7 = 8388613;
        }
        return i7 | 80;
    }

    private int getSelectableCount() {
        return getItemCount() - (hasFooter() ? 1 : 0);
    }

    private int getTopLayoutHeight() {
        return getCreatureLayoutManager().getTopLayoutHeight();
    }

    private int getTopPeopleCount() {
        return getCreatureLayoutManager().getTopPeopleCount();
    }

    private boolean hasTopPeople() {
        if (getTopPeopleCount() > 0) {
            return true;
        }
        return false;
    }

    private boolean isTop5Item(int i2) {
        if (i2 <= 1 || i2 > getCreatureLayoutManager().getTopPeopleCount()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(ListViewHolder listViewHolder, View view) {
        onFooterClicked(listViewHolder);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$1(ListViewHolder listViewHolder, View view) {
        onFooterClicked(listViewHolder);
    }

    private void onFooterClicked(ListViewHolder listViewHolder) {
        if (((ICreatureCategoryView) this.mView).isSelectionMode()) {
            return;
        }
        if (PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
            ((ICreatureCategoryView) this.mView).executeAddFaces();
            return;
        }
        AtomicBoolean atomicBoolean = this.mShowAllContents;
        atomicBoolean.set(!atomicBoolean.get());
        GalleryPreference.getInstance().saveState(PreferenceName.SEARCH_CREATURE_VIEW_ALL, this.mShowAllContents.get());
        updateFooterTitle(listViewHolder);
        notifyDataSetChanged();
    }

    private void updateFooterTitle(ListViewHolder listViewHolder) {
        listViewHolder.updateDecoration(4, Boolean.valueOf(this.mShowAllContents.get()));
    }

    private void updateFooterVisible() {
        boolean z = false;
        if (PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
            if (!PickerUtil.isPickerMode(this.mBlackBoard) && ((ICreatureCategoryView) this.mView).hasHiddenCreature() && getDataCount() > 0) {
                z = true;
            }
            this.mIsFooterVisible = z;
            return;
        }
        if (getDataCount() > getLimitContentsCount()) {
            z = true;
        }
        this.mIsFooterVisible = z;
        if (!z) {
            this.mShowAllContents.set(true);
        }
    }

    private void updateRemoveIcon(CategoryPeopleItemViewHolder categoryPeopleItemViewHolder, int i2) {
        if (!((ICreatureCategoryView) this.mView).isTop5EditMode() || !isTop5Item(i2)) {
            categoryPeopleItemViewHolder.setRemoveIconEnable(false, 0);
        } else {
            categoryPeopleItemViewHolder.setRemoveIconEnable(true, getRemoveIconGravity(i2));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0012, code lost:
        if (r4 == 1) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateTitleFont(com.samsung.android.gallery.widget.listviewholder.ListViewHolder r3, int r4) {
        /*
            r2 = this;
            boolean r0 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85
            if (r0 != 0) goto L_0x002c
            android.widget.TextView r3 = r3.getTitleView()
            if (r3 == 0) goto L_0x002c
            boolean r0 = r2.hasTopPeople()
            r1 = 0
            if (r0 == 0) goto L_0x0015
            r0 = 1
            if (r4 != r0) goto L_0x0015
            goto L_0x0016
        L_0x0015:
            r0 = r1
        L_0x0016:
            if (r0 == 0) goto L_0x001c
            int r2 = r2.mMeTitleTextSize
        L_0x001a:
            float r2 = (float) r2
            goto L_0x001f
        L_0x001c:
            int r2 = r2.mTitleTextSize
            goto L_0x001a
        L_0x001f:
            r3.setTextSize(r1, r2)
            if (r0 == 0) goto L_0x0027
            android.graphics.Typeface r2 = com.samsung.android.gallery.widget.utils.ResourceUtil.SEC_600
            goto L_0x0029
        L_0x0027:
            android.graphics.Typeface r2 = com.samsung.android.gallery.widget.utils.ResourceUtil.SEC_400
        L_0x0029:
            r3.setTypeface(r2)
        L_0x002c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryItemAdapter.updateTitleFont(com.samsung.android.gallery.widget.listviewholder.ListViewHolder, int):void");
    }

    public CategoryItemViewHolderFactory createViewHolderFactory(Context context) {
        return new CreatureItemViewHolderFactory(context);
    }

    public void editModeChanged() {
        notifyItemRangeChanged("updateRemoveIcon");
    }

    public ICreatureLayoutManager getCreatureLayoutManager() {
        return (ICreatureLayoutManager) ((ICreatureCategoryView) this.mView).getLayoutManager();
    }

    public long getDataId(MediaItem mediaItem) {
        return (long) mediaItem.getSubCategory().hashCode();
    }

    public List<Long> getDataIds() {
        ArrayList arrayList = new ArrayList();
        int selectableCount = getSelectableCount();
        for (int i2 = 0; i2 < selectableCount; i2++) {
            MediaItem mediaItemSync = getMediaItemSync(i2);
            if (mediaItemSync != null) {
                arrayList.add(Long.valueOf((long) mediaItemSync.getSubCategory().hashCode()));
            }
        }
        return arrayList;
    }

    public int getDataRowCount(int i2, int i7) {
        return super.getDataRowCount(i2, (i7 - getTopPeopleCount()) - ((this.mIsFooterVisible ? 1 : 0) + true));
    }

    public int getFirstDataPosition() {
        if (hasTopPeople()) {
            return getTopPeopleCount() + 1;
        }
        return 1;
    }

    public int getFooterViewHeight() {
        if (this.mIsFooterVisible) {
            return this.mFooterHeight;
        }
        return 0;
    }

    public int getItemCount() {
        int i2;
        boolean z = this.mIsFooterVisible;
        if (PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
            return super.getItemCount() + z;
        }
        if (this.mShowAllContents.get()) {
            i2 = super.getItemCount();
        } else {
            i2 = Math.min(getLimitContentsCount() + 1, super.getItemCount());
        }
        return i2 + (z ? 1 : 0);
    }

    public int getItemHeight(int i2) {
        if (isFooter(i2)) {
            return getFooterViewHeight();
        }
        int itemHeight = getCreatureLayoutManager().getItemHeight(i2);
        if (itemHeight == 0) {
            return super.getItemHeight(i2);
        }
        return itemHeight;
    }

    public int getItemViewType(int i2) {
        if (!this.mIsFooterVisible || i2 != getItemCount() - 1) {
            return super.getItemViewType(i2);
        }
        return -4;
    }

    public int getMaxScroll() {
        return getFooterViewHeight() + getTopLayoutHeight() + super.getMaxScroll();
    }

    public MediaItem getMediaItemFromCache(int i2) {
        if (isFooter(i2)) {
            return null;
        }
        return super.getMediaItemFromCache(i2);
    }

    public int getNextRowIndex(int i2, int i7) {
        if (!hasTopPeople() || i2 <= 0 || i2 > getTopPeopleCount()) {
            return super.getNextRowIndex(i2, i7);
        }
        return getTopPeopleCount() + 1;
    }

    public int getPrevRowIndex(int i2) {
        if (hasTopPeople()) {
            if (i2 > getTopPeopleCount()) {
                if (i2 < getGridSize() + getTopPeopleCount()) {
                    return 1;
                }
            }
            if (i2 <= getTopPeopleCount()) {
                return 0;
            }
        }
        if (isFooter(i2)) {
            return i2 - 1;
        }
        return super.getPrevRowIndex(i2);
    }

    public int getSpanSize(int i2) {
        if (isFooter(i2)) {
            return ((GridLayoutManager) ((ICreatureCategoryView) this.mView).getLayoutManager()).getSpanCount();
        }
        return super.getSpanSize(i2);
    }

    public int getStartSpan(int i2, int i7) {
        if (isFooter(i2)) {
            return 0;
        }
        return super.getStartSpan(i2, i7);
    }

    public boolean hasFooter() {
        return this.mIsFooterVisible;
    }

    public void initDimens() {
        this.mFooterHeight = getContext().getResources().getDimensionPixelOffset(R.dimen.search_category_creature_footer_bottom_margin) + getContext().getResources().getDimensionPixelOffset(R.dimen.search_category_creature_footer_height);
        this.mTitleTextSize = getContext().getResources().getDimensionPixelSize(R.dimen.search_category_people_item_title_size);
        this.mMeTitleTextSize = getContext().getResources().getDimensionPixelSize(R.dimen.search_category_me_item_title_size);
    }

    public boolean isDragSelectSupported() {
        return false;
    }

    public void onDataChanged() {
        updateFooterVisible();
        super.onDataChanged();
    }

    public void resetCheckBoxState(boolean z, ListViewHolder listViewHolder) {
        listViewHolder.setChecked(false);
    }

    public void setEnableAllContentsButton(boolean z) {
        ListViewHolder listViewHolder;
        if (this.mIsFooterVisible && (listViewHolder = (ListViewHolder) this.mGalleryListView.findViewHolderForAdapterPosition(getItemCount() - 1)) != null) {
            listViewHolder.setEnable(z);
        }
    }

    public void updateItemHeight(ListViewHolder listViewHolder) {
        if (ViewHolderValue.isData(listViewHolder.getViewType())) {
            super.lambda$onBindViewHolder$0(listViewHolder);
        }
    }

    public MediaItem getMediaItemFromCache(int i2, int i7) {
        if (isFooter(i2)) {
            return null;
        }
        return super.getMediaItemFromCache(i2, i7);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (!isData(i2) || !list.contains("updateRemoveIcon")) {
            super.onBindViewHolder(listViewHolder, i2, list);
        } else {
            updateRemoveIcon((CategoryPeopleItemViewHolder) listViewHolder, i2);
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        if (isData(i2)) {
            updateTitleFont(listViewHolder, i2);
            updateRemoveIcon((CategoryPeopleItemViewHolder) listViewHolder, i2);
        } else if (!isFooter(i2)) {
        } else {
            if (this.mIsFooterVisible) {
                listViewHolder.setEnable(!isSelectionMode());
                if (PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
                    ViewUtils.setOnClickListener(listViewHolder.itemView.findViewById(R.id.add_faces_button), new C0493b(this, listViewHolder, 0));
                    if (!Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                        ViewUtils.setText((TextView) listViewHolder.itemView.findViewById(R.id.add_faces_button), (int) R.string.add_people_button);
                        ViewUtils.setText((TextView) listViewHolder.itemView.findViewById(R.id.add_faces_description), (int) R.string.add_people_description);
                        return;
                    }
                    return;
                }
                updateFooterTitle(listViewHolder);
                ViewUtils.setOnClickListener(listViewHolder.itemView.findViewById(R.id.view_all_button), new C0493b(this, listViewHolder, 1));
                return;
            }
            ViewUtils.setVisibleOrGone(listViewHolder.getRootView(), false);
        }
    }

    public void restoreSelectionOnDataChanged(Runnable runnable, boolean z) {
    }
}
