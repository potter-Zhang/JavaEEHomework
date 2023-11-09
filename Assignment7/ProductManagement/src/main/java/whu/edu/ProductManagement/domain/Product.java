package whu.edu.ProductManagement.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author little prince
 * @since 2023-11-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Float price;


}
