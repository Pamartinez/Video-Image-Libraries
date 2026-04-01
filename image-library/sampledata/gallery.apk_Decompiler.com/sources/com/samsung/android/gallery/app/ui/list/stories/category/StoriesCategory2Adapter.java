package com.samsung.android.gallery.app.ui.list.stories.category;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.category.IStoriesCategory2View;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.IStoriesCategoryView;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent;
import com.samsung.android.gallery.app.ui.list.stories.category.category.StoriesCatBaseViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.category.category.StoriesCatViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewAdapter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.imagetranslation.common.LttEngineErrors;
import com.sec.sshutter.SlowShutter;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoriesCategory2Adapter<V extends IStoriesCategory2View> extends StoriesPinchViewAdapter<V> {
    public StoriesCategory2Adapter(V v, String str, View view) {
        super(v, str, view);
    }

    private boolean hasChildData() {
        if (this.mMediaData.getChildCount() != 0) {
            return true;
        }
        return false;
    }

    public void attachHeaderViewToHolder(ListViewHolder listViewHolder) {
        super.attachHeaderViewToHolder(listViewHolder);
        ((IStoriesCategory2View) this.mView).internalEvent(InternalEvent.ON_HEADER_ATTACHED, new Object[0]);
    }

    public boolean checkIfEmpty() {
        if (!super.checkIfEmpty() || hasChildData()) {
            return false;
        }
        return true;
    }

    public int getItemCount() {
        return super.getItemCount();
    }

    public int getItemViewType(int i2) {
        String str;
        if (i2 <= 0) {
            return -3;
        }
        if (i2 >= getItemCount()) {
            return 0;
        }
        MediaItem read = this.mMediaData.read(i2 - 1);
        if (read != null) {
            str = read.getCategory();
        } else {
            str = null;
        }
        if (str == null) {
            return 0;
        }
        if ("divider".equals(str)) {
            return -2;
        }
        int categoryViewType = StoriesCatViewHolderFactory.getCategoryViewType(str);
        if (categoryViewType == -100) {
            return 0;
        }
        return categoryViewType;
    }

    public String getLocationKey(int i2) {
        switch (i2) {
            case LttEngineErrors.ERROR_INPAINTING_NOT_SUPPORTED /*-104*/:
                return "location://stories/category/RecapBookMarked";
            case LttEngineErrors.ERROR_INPAINTING_PROCESSING_FAILED /*-103*/:
                return "location://stories/category/creation";
            case LttEngineErrors.ERROR_INPAINTING_INITIALIZATION_FAILED /*-102*/:
                return "location://stories/category/trip";
            case LttEngineErrors.ERROR_INPAINTING_FAILED /*-101*/:
                return "location://stories/category/transitory";
            case SlowShutter.ERROR_UNKNOWN /*-100*/:
                return "location://stories/category/more";
            default:
                return null;
        }
    }

    public List<GalleryListView> getSelectableHeaderRecyclerListView() {
        return ((IStoriesCategory2View) this.mView).getHeader().getAllList();
    }

    public boolean requireBindHeader() {
        return true;
    }

    public boolean supportHeader() {
        return true;
    }

    public boolean supportVideoPreview() {
        return false;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (!list.contains("PAYLOAD_UPDATE_BADGE")) {
            int viewType = listViewHolder.getViewType();
            if (viewType <= -101) {
                String locationKey = getLocationKey(viewType);
                if (locationKey != null) {
                    ((StoriesCatBaseViewHolder) listViewHolder).bindData(this.mMediaData.getChildMediaData(locationKey), new Bundle());
                    IStoriesCategory2View iStoriesCategory2View = (IStoriesCategory2View) this.mView;
                    Objects.requireNonNull(iStoriesCategory2View);
                    listViewHolder.setOnItemClickListener(new b(iStoriesCategory2View));
                    return;
                }
                return;
            }
            super.onBindViewHolder(listViewHolder, i2, list);
        } else if (listViewHolder instanceof StoriesCatBaseViewHolder) {
            ((StoriesCatBaseViewHolder) listViewHolder).handleEvent(Event.UPDATE_BADGE, new Object[0]);
        }
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 == 0 || i2 == -2 || i2 == -3) {
            return super.onCreateViewHolder(viewGroup, i2);
        }
        return StoriesCatViewHolderFactory.create((IStoriesCategoryView) this.mView, viewGroup, i2);
    }

    public void onViewRecycled(ListViewHolder listViewHolder) {
        listViewHolder.recycle();
    }
}
