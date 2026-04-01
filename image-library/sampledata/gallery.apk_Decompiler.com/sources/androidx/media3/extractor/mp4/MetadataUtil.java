package androidx.media3.extractor.mp4;

import A.a;
import F2.U;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.Mp4Box;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.metadata.id3.ApicFrame;
import androidx.media3.extractor.metadata.id3.CommentFrame;
import androidx.media3.extractor.metadata.id3.Id3Frame;
import androidx.media3.extractor.metadata.id3.Id3Util;
import androidx.media3.extractor.metadata.id3.InternalFrame;
import androidx.media3.extractor.metadata.id3.TextInformationFrame;
import c0.C0086a;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MetadataUtil {
    public static MdtaMetadataEntry findMdtaMetadataEntryWithKey(Metadata metadata, String str) {
        for (int i2 = 0; i2 < metadata.length(); i2++) {
            Metadata.Entry entry = metadata.get(i2);
            if (entry instanceof MdtaMetadataEntry) {
                MdtaMetadataEntry mdtaMetadataEntry = (MdtaMetadataEntry) entry;
                if (mdtaMetadataEntry.key.equals(str)) {
                    return mdtaMetadataEntry;
                }
            }
        }
        return null;
    }

    private static CommentFrame parseCommentAttribute(int i2, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385) {
            parsableByteArray.skipBytes(8);
            String readNullTerminatedString = parsableByteArray.readNullTerminatedString(readInt - 16);
            return new CommentFrame("und", readNullTerminatedString, readNullTerminatedString);
        }
        Log.w("MetadataUtil", "Failed to parse comment attribute: " + Mp4Box.getBoxTypeString(i2));
        return null;
    }

    private static ApicFrame parseCoverArt(ParsableByteArray parsableByteArray) {
        String str;
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385) {
            int parseFullBoxFlags = BoxParser.parseFullBoxFlags(parsableByteArray.readInt());
            if (parseFullBoxFlags == 13) {
                str = "image/jpeg";
            } else if (parseFullBoxFlags == 14) {
                str = "image/png";
            } else {
                str = null;
            }
            if (str == null) {
                a.D(parseFullBoxFlags, "Unrecognized cover art flags: ", "MetadataUtil");
                return null;
            }
            parsableByteArray.skipBytes(4);
            int i2 = readInt - 16;
            byte[] bArr = new byte[i2];
            parsableByteArray.readBytes(bArr, 0, i2);
            return new ApicFrame(str, (String) null, 3, bArr);
        }
        Log.w("MetadataUtil", "Failed to parse cover art attribute");
        return null;
    }

    public static Metadata.Entry parseIlstElement(ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt() + parsableByteArray.getPosition();
        int readInt2 = parsableByteArray.readInt();
        int i2 = (readInt2 >> 24) & ScoverState.TYPE_NFC_SMART_COVER;
        if (i2 == 169 || i2 == 253) {
            int i7 = 16777215 & readInt2;
            if (i7 == 6516084) {
                CommentFrame parseCommentAttribute = parseCommentAttribute(readInt2, parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseCommentAttribute;
            } else if (i7 == 7233901 || i7 == 7631467) {
                TextInformationFrame parseTextAttribute = parseTextAttribute(readInt2, "TIT2", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute;
            } else if (i7 == 6516589 || i7 == 7828084) {
                TextInformationFrame parseTextAttribute2 = parseTextAttribute(readInt2, "TCOM", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute2;
            } else if (i7 == 6578553) {
                TextInformationFrame parseTextAttribute3 = parseTextAttribute(readInt2, "TDRC", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute3;
            } else if (i7 == 4280916) {
                TextInformationFrame parseTextAttribute4 = parseTextAttribute(readInt2, "TPE1", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute4;
            } else if (i7 == 7630703) {
                TextInformationFrame parseTextAttribute5 = parseTextAttribute(readInt2, "TSSE", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute5;
            } else if (i7 == 6384738) {
                TextInformationFrame parseTextAttribute6 = parseTextAttribute(readInt2, "TALB", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute6;
            } else if (i7 == 7108978) {
                TextInformationFrame parseTextAttribute7 = parseTextAttribute(readInt2, "USLT", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute7;
            } else if (i7 == 6776174) {
                TextInformationFrame parseTextAttribute8 = parseTextAttribute(readInt2, "TCON", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute8;
            } else if (i7 == 6779504) {
                TextInformationFrame parseTextAttribute9 = parseTextAttribute(readInt2, "TIT1", parsableByteArray);
                parsableByteArray.setPosition(readInt);
                return parseTextAttribute9;
            }
        } else if (readInt2 == 1735291493) {
            try {
                return parseStandardGenreAttribute(parsableByteArray);
            } finally {
                parsableByteArray.setPosition(readInt);
            }
        } else if (readInt2 == 1684632427) {
            TextInformationFrame parseIndexAndCountAttribute = parseIndexAndCountAttribute(readInt2, "TPOS", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseIndexAndCountAttribute;
        } else if (readInt2 == 1953655662) {
            TextInformationFrame parseIndexAndCountAttribute2 = parseIndexAndCountAttribute(readInt2, "TRCK", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseIndexAndCountAttribute2;
        } else if (readInt2 == 1953329263) {
            Id3Frame parseIntegerAttribute = parseIntegerAttribute(readInt2, "TBPM", parsableByteArray, true, false);
            parsableByteArray.setPosition(readInt);
            return parseIntegerAttribute;
        } else if (readInt2 == 1668311404) {
            Id3Frame parseIntegerAttribute2 = parseIntegerAttribute(readInt2, "TCMP", parsableByteArray, true, true);
            parsableByteArray.setPosition(readInt);
            return parseIntegerAttribute2;
        } else if (readInt2 == 1668249202) {
            ApicFrame parseCoverArt = parseCoverArt(parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseCoverArt;
        } else if (readInt2 == 1631670868) {
            TextInformationFrame parseTextAttribute10 = parseTextAttribute(readInt2, "TPE2", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseTextAttribute10;
        } else if (readInt2 == 1936682605) {
            TextInformationFrame parseTextAttribute11 = parseTextAttribute(readInt2, "TSOT", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseTextAttribute11;
        } else if (readInt2 == 1936679276) {
            TextInformationFrame parseTextAttribute12 = parseTextAttribute(readInt2, "TSOA", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseTextAttribute12;
        } else if (readInt2 == 1936679282) {
            TextInformationFrame parseTextAttribute13 = parseTextAttribute(readInt2, "TSOP", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseTextAttribute13;
        } else if (readInt2 == 1936679265) {
            TextInformationFrame parseTextAttribute14 = parseTextAttribute(readInt2, "TSO2", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseTextAttribute14;
        } else if (readInt2 == 1936679791) {
            TextInformationFrame parseTextAttribute15 = parseTextAttribute(readInt2, "TSOC", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseTextAttribute15;
        } else if (readInt2 == 1920233063) {
            Id3Frame parseIntegerAttribute3 = parseIntegerAttribute(readInt2, "ITUNESADVISORY", parsableByteArray, false, false);
            parsableByteArray.setPosition(readInt);
            return parseIntegerAttribute3;
        } else if (readInt2 == 1885823344) {
            Id3Frame parseIntegerAttribute4 = parseIntegerAttribute(readInt2, "ITUNESGAPLESS", parsableByteArray, false, true);
            parsableByteArray.setPosition(readInt);
            return parseIntegerAttribute4;
        } else if (readInt2 == 1936683886) {
            TextInformationFrame parseTextAttribute16 = parseTextAttribute(readInt2, "TVSHOWSORT", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseTextAttribute16;
        } else if (readInt2 == 1953919848) {
            TextInformationFrame parseTextAttribute17 = parseTextAttribute(readInt2, "TVSHOW", parsableByteArray);
            parsableByteArray.setPosition(readInt);
            return parseTextAttribute17;
        } else if (readInt2 == 757935405) {
            Id3Frame parseInternalAttribute = parseInternalAttribute(parsableByteArray, readInt);
            parsableByteArray.setPosition(readInt);
            return parseInternalAttribute;
        }
        Log.d("MetadataUtil", "Skipped unknown metadata entry: " + Mp4Box.getBoxTypeString(readInt2));
        parsableByteArray.setPosition(readInt);
        return null;
    }

    private static TextInformationFrame parseIndexAndCountAttribute(int i2, String str, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385 && readInt >= 22) {
            parsableByteArray.skipBytes(10);
            int readUnsignedShort = parsableByteArray.readUnsignedShort();
            if (readUnsignedShort > 0) {
                String i7 = C0086a.i(readUnsignedShort, "");
                int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
                if (readUnsignedShort2 > 0) {
                    i7 = i7 + "/" + readUnsignedShort2;
                }
                return new TextInformationFrame(str, (String) null, U.B(i7));
            }
        }
        Log.w("MetadataUtil", "Failed to parse index/count attribute: " + Mp4Box.getBoxTypeString(i2));
        return null;
    }

    private static Id3Frame parseIntegerAttribute(int i2, String str, ParsableByteArray parsableByteArray, boolean z, boolean z3) {
        int parseIntegerAttribute = parseIntegerAttribute(parsableByteArray);
        if (z3) {
            parseIntegerAttribute = Math.min(1, parseIntegerAttribute);
        }
        if (parseIntegerAttribute < 0) {
            Log.w("MetadataUtil", "Failed to parse uint8 attribute: " + Mp4Box.getBoxTypeString(i2));
            return null;
        } else if (z) {
            return new TextInformationFrame(str, (String) null, U.B(Integer.toString(parseIntegerAttribute)));
        } else {
            return new CommentFrame("und", str, Integer.toString(parseIntegerAttribute));
        }
    }

    private static Id3Frame parseInternalAttribute(ParsableByteArray parsableByteArray, int i2) {
        String str = null;
        String str2 = null;
        int i7 = -1;
        int i8 = -1;
        while (parsableByteArray.getPosition() < i2) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            if (readInt2 == 1835360622) {
                str = parsableByteArray.readNullTerminatedString(readInt - 12);
            } else if (readInt2 == 1851878757) {
                str2 = parsableByteArray.readNullTerminatedString(readInt - 12);
            } else {
                if (readInt2 == 1684108385) {
                    i7 = position;
                    i8 = readInt;
                }
                parsableByteArray.skipBytes(readInt - 12);
            }
        }
        if (str == null || str2 == null || i7 == -1) {
            return null;
        }
        parsableByteArray.setPosition(i7);
        parsableByteArray.skipBytes(16);
        return new InternalFrame(str, str2, parsableByteArray.readNullTerminatedString(i8 - 16));
    }

    public static MdtaMetadataEntry parseMdtaMetadataEntryFromIlst(ParsableByteArray parsableByteArray, int i2, String str) {
        while (true) {
            int position = parsableByteArray.getPosition();
            if (position >= i2) {
                return null;
            }
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1684108385) {
                int readInt2 = parsableByteArray.readInt();
                int readInt3 = parsableByteArray.readInt();
                int i7 = readInt - 16;
                byte[] bArr = new byte[i7];
                parsableByteArray.readBytes(bArr, 0, i7);
                return new MdtaMetadataEntry(str, bArr, readInt3, readInt2);
            }
            parsableByteArray.setPosition(position + readInt);
        }
    }

    private static TextInformationFrame parseStandardGenreAttribute(ParsableByteArray parsableByteArray) {
        String resolveV1Genre = Id3Util.resolveV1Genre(parseIntegerAttribute(parsableByteArray) - 1);
        if (resolveV1Genre != null) {
            return new TextInformationFrame("TCON", (String) null, U.B(resolveV1Genre));
        }
        Log.w("MetadataUtil", "Failed to parse standard genre code");
        return null;
    }

    private static TextInformationFrame parseTextAttribute(int i2, String str, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385) {
            parsableByteArray.skipBytes(8);
            return new TextInformationFrame(str, (String) null, U.B(parsableByteArray.readNullTerminatedString(readInt - 16)));
        }
        Log.w("MetadataUtil", "Failed to parse text attribute: " + Mp4Box.getBoxTypeString(i2));
        return null;
    }

    public static void setFormatGaplessInfo(int i2, GaplessInfoHolder gaplessInfoHolder, Format.Builder builder) {
        if (i2 == 1 && gaplessInfoHolder.hasGaplessInfo()) {
            builder.setEncoderDelay(gaplessInfoHolder.encoderDelay).setEncoderPadding(gaplessInfoHolder.encoderPadding);
        }
    }

    public static void setFormatMetadata(int i2, Metadata metadata, Format.Builder builder, Metadata metadata2, Metadata... metadataArr) {
        if (metadata2 == null) {
            metadata2 = new Metadata(new Metadata.Entry[0]);
        }
        if (metadata != null) {
            for (int i7 = 0; i7 < metadata.length(); i7++) {
                Metadata.Entry entry = metadata.get(i7);
                if (entry instanceof MdtaMetadataEntry) {
                    MdtaMetadataEntry mdtaMetadataEntry = (MdtaMetadataEntry) entry;
                    if (!mdtaMetadataEntry.key.equals("com.android.capture.fps")) {
                        metadata2 = metadata2.copyWithAppendedEntries(mdtaMetadataEntry);
                    } else if (i2 == 2) {
                        metadata2 = metadata2.copyWithAppendedEntries(mdtaMetadataEntry);
                    }
                }
            }
        }
        for (Metadata copyWithAppendedEntriesFrom : metadataArr) {
            metadata2 = metadata2.copyWithAppendedEntriesFrom(copyWithAppendedEntriesFrom);
        }
        if (metadata2.length() > 0) {
            builder.setMetadata(metadata2);
        }
    }

    private static int parseIntegerAttribute(ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == 1684108385) {
            parsableByteArray.skipBytes(8);
            int i2 = readInt - 16;
            if (i2 == 1) {
                return parsableByteArray.readUnsignedByte();
            }
            if (i2 == 2) {
                return parsableByteArray.readUnsignedShort();
            }
            if (i2 == 3) {
                return parsableByteArray.readUnsignedInt24();
            }
            if (i2 == 4 && (parsableByteArray.peekUnsignedByte() & 128) == 0) {
                return parsableByteArray.readUnsignedIntToInt();
            }
        }
        Log.w("MetadataUtil", "Failed to parse data atom to int");
        return -1;
    }
}
