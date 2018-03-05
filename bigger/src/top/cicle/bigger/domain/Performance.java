package top.cicle.bigger.domain;

import java.io.Serializable;

public class Performance implements Serializable {

	private Integer id;
	private House house;
	private String year;
	private String month;
	private String cost;
	private String fee;
	private String water_fee;
	private String elec_fee;
	private String clean_fee;
	private String days;
	private String live_days;
	private String income;
	private String profit;
	private Integer house_id;
	
	public Performance()
	{
		super();
	}
	
    public Integer getId()
    {
         return id;
    }
   public void setId(Integer  id)
    {
          this.id=id;
    }

   public House getHouse()
    {
         return house;
    }
   public void setHouse(House  house)
    {
          this.house=house;
    }

   public String getMonth()
    {
         return month;
    }
   public void setMonth(String  month)
    {
          this.month=month;
    }

   public String getCost()
    {
         return cost;
    }
   public void setCost(String cost)
    {
          this.cost=cost;
    }

   public String getFee()
    {
         return fee;
    }
   public void setFee(String  fee)
    {
          this.fee=fee;
    }

   public String getYear()
   {
        return year;
   }
  public void setYear(String  year)
   {
         this.year=year;
   }

  public String getWater_fee()
   {
        return water_fee;
   }
  public void setWater_fee(String  water_fee)
   {
         this.water_fee=water_fee;
   }

  public String getElec_fee()
   {
        return elec_fee;
   }
  public void setElec_fee(String  elec_fee)
   {
         this.elec_fee=elec_fee;
   }

  public String getClean_fee()
   {
        return clean_fee;
   }
  public void setClean_fee(String  clean_fee)
   {
         this.clean_fee=clean_fee;
   }

   public String getDays()
    {
         return days;
    }
   public void setDays(String  days)
    {
          this.days=days;
    }

   public String getLive_days()
    {
         return live_days;
    }
   public void setLive_days(String  live_days)
    {
          this.live_days=live_days;
    }

   public String getIncome()
    {
         return income;
    }
   public void setIncome(String  income)
    {
          this.income=income;
    }

   public String getProfit()
    {
         return profit;
    }
   public void setProfit(String  profit)
    {
          this.profit=profit;
    }
   public Integer getHouse_id()
   {
        return house_id;
   }
  public void setHouse_id(Integer  house_id)
   {
         this.house_id=house_id;
   }

}
