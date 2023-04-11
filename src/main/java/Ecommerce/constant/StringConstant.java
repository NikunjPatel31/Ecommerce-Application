package Ecommerce.constant;

import Ecommerce.model.Product;

public enum StringConstant
{
    OPERATION("Operation"),

    PRODUCT_ID("ProductID"),

    RESPONSE_STATUS("status"),

    RESPONSE_STATUS_OK("ok"),

    RESPONSE_STATUS_ERROR("error"),

    RESPONSE_MESSAGE("Message"),

    PRODUCT_QUANTITY("Product quantity");

    public final String constant;

    private StringConstant(String constant)
    {
        this.constant = constant;
    }
}
