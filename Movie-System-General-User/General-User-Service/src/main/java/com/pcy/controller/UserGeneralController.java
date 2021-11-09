package com.pcy.controller;

import com.pcy.constant.ErrorCodeMsg;
import com.pcy.domain.UserGeneral;
import com.pcy.model.LoginResult;
import com.pcy.model.ResponseObject;
import com.pcy.service.UserGeneralLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PengChenyu
 * @since 2021-07-01 20:47:19
 */
@RestController
@RequestMapping("user")
@Api(value = "user", tags = "普通用户")
public class UserGeneralController {

    @Autowired
    private UserGeneralLoginService userGeneralLoginService;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "username", value = "用户名"),
                    @ApiImplicitParam(name = "password", value = "用户的密码"),
            }
    )
    public ResponseObject<LoginResult<UserGeneral>> login(@RequestParam String username, @RequestParam String password) {
        LoginResult<UserGeneral> loginResult = userGeneralLoginService.login(username, password);
        if(!loginResult.getIsLoginSuccess()){
            return ResponseObject.failed(ErrorCodeMsg.UNAUTHORIZED_CODE,ErrorCodeMsg.UNAUTHORIZED_MESSAGE, null);
        }
        return ResponseObject.success(ErrorCodeMsg.OK_CODE, ErrorCodeMsg.OK_MESSAGE, loginResult);
    }


}
