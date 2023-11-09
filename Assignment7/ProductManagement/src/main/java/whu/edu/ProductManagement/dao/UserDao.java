package whu.edu.ProductManagement.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import whu.edu.ProductManagement.domain.Role;
import whu.edu.ProductManagement.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author little prince
 * @since 2023-11-08
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

    @Select("select * from role inner join user_role on role.id = user_role.role_id " +
            "inner join user on user_role.user_id = user.id where user.id = #{userId}")
    public List<Role> getRoles(String userId);


}
