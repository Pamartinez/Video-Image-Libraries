package com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto;

import com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.File;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.C0153z;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal$ProtobufList;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextQuery extends GeneratedMessageLite implements TextQueryOrBuilder {
    /* access modifiers changed from: private */
    public static final TextQuery DEFAULT_INSTANCE;
    public static final int FILES_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int QUERY_FIELD_NUMBER = 1;
    public static final int SHOULD_AUTO_SUBMIT_FIELD_NUMBER = 2;
    private Internal$ProtobufList files_ = GeneratedMessageLite.emptyProtobufList();
    private String query_ = "";
    private boolean shouldAutoSubmit_;

    /* renamed from: com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.TextQuery$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.TextQuery.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder extends GeneratedMessageLite.Builder implements TextQueryOrBuilder {
        public /* synthetic */ Builder(int i2) {
            this();
        }

        public Builder addAllFiles(Iterable<? extends File> iterable) {
            copyOnWrite();
            ((TextQuery) this.instance).addAllFiles(iterable);
            return this;
        }

        public Builder addFiles(File file) {
            copyOnWrite();
            ((TextQuery) this.instance).addFiles(file);
            return this;
        }

        public Builder clearFiles() {
            copyOnWrite();
            ((TextQuery) this.instance).clearFiles();
            return this;
        }

        public Builder clearQuery() {
            copyOnWrite();
            ((TextQuery) this.instance).clearQuery();
            return this;
        }

        public Builder clearShouldAutoSubmit() {
            copyOnWrite();
            ((TextQuery) this.instance).clearShouldAutoSubmit();
            return this;
        }

        public File getFiles(int i2) {
            return ((TextQuery) this.instance).getFiles(i2);
        }

        public int getFilesCount() {
            return ((TextQuery) this.instance).getFilesCount();
        }

        public List<File> getFilesList() {
            return Collections.unmodifiableList(((TextQuery) this.instance).getFilesList());
        }

        public String getQuery() {
            return ((TextQuery) this.instance).getQuery();
        }

        public ByteString getQueryBytes() {
            return ((TextQuery) this.instance).getQueryBytes();
        }

        public boolean getShouldAutoSubmit() {
            return ((TextQuery) this.instance).getShouldAutoSubmit();
        }

        public Builder removeFiles(int i2) {
            copyOnWrite();
            ((TextQuery) this.instance).removeFiles(i2);
            return this;
        }

        public Builder setFiles(int i2, File file) {
            copyOnWrite();
            ((TextQuery) this.instance).setFiles(i2, file);
            return this;
        }

        public Builder setQuery(String str) {
            copyOnWrite();
            ((TextQuery) this.instance).setQuery(str);
            return this;
        }

        public Builder setQueryBytes(ByteString byteString) {
            copyOnWrite();
            ((TextQuery) this.instance).setQueryBytes(byteString);
            return this;
        }

        public Builder setShouldAutoSubmit(boolean z) {
            copyOnWrite();
            ((TextQuery) this.instance).setShouldAutoSubmit(z);
            return this;
        }

        private Builder() {
            super(TextQuery.DEFAULT_INSTANCE);
        }

        public Builder addFiles(int i2, File file) {
            copyOnWrite();
            ((TextQuery) this.instance).addFiles(i2, file);
            return this;
        }

        public Builder setFiles(int i2, File.Builder builder) {
            copyOnWrite();
            ((TextQuery) this.instance).setFiles(i2, (File) builder.build());
            return this;
        }

        public Builder addFiles(File.Builder builder) {
            copyOnWrite();
            ((TextQuery) this.instance).addFiles((File) builder.build());
            return this;
        }

        public Builder addFiles(int i2, File.Builder builder) {
            copyOnWrite();
            ((TextQuery) this.instance).addFiles(i2, (File) builder.build());
            return this;
        }
    }

    static {
        TextQuery textQuery = new TextQuery();
        DEFAULT_INSTANCE = textQuery;
        GeneratedMessageLite.registerDefaultInstance(TextQuery.class, textQuery);
    }

    private TextQuery() {
    }

    /* access modifiers changed from: private */
    public void addAllFiles(Iterable<? extends File> iterable) {
        ensureFilesIsMutable();
        AbstractMessageLite.addAll(iterable, this.files_);
    }

    /* access modifiers changed from: private */
    public void addFiles(File file) {
        file.getClass();
        ensureFilesIsMutable();
        this.files_.add(file);
    }

    /* access modifiers changed from: private */
    public void clearFiles() {
        this.files_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void clearQuery() {
        this.query_ = getDefaultInstance().getQuery();
    }

    /* access modifiers changed from: private */
    public void clearShouldAutoSubmit() {
        this.shouldAutoSubmit_ = false;
    }

    private void ensureFilesIsMutable() {
        Internal$ProtobufList internal$ProtobufList = this.files_;
        if (!internal$ProtobufList.j()) {
            this.files_ = GeneratedMessageLite.mutableCopy(internal$ProtobufList);
        }
    }

    public static TextQuery getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static TextQuery parseDelimitedFrom(InputStream inputStream) {
        return (TextQuery) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TextQuery parseFrom(ByteBuffer byteBuffer) {
        return (TextQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* access modifiers changed from: private */
    public void removeFiles(int i2) {
        ensureFilesIsMutable();
        this.files_.remove(i2);
    }

    /* access modifiers changed from: private */
    public void setFiles(int i2, File file) {
        file.getClass();
        ensureFilesIsMutable();
        this.files_.set(i2, file);
    }

    /* access modifiers changed from: private */
    public void setQuery(String str) {
        str.getClass();
        this.query_ = str;
    }

    /* access modifiers changed from: private */
    public void setQueryBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.query_ = byteString.z();
    }

    /* access modifiers changed from: private */
    public void setShouldAutoSubmit(boolean z) {
        this.shouldAutoSubmit_ = z;
    }

    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        Parser parser;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new TextQuery();
            case 2:
                return new Builder(0);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001Ȉ\u0002\u0007\u0003\u001b", new Object[]{"query_", "shouldAutoSubmit_", "files_", File.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser2 = PARSER;
                if (parser2 != null) {
                    return parser2;
                }
                synchronized (TextQuery.class) {
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

    public File getFiles(int i2) {
        return (File) this.files_.get(i2);
    }

    public int getFilesCount() {
        return this.files_.size();
    }

    public List<File> getFilesList() {
        return this.files_;
    }

    public FileOrBuilder getFilesOrBuilder(int i2) {
        return (FileOrBuilder) this.files_.get(i2);
    }

    public List<? extends FileOrBuilder> getFilesOrBuilderList() {
        return this.files_;
    }

    public String getQuery() {
        return this.query_;
    }

    public ByteString getQueryBytes() {
        return ByteString.s(this.query_);
    }

    public boolean getShouldAutoSubmit() {
        return this.shouldAutoSubmit_;
    }

    public static Builder newBuilder(TextQuery textQuery) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(textQuery);
    }

    public static TextQuery parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TextQuery) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TextQuery parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (TextQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static TextQuery parseFrom(ByteString byteString) {
        return (TextQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* access modifiers changed from: private */
    public void addFiles(int i2, File file) {
        file.getClass();
        ensureFilesIsMutable();
        this.files_.add(i2, file);
    }

    public static TextQuery parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (TextQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static TextQuery parseFrom(byte[] bArr) {
        return (TextQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TextQuery parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (TextQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static TextQuery parseFrom(InputStream inputStream) {
        return (TextQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TextQuery parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TextQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TextQuery parseFrom(CodedInputStream codedInputStream) {
        return (TextQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static TextQuery parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TextQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
