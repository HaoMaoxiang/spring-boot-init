package tech.mervyn.service.user.impl;

import tech.mervyn.dao.entity.user.UserEntity;
import tech.mervyn.dao.mapper.user.UserMapper;
import tech.mervyn.service.user.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author haomaoxiang@126.com
 * @since 2020-02-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

}
