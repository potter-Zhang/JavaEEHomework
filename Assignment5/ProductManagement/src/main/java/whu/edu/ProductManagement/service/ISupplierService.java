package whu.edu.ProductManagement.service;

import whu.edu.ProductManagement.domain.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import whu.edu.ProductManagement.exception.ProductManagementException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author little prince
 * @since 2023-10-25
 */
public interface ISupplierService extends IService<Supplier> {
    public void addSupplier(Supplier supplier) throws ProductManagementException;
    public void updateSupplier(Supplier supplier) throws ProductManagementException;
    public void removeSupplier(Supplier supplier) throws ProductManagementException;
}
