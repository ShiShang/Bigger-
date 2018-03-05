package top.cicle.bigger.dao;

import static top.cicle.bigger.common.biggerConstants.*;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.mapping.FetchType;

import top.cicle.bigger.dao.provider.PerformanceDynaSqlProvider;
import top.cicle.bigger.dao.provider.Performance_detailDynaSqlProvider;
import top.cicle.bigger.domain.Performance;
import top.cicle.bigger.domain.Performance_detail;

public interface PerformanceDao {

	@SelectProvider(type=PerformanceDynaSqlProvider.class,method="addPerformance")
	void addPerformance(Performance pf);
	
	
	//根据房源ID和时间来查找Performance
	@SelectProvider(type=PerformanceDynaSqlProvider.class,method="findPerformanceByIdAndDate")
	List<Performance> findPerformanceByIdAndDate(Map<String,Object> params);
	
	//根据房源ID和时间来删除Performance
	@SelectProvider(type=PerformanceDynaSqlProvider.class,method="deletePerformanceByIdAndDate")
	void deletePerformanceByIdAndDate(Map<String,Object> params);
	
	//查找所有的Performance数据
	@Select("select * from "+PERFORMANCETABLE+" order by concat(year,month,house_id)")
	List<Performance> getAllPerformance();
	
	//根据年月查找当月总利润
	@SelectProvider(type=PerformanceDynaSqlProvider.class,method="findMonthProditByDate")
	Integer getMonthProfitByDate(Map<String,Object> params);
	
}
