package whu.edu.ProductManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import whu.edu.ProductManagement.dao.ProductDao;
import whu.edu.ProductManagement.domain.Product;
import whu.edu.ProductManagement.domain.Supplier;
import whu.edu.ProductManagement.dao.SupplierDao;
import whu.edu.ProductManagement.service.ISupplierService;
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
public class SupplierServiceImpl extends ServiceImpl<SupplierDao, Supplier> implements ISupplierService {
    @Autowired
    SupplierDao supplierDao;

    @Cacheable(cacheNames = "product", key = "#id", condition = "#name != null")
    public Supplier getSupplier(String id) {
        return supplierDao.selectById(id);
    }

    @CacheEvict(cacheNames = "supplier", key = "#id", condition = "#name != null")
    public int updateSupplier(Supplier supplier) {
        return supplierDao.updateById(supplier);
    }
}
