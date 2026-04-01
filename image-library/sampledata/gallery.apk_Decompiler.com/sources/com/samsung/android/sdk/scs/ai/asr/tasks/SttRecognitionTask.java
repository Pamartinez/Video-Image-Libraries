package com.samsung.android.sdk.scs.ai.asr.tasks;

import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import ba.C0582a;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import com.samsung.android.sdk.scs.ai.BuildConfig;
import com.samsung.android.sdk.scs.ai.asr.ASRLog;
import com.samsung.android.sdk.scs.ai.asr.RecognitionConfig;
import com.samsung.android.sdk.scs.ai.asr.SpeechRecognitionListener;
import com.samsung.android.sdk.scs.ai.asr.io.NullOutputStream;
import com.samsung.android.sdk.scs.ai.asr.safety.WatchDog;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.asr.DialogInfo;
import com.samsung.android.sivs.ai.sdkcommon.asr.IRecognitionListener;
import com.samsung.android.sivs.ai.sdkcommon.asr.ISpeechRecognizer;
import com.samsung.android.sivs.ai.sdkcommon.asr.SpeechRecognitionConst;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SttRecognitionTask extends RecognitionTask<String> {
    private static final long TIMEOUT_WRITE = TimeUnit.SECONDS.toMillis(30);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /* access modifiers changed from: private */
    public static final SpeechRecognitionListener ignoreResult = new SpeechRecognitionListener() {
        public void onPartialResults(String str, Bundle bundle) {
        }

        public void onResults(String str, Bundle bundle) {
        }

        public void onError(int i2, String str, Bundle bundle) {
        }
    };
    private final String TAG;
    private final String callerPackage;
    private final RecognitionConfig config;
    private final AtomicReference<OutputStream> dest = new AtomicReference<>(new NullOutputStream());
    private InputStream inputStream;
    private ListenerWrapper listener;
    private ISpeechRecognizer mRecognizer;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ListenerWrapper extends IRecognitionListener.Stub {
        private final String TAG;
        private final Consumer<String> complete;
        private SpeechRecognitionListener root;

        public ListenerWrapper(String str, SpeechRecognitionListener speechRecognitionListener, Consumer<String> consumer) {
            this.TAG = str;
            this.root = speechRecognitionListener;
            this.complete = consumer;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0066, code lost:
            r8 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0070, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
            r8.addSuppressed(r1);
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [B:13:0x005a, B:24:0x006c] */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x007a A[SYNTHETIC, Splitter:B:33:0x007a] */
        /* JADX WARNING: Removed duplicated region for block: B:43:0x0099  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private android.os.Bundle checkFileUriResult(android.os.Bundle r8) {
            /*
                r7 = this;
                r7.loadClassesBeforeAccess(r8)
                java.lang.String r0 = "large_result_pfd"
                boolean r0 = r8.containsKey(r0)
                if (r0 == 0) goto L_0x009d
                java.lang.Object r0 = r8.getParcelable(com.samsung.android.sivs.ai.sdkcommon.asr.SpeechRecognitionConst.Key.LARGE_RESULT_PFD, android.os.ParcelFileDescriptor.class)     // Catch:{ Exception -> 0x0083 }
                android.os.ParcelFileDescriptor r0 = (android.os.ParcelFileDescriptor) r0     // Catch:{ Exception -> 0x0083 }
                android.os.ParcelFileDescriptor$AutoCloseInputStream r1 = new android.os.ParcelFileDescriptor$AutoCloseInputStream     // Catch:{ all -> 0x0075 }
                r1.<init>(r0)     // Catch:{ all -> 0x0075 }
                int r2 = r1.available()     // Catch:{ all -> 0x0068 }
                java.lang.String r3 = r7.TAG     // Catch:{ all -> 0x0068 }
                java.lang.String r4 = "checkFileUriResult"
                java.lang.Integer r5 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0068 }
                java.lang.Object[] r4 = new java.lang.Object[]{r4, r5}     // Catch:{ all -> 0x0068 }
                com.samsung.android.sdk.scs.ai.asr.ASRLog.i(r3, r4)     // Catch:{ all -> 0x0068 }
                byte[] r3 = new byte[r2]     // Catch:{ all -> 0x0068 }
                r4 = 0
                r1.read(r3, r4, r2)     // Catch:{ all -> 0x0068 }
                android.os.Parcel r5 = android.os.Parcel.obtain()     // Catch:{ all -> 0x0068 }
                r5.unmarshall(r3, r4, r2)     // Catch:{ all -> 0x0068 }
                r5.setDataPosition(r4)     // Catch:{ all -> 0x0068 }
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR     // Catch:{ all -> 0x0068 }
                java.lang.Object r2 = r2.createFromParcel(r5)     // Catch:{ all -> 0x0068 }
                android.os.Bundle r2 = (android.os.Bundle) r2     // Catch:{ all -> 0x0068 }
                r7.loadClassesBeforeAccess(r2)     // Catch:{ all -> 0x0058 }
                r5.recycle()     // Catch:{ all -> 0x0058 }
                boolean r8 = com.samsung.android.sdk.scs.ai.asr.ASRLog.isDevelop     // Catch:{ all -> 0x0058 }
                if (r8 == 0) goto L_0x005a
                java.util.Set r8 = r2.keySet()     // Catch:{ all -> 0x0058 }
                com.samsung.android.sdk.scs.ai.asr.tasks.e r3 = new com.samsung.android.sdk.scs.ai.asr.tasks.e     // Catch:{ all -> 0x0058 }
                r3.<init>(r7, r2)     // Catch:{ all -> 0x0058 }
                r8.forEach(r3)     // Catch:{ all -> 0x0058 }
                goto L_0x005a
            L_0x0058:
                r8 = move-exception
                goto L_0x006c
            L_0x005a:
                r1.close()     // Catch:{ all -> 0x0066 }
                if (r0 == 0) goto L_0x0065
                r0.close()     // Catch:{ Exception -> 0x0063 }
                return r2
            L_0x0063:
                r8 = move-exception
                goto L_0x0086
            L_0x0065:
                return r2
            L_0x0066:
                r8 = move-exception
                goto L_0x0078
            L_0x0068:
                r2 = move-exception
                r6 = r2
                r2 = r8
                r8 = r6
            L_0x006c:
                r1.close()     // Catch:{ all -> 0x0070 }
                goto L_0x0074
            L_0x0070:
                r1 = move-exception
                r8.addSuppressed(r1)     // Catch:{ all -> 0x0066 }
            L_0x0074:
                throw r8     // Catch:{ all -> 0x0066 }
            L_0x0075:
                r1 = move-exception
                r2 = r8
                r8 = r1
            L_0x0078:
                if (r0 == 0) goto L_0x0082
                r0.close()     // Catch:{ all -> 0x007e }
                goto L_0x0082
            L_0x007e:
                r0 = move-exception
                r8.addSuppressed(r0)     // Catch:{ Exception -> 0x0063 }
            L_0x0082:
                throw r8     // Catch:{ Exception -> 0x0063 }
            L_0x0083:
                r0 = move-exception
                r2 = r8
                r8 = r0
            L_0x0086:
                java.lang.String r7 = r7.TAG
                java.lang.String r0 = "failed to check FileUriResult"
                java.lang.String r1 = r8.getMessage()
                java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
                com.samsung.android.sdk.scs.ai.asr.ASRLog.e(r7, r0)
                boolean r7 = com.samsung.android.sdk.scs.ai.asr.ASRLog.isDevelop
                if (r7 == 0) goto L_0x009c
                r8.printStackTrace()
            L_0x009c:
                return r2
            L_0x009d:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.asr.tasks.SttRecognitionTask.ListenerWrapper.checkFileUriResult(android.os.Bundle):android.os.Bundle");
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$checkFileUriResult$0(Bundle bundle, String str) {
            ASRLog.i(this.TAG, "FileUriResult", str, bundle.get(str));
        }

        private void loadClassesBeforeAccess(Bundle bundle) {
            bundle.setClassLoader(DialogInfo.class.getClassLoader());
        }

        public void onError(Bundle bundle) {
            String string = bundle.getString("error_message");
            int i2 = bundle.getInt("error_code");
            ASRLog.secure(this.TAG, "onError", this.root, bundle);
            this.root.onError(i2, string, new Bundle());
            this.complete.accept(string);
        }

        public void onPartialResults(Bundle bundle) {
            Locale locale;
            loadClassesBeforeAccess(bundle);
            Bundle checkFileUriResult = checkFileUriResult(bundle);
            if (checkFileUriResult.containsKey(SpeechRecognitionConst.Key.RET_PROGRESS)) {
                int i2 = checkFileUriResult.getInt(SpeechRecognitionConst.Key.RET_PROGRESS);
                Bundle bundle2 = checkFileUriResult.getBundle(SpeechRecognitionConst.Key.RET_PROGRESS_EXTRA);
                ASRLog.secure(this.TAG, "onProgressUpdate", this.root, checkFileUriResult);
                this.root.onProgressUpdate(i2, bundle2);
                return;
            }
            Bundle bundle3 = checkFileUriResult.getBundle(KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA);
            if (bundle3 == null || !bundle3.containsKey(SpeechRecognitionConst.Key.RET_LOCALE_CHANGED)) {
                try {
                    ASRLog.secure(this.TAG, "onPartialResults", this.root, checkFileUriResult);
                    this.root.onPartialResults(checkFileUriResult.getString("result"), checkFileUriResult);
                } catch (Exception e) {
                    ASRLog.e(this.TAG, "Failed to handle result", e.getMessage());
                    if (ASRLog.isDevelop) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (Build.VERSION.SDK_INT >= 33) {
                    locale = (Locale) bundle3.getSerializable(SpeechRecognitionConst.Key.RET_LOCALE_CHANGED, Locale.class);
                } else {
                    locale = (Locale) bundle3.getSerializable(SpeechRecognitionConst.Key.RET_LOCALE_CHANGED);
                }
                ASRLog.secure(this.TAG, "onLocaleChanged", this.root, checkFileUriResult);
                this.root.onLocaleChanged(locale, bundle3);
            }
        }

        public void onResults(Bundle bundle) {
            loadClassesBeforeAccess(bundle);
            Bundle checkFileUriResult = checkFileUriResult(bundle);
            String string = checkFileUriResult.getString("result");
            try {
                ASRLog.secure(this.TAG, "onResults", this.root, checkFileUriResult);
                this.root.onResults(string, checkFileUriResult);
                Consumer<String> consumer = this.complete;
                consumer.accept("done:" + Optional.ofNullable(string).map(new a(2)).orElse(0));
            } catch (Exception e) {
                ASRLog.e(this.TAG, "Failed to handle result", e.getMessage());
                e.printStackTrace();
            }
        }

        public void release() {
            ASRLog.i(this.TAG, "release listener!!", this.root);
            this.root = SttRecognitionTask.ignoreResult;
        }
    }

    public SttRecognitionTask(RecognitionConfig recognitionConfig, InputStream inputStream2, String str, SpeechRecognitionListener speechRecognitionListener) {
        String str2 = "SttTask@" + hashCode();
        this.TAG = str2;
        Log.e(str2, "create task");
        this.config = recognitionConfig;
        this.callerPackage = str;
        this.inputStream = inputStream2;
        this.listener = new ListenerWrapper(str2, speechRecognitionListener, new C0582a(15, this));
    }

    private Bundle configToBundle(RecognitionConfig recognitionConfig) {
        Bundle bundle = new Bundle();
        if (recognitionConfig.hasExtras()) {
            bundle.putAll(recognitionConfig.getExtras());
        }
        bundle.putSerializable("locale", recognitionConfig.getLocale());
        bundle.putInt("connection_type", recognitionConfig.getConnectionType().getTypeInt());
        bundle.putBoolean("enabled_partial", recognitionConfig.enabledPartialResult());
        bundle.putBoolean("enabled_audio_compression", recognitionConfig.isEnabledAudioCompression());
        bundle.putBoolean("enabled_censor", recognitionConfig.isCensored());
        bundle.putBoolean(SpeechRecognitionConst.Key.ENABLE_PROGRESS, recognitionConfig.needProgress());
        bundle.putInt("server_type", recognitionConfig.getServerType());
        bundle.putParcelable("app_server_type", recognitionConfig.getServerInfo());
        bundle.putBoolean("enable_speaker_diarisation", recognitionConfig.isSpeakerDiarisation());
        bundle.putInt(SpeechRecognitionConst.Key.SD_NOTIFY_INTERVAL_TIME, recognitionConfig.getSdNotifyIntervalTime());
        bundle.putInt(SpeechRecognitionConst.Key.SD_RECORDING_TYPE, recognitionConfig.getSdRecordingType().getTypeInt());
        bundle.putIntegerArrayList("dict_type", new ArrayList((Collection) recognitionConfig.getDictationTypes().stream().map(new a(0)).collect(Collectors.toList())));
        bundle.putSerializable(SpeechRecognitionConst.Key.TARGET_LOCALE, recognitionConfig.getTargetLocale());
        bundle.putLong(SpeechRecognitionConst.Key.VOICE_FILTER_ID, recognitionConfig.getVoiceFilterId());
        bundle.putBoolean(SpeechRecognitionConst.Key.ENABLED_DETAILED_SEGMENT, recognitionConfig.isEnabledDetailedSegment());
        ASRLog.secure(this.TAG, "configToBundle", bundle);
        return bundle;
    }

    private void handleInternalError(Exception exc) {
        ListenerWrapper listenerWrapper;
        boolean isCancelled = isCancelled();
        String str = this.TAG;
        ASRLog.e(str, "handleInternalError :: " + exc, Boolean.valueOf(isCancelled));
        setError(exc);
        if (!isCancelled && (listenerWrapper = this.listener) != null) {
            listenerWrapper.onError(parseError(4, exc.getMessage()));
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.util.function.Consumer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: java.util.function.Consumer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: java.util.function.Consumer} */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.util.function.Consumer, java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void internalRelease() {
        /*
            r4 = this;
            r0 = 0
            com.samsung.android.sivs.ai.sdkcommon.asr.ISpeechRecognizer r1 = r4.mRecognizer     // Catch:{ Exception -> 0x0012 }
            if (r1 == 0) goto L_0x0014
            r1.release()     // Catch:{ Exception -> 0x0012 }
            java.lang.String r1 = r4.TAG     // Catch:{ Exception -> 0x0012 }
            java.lang.String r2 = "release completed"
            com.samsung.android.sdk.scs.base.utils.Log.i(r1, r2)     // Catch:{ Exception -> 0x0012 }
            goto L_0x0014
        L_0x0010:
            r1 = move-exception
            goto L_0x0045
        L_0x0012:
            r1 = move-exception
            goto L_0x0027
        L_0x0014:
            r4.mRecognizer = r0
            com.samsung.android.sdk.scs.ai.asr.tasks.SttRecognitionTask$ListenerWrapper r1 = r4.listener
            java.util.Optional r1 = java.util.Optional.ofNullable(r1)
            com.samsung.android.sdk.scs.ai.asr.tasks.b r2 = new com.samsung.android.sdk.scs.ai.asr.tasks.b
            r2.<init>()
        L_0x0021:
            r1.ifPresent(r2)
            r4.listener = r0
            return
        L_0x0027:
            java.lang.String r2 = r4.TAG     // Catch:{ all -> 0x0010 }
            java.lang.String r3 = "some error on internalRelease"
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0010 }
            java.lang.Object[] r1 = new java.lang.Object[]{r3, r1}     // Catch:{ all -> 0x0010 }
            com.samsung.android.sdk.scs.ai.asr.ASRLog.w(r2, r1)     // Catch:{ all -> 0x0010 }
            r4.mRecognizer = r0
            com.samsung.android.sdk.scs.ai.asr.tasks.SttRecognitionTask$ListenerWrapper r1 = r4.listener
            java.util.Optional r1 = java.util.Optional.ofNullable(r1)
            com.samsung.android.sdk.scs.ai.asr.tasks.b r2 = new com.samsung.android.sdk.scs.ai.asr.tasks.b
            r2.<init>()
            goto L_0x0021
        L_0x0045:
            r4.mRecognizer = r0
            com.samsung.android.sdk.scs.ai.asr.tasks.SttRecognitionTask$ListenerWrapper r2 = r4.listener
            java.util.Optional r2 = java.util.Optional.ofNullable(r2)
            com.samsung.android.sdk.scs.ai.asr.tasks.b r3 = new com.samsung.android.sdk.scs.ai.asr.tasks.b
            r3.<init>()
            r2.ifPresent(r3)
            r4.listener = r0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.asr.tasks.SttRecognitionTask.internalRelease():void");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$writeToPipe$0(OutputStream outputStream) {
        if (ASRLog.isDevelop) {
            ASRLog.e(this.TAG, "close pipe by watchDog ", Long.valueOf(TIMEOUT_WRITE));
            try {
                outputStream.close();
            } catch (IOException unused) {
            }
        } else {
            ASRLog.w(this.TAG, "watch dog occurs but ignored. ", Long.valueOf(TIMEOUT_WRITE));
        }
    }

    private Bundle parseError(int i2, String str, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("error_code", i2);
        bundle2.putString("error_message", str);
        return bundle2;
    }

    private boolean prepare() {
        try {
            if (this.listener == null) {
                Log.e(this.TAG, "listener is not valid");
                return false;
            }
            Bundle bundle = new Bundle();
            bundle.putString(SpeechRecognitionConst.Key.INFO_SDK_VERSION, BuildConfig.VERSION_NAME);
            bundle.putString(SpeechRecognitionConst.Key.INFO_SDK_TYPE, "release");
            bundle.putString("caller_package", this.callerPackage);
            bundle.putString(SpeechRecognitionConst.Key.INFO_CREATE_CALL_TIMESTRING, LocalDateTime.now().format(formatter));
            bundle.putLong(SpeechRecognitionConst.Key.INFO_CREATE_CALL_TIMESTAMP, System.currentTimeMillis());
            bundle.putInt(SpeechRecognitionConst.Key.INFO_API_LEVEL, SpeechRecognitionConst.VERSION.intValue());
            this.mRecognizer = this.mService.create(bundle);
            Log.i(this.TAG, "prepared started");
            boolean prepare = this.mRecognizer.prepare(configToBundle(this.config));
            String str = this.TAG;
            Log.i(str, "prepared : " + prepare);
            return prepare;
        } catch (Exception e) {
            handleInternalError(e);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void taskCompleted(String str) {
        try {
            if (!isComplete()) {
                String str2 = this.TAG;
                Log.i(str2, "taskCompleted : " + str);
                complete(str);
            } else {
                Log.w(this.TAG, "task already completed");
            }
        } catch (Exception e) {
            handleInternalError(e);
        } catch (Throwable th) {
            internalRelease();
            throw th;
        }
        internalRelease();
    }

    private void writeToPipe(ParcelFileDescriptor parcelFileDescriptor, InputStream inputStream2) {
        AtomicReference<OutputStream> atomicReference;
        NullOutputStream nullOutputStream;
        WatchDog create;
        int read;
        Closeable wrapBlocking;
        ASRLog.i(this.TAG, "writeToPipe started ", Optional.ofNullable(inputStream2).map(new a(1)).orElse(-1));
        long j2 = 0;
        try {
            ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptor);
            try {
                create = WatchDog.create();
                byte[] bArr = new byte[ThumbKind.MEDIUM_KIND_SIZE];
                this.dest.set(autoCloseOutputStream);
                loop0:
                while (true) {
                    long j3 = j2;
                    while (!isCancelled() && (read = inputStream2.read(bArr)) != -1) {
                        if (read == 0) {
                            Thread.sleep(10);
                        } else {
                            wrapBlocking = create.wrapBlocking(new c(this, autoCloseOutputStream), TIMEOUT_WRITE);
                            autoCloseOutputStream.write(bArr, 0, read);
                            if (wrapBlocking != null) {
                                wrapBlocking.close();
                            }
                            j2 += (long) read;
                            if (j2 - j3 > 10000) {
                                String str = this.TAG;
                                Log.i(str, "writeToPipe written " + j2);
                            }
                        }
                    }
                }
                this.mRecognizer.prepare(configToBundle(this.config));
                Thread.sleep(200);
                if (create != null) {
                    create.close();
                }
                autoCloseOutputStream.close();
                String str2 = this.TAG;
                Log.i(str2, "writeToPipe done " + j2);
                atomicReference = this.dest;
                nullOutputStream = new NullOutputStream();
            } catch (Throwable th) {
                autoCloseOutputStream.close();
                throw th;
            }
        } catch (IOException | InterruptedException e) {
            handleInternalError(e);
            String str3 = this.TAG;
            Log.i(str3, "writeToPipe done " + j2);
            atomicReference = this.dest;
            nullOutputStream = new NullOutputStream();
        } catch (RemoteException e7) {
            try {
                throw new RuntimeException(e7);
            } catch (Throwable th2) {
                String str4 = this.TAG;
                Log.i(str4, "writeToPipe done " + j2);
                this.dest.set(new NullOutputStream());
                throw th2;
            }
        } catch (Throwable th3) {
            th.addSuppressed(th3);
        }
        atomicReference.set(nullOutputStream);
        return;
        throw th;
        throw th;
    }

    public void cancel() {
        Log.e(this.TAG, Contract.COMMAND_ID_CANCEL);
        super.cancel();
        ISpeechRecognizer iSpeechRecognizer = this.mRecognizer;
        if (iSpeechRecognizer != null) {
            try {
                iSpeechRecognizer.cancel();
            } catch (RemoteException e) {
                handleInternalError(e);
            }
        }
        InputStream inputStream2 = this.inputStream;
        if (inputStream2 != null) {
            try {
                inputStream2.close();
            } catch (Exception e7) {
                handleInternalError(e7);
            } catch (Throwable th) {
                Log.i(this.TAG, "input stream closed");
                throw th;
            }
            Log.i(this.TAG, "input stream closed");
        }
        try {
            this.dest.get().close();
        } catch (Exception e8) {
            String str = this.TAG;
            Log.w(str, "close dest stream has error but ignored. " + e8.getMessage());
        } catch (Throwable th2) {
            Log.i(this.TAG, "dest stream closed");
            throw th2;
        }
        Log.i(this.TAG, "dest stream closed");
        release();
    }

    public void execute() {
        try {
            if (!prepare()) {
                setError(new Exception("Prepare Failed!!"));
                ListenerWrapper listenerWrapper = this.listener;
                if (listenerWrapper != null) {
                    listenerWrapper.onError(new Bundle());
                }
                return;
            }
            Log.e(this.TAG, "execute");
            ParcelFileDescriptor[] createReliablePipe = ParcelFileDescriptor.createReliablePipe();
            ISpeechRecognizer iSpeechRecognizer = this.mRecognizer;
            if (iSpeechRecognizer != null) {
                if (!iSpeechRecognizer.write(createReliablePipe[0], this.listener)) {
                    createReliablePipe[1].closeWithError("Start Error");
                }
                writeToPipe(createReliablePipe[1], this.inputStream);
                this.inputStream = null;
                return;
            }
            throw new Exception("Recognizer is not ready.");
        } catch (Exception e) {
            handleInternalError(e);
        } finally {
            this.inputStream = null;
        }
    }

    public void release() {
        taskCompleted("release");
    }

    private Bundle parseError(int i2, String str) {
        return parseError(i2, str, new Bundle());
    }
}
