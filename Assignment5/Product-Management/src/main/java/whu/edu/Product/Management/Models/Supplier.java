package whu.edu.Product.Management.Models;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author little_prince
 * @since 2023-10-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "name", type = IdType.AUTO)
    private String name;

    private Long tele;

    private String location;


}
