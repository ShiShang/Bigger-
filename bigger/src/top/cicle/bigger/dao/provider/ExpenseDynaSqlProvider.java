package top.cicle.bigger.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import top.cicle.bigger.domain.Expense;
import static top.cicle.bigger.common.biggerConstants.*;

public class ExpenseDynaSqlProvider {

	public String addExpense(final Expense expense)
	{
		String sql=new SQL()
		{
			{
				INSERT_INTO(EXPENSETABLE);
				if(expense !=null)
				{
					if(expense.getTitle()!=null && !expense.getTitle().equals(""))
					{
						VALUES("title","#{title}");
					}
					if(expense.getContent()!=null && !expense.getContent().equals(""))
					{
						VALUES("content","#{content}");
					}
					if(expense.getMoney()!=null && !expense.getMoney().equals(""))
					{
						VALUES("money","#{money}");
					}
					if(expense.getCreatetime()!=null && !expense.getCreatetime().equals(""))
					{
						VALUES("create_time","#{create_time}");
					}
					if(expense.getStatus()!=null && !expense.getStatus().equals(""))
					{
						VALUES("status","#{status}");
					}
					if(expense.getStatus()!=null && !expense.getStatus().equals(""))
					{
						VALUES("user_id","#{user.id}");
					}
				}
				
			}
		}.toString();
		
		return sql;
	}
	
}
