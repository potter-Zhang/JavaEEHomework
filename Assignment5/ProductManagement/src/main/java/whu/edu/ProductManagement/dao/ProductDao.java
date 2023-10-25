package whu.edu.ProductManagement.dao;

import org.apache.ibatis.annotations.Mapper;
import whu.edu.ProductManagement.domain.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author little prince
 * @since 2023-10-25
 */
@Mapper
public interface ProductDao extends BaseMapper<Product> {

}
