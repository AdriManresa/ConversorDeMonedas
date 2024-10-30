package models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ExchangeRateResponse {
    @SerializedName("result")
    private String result;

    @SerializedName("base_code")
    private String base;

    @SerializedName("conversion_rates")
    private Map<String, Double> rates;

    @SerializedName("time_last_update_utc")
    private String date;
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
