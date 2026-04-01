package com.samsung.android.gallery.app.ui.list.search.pictures.relationship;

import A4.C0375j;
import A4.S;
import A6.a;
import B5.b;
import B5.c;
import android.content.Context;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.creature.people.RelationshipMultiplePickerCmd;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RelationshipPickerLauncher {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launch$1(Supplier supplier, ISearchPicturesView iSearchPicturesView, Map map, Object[] objArr) {
        String str;
        if (!((Boolean) supplier.get()).booleanValue()) {
            LinkedHashMap linkedHashMap = (LinkedHashMap) map;
            if (objArr.length > 1) {
                str = objArr[1];
            } else {
                str = null;
            }
            showRelationShipPicker(iSearchPicturesView, linkedHashMap, str);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launch$2(Supplier supplier, ISearchPicturesView iSearchPicturesView, Object[] objArr, Map map) {
        ThreadUtil.runOnUiThread(new c(this, supplier, iSearchPicturesView, map, objArr, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launch$3(ISearchPicturesView iSearchPicturesView, List list, Object[] objArr) {
        Context context = iSearchPicturesView.getContext();
        if (context != null) {
            b bVar = new b(iSearchPicturesView, 0);
            PersonalDataCore.getInstance(iSearchPicturesView.getBlackboard()).loadCandidatePeopleBy(context, list, new S(this, bVar, iSearchPicturesView, objArr, 1), bVar);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showRelationShipPicker$5(String str, LinkedHashMap linkedHashMap, ISearchPicturesView iSearchPicturesView) {
        String parseJsonNoRelationKeywords = parseJsonNoRelationKeywords(str);
        if (linkedHashMap.values().stream().anyMatch(new C0375j(2))) {
            new RelationshipMultiplePickerCmd().execute(iSearchPicturesView.getEventContext(), linkedHashMap);
            return;
        }
        iSearchPicturesView.getBlackboard().publish("data://user/PersonRelationshipCandidateMap", linkedHashMap);
        UriBuilder appendArg = new UriBuilder(LocationKey.getSearchLocationKey("location://search/fileList/RelationshipPreview", parseJsonNoRelationKeywords)).appendArg("sub", parseJsonNoRelationKeywords).appendArg("title", parseJsonNoRelationKeywords).appendArg("term", "key_word").appendArg("need_keyword_search", true).appendArg("search_skip_save_history", true).appendArg("requestFacetTerms", "recommended_id").appendArg("requestMaxFacetTermsSize", 30).appendArg("facetOnly", true).appendArg("disableTimeline", ArgumentsUtil.getArgValue(iSearchPicturesView.getLocationKey(), "disableTimeline"));
        if (TextUtils.isEmpty(parseJsonNoRelationKeywords)) {
            appendArg.appendArg("show_creature_list", true);
        }
        iSearchPicturesView.getBlackboard().post("command://MoveURL", appendArg.build());
    }

    private String parseJsonNoRelationKeywords(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() > 0) {
                return jSONArray.get(0).toString();
            }
            return "";
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void showRelationShipPicker(ISearchPicturesView iSearchPicturesView, LinkedHashMap<String, List<Long>> linkedHashMap, String str) {
        String str2 = str;
        ThreadUtil.runOnUiThread(new a((Object) this, (Object) str2, (Object) linkedHashMap, (Object) iSearchPicturesView, 2));
    }

    public void launch(ISearchPicturesView iSearchPicturesView, Object[] objArr) {
        if (objArr == null) {
            Log.e("RelationshipPickerLauncher", "no suggester data");
            return;
        }
        List<String> parseJsonRelationship = RelationshipKeySet.parseJsonRelationship(objArr[0]);
        if (Features.isEnabled(Features.SUPPORT_PDC_RELATIONSHIP)) {
            SimpleThreadPool.getInstance().execute(new a((Object) this, (Object) iSearchPicturesView, (Object) parseJsonRelationship, (Object) objArr, 1));
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        parseJsonRelationship.forEach(new B5.a(linkedHashMap, 0));
        new RelationshipMultiplePickerCmd().execute(iSearchPicturesView.getEventContext(), linkedHashMap);
    }
}
