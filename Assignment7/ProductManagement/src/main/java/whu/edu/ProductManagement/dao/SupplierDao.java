package whu.edu.ProductManagement.dao;

import org.apache.ibatis.annotations.Mapper;
import whu.edu.ProductManagement.domain.Supplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author little prince
 * @since 2023-11-08
 */
@Mapper
public interface SupplierDao extends BaseMapper<Supplier> {

}
