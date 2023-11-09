package whu.edu.ProductManagement.service;

import whu.edu.ProductManagement.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author little prince
 * @since 2023-11-08
 */
public interface IProductService extends IService<Product> {
    public Product getProduct(String id);

    public int updateProduct(Product product);
}
