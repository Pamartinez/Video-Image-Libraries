package com.samsung.android.gallery.module.search.recommendation;

import android.content.Context;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.SuggestionApi;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.type.SuggestionKeyword;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceName;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BasicQuerySuggester implements IQuerySuggester {
    private static volatile BasicQuerySuggester sInstance;
    protected final ArrayList<String> mOrderedList = new ArrayList<>();
    protected SuggestionApi mSuggestionApi;
    private final ConcurrentLinkedQueue<IRecommendationItem> mSuggestionQueue = new ConcurrentLinkedQueue<>();

    private static String findSuggestionName(IRecommendationItem iRecommendationItem) {
        return SuggestionKeyword.values()[iRecommendationItem.getOrdinary()].name();
    }

    private String getDefaultPriorityList() {
        StringBuilder sb2 = new StringBuilder();
        for (SuggestionKeyword name : SuggestionKeyword.values()) {
            sb2.append(name.name());
            sb2.append("/");
        }
        return sb2.toString();
    }

    public static BasicQuerySuggester getInstance() {
        if (sInstance == null) {
            synchronized (BasicQuerySuggester.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new BasicQuerySuggester();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private int getOrdinary(String str) {
        return this.mOrderedList.indexOf(str);
    }

    private void saveSuggestionOrder() {
        StringBuilder sb2 = new StringBuilder();
        Iterator<String> it = this.mOrderedList.iterator();
        while (it.hasNext()) {
            sb2.append(it.next());
            sb2.append("/");
        }
        GalleryPreference.getInstance().saveState(PreferenceName.SUGGESTION_LIST_ORDER, sb2.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setList(android.content.Context r7, android.database.Cursor r8) {
        /*
            r6 = this;
            java.util.concurrent.ConcurrentLinkedQueue<com.samsung.android.gallery.module.search.recommendation.IRecommendationItem> r0 = r6.mSuggestionQueue
            r0.clear()
            com.samsung.android.gallery.support.type.SuggestionKeyword[] r0 = com.samsung.android.gallery.support.type.SuggestionKeyword.values()
            int r0 = r0.length
            com.samsung.android.gallery.module.search.recommendation.SuggestionItem[] r1 = new com.samsung.android.gallery.module.search.recommendation.SuggestionItem[r0]
            if (r8 == 0) goto L_0x003b
            boolean r2 = r8.moveToFirst()
            if (r2 == 0) goto L_0x003b
        L_0x0014:
            com.samsung.android.gallery.module.search.recommendation.SuggestionItemLoader r2 = new com.samsung.android.gallery.module.search.recommendation.SuggestionItemLoader
            r2.<init>(r8, r7)
            com.samsung.android.gallery.module.search.recommendation.SuggestionItem r2 = r2.getSuggestionItem()
            if (r2 == 0) goto L_0x0035
            java.lang.String r3 = findSuggestionName(r2)
            int r4 = r6.getOrdinary(r3)
            r5 = -1
            if (r4 != r5) goto L_0x0033
            java.lang.String r2 = "BasicQuerySuggester"
            java.lang.String r4 = "suggestionName: "
            A.a.u(r4, r3, r2)
            goto L_0x0035
        L_0x0033:
            r1[r4] = r2
        L_0x0035:
            boolean r2 = r8.moveToNext()
            if (r2 != 0) goto L_0x0014
        L_0x003b:
            r7 = 0
        L_0x003c:
            if (r7 >= r0) goto L_0x004a
            r8 = r1[r7]
            if (r8 == 0) goto L_0x0047
            java.util.concurrent.ConcurrentLinkedQueue<com.samsung.android.gallery.module.search.recommendation.IRecommendationItem> r2 = r6.mSuggestionQueue
            r2.add(r8)
        L_0x0047:
            int r7 = r7 + 1
            goto L_0x003c
        L_0x004a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.search.recommendation.BasicQuerySuggester.setList(android.content.Context, android.database.Cursor):void");
    }

    public void clear() {
        this.mOrderedList.clear();
    }

    public AbstractQueue<IRecommendationItem> consume(Context context, IRecommendationItem iRecommendationItem) {
        this.mOrderedList.remove(findSuggestionName(iRecommendationItem));
        this.mOrderedList.add(findSuggestionName(iRecommendationItem));
        saveSuggestionOrder();
        return this.mSuggestionQueue;
    }

    public AbstractQueue<IRecommendationItem> getSuggestion(Context context, Blackboard blackboard, long j2) {
        if (this.mSuggestionApi == null) {
            this.mSuggestionApi = new SuggestionApi(new QueryParams());
        }
        Cursor suggestion = this.mSuggestionApi.getSuggestion(j2);
        try {
            load(context, suggestion);
            if (suggestion != null) {
                suggestion.close();
            }
            return this.mSuggestionQueue;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void load(Context context, Cursor cursor) {
        this.mOrderedList.clear();
        Collections.addAll(this.mOrderedList, GalleryPreference.getInstance().loadString(PreferenceName.SUGGESTION_LIST_ORDER, getDefaultPriorityList()).split("/"));
        setList(context, cursor);
    }

    public void preloadSuggestion(Context context, Blackboard blackboard) {
    }
}
