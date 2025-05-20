package umc.springboot.apiPayload.exception.handler;

import umc.springboot.apiPayload.code.BaseErrorCode;
import umc.springboot.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}