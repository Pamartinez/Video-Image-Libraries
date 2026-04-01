package androidx.media3.extractor;

import F2.G;
import F2.U;
import F2.y0;
import K.a;
import android.net.Uri;
import androidx.media3.common.FileTypes;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.extractor.amr.AmrExtractor;
import androidx.media3.extractor.avi.AviExtractor;
import androidx.media3.extractor.avif.AvifExtractor;
import androidx.media3.extractor.bmp.BmpExtractor;
import androidx.media3.extractor.flac.FlacExtractor;
import androidx.media3.extractor.flv.FlvExtractor;
import androidx.media3.extractor.heif.HeifExtractor;
import androidx.media3.extractor.jpeg.JpegExtractor;
import androidx.media3.extractor.mkv.MatroskaExtractor;
import androidx.media3.extractor.mp3.Mp3Extractor;
import androidx.media3.extractor.mp4.FragmentedMp4Extractor;
import androidx.media3.extractor.mp4.Mp4Extractor;
import androidx.media3.extractor.ogg.OggExtractor;
import androidx.media3.extractor.png.PngExtractor;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.ts.Ac3Extractor;
import androidx.media3.extractor.ts.Ac4Extractor;
import androidx.media3.extractor.ts.AdtsExtractor;
import androidx.media3.extractor.ts.DefaultTsPayloadReaderFactory;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.media3.extractor.wav.WavExtractor;
import androidx.media3.extractor.webp.WebpExtractor;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DefaultExtractorsFactory implements ExtractorsFactory {
    private static final int[] DEFAULT_EXTRACTOR_ORDER = {5, 4, 12, 8, 3, 10, 9, 11, 6, 2, 0, 1, 7, 16, 15, 14, 17, 18, 19, 20, 21};
    private static final ExtensionLoader FLAC_EXTENSION_LOADER = new ExtensionLoader(new a(14));
    private static final ExtensionLoader MIDI_EXTENSION_LOADER = new ExtensionLoader(new a(15));
    private int adtsFlags;
    private int amrFlags;
    private int codecsToParseWithinGopSampleDependencies;
    private boolean constantBitrateSeekingAlwaysEnabled;
    private boolean constantBitrateSeekingEnabled;
    private int flacFlags;
    private int fragmentedMp4Flags;
    private int jpegFlags;
    private int matroskaFlags;
    private int mp3Flags;
    private int mp4Flags;
    private SubtitleParser.Factory subtitleParserFactory = new DefaultSubtitleParserFactory();
    private boolean textTrackTranscodingEnabled = true;
    private int tsFlags;
    private int tsMode = 1;
    private U tsSubtitleFormats;
    private int tsTimestampSearchBytes = 112800;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ExtensionLoader {
        private final ConstructorSupplier constructorSupplier;
        private final AtomicBoolean extensionLoaded = new AtomicBoolean(false);
        private Constructor<? extends Extractor> extractorConstructor;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public interface ConstructorSupplier {
            Constructor<? extends Extractor> getConstructor();
        }

        public ExtensionLoader(ConstructorSupplier constructorSupplier2) {
            this.constructorSupplier = constructorSupplier2;
        }

        private Constructor<? extends Extractor> maybeLoadExtractorConstructor() {
            synchronized (this.extensionLoaded) {
                if (this.extensionLoaded.get()) {
                    Constructor<? extends Extractor> constructor = this.extractorConstructor;
                    return constructor;
                }
                try {
                    Constructor<? extends Extractor> constructor2 = this.constructorSupplier.getConstructor();
                    return constructor2;
                } catch (ClassNotFoundException unused) {
                    this.extensionLoaded.set(true);
                    return this.extractorConstructor;
                } catch (Exception e) {
                    throw new RuntimeException("Error instantiating extension", e);
                }
            }
        }

        public Extractor getExtractor(Object... objArr) {
            Constructor<? extends Extractor> maybeLoadExtractorConstructor = maybeLoadExtractorConstructor();
            if (maybeLoadExtractorConstructor == null) {
                return null;
            }
            try {
                return (Extractor) maybeLoadExtractorConstructor.newInstance(objArr);
            } catch (Exception e) {
                throw new IllegalStateException("Unexpected error creating extractor", e);
            }
        }
    }

    private void addExtractorsForFileType(int i2, List<Extractor> list) {
        int i7;
        int i8 = 2;
        int i10 = 0;
        switch (i2) {
            case 0:
                list.add(new Ac3Extractor());
                return;
            case 1:
                list.add(new Ac4Extractor());
                return;
            case 2:
                boolean z = this.adtsFlags | this.constantBitrateSeekingEnabled;
                if (!this.constantBitrateSeekingAlwaysEnabled) {
                    i8 = 0;
                }
                list.add(new AdtsExtractor(z | i8 ? 1 : 0));
                return;
            case 3:
                boolean z3 = this.amrFlags | this.constantBitrateSeekingEnabled;
                if (!this.constantBitrateSeekingAlwaysEnabled) {
                    i8 = 0;
                }
                list.add(new AmrExtractor(z3 | i8 ? 1 : 0));
                return;
            case 4:
                Extractor extractor = FLAC_EXTENSION_LOADER.getExtractor(Integer.valueOf(this.flacFlags));
                if (extractor != null) {
                    list.add(extractor);
                    return;
                } else {
                    list.add(new FlacExtractor(this.flacFlags));
                    return;
                }
            case 5:
                list.add(new FlvExtractor());
                return;
            case 6:
                SubtitleParser.Factory factory = this.subtitleParserFactory;
                int i11 = this.matroskaFlags;
                if (this.textTrackTranscodingEnabled) {
                    i8 = 0;
                }
                list.add(new MatroskaExtractor(factory, i11 | i8));
                return;
            case 7:
                boolean z7 = this.mp3Flags | this.constantBitrateSeekingEnabled;
                if (!this.constantBitrateSeekingAlwaysEnabled) {
                    i8 = 0;
                }
                list.add(new Mp3Extractor(z7 | i8 ? 1 : 0));
                return;
            case 8:
                SubtitleParser.Factory factory2 = this.subtitleParserFactory;
                int codecsToParseWithinGopSampleDependenciesAsFlags = this.fragmentedMp4Flags | FragmentedMp4Extractor.codecsToParseWithinGopSampleDependenciesAsFlags(this.codecsToParseWithinGopSampleDependencies);
                if (this.textTrackTranscodingEnabled) {
                    i7 = 0;
                } else {
                    i7 = 32;
                }
                list.add(new FragmentedMp4Extractor(factory2, codecsToParseWithinGopSampleDependenciesAsFlags | i7));
                SubtitleParser.Factory factory3 = this.subtitleParserFactory;
                int codecsToParseWithinGopSampleDependenciesAsFlags2 = this.mp4Flags | Mp4Extractor.codecsToParseWithinGopSampleDependenciesAsFlags(this.codecsToParseWithinGopSampleDependencies);
                if (!this.textTrackTranscodingEnabled) {
                    i10 = 16;
                }
                list.add(new Mp4Extractor(factory3, codecsToParseWithinGopSampleDependenciesAsFlags2 | i10));
                return;
            case 9:
                list.add(new OggExtractor());
                return;
            case 10:
                list.add(new PsExtractor());
                return;
            case 11:
                if (this.tsSubtitleFormats == null) {
                    G g = U.e;
                    this.tsSubtitleFormats = y0.f278h;
                }
                list.add(new TsExtractor(this.tsMode, !this.textTrackTranscodingEnabled ? 1 : 0, this.subtitleParserFactory, new TimestampAdjuster(0), new DefaultTsPayloadReaderFactory(this.tsFlags, this.tsSubtitleFormats), this.tsTimestampSearchBytes));
                return;
            case 12:
                list.add(new WavExtractor());
                return;
            case 14:
                list.add(new JpegExtractor(this.jpegFlags));
                return;
            case 15:
                Extractor extractor2 = MIDI_EXTENSION_LOADER.getExtractor(new Object[0]);
                if (extractor2 != null) {
                    list.add(extractor2);
                    return;
                }
                return;
            case 16:
                list.add(new AviExtractor(this.textTrackTranscodingEnabled ^ true ? 1 : 0, this.subtitleParserFactory));
                return;
            case 17:
                list.add(new PngExtractor());
                return;
            case 18:
                list.add(new WebpExtractor());
                return;
            case 19:
                list.add(new BmpExtractor());
                return;
            case 20:
                int i12 = this.mp4Flags;
                if ((i12 & 2) == 0 && (i12 & 4) == 0) {
                    list.add(new HeifExtractor());
                    return;
                }
                return;
            case 21:
                list.add(new AvifExtractor());
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public static Constructor<? extends Extractor> getFlacExtractorConstructor() {
        if (Boolean.TRUE.equals(Class.forName("androidx.media3.decoder.flac.FlacLibrary").getMethod("isAvailable", (Class[]) null).invoke((Object) null, (Object[]) null))) {
            return Class.forName("androidx.media3.decoder.flac.FlacExtractor").asSubclass(Extractor.class).getConstructor(new Class[]{Integer.TYPE});
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static Constructor<? extends Extractor> getMidiExtractorConstructor() {
        return Class.forName("androidx.media3.decoder.midi.MidiExtractor").asSubclass(Extractor.class).getConstructor((Class[]) null);
    }

    public synchronized Extractor[] createExtractors() {
        return createExtractors(Uri.EMPTY, new HashMap());
    }

    public synchronized DefaultExtractorsFactory setJpegExtractorFlags(int i2) {
        this.jpegFlags = i2;
        return this;
    }

    public synchronized DefaultExtractorsFactory setMp4ExtractorFlags(int i2) {
        this.mp4Flags = i2;
        return this;
    }

    public synchronized Extractor[] createExtractors(Uri uri, Map<String, List<String>> map) {
        ArrayList arrayList;
        try {
            int[] iArr = DEFAULT_EXTRACTOR_ORDER;
            arrayList = new ArrayList(iArr.length);
            int inferFileTypeFromResponseHeaders = FileTypes.inferFileTypeFromResponseHeaders(map);
            if (inferFileTypeFromResponseHeaders != -1) {
                addExtractorsForFileType(inferFileTypeFromResponseHeaders, arrayList);
            }
            int inferFileTypeFromUri = FileTypes.inferFileTypeFromUri(uri);
            if (!(inferFileTypeFromUri == -1 || inferFileTypeFromUri == inferFileTypeFromResponseHeaders)) {
                addExtractorsForFileType(inferFileTypeFromUri, arrayList);
            }
            for (int i2 : iArr) {
                if (!(i2 == inferFileTypeFromResponseHeaders || i2 == inferFileTypeFromUri)) {
                    addExtractorsForFileType(i2, arrayList);
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return (Extractor[]) arrayList.toArray(new Extractor[0]);
    }

    public synchronized DefaultExtractorsFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int i2) {
        this.codecsToParseWithinGopSampleDependencies = i2;
        return this;
    }

    @Deprecated
    public synchronized DefaultExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean z) {
        this.textTrackTranscodingEnabled = z;
        return this;
    }

    public synchronized DefaultExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory factory) {
        this.subtitleParserFactory = factory;
        return this;
    }
}
