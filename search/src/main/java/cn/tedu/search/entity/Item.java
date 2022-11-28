package cn.tedu.search.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@Accessors(chain = true)//支持链式set赋值
@AllArgsConstructor //自动生成全参构造
@NoArgsConstructor//自动生成无参构造
//标记当前类是ES框架对应的实体类
//属性indexName指定ES中对应的索引名称,运行时不存在时会自动创建
@Document(indexName = "items")
public class Item implements Serializable {
    //标记主键
    @Id
    private Long id;
    //标记普通注解
    @Field(type = FieldType.Text,
            analyzer = "ik_max_word",
            searchAnalyzer = "ik_max_word")
    private String title;
    @Field(type = FieldType.Keyword)//不需要分词
    private String category;
    @Field(type = FieldType.Keyword)//不需要分词
    private String brand;
    @Field(type = FieldType.Double)//不需要分词
    private Double price;
    @Field(type = FieldType.Keyword,index = false)
    private String imgPath;
}
