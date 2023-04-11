package Ecommerce.services;

import Ecommerce.constant.StringConstant;
import Ecommerce.repository.ProductRepo;
import org.json.JSONObject;

public class ProductService
{
    public JSONObject getAllProduct()
    {
        var productRepo = ProductRepo.getInstance();

        var list = productRepo.getAllProduct();

        var response = new JSONObject();

        response.put("Product List", list);

        return response;
    }

    public JSONObject buyProduct(int productID, int quantity)
    {
        if (ProductRepo.getInstance().isProductExists(productID))
        {
            if (ProductRepo.getInstance().quantityEquals(productID,quantity))
            {
                ProductRepo.getInstance().buyProduct(productID, quantity);

                var response = new JSONObject();

                response.put(StringConstant.RESPONSE_STATUS.constant, StringConstant.RESPONSE_STATUS_OK.constant);

                response.put(StringConstant.RESPONSE_MESSAGE.constant, "Product bought");

                return response;
            }
            // quantity does not exists...
            var response = new JSONObject();

            response.put(StringConstant.RESPONSE_STATUS.constant, StringConstant.RESPONSE_STATUS_ERROR.constant);

            response.put(StringConstant.RESPONSE_MESSAGE.constant, "Quantity is more than available");
        }
        // product does not exists
        var response = new JSONObject();

        response.put(StringConstant.RESPONSE_STATUS.constant, StringConstant.RESPONSE_STATUS_ERROR.constant);

        response.put(StringConstant.RESPONSE_MESSAGE.constant, "Product is not available");

        return response;
    }
}
