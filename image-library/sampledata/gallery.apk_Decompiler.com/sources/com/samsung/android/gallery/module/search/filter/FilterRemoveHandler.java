package com.samsung.android.gallery.module.search.filter;

import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilterRemoveHandler {
    public String getSubCategory(FilterResultsEntity filterResultsEntity) {
        IdentityCreatureUtil.Category category;
        if (!filterResultsEntity.isCreature()) {
            return filterResultsEntity.getRawLabels();
        }
        long parseLong = Long.parseLong(filterResultsEntity.getRawLabels());
        if (filterResultsEntity.isPerson()) {
            category = IdentityCreatureUtil.Category.PEOPLE;
        } else {
            category = IdentityCreatureUtil.Category.PET;
        }
        return IdentityCreatureUtil.createWithUnifiedId(parseLong, category);
    }

    public String handleTarget(Blackboard blackboard, FilterRemoveParam filterRemoveParam) {
        UriBuilder uriBuilder;
        String str;
        FilterResultsEntity removedEntity = filterRemoveParam.getRemovedEntity();
        FilterResultsEntity newEntity = filterRemoveParam.getNewEntity();
        int position = filterRemoveParam.getPosition();
        String readLocationKeyCurrent = BlackboardUtils.readLocationKeyCurrent(blackboard);
        boolean z = false;
        if (!removedEntity.isPerson() && ArgumentsUtil.getArgValue(readLocationKeyCurrent, "people_only_them", false)) {
            z = true;
        }
        String argValue = ArgumentsUtil.getArgValue(readLocationKeyCurrent, "SelectedFilter", (String) null);
        String removeArg = ArgumentsUtil.removeArg(readLocationKeyCurrent, "SelectedFilter");
        if (position == 0) {
            Log.s("FilterRemoveHandler", "removeData : replaced main entity, " + newEntity.toString());
            uriBuilder = replaceMainKeywordTarget(ArgumentsUtil.removeArgs(removeArg), newEntity);
            blackboard.postEvent(EventMessage.obtain(1078, (Object) null));
            removedEntity = newEntity;
        } else {
            uriBuilder = new UriBuilder(removeArg);
        }
        uriBuilder.appendArg("SelectedFilter", SearchFilterUtil.updateSelectedFilter(argValue, removedEntity, z));
        if (z) {
            str = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE;
        } else {
            str = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE;
        }
        uriBuilder.appendArg("people_only_them", str);
        return uriBuilder.build();
    }

    public UriBuilder replaceMainKeywordTarget(String str, FilterResultsEntity filterResultsEntity) {
        UriBuilder uriBuilder = new UriBuilder(str);
        String subCategory = getSubCategory(filterResultsEntity);
        String name = filterResultsEntity.getName();
        String category = filterResultsEntity.getCategory();
        uriBuilder.appendArg("sub", subCategory).appendArg("title", name).appendArg("term", category).appendArg("collect_keyword", SearchWordCollector.getCollectedKeyword(name, subCategory, category)).appendArg("collect_type", SearchWordCollector.Type.FACET.toString());
        if (filterResultsEntity.isPerson()) {
            uriBuilder.appendArg("isNamed", String.valueOf(IdentityCreatureUtil.isAssignedCreature(subCategory))).appendArg("people_from_visual_search", true);
        }
        if (LocationKey.isSearchKeyword(str) && PreferenceFeatures.OneUi7x.DISABLE_TIMELINE_ON_KEYWORD) {
            uriBuilder.appendArg("disableTimeline", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
        }
        uriBuilder.appendArg("RefreshFilterResults", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE).appendArg("need_keyword_search", true);
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            uriBuilder.appendArg("search_skip_save_history", false);
        }
        return uriBuilder;
    }
}
