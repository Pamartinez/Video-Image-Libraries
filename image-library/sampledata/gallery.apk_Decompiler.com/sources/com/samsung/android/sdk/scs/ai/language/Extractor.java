package com.samsung.android.sdk.scs.ai.language;

import B8.k;
import a6.g;
import android.content.Context;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import ba.C0582a;
import c4.C0431a;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.ai.language.service.AuthHeader;
import com.samsung.android.sdk.scs.ai.language.service.ExtractionServiceExecutor;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceRunnable;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.C0167n;
import com.samsung.android.sivs.ai.sdkcommon.language.C0169p;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Extractor {
    private static final String TAG = "Extractor";
    private final String featureName = Feature.FEATURE_SIVS_EXTRACTION;
    private final ExtractionServiceExecutor mServiceExecutor;

    public Extractor(Context context) {
        Log.d(TAG, TAG);
        this.mServiceExecutor = new ExtractionServiceExecutor(context);
    }

    private File createFolder(String str) {
        File file = new File(this.mServiceExecutor.context.getFilesDir().getAbsolutePath(), str);
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        Log.d(TAG, "extract_image folder creation failed");
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$extract$0(AppInfo appInfo, String str, ExtractionCategory extractionCategory, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((C0167n) this.mServiceExecutor.getService()).a(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, extractionCategory.name(), llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$extract$1(ExtractionInputParams extractionInputParams, AppInfo.RequestType requestType, AppInfo appInfo, Map map, LlmServiceObserver2 llmServiceObserver2) {
        File createFolder;
        FileOutputStream fileOutputStream;
        Throwable th;
        try {
            ParcelFileDescriptor parcelFileDescriptor = null;
            if (!(extractionInputParams.getData() == null || (createFolder = createFolder("extract_image")) == null)) {
                File file = new File(createFolder, "extract_image_file");
                try {
                    fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(extractionInputParams.getData());
                    parcelFileDescriptor = ParcelFileDescriptor.open(file, 268435456);
                    fileOutputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "datafile failure: " + e.getMessage());
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptor;
            if (requestType == AppInfo.RequestType.CLOUD) {
                C0167n nVar = (C0167n) this.mServiceExecutor.getService();
                nVar.b(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), extractionInputParams.getInputText(), extractionInputParams.getCategory().getName(), extractionInputParams.getDataMimeType(), parcelFileDescriptor2, llmServiceObserver2, map);
                return;
            }
            C0169p service = this.mServiceExecutor.getService();
            Map<String, String> generateHeaderMap = new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context);
            String inputText = extractionInputParams.getInputText();
            String name = extractionInputParams.getCategory().getName();
            C0167n nVar2 = (C0167n) service;
            nVar2.d(generateHeaderMap, inputText, name, parcelFileDescriptor2, llmServiceObserver2, map);
            return;
            throw th;
        } catch (RemoteException e7) {
            throw new RuntimeException(e7);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$extract$2(ExtractionMultiInputParams extractionMultiInputParams, AppInfo.RequestType requestType, AppInfo appInfo, Map map, LlmServiceObserver2 llmServiceObserver2) {
        FileOutputStream fileOutputStream;
        Throwable th;
        try {
            List<byte[]> dataList = extractionMultiInputParams.getDataList();
            ArrayList arrayList = new ArrayList();
            File createFolder = createFolder("extract_image");
            if (createFolder != null) {
                for (int i2 = 0; i2 < dataList.size(); i2++) {
                    File file = new File(createFolder, "extract_image_file_" + i2);
                    try {
                        fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(dataList.get(i2));
                        arrayList.add(ParcelFileDescriptor.open(file, 268435456));
                        fileOutputStream.close();
                    } catch (IOException e) {
                        Log.e(TAG, "datafile failure: " + e.getMessage());
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
            }
            if (requestType == AppInfo.RequestType.CLOUD) {
                ((C0167n) this.mServiceExecutor.getService()).c(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), extractionMultiInputParams.getInputText(), extractionMultiInputParams.getCategory().getName(), extractionMultiInputParams.getDataMimeTypeList(), arrayList, llmServiceObserver2, map);
                return;
            }
            Log.e(TAG, "ExtractionMultiInput requestType: " + requestType + ", now only support CLOUD");
            return;
            throw th;
        } catch (RemoteException e7) {
            throw new RuntimeException(e7);
        }
    }

    /* access modifiers changed from: private */
    public void lambda$release$3(LlmServiceObserver2 llmServiceObserver2) {
        Parcel obtain;
        try {
            C0167n nVar = (C0167n) this.mServiceExecutor.getService();
            nVar.getClass();
            obtain = Parcel.obtain();
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.IExtractionService");
            nVar.f1713a.transact(5, obtain, (Parcel) null, 1);
            obtain.recycle();
            llmServiceObserver2.onNext(new ArrayList());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$release$4(Task task) {
        Log.i(TAG, "connected: " + this.mServiceExecutor.isConnected());
        this.mServiceExecutor.deInit();
    }

    public Task<Result> extract(AppInfo appInfo, String str, ExtractionCategory extractionCategory) {
        return extract(appInfo, str, extractionCategory, new HashMap());
    }

    public void release() {
        Log.i(TAG, "release: " + this.mServiceExecutor.isConnected());
        if (Feature.checkFeature(this.mServiceExecutor.context, Feature.FEATURE_SIVS_EXTRACTION_ONDEVICE) == 0) {
            LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(Feature.FEATURE_SIVS_EXTRACTION, false, new C0582a(19, this), new C0431a(22));
            this.mServiceExecutor.execute(llmServiceRunnable);
            llmServiceRunnable.getTask().addOnCompleteListener(new g(27, this));
            return;
        }
        this.mServiceExecutor.deInit();
    }

    public Task<Result> extract(AppInfo appInfo, String str, ExtractionCategory extractionCategory, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(Feature.FEATURE_SIVS_EXTRACTION, appInfo.isStreamingMode(), new k(this, appInfo, str, extractionCategory, map, 8), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }

    public Task<Result> extract(AppInfo appInfo, ExtractionInputParams extractionInputParams) {
        return extract(appInfo, extractionInputParams, (Map<String, String>) new HashMap());
    }

    public Task<Result> extract(AppInfo appInfo, ExtractionInputParams extractionInputParams, Map<String, String> map) {
        String str;
        AppInfo.RequestType requestType = appInfo.getRequestType();
        if (requestType == AppInfo.RequestType.CLOUD) {
            str = Feature.FEATURE_SIVS_EXTRACTION;
        } else {
            str = Feature.FEATURE_SIVS_EXTRACTION_ONDEVICE;
        }
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(str, appInfo.isStreamingMode(), new k(this, extractionInputParams, requestType, appInfo, map, 6), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }

    public Task<Result> extract(AppInfo appInfo, ExtractionMultiInputParams extractionMultiInputParams, Map<String, String> map) {
        String str;
        AppInfo.RequestType requestType = appInfo.getRequestType();
        if (requestType == AppInfo.RequestType.CLOUD) {
            str = Feature.FEATURE_SIVS_EXTRACTION;
        } else {
            str = Feature.FEATURE_SIVS_EXTRACTION_ONDEVICE;
        }
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(str, appInfo.isStreamingMode(), new k(this, extractionMultiInputParams, requestType, appInfo, map, 7), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }
}
