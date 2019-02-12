package com.alexandru.obaj.soa.client;

import com.alexandru.obaj.soa.client.model.StockDescription;

import java.util.List;

/**
 * Interface specifying the contract for the communication with the remote stock trading API.
 */
public interface IStockClient {

    /**
     * Retrieve a list of available reference symbols in the 3rd party system.
     *
     * @return a list of {@link StockDescription} POJOs describing each available stock
     */
    List<StockDescription> getRefferenceStocks();

    /**
     * Retrieve the latest price(15 minute delayed) of the given stock identified by its symbol.
     *
     * @param ssymbol the symbol of the stock
     * @return integer identifying the current price of the stock
     */
    Integer getStockPrice(String ssymbol);

}
