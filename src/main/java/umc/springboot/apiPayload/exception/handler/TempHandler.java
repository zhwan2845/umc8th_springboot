package umc.springboot.apiPayload.exception.handler;

import umc.springboot.apiPayload.code.BaseErrorCode;
import umc.springboot.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}