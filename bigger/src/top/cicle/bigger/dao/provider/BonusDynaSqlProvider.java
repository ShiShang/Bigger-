package top.cicle.bigger.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import static top.cicle.bigger.common.biggerConstants.*;
import top.cicle.bigger.domain.Bonus;

public class BonusDynaSqlProvider {

	public String addBonus(final Bonus bonus)
	{
		String sql=new SQL()
		{
			{
				INSERT_INTO(BONUSTABLE);
				if(bonus!=null)
				{
					if(bonus.getMoney()!=null && !bonus.getMoney().equals(""))
					{
						VALUES("money","#{money}");
					}
					if(bonus.getStatus()!=null && !bonus.getStatus().equals(""))
					{
						VALUES("status","#{status}");
					}
					if(bonus.getUser()!=null && !bonus.getUser().equals(""))
					{
						VALUES("user_id","#{user.id}");
					}
					if(bonus.getTitle()!=null && !bonus.getTitle().equals(""))
					{
						VALUES("title","#{title}");
					}
				}
			}
		}.toString();
		return sql;
	}
	
}
