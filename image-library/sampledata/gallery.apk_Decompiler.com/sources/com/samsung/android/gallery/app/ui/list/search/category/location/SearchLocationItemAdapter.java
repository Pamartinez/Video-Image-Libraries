package com.samsung.android.gallery.app.ui.list.search.category.location;

import Ad.C0720a;
import Z8.c;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.list.search.ISearchView;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryGroupItemAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.sec.android.gallery3d.R;
import i.C0212a;
import ic.l;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchLocationItemAdapter extends CategoryGroupItemAdapter {
    private String mOthers;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CityPositionInfo extends C0212a {
        private final String city;
        private final int count;
        private final int position;

        public /* synthetic */ CityPositionInfo(String str, int i2, int i7, int i8) {
            this(str, i2, i7);
        }

        public String city() {
            return this.city;
        }

        public int count() {
            return this.count;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof CityPositionInfo)) {
                return false;
            }
            CityPositionInfo cityPositionInfo = (CityPositionInfo) obj;
            if (this.position == cityPositionInfo.position && this.count == cityPositionInfo.count && Objects.equals(this.city, cityPositionInfo.city)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            int i2 = this.position;
            int i7 = this.count;
            return Objects.hashCode(this.city) + (((i2 * 31) + i7) * 31);
        }

        public int position() {
            return this.position;
        }

        public final String toString() {
            String[] strArr;
            Object[] objArr = {this.city, Integer.valueOf(this.position), Integer.valueOf(this.count)};
            if ("city;position;count".length() == 0) {
                strArr = new String[0];
            } else {
                strArr = "city;position;count".split(";");
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(CityPositionInfo.class.getSimpleName());
            sb2.append("[");
            for (int i2 = 0; i2 < strArr.length; i2++) {
                sb2.append(strArr[i2]);
                sb2.append("=");
                sb2.append(objArr[i2]);
                if (i2 != strArr.length - 1) {
                    sb2.append(ArcCommonLog.TAG_COMMA);
                }
            }
            sb2.append("]");
            return sb2.toString();
        }

        private CityPositionInfo(String str, int i2, int i7) {
            this.city = str;
            this.position = i2;
            this.count = i7;
        }
    }

    public SearchLocationItemAdapter(ISearchView iSearchView, String str, GalleryListView galleryListView, CategoryPropertyHelper categoryPropertyHelper, boolean z) {
        super(iSearchView, str, galleryListView, categoryPropertyHelper, z);
    }

    private String getOthers() {
        if (this.mOthers == null) {
            this.mOthers = getContext().getString(R.string.others);
        }
        return this.mOthers;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ int lambda$sortCountry$4(Map.Entry entry, Map.Entry entry2) {
        if (TextUtils.equals(getOthers(), (CharSequence) entry.getKey())) {
            return 1;
        }
        if (TextUtils.equals(getOthers(), (CharSequence) entry2.getKey())) {
            return -1;
        }
        if (Objects.equals(entry2.getValue(), entry.getValue())) {
            return ((String) entry.getKey()).compareToIgnoreCase((String) entry2.getKey());
        }
        return ((Integer) entry2.getValue()).intValue() - ((Integer) entry.getValue()).intValue();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$sortPositionMap$2(CityPositionInfo cityPositionInfo, CityPositionInfo cityPositionInfo2) {
        if (Integer.valueOf(cityPositionInfo.count()).equals(Integer.valueOf(cityPositionInfo2.count()))) {
            return cityPositionInfo.city().compareToIgnoreCase(cityPositionInfo2.city());
        }
        return cityPositionInfo2.count() - cityPositionInfo.count();
    }

    private List<Map.Entry<String, Integer>> sortCountry(HashMap<String, Integer> hashMap) {
        LinkedList linkedList = new LinkedList(hashMap.entrySet());
        linkedList.sort(new c(5, this));
        return linkedList;
    }

    private void sortPositionMap(HashMap<String, ArrayList<CityPositionInfo>> hashMap) {
        if (GalleryPreference.getInstance().loadInt(PreferenceName.LOCATION_SORT_BY, 50) == 30) {
            hashMap.values().forEach(new l(24));
        } else {
            hashMap.values().forEach(new l(25));
        }
    }

    private int sum(Integer num, int i2) {
        if (num != null) {
            return num.intValue() + i2;
        }
        return i2;
    }

    private ArrayList<CityPositionInfo> updatePositionMap(ArrayList<CityPositionInfo> arrayList, String str, int i2, int i7) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        arrayList.add(new CityPositionInfo(str, i2, i7, 0));
        return arrayList;
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [java.lang.Object, java.util.function.Function] */
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
                    String str = DetailsData.of(read).countryInDB;
                    if (TextUtils.isEmpty(str) || str.equals("null")) {
                        str = getOthers();
                    }
                    hashMap.put(str, updatePositionMap((ArrayList) hashMap.get(str), read.getTitle(), i2, read.getCount()));
                    hashMap2.put(str, Integer.valueOf(sum((Integer) hashMap2.get(str), read.getCount())));
                    if (this.mIsClusterDisabled) {
                        if (read.getCount() < 2) {
                            z = true;
                        } else {
                            z = false;
                        }
                        this.mIsClusterDisabled = z;
                    }
                }
            }
            sortPositionMap(hashMap);
            List<Map.Entry<String, Integer>> sortCountry = sortCountry(hashMap2);
            this.mDividerIndex = this.mIsClusterDisabled ? new int[]{-1} : new int[sortCountry.size()];
            int i7 = 0;
            for (int i8 = 0; i8 < sortCountry.size(); i8++) {
                String str2 = (String) sortCountry.get(i8).getKey();
                ArrayList arrayList = (ArrayList) hashMap.get(str2);
                if (arrayList != null) {
                    updateViewPositions((ArrayList) arrayList.stream().map(new Object()).collect(Collectors.toCollection(new C0720a(1))));
                    if (!this.mIsClusterDisabled) {
                        this.mDividerIndex[i8] = i7;
                        this.mGroupItemList.add(str2);
                        i7 = arrayList.size() + 1 + i7;
                    }
                }
            }
        }
    }
}
