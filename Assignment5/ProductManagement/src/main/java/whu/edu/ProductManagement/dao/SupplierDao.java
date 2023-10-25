package whu.edu.ProductManagement.dao;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import whu.edu.ProductManagement.domain.Supplier;
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
public interface SupplierDao extends BaseMapper<Supplier> {

}
