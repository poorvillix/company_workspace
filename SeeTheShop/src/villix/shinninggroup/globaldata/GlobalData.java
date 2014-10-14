package villix.shinninggroup.globaldata;

public class GlobalData
{
	public static enum NowMainFragState
	{
		enumShopCover,
		enumShopList,
	}
	
	public static enum NowDescTypeState
	{
		enumShopWeb,
		enumShopDescription;
		
		public static NowDescTypeState getNowFragStateByString(String szPosName)
		{
			if(szPosName.equals("0"))
				return enumShopWeb;
			else if(szPosName.equals("1"))
				return enumShopDescription;
			return enumShopWeb;
		}
	}
}
