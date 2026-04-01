package com.samsung.android.sdk.scs.ai.language;

import A4.S;
import android.content.Context;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import ba.C0582a;
import c4.C0431a;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.ai.language.service.AuthHeader;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceRunnable;
import com.samsung.android.sdk.scs.ai.language.service.WritingComposerServiceExecutor;
import com.samsung.android.sdk.scs.ai.language.service.WritingComposerServiceExecutorForExternal;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.m0;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WritingComposer {
    private static final String TAG = "WritingComposer";
    protected WritingComposerServiceExecutor mServiceExecutor;

    public WritingComposer(Context context) {
        Log.d(TAG, TAG);
        createExecutor(context);
    }

    private File createFolder(String str) {
        File file = new File(this.mServiceExecutor.context.getFilesDir().getAbsolutePath(), str);
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        Log.d(TAG, "writing_composer folder creation failed");
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$compose$0(WritingComposerInputParams writingComposerInputParams, AppInfo appInfo, Map map, LlmServiceObserver2 llmServiceObserver2) {
        File createFolder;
        FileOutputStream fileOutputStream;
        Throwable th;
        try {
            ParcelFileDescriptor parcelFileDescriptor = null;
            if (!(writingComposerInputParams.getData() == null || (createFolder = createFolder("writing_composer")) == null)) {
                File file = new File(createFolder, "writing_composer_file");
                try {
                    fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(writingComposerInputParams.getData());
                    parcelFileDescriptor = ParcelFileDescriptor.open(file, 268435456);
                    fileOutputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "datafile failure: " + e.getMessage());
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptor;
            if (writingComposerInputParams.getDynamicFormat().isEmpty() || writingComposerInputParams.getDynamicTone().isEmpty()) {
                Map map2 = map;
                m0 m0Var = (m0) this.mServiceExecutor.getService();
                m0Var.a(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), writingComposerInputParams.getInputText(), writingComposerInputParams.getDataMimeType(), parcelFileDescriptor2, writingComposerInputParams.getFormat().name(), writingComposerInputParams.getTone().name(), writingComposerInputParams.getOutputSentenceCount(), llmServiceObserver2, map2);
                return;
            }
            ((m0) this.mServiceExecutor.getService()).c(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), writingComposerInputParams.getInputText(), writingComposerInputParams.getDataMimeType(), parcelFileDescriptor2, writingComposerInputParams.getDynamicFormat(), writingComposerInputParams.getDynamicTone(), writingComposerInputParams.getOutputSentenceCount(), llmServiceObserver2, map);
            return;
            throw th;
        } catch (RemoteException e7) {
            throw new RuntimeException(e7);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$compose$1(WritingComposerMultiInputParams writingComposerMultiInputParams, AppInfo appInfo, Map map, LlmServiceObserver2 llmServiceObserver2) {
        FileOutputStream fileOutputStream;
        Throwable th;
        try {
            List<byte[]> dataList = writingComposerMultiInputParams.getDataList();
            ArrayList arrayList = new ArrayList();
            File createFolder = createFolder("writing_composer");
            if (createFolder != null) {
                for (int i2 = 0; i2 < dataList.size(); i2++) {
                    File file = new File(createFolder, "writing_composer_file_" + i2);
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
            ((m0) this.mServiceExecutor.getService()).b(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), writingComposerMultiInputParams.getInputText(), writingComposerMultiInputParams.getDataMimeTypeList(), arrayList, writingComposerMultiInputParams.getFormat().name(), writingComposerMultiInputParams.getTone().name(), writingComposerMultiInputParams.getOutputSentenceCount(), llmServiceObserver2, map);
            return;
            throw th;
        } catch (RemoteException e7) {
            throw new RuntimeException(e7);
        }
    }

    /* access modifiers changed from: private */
    public void lambda$release$2(LlmServiceObserver2 llmServiceObserver2) {
        Parcel obtain;
        try {
            m0 m0Var = (m0) this.mServiceExecutor.getService();
            m0Var.getClass();
            obtain = Parcel.obtain();
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.IWritingComposerService");
            m0Var.f1712a.transact(2, obtain, (Parcel) null, 1);
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
    public /* synthetic */ void lambda$release$3(Task task) {
        Log.i(TAG, "connected: " + this.mServiceExecutor.isConnected());
        this.mServiceExecutor.deInit();
    }

    public Task<Result> compose(AppInfo appInfo, WritingComposerInputParams writingComposerInputParams) {
        return compose(appInfo, writingComposerInputParams, (Map<String, String>) new HashMap());
    }

    public void createExecutor(Context context) {
        this.mServiceExecutor = new WritingComposerServiceExecutor(context);
    }

    public void release() {
        Log.i(TAG, "release: " + this.mServiceExecutor.isConnected());
        if (Feature.checkFeature(this.mServiceExecutor.context, Feature.FEATURE_SIVS_WRITING_COMPOSER_ONDEVICE) == 0) {
            LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(Feature.FEATURE_SIVS_WRITING_COMPOSER, false, new C0582a(26, this), new C0431a(22));
            this.mServiceExecutor.execute(llmServiceRunnable);
            llmServiceRunnable.getTask().addOnCompleteListener(new a(4, this));
            return;
        }
        this.mServiceExecutor.deInit();
    }

    public Task<Result> compose(AppInfo appInfo, WritingComposerInputParams writingComposerInputParams, Map<String, String> map) {
        String str;
        if (appInfo.getRequestType() == AppInfo.RequestType.CLOUD) {
            str = Feature.FEATURE_SIVS_WRITING_COMPOSER;
        } else {
            str = Feature.FEATURE_SIVS_WRITING_COMPOSER_ONDEVICE;
        }
        if (this.mServiceExecutor instanceof WritingComposerServiceExecutorForExternal) {
            str = Feature.FEATURE_SIVS_WRITING_COMPOSER_FOR_EXTERNAL;
        }
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(str, appInfo.isStreamingMode(), new S(this, writingComposerInputParams, appInfo, map, 12), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }

    public Task<Result> compose(AppInfo appInfo, WritingComposerMultiInputParams writingComposerMultiInputParams, Map<String, String> map) {
        String str;
        if (appInfo.getRequestType() == AppInfo.RequestType.CLOUD) {
            str = Feature.FEATURE_SIVS_WRITING_COMPOSER;
        } else {
            str = Feature.FEATURE_SIVS_WRITING_COMPOSER_ONDEVICE;
        }
        if (this.mServiceExecutor instanceof WritingComposerServiceExecutorForExternal) {
            str = Feature.FEATURE_SIVS_WRITING_COMPOSER_FOR_EXTERNAL;
        }
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(str, appInfo.isStreamingMode(), new S(this, writingComposerMultiInputParams, appInfo, map, 13), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }
}
