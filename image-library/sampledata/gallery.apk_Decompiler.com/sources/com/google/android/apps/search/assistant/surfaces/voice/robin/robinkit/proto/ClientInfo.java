package com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.C0153z;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ClientInfo extends GeneratedMessageLite implements ClientInfoOrBuilder {
    public static final int APP_NAME_FIELD_NUMBER = 1;
    public static final int APP_PACKAGE_NAME_FIELD_NUMBER = 3;
    public static final int APP_VERSION_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final ClientInfo DEFAULT_INSTANCE;
    public static final int IS_WORK_PROFILE_FIELD_NUMBER = 4;
    private static volatile Parser PARSER;
    private String appName_ = "";
    private String appPackageName_ = "";
    private String appVersion_ = "";
    private boolean isWorkProfile_;

    /* renamed from: com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.ClientInfo$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r0 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.ClientInfo.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder extends GeneratedMessageLite.Builder implements ClientInfoOrBuilder {
        public /* synthetic */ Builder(int i2) {
            this();
        }

        public Builder clearAppName() {
            copyOnWrite();
            ((ClientInfo) this.instance).clearAppName();
            return this;
        }

        public Builder clearAppPackageName() {
            copyOnWrite();
            ((ClientInfo) this.instance).clearAppPackageName();
            return this;
        }

        public Builder clearAppVersion() {
            copyOnWrite();
            ((ClientInfo) this.instance).clearAppVersion();
            return this;
        }

        public Builder clearIsWorkProfile() {
            copyOnWrite();
            ((ClientInfo) this.instance).clearIsWorkProfile();
            return this;
        }

        public String getAppName() {
            return ((ClientInfo) this.instance).getAppName();
        }

        public ByteString getAppNameBytes() {
            return ((ClientInfo) this.instance).getAppNameBytes();
        }

        public String getAppPackageName() {
            return ((ClientInfo) this.instance).getAppPackageName();
        }

        public ByteString getAppPackageNameBytes() {
            return ((ClientInfo) this.instance).getAppPackageNameBytes();
        }

        public String getAppVersion() {
            return ((ClientInfo) this.instance).getAppVersion();
        }

        public ByteString getAppVersionBytes() {
            return ((ClientInfo) this.instance).getAppVersionBytes();
        }

        public boolean getIsWorkProfile() {
            return ((ClientInfo) this.instance).getIsWorkProfile();
        }

        public Builder setAppName(String str) {
            copyOnWrite();
            ((ClientInfo) this.instance).setAppName(str);
            return this;
        }

        public Builder setAppNameBytes(ByteString byteString) {
            copyOnWrite();
            ((ClientInfo) this.instance).setAppNameBytes(byteString);
            return this;
        }

        public Builder setAppPackageName(String str) {
            copyOnWrite();
            ((ClientInfo) this.instance).setAppPackageName(str);
            return this;
        }

        public Builder setAppPackageNameBytes(ByteString byteString) {
            copyOnWrite();
            ((ClientInfo) this.instance).setAppPackageNameBytes(byteString);
            return this;
        }

        public Builder setAppVersion(String str) {
            copyOnWrite();
            ((ClientInfo) this.instance).setAppVersion(str);
            return this;
        }

        public Builder setAppVersionBytes(ByteString byteString) {
            copyOnWrite();
            ((ClientInfo) this.instance).setAppVersionBytes(byteString);
            return this;
        }

        public Builder setIsWorkProfile(boolean z) {
            copyOnWrite();
            ((ClientInfo) this.instance).setIsWorkProfile(z);
            return this;
        }

        private Builder() {
            super(ClientInfo.DEFAULT_INSTANCE);
        }
    }

    static {
        ClientInfo clientInfo = new ClientInfo();
        DEFAULT_INSTANCE = clientInfo;
        GeneratedMessageLite.registerDefaultInstance(ClientInfo.class, clientInfo);
    }

    private ClientInfo() {
    }

    /* access modifiers changed from: private */
    public void clearAppName() {
        this.appName_ = getDefaultInstance().getAppName();
    }

    /* access modifiers changed from: private */
    public void clearAppPackageName() {
        this.appPackageName_ = getDefaultInstance().getAppPackageName();
    }

    /* access modifiers changed from: private */
    public void clearAppVersion() {
        this.appVersion_ = getDefaultInstance().getAppVersion();
    }

    /* access modifiers changed from: private */
    public void clearIsWorkProfile() {
        this.isWorkProfile_ = false;
    }

    public static ClientInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static ClientInfo parseDelimitedFrom(InputStream inputStream) {
        return (ClientInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ClientInfo parseFrom(ByteBuffer byteBuffer) {
        return (ClientInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* access modifiers changed from: private */
    public void setAppName(String str) {
        str.getClass();
        this.appName_ = str;
    }

    /* access modifiers changed from: private */
    public void setAppNameBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.appName_ = byteString.z();
    }

    /* access modifiers changed from: private */
    public void setAppPackageName(String str) {
        str.getClass();
        this.appPackageName_ = str;
    }

    /* access modifiers changed from: private */
    public void setAppPackageNameBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.appPackageName_ = byteString.z();
    }

    /* access modifiers changed from: private */
    public void setAppVersion(String str) {
        str.getClass();
        this.appVersion_ = str;
    }

    /* access modifiers changed from: private */
    public void setAppVersionBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.appVersion_ = byteString.z();
    }

    /* access modifiers changed from: private */
    public void setIsWorkProfile(boolean z) {
        this.isWorkProfile_ = z;
    }

    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        Parser parser;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ClientInfo();
            case 2:
                return new Builder(0);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004\u0007", new Object[]{"appName_", "appVersion_", "appPackageName_", "isWorkProfile_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser2 = PARSER;
                if (parser2 != null) {
                    return parser2;
                }
                synchronized (ClientInfo.class) {
                    try {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new C0153z(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public String getAppName() {
        return this.appName_;
    }

    public ByteString getAppNameBytes() {
        return ByteString.s(this.appName_);
    }

    public String getAppPackageName() {
        return this.appPackageName_;
    }

    public ByteString getAppPackageNameBytes() {
        return ByteString.s(this.appPackageName_);
    }

    public String getAppVersion() {
        return this.appVersion_;
    }

    public ByteString getAppVersionBytes() {
        return ByteString.s(this.appVersion_);
    }

    public boolean getIsWorkProfile() {
        return this.isWorkProfile_;
    }

    public static Builder newBuilder(ClientInfo clientInfo) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(clientInfo);
    }

    public static ClientInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ClientInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ClientInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (ClientInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ClientInfo parseFrom(ByteString byteString) {
        return (ClientInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ClientInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (ClientInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ClientInfo parseFrom(byte[] bArr) {
        return (ClientInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ClientInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (ClientInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ClientInfo parseFrom(InputStream inputStream) {
        return (ClientInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ClientInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ClientInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ClientInfo parseFrom(CodedInputStream codedInputStream) {
        return (ClientInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ClientInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ClientInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
