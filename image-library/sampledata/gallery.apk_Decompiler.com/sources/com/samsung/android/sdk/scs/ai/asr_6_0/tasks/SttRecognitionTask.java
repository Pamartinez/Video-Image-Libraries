package com.samsung.android.sdk.scs.ai.asr_6_0.tasks;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import ba.C0582a;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.scs.ai.sdkcommon.asr.DialogInfo;
import com.samsung.android.scs.ai.sdkcommon.asr.IRecognitionListener;
import com.samsung.android.scs.ai.sdkcommon.asr.ISpeechRecognizer;
import com.samsung.android.sdk.scs.ai.asr.ASRLog;
import com.samsung.android.sdk.scs.ai.asr.io.NullOutputStream;
import com.samsung.android.sdk.scs.ai.asr.tasks.a;
import com.samsung.android.sdk.scs.ai.asr_6_0.RecognitionConfig;
import com.samsung.android.sdk.scs.ai.asr_6_0.SpeechRecognitionListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SttRecognitionTask extends RecognitionTask<String> {
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

        public void onError(Bundle bundle) {
            String string = bundle.getString("error_message");
            int i2 = bundle.getInt("error_code");
            ASRLog.secure(this.TAG, "onError", this.root, bundle);
            this.root.onError(i2, string, new Bundle());
            this.complete.accept(string);
        }

        public void onPartialResults(Bundle bundle) {
            bundle.setClassLoader(DialogInfo.class.getClassLoader());
            ASRLog.secure(this.TAG, "onPartialResults", this.root, bundle);
            this.root.onPartialResults(bundle.getString("result"), bundle);
        }

        public void onResults(Bundle bundle) {
            bundle.setClassLoader(DialogInfo.class.getClassLoader());
            String string = bundle.getString("result");
            ASRLog.secure(this.TAG, "onResults", this.root, bundle);
            this.root.onResults(string, bundle);
            Consumer<String> consumer = this.complete;
            consumer.accept("done:" + Optional.ofNullable(string).map(new a(2)).orElse(0));
        }

        public void release() {
            ASRLog.i(this.TAG, "release listener", this.root);
            this.root = SttRecognitionTask.ignoreResult;
        }
    }

    public SttRecognitionTask(RecognitionConfig recognitionConfig, InputStream inputStream2, SpeechRecognitionListener speechRecognitionListener) {
        String str = "SttTask6.0@" + hashCode();
        this.TAG = str;
        Log.e(str, "create task");
        this.config = recognitionConfig;
        this.inputStream = inputStream2;
        this.listener = new ListenerWrapper(str, speechRecognitionListener, new C0582a(16, this));
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

    private Bundle parseError(int i2, String str, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("error_code", i2);
        bundle2.putString("error_message", str);
        return bundle2;
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object, java.util.function.Function] */
    private boolean prepare() {
        try {
            if (this.listener == null) {
                Log.e(this.TAG, "listener is not valid");
                return false;
            }
            this.mRecognizer = this.mService.create(new Bundle());
            Bundle bundle = new Bundle();
            bundle.putSerializable("locale", this.config.getLocale());
            bundle.putInt("connection_type", this.config.getConnectionType().getTypeInt());
            bundle.putBoolean("enabled_partial", this.config.enabledPartialResult());
            bundle.putBoolean("enabled_audio_compression", this.config.isEnabledAudioCompression());
            bundle.putBoolean("enabled_censor", this.config.isCensored());
            bundle.putInt("server_type", this.config.getServerType());
            bundle.putParcelable("app_server_type", this.config.getServerInfo());
            bundle.putBoolean("enable_speaker_diarisation", this.config.isSpeakerDiarisation());
            bundle.putIntegerArrayList("dict_type", new ArrayList((Collection) this.config.getDictationTypes().stream().map(new Object()).collect(Collectors.toList())));
            Log.i(this.TAG, "prepared started");
            boolean prepare = this.mRecognizer.prepare(bundle);
            String str = this.TAG;
            Log.i(str, "prepared : " + prepare);
            return prepare;
        } catch (RemoteException e) {
            handleInternalError(e);
            return false;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.util.function.Consumer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.util.function.Consumer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: java.util.function.Consumer} */
    /* JADX WARNING: type inference failed for: r2v0, types: [java.util.function.Consumer, java.lang.Object] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void taskCompleted(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r0 = "taskCompleted : "
            boolean r1 = r4.isComplete()
            if (r1 != 0) goto L_0x0062
            r1 = 0
            java.lang.String r2 = r4.TAG     // Catch:{ Exception -> 0x0028 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0028 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0028 }
            r3.append(r5)     // Catch:{ Exception -> 0x0028 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0028 }
            android.util.Log.e(r2, r0)     // Catch:{ Exception -> 0x0028 }
            r4.complete(r5)     // Catch:{ Exception -> 0x0028 }
            com.samsung.android.scs.ai.sdkcommon.asr.ISpeechRecognizer r5 = r4.mRecognizer     // Catch:{ Exception -> 0x0028 }
            if (r5 == 0) goto L_0x002a
            r5.release()     // Catch:{ Exception -> 0x0028 }
            goto L_0x002a
        L_0x0026:
            r5 = move-exception
            goto L_0x004f
        L_0x0028:
            r5 = move-exception
            goto L_0x003d
        L_0x002a:
            r4.mRecognizer = r1
            com.samsung.android.sdk.scs.ai.asr_6_0.tasks.SttRecognitionTask$ListenerWrapper r5 = r4.listener
            java.util.Optional r5 = java.util.Optional.ofNullable(r5)
            com.samsung.android.sdk.scs.ai.asr_6_0.tasks.b r0 = new com.samsung.android.sdk.scs.ai.asr_6_0.tasks.b
            r0.<init>()
        L_0x0037:
            r5.ifPresent(r0)
            r4.listener = r1
            goto L_0x004e
        L_0x003d:
            r4.handleInternalError(r5)     // Catch:{ all -> 0x0026 }
            r4.mRecognizer = r1
            com.samsung.android.sdk.scs.ai.asr_6_0.tasks.SttRecognitionTask$ListenerWrapper r5 = r4.listener
            java.util.Optional r5 = java.util.Optional.ofNullable(r5)
            com.samsung.android.sdk.scs.ai.asr_6_0.tasks.b r0 = new com.samsung.android.sdk.scs.ai.asr_6_0.tasks.b
            r0.<init>()
            goto L_0x0037
        L_0x004e:
            return
        L_0x004f:
            r4.mRecognizer = r1
            com.samsung.android.sdk.scs.ai.asr_6_0.tasks.SttRecognitionTask$ListenerWrapper r0 = r4.listener
            java.util.Optional r0 = java.util.Optional.ofNullable(r0)
            com.samsung.android.sdk.scs.ai.asr_6_0.tasks.b r2 = new com.samsung.android.sdk.scs.ai.asr_6_0.tasks.b
            r2.<init>()
            r0.ifPresent(r2)
            r4.listener = r1
            throw r5
        L_0x0062:
            java.lang.String r4 = r4.TAG
            java.lang.String r5 = "task already completed"
            android.util.Log.w(r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.asr_6_0.tasks.SttRecognitionTask.taskCompleted(java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0076, code lost:
        r12 = r11.TAG;
        android.util.Log.i(r12, "writeToPipe done " + r1);
        r11 = r11.dest;
        r12 = new com.samsung.android.sdk.scs.ai.asr.io.NullOutputStream();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeToPipe(android.os.ParcelFileDescriptor r12, java.io.InputStream r13) {
        /*
            r11 = this;
            java.lang.String r0 = "writeToPipe done "
            java.lang.String r1 = r11.TAG
            java.util.Optional r2 = java.util.Optional.ofNullable(r13)
            com.samsung.android.sdk.scs.ai.asr.tasks.a r3 = new com.samsung.android.sdk.scs.ai.asr.tasks.a
            r4 = 1
            r3.<init>(r4)
            java.util.Optional r2 = r2.map(r3)
            r3 = -1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            java.lang.Object r2 = r2.orElse(r4)
            java.lang.String r4 = "writeToPipe started "
            java.lang.Object[] r2 = new java.lang.Object[]{r4, r2}
            com.samsung.android.sdk.scs.ai.asr.ASRLog.i(r1, r2)
            r1 = 0
            android.os.ParcelFileDescriptor$AutoCloseOutputStream r4 = new android.os.ParcelFileDescriptor$AutoCloseOutputStream     // Catch:{ IOException | InterruptedException -> 0x0094 }
            r4.<init>(r12)     // Catch:{ IOException | InterruptedException -> 0x0094 }
            r12 = 320(0x140, float:4.48E-43)
            byte[] r12 = new byte[r12]     // Catch:{ all -> 0x004b }
            java.util.concurrent.atomic.AtomicReference<java.io.OutputStream> r5 = r11.dest     // Catch:{ all -> 0x004b }
            r5.set(r4)     // Catch:{ all -> 0x004b }
        L_0x0036:
            r5 = r1
        L_0x0037:
            boolean r7 = r11.isCancelled()     // Catch:{ all -> 0x004b }
            if (r7 != 0) goto L_0x0073
            int r7 = r13.read(r12)     // Catch:{ all -> 0x004b }
            if (r7 == r3) goto L_0x0073
            if (r7 != 0) goto L_0x004d
            r7 = 10
            java.lang.Thread.sleep(r7)     // Catch:{ all -> 0x004b }
            goto L_0x0037
        L_0x004b:
            r12 = move-exception
            goto L_0x0096
        L_0x004d:
            r8 = 0
            r4.write(r12, r8, r7)     // Catch:{ all -> 0x004b }
            long r7 = (long) r7     // Catch:{ all -> 0x004b }
            long r1 = r1 + r7
            long r7 = r1 - r5
            r9 = 10000(0x2710, double:4.9407E-320)
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 <= 0) goto L_0x0037
            java.lang.String r5 = r11.TAG     // Catch:{ all -> 0x004b }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x004b }
            r6.<init>()     // Catch:{ all -> 0x004b }
            java.lang.String r7 = "writeToPipe written "
            r6.append(r7)     // Catch:{ all -> 0x004b }
            r6.append(r1)     // Catch:{ all -> 0x004b }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x004b }
            android.util.Log.i(r5, r6)     // Catch:{ all -> 0x004b }
            goto L_0x0036
        L_0x0073:
            r4.close()     // Catch:{ IOException | InterruptedException -> 0x0094 }
            java.lang.String r12 = r11.TAG
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r0)
            r13.append(r1)
            java.lang.String r13 = r13.toString()
            android.util.Log.i(r12, r13)
            java.util.concurrent.atomic.AtomicReference<java.io.OutputStream> r11 = r11.dest
            com.samsung.android.sdk.scs.ai.asr.io.NullOutputStream r12 = new com.samsung.android.sdk.scs.ai.asr.io.NullOutputStream
            r12.<init>()
        L_0x008e:
            r11.set(r12)
            return
        L_0x0092:
            r12 = move-exception
            goto L_0x00bb
        L_0x0094:
            r12 = move-exception
            goto L_0x009f
        L_0x0096:
            r4.close()     // Catch:{ all -> 0x009a }
            goto L_0x009e
        L_0x009a:
            r13 = move-exception
            r12.addSuppressed(r13)     // Catch:{ IOException | InterruptedException -> 0x0094 }
        L_0x009e:
            throw r12     // Catch:{ IOException | InterruptedException -> 0x0094 }
        L_0x009f:
            r11.handleInternalError(r12)     // Catch:{ all -> 0x0092 }
            java.lang.String r12 = r11.TAG
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r0)
            r13.append(r1)
            java.lang.String r13 = r13.toString()
            android.util.Log.i(r12, r13)
            java.util.concurrent.atomic.AtomicReference<java.io.OutputStream> r11 = r11.dest
            com.samsung.android.sdk.scs.ai.asr.io.NullOutputStream r12 = new com.samsung.android.sdk.scs.ai.asr.io.NullOutputStream
            r12.<init>()
            goto L_0x008e
        L_0x00bb:
            java.lang.String r13 = r11.TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            r3.append(r1)
            java.lang.String r0 = r3.toString()
            android.util.Log.i(r13, r0)
            java.util.concurrent.atomic.AtomicReference<java.io.OutputStream> r11 = r11.dest
            com.samsung.android.sdk.scs.ai.asr.io.NullOutputStream r13 = new com.samsung.android.sdk.scs.ai.asr.io.NullOutputStream
            r13.<init>()
            r11.set(r13)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.asr_6_0.tasks.SttRecognitionTask.writeToPipe(android.os.ParcelFileDescriptor, java.io.InputStream):void");
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
