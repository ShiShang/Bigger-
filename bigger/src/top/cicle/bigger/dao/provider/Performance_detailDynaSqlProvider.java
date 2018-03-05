package top.cicle.bigger.dao.provider;

import static top.cicle.bigger.common.biggerConstants.*;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import top.cicle.bigger.domain.Performance_detail;

public class Performance_detailDynaSqlProvider {

	
	public String addPerformance_detail(final Performance_detail pd)
	{
		String sql=new SQL()
		{
			{
				INSERT_INTO(PERFORMANCE_DETAILTABLE);
				if(pd !=null)
				{
					if(pd.getYear()!=null)
					{
						VALUES("year","#{year}");
					}
					if(pd.getMonth()!=null)
					{
						VALUES("month","#{month}");
					}
					if(pd.getDay_in()!=null)
					{
						VALUES("day_in","#{day_in}");
					}
					if(pd.getDay_out()!=null)
					{
						VALUES("day_out","#{day_out}");
					}
					if(pd.getDay_live()!=null)
					{
						VALUES("day_live","#{day_live}");
					}
					if(pd.getPrice()!=null)
					{
						VALUES("price","#{price}");
					}
					if(pd.getClean_fee()!=null)
					{
						VALUES("clean_fee","#{clean_fee}");
					}
					if(pd.getSource()!=null)
					{
						VALUES("source","#{source}");
					}
					if(pd.getHouse()!=null)
					{
						VALUES("house_id","#{house.id}");
					}
					
				}
			}
		}.toString();
		return sql;
	}
	
	//根据房源名称和时间来查找Performance_detail
	public String findPerformance_detailByIdAndDate(final Map<String,Object> params)
	{
		String sql=new SQL()
		{
			{
				SELECT("*");
				FROM(PERFORMANCE_DETAILTABLE);
				if(params.get("id")!=null && !params.get("id").equals(""))
				{
					Integer id=(Integer) params.get("id");
					WHERE(" house_id=#{id}");
				}
				if(params.get("year")!=null && !params.get("year").equals(""))
				{
					String year=(String) params.get("year");
					WHERE(" year=#{year}");
				}
				if(params.get("month")!=null && !params.get("month").equals(""))
				{
					String month=(String) params.get("month");
					WHERE(" month=#{month}");
				}
			}
			
		}.toString();
		return sql;
	}
	
	//根据房源名称和时间来删除Performance_detail
		public String deletePerformance_detailByIdAndDate(final Map<String,Object> params)
		{
			String sql=new SQL()
			{
				{
					DELETE_FROM(PERFORMANCE_DETAILTABLE);
					if(params.get("id")!=null && !params.get("id").equals(""))
					{
						Integer id=(Integer) params.get("id");
						WHERE(" house_id=#{id}");
					}
					if(params.get("year")!=null && !params.get("year").equals(""))
					{
						String year=(String) params.get("year");
						WHERE(" year=#{year}");
					}
					if(params.get("month")!=null && !params.get("month").equals(""))
					{
						String month=(String) params.get("month");
						WHERE(" month=#{month}");
					}
				}
				
			}.toString();
			return sql;
		}
		
		
		public String getAllOrderByDate(final Map<String,Object> params)
		{
			String sql=new SQL()
			{
				{
					SELECT("count(*)");
					FROM(PERFORMANCE_DETAILTABLE);
					if(params.get("year")!=null)
					{
						WHERE("year=#{year}");
					}
					if(params.get("month")!=null)
					{
						WHERE("month=#{month}");
					}
				}
			}.toString();
			return sql;
		}
}
