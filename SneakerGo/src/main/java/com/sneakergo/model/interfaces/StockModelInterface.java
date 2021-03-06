package com.sneakergo.model.interfaces;

import com.sneakergo.entity.StockDisplayEntity;
import com.sneakergo.entity.StockEntity;

import java.util.List;

/**
 * Created by Hung on 11/20/2016.
 */
public interface StockModelInterface {
    StockEntity getStockByProductIDAndAttributeID(int productID, int attributeID);

    boolean updateStockQuantity(StockEntity stockEntity, int quantity);

    boolean createStock(StockEntity stockEntity);

    boolean isProductStockAvailable(int productID);

    List<StockDisplayEntity> getStock();
}
