package com.samsung.android.gallery.support.utils;

import A.a;
import E5.b;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import h4.C0464a;
import i.C0212a;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class IdentityCreatureUtil {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Category {
        PEOPLE,
        PET
    }

    public static String create(long j2, long j3, long j8, Category category) {
        String str;
        if (PreferenceFeatures.OneUi5x.SUPPORT_UNIFIED_CREATURE_KEY) {
            str = createUnifiedIdentityInfo(createUnifiedId(j2, j3, j8));
        } else {
            str = createIdentityInfo(j2, j3);
        }
        StringBuilder t = C0212a.t(str, NumericEnum.SEP);
        t.append(category.ordinal());
        return t.toString();
    }

    private static String createIdentityInfo(long j2, long j3) {
        if (unassignedCreature(j3)) {
            return a.f("key_group_id:", j2);
        }
        return a.f("key_creature_id:", j3);
    }

    public static String createPeopleUnifiedId(long j2) {
        return createWithUnifiedId(j2, Category.PEOPLE);
    }

    public static String createPetUnifiedId(long j2) {
        return createWithUnifiedId(j2, Category.PET);
    }

    public static long createUnifiedId(long j2, long j3, long j8) {
        if (j8 >= 0) {
            return j8;
        }
        if (unassignedCreature(j3)) {
            return j2 + 100000;
        }
        return j3;
    }

    private static String createUnifiedIdentityInfo(long j2) {
        return a.f("key_unified_id:", j2);
    }

    public static String createWithCreatureId(long j2, Category category) {
        return create(-1, j2, -1, category);
    }

    public static String createWithUnifiedId(long j2, Category category) {
        return create(-1, -1, j2, category);
    }

    public static long getIdentityId(String str) {
        try {
            if (PreferenceFeatures.OneUi5x.SUPPORT_UNIFIED_CREATURE_KEY) {
                if (isUnifiedKey(str)) {
                    long j2 = UnsafeCast.toLong(str.split(NumericEnum.SEP)[1], -1);
                    if (j2 < 100000) {
                        return j2;
                    }
                    return j2 - 100000;
                }
            }
            return UnsafeCast.toLong(str.split(NumericEnum.SEP)[1], -1);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            Log.e((CharSequence) "IdentityCreatureUtil", "wrong id : " + str, e);
            return -1;
        }
    }

    public static long getUnifiedIdentityId(String str) {
        if (str != null) {
            try {
                return UnsafeCast.toLong(str.split(NumericEnum.SEP)[1], -1);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                Log.e((CharSequence) "IdentityCreatureUtil", "wrong id : ".concat(str), e);
            }
        }
        return -1;
    }

    public static String getUnifiedIdentityIds(List<String> list, boolean z) {
        C0464a aVar;
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        Stream<String> stream = list.stream();
        if (z) {
            aVar = new C0464a(14);
        } else {
            aVar = new C0464a(15);
        }
        stream.filter(aVar).mapToLong(new b(6)).forEach(new C0681t(stringJoiner));
        return stringJoiner.toString();
    }

    public static boolean isAssignedCreature(long j2) {
        return j2 < 100000;
    }

    public static boolean isIdentityKey(String str) {
        if (str == null) {
            return false;
        }
        String str2 = str.split(NumericEnum.SEP)[0];
        if (PreferenceFeatures.OneUi5x.SUPPORT_UNIFIED_CREATURE_KEY) {
            return "key_unified_id".equals(str2);
        }
        if ("key_creature_id".equals(str2) || "key_group_id".equals(str2)) {
            return true;
        }
        return false;
    }

    public static boolean isPerson(String str) {
        if (str != null) {
            String[] split = str.split(NumericEnum.SEP);
            if (Category.PEOPLE.ordinal() == Integer.parseInt(split[split.length - 1])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPet(String str) {
        if (str != null) {
            String[] split = str.split(NumericEnum.SEP);
            if (Category.PET.ordinal() == Integer.parseInt(split[split.length - 1])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPetRecognized() {
        if (!Features.isEnabled(Features.SUPPORT_PET_CLUSTER) || !PreferenceCache.SearchPetClusterRecognized.getBoolean()) {
            return false;
        }
        return true;
    }

    private static boolean isUnifiedKey(String str) {
        if (str == null || !str.contains("key_unified_id")) {
            return false;
        }
        return true;
    }

    private static boolean unassignedCreature(long j2) {
        if (j2 <= 1) {
            return true;
        }
        return false;
    }

    public static boolean isAssignedCreature(String str) {
        if (str == null) {
            return false;
        }
        if (!PreferenceFeatures.OneUi5x.SUPPORT_UNIFIED_CREATURE_KEY || !isUnifiedKey(str)) {
            return "key_creature_id".equals(str.split(NumericEnum.SEP)[0]);
        }
        if (Long.parseLong(str.split(NumericEnum.SEP)[1]) < 100000) {
            return true;
        }
        return false;
    }
}
