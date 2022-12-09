package cn.tedu.csmall.commons.restful;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class JsonPage<T> implements Serializable{

    //当前类代替page、pageInfo类，既能保存分页数据，又能保存分页信息
    //也就是要将分页数据和分页信息声明出属性,至于声明出那些属性,是自己定义的原则是满足前端需要
    @ApiModelProperty(value = "总页数", name = "totalPage")
    private Integer totalPages;
    @ApiModelProperty(value = "总条数", name = "totalCount")
    private Long totalCount;
    @ApiModelProperty(value = "页码", name = "page")
    private Integer page;
    @ApiModelProperty(value = "每页条数", name = "pageSize")
    private Integer pageSize;

    @ApiModelProperty(value = "分页数据", name = "list")
    private List<T> list;

    // 下面定义一个方法,实现将PageInfo类型中的对象的值赋值给当前JsonPage对象对应的属性
// 相当于一个转换方法
// 任何静态方法需要泛型类型声明都需要在static关键字和返回值之前声明一个<T>
    public static <T> JsonPage<T> restPage(PageInfo<T> pageInfo){
        JsonPage<T> result=new JsonPage<>();
        result.setPage(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotalCount(pageInfo.getTotal());
        result.setTotalPages(pageInfo.getPages());
        // 将pageInfo中的查询结果结合也要赋值过去
        result.setList(pageInfo.getList());
        return  result;
    }
}
