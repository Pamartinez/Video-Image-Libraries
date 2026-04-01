package com.samsung.android.sdk.scs.ai.visual;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.samsung.android.sdk.scs.ai.visual.MultiModalParam;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageEditorParam {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AiEraserModeBuilder<T> extends Builder {
        public AiEraserModeBuilder(Builder builder) {
            super(builder.params);
            priority(0);
        }

        public AiEraserModeBuilder<T> dilationRadius(int i2) {
            this.params.putInt("dilationRadius", i2);
            return this;
        }

        public AiEraserModeBuilder<T> mask(T t) {
            if (t instanceof Bitmap) {
                this.params.putParcelable("alphaBitmap", (Parcelable) t);
                return this;
            }
            if (t instanceof ParcelFileDescriptor) {
                this.params.putParcelable("alphaPfd", (Parcelable) t);
            }
            return this;
        }

        public AiEraserModeBuilder<T> priority(int i2) {
            this.params.putInt("priority", i2);
            return this;
        }

        public AiEraserModeBuilder<T> safetyFilter(boolean z) {
            this.params.putBoolean("safetyFilter", z);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BitmapBuilder extends InputBuilder<Bitmap> {
        public BitmapBuilder(Builder builder) {
            super(builder, "bitmap");
        }

        public AiEraserModeBuilder<Bitmap> asAiEraserMode() {
            return super.asAiEraserMode();
        }

        public GenEditModeBuilder<Bitmap> asGenEditMode() {
            return super.asGenEditMode();
        }

        public HarmonizationModeBuilder<Bitmap> asHarmonizationMode() {
            return super.asHarmonizationMode();
        }

        public ImageConversionModeBuilder<Bitmap> asImageConversion() {
            return super.asImageConversion();
        }

        public PetPortraitModeBuilder<Bitmap> asPetPortraitMode() {
            return super.asPetPortraitMode();
        }

        public PortraitModeBuilder<Bitmap> asPortraitMode() {
            return super.asPortraitMode();
        }

        public RestylingBuilder<Bitmap> asRestylingMode() {
            return super.asRestylingMode();
        }

        public Sketch2ImageModeBuilder<Bitmap> asSketch2ImageMode() {
            return super.asSketch2ImageMode();
        }

        public SketchGuidedEditingModeBuilder<Bitmap> asSketchGuidedEditingMode() {
            return super.asSketchGuidedEditingMode();
        }

        public WallpaperModeBuilder<Bitmap> asWallpaperMode() {
            return super.asWallpaperMode();
        }

        public BitmapBuilder input(Bitmap bitmap) {
            this.params.putParcelable("inputBitmap", bitmap);
            return this;
        }

        public BitmapBuilder sampleCount(int i2) {
            this.params.putInt("sampleCount", i2);
            return this;
        }

        public BitmapBuilder serviceVersion(String str) {
            this.params.putString("serviceVersion", str);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BitmapTextBuilder extends InputBuilder<Bitmap> {
        public BitmapTextBuilder(Builder builder) {
            super(builder, "bitmapText");
        }

        public ImageConversionModeBuilder<Bitmap> asImageConversion() {
            return super.asImageConversion();
        }

        public BitmapTextBuilder input(String str) {
            this.params.putString("inputText", str);
            return this;
        }

        public BitmapTextBuilder sampleCount(int i2) {
            this.params.putInt("sampleCount", i2);
            return this;
        }

        public BitmapTextBuilder serviceVersion(String str) {
            this.params.putString("serviceVersion", str);
            return this;
        }

        public BitmapTextBuilder input(Bitmap bitmap) {
            this.params.putParcelable("inputBitmap", bitmap);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GenEditModeBuilder<T> extends Builder {
        public GenEditModeBuilder(Builder builder) {
            super(builder.params);
            priority(0);
        }

        public GenEditModeBuilder<T> mask(T t) {
            if (t instanceof Bitmap) {
                this.params.putParcelable("alphaBitmap", (Parcelable) t);
                return this;
            }
            if (t instanceof ParcelFileDescriptor) {
                this.params.putParcelable("alphaPfd", (Parcelable) t);
            }
            return this;
        }

        public GenEditModeBuilder<T> priority(int i2) {
            this.params.putInt("priority", i2);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GenStickerModeBuilder<T> extends Builder {
        public GenStickerModeBuilder(Builder builder) {
            super(builder.params);
        }

        public GenStickerModeBuilder<T> style(String str) {
            this.params.putString("style", str);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class HarmonizationModeBuilder<T> extends Builder {
        public HarmonizationModeBuilder(Builder builder) {
            super(builder.params);
            priority(0);
        }

        public HarmonizationModeBuilder<T> mask(T t) {
            if (t instanceof Bitmap) {
                this.params.putParcelable("alphaBitmap", (Parcelable) t);
                return this;
            }
            if (t instanceof ParcelFileDescriptor) {
                this.params.putParcelable("alphaPfd", (Parcelable) t);
            }
            return this;
        }

        public HarmonizationModeBuilder<T> priority(int i2) {
            this.params.putInt("priority", i2);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ImageConversionModeBuilder<T> extends Builder {
        public ImageConversionModeBuilder(Builder builder) {
            super(builder.params);
        }

        public ImageConversionModeBuilder<T> conversionInputType(String str) {
            this.params.putString("conversionInputType", str);
            return this;
        }

        public ImageConversionModeBuilder<T> personDetection(boolean z) {
            this.params.putBoolean("personDetection", z);
            return this;
        }

        public ImageConversionModeBuilder<T> personGeneration(String str) {
            this.params.putString("personGeneration", str);
            return this;
        }

        public ImageConversionModeBuilder<T> style(String str) {
            this.params.putString("style", str);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class InputBuilder<T> extends Builder {
        protected final ArrayList<Parcelable> outputs = new ArrayList<>();

        public InputBuilder(Builder builder, String str) {
            super(builder.params);
            this.params.putString("inputType", str);
        }

        public void addOutputParcelable(Parcelable parcelable) {
            this.outputs.add(parcelable);
        }

        public AiEraserModeBuilder<T> asAiEraserMode() {
            return new AiEraserModeBuilder<>(this);
        }

        public GenEditModeBuilder<T> asGenEditMode() {
            return new GenEditModeBuilder<>(this);
        }

        public GenStickerModeBuilder<T> asGenSticker() {
            return new GenStickerModeBuilder<>(this);
        }

        public HarmonizationModeBuilder<T> asHarmonizationMode() {
            return new HarmonizationModeBuilder<>(this);
        }

        public ImageConversionModeBuilder<T> asImageConversion() {
            return new ImageConversionModeBuilder<>(this);
        }

        public PetPortraitModeBuilder<T> asPetPortraitMode() {
            return new PetPortraitModeBuilder<>(this);
        }

        public PortraitModeBuilder<T> asPortraitMode() {
            return new PortraitModeBuilder<>(this);
        }

        public RestylingBuilder<T> asRestylingMode() {
            return new RestylingBuilder<>(this);
        }

        public Sketch2ImageModeBuilder<T> asSketch2ImageMode() {
            return new Sketch2ImageModeBuilder<>(this);
        }

        public SketchGuidedEditingModeBuilder<T> asSketchGuidedEditingMode() {
            return new SketchGuidedEditingModeBuilder<>(this);
        }

        public WallpaperModeBuilder<T> asWallpaperMode() {
            return new WallpaperModeBuilder<>(this);
        }

        public abstract InputBuilder<T> input(T t);

        public abstract InputBuilder<T> sampleCount(int i2);

        public abstract InputBuilder<T> serviceVersion(String str);

        public void addOutputParcelable(List<? extends Parcelable> list) {
            this.outputs.addAll(list);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ParamBuilder extends Builder {
        public ParamBuilder() {
        }

        public BitmapBuilder asBitmap() {
            return new BitmapBuilder(this);
        }

        public BitmapTextBuilder asBitmapText() {
            return new BitmapTextBuilder(this);
        }

        public MultiModalParam.MultiModalModeBuilder asMultiModal() {
            return new MultiModalParam.MultiModalModeBuilder(this);
        }

        public ParcelFileDescriptorBuilder asParcelFileDescriptor() {
            return new ParcelFileDescriptorBuilder(this);
        }

        public ParcelFileDescriptorTextBuilder asParcelFileDescriptorText() {
            return new ParcelFileDescriptorTextBuilder(this);
        }

        public TextBuilder asText() {
            return new TextBuilder(this);
        }

        public ParamBuilder(Bundle bundle) {
            super(bundle);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ParcelFileDescriptorBuilder extends InputBuilder<ParcelFileDescriptor> {
        public ParcelFileDescriptorBuilder(Builder builder) {
            super(builder, "file");
        }

        public ParcelFileDescriptorBuilder addOutput(ParcelFileDescriptor parcelFileDescriptor) {
            super.addOutputParcelable((Parcelable) parcelFileDescriptor);
            this.params.putParcelableArrayList("outputPfdList", this.outputs);
            return this;
        }

        public AiEraserModeBuilder<ParcelFileDescriptor> asAiEraserMode() {
            return super.asAiEraserMode();
        }

        public GenEditModeBuilder<ParcelFileDescriptor> asGenEditMode() {
            return super.asGenEditMode();
        }

        public HarmonizationModeBuilder<ParcelFileDescriptor> asHarmonizationMode() {
            return super.asHarmonizationMode();
        }

        public ImageConversionModeBuilder<ParcelFileDescriptor> asImageConversion() {
            return super.asImageConversion();
        }

        public PetPortraitModeBuilder<ParcelFileDescriptor> asPetPortraitMode() {
            return super.asPetPortraitMode();
        }

        public PortraitModeBuilder<ParcelFileDescriptor> asPortraitMode() {
            return super.asPortraitMode();
        }

        public RestylingBuilder<ParcelFileDescriptor> asRestylingMode() {
            return new RestylingBuilder<>(this);
        }

        public Sketch2ImageModeBuilder<ParcelFileDescriptor> asSketch2ImageMode() {
            return super.asSketch2ImageMode();
        }

        public SketchGuidedEditingModeBuilder<ParcelFileDescriptor> asSketchGuidedEditingMode() {
            return super.asSketchGuidedEditingMode();
        }

        public WallpaperModeBuilder<ParcelFileDescriptor> asWallpaperMode() {
            return super.asWallpaperMode();
        }

        public ParcelFileDescriptorBuilder input(ParcelFileDescriptor parcelFileDescriptor) {
            this.params.putParcelable("inputPfd", parcelFileDescriptor);
            return this;
        }

        public ParcelFileDescriptorBuilder sampleCount(int i2) {
            this.params.putInt("sampleCount", i2);
            return this;
        }

        public ParcelFileDescriptorBuilder serviceVersion(String str) {
            this.params.putString("serviceVersion", str);
            return this;
        }

        public ParcelFileDescriptorBuilder addOutput(List<ParcelFileDescriptor> list) {
            super.addOutputParcelable((List<? extends Parcelable>) list);
            this.params.putParcelableArrayList("outputPfdList", this.outputs);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ParcelFileDescriptorTextBuilder extends InputBuilder<ParcelFileDescriptor> {
        public ParcelFileDescriptorTextBuilder(Builder builder) {
            super(builder, "fileText");
        }

        public ParcelFileDescriptorTextBuilder addOutput(ParcelFileDescriptor parcelFileDescriptor) {
            super.addOutputParcelable((Parcelable) parcelFileDescriptor);
            this.params.putParcelableArrayList("outputPfdList", this.outputs);
            return this;
        }

        public ImageConversionModeBuilder<ParcelFileDescriptor> asImageConversion() {
            return super.asImageConversion();
        }

        public ParcelFileDescriptorTextBuilder input(String str) {
            this.params.putString("inputText", str);
            return this;
        }

        public ParcelFileDescriptorTextBuilder sampleCount(int i2) {
            this.params.putInt("sampleCount", i2);
            return this;
        }

        public ParcelFileDescriptorTextBuilder serviceVersion(String str) {
            this.params.putString("serviceVersion", str);
            return this;
        }

        public ParcelFileDescriptorTextBuilder addOutput(List<ParcelFileDescriptor> list) {
            super.addOutputParcelable((List<? extends Parcelable>) list);
            this.params.putParcelableArrayList("outputPfdList", this.outputs);
            return this;
        }

        public ParcelFileDescriptorTextBuilder input(ParcelFileDescriptor parcelFileDescriptor) {
            this.params.putParcelable("inputPfd", parcelFileDescriptor);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PetPortraitModeBuilder<T> extends Builder {
        public PetPortraitModeBuilder(Builder builder) {
            super(builder.params);
        }

        public PetPortraitModeBuilder<T> aspectRatio(String str) {
            this.params.putString("aspectRatio", str);
            return this;
        }

        public PetPortraitModeBuilder<T> petPortraitInputType(String str) {
            this.params.putString("petPortraitInputType", str);
            return this;
        }

        public PetPortraitModeBuilder<T> regionOfInterest(Rect rect) {
            this.params.putParcelable("regionOfInterest", rect);
            return this;
        }

        public PetPortraitModeBuilder<T> seedIndex(int i2) {
            this.params.putInt("seedIndex", i2);
            return this;
        }

        public PetPortraitModeBuilder<T> style(String str) {
            this.params.putString("style", str);
            return this;
        }

        public PetPortraitModeBuilder<T> userInput(String str) {
            this.params.putString("userInput", str);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PortraitModeBuilder<T> extends Builder {
        public PortraitModeBuilder(Builder builder) {
            super(builder.params);
        }

        public PortraitModeBuilder<T> aspectRatio(String str) {
            this.params.putString("aspectRatio", str);
            return this;
        }

        public PortraitModeBuilder<T> faceRect(Rect rect) {
            this.params.putParcelable("portraitFaceRectByUser", rect);
            return this;
        }

        public PortraitModeBuilder<T> identityControl(float f) {
            this.params.putFloat("portraitIdentityControl", f);
            return this;
        }

        public PortraitModeBuilder<T> portraitInputType(String str) {
            this.params.putString("portraitInputType", str);
            return this;
        }

        public PortraitModeBuilder<T> seedIndex(int i2) {
            this.params.putInt("seedIndex", i2);
            return this;
        }

        public PortraitModeBuilder<T> structureControl(float f) {
            this.params.putFloat("portraitStructureControl", f);
            return this;
        }

        public PortraitModeBuilder<T> style(String str) {
            this.params.putString("portraitStyle", str);
            return this;
        }

        public PortraitModeBuilder<T> userInput(String str) {
            this.params.putString("userInput", str);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RestylingBuilder<T> extends Builder {
        public RestylingBuilder(Builder builder) {
            super(builder.params);
        }

        public RestylingBuilder<T> aspectRatio(String str) {
            this.params.putString("aspectRatio", str);
            return this;
        }

        public RestylingBuilder<T> restylingInputType(String str) {
            this.params.putString("restylingInputType", str);
            return this;
        }

        public RestylingBuilder<T> seedIndex(int i2) {
            this.params.putInt("seedIndex", i2);
            return this;
        }

        public RestylingBuilder<T> style(String str) {
            this.params.putString("style", str);
            return this;
        }

        public RestylingBuilder<T> userInput(String str) {
            this.params.putString("userInput", str);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Sketch2ImageModeBuilder<T> extends Builder {
        public Sketch2ImageModeBuilder(Builder builder) {
            super(builder.params);
            followSketchStrength("strong");
        }

        public Sketch2ImageModeBuilder<T> followSketchStrength(String str) {
            this.params.putString("followSketchStrength", str);
            return this;
        }

        public Sketch2ImageModeBuilder<T> style(String str) {
            this.params.putString("style", str);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TextBuilder extends InputBuilder<String> {
        public TextBuilder(Builder builder) {
            super(builder, "text");
        }

        public TextBuilder addOutput(ParcelFileDescriptor parcelFileDescriptor) {
            super.addOutputParcelable((Parcelable) parcelFileDescriptor);
            this.params.putParcelableArrayList("outputPfdList", this.outputs);
            return this;
        }

        public GenStickerModeBuilder<String> asGenSticker() {
            return super.asGenSticker();
        }

        public ImageConversionModeBuilder<String> asImageConversion() {
            return super.asImageConversion();
        }

        public TextBuilder input(String str) {
            this.params.putString("inputText", str);
            return this;
        }

        public TextBuilder sampleCount(int i2) {
            this.params.putInt("sampleCount", i2);
            return this;
        }

        public TextBuilder serviceVersion(String str) {
            this.params.putString("serviceVersion", str);
            return this;
        }

        public TextBuilder addOutput(List<ParcelFileDescriptor> list) {
            super.addOutputParcelable((List<? extends Parcelable>) list);
            this.params.putParcelableArrayList("outputPfdList", this.outputs);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class WallpaperModeBuilder<T> extends Builder {
        public WallpaperModeBuilder(Builder builder) {
            super(builder.params);
            mode("mask");
            priority(0);
        }

        public WallpaperModeBuilder<T> input2(T t) {
            if (t instanceof Bitmap) {
                this.params.putParcelable("alphaBitmap", (Parcelable) t);
                return this;
            }
            if (t instanceof ParcelFileDescriptor) {
                this.params.putParcelable("alphaPfd", (Parcelable) t);
            }
            return this;
        }

        public WallpaperModeBuilder<T> mode(String str) {
            this.params.putString("generateMode", str);
            return this;
        }

        public WallpaperModeBuilder<T> priority(int i2) {
            this.params.putInt("priority", i2);
            return this;
        }

        public WallpaperModeBuilder<T> time(String str) {
            this.params.putString("time", str);
            return this;
        }

        public WallpaperModeBuilder<T> weather(String str) {
            this.params.putString("weather", str);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        protected Bundle params;

        public Builder() {
            this.params = new Bundle();
        }

        public Bundle build() {
            return this.params;
        }

        public Builder(Bundle bundle) {
            new Bundle();
            this.params = bundle;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SketchGuidedEditingModeBuilder<T> extends Builder {
        public SketchGuidedEditingModeBuilder(Builder builder) {
            super(builder.params);
        }

        public SketchGuidedEditingModeBuilder<T> assistance(T t) {
            if (t instanceof Bitmap) {
                this.params.putParcelable("assistanceBitmap", (Parcelable) t);
                return this;
            }
            if (t instanceof ParcelFileDescriptor) {
                this.params.putParcelable("assistancePfd", (Parcelable) t);
            }
            return this;
        }

        public SketchGuidedEditingModeBuilder<T> base(T t) {
            if (t instanceof Bitmap) {
                this.params.putParcelable("baseBitmap", (Parcelable) t);
                return this;
            }
            if (t instanceof ParcelFileDescriptor) {
                this.params.putParcelable("basePfd", (Parcelable) t);
            }
            return this;
        }

        public SketchGuidedEditingModeBuilder<T> levelType(int i2) {
            this.params.putInt("levelType", i2);
            return this;
        }

        public SketchGuidedEditingModeBuilder<T> mask(T t) {
            if (t instanceof Bitmap) {
                this.params.putParcelable("alphaBitmap", (Parcelable) t);
                return this;
            }
            if (t instanceof ParcelFileDescriptor) {
                this.params.putParcelable("alphaPfd", (Parcelable) t);
            }
            return this;
        }

        public SketchGuidedEditingModeBuilder<T> mask(Rect rect) {
            this.params.putParcelable("alphaRect", rect);
            return this;
        }

        public SketchGuidedEditingModeBuilder<T> mask(ArrayList<Rect> arrayList) {
            this.params.putParcelableArrayList("alphaRectList", arrayList);
            return this;
        }
    }
}
