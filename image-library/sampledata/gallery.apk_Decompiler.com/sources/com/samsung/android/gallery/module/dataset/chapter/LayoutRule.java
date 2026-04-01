package com.samsung.android.gallery.module.dataset.chapter;

import i.C0212a;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LayoutRule {
    private static final HashMap<Integer, int[]> sItemGroup;
    private static final HashMap<String, RuleHolder> sLayoutRules;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RuleHolder {
        public Align align;
        public Layout type;

        public RuleHolder(Layout layout, Align align2) {
            this.type = layout;
            this.align = align2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RuleKey {
        private static final int[] subKeyForSingle = {0, 0, 2, 0, 1, 0, 2, 0, 1, 0};
        private static final int[] subKeyForTwo = {2, 0, 0, 1, 2, 2, 1, 1, 2, 1};

        /* access modifiers changed from: private */
        public static String get(int i2, int i7, int i8) {
            return getPrefix(i2, i7) + getSubKey(i2, i7, i8);
        }

        private static String getPrefix(int i2, int i7) {
            if (i2 == 2) {
                return C0212a.j(i7, "2by/", "/");
            }
            if (i2 == 3) {
                return "3by/";
            }
            return "1by/";
        }

        private static String getSubKey(int i2, int i7, int i8) {
            if (i2 == 3) {
                return String.valueOf(i7);
            }
            if (i2 == 2) {
                return String.valueOf(subKeyForTwo[i8 % 10]);
            }
            return String.valueOf(subKeyForSingle[i8 % 10]);
        }
    }

    static {
        HashMap<String, RuleHolder> hashMap = new HashMap<>();
        sLayoutRules = hashMap;
        Layout layout = Layout.REAL_RATIO;
        Align align = Align.MIDDLE;
        hashMap.put("horizontal", new RuleHolder(layout, align));
        hashMap.put(RuleKey.get(1, 0, 0), new RuleHolder(layout, align));
        String a7 = RuleKey.get(1, 0, 2);
        Layout layout2 = Layout.LARGE;
        Align align2 = Align.START;
        hashMap.put(a7, new RuleHolder(layout2, align2));
        String a10 = RuleKey.get(1, 0, 4);
        Align align3 = Align.END;
        hashMap.put(a10, new RuleHolder(layout2, align3));
        String a11 = RuleKey.get(2, 0, 1);
        Layout layout3 = Layout.AVERAGE;
        hashMap.put(a11, new RuleHolder(layout3, align2));
        hashMap.put(RuleKey.get(2, 1, 1), new RuleHolder(layout3, align3));
        hashMap.put(RuleKey.get(2, 0, 3), new RuleHolder(layout2, align2));
        String a12 = RuleKey.get(2, 1, 3);
        Layout layout4 = Layout.SMALL;
        hashMap.put(a12, new RuleHolder(layout4, align3));
        hashMap.put(RuleKey.get(2, 0, 4), new RuleHolder(layout4, align2));
        hashMap.put(RuleKey.get(2, 1, 4), new RuleHolder(layout2, align3));
        hashMap.put(RuleKey.get(3, 0, 0), new RuleHolder(layout3, align2));
        hashMap.put(RuleKey.get(3, 1, 0), new RuleHolder(layout3, align));
        hashMap.put(RuleKey.get(3, 2, 0), new RuleHolder(layout3, align3));
        HashMap<Integer, int[]> hashMap2 = new HashMap<>();
        sItemGroup = hashMap2;
        hashMap2.put(1, new int[]{1});
        hashMap2.put(2, new int[]{2});
        hashMap2.put(3, new int[]{3});
        hashMap2.put(4, new int[]{1, 2, 1});
        hashMap2.put(5, new int[]{1, 3, 1});
        hashMap2.put(6, new int[]{1, 2, 3});
        hashMap2.put(7, new int[]{1, 2, 3, 1});
        hashMap2.put(8, new int[]{1, 2, 1, 3, 1});
        hashMap2.put(9, new int[]{1, 2, 3, 1, 2});
    }

    private static int[] getGroupTypeAndPos(int i2, int i7) {
        int i8 = 0;
        for (int i10 : sItemGroup.getOrDefault(Integer.valueOf(i2), new int[]{1, 1})) {
            i8 += i10;
            if (i7 < i8) {
                return new int[]{i10, i7 - (i8 - i10)};
            }
        }
        return new int[]{1, 1};
    }

    private static void modifyGroupSingleRule(LayoutInfo layoutInfo) {
        if (layoutInfo.groupType != 1 || !layoutInfo.layoutType.large() || layoutInfo.align.middle()) {
            layoutInfo.hasExtraInfo = false;
        } else if (!layoutInfo.hasExtraInfo) {
            layoutInfo.align = Align.MIDDLE;
            layoutInfo.layoutType = Layout.LARGE;
        }
    }

    private static void modifyGroupTwoRule(LayoutInfo layoutInfo, LayoutInfo layoutInfo2, int i2) {
        if (layoutInfo2 != null && layoutInfo.groupType == 2 && i2 == 1) {
            Layout layout = layoutInfo2.layoutType;
            if (layout.large()) {
                layoutInfo.layoutType = Layout.SMALL;
            } else if (layout.small()) {
                layoutInfo.layoutType = Layout.LARGE;
            } else if (layout.average()) {
                layoutInfo.layoutType = Layout.AVERAGE;
            }
        }
    }

    public static void setGroupRule(List<LayoutInfo> list) {
        int size = list.size();
        LayoutInfo layoutInfo = null;
        int i2 = 0;
        while (i2 < size) {
            LayoutInfo layoutInfo2 = list.get(i2);
            setLayoutRule(layoutInfo2, layoutInfo, size, i2);
            i2++;
            layoutInfo = layoutInfo2;
        }
    }

    private static void setLayoutAndAlign(LayoutInfo layoutInfo, int i2) {
        RuleHolder ruleHolder;
        if (layoutInfo.horizontal) {
            ruleHolder = sLayoutRules.get("horizontal");
        } else if (layoutInfo.groupType != 2 || !layoutInfo.isSimilarChunk()) {
            ruleHolder = sLayoutRules.get(RuleKey.get(layoutInfo.groupType, i2, layoutInfo.mod));
        } else {
            ruleHolder = sLayoutRules.get(RuleKey.get(layoutInfo.groupType, i2, 1));
        }
        if (ruleHolder == null) {
            ruleHolder = new RuleHolder(Layout.REAL_RATIO, Align.MIDDLE);
        }
        layoutInfo.layoutType = ruleHolder.type;
        layoutInfo.align = ruleHolder.align;
    }

    private static void setLayoutRule(LayoutInfo layoutInfo, LayoutInfo layoutInfo2, int i2, int i7) {
        int[] groupTypeAndPos = getGroupTypeAndPos(i2, i7);
        layoutInfo.groupType = groupTypeAndPos[0];
        setLayoutAndAlign(layoutInfo, groupTypeAndPos[1]);
        modifyGroupSingleRule(layoutInfo);
        modifyGroupTwoRule(layoutInfo, layoutInfo2, groupTypeAndPos[1]);
    }

    public static void setTagChunkRule(LayoutInfo layoutInfo) {
        layoutInfo.align = Align.START;
        layoutInfo.layoutType = Layout.FULL;
        layoutInfo.groupType = 1;
    }
}
