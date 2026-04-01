package androidx.slice;

import android.app.PendingIntent;
import android.os.Parcelable;
import android.text.Spanned;
import androidx.core.text.HtmlCompat;
import androidx.core.util.Pair;
import androidx.versionedparcelable.VersionedParcelable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SliceItemHolder implements VersionedParcelable {
    int mInt;
    long mLong;
    Parcelable mParcelable;
    String mStr;
    VersionedParcelable mVersionedParcelable;

    public SliceItemHolder() {
    }

    public Object getObj(String str) {
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
                return new Pair(this.mParcelable, (Slice) this.mVersionedParcelable);
            case 1:
                return Integer.valueOf(this.mInt);
            case 2:
                return Long.valueOf(this.mLong);
            case 3:
                String str2 = this.mStr;
                if (str2 == null || str2.length() == 0) {
                    return "";
                }
                return HtmlCompat.fromHtml(this.mStr, 0);
            case 4:
            case 6:
                return this.mVersionedParcelable;
            case 5:
                return this.mParcelable;
            default:
                throw new IllegalArgumentException("Unrecognized format ".concat(str));
        }
    }

    public SliceItemHolder(String str, Object obj, boolean z) {
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
                Pair pair = (Pair) obj;
                F f = pair.first;
                if (f instanceof PendingIntent) {
                    if (!z) {
                        this.mParcelable = (Parcelable) f;
                    } else {
                        throw new IllegalArgumentException("Cannot write PendingIntent to stream");
                    }
                } else if (!z) {
                    throw new IllegalArgumentException("Cannot write callback to parcel");
                }
                this.mVersionedParcelable = (VersionedParcelable) pair.second;
                return;
            case 1:
                this.mInt = ((Integer) obj).intValue();
                return;
            case 2:
                this.mLong = ((Long) obj).longValue();
                return;
            case 3:
                this.mStr = obj instanceof Spanned ? HtmlCompat.toHtml((Spanned) obj, 0) : (String) obj;
                return;
            case 4:
            case 6:
                this.mVersionedParcelable = (VersionedParcelable) obj;
                return;
            case 5:
                if (!z) {
                    this.mParcelable = (Parcelable) obj;
                    return;
                }
                throw new IllegalArgumentException("Cannot write RemoteInput to stream");
            default:
                return;
        }
    }
}
