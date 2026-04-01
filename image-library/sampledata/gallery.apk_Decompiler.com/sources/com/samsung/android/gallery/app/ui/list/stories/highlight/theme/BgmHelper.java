package com.samsung.android.gallery.app.ui.list.stories.highlight.theme;

import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.bgm.bgmlist.story.Bgm;
import com.samsung.android.gallery.module.story.EffectTheme;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BgmHelper {
    private static final ConcurrentHashMap<String, String> sLastUsedBgm = new ConcurrentHashMap<>();

    public static BgmExtraInfo createBgmExtraInfo(EventHandler eventHandler, EffectTheme effectTheme, boolean z, String str) {
        return createBgmExtraInfo(pickThemeBgm(effectTheme, eventHandler), z, str);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.util.List} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String pickThemeBgm(com.samsung.android.gallery.module.story.EffectTheme r6, com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler r7) {
        /*
            java.lang.String r0 = "pickThemeBgm"
            java.lang.String r1 = "BgmHelper"
            java.lang.String r2 = r6.name()     // Catch:{ Exception -> 0x007a }
            java.lang.String[] r2 = com.samsung.android.gallery.module.bgm.bgmlist.story.Bgm.getBgmList(r2)     // Catch:{ Exception -> 0x007a }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x007a }
            r3.<init>()     // Catch:{ Exception -> 0x007a }
            com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest r4 = com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest.REQ_BGM_URI_SERVICE     // Catch:{ Exception -> 0x007a }
            java.lang.Object r4 = r7.requestData(r4)     // Catch:{ Exception -> 0x007a }
            com.samsung.android.gallery.module.bgm.BgmUriService r4 = (com.samsung.android.gallery.module.bgm.BgmUriService) r4     // Catch:{ Exception -> 0x007a }
            if (r4 == 0) goto L_0x0037
            java.util.stream.Stream r2 = java.util.Arrays.stream(r2)     // Catch:{ Exception -> 0x007a }
            h3.b r3 = new h3.b     // Catch:{ Exception -> 0x007a }
            r5 = 7
            r3.<init>(r5, r4)     // Catch:{ Exception -> 0x007a }
            java.util.stream.Stream r2 = r2.filter(r3)     // Catch:{ Exception -> 0x007a }
            java.util.stream.Collector r3 = java.util.stream.Collectors.toList()     // Catch:{ Exception -> 0x007a }
            java.lang.Object r2 = r2.collect(r3)     // Catch:{ Exception -> 0x007a }
            r3 = r2
            java.util.List r3 = (java.util.List) r3     // Catch:{ Exception -> 0x007a }
            goto L_0x0037
        L_0x0035:
            r7 = move-exception
            goto L_0x0096
        L_0x0037:
            boolean r2 = r3.isEmpty()     // Catch:{ Exception -> 0x007a }
            if (r2 == 0) goto L_0x004c
            java.lang.String r2 = r6.name()     // Catch:{ Exception -> 0x007a }
            java.lang.String[] r2 = com.samsung.android.gallery.module.bgm.bgmlist.story.Bgm.getPreloadBgmList(r2)     // Catch:{ Exception -> 0x007a }
            java.util.List r2 = java.util.Arrays.asList(r2)     // Catch:{ Exception -> 0x007a }
            r3.addAll(r2)     // Catch:{ Exception -> 0x007a }
        L_0x004c:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r2 = sLastUsedBgm     // Catch:{ Exception -> 0x007a }
            java.lang.String r4 = r6.name()     // Catch:{ Exception -> 0x007a }
            java.lang.Object r2 = r2.get(r4)     // Catch:{ Exception -> 0x007a }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x007a }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x007a }
            if (r4 != 0) goto L_0x0061
            r3.remove(r2)     // Catch:{ Exception -> 0x007a }
        L_0x0061:
            int r2 = r3.size()     // Catch:{ Exception -> 0x007a }
            int r2 = com.samsung.android.gallery.support.utils.RandomNumber.nextInt(r2)     // Catch:{ Exception -> 0x007a }
            java.lang.Object r2 = r3.get(r2)     // Catch:{ Exception -> 0x007a }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x007a }
            updateLastUsedBgm(r2)
            java.lang.Object[] r6 = new java.lang.Object[]{r2, r6}
            com.samsung.android.gallery.support.utils.Log.d(r1, r0, r6)
            return r2
        L_0x007a:
            java.lang.String r2 = "fail to load bgm, use preload bgm"
            com.samsung.android.gallery.support.utils.Log.w(r1, r2)     // Catch:{ all -> 0x0035 }
            int r7 = r7.hashCode()     // Catch:{ all -> 0x0035 }
            int r2 = r6.ordinal()     // Catch:{ all -> 0x0035 }
            java.lang.String r7 = com.samsung.android.gallery.module.story.EffectTheme.getBgmName(r7, r2)     // Catch:{ all -> 0x0035 }
            updateLastUsedBgm(r7)
            java.lang.Object[] r6 = new java.lang.Object[]{r7, r6}
            com.samsung.android.gallery.support.utils.Log.d(r1, r0, r6)
            return r7
        L_0x0096:
            java.lang.String r2 = ""
            updateLastUsedBgm(r2)
            java.lang.Object[] r6 = new java.lang.Object[]{r2, r6}
            com.samsung.android.gallery.support.utils.Log.d(r1, r0, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.stories.highlight.theme.BgmHelper.pickThemeBgm(com.samsung.android.gallery.module.story.EffectTheme, com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler):java.lang.String");
    }

    public static void updateLastUsedBgm(String str) {
        for (String next : Bgm.getBelongThemes(str)) {
            if (!TextUtils.isEmpty(str)) {
                sLastUsedBgm.put(next, str);
            }
        }
    }

    private static BgmExtraInfo createBgmExtraInfo(String str, boolean z, String str2) {
        BgmExtraInfo bgmExtraInfo = new BgmExtraInfo();
        bgmExtraInfo.bgmName = str;
        bgmExtraInfo.isFragmentedBgm = !z;
        bgmExtraInfo.isMyMusic = z;
        bgmExtraInfo.path = str2;
        return bgmExtraInfo;
    }
}
