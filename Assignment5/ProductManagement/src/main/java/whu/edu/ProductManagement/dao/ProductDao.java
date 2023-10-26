package whu.edu.ProductManagement.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import whu.edu.ProductManagement.domain.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import whu.edu.ProductManagement.domain.Supplier;

import java.util.List;

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
    @Select("select supplier.* from supplier " +
            "where supplier.name = product.supplier and product.id = #{productId}")
    public List<Supplier> findSuppliersByProduct(@Param("productId")Long productId);
}
