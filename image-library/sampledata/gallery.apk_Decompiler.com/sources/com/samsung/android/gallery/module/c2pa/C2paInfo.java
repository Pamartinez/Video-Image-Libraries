package com.samsung.android.gallery.module.c2pa;

import A.a;
import N2.j;
import android.content.Context;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.ai.visual.c2pa.Action;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paAction;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifestList;
import com.samsung.android.sdk.scs.ai.visual.c2pa.Exif;
import i.C0212a;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C2paInfo {
    public static final C2paInfo EMPTY = new C2paInfo((C2paManifestList) null, false);
    private final List<C2paActionWrapper> mActionList = new ArrayList();
    String mRepresentAction;
    private final boolean mTrusted;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class C2paActionWrapper {
        public final Action action;
        public final String claimGenerator;
        public final C2paExif exif;
        public final boolean isAiGenerated;
        public final boolean isCreated;
        public final boolean isInvalid;
        public final String issuer;
        public final String softwareAgent;
        public final String time;

        public C2paActionWrapper(Action action2, Map<Exif, String> map) {
            C2paExif c2paExif;
            this.isCreated = isCreatedAction(action2);
            this.isAiGenerated = action2.isAiGenerated();
            this.claimGenerator = cleanupC2paStringValue(action2.getClaimGenerator());
            this.time = convertTime(action2.getActionTime());
            this.softwareAgent = action2.getSoftwareAgent();
            this.issuer = action2.getIssuer();
            this.isInvalid = Boolean.TRUE.equals(action2.isInvalid());
            if (map == null || map.isEmpty()) {
                c2paExif = null;
            } else {
                c2paExif = new C2paExif(map);
            }
            this.exif = c2paExif;
            this.action = action2;
        }

        public static boolean isCreatedAction(Action action2) {
            return C2paAction.C2PA_CREATED.getStr().equals(action2.getAction());
        }

        public static boolean isOpenedAction(Action action2) {
            return C2paAction.C2PA_OPENED.getStr().equals(action2.getAction());
        }

        public String cleanupC2paStringValue(String str) {
            int lastIndexOf;
            String trim = str.trim();
            if (trim.isEmpty() || (lastIndexOf = trim.lastIndexOf(32)) <= 0) {
                return trim;
            }
            return trim.substring(0, lastIndexOf);
        }

        public String convertTime(String str) {
            if ("UnKnown".equalsIgnoreCase(str)) {
                return AppResources.getString(R$string.no_date_information);
            }
            try {
                return TimeUtil.getLocalizedDateTime(ZonedDateTime.parse(str).toInstant().toEpochMilli(), " ", 0);
            } catch (Exception e) {
                a.s(e, j.k("exception ", str, "\n"), "C2paInfo");
                return AppResources.getString(R$string.no_date_information);
            }
        }

        public String getExif() {
            C2paExif c2paExif = this.exif;
            if (c2paExif != null) {
                return c2paExif.getInfo();
            }
            return null;
        }

        public String toString() {
            return this.action.getAction();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0066, code lost:
        if (com.samsung.android.gallery.module.c2pa.C2paInfo.C2paActionWrapper.isCreatedAction(r4) != false) goto L_0x006a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C2paInfo(com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifestList r10, boolean r11) {
        /*
            r9 = this;
            r9.<init>()
            r9.mTrusted = r11
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r9.mActionList = r11
            r11 = 0
            if (r10 == 0) goto L_0x0014
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifestList r10 = r10.getSingleTreeC2paManifestListLatest()
            goto L_0x0015
        L_0x0014:
            r10 = r11
        L_0x0015:
            if (r10 != 0) goto L_0x001c
            java.lang.String r10 = ""
            r9.mRepresentAction = r10
            return
        L_0x001c:
            java.util.List r0 = r10.getFilteredActions(r11, r11, r11)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "action count="
            r1.<init>(r2)
            int r2 = r0.size()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "C2paInfo"
            com.samsung.android.gallery.support.utils.Log.d(r2, r1)
            r1 = 0
            r3 = r1
        L_0x0039:
            int r4 = r0.size()
            if (r3 >= r4) goto L_0x009b
            java.lang.Object r4 = r0.get(r3)
            com.samsung.android.sdk.scs.ai.visual.c2pa.Action r4 = (com.samsung.android.sdk.scs.ai.visual.c2pa.Action) r4
            if (r4 == 0) goto L_0x0098
            boolean r5 = com.samsung.android.gallery.module.c2pa.C2paInfo.C2paActionWrapper.isOpenedAction(r4)
            if (r5 == 0) goto L_0x004e
            goto L_0x0098
        L_0x004e:
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            java.lang.Boolean r6 = r4.isInvalid()
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L_0x0069
            int r6 = r0.size()
            r7 = 1
            int r6 = r6 - r7
            if (r3 != r6) goto L_0x0069
            boolean r6 = com.samsung.android.gallery.module.c2pa.C2paInfo.C2paActionWrapper.isCreatedAction(r4)
            if (r6 == 0) goto L_0x0069
            goto L_0x006a
        L_0x0069:
            r7 = r1
        L_0x006a:
            java.util.List<com.samsung.android.gallery.module.c2pa.C2paInfo$C2paActionWrapper> r6 = r9.mActionList
            com.samsung.android.gallery.module.c2pa.C2paInfo$C2paActionWrapper r8 = new com.samsung.android.gallery.module.c2pa.C2paInfo$C2paActionWrapper
            if (r7 == 0) goto L_0x0075
            java.util.Map r7 = r10.getExifFromSource()
            goto L_0x0076
        L_0x0075:
            r7 = r11
        L_0x0076:
            r8.<init>(r4, r7)
            r6.add(r8)
            if (r5 == 0) goto L_0x0098
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "invalid ["
            r9.<init>(r10)
            r9.append(r3)
            java.lang.String r10 = "]="
            r9.append(r10)
            r9.append(r4)
            java.lang.String r9 = r9.toString()
            com.samsung.android.gallery.support.utils.Log.d(r2, r9)
            return
        L_0x0098:
            int r3 = r3 + 1
            goto L_0x0039
        L_0x009b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.c2pa.C2paInfo.<init>(com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifestList, boolean):void");
    }

    private C2paActionWrapper getLatestAiAction() {
        for (C2paActionWrapper next : this.mActionList) {
            if (next.isAiGenerated) {
                return next;
            }
        }
        return null;
    }

    private C2paActionWrapper getLatestEditAction() {
        for (C2paActionWrapper next : this.mActionList) {
            if (!next.isCreated) {
                return next;
            }
        }
        return null;
    }

    private C2paActionWrapper getSourceAction() {
        for (int size = this.mActionList.size() - 1; size >= 0; size--) {
            C2paActionWrapper c2paActionWrapper = this.mActionList.get(size);
            if (c2paActionWrapper.isCreated) {
                return c2paActionWrapper;
            }
        }
        return null;
    }

    public List<C2paActionWrapper> getActionList() {
        return this.mActionList;
    }

    public String getRepresentActionString(Context context) {
        C2paActionWrapper sourceAction;
        int i2;
        String str = this.mRepresentAction;
        if (str != null) {
            return str;
        }
        this.mRepresentAction = "";
        if (this.mActionList.size() == 1) {
            C2paActionWrapper c2paActionWrapper = this.mActionList.get(0);
            if (c2paActionWrapper.isInvalid) {
                if (c2paActionWrapper.isAiGenerated) {
                    this.mRepresentAction = context.getString(R$string.c2pa_contains_ai_generated_content);
                    this.mRepresentAction = C0212a.p(new StringBuilder(), this.mRepresentAction, "\n");
                }
                String str2 = this.mRepresentAction + context.getString(R$string.c2pa_invalid_item);
                this.mRepresentAction = str2;
                return str2;
            }
        }
        C2paActionWrapper latestAiAction = getLatestAiAction();
        if (latestAiAction != null) {
            this.mRepresentAction = context.getString(R$string.c2pa_contains_ai_generated_content);
            this.mRepresentAction = C0212a.p(new StringBuilder(), this.mRepresentAction, "\n");
            this.mRepresentAction += context.getString(R$string.c2pa_tool_used, new Object[]{latestAiAction.softwareAgent});
        } else {
            C2paActionWrapper latestEditAction = getLatestEditAction();
            if (latestEditAction != null) {
                this.mRepresentAction = context.getString(R$string.c2pa_edited_with, new Object[]{latestEditAction.claimGenerator});
            }
            if (this.mRepresentAction.isEmpty() && (sourceAction = getSourceAction()) != null) {
                if (sourceAction.exif != null) {
                    i2 = R$string.c2pa_shot_on;
                } else {
                    i2 = R$string.c2pa_create_with;
                }
                this.mRepresentAction = SeApiCompat.naturalizeText(context.getString(i2, new Object[]{sourceAction.claimGenerator}));
            }
        }
        return this.mRepresentAction;
    }

    public boolean isEmpty() {
        return this.mActionList.isEmpty();
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("C2paInfo{");
        sb2.append(getActionList().size());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return C0212a.p(sb2, (String) getActionList().stream().map(new Gb.a(26)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "[", "]")), "}");
    }
}
