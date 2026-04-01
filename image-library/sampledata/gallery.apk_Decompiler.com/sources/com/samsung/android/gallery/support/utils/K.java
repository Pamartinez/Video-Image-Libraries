package com.samsung.android.gallery.support.utils;

import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.ai.language.Suggester;
import com.samsung.android.sdk.scs.ai.language.SuggestionCategory;
import com.samsung.android.sdk.scs.ai.language.Translator;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import com.samsung.android.sum.core.graph.GraphNode;
import com.samsung.android.sum.core.graph.MFGraph;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class K implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f3166h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Serializable f3167i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Serializable f3168j;

    public /* synthetic */ K(MFGraph mFGraph, MFGraph.Builder builder, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
        this.d = 3;
        this.g = mFGraph;
        this.f3166h = builder;
        this.f = arrayList;
        this.f3167i = arrayList2;
        this.f3168j = arrayList3;
        this.e = arrayList4;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                PreferenceCache.lambda$migrate$2((ArrayList) this.f, (HashMap) this.g, (Map) this.e, (HashMap) this.f3166h, (HashMap) this.f3167i, (HashMap) this.f3168j, (PreferenceCache) obj);
                return;
            case 1:
                ((Suggester) this.f).lambda$suggestion$1((AppInfo.RequestType) this.g, (AppInfo) this.f3166h, (String) this.f3167i, (SuggestionCategory) this.f3168j, (Map) this.e, (LlmServiceObserver2) obj);
                return;
            case 2:
                ((Translator) this.f).lambda$interpret$0((AppInfo) this.g, (String) this.f3166h, (String) this.f3167i, (String) this.f3168j, (Map) this.e, (LlmServiceObserver2) obj);
                return;
            default:
                ((MFGraph) this.g).lambda$new$0((MFGraph.Builder) this.f3166h, (ArrayList) this.f, (ArrayList) this.f3167i, (ArrayList) this.f3168j, (ArrayList) this.e, (GraphNode) obj);
                return;
        }
    }

    public /* synthetic */ K(Object obj, Object obj2, Object obj3, String str, Object obj4, Map map, int i2) {
        this.d = i2;
        this.f = obj;
        this.g = obj2;
        this.f3166h = obj3;
        this.f3167i = str;
        this.f3168j = (Serializable) obj4;
        this.e = map;
    }

    public /* synthetic */ K(ArrayList arrayList, HashMap hashMap, Map map, HashMap hashMap2, HashMap hashMap3, HashMap hashMap4) {
        this.d = 0;
        this.f = arrayList;
        this.g = hashMap;
        this.e = map;
        this.f3166h = hashMap2;
        this.f3167i = hashMap3;
        this.f3168j = hashMap4;
    }
}
