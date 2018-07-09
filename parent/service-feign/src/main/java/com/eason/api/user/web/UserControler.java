package com.eason.api.user.web;

import com.eason.api.base.vo.model.FileItemModel;
import com.eason.api.base.vo.response.ResponseVo;
import com.eason.api.service.user.exception.UserServiceException;
import com.eason.api.service.user.vo.code.UserCodeRequestVo;
import com.eason.api.service.user.vo.login.LoginRequestVo;
import com.eason.api.service.user.vo.register.RegisterRequestVo;
import com.eason.api.service.user.vo.user.UserInfoRequestVo;
import com.eason.api.user.service.FUserService;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserControler {
    @Autowired
    private FUserService userServiceImpl;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseVo register(@RequestBody RegisterRequestVo registerRequestVo) {
        try {
            ResponseVo responseVo = new ResponseVo(0, "操作成功");
            responseVo.setData(userServiceImpl.register(registerRequestVo));
            return responseVo;
        } catch (UserServiceException e) {
            ResponseVo responseVo = new ResponseVo(401, e.getMessage());
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (HystrixRuntimeException e) {
            ResponseVo responseVo = new ResponseVo(500, "服务器忙，请重试！");
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (Exception e) {
            ResponseVo responseVo = new ResponseVo(500, e.getMessage());
            responseVo.setData(new HashMap<>());
            return responseVo;
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseVo login(@RequestBody LoginRequestVo requestVo) {
        try {
            ResponseVo responseVo = new ResponseVo(0, "操作成功");
            responseVo.setData(userServiceImpl.login(requestVo));
            return responseVo;
        } catch (UserServiceException e) {
            ResponseVo responseVo = new ResponseVo(401, e.getMessage());
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (HystrixRuntimeException e) {
            ResponseVo responseVo = new ResponseVo(500, "服务器忙，请重试！");
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (Exception e) {
            ResponseVo responseVo = new ResponseVo(500, e.getMessage());
            responseVo.setData(new HashMap<>());
            return responseVo;
        }
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ResponseVo reset(@RequestBody RegisterRequestVo requestVo) {
        try {
            ResponseVo responseVo = new ResponseVo(0, "操作成功");
            responseVo.setData(userServiceImpl.reset(requestVo));
            return responseVo;
        } catch (UserServiceException e) {
            ResponseVo responseVo = new ResponseVo(401, e.getMessage());
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (HystrixRuntimeException e) {
            ResponseVo responseVo = new ResponseVo(500, "服务器忙，请重试！");
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (Exception e) {
            ResponseVo responseVo = new ResponseVo(500, e.getMessage());
            responseVo.setData(new HashMap<>());
            return responseVo;
        }
    }

    @RequestMapping(value = "/getValidateCode", method = RequestMethod.POST)
    public ResponseVo getValidateCode(@RequestBody UserCodeRequestVo requestVo) {
        try {
            ResponseVo responseVo = new ResponseVo(0, "操作成功");
            responseVo.setData(userServiceImpl.getValidateCode(requestVo));
            return responseVo;
        } catch (UserServiceException e) {
            ResponseVo responseVo = new ResponseVo(401, e.getMessage());
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (HystrixRuntimeException e) {
            ResponseVo responseVo = new ResponseVo(500, "服务器忙，请重试！");
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (Exception e) {
            ResponseVo responseVo = new ResponseVo(500, e.getMessage());
            responseVo.setData(new HashMap<>());
            return responseVo;
        }
    }

    @RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST)
    public ResponseVo uploadAvatar(@RequestParam(value = "avatar", required = true) MultipartFile img, HttpServletRequest request) {
        try {
            Integer userId=null;
            String api_token=request.getHeader("token");
            if (StringUtils.isEmpty(api_token)){
                api_token = request.getParameter("token");
            }
            if (StringUtils.isNotEmpty(api_token)){
                String id = stringRedisTemplate.opsForValue().get("token:"+api_token);
                if (id != null) {
                    userId=Integer.parseInt(id);
                }
            }
            ResponseVo responseVo = new ResponseVo(0, "操作成功");
            FileItemModel fileImg = new FileItemModel();
            fileImg.setFileName("user-"+img.getOriginalFilename()+"-"+System.currentTimeMillis() );
            fileImg.setContent(img.getBytes());
            fileImg.setMimeType(img.getContentType());
            responseVo.setData(userServiceImpl.uploadAvatar(userId,fileImg));
            return responseVo;
        } catch (UserServiceException e) {
            ResponseVo responseVo = new ResponseVo(401, e.getMessage());
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (HystrixRuntimeException e) {
            ResponseVo responseVo = new ResponseVo(500, "服务器忙，请重试！");
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (Exception e) {
            ResponseVo responseVo = new ResponseVo(500, e.getMessage());
            responseVo.setData(new HashMap<>());
            return responseVo;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseVo edit(@RequestBody UserInfoRequestVo requestVo,HttpServletRequest request) {
        try {
            Integer userId=null;
            String api_token=request.getHeader("token");
            if (StringUtils.isEmpty(api_token)){
                api_token = request.getParameter("token");
            }
            if (StringUtils.isNotEmpty(api_token)){
                String id = stringRedisTemplate.opsForValue().get("token:"+api_token);
                if (id != null) {
                    userId=Integer.parseInt(id);
                }
            }
            ResponseVo responseVo = new ResponseVo(0, "操作成功");
            responseVo.setData(userServiceImpl.edit(userId,requestVo));
            return responseVo;
        } catch (UserServiceException e) {
            ResponseVo responseVo = new ResponseVo(401, e.getMessage());
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (HystrixRuntimeException e) {
            ResponseVo responseVo = new ResponseVo(500, "服务器忙，请重试！");
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (Exception e) {
            ResponseVo responseVo = new ResponseVo(500, e.getMessage());
            responseVo.setData(new HashMap<>());
            return responseVo;
        }
    }
}
