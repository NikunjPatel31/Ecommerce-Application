package Ecommerce.services;

import Ecommerce.constant.ErrorConstant;
import Ecommerce.constant.StringConstant;
import Ecommerce.model.Customer;
import Ecommerce.repository.AuthenticationRepo;
import org.json.JSONObject;

public class AuthenticationService
{
    public JSONObject createAccount(Customer customer)
    {
        AuthenticationRepo.getInstance().insert(customer);

        var response = new JSONObject();

        response.put(StringConstant.RESPONSE_STATUS.getConstant().toString(), StringConstant.RESPONSE_STATUS_OK.getConstant());

        response.put(StringConstant.RESPONSE_MESSAGE.getConstant().toString(), "Customer account created");

        response.put(StringConstant.CUSTOMER_ID.getConstant().toString(), customer.getCustomerID());

        return response;
    }

    public JSONObject login(int customerID, String password)
    {
        var customer = (Customer) AuthenticationRepo.getInstance().select(customerID);

        var response = new JSONObject();

        if (customer == null)
        {
            // invalid credentials
            response.put(StringConstant.RESPONSE_STATUS.getConstant().toString(), StringConstant.RESPONSE_STATUS_ERROR.getConstant().toString());

            response.put(StringConstant.RESPONSE_MESSAGE.getConstant().toString(), ErrorConstant.USER_DOES_NOT_EXISTS.getConstant().toString());

            return response;
        }

        if (customer.getPassword().equals(password))
        {
            // login successful
            response.put(StringConstant.RESPONSE_STATUS.getConstant().toString(), StringConstant.RESPONSE_STATUS_OK.getConstant().toString());

            response.put(StringConstant.RESPONSE_MESSAGE.getConstant().toString(), StringConstant.LOGIN_SUCCESSFUL.getConstant().toString());

            return response;
        }
        response.put(StringConstant.RESPONSE_STATUS.getConstant().toString(), StringConstant.RESPONSE_STATUS_ERROR.getConstant().toString());

        response.put(StringConstant.RESPONSE_MESSAGE.getConstant().toString(), ErrorConstant.INVALID_CREDENTIAL.getConstant().toString());

        return response;
    }
}
