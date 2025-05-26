package umc.springboot.apiPayload.exception.handler;

import umc.springboot.apiPayload.code.BaseErrorCode;
import umc.springboot.apiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {
    public StoreHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
