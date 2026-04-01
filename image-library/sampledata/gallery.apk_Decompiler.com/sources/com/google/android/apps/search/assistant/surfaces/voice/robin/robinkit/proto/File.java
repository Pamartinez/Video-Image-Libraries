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
public final class File extends GeneratedMessageLite implements FileOrBuilder {
    /* access modifiers changed from: private */
    public static final File DEFAULT_INSTANCE;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 2;
    public static final int MIME_TYPE_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int URI_FIELD_NUMBER = 1;
    private String displayName_ = "";
    private String mimeType_ = "";
    private String uri_ = "";

    /* renamed from: com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.File$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.File.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder extends GeneratedMessageLite.Builder implements FileOrBuilder {
        public /* synthetic */ Builder(int i2) {
            this();
        }

        public Builder clearDisplayName() {
            copyOnWrite();
            ((File) this.instance).clearDisplayName();
            return this;
        }

        public Builder clearMimeType() {
            copyOnWrite();
            ((File) this.instance).clearMimeType();
            return this;
        }

        public Builder clearUri() {
            copyOnWrite();
            ((File) this.instance).clearUri();
            return this;
        }

        public String getDisplayName() {
            return ((File) this.instance).getDisplayName();
        }

        public ByteString getDisplayNameBytes() {
            return ((File) this.instance).getDisplayNameBytes();
        }

        public String getMimeType() {
            return ((File) this.instance).getMimeType();
        }

        public ByteString getMimeTypeBytes() {
            return ((File) this.instance).getMimeTypeBytes();
        }

        public String getUri() {
            return ((File) this.instance).getUri();
        }

        public ByteString getUriBytes() {
            return ((File) this.instance).getUriBytes();
        }

        public Builder setDisplayName(String str) {
            copyOnWrite();
            ((File) this.instance).setDisplayName(str);
            return this;
        }

        public Builder setDisplayNameBytes(ByteString byteString) {
            copyOnWrite();
            ((File) this.instance).setDisplayNameBytes(byteString);
            return this;
        }

        public Builder setMimeType(String str) {
            copyOnWrite();
            ((File) this.instance).setMimeType(str);
            return this;
        }

        public Builder setMimeTypeBytes(ByteString byteString) {
            copyOnWrite();
            ((File) this.instance).setMimeTypeBytes(byteString);
            return this;
        }

        public Builder setUri(String str) {
            copyOnWrite();
            ((File) this.instance).setUri(str);
            return this;
        }

        public Builder setUriBytes(ByteString byteString) {
            copyOnWrite();
            ((File) this.instance).setUriBytes(byteString);
            return this;
        }

        private Builder() {
            super(File.DEFAULT_INSTANCE);
        }
    }

    static {
        File file = new File();
        DEFAULT_INSTANCE = file;
        GeneratedMessageLite.registerDefaultInstance(File.class, file);
    }

    private File() {
    }

    /* access modifiers changed from: private */
    public void clearDisplayName() {
        this.displayName_ = getDefaultInstance().getDisplayName();
    }

    /* access modifiers changed from: private */
    public void clearMimeType() {
        this.mimeType_ = getDefaultInstance().getMimeType();
    }

    /* access modifiers changed from: private */
    public void clearUri() {
        this.uri_ = getDefaultInstance().getUri();
    }

    public static File getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static File parseDelimitedFrom(InputStream inputStream) {
        return (File) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static File parseFrom(ByteBuffer byteBuffer) {
        return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* access modifiers changed from: private */
    public void setDisplayName(String str) {
        str.getClass();
        this.displayName_ = str;
    }

    /* access modifiers changed from: private */
    public void setDisplayNameBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.displayName_ = byteString.z();
    }

    /* access modifiers changed from: private */
    public void setMimeType(String str) {
        str.getClass();
        this.mimeType_ = str;
    }

    /* access modifiers changed from: private */
    public void setMimeTypeBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.mimeType_ = byteString.z();
    }

    /* access modifiers changed from: private */
    public void setUri(String str) {
        str.getClass();
        this.uri_ = str;
    }

    /* access modifiers changed from: private */
    public void setUriBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.uri_ = byteString.z();
    }

    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        Parser parser;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new File();
            case 2:
                return new Builder(0);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ", new Object[]{"uri_", "displayName_", "mimeType_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser2 = PARSER;
                if (parser2 != null) {
                    return parser2;
                }
                synchronized (File.class) {
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

    public String getDisplayName() {
        return this.displayName_;
    }

    public ByteString getDisplayNameBytes() {
        return ByteString.s(this.displayName_);
    }

    public String getMimeType() {
        return this.mimeType_;
    }

    public ByteString getMimeTypeBytes() {
        return ByteString.s(this.mimeType_);
    }

    public String getUri() {
        return this.uri_;
    }

    public ByteString getUriBytes() {
        return ByteString.s(this.uri_);
    }

    public static Builder newBuilder(File file) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(file);
    }

    public static File parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (File) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static File parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static File parseFrom(ByteString byteString) {
        return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static File parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static File parseFrom(byte[] bArr) {
        return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static File parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static File parseFrom(InputStream inputStream) {
        return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static File parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static File parseFrom(CodedInputStream codedInputStream) {
        return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static File parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (File) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
