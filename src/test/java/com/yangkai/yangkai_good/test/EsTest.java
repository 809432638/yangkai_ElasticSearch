package com.yangkai.yangkai_good.test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.yangkai.yangkai_good.domain.ESUtils;
import com.yangkai.yangkai_good.domain.Good;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class EsTest {
		@Resource
		private ElasticsearchTemplate elasticsearchTemplate;
		
		//创建索引
		@Test
		public void creatIndex() {
			elasticsearchTemplate.createIndex(Good.class);
			
		}
		
		
		//保存数据
		@Test
		public void saveGoods() {
			for (int i = 1; i <= 10; i++) {
				Good good = new Good(i, "杨笑月"+i, 180000.0, "媳妇", new Date());
				//创建查询
				IndexQuery query = new IndexQuery();
				query.setId(good.getId()+"");
				query.setObject(good);
				//保存
				elasticsearchTemplate.index(query);
			}
		}
		
		
		
		@Test
		public void deleteGood() {
			//删除
//			elasticsearchTemplate.delete(Good.class,1+"");
			elasticsearchTemplate.deleteIndex(Good.class);
		}
		
		
		
		//取值
		@Test
		public void selectGood() {
			GetQuery getQuery = new GetQuery();
			getQuery.setId("4");
			Good good = elasticsearchTemplate.queryForObject(getQuery, Good.class);
			System.out.println(good);
		}
		
		
		//模糊查询
		@Test
		public void selectLikeGood() {
			//构建查询对象
			MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("name", "笑");
			//定义查询对象
			SearchQuery query = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
			//执行查询
			List<Good> list = elasticsearchTemplate.queryForList(query, Good.class);
			//for循环输出
//			for (Good good : list) {
//				System.out.println(good);
//			}
		
			//Lamdan表达式
			list.forEach(System.out::println);
		}
		
		
	
		@Test
		public void slectHightGood() {
			//获取分页对象  高亮
			AggregatedPage<?> objects = ESUtils.selectObjects(elasticsearchTemplate, Good.class, 1, 3, new String[] {"name"}, "笑");
			List<?> list = objects.getContent();
			
			list.forEach(System.out::println);
		}
}
