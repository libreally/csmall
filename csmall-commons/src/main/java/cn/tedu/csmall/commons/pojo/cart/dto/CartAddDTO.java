package cn.tedu.csmall.commons.pojo.cart.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "新增购物车的DTO")
@Data
public class CartAddDTO implements Serializable {
    @ApiModelProperty(value = "商品编号", name = "commodityCode", example = "PC100")
    private String commodityCode; //商品编号

    @ApiModelProperty(value = "价格", name = "price", example = "100")
    private Integer price;    // 价格

    @ApiModelProperty(value = "用户id", name = "userId", example = "UU100")
    private String userId;    // 用户id

    @ApiModelProperty(value = "数量", name = "count", example = "5")
    private Integer count;    // 数量
}
