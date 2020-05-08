package nz.mega.core;

import java.util.Map;

public class CurrencyLayerResponse {
    private final boolean success;
    private final String terms;
    private final String privacy;
    private final long timestamp;
    private final String source;
    private final Map<String, Double> quotes;

    CurrencyLayerResponse(boolean success, String terms, String privacy, long timestamp, String source, Map<String, Double> quotes) {
        this.success = success;
        this.terms = terms;
        this.privacy = privacy;
        this.timestamp = timestamp;
        this.source = source;
        this.quotes = quotes;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getTerms() {
        return terms;
    }

    public String getPrivacy() {
        return privacy;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getSource() {
        return source;
    }

    public Map<String, Double> getQuotes() {
        return quotes;
    }

    @Override
    public String toString() {
        return "CurrencyLayerResponse{" +
                "success=" + success +
                ", terms='" + terms + '\'' +
                ", privacy='" + privacy + '\'' +
                ", timestamp=" + timestamp +
                ", source='" + source + '\'' +
                ", quotes=" + quotes +
                '}';
    }
}
