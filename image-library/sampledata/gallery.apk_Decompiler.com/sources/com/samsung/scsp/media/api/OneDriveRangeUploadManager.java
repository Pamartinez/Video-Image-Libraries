package com.samsung.scsp.media.api;

import Bd.C0726b;
import O3.r;
import android.util.Pair;
import com.samsung.android.sum.core.filter.n;
import com.samsung.scsp.common.ContextFactory;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.SContext;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.api.Api;
import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.SCHashMap;
import com.samsung.scsp.framework.core.identity.ScspCorePreferences;
import com.samsung.scsp.framework.core.listeners.Listeners;
import com.samsung.scsp.framework.core.util.StringUtil;
import com.samsung.scsp.media.Media;
import com.samsung.scsp.media.MediaApiSpec;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import com.samsung.scsp.media.api.database.url.OneDriveUpdateUrlDbManager;
import com.samsung.scsp.media.api.database.url.OneDriveUploadUrlDbManager;
import com.samsung.scsp.media.api.database.url.OneDriveUrlDbManager;
import com.samsung.scsp.media.file.FileApiContract;
import com.samsung.scsp.media.nde.NDEApiHelper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneDriveRangeUploadManager {
    private static final String PARAM_IS_RESUMABLE = "is_resumable";
    private static final String PARAM_UPLOAD_START_TIME = "upload_start_time";
    private static final String PARAM_UPLOAD_TYPE = "upload_type";
    private static final long RANGE_START_UNKNOWN_VALUE = -1;
    private static final long RANGE_START_UPLOAD_COMPLETED_VALUE = -2;
    private String hashedUserId;
    private Logger logger = Logger.get("OneDriveRangeUploadManager");
    private Api mediaApi;
    private NDEApiHelper ndeApiHelper;
    private Map<UploadType, OneDriveUrlDbManager> onedriveDBManagerMap;
    private SContext scontext = SContext.getInstance();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum UploadType {
        Create,
        Update
    }

    public OneDriveRangeUploadManager(Api api) {
        this.mediaApi = api;
        this.ndeApiHelper = new NDEApiHelper(SContext.getInstance());
        initialize();
    }

    private void deleteUrlInfo(Media media, SCHashMap sCHashMap) {
        if (sCHashMap != null) {
            OneDriveUrlDbManager urlDBManager = getUrlDBManager(sCHashMap);
            if (StringUtil.isEmpty(getItemOriginalUploadUrl(sCHashMap)) || StringUtil.isEmpty(media.originalBinaryHash)) {
                urlDBManager.deleteDefaultUrlInfo(media.hash);
            } else {
                urlDBManager.deleteNDEUrlInfo(media.hash, media.originalBinaryHash);
            }
        }
    }

    private Media getAndValidateMediaToUpload(ApiContext apiContext) {
        Media media = (Media) apiContext.content;
        if (media != null) {
            return media;
        }
        throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "no upload media found");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: com.samsung.scsp.media.api.database.url.OneDriveUrlInfo} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean getStatusOneDriveUploadUrl(com.samsung.scsp.framework.core.api.ApiContext r9, com.samsung.scsp.framework.core.api.SCHashMap r10) {
        /*
            r8 = this;
            com.samsung.scsp.media.Media r0 = r8.getAndValidateMediaToUpload(r9)
            com.samsung.scsp.media.nde.NDEApiHelper r1 = r8.ndeApiHelper
            boolean r1 = r1.isNDEUploadApi(r9)
            com.samsung.scsp.media.api.database.url.OneDriveUrlDbManager r2 = r8.getUrlDBManager(r10)
            r3 = 0
            if (r1 == 0) goto L_0x0029
            java.lang.String r4 = r0.hash
            java.lang.String r0 = r0.originalBinaryHash
            java.lang.String r5 = r8.hashedUserId
            android.util.Pair r0 = r2.getUrlForNDEType(r4, r0, r5)
            if (r0 == 0) goto L_0x0027
            java.lang.Object r2 = r0.first
            r3 = r2
            com.samsung.scsp.media.api.database.url.OneDriveUrlInfo r3 = (com.samsung.scsp.media.api.database.url.OneDriveUrlInfo) r3
            java.lang.Object r0 = r0.second
            com.samsung.scsp.media.api.database.url.OneDriveUrlInfo r0 = (com.samsung.scsp.media.api.database.url.OneDriveUrlInfo) r0
            goto L_0x0034
        L_0x0027:
            r0 = r3
            goto L_0x0034
        L_0x0029:
            java.lang.String r0 = r0.hash
            java.lang.String r4 = r8.hashedUserId
            com.samsung.scsp.media.api.database.url.OneDriveUrlInfo r0 = r2.getUrlInfoForDefaultType(r0, r4)
            r7 = r3
            r3 = r0
            r0 = r7
        L_0x0034:
            r2 = 0
            if (r3 == 0) goto L_0x005e
            java.lang.String r4 = r3.url
            boolean r4 = com.samsung.scsp.framework.core.util.StringUtil.isEmpty(r4)
            if (r4 != 0) goto L_0x005e
            java.lang.String r4 = r3.url
            long r5 = r3.expirationDate
            android.util.Pair r4 = r8.getUploadSessionInfo(r9, r4, r5)
            java.lang.String r5 = "url"
            java.lang.String r3 = r3.url
            r10.put(r5, r3)
            java.lang.String r3 = "RANGE_START"
            java.lang.Object r5 = r4.second
            r10.put(r3, r5)
            java.lang.Object r3 = r4.first
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            goto L_0x005f
        L_0x005e:
            r3 = r2
        L_0x005f:
            if (r3 == 0) goto L_0x008c
            if (r1 == 0) goto L_0x008c
            if (r0 == 0) goto L_0x008d
            java.lang.String r3 = r0.url
            boolean r3 = com.samsung.scsp.framework.core.util.StringUtil.isEmpty(r3)
            if (r3 != 0) goto L_0x008d
            java.lang.String r2 = r0.url
            long r3 = r0.expirationDate
            android.util.Pair r9 = r8.getUploadSessionInfo(r9, r2, r3)
            java.lang.String r2 = "originalUrl"
            java.lang.String r0 = r0.url
            r10.put(r2, r0)
            java.lang.String r0 = "original_upload_range_start"
            java.lang.Object r2 = r9.second
            r10.put(r0, r2)
            java.lang.Object r9 = r9.first
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r2 = r9.booleanValue()
            goto L_0x008d
        L_0x008c:
            r2 = r3
        L_0x008d:
            com.samsung.scsp.error.Logger r8 = r8.logger
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "getStatusOneDriveUploadUrl: "
            r9.<init>(r10)
            r9.append(r2)
            java.lang.String r10 = ", "
            r9.append(r10)
            r9.append(r1)
            java.lang.String r9 = r9.toString()
            r8.i(r9)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.scsp.media.api.OneDriveRangeUploadManager.getStatusOneDriveUploadUrl(com.samsung.scsp.framework.core.api.ApiContext, com.samsung.scsp.framework.core.api.SCHashMap):boolean");
    }

    private Pair<Boolean, Long> getUploadSessionInfo(ApiContext apiContext, String str, long j2) {
        boolean[] zArr = {false};
        long[] jArr = {-1};
        apiContext.name = MediaApiSpec.ONEDRIVE_URL_CHECK;
        apiContext.parameters.put("url", str);
        Listeners listeners = new Listeners();
        listeners.responseListener = new r(j2, zArr, jArr);
        try {
            this.mediaApi.execute(apiContext, listeners);
        } catch (ScspException unused) {
            zArr[0] = false;
            jArr[0] = -1;
        }
        Pair<Boolean, Long> pair = new Pair<>(Boolean.valueOf(zArr[0]), Long.valueOf(jArr[0]));
        this.logger.d(new C0726b(11, pair, str));
        return pair;
    }

    private UploadType getUploadType(ApiContext apiContext) {
        if (apiContext.name.equals(MediaApiSpec.Control.UPLOAD_RESUMABLE)) {
            return UploadType.Create;
        }
        if (apiContext.name.equals(MediaApiSpec.Control.UPDATE_RESUMABLE)) {
            return UploadType.Update;
        }
        if (apiContext.name.equals(MediaApiSpec.Control.REVERT_WITH_UPDATE)) {
            return UploadType.Update;
        }
        throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "resumable upload is not supported for this api - " + apiContext.name);
    }

    private OneDriveUrlDbManager getUrlDBManager(SCHashMap sCHashMap) {
        return this.onedriveDBManagerMap.get(UploadType.valueOf(sCHashMap.getAsString(PARAM_UPLOAD_TYPE)));
    }

    private void initialize() {
        this.hashedUserId = ScspCorePreferences.get().hashedUid.get();
        this.onedriveDBManagerMap = new HashMap();
        OneDriveUploadUrlDbManager oneDriveUploadUrlDbManager = new OneDriveUploadUrlDbManager(ContextFactory.getApplicationContext());
        OneDriveUpdateUrlDbManager oneDriveUpdateUrlDbManager = new OneDriveUpdateUrlDbManager(ContextFactory.getApplicationContext());
        this.onedriveDBManagerMap.put(UploadType.Create, oneDriveUploadUrlDbManager);
        this.onedriveDBManagerMap.put(UploadType.Update, oneDriveUpdateUrlDbManager);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getUploadSessionInfo$6(long j2, boolean[] zArr, long[] jArr, SCHashMap sCHashMap) {
        long j3;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            j2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault()).parse(sCHashMap.getAsString(MediaApiContract.Parameter.EXPIRATION_DATE_TIME)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (currentTimeMillis >= j2) {
            zArr[0] = false;
            return;
        }
        String asString = sCHashMap.getAsString(MediaApiContract.Parameter.NEXT_EXPECTED_RANGE);
        if (StringUtil.isEmpty(asString)) {
            zArr[0] = true;
            jArr[0] = -2;
            return;
        }
        try {
            j3 = Long.parseLong(asString.trim().split("-")[0]);
        } catch (Exception e7) {
            e7.printStackTrace();
            j3 = -1;
        }
        if (j3 >= 0) {
            zArr[0] = true;
            jArr[0] = j3;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getUploadSessionInfo$7(Pair pair, String str) {
        return "getUploadSessionInfo: " + pair + "   /  " + str;
    }

    public long getDefaultRangeStart(SCHashMap sCHashMap) {
        Long l = (Long) Optional.ofNullable(sCHashMap).map(new d(1)).orElse(0L);
        if (l.longValue() >= 0) {
            return l.longValue();
        }
        return 0;
    }

    public String getDefaultUploadUrl(SCHashMap sCHashMap) {
        return (String) Optional.ofNullable(sCHashMap).map(new d(4)).orElse("");
    }

    public long getItemOriginalRangeStart(SCHashMap sCHashMap) {
        Long l = (Long) Optional.ofNullable(sCHashMap).map(new n(29)).orElse(0L);
        if (l.longValue() >= 0) {
            return l.longValue();
        }
        return 0;
    }

    public String getItemOriginalUploadUrl(SCHashMap sCHashMap) {
        return (String) Optional.ofNullable(sCHashMap).map(new d(0)).orElse("");
    }

    public boolean isCanResumable(SCHashMap sCHashMap) {
        Boolean asBoolean = sCHashMap.getAsBoolean(PARAM_IS_RESUMABLE);
        if (asBoolean == null || !asBoolean.booleanValue()) {
            return false;
        }
        return true;
    }

    public boolean isDefaultUploadCompleted(SCHashMap sCHashMap) {
        if (((Long) Optional.ofNullable(sCHashMap).map(new d(3)).orElse(0L)).longValue() == RANGE_START_UPLOAD_COMPLETED_VALUE) {
            return true;
        }
        return false;
    }

    public boolean isItemOriginalUploadCompleted(SCHashMap sCHashMap) {
        if (((Long) Optional.ofNullable(sCHashMap).map(new d(2)).orElse(0L)).longValue() == RANGE_START_UPLOAD_COMPLETED_VALUE) {
            return true;
        }
        return false;
    }

    public void onCommitResumableUpload(ApiContext apiContext, SCHashMap sCHashMap) {
        deleteUrlInfo(getAndValidateMediaToUpload(apiContext), sCHashMap);
    }

    public void onCreatedUploadUrl(ApiContext apiContext, SCHashMap sCHashMap, SCHashMap sCHashMap2) {
        if (!StringUtil.isEmpty(this.hashedUserId)) {
            Media andValidateMediaToUpload = getAndValidateMediaToUpload(apiContext);
            sCHashMap.remove("url");
            sCHashMap.remove(MediaApiContract.Parameter.ORIGINAL_URL);
            sCHashMap.remove(FileApiContract.Parameter.RANGE_START);
            sCHashMap.remove(MediaApiContract.Parameter.ORIGINAL_UPLOAD_RANGE_START);
            String asString = sCHashMap2.getAsString("url");
            String asString2 = sCHashMap2.getAsString(MediaApiContract.Parameter.ORIGINAL_URL);
            sCHashMap.put("url", asString);
            if (StringUtil.isEmpty(asString2) || StringUtil.isEmpty(andValidateMediaToUpload.originalBinaryHash)) {
                getUrlDBManager(sCHashMap).writeDefaultUrlInfo(andValidateMediaToUpload.hash, asString, this.hashedUserId);
                return;
            }
            sCHashMap.put(MediaApiContract.Parameter.ORIGINAL_URL, asString2);
            getUrlDBManager(sCHashMap).writeNDEUrlInfo(andValidateMediaToUpload.hash, asString, andValidateMediaToUpload.originalBinaryHash, asString2, this.hashedUserId);
        }
    }

    public SCHashMap onPrepareResumableUpload(ApiContext apiContext, Listeners listeners) {
        SCHashMap sCHashMap = new SCHashMap();
        Media andValidateMediaToUpload = getAndValidateMediaToUpload(apiContext);
        sCHashMap.put(PARAM_UPLOAD_TYPE, getUploadType(apiContext).name());
        getUrlDBManager(sCHashMap).clearExpiredInfo(System.currentTimeMillis());
        boolean statusOneDriveUploadUrl = getStatusOneDriveUploadUrl(apiContext, sCHashMap);
        sCHashMap.put(PARAM_IS_RESUMABLE, Boolean.valueOf(statusOneDriveUploadUrl));
        if (!statusOneDriveUploadUrl) {
            deleteUrlInfo(andValidateMediaToUpload, sCHashMap);
        }
        return sCHashMap;
    }

    public void onStartedResumableUpload(SCHashMap sCHashMap) {
        if (sCHashMap != null) {
            sCHashMap.put(PARAM_UPLOAD_START_TIME, Long.valueOf(System.currentTimeMillis()));
        }
    }
}
