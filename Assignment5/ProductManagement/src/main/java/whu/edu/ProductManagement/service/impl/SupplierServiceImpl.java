package whu.edu.ProductManagement.service.impl;

import whu.edu.ProductManagement.domain.Supplier;
import whu.edu.ProductManagement.dao.SupplierDao;
import whu.edu.ProductManagement.exception.ProductManagementException;
import whu.edu.ProductManagement.service.ISupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author little prince
 * @since 2023-10-25
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierDao, Supplier> implements ISupplierService {
    public void addSupplier(Supplier supplier) throws ProductManagementException {
        try {
            this.baseMapper.insert(supplier);
        } catch (Exception e) {
            throw new ProductManagementException("add supplier failed:" + e.getMessage());
        }
    }
    public void updateSupplier(Supplier supplier) throws ProductManagementException {
        try {
            this.baseMapper.updateById(supplier);
        } catch (Exception e) {
            throw new ProductManagementException("update supplier failed:" + e.getMessage());
        }
    }
    public void removeSupplier(Supplier supplier) throws ProductManagementException {
        try {
            this.baseMapper.deleteById(supplier.getName());
        } catch (Exception e) {
            throw new ProductManagementException("remoev supplier failed:" + e.getMessage());
        }
    }

}
