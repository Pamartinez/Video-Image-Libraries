package androidx.profileinstaller;

import java.util.TreeMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DexProfileData {
    final String apkName;
    int classSetSize;
    int[] classes;
    final long dexChecksum;
    final String dexName;
    final int hotMethodRegionSize;
    long mTypeIdCount;
    final TreeMap<Integer, Integer> methods;
    final int numMethodIds;

    public DexProfileData(String str, String str2, long j2, long j3, int i2, int i7, int i8, int[] iArr, TreeMap<Integer, Integer> treeMap) {
        this.apkName = str;
        this.dexName = str2;
        this.dexChecksum = j2;
        this.mTypeIdCount = j3;
        this.classSetSize = i2;
        this.hotMethodRegionSize = i7;
        this.numMethodIds = i8;
        this.classes = iArr;
        this.methods = treeMap;
    }
}
