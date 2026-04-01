package com.samsung.android.app.sdk.deepsky.textextraction.translate.data;

import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0001\u0005¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationResult;", "", "<init>", "()V", "Error", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationResult$Error;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ImageTranslationResult {

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001:\u0004\t\n\u000b\fB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationResult$Error;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationResult;", "", "code", "<init>", "(I)V", "I", "getCode", "()I", "RequestLanguagePackDownload", "EmptyBlockList", "RendererFail", "Unknown", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Error extends ImageTranslationResult {
        private final int code;

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u000bHÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationResult$Error$EmptyBlockList;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationResult$Error;", "", "code", "<init>", "(I)V", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getCode", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class EmptyBlockList extends Error {
            private final int code;

            public EmptyBlockList(int i2) {
                super(i2);
                this.code = i2;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if ((obj instanceof EmptyBlockList) && this.code == ((EmptyBlockList) obj).code) {
                    return true;
                }
                return false;
            }

            public int hashCode() {
                return Integer.hashCode(this.code);
            }

            public String toString() {
                return C0212a.j(this.code, "EmptyBlockList(code=", ")");
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ EmptyBlockList(int i2, int i7, e eVar) {
                this((i7 & 1) != 0 ? -200 : i2);
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u000bHÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationResult$Error$RendererFail;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationResult$Error;", "", "code", "<init>", "(I)V", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getCode", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class RendererFail extends Error {
            private final int code;

            public RendererFail(int i2) {
                super(i2);
                this.code = i2;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if ((obj instanceof RendererFail) && this.code == ((RendererFail) obj).code) {
                    return true;
                }
                return false;
            }

            public int hashCode() {
                return Integer.hashCode(this.code);
            }

            public String toString() {
                return C0212a.j(this.code, "RendererFail(code=", ")");
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ RendererFail(int i2, int i7, e eVar) {
                this((i7 & 1) != 0 ? -300 : i2);
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u000bHÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationResult$Error$RequestLanguagePackDownload;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationResult$Error;", "", "code", "<init>", "(I)V", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getCode", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class RequestLanguagePackDownload extends Error {
            private final int code;

            public RequestLanguagePackDownload(int i2) {
                super(i2);
                this.code = i2;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if ((obj instanceof RequestLanguagePackDownload) && this.code == ((RequestLanguagePackDownload) obj).code) {
                    return true;
                }
                return false;
            }

            public int hashCode() {
                return Integer.hashCode(this.code);
            }

            public String toString() {
                return C0212a.j(this.code, "RequestLanguagePackDownload(code=", ")");
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ RequestLanguagePackDownload(int i2, int i7, e eVar) {
                this((i7 & 1) != 0 ? -100 : i2);
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u000bHÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationResult$Error$Unknown;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationResult$Error;", "", "code", "<init>", "(I)V", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getCode", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Unknown extends Error {
            private final int code;

            public Unknown(int i2) {
                super(i2);
                this.code = i2;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if ((obj instanceof Unknown) && this.code == ((Unknown) obj).code) {
                    return true;
                }
                return false;
            }

            public int hashCode() {
                return Integer.hashCode(this.code);
            }

            public String toString() {
                return C0212a.j(this.code, "Unknown(code=", ")");
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Unknown(int i2, int i7, e eVar) {
                this((i7 & 1) != 0 ? -400 : i2);
            }
        }

        public Error(int i2) {
            super((e) null);
            this.code = i2;
        }
    }

    public /* synthetic */ ImageTranslationResult(e eVar) {
        this();
    }

    private ImageTranslationResult() {
    }
}
