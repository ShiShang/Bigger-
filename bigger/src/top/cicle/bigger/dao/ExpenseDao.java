package top.cicle.bigger.dao;

import static top.cicle.bigger.common.biggerConstants.*;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import top.cicle.bigger.dao.provider.ExpenseDynaSqlProvider;
import top.cicle.bigger.domain.Expense;
import top.cicle.bigger.domain.Notice;

public interface ExpenseDao {

	@SelectProvider(type=ExpenseDynaSqlProvider.class,method="addExpense")
	public void addExpense(Expense expense);
	
	//获取所有报销数据
	@Select("select * from "+EXPENSETABLE+" order by create_time desc")
	public List<Expense> getAllExpenseData();
	
	//根据Id删除报销
	@Select("delete from "+EXPENSETABLE+" where id=#{id}")
	public Expense deleteExpenseById(Integer id);
	
}