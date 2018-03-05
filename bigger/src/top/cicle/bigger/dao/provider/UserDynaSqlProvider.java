package top.cicle.bigger.dao.provider;

import static top.cicle.bigger.common.biggerConstants.*;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import top.cicle.bigger.domain.User;


public class UserDynaSqlProvider {

	public String selectWithParam(final Map<String,Object> params)
	{
		String sql=new SQL()
		{
			{
				SELECT("*");
				FROM(USERTABLE);
				if(params.get("user") !=null)
				{
					User user=(User) params.get("user");
					if(user.getName()!=null && !user.getName().equals(""))
					{
						WHERE(" name like CONCAT('%',#{user.name},'%') ");
					}
				}
			}
		}.toString();
		if(params.get("pageModel")!=null)
		{
			sql+="limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
		}
		return sql;
	}
	
	public String updateUser(final User user)
	{
		String sql= new SQL()
		{
			{
			   UPDATE(USERTABLE);
			   if(user.getName()!=null && !user.getName().equals(""))
			   {
				   SET("name=#{name}");
			   }
			   if(user.getEmail()!=null && !user.getEmail().equals(""))
			   {
				   SET("email=#{email}");
			   }
			   if(user.getTel()!=null && !user.getTel().equals(""))
			   {
				   SET("tel=#{tel}");
			   }
			   if(user.getInTime()!=null && !user.getInTime().equals(""))
			   {
				   SET("in_time=#{in_time}");
			   }
			   if(user.getProfile()!=null && !user.getProfile().equals(""))
			   {
				   SET("profile=#{profile}");
			   }
			   if(user.getPassword()!=null && !user.getPassword().equals(""))
			   {
				   SET("password=#{password}");
			   }
			   if(user.getPic_url()!=null && !user.getPic_url().equals(""))
			   {
				   SET("pic_url=#{pic_url}");
			   }
			   WHERE("id=#{id}");
			}
		}.toString();
		System.out.println(sql);
		return sql;
	}
	
	
}
