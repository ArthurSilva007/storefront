package br.com.dio.storefront.dto;

import java.util.UUID;

public record StockStatusMessage(UUID Id, String status) {

    public boolean active()
    {return status.equals("AVAILABLE");}

}
