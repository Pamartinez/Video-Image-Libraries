package com.samsung.android.sdk.scs.ai.asr;

import android.os.Bundle;
import android.util.ArraySet;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.asr.ServerInfo;
import com.samsung.android.sivs.ai.sdkcommon.asr.SpeechRecognitionConst;
import f4.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecognitionConfig {
    private static final String TAG = "RecognitionConfig";
    private Set<DictationType> dictationType = new ArraySet();
    private boolean enableDetailedSegment;
    private boolean enabledAudioCompression;
    private boolean enabledPartial;
    private boolean enabledProgress = false;
    private boolean isCensored;
    private Locale locale;
    private Bundle mExtraBundle;
    private Locale mTargetLocale;
    private long mVoiceFilterId = -1;
    private int sdNotifyIntervalTime;
    private SDRecordingType sdRecordingType;
    private ServerInfo serverInfo;
    private int serverType;
    private boolean speakerDiarisation;
    private ConnectionType type;
    private int viewType = 0;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        private Bundle bundle = null;
        private final Set<DictationType> dictationType = new ArraySet();
        private boolean enableDetailedSegment = false;
        private boolean enabledAudioCompression = false;
        private boolean enabledPartial = false;
        private boolean enabledProgress = false;
        private boolean isCensored = false;
        private boolean lid = false;
        private Locale locale;
        private Locale[] locs = null;
        private int sdNotifyIntervalTime = 0;
        private SDRecordingType sdRecordingType = SDRecordingType.TYPE_NORMAL;
        private ServerInfo serverInfo = null;
        private int serverType = 0;
        private boolean speakerDiarisation = false;
        private Locale targetLocale;
        private ConnectionType type = ConnectionType.NETWORK;
        private int viewType = 0;
        private long voiceFilterId = -1;

        /* access modifiers changed from: private */
        public static /* synthetic */ Set lambda$addDictationType$0(Stream stream) {
            return (Set) stream.collect(Collectors.toSet());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ Set lambda$removeDictationType$1(Stream stream) {
            return (Set) stream.collect(Collectors.toSet());
        }

        public Builder addDictationType(DictationType... dictationTypeArr) {
            this.dictationType.addAll((Collection) Optional.ofNullable(dictationTypeArr).map(new f(27)).map(new f(28)).orElseGet(new h(4)));
            return this;
        }

        public RecognitionConfig build() {
            RecognitionConfig recognitionConfig = new RecognitionConfig();
            if (this.lid) {
                recognitionConfig.setLocale(Locale.ROOT);
                if (this.bundle == null) {
                    this.bundle = new Bundle();
                }
                this.bundle.putSerializable(SpeechRecognitionConst.Key.PRIMARY_LOCALE, this.locale);
            } else {
                recognitionConfig.setLocale(this.locale);
            }
            recognitionConfig.setConnectionType(this.type);
            recognitionConfig.enablePartialResult(this.enabledPartial);
            recognitionConfig.enableAudioCompression(this.enabledAudioCompression);
            recognitionConfig.enableCensor(this.isCensored);
            recognitionConfig.enableProgress(this.enabledProgress);
            recognitionConfig.setServerType(this.serverType);
            recognitionConfig.setViewType(this.viewType);
            recognitionConfig.setDictationTypes(this.dictationType);
            recognitionConfig.setServerInfo((ServerInfo) Optional.ofNullable(this.serverInfo).orElseGet(new h(5)));
            recognitionConfig.setSpeakerDiarisation(this.speakerDiarisation);
            recognitionConfig.setTargetLocale(this.targetLocale);
            recognitionConfig.setExtras(this.bundle);
            int i2 = this.sdNotifyIntervalTime;
            if (i2 > 0) {
                recognitionConfig.setSDNotifyIntervalTime(i2);
            }
            recognitionConfig.setSdRecordingType(this.sdRecordingType);
            recognitionConfig.ignoreDictationByView();
            recognitionConfig.setVoiceFilterId(this.voiceFilterId);
            recognitionConfig.enableDetailedSegment(this.enableDetailedSegment);
            return recognitionConfig;
        }

        public Builder enableAudioCompression(boolean z) {
            this.enabledAudioCompression = z;
            return this;
        }

        public Builder enableCensor(boolean z) {
            this.isCensored = z;
            return this;
        }

        public Builder enableDetailedSegment(boolean z) {
            this.enableDetailedSegment = z;
            return this;
        }

        public Builder enableLocaleRecognition(boolean z) {
            this.lid = z;
            return this;
        }

        public Builder enablePartialResult(boolean z) {
            this.enabledPartial = z;
            return this;
        }

        public Builder enableProgress(boolean z) {
            this.enabledProgress = z;
            return this;
        }

        public Builder enableSpeakerDiarisation(boolean z) {
            this.speakerDiarisation = z;
            return this;
        }

        public Builder generateVoiceFilterId() {
            this.voiceFilterId = 0;
            return this;
        }

        public Builder putExtras(Bundle bundle2) {
            if (bundle2 != null && !bundle2.isEmpty()) {
                if (this.bundle == null) {
                    this.bundle = new Bundle();
                }
                this.bundle.putAll(bundle2);
            }
            return this;
        }

        public Builder removeDictationType(DictationType... dictationTypeArr) {
            this.dictationType.removeAll((Collection) Optional.ofNullable(dictationTypeArr).map(new f(27)).map(new f(29)).orElseGet(new h(4)));
            return this;
        }

        public Builder setAlternativeLocales(Locale... localeArr) {
            this.locs = localeArr;
            Bundle bundle2 = new Bundle();
            ArrayList arrayList = new ArrayList();
            Stream.of(this.locs).map(new g(0)).forEach(new a(arrayList, 17));
            bundle2.putStringArrayList(SpeechRecognitionConst.Key.ALTERNATIVE_LOCALE, arrayList);
            putExtras(bundle2);
            return this;
        }

        public Builder setLocale(Locale locale2) {
            this.locale = locale2;
            if (this.targetLocale == null) {
                this.targetLocale = locale2;
            }
            return this;
        }

        public Builder setSDNotifyIntervalTime(int i2) {
            this.sdNotifyIntervalTime = i2;
            return this;
        }

        public Builder setSdRecordingType(SDRecordingType sDRecordingType) {
            this.sdRecordingType = sDRecordingType;
            return this;
        }

        public Builder setServerInfo(ServerInfo serverInfo2) {
            this.serverInfo = serverInfo2;
            return this;
        }

        public Builder setServerType(int i2) {
            this.serverType = i2;
            return this;
        }

        public Builder setTargetLocale(Locale locale2) {
            this.targetLocale = locale2;
            return this;
        }

        public Builder setType(ConnectionType connectionType) {
            this.type = connectionType;
            return this;
        }

        public Builder setViewType(int i2) {
            this.viewType = i2;
            return this;
        }

        public Builder setVoiceFilterId(long j2) {
            if (j2 > 0) {
                this.voiceFilterId = j2;
            }
            return this;
        }
    }

    private void disableSpakerDiarisation() {
        if (this.type != ConnectionType.LOCAL) {
            this.speakerDiarisation = false;
        }
    }

    /* access modifiers changed from: private */
    public void enableDetailedSegment(boolean z) {
        this.enableDetailedSegment = z;
    }

    /* access modifiers changed from: private */
    public void setTargetLocale(Locale locale2) {
        this.mTargetLocale = locale2;
    }

    /* access modifiers changed from: private */
    public void setVoiceFilterId(long j2) {
        this.mVoiceFilterId = j2;
    }

    public void enableAudioCompression(boolean z) {
        this.enabledAudioCompression = z;
    }

    public void enableCensor(boolean z) {
        this.isCensored = z;
    }

    public void enablePartialResult(boolean z) {
        this.enabledPartial = z;
    }

    public void enableProgress(boolean z) {
        this.enabledProgress = z;
    }

    public boolean enabledPartialResult() {
        return this.enabledPartial;
    }

    public ConnectionType getConnectionType() {
        return this.type;
    }

    public Set<DictationType> getDictationTypes() {
        return (Set) Optional.ofNullable(this.dictationType).orElseGet(new h(4));
    }

    public Bundle getExtras() {
        return this.mExtraBundle;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public int getSdNotifyIntervalTime() {
        return this.sdNotifyIntervalTime;
    }

    public SDRecordingType getSdRecordingType() {
        return this.sdRecordingType;
    }

    public ServerInfo getServerInfo() {
        return this.serverInfo;
    }

    public int getServerType() {
        return this.serverType;
    }

    public Locale getTargetLocale() {
        return this.mTargetLocale;
    }

    public long getVoiceFilterId() {
        return this.mVoiceFilterId;
    }

    public boolean hasExtras() {
        Bundle bundle = this.mExtraBundle;
        if (bundle == null || bundle.isEmpty()) {
            return false;
        }
        return true;
    }

    public void ignoreDictationByView() {
        if (this.viewType == 3) {
            Set<DictationType> set = this.dictationType;
            DictationType dictationType2 = DictationType.TYPING;
            if (set.contains(dictationType2)) {
                Log.d(TAG, "ignored Dictation by View due to view type");
                this.dictationType.remove(dictationType2);
            }
        }
    }

    public boolean isCensored() {
        return this.isCensored;
    }

    public boolean isEnabledAudioCompression() {
        return this.enabledAudioCompression;
    }

    public boolean isEnabledDetailedSegment() {
        return this.enableDetailedSegment;
    }

    public boolean isSpeakerDiarisation() {
        return this.speakerDiarisation;
    }

    public boolean needProgress() {
        return this.enabledProgress;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.type = connectionType;
    }

    public void setDictationTypes(Set<DictationType> set) {
        this.dictationType = set;
    }

    public void setExtras(Bundle bundle) {
        this.mExtraBundle = bundle;
    }

    public void setLocale(Locale locale2) {
        this.locale = locale2;
    }

    public void setSDNotifyIntervalTime(int i2) {
        this.sdNotifyIntervalTime = i2;
    }

    public void setSdRecordingType(SDRecordingType sDRecordingType) {
        this.sdRecordingType = sDRecordingType;
    }

    public void setServerInfo(ServerInfo serverInfo2) {
        this.serverInfo = serverInfo2;
    }

    public void setServerType(int i2) {
        this.serverType = i2;
    }

    public void setSpeakerDiarisation(boolean z) {
        this.speakerDiarisation = z;
    }

    public void setViewType(int i2) {
        this.viewType = i2;
    }
}
