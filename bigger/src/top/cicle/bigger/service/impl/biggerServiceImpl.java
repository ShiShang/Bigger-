package top.cicle.bigger.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import top.cicle.bigger.dao.AttachmentDao;
import top.cicle.bigger.dao.BonusDao;
import top.cicle.bigger.dao.ExpenseDao;
import top.cicle.bigger.dao.HouseDao;
import top.cicle.bigger.dao.NoticeDao;
import top.cicle.bigger.dao.PerformanceDao;
import top.cicle.bigger.dao.Performance_detailDao;
import top.cicle.bigger.dao.UserDao;
import top.cicle.bigger.domain.Attachment;
import top.cicle.bigger.domain.Bonus;
import top.cicle.bigger.domain.Expense;
import top.cicle.bigger.domain.House;
import top.cicle.bigger.domain.Notice;
import top.cicle.bigger.domain.Performance;
import top.cicle.bigger.domain.Performance_detail;
import top.cicle.bigger.domain.User;
import top.cicle.bigger.service.biggerService;


@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("biggerService")
public class biggerServiceImpl implements biggerService
{
	
    @Autowired
    private UserDao userDao;
    @Autowired
    private ExpenseDao expenseDao;
    @Autowired
    private BonusDao bonusDao;
    @Autowired
    private HouseDao houseDao;
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private PerformanceDao performanceDao;
    @Autowired
    private Performance_detailDao performance_detailDao;
    @Autowired
    private AttachmentDao attachmentDao;
    
 

    @Transactional(readOnly=true)
	@Cacheable(value="myUser") //���û���
    public User login(String name, String password) {
		return userDao.selectByNameAndPassword(name, password);  //登陆判断
	}


	public User findUserById(Integer id) {
		return userDao.selectById(id);       //根据ID查找User
	}

	public void updateUser(User user) {
		userDao.updateUser(user);           //修改User
	}
   
    public Integer User_count()
    {
	   return userDao.countUser();  //User 总数
    }
	
    public List<User> selectUserwithName(Map<String,Object > params)
    {
    	return userDao.selectUserByPage(params);   //根据姓名查找User
    }
    
    
    
	//Expense
	public void addExpense(Expense expense) {
		expenseDao.addExpense(expense);           //添加报销数据
	}
	
	@Cacheable(value="getAllExpenseData")
	public List<Expense> getAllExpenseData()
	{
		return expenseDao.getAllExpenseData();
	}
	public void deleteExpenseById(Integer id)
	{
		expenseDao.deleteExpenseById(id);
	}
	
	
	//Bonus
	public void addBonus(Bonus bonus){
		bonusDao.addBonus(bonus);          //添加分红数据
	}
	
	@Cacheable(value="getAllBonusData")
	public List<Bonus> getAllBonusData()
	{
		return bonusDao.getAllBonusData();
	}
	public void deleteBonusById(Integer id)
	{
		bonusDao.deleteBonusById(id);
	}
	
 
	//House
	public void addHouse(House house) {
		houseDao.addHouse(house);            //添加房源数据
		
	}

	public House findHouseById(Integer id) {
		return houseDao.selectHouseById(id);
	}
	
	public House findHouseByName(String name)
	{
		return houseDao.selectHouseByName(name);
	}
	
	@Cacheable(value="getAllHouseData")
	public List<House> getAllHouse()
	{
		return houseDao.getAllHouse();
	}
	
	//Notice
    public void addNotice(Notice notice)
    {
    	noticeDao.addNotice(notice);
    }
    
	@Cacheable(value="getAllNoticeData")
    public List<Notice> getAllNoticeData()
	{
		return noticeDao.getAllNoticeData();
	}
	public Notice getNoticeById(Integer id) {
		return noticeDao.getNoticeById(id);
	}
	public void deleteNoticeById(Integer id)
	{
		noticeDao.deleteNoticeById(id);
	}
    
    
    //Performance
    public void addPerformance(Performance pf)
    {
    	performanceDao.addPerformance(pf);
    }
	public List<Performance> findPerformanceByIdAndDate(
			Map<String, Object> params) {
		
		return performanceDao.findPerformanceByIdAndDate(params);
	}
	public void deletePerformanceByIdAndDate(
			Map<String, Object> params) {
		
		performanceDao.deletePerformanceByIdAndDate(params);
	}
	
	@Cacheable(value="getAllPerformanceData")
	public List<Performance> getAllPerformanceData() {
		return performanceDao.getAllPerformance();
	}
	public Integer getMonthProfitByDate(Map<String, Object> params) {
		return performanceDao.getMonthProfitByDate(params);
	}
	
    //Performance_detail
	public void addPerformance_detail(Performance_detail performance_detail) {
		performance_detailDao.addPerormance_detail(performance_detail);
		
	}
	public List<Performance_detail> findPerformance_detailByIdAndDate(
			Map<String, Object> params) {
		
		return performance_detailDao.findPerformance_detailByIdAndDate(params);
	}
	public void deletePerformance_detailByIdAndDate(
			Map<String, Object> params) {
		
		performance_detailDao.deletePerformance_detailByIdAndDate(params);
	}
	
	@Cacheable(value="getAllOrderByDate")
	public Integer getAllOrderByDate(Map<String,Object> params)
	{
		return performance_detailDao.getAllOrderByDate(params);
	}
	
   //Attachment
	@Cacheable(value="getAllAttachmentData")
	public List<Attachment> selectAttachent(Map<String,Object> params)
	{
		return attachmentDao.selectAttachment(params);
	}
    public void addAttachment(Attachment attachment)
    {
    	attachmentDao.addAttachment(attachment);
    }
    public Attachment selectAttachmentById(Integer id)
    {
    	return attachmentDao.selectAttachmentById(id);
    }
    public List<Attachment> selectAllAttachment()
    {
    	return attachmentDao.selectAllAttachment();
    }
    public  void delectAttById(Integer id)
    {
    	attachmentDao.delectAttById(id);
    }
	
}
