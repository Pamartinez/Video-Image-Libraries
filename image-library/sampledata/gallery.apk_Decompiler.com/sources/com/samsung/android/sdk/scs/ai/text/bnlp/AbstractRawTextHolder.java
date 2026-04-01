package com.samsung.android.sdk.scs.ai.text.bnlp;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbstractRawTextHolder {
    protected int position;
    protected String rawText;

    public AbstractRawTextHolder() {
    }

    public int endPosition() {
        return length() + this.position;
    }

    public int getPosition() {
        return this.position;
    }

    public String getRawText() {
        return this.rawText;
    }

    public int length() {
        String str = this.rawText;
        if (str == null) {
            return 0;
        }
        return str.length();
    }

    public void setPosition(int i2) {
        this.position = i2;
    }

    public void setRawText(String str) {
        this.rawText = str;
    }

    public String subRawText(int i2) {
        String str = this.rawText;
        if (str == null) {
            return null;
        }
        return str.substring(i2);
    }

    public AbstractRawTextHolder(String str) {
        this.rawText = str;
    }

    public String subRawText(int i2, int i7) {
        String str = this.rawText;
        if (str == null) {
            return null;
        }
        return str.substring(i2, i7);
    }

    public AbstractRawTextHolder(String str, int i2) {
        this.rawText = str;
        this.position = i2;
    }
}
