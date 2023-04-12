package Ecommerce.services;

import Ecommerce.constant.StringConstant;
import Ecommerce.repository.TransactionRepo;
import org.json.JSONObject;

public class TransactionService
{
    public JSONObject showTransaction(int customerID)
    {
        var list = TransactionRepo.getInstance().select(customerID);

        var response = new JSONObject();

        response.put(StringConstant.RESPONSE_STATUS.getConstant().toString(), StringConstant.RESPONSE_STATUS_OK);

        response.put(StringConstant.TRANSACTION.getConstant().toString(), list);

        return response;
    }

    public JSONObject showTransaction()
    {
        var list = TransactionRepo.getInstance().select();

        System.out.println(list);

        var response = new JSONObject();

        response.put(StringConstant.RESPONSE_STATUS.getConstant().toString(), StringConstant.RESPONSE_STATUS_OK);

        response.put(StringConstant.TRANSACTION.getConstant().toString(), list);

        return response;
    }
}
