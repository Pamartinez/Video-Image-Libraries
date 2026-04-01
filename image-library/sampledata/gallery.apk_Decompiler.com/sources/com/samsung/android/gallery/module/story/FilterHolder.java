package com.samsung.android.gallery.module.story;

import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.support.utils.Features;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FilterHolder {
    private static final ArrayList<LinkedHashMap<EffectTheme, Filter>> sFilterGroup;
    private static final LinkedHashMap<EffectTheme, Filter> sFilterMapV5;
    private static final LinkedHashMap<EffectTheme, Filter> sFilterMapV7;

    static {
        LinkedHashMap<EffectTheme, Filter> linkedHashMap = new LinkedHashMap<>();
        sFilterMapV5 = linkedHashMap;
        LinkedHashMap<EffectTheme, Filter> linkedHashMap2 = new LinkedHashMap<>();
        sFilterMapV7 = linkedHashMap2;
        ArrayList<LinkedHashMap<EffectTheme, Filter>> arrayList = new ArrayList<>();
        sFilterGroup = arrayList;
        arrayList.add(linkedHashMap);
        arrayList.add(linkedHashMap2);
        EffectTheme effectTheme = EffectTheme.Comic;
        linkedHashMap.put(effectTheme, Filter.Soft);
        EffectTheme effectTheme2 = EffectTheme.Happy;
        linkedHashMap.put(effectTheme2, Filter.Warm);
        EffectTheme effectTheme3 = EffectTheme.Lounge;
        linkedHashMap.put(effectTheme3, Filter.Blossom);
        EffectTheme effectTheme4 = EffectTheme.Relaxing;
        linkedHashMap.put(effectTheme4, Filter.Light);
        EffectTheme effectTheme5 = EffectTheme.Upbeat;
        linkedHashMap.put(effectTheme5, Filter.Cool);
        EffectTheme effectTheme6 = EffectTheme.Sentimental;
        linkedHashMap.put(effectTheme6, Filter.BW);
        EffectTheme effectTheme7 = EffectTheme.Mystery;
        linkedHashMap.put(effectTheme7, Filter.Grayscale);
        EffectTheme effectTheme8 = EffectTheme.Lovely;
        linkedHashMap.put(effectTheme8, Filter.KissMe);
        EffectTheme effectTheme9 = EffectTheme.Intense;
        linkedHashMap.put(effectTheme9, Filter.Frosty);
        EffectTheme effectTheme10 = EffectTheme.Dynamic;
        linkedHashMap.put(effectTheme10, Filter.Ivory);
        linkedHashMap2.put(effectTheme, Filter.Breeze);
        linkedHashMap2.put(effectTheme2, Filter.Sunbeam);
        linkedHashMap2.put(effectTheme3, Filter.Amber);
        linkedHashMap2.put(effectTheme4, Filter.Crystal);
        linkedHashMap2.put(effectTheme5, Filter.Chill);
        linkedHashMap2.put(effectTheme6, Filter.Shade);
        linkedHashMap2.put(effectTheme7, Filter.Shadow);
        linkedHashMap2.put(effectTheme8, Filter.Glow);
        linkedHashMap2.put(effectTheme9, Filter.Shiver);
        linkedHashMap2.put(effectTheme10, Filter.Pulse);
    }

    public static boolean belongInTheme(EffectTheme effectTheme, Filter filter) {
        if (filter.equals(sFilterMapV7.get(effectTheme)) || filter.equals(sFilterMapV5.get(effectTheme))) {
            return true;
        }
        return false;
    }

    public static Filter findCurrentSepFilter(String str) {
        Filter filter = Filter.get(str);
        if (filter == Filter.NONE) {
            return filter;
        }
        Iterator<LinkedHashMap<EffectTheme, Filter>> it = sFilterGroup.iterator();
        while (it.hasNext()) {
            LinkedHashMap next = it.next();
            ArrayList arrayList = new ArrayList(next.values());
            if (arrayList.contains(filter)) {
                return getFilterMap().get((EffectTheme) new ArrayList(next.keySet()).get(arrayList.indexOf(filter)));
            }
        }
        return filter;
    }

    public static Filter getFilter(EffectTheme effectTheme) {
        return getFilterMap().get(effectTheme);
    }

    private static LinkedHashMap<EffectTheme, Filter> getFilterMap() {
        if (Features.isEnabled(Features.SUPPORT_SEM_IMAGE_FILTER_V)) {
            return sFilterMapV7;
        }
        return sFilterMapV5;
    }

    public static List<Filter> getFilters() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Filter.NONE);
        arrayList.addAll(getFilterMap().values());
        return arrayList;
    }
}
