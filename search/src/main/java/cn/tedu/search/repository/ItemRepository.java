package cn.tedu.search.repository;

import cn.tedu.search.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {
    // ItemRepository接口要继承SpringData框架提供的父接口ElasticsearchRepository
    // 一旦继承,当前接口就可以编写使用父接口中提供的连接ES的方法了
    // 继承父接口后,SpringData会根据我们在泛型中指定的Item实体类,找到对应的索引
    // 并生成操作这个索引的基本增删改查方法,我们自己无需编写
    // ElasticsearchRepository<[要操作的实体类名称],[实体类主键的类型]>


    /*
     *  SpringData自定义查询
     * 我们要编写遵循SpringData给定的格式的方法名
     * SpringData会根据方法名自动推断出、查询意图,生成能够完成该查询的语句
     * query(查询):表达当前的方法是一个查询方法,类似sql语句中的select
     * Item/Items:是要查询的实体类名称,返回集合的查询应该带s
     * By(通过\根据):标识开始设置查询条件的关键字,等价于sql语句中的where
     * Title:要查询的字段,可以是Item实体类中的任何字段
     * Matches(匹配):执行查询的条件,Matches是查询匹配字符串的关键字,类似于sql语句中的like
     */
    Iterable<Item> queryItemsByTitleMatches(String title);

    /*
     * 自定义多条件查询
     * 多个条件之间使用and或or来分隔，表示多个条件之间的逻辑关系
     * 下面我们要使用title和brand字段进行多条件查询
     * 多个条件时,方法名要按照规则编写多个条件,参数也要对应条件数量来变化
     * 声明的参数会按照顺序依次赋值到需要值的条件中,和参数名称无关
     */
    Iterable<Item> queryItemByTitleMatchesAndBrandMatches(String title,String brand);

    /*
    * 排序查询
    *
    */
    Iterable<Item> queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc(String title,String brand);

    // 分页查询
    // 返回值类型需要修改为Page类型,这个类型既可以保存从ES中查询出的数据
    // 又可以保存当前分页查询的分页信息例如:当前页码,每页条数,总条数,总页数,有没有上一页,有没有下一页等
    // 参数方面,需要在参数列表末尾添加一个Pageable类型的参数
    // 这个类型的对象包含要查询的页码和每页的条数
    Page<Item> queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc(String title, String brand, Pageable pageable);

}
