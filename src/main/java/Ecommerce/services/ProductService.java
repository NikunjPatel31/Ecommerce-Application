package Ecommerce.services;

import Ecommerce.constant.StringConstant;
import Ecommerce.model.Product;
import Ecommerce.repository.ProductRepo;
import Ecommerce.repository.TransactionRepo;
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

    public JSONObject buyProduct(int productID, int quantity, int customerID)
    {
        if (ProductRepo.getInstance().isProductExists(productID))
        {
            if (ProductRepo.getInstance().quantityEquals(productID,quantity))
            {
                ProductRepo.getInstance().buyProduct(productID, quantity);

                TransactionRepo.getInstance().insert(customerID, (Product) ProductRepo.getInstance().select(productID), quantity);

                var response = new JSONObject();

                response.put(StringConstant.RESPONSE_STATUS.getConstant().toString(), StringConstant.RESPONSE_STATUS_OK.getConstant().toString());

                response.put(StringConstant.RESPONSE_MESSAGE.getConstant().toString(), "Product bought");

                return response;
            }
            // quantity does not exists...
            var response = new JSONObject();

            response.put(StringConstant.RESPONSE_STATUS.getConstant().toString(), StringConstant.RESPONSE_STATUS_ERROR.getConstant().toString());

            response.put(StringConstant.RESPONSE_MESSAGE.getConstant().toString(), "Quantity is more than available");

            return response;
        }
        // product does not exists
        var response = new JSONObject();

        response.put(StringConstant.RESPONSE_STATUS.getConstant().toString(), StringConstant.RESPONSE_STATUS_ERROR.getConstant().toString());

        response.put(StringConstant.RESPONSE_MESSAGE.getConstant().toString(), "Product is not available");

        return response;
    }
}
