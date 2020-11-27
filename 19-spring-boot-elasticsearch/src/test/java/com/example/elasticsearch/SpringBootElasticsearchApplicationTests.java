package com.example.elasticsearch;

import com.example.elasticsearch.entity.Book;
import com.example.elasticsearch.entity.Goods;
import com.example.elasticsearch.respository.BookRepository;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.reindex.UpdateByQueryAction;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequestBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class SpringBootElasticsearchApplicationTests {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    @Autowired
    DocumentOperations documentOperations;

    @Autowired
    SearchOperations searchOperations;

    @Test
    public void createBookIndex() {
        List<Book> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Book book = new Book();
            book.setId(i);
            book.setBookName("西游记---" + i);
            book.setAuthor("吴承恩");
            list.add(book);
        }
        bookRepository.saveAll(list);
        System.out.printf("保存成功");
    }

    @Test
    public void findAll() {
        Iterable<Book> result = bookRepository.findAll();
        Iterator<Book> data = result.iterator();
        while (data.hasNext()) {
            System.out.printf(data.next().toString());
        }
    }

    @Test
    public void findById() {
        Optional<Book> book = bookRepository.findById("10");

    }

    @Test
    public void deleteById() {
        long count = bookRepository.count();
        System.out.printf("删除前总数:" + count);
        bookRepository.deleteById("10");
        count = bookRepository.count();
        System.out.printf("删除后总数:" + count);
    }

    @Test
    public void findByCustomNameLike() {
        Iterable<Book> result = bookRepository.findBookByBookNameLike("9");
        Iterator<Book> data = result.iterator();
        while (data.hasNext()) {
            System.out.printf(data.next().toString());
        }
    }

    @Test
    public void findByCustomNameContains() {
        Iterable<Book> result = bookRepository.findBookByBookNameContains("9");
        Iterator<Book> data = result.iterator();
        while (data.hasNext()) {
            System.out.printf(data.next().toString());
        }
    }

    @Test
    public void doAction() {
        bookRepository.doAction();
    }

    @Test
    public void testQuery() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本的分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("bookName", "西游记"));
        // 执行搜索，获取结果
        Page<Book> items = bookRepository.search(queryBuilder.build());
        // 打印总条数
        System.out.println(items.getTotalElements());
        // 打印总页数
        System.out.println(items.getTotalPages());
        items.forEach(System.out::println);
    }

    @Test
    public void testNativeQueryPage(){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本的分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("bookName", "西游记---99"));

        // 初始化分页参数
        int page = 0;
        int size = 3;
        // 设置分页参数
        queryBuilder.withPageable(PageRequest.of(page, size));

        // 执行搜索，获取结果
        Page<Book> items = bookRepository.search(queryBuilder.build());
        // 打印总条数
        System.out.println(items.getTotalElements());
        // 打印总页数
        System.out.println(items.getTotalPages());
        // 每页大小
        System.out.println(items.getSize());
        // 当前页
        System.out.println(items.getNumber());
        items.forEach(System.out::println);
    }


    @Test
    public void insert() {
        Goods goods = Goods.builder()
                .id(0L)
                .name("HHKB静电容键盘")
                .title("程序员必备")
                .price(new BigDecimal(9999999))
                .publishDate("2020-11-20")
                .build();
        /**
         * index用于新增或者修改整个文档
         * 文档不存在，执行新增操作
         * 文档存在，执行修改操作
         * 此处只需要设置索引的对象即可，底层代码会从索引对象的@Document注解中获取索引、类型以及文档的id
         */
        IndexQuery indexQuery = new IndexQueryBuilder().withObject(goods).build();
        IndexCoordinates indexCoordinates = elasticsearchRestTemplate.getIndexCoordinatesFor(goods.getClass());
        elasticsearchRestTemplate.index(indexQuery, indexCoordinates);
        System.out.printf("新增完成");
    }

    @Test
    public void getById() {
        IndexCoordinates indexCoordinates = elasticsearchRestTemplate.getIndexCoordinatesFor(Goods.class);
        Goods good = elasticsearchRestTemplate.get("0", Goods.class, indexCoordinates);
        System.out.printf("查询结果：" + good);
    }

    @Test
    public void batchInsert() {
        List<Goods> goodsList = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            Goods goods = Goods.builder()
                    .id((long) i)
                    .name("HHKB静电容键盘---" + i)
                    .title("程序员必备")
                    .price(new BigDecimal(9999999))
                    .publishDate("2020-11-20")
                    .build();
            goodsList.add(goods);
        }

        List<IndexQuery> indexQueryList = new ArrayList<>();
        for (int i = 0, len = goodsList.size(); i < len; i++) {
            indexQueryList.add(new IndexQueryBuilder()
                    .withObject(goodsList.get(i)).build());
        }
        System.out.printf("批量新增前...");
        // 使用bulk方法批量索引文档
        IndexCoordinates indexCoordinates = elasticsearchRestTemplate.getIndexCoordinatesFor(Goods.class);
        elasticsearchRestTemplate.bulkIndex(indexQueryList, indexCoordinates);
        System.out.printf("批量新增完成");
    }

    @Test
    public void update() {
        Goods goods = Goods.builder()
                .id(0L)
                .name("HHKB静电容键盘---0")
                .title("程序员必备")
                .price(new BigDecimal(6666))
                .publishDate("2020-11-20")
                .build();
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(goods)
                .build();
        /**
         * 使用index方法来更新整个文档
         * 如果有字段为null，更新到es中，es文档中不会有该字段存在
         * {
         *           "_class" : "com.boot.example.Goods",
         *           "id" : 10006,
         *           "name" : "AppleiPhone 11 update",
         *           "title" : "Apple iPhone 11 (A2223) 64GB 黑色 移动联通电信4G手机 双卡双待 update"
         *         }
         */
        IndexCoordinates indexCoordinates = elasticsearchRestTemplate.getIndexCoordinatesFor(Goods.class);
        elasticsearchRestTemplate.index(indexQuery, indexCoordinates);
        System.out.printf("数据更新成功");
    }

    @Test
    public void partialUpdate() {
        // 构建需要更新的字段
        Map<String, Object> map = new HashMap<>();
        map.put("price", new BigDecimal(6666));
        map.put("publishDate", "2019-09-08");
//        UpdateQuery updateQuery = new UpdateQueryBuilder()
//                .withId("10001")
//                .withClass(Goods.class)
//                .withUpdateRequest(new UpdateRequest().doc(map))
//                .build();
//         更新文档的部分内容
//         更新文档的部分内容
//        elasticsearchRestTemplate.update(updateQuery);
    }

    @Test
    public void list() {
        /**
         * 使用match进行搜索
         * GET /goods/_search
         * {
         *   "query": {
         *     "match": {
         *       "title": "apple"
         *     }
         *   }
         * }
         */
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(new MatchQueryBuilder("name", "HHKB静电容键盘---25"))
                .withSort(new FieldSortBuilder("publishDate").order(SortOrder.DESC))
                .build();
        IndexCoordinates indexCoordinates = elasticsearchRestTemplate.getIndexCoordinatesFor(Goods.class);
        List<Goods> goodsList = elasticsearchRestTemplate.queryForList(nativeSearchQuery, Goods.class, indexCoordinates);
        System.out.printf("文档总数:" + goodsList.size() + "---商品列表:" + goodsList);
    }

    @Test
    public void listByMultiCondition() {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(new BoolQueryBuilder()
                        .must(new MatchQueryBuilder("title", "程序员必备"))
                        .must(new RangeQueryBuilder("price").from(BigDecimal.ZERO).to(new BigDecimal(9999999)))
                )
                .build();
        IndexCoordinates indexCoordinates = elasticsearchRestTemplate.getIndexCoordinatesFor(Goods.class);
        List<Goods> goodsList = elasticsearchRestTemplate.queryForList(nativeSearchQuery, Goods.class, indexCoordinates);
        System.out.printf("文档总数:" + goodsList.size() + "---商品列表:" + goodsList);
    }

    @Test
    public void page() {
        QueryBuilder queryBuilder = new MatchQueryBuilder("title", "程序员必备");
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(PageRequest.of(0, 10))
                .build();
        IndexCoordinates indexCoordinates = elasticsearchRestTemplate.getIndexCoordinatesFor(Goods.class);
        Page<Goods> page = elasticsearchRestTemplate.queryForPage(nativeSearchQuery, Goods.class, indexCoordinates);
        List<Goods> goodsList = page.getContent();
        System.out.printf("文档总数:" + goodsList.size() + "---商品列表:" + goodsList);
    }

    @Test
    public void delete() {
        // 删除文档
        IndexCoordinates indexCoordinates = elasticsearchRestTemplate.getIndexCoordinatesFor(Goods.class);
        elasticsearchRestTemplate.delete("0", indexCoordinates);
        System.out.printf("删除成功");
    }

    @Test
    public void count() {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().build();
        Long count = elasticsearchRestTemplate.count(searchQuery, Goods.class);
        System.out.printf("商品总数:" + count);
    }

    @Test
    public void avgPrice() {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .addAggregation(AggregationBuilders.avg("avg_price").field("price"))
                .build();
//        double avgPrice = elasticsearchRestTemplate.que(searchQuery, response -> {
//            Avg avg = response.getAggregations().get("avg_price");
//            return avg.getValue();
//        });
//        log.info("avg_price：{}", avgPrice);
    }

    @Test
    public void maxPrice() {
        Query searchQuery = new NativeSearchQueryBuilder()
                .addAggregation(AggregationBuilders.max("max_price").field("price"))
                .build();
//        double maxPrice = elasticsearchRestTemplate.query(searchQuery, response -> {
//            Max max = response.getAggregations().get("max_price");
//            return max.getValue();
//        });
//        System.out.printf("max_price:" + maxPrice);
    }

}
