package androidx.appcompat.app;

import android.util.AttributeSet;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LayoutIncludeDetector {
    private final Deque<WeakReference<XmlPullParser>> mXmlParserStack = new ArrayDeque();

    private static boolean isParserOutdated(XmlPullParser xmlPullParser) {
        if (xmlPullParser != null) {
            try {
                if (xmlPullParser.getEventType() == 3 || xmlPullParser.getEventType() == 1) {
                    return true;
                }
                return false;
            } catch (XmlPullParserException unused) {
            }
        }
        return true;
    }

    private static XmlPullParser popOutdatedAttrHolders(Deque<WeakReference<XmlPullParser>> deque) {
        while (!deque.isEmpty()) {
            XmlPullParser xmlPullParser = (XmlPullParser) deque.peek().get();
            if (!isParserOutdated(xmlPullParser)) {
                return xmlPullParser;
            }
            deque.pop();
        }
        return null;
    }

    private static boolean shouldInheritContext(XmlPullParser xmlPullParser, XmlPullParser xmlPullParser2) {
        if (xmlPullParser2 == null || xmlPullParser == xmlPullParser2) {
            return false;
        }
        try {
            if (xmlPullParser2.getEventType() == 2) {
                return "include".equals(xmlPullParser2.getName());
            }
            return false;
        } catch (XmlPullParserException unused) {
            return false;
        }
    }

    public boolean detect(AttributeSet attributeSet) {
        if (!(attributeSet instanceof XmlPullParser)) {
            return false;
        }
        XmlPullParser xmlPullParser = (XmlPullParser) attributeSet;
        if (xmlPullParser.getDepth() != 1) {
            return false;
        }
        XmlPullParser popOutdatedAttrHolders = popOutdatedAttrHolders(this.mXmlParserStack);
        this.mXmlParserStack.push(new WeakReference(xmlPullParser));
        if (shouldInheritContext(xmlPullParser, popOutdatedAttrHolders)) {
            return true;
        }
        return false;
    }
}
