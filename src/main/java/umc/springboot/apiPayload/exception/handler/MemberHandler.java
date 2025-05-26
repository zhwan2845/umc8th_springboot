package umc.springboot.apiPayload.exception.handler;

import umc.springboot.apiPayload.code.BaseErrorCode;
import umc.springboot.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
