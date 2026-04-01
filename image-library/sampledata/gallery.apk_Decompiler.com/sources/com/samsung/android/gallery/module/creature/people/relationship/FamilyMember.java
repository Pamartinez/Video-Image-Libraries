package com.samsung.android.gallery.module.creature.people.relationship;

import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FamilyMember {
    public static HashMap<String, FamilyMember> FAMILY_MEMBER_MAP = new HashMap<String, FamilyMember>() {
        {
            put("family", new FamilyMember("family", (String) null));
            put("parents", new FamilyMember("parents", "family"));
            put("spouse", new FamilyMember("spouse", "family"));
            put("older_sister_female", new FamilyMember("older_sister_female", "family"));
            put("older_brother_female", new FamilyMember("older_brother_female", "family"));
            put("older_sister_male", new FamilyMember("older_sister_male", "family"));
            put("older_brother_male", new FamilyMember("older_brother_male", "family"));
            put("younger_sister", new FamilyMember("younger_sister", "family"));
            put("younger_brother", new FamilyMember("younger_brother", "family"));
            put("mother", new FamilyMember("mother", "parents"));
            put("father", new FamilyMember("father", "parents"));
            put("wife", new FamilyMember("wife", "spouse"));
            put("husband", new FamilyMember("husband", "spouse"));
        }
    };
    String parent;
    String type;

    public FamilyMember(String str, String str2) {
        this.type = str;
        this.parent = str2;
    }

    public static String getHierarchy(String str) {
        if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71 || str == null) {
            return str;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        String str2 = str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)[0];
        while (FAMILY_MEMBER_MAP.containsKey(str2) && (str2 = FAMILY_MEMBER_MAP.get(str2).parent) != null) {
            arrayList.add(str2);
        }
        Collections.reverse(arrayList);
        return TextUtils.join("\u001f", arrayList);
    }
}
