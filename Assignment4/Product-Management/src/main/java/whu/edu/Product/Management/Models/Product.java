package whu.edu.Product.Management.Models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

@Data

public class Product {

    Long id;
    String name;
    float price;

}
