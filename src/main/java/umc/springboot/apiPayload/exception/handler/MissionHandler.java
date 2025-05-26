package umc.springboot.apiPayload.exception.handler;

import umc.springboot.apiPayload.code.BaseErrorCode;
import umc.springboot.apiPayload.exception.GeneralException;

public class MissionHandler extends GeneralException {
    public MissionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
