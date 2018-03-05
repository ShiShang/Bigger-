package top.cicle.bigger.dao;

import static top.cicle.bigger.common.biggerConstants.*;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import top.cicle.bigger.dao.provider.Performance_detailDynaSqlProvider;
import top.cicle.bigger.domain.Performance_detail;

public interface Performance_detailDao {

	@SelectProvider(type=Performance_detailDynaSqlProvider.class,method="addPerformance_detail")
	void addPerormance_detail(Performance_detail performance_detail);
	
	//根据房源名称和时间来查找Performance_detail
	@SelectProvider(type=Performance_detailDynaSqlProvider.class,method="findPerformance_detailByIdAndDate")
	List<Performance_detail> findPerformance_detailByIdAndDate(Map<String,Object> params);
	
	//根据房源名称和时间来删除Performance_detail
	@SelectProvider(type=Performance_detailDynaSqlProvider.class,method="deletePerformance_detailByIdAndDate")
	void deletePerformance_detailByIdAndDate(Map<String,Object> params);
	
	//根据年月来查询订单总数
	@SelectProvider(type=Performance_detailDynaSqlProvider.class,method="getAllOrderByDate")
	Integer getAllOrderByDate(Map<String,Object> params);
	
	
}
