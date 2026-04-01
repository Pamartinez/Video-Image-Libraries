package com.samsung.android.gallery.database.dbtype;

import T8.C0578a;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum GroupType {
    BURST(1, 'B'),
    SIMILAR(2, 's'),
    SINGLE_TAKEN(3, 'S');
    
    public final char tag;
    public final String type;
    public final int value;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DataHolder {
        static final HashMap<Character, GroupType> map = null;

        static {
            map = new HashMap<Character, GroupType>() {
                {
                    GroupType groupType = GroupType.BURST;
                    put(Character.valueOf(groupType.tag), groupType);
                    GroupType groupType2 = GroupType.SIMILAR;
                    put(Character.valueOf(groupType2.tag), groupType2);
                    GroupType groupType3 = GroupType.SINGLE_TAKEN;
                    put(Character.valueOf(groupType3.tag), groupType3);
                }
            };
        }
    }

    private GroupType(int i2, char c5) {
        this.value = i2;
        this.type = String.valueOf(i2);
        this.tag = c5;
    }

    public static GroupType[] arrayOf(String str) {
        char[] charArray = str.toCharArray();
        ArrayList arrayList = new ArrayList();
        for (char of2 : charArray) {
            arrayList.add(of(of2));
        }
        return (GroupType[]) arrayList.stream().distinct().toArray(new C0578a(10));
    }

    public static GroupType of(char c5) {
        return DataHolder.map.get(Character.valueOf(c5));
    }
}
