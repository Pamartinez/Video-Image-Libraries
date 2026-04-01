package com.samsung.android.sdk.scs.ai.text.category;

import Tc.a;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import c0.C0086a;
import c4.C0431a;
import com.samsung.android.motionphoto.utils.v2.k;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.ai.text.TextServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sum.core.Def;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DocumentCategoryClassifier {
    private static final Set<String> SUPPORTED_COUNTRY = ((Set) Stream.of(new String[]{"US", "GB", "CA", "KR"}).collect(Collectors.collectingAndThen(Collectors.toSet(), new C0431a(25))));
    private static final String TAG = "ScsApi@DocumentCategoryClassifier";
    private final boolean isDocumentCategoryClassifierSupported;
    private TextServiceExecutor mServiceExecutor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ClassifyOptions {
        String country;
        String language;
        String requestType;

        public ClassifyOptions(RequestType requestType2) {
            this.requestType = requestType2.name();
        }

        public String getCountry() {
            return this.country;
        }

        public String getLanguage() {
            return this.language;
        }

        public String getRequestType() {
            return this.requestType;
        }

        public ClassifyOptions setCountry(String str) {
            this.country = str;
            return this;
        }

        public ClassifyOptions setLanguage(String str) {
            this.language = str;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum RequestType {
        MESSAGE,
        NOTIFICATION
    }

    public DocumentCategoryClassifier(Context context) {
        boolean z;
        if (Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_DOCUMENT_CATEGORY) == 0) {
            z = true;
        } else {
            z = false;
        }
        this.isDocumentCategoryClassifierSupported = z;
        initConnection(context);
    }

    private void initConnection(Context context) {
        Log.d(TAG, "initConnection");
        this.mServiceExecutor = new TextServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ List lambda$classifySynchronously$2(String str, ClassifyOptions classifyOptions) {
        String str2;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(str);
        if (classifyOptions.getLanguage() != null) {
            str2 = classifyOptions.getLanguage();
        } else {
            str2 = Locale.getDefault().getLanguage();
        }
        Bundle bundle = null;
        try {
            ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
            Bundle bundle2 = new Bundle();
            bundle2.putString("language", str2);
            bundle2.putString("requestType", classifyOptions.getRequestType());
            bundle2.putString(BuddyContract.Address.COUNTRY, classifyOptions.getCountry());
            bundle2.putStringArrayList("string", arrayList2);
            bundle = textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "getCategoryClassify", (String) null, bundle2);
        } catch (Exception e) {
            Log.e(TAG, "Exception :: classifySynchronously ", e);
        }
        if (bundle == null) {
            Log.e(TAG, "DocumentCategoryClassifier.classify(). ContentResolver result is null!!");
            return arrayList;
        }
        int i2 = bundle.getInt(OCRServiceConstant.KEY_RESULT_CODE);
        if (i2 != 1) {
            C0086a.u(i2, "unexpected resultCode!!! resultCode: ", TAG);
            return arrayList;
        }
        long[] longArray = bundle.getLongArray("categoryIdList");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("categoryNameList");
        double[] doubleArray = bundle.getDoubleArray("categoryScoreArray");
        if (longArray == null || stringArrayList == null || doubleArray == null) {
            Log.e(TAG, "null!! categoryIdArray: " + Arrays.toString(longArray) + ", categoryNameList: " + stringArrayList + ", categoryScoreArray: " + Arrays.toString(doubleArray));
            return arrayList;
        }
        int length = longArray.length;
        if (length == stringArrayList.size() && length == doubleArray.length) {
            for (int i7 = 0; i7 < length; i7++) {
                DocumentCategory documentCategory = new DocumentCategory();
                documentCategory.setId(Long.valueOf(longArray[i7]));
                documentCategory.setName(stringArrayList.get(i7));
                documentCategory.setScore(doubleArray[i7]);
                arrayList.add(documentCategory);
            }
            return arrayList;
        }
        StringBuilder o2 = C0086a.o(length, "unexpected size!!! : ", " vs ");
        C0086a.A(o2, stringArrayList, " vs ");
        o2.append(doubleArray.length);
        Log.e(TAG, o2.toString());
        return arrayList;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Map lambda$getCategoryInfo$1(RequestType requestType, String str) {
        HashMap hashMap = new HashMap();
        try {
            ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
            Bundle bundle = new Bundle();
            bundle.putString("requestType", requestType.name());
            bundle.putString("language", str);
            Bundle call = textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "getCategoryInfo", (String) null, bundle);
            if (call == null) {
                Log.e(TAG, "DocumentCategoryClassifier.getCategoryInfo(). ContentResolver result is null!!");
                return null;
            }
            int i2 = call.getInt(OCRServiceConstant.KEY_RESULT_CODE);
            if (i2 != 1) {
                Log.e(TAG, "unexpected resultCode!!! resultCode: " + i2);
                return null;
            }
            long[] longArray = call.getLongArray("categoryIdList");
            ArrayList<String> stringArrayList = call.getStringArrayList("categoryNameList");
            if (longArray != null) {
                if (stringArrayList != null) {
                    int length = longArray.length;
                    if (length != stringArrayList.size()) {
                        Log.e(TAG, "unexpected size!!! : " + length + " vs " + stringArrayList.size());
                        return null;
                    }
                    for (int i7 = 0; i7 < length; i7++) {
                        hashMap.put(stringArrayList.get(i7), Long.valueOf(longArray[i7]));
                    }
                    return hashMap;
                }
            }
            Log.e(TAG, "null!! categoryIdArray: " + Arrays.toString(longArray) + ", categoryNameList: " + stringArrayList);
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Exception :: getCategoryInfo", e);
            return hashMap;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$isSupported$0(RequestType requestType, String str, String str2) {
        try {
            ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
            Bundle bundle = new Bundle();
            bundle.putString("requestType", requestType.name());
            bundle.putString("language", str);
            bundle.putString(BuddyContract.Address.COUNTRY, str2);
            Bundle call = textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "getCategorySupported", (String) null, bundle);
            if (call == null) {
                Log.e(TAG, "DocumentCategory.isSupported(). ContentResolver result is null!!");
                return Boolean.FALSE;
            } else if (call.isEmpty()) {
                Log.e(TAG, "DocumentCategory.isSupported(). result is empty!! App version is lower than Sdk so just check in static Array");
                if (requestType != RequestType.MESSAGE || str2 == null) {
                    return Boolean.FALSE;
                }
                return Boolean.valueOf(SUPPORTED_COUNTRY.contains(str2.toUpperCase()));
            } else {
                int i2 = call.getInt(OCRServiceConstant.KEY_RESULT_CODE);
                if (i2 == 1) {
                    return Boolean.valueOf(call.getBoolean("textSupportedBoolean"));
                }
                Log.e(TAG, "unexpected resultCode!!! resultCode: " + i2);
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception :: isSupported", e);
            return Boolean.FALSE;
        }
    }

    public Task<List<DocumentCategory>> classify(String str, ClassifyOptions classifyOptions) {
        Log.d(TAG, "DocumentCategory classify for singleDocument");
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return classify((ArrayList<String>) arrayList, classifyOptions);
    }

    public List<DocumentCategory> classifySynchronously(String str, ClassifyOptions classifyOptions) {
        List<DocumentCategory> list = Collections.EMPTY_LIST;
        Log.d(TAG, "DocumentCategory classify synchronously for singleDocument");
        if (!this.isDocumentCategoryClassifierSupported) {
            Log.e(TAG, "Feature.FEATURE_TEXT_GET_DOCUMENT_CATEGORY not supported!");
            return list;
        }
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new a((Object) this, (Object) str, (Object) classifyOptions, 1));
        try {
            List<DocumentCategory> list2 = (List) submit.get(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
            newSingleThreadExecutor.shutdownNow();
            return list2;
        } catch (TimeoutException e) {
            submit.cancel(true);
            Log.e(TAG, "Timeout in classifySynchronously : " + e.getMessage());
            List list3 = Collections.EMPTY_LIST;
            newSingleThreadExecutor.shutdownNow();
            return list;
        } catch (Exception e7) {
            Log.e(TAG, "Exception occurred in classifySynchronously : " + e7.getMessage());
            List list4 = Collections.EMPTY_LIST;
            newSingleThreadExecutor.shutdownNow();
            return list;
        } catch (Throwable th) {
            newSingleThreadExecutor.shutdownNow();
            throw th;
        }
    }

    public Map<String, Long> getCategoryInfo(RequestType requestType, String str) {
        Log.d(TAG, "DocumentCategory getCategoryInfo - language : " + str);
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new a((Object) this, (Serializable) requestType, str, 2));
        try {
            Map<String, Long> map = (Map) submit.get(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
            newSingleThreadExecutor.shutdownNow();
            return map;
        } catch (TimeoutException e) {
            submit.cancel(true);
            Log.e(TAG, "Timeout getCategoryInfo : " + e.getMessage());
            newSingleThreadExecutor.shutdownNow();
            return null;
        } catch (Exception e7) {
            Log.e(TAG, "Exception occurred in getCategoryInfo : " + e7.getMessage());
            newSingleThreadExecutor.shutdownNow();
            return null;
        } catch (Throwable th) {
            newSingleThreadExecutor.shutdownNow();
            throw th;
        }
    }

    public Task<List<DocumentSimilarity>> isSimilar(String str, ArrayList<String> arrayList, ClassifyOptions classifyOptions) {
        Log.d(TAG, "DocumentCategory isSimilar for multiple target");
        SimilarityRunnable similarityRunnable = new SimilarityRunnable(this.mServiceExecutor);
        int length = str.length();
        if (length > 100000) {
            Log.i(TAG, String.format("DocumentCategory isSimilar source input length(%d) exceed MAX_VAL(%d), so cut to %d", new Object[]{Integer.valueOf(length), 100000, 100000}));
            str = str.substring(length - 100000, length);
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            int length2 = next.length();
            if (length2 > 100000) {
                Log.i(TAG, String.format("DocumentCategory isSimilar target input length(%d) exceed MAX_VAL(%d), so cut to %d", new Object[]{Integer.valueOf(length2), 100000, 100000}));
                next = next.substring(length2 - 100000, length2);
            }
            arrayList2.add(next);
        }
        similarityRunnable.setSourceText(str);
        similarityRunnable.setTargetTextList(arrayList2);
        similarityRunnable.setOptions(classifyOptions);
        this.mServiceExecutor.execute(similarityRunnable);
        return similarityRunnable.getTask();
    }

    public boolean isSupported(RequestType requestType, String str, String str2) {
        Boolean bool;
        StringBuilder q = C0086a.q("DocumentCategory isSupported - requestType : ", requestType.name(), ", language : ", str, ", country : ");
        q.append(str2);
        Log.d(TAG, q.toString());
        if (!this.isDocumentCategoryClassifierSupported && requestType == RequestType.MESSAGE) {
            return false;
        }
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new k(this, requestType, str, str2, 1));
        try {
            bool = (Boolean) submit.get(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            TimeoutException timeoutException = e;
            submit.cancel(true);
            Log.e(TAG, "Timeout in isSupported : " + timeoutException.getMessage());
            bool = Boolean.FALSE;
        } catch (Exception e7) {
            Exception exc = e7;
            Log.e(TAG, "Exception occurred in isSupported : " + exc.getMessage());
            bool = Boolean.FALSE;
        } catch (Throwable th) {
            Throwable th2 = th;
            newSingleThreadExecutor.shutdownNow();
            throw th2;
        }
        newSingleThreadExecutor.shutdownNow();
        return bool.booleanValue();
    }

    public Task<List<DocumentCategory>> classify(ArrayList<String> arrayList, ClassifyOptions classifyOptions) {
        Log.d(TAG, "DocumentCategory classify for multiDocuments");
        CategoryClassifyRunnable categoryClassifyRunnable = new CategoryClassifyRunnable(this.mServiceExecutor);
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next != null) {
                int length = next.length();
                if (length > 100000) {
                    Log.i(TAG, String.format("DocumentCategory classify input length(%d) exceed MAX_VAL(%d), so cut to %d", new Object[]{Integer.valueOf(length), 100000, 100000}));
                    next = next.substring(length - 100000, length);
                }
                arrayList2.add(next);
            }
        }
        categoryClassifyRunnable.setTextList(arrayList2);
        categoryClassifyRunnable.setOptions(classifyOptions);
        this.mServiceExecutor.execute(categoryClassifyRunnable);
        return categoryClassifyRunnable.getTask();
    }
}
