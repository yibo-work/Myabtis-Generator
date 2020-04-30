package com.lost;

import com.lost.core.ApiRequest;
import com.lost.core.ApiResponse;
import com.lost.model.BaseUser;
import com.lost.service.BaseUserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@MapperScan(basePackages = "com.lost.dao")
public class LostApplication {

    @Autowired
    BaseUserService baseUserService;

    public static void main(String[] args) {
        SpringApplication.run(LostApplication.class, args);
    }


    @RequestMapping("/init")
    public ApiResponse inåit(ApiRequest apiRequest){
        //初始化用户
        BaseUser baseUser = new BaseUser();
        baseUser.setAccount("xs1");
        baseUser.setNickname("石泰铭");
        baseUser.setPhone("18806");
        baseUser.setPassword("123");
        baseUser.setRoleId(1);
        baseUserService.save(baseUser);

        baseUser.setAccount("xs2");
        baseUser.setNickname("石泰铭的爸爸");
        baseUser.setPhone("18806");
        baseUser.setPassword("123");
        baseUser.setRoleId(1);
        baseUserService.save(baseUser);


        baseUser.setAccount("xs3");
        baseUser.setNickname("石泰铭的妈妈");
        baseUser.setPhone("18806");
        baseUser.setPassword("123");
        baseUser.setRoleId(1);

        baseUserService.save(baseUser);


        baseUser.setAccount("admin");
        baseUser.setNickname("石泰铭的管理员");
        baseUser.setPhone("18806");
        baseUser.setPassword("123");
        baseUser.setRoleId(2);

        baseUserService.save(baseUser);


        return ApiResponse.getDefaultResponse(baseUserService.list(null));
    }
}
