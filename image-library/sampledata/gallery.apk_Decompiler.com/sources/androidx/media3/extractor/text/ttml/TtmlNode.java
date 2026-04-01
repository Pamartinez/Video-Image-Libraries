package androidx.media3.extractor.text.ttml;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableStringBuilder;
import android.util.Base64;
import android.util.Pair;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TtmlNode {
    private List<TtmlNode> children;
    public final long endTimeUs;
    public final String imageId;
    public final boolean isTextNode;
    private final HashMap<String, Integer> nodeEndsByRegion;
    private final HashMap<String, Integer> nodeStartsByRegion;
    public final TtmlNode parent;
    public final String regionId;
    public final long startTimeUs;
    public final TtmlStyle style;
    private final String[] styleIds;
    public final String tag;
    public final String text;

    private TtmlNode(String str, String str2, long j2, long j3, TtmlStyle ttmlStyle, String[] strArr, String str3, String str4, TtmlNode ttmlNode) {
        boolean z;
        this.tag = str;
        this.text = str2;
        this.imageId = str4;
        this.style = ttmlStyle;
        this.styleIds = strArr;
        if (str2 != null) {
            z = true;
        } else {
            z = false;
        }
        this.isTextNode = z;
        this.startTimeUs = j2;
        this.endTimeUs = j3;
        this.regionId = (String) Assertions.checkNotNull(str3);
        this.parent = ttmlNode;
        this.nodeStartsByRegion = new HashMap<>();
        this.nodeEndsByRegion = new HashMap<>();
    }

    private void applyStyleToOutput(Map<String, TtmlStyle> map, Cue.Builder builder, int i2, int i7, int i8) {
        TtmlStyle resolveStyle = TtmlRenderUtil.resolveStyle(this.style, this.styleIds, map);
        SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) builder.getText();
        if (spannableStringBuilder == null) {
            spannableStringBuilder = new SpannableStringBuilder();
            builder.setText(spannableStringBuilder);
        }
        SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
        if (resolveStyle != null) {
            TtmlRenderUtil.applyStylesToSpan(spannableStringBuilder2, i2, i7, resolveStyle, this.parent, map, i8);
            if ("p".equals(this.tag)) {
                if (resolveStyle.getShearPercentage() != Float.MAX_VALUE) {
                    builder.setShearDegrees((resolveStyle.getShearPercentage() * -90.0f) / 100.0f);
                }
                if (resolveStyle.getTextAlign() != null) {
                    builder.setTextAlignment(resolveStyle.getTextAlign());
                }
                if (resolveStyle.getMultiRowAlign() != null) {
                    builder.setMultiRowAlignment(resolveStyle.getMultiRowAlign());
                }
            }
        }
    }

    public static TtmlNode buildNode(String str, long j2, long j3, TtmlStyle ttmlStyle, String[] strArr, String str2, String str3, TtmlNode ttmlNode) {
        return new TtmlNode(str, (String) null, j2, j3, ttmlStyle, strArr, str2, str3, ttmlNode);
    }

    public static TtmlNode buildTextNode(String str) {
        return new TtmlNode((String) null, TtmlRenderUtil.applyTextElementSpacePolicy(str), -9223372036854775807L, -9223372036854775807L, (TtmlStyle) null, (String[]) null, "", (String) null, (TtmlNode) null);
    }

    private static void cleanUpText(SpannableStringBuilder spannableStringBuilder) {
        for (DeleteTextSpan deleteTextSpan : (DeleteTextSpan[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), DeleteTextSpan.class)) {
            spannableStringBuilder.replace(spannableStringBuilder.getSpanStart(deleteTextSpan), spannableStringBuilder.getSpanEnd(deleteTextSpan), "");
        }
        for (int i2 = 0; i2 < spannableStringBuilder.length(); i2++) {
            if (spannableStringBuilder.charAt(i2) == ' ') {
                int i7 = i2 + 1;
                int i8 = i7;
                while (i8 < spannableStringBuilder.length() && spannableStringBuilder.charAt(i8) == ' ') {
                    i8++;
                }
                int i10 = i8 - i7;
                if (i10 > 0) {
                    spannableStringBuilder.delete(i2, i10 + i2);
                }
            }
        }
        if (spannableStringBuilder.length() > 0 && spannableStringBuilder.charAt(0) == ' ') {
            spannableStringBuilder.delete(0, 1);
        }
        for (int i11 = 0; i11 < spannableStringBuilder.length() - 1; i11++) {
            if (spannableStringBuilder.charAt(i11) == 10) {
                int i12 = i11 + 1;
                if (spannableStringBuilder.charAt(i12) == ' ') {
                    spannableStringBuilder.delete(i12, i11 + 2);
                }
            }
        }
        if (spannableStringBuilder.length() > 0 && spannableStringBuilder.charAt(spannableStringBuilder.length() - 1) == ' ') {
            spannableStringBuilder.delete(spannableStringBuilder.length() - 1, spannableStringBuilder.length());
        }
        for (int i13 = 0; i13 < spannableStringBuilder.length() - 1; i13++) {
            if (spannableStringBuilder.charAt(i13) == ' ') {
                int i14 = i13 + 1;
                if (spannableStringBuilder.charAt(i14) == 10) {
                    spannableStringBuilder.delete(i13, i14);
                }
            }
        }
        if (spannableStringBuilder.length() > 0 && spannableStringBuilder.charAt(spannableStringBuilder.length() - 1) == 10) {
            spannableStringBuilder.delete(spannableStringBuilder.length() - 1, spannableStringBuilder.length());
        }
    }

    private void getEventTimes(TreeSet<Long> treeSet, boolean z) {
        boolean z3;
        boolean equals = "p".equals(this.tag);
        boolean equals2 = "div".equals(this.tag);
        if (z || equals || (equals2 && this.imageId != null)) {
            long j2 = this.startTimeUs;
            if (j2 != -9223372036854775807L) {
                treeSet.add(Long.valueOf(j2));
            }
            long j3 = this.endTimeUs;
            if (j3 != -9223372036854775807L) {
                treeSet.add(Long.valueOf(j3));
            }
        }
        if (this.children != null) {
            for (int i2 = 0; i2 < this.children.size(); i2++) {
                TtmlNode ttmlNode = this.children.get(i2);
                if (z || equals) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                ttmlNode.getEventTimes(treeSet, z3);
            }
        }
    }

    private static SpannableStringBuilder getRegionOutputText(String str, Map<String, Cue.Builder> map) {
        if (!map.containsKey(str)) {
            Cue.Builder builder = new Cue.Builder();
            builder.setText(new SpannableStringBuilder());
            map.put(str, builder);
        }
        return (SpannableStringBuilder) Assertions.checkNotNull(map.get(str).getText());
    }

    private void traverseForImage(long j2, String str, List<Pair<String, String>> list) {
        if (!"".equals(this.regionId)) {
            str = this.regionId;
        }
        if (!isActive(j2) || !"div".equals(this.tag) || this.imageId == null) {
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                getChild(i2).traverseForImage(j2, str, list);
            }
            return;
        }
        list.add(new Pair(str, this.imageId));
    }

    private void traverseForStyle(long j2, Map<String, TtmlStyle> map, Map<String, TtmlRegion> map2, String str, Map<String, Cue.Builder> map3) {
        String str2;
        if (isActive(j2)) {
            if ("".equals(this.regionId)) {
                str2 = str;
            } else {
                str2 = this.regionId;
            }
            Iterator<Map.Entry<String, Integer>> it = this.nodeEndsByRegion.entrySet().iterator();
            while (true) {
                int i2 = 0;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry next = it.next();
                String str3 = (String) next.getKey();
                if (this.nodeStartsByRegion.containsKey(str3)) {
                    i2 = this.nodeStartsByRegion.get(str3).intValue();
                }
                int intValue = ((Integer) next.getValue()).intValue();
                Map<String, Cue.Builder> map4 = map3;
                if (i2 != intValue) {
                    int i7 = i2;
                    applyStyleToOutput(map, (Cue.Builder) Assertions.checkNotNull(map3.get(str3)), i7, intValue, ((TtmlRegion) Assertions.checkNotNull(map2.get(str2))).verticalType);
                }
            }
            int i8 = 0;
            while (true) {
                Map<String, Cue.Builder> map5 = map3;
                if (i8 < getChildCount()) {
                    getChild(i8).traverseForStyle(j2, map, map2, str2, map5);
                    i8++;
                } else {
                    return;
                }
            }
        }
    }

    private void traverseForText(long j2, boolean z, String str, Map<String, Cue.Builder> map) {
        Map<String, Cue.Builder> map2;
        boolean z3;
        long j3;
        this.nodeStartsByRegion.clear();
        this.nodeEndsByRegion.clear();
        if (!GroupContract.Group.META_DATA.equals(this.tag)) {
            if (!"".equals(this.regionId)) {
                str = this.regionId;
            }
            String str2 = str;
            if (this.isTextNode && z) {
                getRegionOutputText(str2, map).append((CharSequence) Assertions.checkNotNull(this.text));
            } else if ("br".equals(this.tag) && z) {
                getRegionOutputText(str2, map).append(10);
            } else if (isActive(j2)) {
                for (Map.Entry next : map.entrySet()) {
                    this.nodeStartsByRegion.put((String) next.getKey(), Integer.valueOf(((CharSequence) Assertions.checkNotNull(((Cue.Builder) next.getValue()).getText())).length()));
                }
                boolean equals = "p".equals(this.tag);
                int i2 = 0;
                while (i2 < getChildCount()) {
                    TtmlNode child = getChild(i2);
                    if (z || equals) {
                        z3 = true;
                        map2 = map;
                        j3 = j2;
                    } else {
                        z3 = false;
                        j3 = j2;
                        map2 = map;
                    }
                    child.traverseForText(j3, z3, str2, map2);
                    i2++;
                    j2 = j3;
                    map = map2;
                }
                Map<String, Cue.Builder> map3 = map;
                if (equals) {
                    TtmlRenderUtil.endParagraph(getRegionOutputText(str2, map3));
                }
                for (Map.Entry next2 : map3.entrySet()) {
                    this.nodeEndsByRegion.put((String) next2.getKey(), Integer.valueOf(((CharSequence) Assertions.checkNotNull(((Cue.Builder) next2.getValue()).getText())).length()));
                }
            }
        }
    }

    public void addChild(TtmlNode ttmlNode) {
        if (this.children == null) {
            this.children = new ArrayList();
        }
        this.children.add(ttmlNode);
    }

    public TtmlNode getChild(int i2) {
        List<TtmlNode> list = this.children;
        if (list != null) {
            return list.get(i2);
        }
        throw new IndexOutOfBoundsException();
    }

    public int getChildCount() {
        List<TtmlNode> list = this.children;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public List<Cue> getCues(long j2, Map<String, TtmlStyle> map, Map<String, TtmlRegion> map2, Map<String, String> map3) {
        ArrayList arrayList = new ArrayList();
        traverseForImage(j2, this.regionId, arrayList);
        TreeMap treeMap = new TreeMap();
        long j3 = j2;
        traverseForText(j3, false, this.regionId, treeMap);
        Map<String, TtmlRegion> map4 = map2;
        TreeMap treeMap2 = treeMap;
        traverseForStyle(j3, map, map4, this.regionId, treeMap2);
        TreeMap treeMap3 = treeMap2;
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            String str = map3.get(pair.second);
            if (str != null) {
                byte[] decode = Base64.decode(str, 0);
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                TtmlRegion ttmlRegion = (TtmlRegion) Assertions.checkNotNull(map4.get(pair.first));
                arrayList2.add(new Cue.Builder().setBitmap(decodeByteArray).setPosition(ttmlRegion.position).setPositionAnchor(0).setLine(ttmlRegion.line, 0).setLineAnchor(ttmlRegion.lineAnchor).setSize(ttmlRegion.width).setBitmapHeight(ttmlRegion.height).setVerticalType(ttmlRegion.verticalType).build());
            }
        }
        for (Map.Entry entry : treeMap3.entrySet()) {
            TtmlRegion ttmlRegion2 = (TtmlRegion) Assertions.checkNotNull(map4.get(entry.getKey()));
            Cue.Builder builder = (Cue.Builder) entry.getValue();
            cleanUpText((SpannableStringBuilder) Assertions.checkNotNull(builder.getText()));
            builder.setLine(ttmlRegion2.line, ttmlRegion2.lineType);
            builder.setLineAnchor(ttmlRegion2.lineAnchor);
            builder.setPosition(ttmlRegion2.position);
            builder.setSize(ttmlRegion2.width);
            builder.setTextSize(ttmlRegion2.textSize, ttmlRegion2.textSizeType);
            builder.setVerticalType(ttmlRegion2.verticalType);
            arrayList2.add(builder.build());
        }
        return arrayList2;
    }

    public long[] getEventTimesUs() {
        TreeSet treeSet = new TreeSet();
        int i2 = 0;
        getEventTimes(treeSet, false);
        long[] jArr = new long[treeSet.size()];
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            jArr[i2] = ((Long) it.next()).longValue();
            i2++;
        }
        return jArr;
    }

    public String[] getStyleIds() {
        return this.styleIds;
    }

    public boolean isActive(long j2) {
        long j3 = this.startTimeUs;
        if (j3 == -9223372036854775807L && this.endTimeUs == -9223372036854775807L) {
            return true;
        }
        if (j3 <= j2 && this.endTimeUs == -9223372036854775807L) {
            return true;
        }
        if (j3 == -9223372036854775807L && j2 < this.endTimeUs) {
            return true;
        }
        if (j3 > j2 || j2 >= this.endTimeUs) {
            return false;
        }
        return true;
    }
}
