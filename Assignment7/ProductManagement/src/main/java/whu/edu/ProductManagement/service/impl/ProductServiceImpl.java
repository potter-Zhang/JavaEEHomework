package whu.edu.ProductManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import whu.edu.ProductManagement.ProductManagementApplication;
import whu.edu.ProductManagement.domain.Product;
import whu.edu.ProductManagement.dao.ProductDao;
import whu.edu.ProductManagement.service.IProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements IProductService {
    @Autowired
    ProductDao productDao;

    @Cacheable(cacheNames = "product", key = "#id", condition = "#name != null")
    public Product getProduct(String id) {
        return productDao.selectById(id);
    }

    @CacheEvict(cacheNames = "product", key = "#id", condition = "#name != null")
    public int updateProduct(Product product) {
        return productDao.updateById(product);
    }
}
