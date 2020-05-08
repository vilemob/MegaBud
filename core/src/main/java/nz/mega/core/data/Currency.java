package nz.mega.core.data;

public enum Currency {
    NZD("NZD"),
    USD("USD");

    private String code;

    Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
