package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import Z8.c;
import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.search.ISearchView;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryGroupItemAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import t8.b;
import x5.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusterCategoryItemAdapter extends CategoryGroupItemAdapter {
    private HashMap<String, MediaItem> mDividerItemMap = new HashMap<>();

    public ClusterCategoryItemAdapter(ISearchView iSearchView, String str, GalleryListView galleryListView, CategoryPropertyHelper categoryPropertyHelper, boolean z) {
        super(iSearchView, str, galleryListView, categoryPropertyHelper, z);
    }

    private String getClusterString(String str) {
        return getContext().getResources().getString(ClusterResultType.of(str).titleResId);
    }

    private int getCount(MediaItem mediaItem) {
        return mediaItem.getCount();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getDividerMediaItem$2(String str, MediaItem mediaItem, int i2) {
        this.mDividerItemMap.put(str, mediaItem);
        notifyItemChanged(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getDividerMediaItem$3(MediaItem mediaItem, int i2, String str) {
        mediaItem.setTitle(TranslationManager.getInstance().getTranslatedString("Documents", this.mGroupItemList.get(getIndex(i2))));
        ThreadUtil.postOnUiThread(new a(this, str, mediaItem, i2));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$sortSubCategory$0(Map.Entry entry, Map.Entry entry2) {
        return ((Integer) entry2.getValue()).intValue() - ((Integer) entry.getValue()).intValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ int lambda$updatePositionList$1(Integer num, Integer num2) {
        MediaItem read = this.mMediaData.read(num.intValue());
        MediaItem read2 = this.mMediaData.read(num2.intValue());
        if (read == null || read2 == null) {
            return 0;
        }
        int i2 = -Integer.compare(getCount(read), getCount(read2));
        if (i2 != 0 || TextUtils.isEmpty(read.getTitle()) || TextUtils.isEmpty(read2.getTitle())) {
            return i2;
        }
        return read.getTitle().compareToIgnoreCase(read2.getTitle());
    }

    private List<Map.Entry<String, Integer>> sortSubCategory(HashMap<String, Integer> hashMap) {
        LinkedList linkedList = new LinkedList(hashMap.entrySet());
        linkedList.sort(new b(1));
        return linkedList;
    }

    private int sum(Integer num, int i2) {
        if (num != null) {
            return num.intValue() + i2;
        }
        return i2;
    }

    public void destroy() {
        super.destroy();
        this.mDividerItemMap.clear();
    }

    public MediaItem getDividerMediaItem(int i2) {
        String str = this.mGroupItemList.get(getIndex(i2));
        MediaItem mediaItem = this.mDividerItemMap.get(str);
        if (mediaItem != null) {
            return mediaItem;
        }
        MediaItem mediaItem2 = new MediaItem();
        SimpleThreadPool.getInstance().execute(new a(this, mediaItem2, i2, str));
        return mediaItem2;
    }

    public int getItemViewType(int i2) {
        if (!isDivider(i2)) {
            return 9;
        }
        if (i2 == 0) {
            return -1;
        }
        return -2;
    }

    public void updateCluster() {
        boolean z;
        if (this.mMediaData != null) {
            this.mIsClusterDisabled = true;
            this.mGroupItemList = new ArrayList<>();
            this.mViewPositions = new ArrayList<>();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            for (int i2 = 0; i2 < this.mMediaData.getCount(); i2++) {
                MediaItem read = this.mMediaData.read(i2);
                if (read != null) {
                    String clusterString = getClusterString((String) read.getExtra(ExtrasID.CLUSTER_TYPE));
                    hashMap.put(clusterString, updatePositionList((ArrayList) hashMap.get(clusterString), i2));
                    int count = getCount(read);
                    hashMap2.put(clusterString, Integer.valueOf(sum((Integer) hashMap2.get(clusterString), count)));
                    if (this.mIsClusterDisabled) {
                        if (count < 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        this.mIsClusterDisabled = z;
                    }
                }
            }
            List<Map.Entry<String, Integer>> sortSubCategory = sortSubCategory(hashMap2);
            this.mDividerIndex = this.mIsClusterDisabled ? new int[]{-1} : new int[sortSubCategory.size()];
            int i7 = 0;
            for (int i8 = 0; i8 < sortSubCategory.size(); i8++) {
                String str = (String) sortSubCategory.get(i8).getKey();
                ArrayList arrayList = (ArrayList) hashMap.get(str);
                if (arrayList != null) {
                    updateViewPositions(arrayList);
                    if (!this.mIsClusterDisabled) {
                        this.mDividerIndex[i8] = i7;
                        this.mGroupItemList.add(str);
                        i7 = arrayList.size() + 1 + i7;
                    }
                }
            }
        }
    }

    public ArrayList<Integer> updatePositionList(ArrayList<Integer> arrayList, int i2) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        arrayList.add(Integer.valueOf(i2));
        arrayList.sort(new c(9, this));
        return arrayList;
    }

    public ClusterCategoryItemViewHolderFactory createViewHolderFactory(Context context) {
        return new ClusterCategoryItemViewHolderFactory(context);
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return createViewHolderFactory(getContext()).createListViewHolder(viewGroup, i2);
    }
}
