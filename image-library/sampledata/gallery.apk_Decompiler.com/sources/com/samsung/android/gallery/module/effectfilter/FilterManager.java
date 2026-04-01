package com.samsung.android.gallery.module.effectfilter;

import com.samsung.android.camera.filter.SemFilter;
import com.samsung.android.camera.filter.SemFilterManager;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilterManager {
    private static final boolean SUPPORT = PreferenceFeatures.isEnabled(PreferenceFeatures.StoriesFilter);
    private static final HashMap<Filter, String> mFilterPath;
    protected final HashMap<Filter, SemFilter> mFilterMatcher = new HashMap<>();
    protected List<SemFilter> mSemFilters = new ArrayList();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        /* access modifiers changed from: private */
        public static final FilterManager INSTANCE = new FilterManager();
    }

    static {
        String str;
        HashMap<Filter, String> hashMap = new HashMap<>();
        mFilterPath = hashMap;
        Filter filter = Filter.Light;
        if (Features.isEnabled(Features.USE_LOLLI_FILTER)) {
            str = "lolli.sel";
        } else {
            str = "light.sel";
        }
        hashMap.put(filter, str);
        hashMap.put(Filter.Blossom, "blossom.sel");
        hashMap.put(Filter.Warm, "vivid_warm.sel");
        hashMap.put(Filter.Soft, "softtone_warm_mini.sel");
        hashMap.put(Filter.Cool, "vivid_cool.sel");
        hashMap.put(Filter.BW, "c360wb.sel");
        hashMap.put(Filter.Grayscale, "greyscale.sel");
        hashMap.put(Filter.KissMe, "kissme.sel");
        hashMap.put(Filter.Frosty, "pale_jaehan.sel");
        hashMap.put(Filter.Ivory, "fc03.sel");
        hashMap.put(Filter.Breeze, "cinepea.sel");
        hashMap.put(Filter.Sunbeam, "sun_beam.sel");
        hashMap.put(Filter.Amber, "lomo400.sel");
        hashMap.put(Filter.Crystal, "crystal.sel");
        hashMap.put(Filter.Chill, "chill.sel");
        hashMap.put(Filter.Shade, "noir_mono_myfilter.sel");
        hashMap.put(Filter.Shadow, "shadow.sel");
        hashMap.put(Filter.Glow, "fleecia_film.sel");
        hashMap.put(Filter.Shiver, "excitea_film_1008.sel");
        hashMap.put(Filter.Pulse, "jazzchrome_film.sel");
    }

    public FilterManager() {
        if (SUPPORT) {
            long currentTimeMillis = System.currentTimeMillis();
            List<SemFilter> availableFilters = new SemFilterManager(AppResources.getAppContext()).getAvailableFilters(100);
            this.mSemFilters = availableFilters;
            this.mSemFilters = availableFilters == null ? new ArrayList<>() : availableFilters;
            fillMatcher();
            Log.d("FilterManager", "init", Integer.valueOf(this.mSemFilters.size()), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        }
    }

    public static FilterManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void fillMatcher() {
        for (SemFilter next : this.mSemFilters) {
            Filter filter = Filter.get(next.getFilterName());
            if (filter != null) {
                this.mFilterMatcher.put(filter, next);
            }
        }
    }

    public String getFilterRawPath(Filter filter) {
        if (filter == null || filter.noneFilter()) {
            return null;
        }
        return "/system/cameradata/preloadfilters/Sel/com.samsung.android.provider.filterprovider." + mFilterPath.get(filter);
    }

    public SemFilter getSemFilter(Filter filter) {
        return this.mFilterMatcher.get(filter);
    }

    public boolean isAvailable() {
        return !this.mSemFilters.isEmpty();
    }
}
