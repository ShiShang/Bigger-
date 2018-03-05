package top.cicle.bigger.dao.provider;

import static top.cicle.bigger.common.biggerConstants.*;
import org.apache.ibatis.jdbc.SQL;
import top.cicle.bigger.domain.House;

public class HouseDynaSqlProvider {

	public String addHouse(final House house)
	{
		String sql=new SQL()
		{
			{
				INSERT_INTO(HOUSETABLE);
				if(house!=null)
				{
					if(house.getName()!=null && !house.getName().equals(""))
					{
						VALUES("name","#{name}");
					}
					if(house.getStart_time()!=null && !house.getStart_time().equals(""))
					{
						VALUES("create_time","#{create_time}");
					}
					if(house.getEnd_time()!=null && !house.getEnd_time().equals(""))
					{
						VALUES("end_time","#{end_time}");
					}
					if(house.getUser()!=null)
					{
						VALUES("user_id","#{user.id}");
					}
					if(house.getCost()!=null && !house.getCost().equals(""))
					{
						VALUES("cost","#{cost}");
					}
					if(house.getRemark()!=null && !house.getRemark().equals(""))
					{
						VALUES("remark","#{remark}");
					}
					if(house.getStatus()!=null && !house.getStatus().equals(""))
					{
						VALUES("status","#{status}");
					}
				}
			}
		}.toString();
		return sql;
	}
	
	
	
}
