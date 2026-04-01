package com.google.gson;

import N2.j;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.NumberLimits;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.MalformedJsonException;
import java.math.BigDecimal;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ToNumberPolicy implements ToNumberStrategy {
    DOUBLE {
        public Double readNumber(JsonReader jsonReader) {
            return Double.valueOf(jsonReader.nextDouble());
        }
    },
    LAZILY_PARSED_NUMBER {
        public Number readNumber(JsonReader jsonReader) {
            return new LazilyParsedNumber(jsonReader.nextString());
        }
    },
    LONG_OR_DOUBLE {
        private Number parseAsDouble(String str, JsonReader jsonReader) {
            try {
                Double valueOf = Double.valueOf(str);
                if (!valueOf.isInfinite()) {
                    if (valueOf.isNaN()) {
                    }
                    return valueOf;
                }
                if (!jsonReader.isLenient()) {
                    throw new MalformedJsonException("JSON forbids NaN and infinities: " + valueOf + "; at path " + jsonReader.getPreviousPath());
                }
                return valueOf;
            } catch (NumberFormatException e) {
                StringBuilder k = j.k("Cannot parse ", str, "; at path ");
                k.append(jsonReader.getPreviousPath());
                throw new JsonParseException(k.toString(), e);
            }
        }

        public Number readNumber(JsonReader jsonReader) {
            String nextString = jsonReader.nextString();
            if (nextString.indexOf(46) >= 0) {
                return parseAsDouble(nextString, jsonReader);
            }
            try {
                return Long.valueOf(Long.parseLong(nextString));
            } catch (NumberFormatException unused) {
                return parseAsDouble(nextString, jsonReader);
            }
        }
    },
    BIG_DECIMAL {
        public BigDecimal readNumber(JsonReader jsonReader) {
            String nextString = jsonReader.nextString();
            try {
                return NumberLimits.parseBigDecimal(nextString);
            } catch (NumberFormatException e) {
                StringBuilder k = j.k("Cannot parse ", nextString, "; at path ");
                k.append(jsonReader.getPreviousPath());
                throw new JsonParseException(k.toString(), e);
            }
        }
    }
}
