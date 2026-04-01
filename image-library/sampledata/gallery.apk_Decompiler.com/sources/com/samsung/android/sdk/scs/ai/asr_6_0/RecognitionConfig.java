package com.samsung.android.sdk.scs.ai.asr_6_0;

import android.util.ArraySet;
import android.util.Log;
import com.samsung.android.scs.ai.sdkcommon.asr.ServerInfo;
import com.samsung.android.sdk.scs.ai.asr.h;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecognitionConfig {
    private static final String TAG = "RecognitionConfig";
    private Set<DictationType> dictationType;
    private boolean enabledAudioCompression;
    private boolean enabledPartial;
    private boolean isCensored;
    private Locale locale;
    private ServerInfo serverInfo;
    private int serverType;
    private boolean speakerDiarisation;
    private ConnectionType type;
    private int viewType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        private final Set<DictationType> dictationType = new ArraySet();
        private boolean enabledAudioCompression = false;
        private boolean enabledPartial = false;
        private boolean isCensored = false;
        private Locale locale;
        private ServerInfo serverInfo = null;
        private int serverType = 0;
        private boolean speakerDiarisation = false;
        private ConnectionType type = ConnectionType.NETWORK;
        private int viewType = 0;

        /* access modifiers changed from: private */
        public static /* synthetic */ Set lambda$addDictationType$0(Stream stream) {
            return (Set) stream.collect(Collectors.toSet());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ Set lambda$removeDictationType$1(Stream stream) {
            return (Set) stream.collect(Collectors.toSet());
        }

        public Builder addDictationType(DictationType... dictationTypeArr) {
            this.dictationType.addAll((Collection) Optional.ofNullable(dictationTypeArr).map(new c(20)).map(new c(21)).orElseGet(new h(4)));
            return this;
        }

        public RecognitionConfig build() {
            RecognitionConfig recognitionConfig = new RecognitionConfig(0);
            recognitionConfig.setLocale(this.locale);
            recognitionConfig.setConnectionType(this.type);
            recognitionConfig.enablePartialResult(this.enabledPartial);
            recognitionConfig.enableAudioCompression(this.enabledAudioCompression);
            recognitionConfig.enableCensor(this.isCensored);
            recognitionConfig.setServerType(this.serverType);
            recognitionConfig.setViewType(this.viewType);
            recognitionConfig.setDictationTypes(this.dictationType);
            recognitionConfig.setServerInfo((ServerInfo) Optional.ofNullable(this.serverInfo).orElseGet(new f(3)));
            recognitionConfig.setSpeakerDiarisation(this.speakerDiarisation);
            recognitionConfig.ignoreDictationByView();
            recognitionConfig.disableSpakerDiarisation();
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

        public Builder enablePartialResult(boolean z) {
            this.enabledPartial = z;
            return this;
        }

        public Builder enableSpeakerDiarisation(boolean z) {
            this.speakerDiarisation = z;
            return this;
        }

        public Builder removeDictationType(DictationType... dictationTypeArr) {
            this.dictationType.removeAll((Collection) Optional.ofNullable(dictationTypeArr).map(new c(20)).map(new c(22)).orElseGet(new h(4)));
            return this;
        }

        public Builder setLocale(Locale locale2) {
            this.locale = locale2;
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

        public Builder setType(ConnectionType connectionType) {
            this.type = connectionType;
            return this;
        }

        public Builder setViewType(int i2) {
            this.viewType = i2;
            return this;
        }
    }

    public /* synthetic */ RecognitionConfig(int i2) {
        this();
    }

    /* access modifiers changed from: private */
    public void disableSpakerDiarisation() {
        if (this.type != ConnectionType.LOCAL) {
            this.speakerDiarisation = false;
        }
    }

    /* access modifiers changed from: private */
    public void enableAudioCompression(boolean z) {
        this.enabledAudioCompression = z;
    }

    /* access modifiers changed from: private */
    public void enableCensor(boolean z) {
        this.isCensored = z;
    }

    /* access modifiers changed from: private */
    public void enablePartialResult(boolean z) {
        this.enabledPartial = z;
    }

    /* access modifiers changed from: private */
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

    /* access modifiers changed from: private */
    public void setConnectionType(ConnectionType connectionType) {
        this.type = connectionType;
    }

    /* access modifiers changed from: private */
    public void setDictationTypes(Set<DictationType> set) {
        this.dictationType = set;
    }

    /* access modifiers changed from: private */
    public void setLocale(Locale locale2) {
        this.locale = locale2;
    }

    /* access modifiers changed from: private */
    public void setServerInfo(ServerInfo serverInfo2) {
        this.serverInfo = serverInfo2;
    }

    /* access modifiers changed from: private */
    public void setServerType(int i2) {
        this.serverType = i2;
    }

    /* access modifiers changed from: private */
    public void setSpeakerDiarisation(boolean z) {
        this.speakerDiarisation = z;
    }

    /* access modifiers changed from: private */
    public void setViewType(int i2) {
        this.viewType = i2;
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

    public Locale getLocale() {
        return this.locale;
    }

    public ServerInfo getServerInfo() {
        return this.serverInfo;
    }

    public int getServerType() {
        return this.serverType;
    }

    public boolean isCensored() {
        return this.isCensored;
    }

    public boolean isEnabledAudioCompression() {
        return this.enabledAudioCompression;
    }

    public boolean isSpeakerDiarisation() {
        return this.speakerDiarisation;
    }

    private RecognitionConfig() {
        this.dictationType = new ArraySet();
        this.viewType = 0;
    }
}
