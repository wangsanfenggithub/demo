package com.controller;

import com.po.Dto;
import com.po.ItripUser;
import com.service.IUserService;
import com.util.DtoUtil;
import com.util.ErrorCode;
import com.util.ItripUserVO;
import com.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private IUserService userService;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registerbyphone")
    public Dto save(HttpServletRequest request, HttpServletResponse response, @RequestBody ItripUserVO userVO) {
          /*  System.out.println(us.toString());
            System.out.println("账号：" + us.getUserCode());
            System.out.println("账号：" + us.getUserPassword());
            System.out.println("账号：" + us.getUserCode());
            return DtoUtil.returnFail("用户已存在，注册失败", ErrorCode.AUTH_USER_ALREADY_EXISTS);*/
        try {
            if (!validPhone(userVO.getUserCode())) {
                return DtoUtil.returnFail("请使用正确的手机号注册", ErrorCode.AUTH_ILLEGAL_USERCODE);
            }
            ItripUser user = new ItripUser();
            user.setUserCode(userVO.getUserCode());
            user.setUserPassword(userVO.getUserPassword());
            user.setUserType(0);
            user.setUserName(userVO.getUserName());
            System.out.println(user.toString());
            //该用户不存在
            System.out.println(user.getUserCode());
            ItripUser olduser = userService.findByUsername(user.getUserCode());
            System.out.println(olduser.toString());
            if (olduser.getUserCode() == null) {
                System.out.println("1111111111111");
                user.setUserPassword(MD5.getMd5(user.getUserPassword(), 32));
                //添加用户
                userService.itriptxCreateUserByPhone(user);
                return DtoUtil.returnSuccess();
            } else {
                System.out.println("222222222222222");
                return DtoUtil.returnFail("用户已存在，注册失败", ErrorCode.AUTH_USER_ALREADY_EXISTS);
            }

        } catch (java.lang.NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail(e.getMessage(), ErrorCode.AUTH_UNKNOWN);
        }
        return DtoUtil.returnFail("用户已存在，注册失败", ErrorCode.AUTH_USER_ALREADY_EXISTS);
    }

    /**
     * 合法E-mail地址：
     * 1. 必须包含一个并且只有一个符号“@”
     * 2. 第一个字符不得是“@”或者“.”
     * 3. 不允许出现“@.”或者.@
     * 4. 结尾不得是字符“@”或者“.”
     * 5. 允许“@”前的字符中出现“＋”
     * 6. 不允许“＋”在最前面，或者“＋@”
     */
    private boolean validEmail(String email) {

        String regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        return Pattern.compile(regex).matcher(email).find();
    }

    /**
     * 验证是否合法的手机号
     *
     * @param phone
     * @return
     */
    private boolean validPhone(String phone) {
        String regex = "^1[3578]{1}\\d{9}$";
        return Pattern.compile(regex).matcher(phone).find();
    }

}
