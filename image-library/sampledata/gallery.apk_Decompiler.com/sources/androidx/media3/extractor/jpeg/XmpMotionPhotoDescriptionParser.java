package androidx.media3.extractor.jpeg;

import F2.G;
import F2.Q;
import F2.U;
import F2.y0;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.XmlPullParserUtil;
import androidx.media3.extractor.jpeg.MotionPhotoDescription;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import i.C0212a;
import java.io.StringReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class XmpMotionPhotoDescriptionParser {
    private static final String[] DESCRIPTION_MICRO_VIDEO_OFFSET_ATTRIBUTE_NAMES = {"Camera:MicroVideoOffset", "GCamera:MicroVideoOffset"};
    private static final String[] DESCRIPTION_MOTION_PHOTO_PRESENTATION_TIMESTAMP_ATTRIBUTE_NAMES = {"Camera:MotionPhotoPresentationTimestampUs", "GCamera:MotionPhotoPresentationTimestampUs", "Camera:MicroVideoPresentationTimestampUs", "GCamera:MicroVideoPresentationTimestampUs"};
    private static final String[] MOTION_PHOTO_ATTRIBUTE_NAMES = {"Camera:MotionPhoto", "GCamera:MotionPhoto", "Camera:MicroVideo", "GCamera:MicroVideo"};

    public static MotionPhotoDescription parse(String str) {
        try {
            return parseInternal(str);
        } catch (ParserException | NumberFormatException | XmlPullParserException unused) {
            Log.w("MotionPhotoXmpParser", "Ignoring unexpected XMP metadata");
            return null;
        }
    }

    private static MotionPhotoDescription parseInternal(String str) {
        XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
        newPullParser.setInput(new StringReader(str));
        newPullParser.next();
        if (XmlPullParserUtil.isStartTag(newPullParser, "x:xmpmeta")) {
            G g = U.e;
            U u = y0.f278h;
            long j2 = -9223372036854775807L;
            while (true) {
                newPullParser.next();
                if (XmlPullParserUtil.isStartTag(newPullParser, "rdf:Description")) {
                    if (!parseMotionPhotoFlagFromDescription(newPullParser)) {
                        break;
                    }
                    j2 = parseMotionPhotoPresentationTimestampUsFromDescription(newPullParser);
                    u = parseMicroVideoOffsetFromDescription(newPullParser);
                } else if (XmlPullParserUtil.isStartTag(newPullParser, "Container:Directory")) {
                    u = parseMotionPhotoV1Directory(newPullParser, "Container", "Item");
                } else if (XmlPullParserUtil.isStartTag(newPullParser, "GContainer:Directory")) {
                    u = parseMotionPhotoV1Directory(newPullParser, "GContainer", "GContainerItem");
                }
                if (XmlPullParserUtil.isEndTag(newPullParser, "x:xmpmeta")) {
                    if (!u.isEmpty()) {
                        return new MotionPhotoDescription(j2, u);
                    }
                }
            }
            return null;
        }
        throw ParserException.createForMalformedContainer("Couldn't find xmp metadata", (Throwable) null);
    }

    private static U parseMicroVideoOffsetFromDescription(XmlPullParser xmlPullParser) {
        for (String attributeValue : DESCRIPTION_MICRO_VIDEO_OFFSET_ATTRIBUTE_NAMES) {
            String attributeValue2 = XmlPullParserUtil.getAttributeValue(xmlPullParser, attributeValue);
            if (attributeValue2 != null) {
                return U.D(new MotionPhotoDescription.ContainerItem("image/jpeg", MediaDefs.Meta.XMP.XMP_KEY_PRIMARY, 0, 0), new MotionPhotoDescription.ContainerItem(Encode.ContentType.VIDEO_MP4, MediaDefs.Meta.XMP.XMP_KEY_MOTION_PHOTO, Long.parseLong(attributeValue2), 0));
            }
        }
        G g = U.e;
        return y0.f278h;
    }

    private static boolean parseMotionPhotoFlagFromDescription(XmlPullParser xmlPullParser) {
        String[] strArr = MOTION_PHOTO_ATTRIBUTE_NAMES;
        int length = strArr.length;
        int i2 = 0;
        while (i2 < length) {
            String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, strArr[i2]);
            if (attributeValue == null) {
                i2++;
            } else if (Integer.parseInt(attributeValue) == 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private static long parseMotionPhotoPresentationTimestampUsFromDescription(XmlPullParser xmlPullParser) {
        for (String attributeValue : DESCRIPTION_MOTION_PHOTO_PRESENTATION_TIMESTAMP_ATTRIBUTE_NAMES) {
            String attributeValue2 = XmlPullParserUtil.getAttributeValue(xmlPullParser, attributeValue);
            if (attributeValue2 != null) {
                long parseLong = Long.parseLong(attributeValue2);
                if (parseLong == -1) {
                    return -9223372036854775807L;
                }
                return parseLong;
            }
        }
        return -9223372036854775807L;
    }

    private static U parseMotionPhotoV1Directory(XmlPullParser xmlPullParser, String str, String str2) {
        long j2;
        Q x9 = U.x();
        String A10 = C0212a.A(str, ":Item");
        String A11 = C0212a.A(str, ":Directory");
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, A10)) {
                String A12 = C0212a.A(str2, ":Mime");
                String A13 = C0212a.A(str2, ":Semantic");
                String A14 = C0212a.A(str2, ":Length");
                String A15 = C0212a.A(str2, ":Padding");
                String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, A12);
                String attributeValue2 = XmlPullParserUtil.getAttributeValue(xmlPullParser, A13);
                String attributeValue3 = XmlPullParserUtil.getAttributeValue(xmlPullParser, A14);
                String attributeValue4 = XmlPullParserUtil.getAttributeValue(xmlPullParser, A15);
                if (attributeValue == null || attributeValue2 == null) {
                    return y0.f278h;
                }
                long j3 = 0;
                if (attributeValue3 != null) {
                    j2 = Long.parseLong(attributeValue3);
                } else {
                    j2 = 0;
                }
                if (attributeValue4 != null) {
                    j3 = Long.parseLong(attributeValue4);
                }
                x9.a(new MotionPhotoDescription.ContainerItem(attributeValue, attributeValue2, j2, j3));
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, A11));
        return x9.f();
    }
}
