package whu.edu.ProductManagement.service.impl;

import whu.edu.ProductManagement.domain.Product;
import whu.edu.ProductManagement.dao.ProductDao;
import whu.edu.ProductManagement.exception.ProductManagementException;
import whu.edu.ProductManagement.service.IProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements IProductService {
    public Product addProduct(Product product) throws ProductManagementException {
        try {
            this.baseMapper.insert(product);
            return product;
        } catch (Exception e) {
            throw new ProductManagementException("inserting a new product failed:" + e.getMessage());
        }
    }

    public Product getProductById(Long id) throws ProductManagementException {
        try {
            return this.baseMapper.selectById(id);
        } catch (Exception e) {
            throw new ProductManagementException(("failed to find product by " + id));
        }
    }

    public int removeProduct(Long id) throws ProductManagementException {
        try {
            return this.baseMapper.deleteById(id);
        } catch (Exception e) {
            throw new ProductManagementException("removing product " + id + " failed.");
        }
    }

    public Product updateProduct(Product product) throws ProductManagementException {
        try {
            Product oldProduct = this.baseMapper.selectById(product.getId());
            if (oldProduct == null) {
                this.baseMapper.insert(product);
            } else {
                this.baseMapper.updateById(product);
            }
            return oldProduct;
        } catch (Exception e) {
            throw new ProductManagementException("update product " + product.getId() + " failed");
        }
    }


}
