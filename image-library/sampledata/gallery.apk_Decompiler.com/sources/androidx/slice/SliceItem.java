package androidx.slice;

import android.graphics.Color;
import android.text.format.DateUtils;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.util.Pair;
import androidx.versionedparcelable.CustomVersionedParcelable;
import i.C0212a;
import java.util.Calendar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SliceItem extends CustomVersionedParcelable {
    String mFormat;
    protected String[] mHints = new String[0];
    SliceItemHolder mHolder;
    Object mObj;
    String mSubType;

    private static String layoutDirectionToString(int i2) {
        if (i2 == 0) {
            return "LTR";
        }
        if (i2 == 1) {
            return "RTL";
        }
        if (i2 == 2) {
            return "INHERIT";
        }
        if (i2 != 3) {
            return Integer.toString(i2);
        }
        return "LOCALE";
    }

    public static String typeToString(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1422950858:
                if (str.equals("action")) {
                    c5 = 0;
                    break;
                }
                break;
            case 104431:
                if (str.equals("int")) {
                    c5 = 1;
                    break;
                }
                break;
            case 3327612:
                if (str.equals("long")) {
                    c5 = 2;
                    break;
                }
                break;
            case 3556653:
                if (str.equals("text")) {
                    c5 = 3;
                    break;
                }
                break;
            case 100313435:
                if (str.equals("image")) {
                    c5 = 4;
                    break;
                }
                break;
            case 100358090:
                if (str.equals("input")) {
                    c5 = 5;
                    break;
                }
                break;
            case 109526418:
                if (str.equals("slice")) {
                    c5 = 6;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return "Action";
            case 1:
                return "Int";
            case 2:
                return "Long";
            case 3:
                return "Text";
            case 4:
                return "Image";
            case 5:
                return "RemoteInput";
            case 6:
                return "Slice";
            default:
                return "Unrecognized format: ".concat(str);
        }
    }

    public String getFormat() {
        return this.mFormat;
    }

    public IconCompat getIcon() {
        return (IconCompat) this.mObj;
    }

    public int getInt() {
        return ((Integer) this.mObj).intValue();
    }

    public long getLong() {
        return ((Long) this.mObj).longValue();
    }

    public Slice getSlice() {
        if ("action".equals(getFormat())) {
            return (Slice) ((Pair) this.mObj).second;
        }
        return (Slice) this.mObj;
    }

    public String getSubType() {
        return this.mSubType;
    }

    public CharSequence getText() {
        return (CharSequence) this.mObj;
    }

    public void onPostParceling() {
        this.mObj = this.mHolder.getObj(this.mFormat);
        this.mHolder = null;
    }

    public void onPreParceling(boolean z) {
        this.mHolder = new SliceItemHolder(this.mFormat, this.mObj, z);
    }

    public String toString(String str) {
        StringBuilder s = C0212a.s(str);
        s.append(getFormat());
        if (getSubType() != null) {
            s.append('<');
            s.append(getSubType());
            s.append('>');
        }
        s.append(' ');
        String[] strArr = this.mHints;
        if (strArr.length > 0) {
            Slice.appendHints(s, strArr);
            s.append(' ');
        }
        String A10 = C0212a.A(str, "  ");
        String format = getFormat();
        format.getClass();
        char c5 = 65535;
        switch (format.hashCode()) {
            case -1422950858:
                if (format.equals("action")) {
                    c5 = 0;
                    break;
                }
                break;
            case 104431:
                if (format.equals("int")) {
                    c5 = 1;
                    break;
                }
                break;
            case 3327612:
                if (format.equals("long")) {
                    c5 = 2;
                    break;
                }
                break;
            case 3556653:
                if (format.equals("text")) {
                    c5 = 3;
                    break;
                }
                break;
            case 100313435:
                if (format.equals("image")) {
                    c5 = 4;
                    break;
                }
                break;
            case 109526418:
                if (format.equals("slice")) {
                    c5 = 5;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                F f = ((Pair) this.mObj).first;
                s.append('[');
                s.append(f);
                s.append("] ");
                s.append("{\n");
                s.append(getSlice().toString(A10));
                s.append(10);
                s.append(str);
                s.append('}');
                break;
            case 1:
                if (!"color".equals(getSubType())) {
                    if (!"layout_direction".equals(getSubType())) {
                        s.append(getInt());
                        break;
                    } else {
                        s.append(layoutDirectionToString(getInt()));
                        break;
                    }
                } else {
                    int i2 = getInt();
                    s.append(String.format("a=0x%02x r=0x%02x g=0x%02x b=0x%02x", new Object[]{Integer.valueOf(Color.alpha(i2)), Integer.valueOf(Color.red(i2)), Integer.valueOf(Color.green(i2)), Integer.valueOf(Color.blue(i2))}));
                    break;
                }
            case 2:
                if ("millis".equals(getSubType())) {
                    if (getLong() != -1) {
                        s.append(DateUtils.getRelativeTimeSpanString(getLong(), Calendar.getInstance().getTimeInMillis(), 1000, 262144));
                        break;
                    } else {
                        s.append("INFINITY");
                        break;
                    }
                } else {
                    s.append(getLong());
                    s.append('L');
                    break;
                }
            case 3:
                s.append('\"');
                s.append(getText());
                s.append('\"');
                break;
            case 4:
                s.append(getIcon());
                break;
            case 5:
                s.append("{\n");
                s.append(getSlice().toString(A10));
                s.append(10);
                s.append(str);
                s.append('}');
                break;
            default:
                s.append(typeToString(getFormat()));
                break;
        }
        s.append("\n");
        return s.toString();
    }

    public String toString() {
        return toString("");
    }
}
