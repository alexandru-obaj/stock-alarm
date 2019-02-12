package com.alexandru.obaj.soa.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StockDescription {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("name")
    private String name;

    @JsonProperty("date")
    private String date;

    @JsonProperty("isEnabled")
    private boolean isEnabled;

    @JsonProperty("type")
    private String type;

    @JsonProperty("iexId")
    private int iexId;

    @JsonCreator
    public StockDescription(@JsonProperty("symbol") String symbol, @JsonProperty("name") String name, @JsonProperty("date") String date,
                            @JsonProperty("isEnabled") boolean isEnabled, @JsonProperty("type") String type, @JsonProperty("iexId") int iexId) {
        super();
        this.symbol = symbol;
        this.name = name;
        this.date = date;
        this.isEnabled = isEnabled;
        this.type = type;
        this.iexId = iexId;
    }
}
