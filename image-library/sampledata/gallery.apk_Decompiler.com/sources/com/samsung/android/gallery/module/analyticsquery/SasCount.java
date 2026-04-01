package com.samsung.android.gallery.module.analyticsquery;

import A4.C0367b;
import B8.d;
import C3.p;
import D3.i;
import D8.b;
import D8.c;
import N2.j;
import android.content.SharedPreferences;
import android.util.SparseArray;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.mobileservice.profile.Privacy;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SasCount {
    public static final boolean SUPPORT_CLUSTER = SdkConfig.atLeast(SdkConfig.SEM.U);
    private final SparseArray<Object> list = new SparseArray<>();
    private final ArrayList<String> logs = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AnalysisServiceHolder {
        static HashMap<String, Integer> map = new HashMap<String, Integer>() {
            {
                put("Tag", 10600);
                put("OCR", 10610);
                put("Face", 10620);
                put("Pet", 10630);
                put("MediaSearch", 10640);
                put("VideoScan", 10660);
                if (SdkConfig.atLeast(SdkConfig.SEM.B_MR5)) {
                    put("Video_Face", 10670);
                    put("Video_MediaSearch", 10680);
                    put("MFC", 10690);
                }
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DocumentIndex {
        static final HashMap<String, Integer> map = new HashMap<String, Integer>() {
            {
                put("qr_codes", 10400);
                put("barcodes", 10401);
                put("receipts", 10402);
                put("business_cards", 10403);
                put("credit_cards", 10404);
                put("ids", 10405);
                put("passports", 10406);
                put("legal_documents", 10407);
                put("books", 10408);
                put("maps", 10409);
                put(Privacy.KEY_NOTES, 10410);
                put("presentations", 10411);
                put("other_documents", 10412);
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Keys {
        static final ArrayList<int[]> rangeList = new ArrayList<int[]>() {
            {
                add(new int[]{10000, 10018});
                add(new int[]{10100, 10113});
                if (SasCount.SUPPORT_CLUSTER) {
                    add(new int[]{10200, 10218});
                }
                add(new int[]{10300, 10301});
                add(new int[]{10400, 10412});
                add(new int[]{10600, 10602});
                add(new int[]{10610, 10612});
                add(new int[]{10620, 10622});
                SdkConfig.SEM sem = SdkConfig.SEM.B_MR5;
                if (SdkConfig.lessThan(sem)) {
                    add(new int[]{10630, 10632});
                }
                add(new int[]{10640, 10642});
                add(new int[]{10660, 10664});
                if (SdkConfig.atLeast(sem)) {
                    add(new int[]{10670, 10672});
                    add(new int[]{10680, 10682});
                    add(new int[]{10690, 10692});
                }
                add(new int[]{10700, 10704});
                add(new int[]{10800, 10801});
                add(new int[]{10811, 10817});
                add(new int[]{10900, 10900});
                add(new int[]{ErrorCodeConvertor.CLOUD_UID_VALIDATION, ErrorCodeConvertor.CLOUD_DEVICE_ID_REQUIRED});
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PackageKeyHolder {
        static final HashMap<String, Integer> map = new HashMap<String, Integer>() {
            {
                put("com.sec.android.app.vepreload", Integer.valueOf(ErrorCodeConvertor.CLOUD_UID_VALIDATION));
                put(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, Integer.valueOf(ErrorCodeConvertor.CLOUD_UID_VALIDATION2));
                put("com.samsung.android.video", 19002);
                put("com.samsung.app.newtrim", 19003);
                put("com.samsung.android.smartmirroring", Integer.valueOf(ErrorCodeConvertor.CLOUD_DEVICE_ID_REQUIRED));
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RelationshipIndex {
        static final HashMap<String, Integer> map = new HashMap<String, Integer>() {
            {
                put("me", 10205);
                put("mother", 10207);
                put("father", 10208);
                put("parents", 10209);
                put("sister", 10210);
                put("brother", 10211);
                put("wife", 10212);
                put("husband", 10213);
                put("spouse", 10214);
                put("daughter", 10215);
                put("son", 10216);
                put("child", 10217);
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ScreenCaptureType {
        static final HashMap<String, Integer> types = new HashMap<String, Integer>() {
            {
                put("cap_boarding_pass", 10811);
                put("cap_coupon", 10812);
                put("cap_shopping", 10813);
                put("cap_location", 10814);
                put("cap_barcode_qr", 10815);
                put("cap_communication", 10816);
                put("cap_social_media", 10817);
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ShotModeIndex {
        static final HashMap<String, Integer> map = new HashMap<String, Integer>() {
            {
                put("motion_photo", 10001);
                put("live_focus", 10002);
                put("GIF", 10003);
                put("panorama", 10004);
                put("document_scan", 10005);
                put("burst_shot", 10006);
                put("360_photo", 10007);
                put("Single Taken", 10008);
                put("pro", 10009);
                put("raw", 10012);
                put("sticker", 10013);
                put("selfie", 10014);
                put("long_exposure", 10015);
                put("3d_capture_vst", 10016);
                put("3d_capture_mobile", 10017);
                put("live_effect_photo", 10018);
                put("slow_motion", 10101);
                put("super_slow_mo", 10102);
                put("360_video", 10103);
                put("pro_video", 10104);
                put("Dual_Recording_Info", 10105);
                put("hyperlapse", 10106);
                put("3d_capture_video", 10108);
                put("live_effect_video", 10109);
                put("instant_slow_mo", 10110);
                put("log_video", 10111);
                put("apv_video", 10112);
                put("portrait_video", 10113);
            }
        };
    }

    public static List<String> keys() {
        ArrayList arrayList = new ArrayList();
        Keys.rangeList.forEach(new d(arrayList, 2));
        return arrayList;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$apply$18(SharedPreferences.Editor editor) {
        int size = this.list.size();
        for (int i2 = 0; i2 < size; i2++) {
            int keyAt = this.list.keyAt(i2);
            Object valueAt = this.list.valueAt(i2);
            if (valueAt instanceof Long) {
                editor.putLong(String.valueOf(keyAt), ((Long) valueAt).longValue());
            } else if (valueAt instanceof Integer) {
                editor.putInt(String.valueOf(keyAt), ((Integer) valueAt).intValue());
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$keys$0(ArrayList arrayList, int[] iArr) {
        for (int i2 = iArr[0]; i2 <= iArr[1]; i2++) {
            arrayList.add(String.valueOf(i2));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$logCluster$2(HashMap hashMap, String str, Integer num) {
        int i2;
        Integer num2 = (Integer) hashMap.get(str);
        SparseArray<Object> sparseArray = this.list;
        int intValue = num.intValue();
        if (num2 == null || num2.intValue() <= 0) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        sparseArray.append(intValue, Integer.valueOf(i2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$logDocument$5(HashMap hashMap, String str, Integer num) {
        int i2;
        Integer num2 = (Integer) hashMap.get(str);
        SparseArray<Object> sparseArray = this.list;
        int intValue = num.intValue();
        if (num2 == null) {
            i2 = 0;
        } else {
            i2 = num2.intValue();
        }
        sparseArray.append(intValue, Integer.valueOf(i2));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$logPackageStatus$8(Map.Entry entry) {
        return Logger.getSimpleName((String) entry.getKey()) + "=" + entry.getValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$logPackageStatus$9(String str, Integer num) {
        Integer num2 = PackageKeyHolder.map.get(str);
        if (num2 != null) {
            this.list.append(num2.intValue(), num);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$logScreenshot$3(HashMap hashMap, int i2, String str, Integer num) {
        int i7;
        Integer num2 = (Integer) hashMap.get(str);
        SparseArray<Object> sparseArray = this.list;
        int intValue = num.intValue();
        if (num2 == null) {
            i7 = 0;
        } else {
            i7 = Math.round((((float) num2.intValue()) * 100.0f) / ((float) i2));
        }
        sparseArray.append(intValue, Integer.valueOf(i7));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$logScreenshot$4(String str, Integer num) {
        this.list.append(num.intValue(), 0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$logSearchAnalysisStatus$6(HashMap hashMap, String str, Integer num) {
        SearchAnalysisStatus searchAnalysisStatus = (SearchAnalysisStatus) hashMap.get(str);
        if (searchAnalysisStatus != null) {
            this.list.append(num.intValue(), Integer.valueOf(searchAnalysisStatus.n));
            this.list.append(num.intValue() + 1, Integer.valueOf(searchAnalysisStatus.np));
            this.list.append(num.intValue() + 2, Long.valueOf(searchAnalysisStatus.npt));
            if ("VideoScan".equals(str)) {
                this.list.append(num.intValue() + 3, Integer.valueOf(searchAnalysisStatus.sp));
                this.list.append(num.intValue() + 4, Integer.valueOf(searchAnalysisStatus.op));
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$logSearchAnalysisStatus$7(Map.Entry entry) {
        return ((String) entry.getKey()).substring(0, 2) + entry.getValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$logShotMode$1(HashMap hashMap, String str, Integer num) {
        int i2;
        Integer num2 = (Integer) hashMap.get(str);
        SparseArray<Object> sparseArray = this.list;
        int intValue = num.intValue();
        if (num2 == null) {
            i2 = 0;
        } else {
            i2 = num2.intValue();
        }
        sparseArray.append(intValue, Integer.valueOf(i2));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$summaryHtmlOfSaS$16(Map.Entry entry) {
        return "<tr> <td>" + ((String) entry.getKey()) + "</td> " + ((SearchAnalysisStatus) entry.getValue()).toHtmlTableRow() + "</tr>";
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$summaryHtmlOfSaS$17(Map.Entry entry) {
        return "<tr> <td>" + ((String) entry.getKey()) + "</td> " + ((SearchAnalysisStatus) entry.getValue()).toHtmlTableRow() + "</tr>";
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$summaryOfSaS$12(Map.Entry entry) {
        return ((String) entry.getKey()).substring(0, 2) + ((SearchAnalysisStatus) entry.getValue()).toSimpleString();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$toString$19(Map.Entry entry) {
        return ((String) entry.getKey()) + "=" + entry.getValue();
    }

    public void apply() {
        GalleryPreference.getInstanceAnalytics().saveState((Consumer<SharedPreferences.Editor>) new C0367b(24, this));
        Log.d("SasCount", "apply" + Logger.v(Integer.valueOf(this.list.size())) + "\n" + this.list);
    }

    public SasCount logCluster() {
        if (SUPPORT_CLUSTER) {
            long currentTimeMillis = System.currentTimeMillis();
            long faceClusterCount = AnalyticsQuery.getFaceClusterCount();
            long petClusterCount = AnalyticsQuery.getPetClusterCount();
            long namedFaceClusterCount = AnalyticsQuery.getNamedFaceClusterCount();
            long namedPetClusterCount = AnalyticsQuery.getNamedPetClusterCount();
            long namedRelationshipCount = AnalyticsQuery.getNamedRelationshipCount();
            long hiddenFaceClusterCount = AnalyticsQuery.getHiddenFaceClusterCount();
            HashMap<String, Integer> relationshipCount = AnalyticsQuery.getRelationshipCount();
            long j2 = currentTimeMillis;
            ArrayList<String> arrayList = this.logs;
            long j3 = faceClusterCount;
            arrayList.add("cluster" + Logger.vt(Long.valueOf(j3), Long.valueOf(petClusterCount), Long.valueOf(namedFaceClusterCount), Long.valueOf(namedPetClusterCount), Long.valueOf(namedRelationshipCount), Long.valueOf(hiddenFaceClusterCount), Logger.getEncodedString(toString(relationshipCount)), Long.valueOf(j2)));
            this.list.append(10200, Long.valueOf(j3));
            this.list.append(10201, Long.valueOf(petClusterCount));
            this.list.append(10202, Long.valueOf(namedFaceClusterCount));
            this.list.append(10203, Long.valueOf(namedPetClusterCount));
            this.list.append(10204, Long.valueOf(namedRelationshipCount));
            this.list.append(10206, Long.valueOf(hiddenFaceClusterCount));
            RelationshipIndex.map.forEach(new D8.d(this, relationshipCount, 0));
        }
        return this;
    }

    public SasCount logDocument() {
        long currentTimeMillis = System.currentTimeMillis();
        HashMap<String, Integer> documentCount = AnalyticsQuery.getDocumentCount();
        ArrayList<String> arrayList = this.logs;
        arrayList.add("document" + Logger.vt(toString(documentCount), Long.valueOf(currentTimeMillis)));
        DocumentIndex.map.forEach(new D8.d(this, documentCount, 3));
        return this;
    }

    public SasCount logImage() {
        long currentTimeMillis = System.currentTimeMillis();
        int imageCount = (int) AnalyticsQuery.getImageCount();
        int aiEditImageCount = (int) AnalyticsQuery.getAiEditImageCount();
        int recentlyEditImageCount = (int) AnalyticsQuery.getRecentlyEditImageCount();
        ArrayList<String> arrayList = this.logs;
        arrayList.add("image" + Logger.vt(Integer.valueOf(imageCount), Integer.valueOf(aiEditImageCount), Integer.valueOf(recentlyEditImageCount), Long.valueOf(currentTimeMillis)));
        this.list.append(10000, Integer.valueOf(imageCount));
        this.list.append(10010, Integer.valueOf(aiEditImageCount));
        this.list.append(10011, Integer.valueOf(recentlyEditImageCount));
        return this;
    }

    public SasCount logLocation() {
        long currentTimeMillis = System.currentTimeMillis();
        int localityCount = (int) AnalyticsQuery.getLocalityCount();
        int countryCount = (int) AnalyticsQuery.getCountryCount();
        ArrayList<String> arrayList = this.logs;
        arrayList.add("location" + Logger.vt(Integer.valueOf(localityCount), Integer.valueOf(countryCount), Long.valueOf(currentTimeMillis)));
        this.list.append(10300, Integer.valueOf(localityCount));
        this.list.append(10301, Integer.valueOf(countryCount));
        return this;
    }

    public SasCount logMisc() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean isEnabled = PreferenceFeatures.isEnabled(PreferenceFeatures.TimelineSimilarPhotoMode);
        ArrayList<String> arrayList = this.logs;
        arrayList.add("misc" + Logger.vt(Integer.valueOf(isEnabled ? 1 : 0), Long.valueOf(currentTimeMillis)));
        this.list.append(10900, Integer.valueOf(isEnabled));
        return this;
    }

    public SasCount logPackageStatus() {
        long currentTimeMillis = System.currentTimeMillis();
        HashMap<String, Integer> loadPackageStatus = AnalyticsQuery.loadPackageStatus((String[]) PackageKeyHolder.map.keySet().toArray(new String[0]));
        loadPackageStatus.forEach(new c(this, 1));
        ArrayList<String> arrayList = this.logs;
        StringBuilder k = j.k("package [", (String) loadPackageStatus.entrySet().stream().map(new i(7)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)), "] +");
        k.append(System.currentTimeMillis() - currentTimeMillis);
        arrayList.add(k.toString());
        return this;
    }

    public SasCount logScreenshot() {
        long currentTimeMillis = System.currentTimeMillis();
        int screenShotCount = (int) AnalyticsQuery.getScreenShotCount();
        int screenRecordingCount = (int) AnalyticsQuery.getScreenRecordingCount();
        ArrayList<String> arrayList = this.logs;
        arrayList.add("screenshot" + Logger.vt(Integer.valueOf(screenShotCount), Integer.valueOf(screenRecordingCount), Long.valueOf(currentTimeMillis)));
        this.list.append(10800, Integer.valueOf(screenShotCount));
        this.list.append(10801, Integer.valueOf(screenRecordingCount));
        if (screenShotCount > 0) {
            long currentTimeMillis2 = System.currentTimeMillis();
            HashMap<String, Integer> loadScreenCaptureTypeCount = AnalyticsQuery.loadScreenCaptureTypeCount();
            ScreenCaptureType.types.forEach(new b(this, (HashMap) loadScreenCaptureTypeCount, screenShotCount));
            ArrayList<String> arrayList2 = this.logs;
            arrayList2.add("screen-capture " + loadScreenCaptureTypeCount + " +" + (System.currentTimeMillis() - currentTimeMillis2));
            return this;
        }
        ScreenCaptureType.types.forEach(new c(this, 0));
        return this;
    }

    public SasCount logSearchAnalysisStatus() {
        long currentTimeMillis = System.currentTimeMillis();
        HashMap<String, SearchAnalysisStatus> loadSearchAnalysisStatus = AnalyticsQuery.loadSearchAnalysisStatus();
        AnalysisServiceHolder.map.forEach(new D8.d(this, loadSearchAnalysisStatus, 1));
        ArrayList<String> arrayList = this.logs;
        arrayList.add("SaS" + Logger.vt((String) loadSearchAnalysisStatus.entrySet().stream().map(new i(6)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)), Long.valueOf(currentTimeMillis)));
        return this;
    }

    public SasCount logShotMode() {
        long currentTimeMillis = System.currentTimeMillis();
        HashMap<String, Integer> shotModeCount = AnalyticsQuery.getShotModeCount();
        ArrayList<String> arrayList = this.logs;
        arrayList.add("shotMode" + Logger.vt(toString(shotModeCount), Long.valueOf(currentTimeMillis)));
        ShotModeIndex.map.forEach(new D8.d(this, shotModeCount, 2));
        return this;
    }

    public SasCount logStory() {
        long currentTimeMillis = System.currentTimeMillis();
        int tripStoryCount = (int) AnalyticsQuery.getTripStoryCount();
        int manualStoryCount = (int) AnalyticsQuery.getManualStoryCount();
        int onDemandStoryCount = (int) AnalyticsQuery.getOnDemandStoryCount();
        int moreStoryCount = (int) AnalyticsQuery.getMoreStoryCount();
        int favoriteStoryCount = (int) AnalyticsQuery.getFavoriteStoryCount();
        ArrayList<String> arrayList = this.logs;
        arrayList.add("story" + Logger.vt(Integer.valueOf(tripStoryCount), Integer.valueOf(manualStoryCount), Integer.valueOf(onDemandStoryCount), Integer.valueOf(moreStoryCount), Integer.valueOf(favoriteStoryCount), Long.valueOf(currentTimeMillis)));
        this.list.append(10700, Integer.valueOf(tripStoryCount));
        this.list.append(10701, Integer.valueOf(manualStoryCount));
        this.list.append(10702, Integer.valueOf(onDemandStoryCount));
        this.list.append(10703, Integer.valueOf(moreStoryCount));
        this.list.append(10704, Integer.valueOf(favoriteStoryCount));
        return this;
    }

    public SasCount logVideo() {
        long currentTimeMillis = System.currentTimeMillis();
        int videoCount = (int) AnalyticsQuery.getVideoCount();
        int recentlyEditVideoCount = (int) AnalyticsQuery.getRecentlyEditVideoCount();
        ArrayList<String> arrayList = this.logs;
        arrayList.add("video" + Logger.vt(Integer.valueOf(videoCount), Integer.valueOf(recentlyEditVideoCount), Long.valueOf(currentTimeMillis)));
        this.list.append(10100, Integer.valueOf(videoCount));
        this.list.append(10107, Integer.valueOf(recentlyEditVideoCount));
        return this;
    }

    public SasCount summarize() {
        long currentTimeMillis = System.currentTimeMillis();
        SasCount logPackageStatus = logImage().logVideo().logShotMode().logCluster().logLocation().logStory().logScreenshot().logDocument().logSearchAnalysisStatus().logMisc().logPackageStatus();
        Log.d("SasCount", "summarize" + Logger.vt(Integer.valueOf(this.list.size()), Long.valueOf(currentTimeMillis)) + "");
        return logPackageStatus;
    }

    public String summaryHtmlOfSaS() {
        String str;
        HashMap<String, SearchAnalysisStatus> loadSearchAnalysisStatus = AnalyticsQuery.loadSearchAnalysisStatus();
        if (SdkConfig.atLeast(SdkConfig.SEM.B_MR5) && !loadSearchAnalysisStatus.containsKey("Pet")) {
            Optional.ofNullable(loadSearchAnalysisStatus.remove("Face")).ifPresent(new p(4, loadSearchAnalysisStatus));
        }
        HashMap hashMap = new HashMap();
        Optional.ofNullable(loadSearchAnalysisStatus.remove("Video_Face")).ifPresent(new p(5, hashMap));
        Optional.ofNullable(loadSearchAnalysisStatus.remove("Video_MediaSearch")).ifPresent(new p(6, hashMap));
        String m = C0212a.m("Search analysis status (image)<table class=\"equal-width-table\"><tr> <th>Solution</th> <th>Total</th> <th>Analyzed</th> <th>Ratio</th> </tr>", (String) loadSearchAnalysisStatus.entrySet().stream().map(new i(4)).collect(Collectors.joining("\n")), "</table>");
        if (!hashMap.isEmpty()) {
            str = C0212a.m("<br>Search analysis status (video)<table class=\"equal-width-table\"><tr> <th>Solution</th> <th>Total</th> <th>Analyzed</th> <th>Ratio</th> </tr>", (String) hashMap.entrySet().stream().map(new i(5)).collect(Collectors.joining("\n")), "</table>");
        } else {
            str = "";
        }
        return C0212a.m("<style>.equal-width-table {table-layout:fixed; width: 100%; }.equal-width-table th,.equal-width-table td {width: calc(100%/3); border: 1px solid black; text-align: center; font-size: 12px}</style>", m, str);
    }

    public String summaryOfSaS() {
        HashMap<String, SearchAnalysisStatus> loadSearchAnalysisStatus = AnalyticsQuery.loadSearchAnalysisStatus();
        Optional.ofNullable(loadSearchAnalysisStatus.remove("Video_Face")).ifPresent(new p(2, loadSearchAnalysisStatus));
        Optional.ofNullable(loadSearchAnalysisStatus.remove("Video_MediaSearch")).ifPresent(new p(3, loadSearchAnalysisStatus));
        return (String) loadSearchAnalysisStatus.entrySet().stream().map(new i(3)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
    }

    public String toString(HashMap<String, Integer> hashMap) {
        return (String) hashMap.entrySet().stream().map(new i(2)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
    }
}
