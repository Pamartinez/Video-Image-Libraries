package com.samsung.android.gallery.module.aiedit;

import A.a;
import A4.A;
import A4.d0;
import A8.C0544a;
import A8.C0545b;
import android.text.TextUtils;
import androidx.core.util.Pair;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.aiedit.RemasterDetector2;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaCacheLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MemLog;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.samsung.o3dp.lib3dphotography.utils.RemasterUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterDetector {
    public static final boolean SUPPORT = SdkConfig.atLeast(SdkConfig.SEM.U_MR1);
    public static final boolean SUPPORT_AUTO_TILT = PreferenceFeatures.OneUi6x.SUPPORT_PE_GEN_EDIT;
    public static final boolean SUPPORT_BEST_FACE = Features.isEnabled(Features.SUPPORT_BEST_FACE);
    public static final boolean SUPPORT_FLARE_DISTORTION = PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_FLARE_AND_DISTORTION;
    public static final boolean SUPPORT_REMASTER_DETECT;
    public static final boolean SUPPORT_UNIFIED;
    protected final String TAG = getClass().getSimpleName();
    private final ConcurrentHashMap<Integer, ArrayList<String>> mMap = new ConcurrentHashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        static final RemasterDetector INSTANCE = new RemasterDetector();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RemasterSefInfo {
        static final List<Integer> EMPTY = Collections.EMPTY_LIST;
        static final HashMap<String, Integer> map = new HashMap<String, Integer>() {
            {
                put("Colorize", 19);
                put("Demoire", 9);
                put("UwRetouch", 15);
                put("FlareRemoval", 17);
                put("O3DP", 25);
            }
        };

        public static List<Integer> toList(FileItemInterface fileItemInterface) {
            String sefString = SeApiCompat.getSefString(fileItemInterface.getPath(), SefInfo.REMASTER_INFO.keyName);
            if (sefString.isEmpty()) {
                return EMPTY;
            }
            return (List) map.entrySet().stream().filter(new a(sefString)).mapToInt(new C0545b(0)).boxed().collect(Collectors.toList());
        }
    }

    static {
        Features features = Features.SUPPORT_ALL_NEW_REMASTER;
        SUPPORT_UNIFIED = Features.isEnabled(features);
        SUPPORT_REMASTER_DETECT = Features.isEnabled(features);
    }

    private void deInit(VslMesDetectorCompat vslMesDetectorCompat) {
        if (vslMesDetectorCompat != null) {
            try {
                vslMesDetectorCompat.deInit(Features.isEnabled(Features.SUPPORT_ON_DEMAND_REMASTER_GIF));
            } catch (Exception e) {
                a.s(e, new StringBuilder("deInit failed. e="), this.TAG);
            }
        } else {
            Log.w(this.TAG, "deInit skip null");
        }
    }

    public static RemasterDetector getInstance() {
        if (SUPPORT_UNIFIED) {
            return RemasterDetector2.LazyHolder.INSTANCE;
        }
        return LazyHolder.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$unmarshalAnalyzedTag$1(JSONObject jSONObject, ArrayList arrayList, String str) {
        if (jSONObject.optString(str).equals(SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE)) {
            arrayList.add(str);
        }
    }

    public VslMesDetectorCompat createDetector() {
        try {
            VslMesDetectorCompat createVslMesDetectorCompat = SeApiCompat.createVslMesDetectorCompat("");
            if (!SUPPORT_UNIFIED) {
                createVslMesDetectorCompat.init(AppResources.getAppContext());
                return createVslMesDetectorCompat;
            } else if (createVslMesDetectorCompat.tryInit(AppResources.getAppContext())) {
                createVslMesDetectorCompat.setServicePurpose(RemasterUtil.REMASTER_SERVICE_PURPOSE_FOR_3D_PHOTO);
                return createVslMesDetectorCompat;
            } else {
                Log.w(this.TAG, "create failed");
                return null;
            }
        } catch (Error | Exception e) {
            Log.e((CharSequence) this.TAG, "create failed", e);
            return null;
        }
    }

    /* renamed from: detect */
    public ArrayList<String> lambda$detectImage$0(MediaItem mediaItem, String str) {
        String str2;
        TimeTickLog timeTickLog = new TimeTickLog();
        VslMesDetectorCompat createDetector = createDetector();
        ArrayList<String> arrayList = null;
        if (createDetector == null) {
            try {
                Log.e(this.TAG, "skip detect");
                ArrayList<String> arrayList2 = new ArrayList<>();
                deInit(createDetector);
                timeTickLog.tick();
                return arrayList2;
            } catch (Error | Exception e) {
                e = e;
                str2 = str;
                try {
                    String str3 = this.TAG;
                    Log.e(str3, "detect failed. e=" + e.getMessage());
                    deInit(createDetector);
                    timeTickLog.tick();
                    String str4 = this.TAG;
                    a.A(new Object[]{Long.valueOf(mediaItem.getFileId()), str2, arrayList, timeTickLog}, new StringBuilder("detect"), str4);
                    return arrayList;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    deInit(createDetector);
                    timeTickLog.tick();
                    throw th2;
                }
            }
        } else {
            timeTickLog.tick();
            str2 = str;
            try {
                if (createDetector.detectImage(ContentUri.getUri(mediaItem), mediaItem.getPath(), mediaItem.getDateTaken(), str2)) {
                    arrayList = unmarshalAnalyzedTag(createDetector.getTagAnalyzedFull());
                }
                timeTickLog.tick();
            } catch (Error | Exception e7) {
                e = e7;
                String str32 = this.TAG;
                Log.e(str32, "detect failed. e=" + e.getMessage());
                deInit(createDetector);
                timeTickLog.tick();
                String str42 = this.TAG;
                a.A(new Object[]{Long.valueOf(mediaItem.getFileId()), str2, arrayList, timeTickLog}, new StringBuilder("detect"), str42);
                return arrayList;
            }
            deInit(createDetector);
            timeTickLog.tick();
            String str422 = this.TAG;
            a.A(new Object[]{Long.valueOf(mediaItem.getFileId()), str2, arrayList, timeTickLog}, new StringBuilder("detect"), str422);
            return arrayList;
        }
    }

    public synchronized ArrayList<String> detectImage(MediaItem mediaItem, ArrayList<Integer> arrayList) {
        ArrayList<String> arrayList2;
        int fileHashCode = mediaItem.getFileHashCode();
        if (this.mMap.get(Integer.valueOf(fileHashCode)) != null) {
            return getEstimatorResult(fileHashCode);
        } else if (excludeIfPresent(mediaItem, arrayList)) {
            return new ArrayList<>();
        } else {
            String obj = arrayList.toString();
            MemLog memLog = new MemLog(this.TAG);
            if (MediaCacheLoader.SUPPORT_REMASTER) {
                arrayList2 = MediaCacheLoader.getInstance().computeRemasterTags(mediaItem, new C0544a(this, mediaItem, obj, 0));
            } else {
                arrayList2 = lambda$detectImage$0(mediaItem, obj);
            }
            memLog.check();
            if (arrayList2 != null) {
                this.mMap.put(Integer.valueOf(fileHashCode), arrayList2);
            }
            return getEstimatorResult(fileHashCode);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0033, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0034, code lost:
        r13 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        deInit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009a, code lost:
        throw r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009c, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0011, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:4:0x0008, B:28:0x007a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized androidx.core.util.Pair<java.lang.Boolean, java.lang.String> detectSingle(com.samsung.android.gallery.module.data.MediaItem r13, java.lang.String r14, java.lang.String r15) {
        /*
            r12 = this;
            java.lang.String r1 = "detect single fail= "
            java.lang.String r0 = "detect single done"
            monitor-enter(r12)
            r2 = 0
            if (r13 != 0) goto L_0x0015
            java.lang.String r13 = r12.TAG     // Catch:{ all -> 0x0011 }
            java.lang.String r14 = "detect single failed. null item"
            com.samsung.android.gallery.support.utils.Log.e(r13, r14)     // Catch:{ all -> 0x0011 }
            monitor-exit(r12)
            return r2
        L_0x0011:
            r0 = move-exception
            r13 = r0
            goto L_0x009b
        L_0x0015:
            com.samsung.android.gallery.support.utils.MemLog r3 = new com.samsung.android.gallery.support.utils.MemLog     // Catch:{ all -> 0x0011 }
            java.lang.String r4 = r12.TAG     // Catch:{ all -> 0x0011 }
            r3.<init>(r4)     // Catch:{ all -> 0x0011 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0011 }
            com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat r6 = r12.createDetector()     // Catch:{ all -> 0x0011 }
            if (r6 != 0) goto L_0x0039
            java.lang.String r13 = r12.TAG     // Catch:{ Exception -> 0x0036 }
            java.lang.String r14 = "skip detect"
            com.samsung.android.gallery.support.utils.Log.e(r13, r14)     // Catch:{ Exception -> 0x0036 }
            r12.deInit(r6)     // Catch:{ all -> 0x0011 }
            monitor-exit(r12)
            return r2
        L_0x0033:
            r0 = move-exception
            r13 = r0
            goto L_0x0097
        L_0x0036:
            r0 = move-exception
            r13 = r0
            goto L_0x007a
        L_0x0039:
            android.net.Uri r7 = com.samsung.android.gallery.module.data.ContentUri.getUri(r13)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r8 = r13.getPath()     // Catch:{ Exception -> 0x0036 }
            long r9 = r13.getDateTaken()     // Catch:{ Exception -> 0x0036 }
            r11 = r14
            boolean r14 = r6.detectImage(r7, r8, r9, r11)     // Catch:{ Exception -> 0x0036 }
            if (r14 == 0) goto L_0x0054
            java.lang.String r14 = r6.getTagAnalyzedFull()     // Catch:{ Exception -> 0x0036 }
            androidx.core.util.Pair r2 = r12.unmarshalAnalyzedTagAndValue(r15, r14)     // Catch:{ Exception -> 0x0036 }
        L_0x0054:
            java.lang.String r14 = r12.TAG     // Catch:{ Exception -> 0x0036 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0036 }
            r15.<init>(r0)     // Catch:{ Exception -> 0x0036 }
            long r7 = r13.getFileId()     // Catch:{ Exception -> 0x0036 }
            java.lang.Long r13 = java.lang.Long.valueOf(r7)     // Catch:{ Exception -> 0x0036 }
            java.lang.Long r0 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x0036 }
            java.lang.Object[] r13 = new java.lang.Object[]{r13, r2, r0}     // Catch:{ Exception -> 0x0036 }
            java.lang.String r13 = com.samsung.android.gallery.support.utils.Logger.vt((java.lang.Object[]) r13)     // Catch:{ Exception -> 0x0036 }
            r15.append(r13)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r13 = r15.toString()     // Catch:{ Exception -> 0x0036 }
            com.samsung.android.gallery.support.utils.Log.d(r14, r13)     // Catch:{ Exception -> 0x0036 }
            goto L_0x008f
        L_0x007a:
            java.lang.String r14 = r12.TAG     // Catch:{ all -> 0x0033 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ all -> 0x0033 }
            r15.<init>(r1)     // Catch:{ all -> 0x0033 }
            java.lang.String r13 = r13.getMessage()     // Catch:{ all -> 0x0033 }
            r15.append(r13)     // Catch:{ all -> 0x0033 }
            java.lang.String r13 = r15.toString()     // Catch:{ all -> 0x0033 }
            com.samsung.android.gallery.support.utils.Log.e(r14, r13)     // Catch:{ all -> 0x0033 }
        L_0x008f:
            r12.deInit(r6)     // Catch:{ all -> 0x0011 }
            r3.check()     // Catch:{ all -> 0x0011 }
            monitor-exit(r12)
            return r2
        L_0x0097:
            r12.deInit(r6)     // Catch:{ all -> 0x0011 }
            throw r13     // Catch:{ all -> 0x0011 }
        L_0x009b:
            monitor-exit(r12)     // Catch:{ all -> 0x0011 }
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.aiedit.RemasterDetector.detectSingle(com.samsung.android.gallery.module.data.MediaItem, java.lang.String, java.lang.String):androidx.core.util.Pair");
    }

    public boolean excludeIfPresent(MediaItem mediaItem, ArrayList<Integer> arrayList) {
        List<Integer> list = RemasterSefInfo.toList(mediaItem);
        if (list == null || list.isEmpty()) {
            return false;
        }
        int size = arrayList.size();
        arrayList.removeIf(new d0(1, list));
        if (size != arrayList.size()) {
            String str = this.TAG;
            Log.d(str, "detect exclude" + Logger.v(Long.valueOf(mediaItem.getFileId()), arrayList, list));
        }
        return arrayList.isEmpty();
    }

    public synchronized ArrayList<String> getEstimatorResult(int i2) {
        return this.mMap.getOrDefault(Integer.valueOf(i2), new ArrayList());
    }

    public ArrayList<String> unmarshalAnalyzedTag(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject optJSONObject = new JSONObject(str).optJSONObject("EstimatorResult");
                if (optJSONObject != null) {
                    optJSONObject.keys().forEachRemaining(new A(1, (Object) optJSONObject, (Object) arrayList));
                    return arrayList;
                }
            } catch (JSONException e) {
                String str2 = this.TAG;
                Log.e(str2, "parseAnalyzedTag failed. e=" + e.getMessage());
            }
        }
        return arrayList;
    }

    public Pair<Boolean, String> unmarshalAnalyzedTagAndValue(String str, String str2) {
        boolean z = false;
        String str3 = "";
        if (!TextUtils.isEmpty(str2)) {
            try {
                JSONObject optJSONObject = new JSONObject(str2).optJSONObject("EstimatorResult");
                if (optJSONObject != null) {
                    z = optJSONObject.optBoolean(str, false);
                }
                JSONObject optJSONObject2 = new JSONObject(str2).optJSONObject("EstimatorValue");
                if (optJSONObject2 != null) {
                    str3 = optJSONObject2.optString(str);
                }
            } catch (JSONException e) {
                String str4 = this.TAG;
                Log.e(str4, "parseAnalyzedTagAndValue failed. e=" + e.getMessage());
            }
        }
        return new Pair<>(Boolean.valueOf(z), str3);
    }

    public void close() {
    }

    public void init() {
    }

    public void open() {
    }

    public void releaseIfExist() {
    }

    public void releaseOnStop() {
    }
}
