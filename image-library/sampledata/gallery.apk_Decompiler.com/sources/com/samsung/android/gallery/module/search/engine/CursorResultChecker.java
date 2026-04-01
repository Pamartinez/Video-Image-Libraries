package com.samsung.android.gallery.module.search.engine;

import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CursorResultChecker {
    private static final boolean SUPPORT_HIERARCHICAL_SUGGESTION_V2 = Features.isEnabled(Features.SUPPORT_HIERARCHICAL_SUGGESTION_V2);

    private boolean isExistFallbackResult(ExtraResults extraResults) {
        return extraResults.isExistFallbackResult();
    }

    private boolean isKeywordSearch(SearchFilter searchFilter) {
        return searchFilter.getTerm().equals("key_word");
    }

    private boolean isScsDisabledReasonAlreadyExist(ExtraResults extraResults) {
        return !TextUtils.isEmpty(extraResults.getScsDisabledReason());
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isValid(android.database.Cursor r4, com.samsung.android.gallery.database.dbtype.SearchFilter r5, com.samsung.android.gallery.module.search.engine.ExtraResults r6) {
        /*
            r3 = this;
            boolean r0 = r3.isKeywordSearch(r5)
            r1 = 1
            if (r0 != 0) goto L_0x000e
            boolean r0 = r5.hasLastKeyword()
            if (r0 != 0) goto L_0x000e
            return r1
        L_0x000e:
            boolean r0 = r3.isScsDisabledReasonAlreadyExist(r6)
            if (r0 == 0) goto L_0x0015
            return r1
        L_0x0015:
            r0 = 0
            if (r4 == 0) goto L_0x0050
            int r2 = r4.getCount()
            if (r2 <= 0) goto L_0x001f
            return r1
        L_0x001f:
            com.samsung.android.gallery.module.search.engine.ExtraResults r2 = new com.samsung.android.gallery.module.search.engine.ExtraResults
            r2.<init>()
            android.os.Bundle r4 = r4.getExtras()
            boolean r5 = r5.fromInstantSearch()
            r2.parse(r4, r5)
            java.lang.String r4 = r2.getScsDisabledReason()
            java.lang.String r5 = r2.getRelationshipResult()
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 == 0) goto L_0x004e
            boolean r3 = r3.isExistFallbackResult(r2)
            if (r3 != 0) goto L_0x004e
            java.lang.String r3 = r2.getActionResult()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x004e
            goto L_0x0051
        L_0x004e:
            r3 = r0
            goto L_0x0052
        L_0x0050:
            r4 = 0
        L_0x0051:
            r3 = r1
        L_0x0052:
            if (r3 == 0) goto L_0x005e
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L_0x005e
            r6.setScsDisabledReason(r4)
            goto L_0x005f
        L_0x005e:
            r0 = r3
        L_0x005f:
            r3 = r0 ^ 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.search.engine.CursorResultChecker.isValid(android.database.Cursor, com.samsung.android.gallery.database.dbtype.SearchFilter, com.samsung.android.gallery.module.search.engine.ExtraResults):boolean");
    }
}
