package cn.tedu.search;

import cn.tedu.search.entity.Item;
import cn.tedu.search.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SearchApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void contextLoads() {
    }

    //新增
    @Test
    void addOne() {
        Item item = new Item()
                .setId(1L)
                .setTitle("罗技激光无线游戏鼠标")
                .setCategory("鼠标")
                .setBrand("罗技")
                .setPrice(188.0)
                .setImgPath("/1.jpg");
        itemRepository.save(item);
        System.out.println("ok");
    }
    // 批量增
    @Test
    void addList(){
        // 实例化一个List,把要保存到Es中的数据都添加到这个集合中
        List<Item> list=new ArrayList<>();
        list.add(new Item(2L,"罗技激光有线办公鼠标","鼠标",
                "罗技",9.9,"/2.jpg"));
        list.add(new Item(3L,"雷蛇机械无线游戏键盘","键盘",
                "雷蛇",268.0,"/3.jpg"));
        list.add(new Item(4L,"微软有线静音办公鼠标","鼠标",
                "微软",199.0,"/4.jpg"));
        list.add(new Item(5L,"罗技机械有线背光键盘","键盘",
                "罗技",228.0,"/5.jpg"));
        itemRepository.saveAll(list);
        System.out.println("ok");
    }

    // 全查
    @Test
    void getAll(){
        // SpringData框架提供的全查ES中对应实体类所有数据的方法
        Iterable<Item> items = itemRepository.findAll();
        for(Item item : items){
            System.out.println(item);
        }
        System.out.println("----------------------------------");
        items.forEach(System.out::println);// items.forEach(item -> System.out.println(item));
    }
    //单条件自定义查询
    @Test
    void queryOne(){
        // SpringData框架提供的全查ES中对应实体类所有数据的方法
        Iterable<Item> items = itemRepository.queryItemsByTitleMatches("游戏");
        items.forEach(System.out::println);
    }

    //多条件自定义查询
    @Test
    void queryTwo(){
        // SpringData框架提供的全查ES中对应实体类所有数据的方法
        Iterable<Item> items = itemRepository
                .queryItemByTitleMatchesAndBrandMatches("游戏","罗技");
        items.forEach(System.out::println);
    }
    //多条件自定义排序查询
    @Test
    void querySort(){
        // SpringData框架提供的全查ES中对应实体类所有数据的方法
        Iterable<Item> items = itemRepository
                .queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc("游戏","罗技");
        items.forEach(System.out::println);
    }
    // 自定义分页查询
    @Test
    void queryPage(){
        int page=1;                 // 设置要查询的页码 1表示查询第一页
        int pageSize=2;             // 每页条数的设置
        Page<Item> pages=itemRepository
                .queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc(
                        "游戏","罗技", PageRequest.of(page-1,pageSize));
        pages.forEach(System.out::println);
        // pages对象包含的分页信息输出
        System.out.println("总页数:"+pages.getTotalPages());
        System.out.println("总条数:"+pages.getTotalElements());
        System.out.println("当前页:"+(pages.getNumber()+1));
        System.out.println("每页条数:"+pages.getSize());
        System.out.println("是否是首页:"+pages.isFirst());
        System.out.println("是否是末页:"+pages.isLast());

    }

}
