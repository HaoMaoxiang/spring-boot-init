package tech.mervyn.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import tech.mervyn.common.api.ApiResult;
import tech.mervyn.dao.entity.user.UserEntity;
import tech.mervyn.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器123
 * </p>
 *
 * @author haomaoxiang@126.com
 * @since 2020-02-19
 */
@Slf4j
@RestController
@Controller
@RequestMapping("/user")
@Api(" API")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "获取UserEntity对象详情", notes = "查看", response = UserEntity.class)
    public ResponseEntity<ApiResult> getUserEntity(@PathVariable("id") Long id) {
        UserEntity user = userService.getById(id);
        return new ResponseEntity<>(new ApiResult(user), HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation(value = "添加UserEntity对象", notes = "添加", response = ApiResult.class)
    public ResponseEntity<ApiResult> addUserEntity(@RequestBody UserEntity user) {
        boolean res = userService.save(user);
        return new ResponseEntity<>(new ApiResult(res), HttpStatus.OK);
    }

    @PostMapping("{id}")
    @ApiOperation(value = "修改UserEntity对象", notes = "修改", response = ApiResult.class)
    public ResponseEntity<ApiResult> updateUserEntity(@PathVariable("id") Long id, @RequestBody UserEntity user) {
        boolean res = userService.updateById(user);
        return new ResponseEntity<>(new ApiResult(res), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除UserEntity对象", notes = "删除", response = ApiResult.class)
    public ResponseEntity<ApiResult> deleteUserEntity(@PathVariable("id") Long id) {
        boolean res = userService.removeById(id);
        return new ResponseEntity<>(new ApiResult(res), HttpStatus.OK);
    }

}

