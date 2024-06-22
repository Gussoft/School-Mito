package com.gussoft.school_mito.integration.transfer.response;

import java.util.Date;
public record AuthResponse(String token, Date expiration) {

}
