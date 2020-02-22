package tech.mervyn.dao.mapper.user;

import tech.mervyn.dao.entity.user.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author haomaoxiang@126.com
 * @since 2020-02-19
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    UserEntity getOneByName(@Param("name") String name);

}
