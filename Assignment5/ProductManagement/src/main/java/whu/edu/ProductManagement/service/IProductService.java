package whu.edu.ProductManagement.service;

import whu.edu.ProductManagement.domain.Product;
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
public interface IProductService extends IService<Product> {
    public Product addProduct(Product product) throws ProductManagementException;

    public Product getProductById(Long id) throws ProductManagementException;

    public int removeProduct(Long id) throws ProductManagementException;

    public Product updateProduct(Product product) throws ProductManagementException;
}
