package top.cicle.bigger.dao.provider;

import static top.cicle.bigger.common.biggerConstants.*;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import top.cicle.bigger.domain.Performance;

public class PerformanceDynaSqlProvider {

	
	public String addPerformance(final Performance pf)
	{
		String sql=new SQL()
		{
			{
				INSERT_INTO(PERFORMANCETABLE);
				if(pf!=null)
				{
					if(pf.getYear()!=null)
					{
						VALUES("year","#{year}");
					}
					if(pf.getMonth()!=null)
					{
						VALUES("month","#{month}");
					}
					if(pf.getCost()!=null)
					{
						VALUES("cost","#{cost}");
					}
					if(pf.getFee()!=null)
					{
						VALUES("fee","#{fee}");
					}
					if(pf.getWater_fee()!=null)
					{
						VALUES("water_fee","#{water_fee}");
					}
					if(pf.getElec_fee()!=null)
					{
						VALUES("elec_fee","#{elec_fee}");
					}
					if(pf.getClean_fee()!=null)
					{
						VALUES("clean_fee","#{clean_fee}");
					}
					if(pf.getDays()!=null)
					{
						VALUES("days","#{days}");
					}
					if(pf.getLive_days()!=null)
					{
						VALUES("live_days","#{live_days}");
					}
					if(pf.getIncome()!=null)
					{
						VALUES("income","#{income}");
					}
					if(pf.getProfit()!=null)
					{
						VALUES("profit","#{profit}");
					}
					if(pf.getHouse()!=null)
					{
						VALUES("house_id","#{house.id}");
					}
					
				}
			}
		}.toString();
		return sql;
	}
	
	//根据房源名称和时间来查找Performance
		public String findPerformanceByIdAndDate(final Map<String,Object> params)
		{
			String sql=new SQL()
			{
				{
					SELECT("*");
					FROM(PERFORMANCETABLE);
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
		
		//根据房源名称和时间来删除Performance
				public String deletePerformanceByIdAndDate(final Map<String,Object> params)
				{
					String sql=new SQL()
					{
						{
							DELETE_FROM(PERFORMANCETABLE);
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
	public String findMonthProditByDate(final Map<String,Object> params)
	{
		String sql=new SQL()
		{
			{
				SELECT("sum(profit)");
				FROM(PERFORMANCETABLE);
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
