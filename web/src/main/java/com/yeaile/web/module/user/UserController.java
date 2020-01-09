package com.yeaile.web.module.user;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.yeaile.common.domain.user.dto.UserDTO;
import com.yeaile.common.domain.user.dto.UserLoginDto;
import com.yeaile.common.domain.user.dto.UserQueryDto;
import com.yeaile.common.domain.user.dto.UserRegDto;
import com.yeaile.common.domain.user.vo.UserVo;
import com.yeaile.common.result.R;
import com.yeaile.common.result.Result;
import com.yeaile.common.result.Rx;
import com.yeaile.common.result.StatusCode;
import com.yeaile.common.utils.JwtTokenUtil;
import com.yeaile.user.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */

@RestController
@RequestMapping("/api/ceviri-kizlar/user/")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private HttpServletRequest request;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    /**
     * @author yefan
     * @date 2019/12/4
     * @Description
     * @参数
     * @返回值
     * @修改人和其他信息
     **/

    @RequestMapping(value = "v1/register/{code}",method = RequestMethod.POST)
    public Result register(@PathVariable String code, @RequestBody UserRegDto user){
        iUserService.register(user);
        return new Result(true, StatusCode.OK,"注册成功");
    }

    @RequestMapping(value = "v1/login",method = RequestMethod.POST)
    public R login(@RequestBody UserLoginDto user){
        String codeIn = (String) request.getSession().getAttribute("vrifyCode");
        request.getSession().removeAttribute("verificationCode");
        if (StringUtils.isEmpty(codeIn) || !codeIn.equals(user.getCode())) {
            return  Rx.error("验证码错误，或已失效");
        }
        String token = iUserService.login(user);
        if (token == null) {
            return Rx.error("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return Rx.success(tokenMap);

    }






    @RequestMapping(value = "/v1/info", method = RequestMethod.GET)
    public Result getInfo() {

        return new Result(true, StatusCode.OK, "");
    }

    /**
     * 分页
     * @param userQueryDto
     * @return
     */
    @RequestMapping(value = "/v1/userlist", method = RequestMethod.POST)
    public Result userList(@RequestBody UserQueryDto userQueryDto) {
        IPage<UserVo> userVoIPage = iUserService.userList(userQueryDto);
        return new Result(true, StatusCode.OK, userVoIPage);
    }



    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */

    @RequestMapping(value = "/v1/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", iUserService.findById(id));
    }
    /**
     * 如果你想指定AND（和）这个条件，我的意思说deleteUser 方法只能被同时拥有ADMIN & DBA 。但是仅仅通过使用 @Secured注解是无法实现的。
     *
     * 但是你可以使用Spring的新的注解@PreAuthorize/@PostAuthorize（支持Spring EL），使得实现上面的功能成为可能，而且无限制。
     *
     * Spring的 @PreAuthorize/@PostAuthorize 注解更适合方法级的安全,也支持Spring 表达式语言，提供了基于表达式的访问控制。
     *
     * @PreAuthorize 注解适合进入方法前的权限验证， @PreAuthorize可以将登录用户的roles/permissions参数传到方法中。
     *
     * @PostAuthorize 注解使用并不多，在方法执行后再进行权限验证。
     *
     * 所以它适合验证带有返回值的权限。Spring EL 提供 返回对象能够在表达式语言中获取返回的对象returnObject。
     *
     * 请参考 Common Built-In Expressions 获取支持的表达式.
     *
     * 现在言归正常，使用@PreAuthorize / @PostAuthorize注解
     */


    /**
     * 增加
     *
     * @param userDto 由于 @PreAuthorize可以使用Spring 表达式语言, 使用EL表达式可以轻易的表示任意条件. deleteUser方法 可以被拥有ADMIN & DBA角色的用户调用 .
     *                <p>
     *                另外，我们增加了带有@PostAuthorize注解的findById()方法。通过@PostAuthorize注解 method(User object)的返回值在Spring表达式语言中可以通过returnObject 来使用。
     *                在例子中我们确保登录用户只能获取他自己的用户对象。
     */
    @RequestMapping(value = "/v1" ,method = RequestMethod.POST)
    public Result add(@RequestBody UserDTO userDto) {
        iUserService.addUser(userDto);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param userDto
     */
    @RequestMapping(value = "/v1/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody UserDTO userDto, @PathVariable String id) {
        userDto.setId(id);
        iUserService.updateUser(userDto);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        iUserService.deleteUserById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 退出
     *
     * @param
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result logout() {

        return new Result(true, StatusCode.OK, "删除成功");
    }

}

