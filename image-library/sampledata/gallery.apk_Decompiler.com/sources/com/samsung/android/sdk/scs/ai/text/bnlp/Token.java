package com.samsung.android.sdk.scs.ai.text.bnlp;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Token extends AbstractRawTextHolder {
    protected String lemma;
    protected String mpTags;
    protected String posTag;
    protected String stem;

    public Token() {
    }

    public String getLemma() {
        return this.lemma;
    }

    public String getMpTags() {
        return this.mpTags;
    }

    public String getPosTag() {
        return this.posTag;
    }

    public String getStem() {
        return this.stem;
    }

    public void setLemma(String str) {
        this.lemma = str;
    }

    public void setMpTags(String str) {
        this.mpTags = str;
    }

    public void setPosTag(String str) {
        this.posTag = str;
    }

    public void setStem(String str) {
        this.stem = str;
    }

    public String toString() {
        return this.rawText + "/" + this.posTag;
    }

    public Token(String str) {
        super(str);
    }

    public Token(String str, int i2) {
        super(str, i2);
    }

    public Token(String str, int i2, String str2) {
        super(str, i2);
        this.posTag = str2;
    }

    public Token(String str, int i2, String str2, String str3, String str4) {
        super(str, i2);
        this.posTag = str2;
        this.stem = str3;
        this.lemma = str4;
    }

    public Token(String str, int i2, String str2, String str3, String str4, String str5) {
        super(str, i2);
        this.posTag = str2;
        this.stem = str3;
        this.lemma = str4;
        this.mpTags = str5;
    }
}
