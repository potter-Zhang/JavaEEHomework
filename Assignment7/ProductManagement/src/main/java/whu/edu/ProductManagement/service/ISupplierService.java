package whu.edu.ProductManagement.service;

import whu.edu.ProductManagement.domain.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author little prince
 * @since 2023-11-08
 */
public interface ISupplierService extends IService<Supplier> {
    public int updateSupplier(Supplier supplier);
    public Supplier getSupplier(String id);
}
