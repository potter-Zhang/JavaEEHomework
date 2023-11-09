package whu.edu.ProductManagement.service.impl;

import whu.edu.ProductManagement.domain.User;
import whu.edu.ProductManagement.dao.UserDao;
import whu.edu.ProductManagement.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author little prince
 * @since 2023-11-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

}
