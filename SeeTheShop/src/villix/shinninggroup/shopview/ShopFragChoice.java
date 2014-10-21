package villix.shinninggroup.shopview;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import villix.shinninggroup.globaldata.GlobalData;
import villix.shinninggroup.seetheshop.CSVFileReader;
import villix.shinninggroup.seetheshop.MainActivity;
import villix.shinninggroup.seetheshop.R;

public class ShopFragChoice {
	private static ShopFragChoice instance = null;
	
	// 商店狀態名稱
	enum ShopListType
	{
		enumShopListName,
		enumShopListHeadPic,
		enumShopListDesc,
		enumDescChoice,
	}
	private static ArrayList<HashMap<String,Object>> mShopList = new ArrayList<HashMap<String,Object>>();
	private static List<String[]> mShopListDesc = new ArrayList<String[]>();

	private ShopFragChoice()
	{
	}
	public static ShopFragChoice getInstance()
	{
		if ( instance == null )
		{
			instance = new ShopFragChoice();
			
			getCSVFile();
			
			getDescCSVFile();
		}
		
		return instance;
	}
	
	private static void getCSVFile()
	{
		Context MainContext = MainActivity.MainActivityThis.getApplicationContext();
		
		
		InputStream inputStream = MainContext.getResources().openRawResource(R.raw.allshoplist);
		CSVFileReader csvFile = new CSVFileReader(inputStream);
		List<String[]> shopList = null;
		
		try
		{
			shopList = csvFile.read();
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		if(shopList == null)
			return;
		
		//把資料加入ArrayList中
		for(int nLinePos=0;nLinePos<shopList.size(); nLinePos++)
		{
			HashMap<String,Object> item = new HashMap<String,Object>();
			// 名字
			item.put("Name", shopList.get(nLinePos)[ShopListType.enumShopListName.ordinal()]);
			// 頭像圖片
			String uri = "@drawable/" + shopList.get(nLinePos)[ShopListType.enumShopListHeadPic.ordinal()];
			int imageResource = MainContext.getResources().getIdentifier(uri, null, MainContext.getPackageName());
			item.put("HeadPic", imageResource );
			// 地址
			item.put("Desc", shopList.get(nLinePos)[ShopListType.enumShopListDesc.ordinal()] );
			// 選擇呈現方式
			item.put("DescChoice", shopList.get(nLinePos)[ShopListType.enumDescChoice.ordinal()] );
			// 加入List中
			mShopList.add(item);
		}
	}
	
	private static void getDescCSVFile()
	{
		
		Context MainContext = MainActivity.MainActivityThis.getApplicationContext();
		
		InputStream inputStream = MainContext.getResources().openRawResource(R.raw.allshoplistdesc);
		CSVFileReader csvFile = new CSVFileReader(inputStream);
		List<String[]> shopList = null;
		try
		{
			shopList = csvFile.read();
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(shopList == null)
			return;
		
		mShopListDesc = shopList;
		System.out.println(mShopListDesc);
	}
	
	public void onChangeMainFragment(GlobalData.NowMainFragState nowState)
	{
		FragmentTransaction fragmentTransaction = MainActivity.MainActivityThis.getSupportFragmentManager().beginTransaction();
		
		Fragment nowFrag = null;
		switch(nowState)
		{
		case enumShopCover:
			nowFrag = new ShopCover();
			fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out);
			break;
		case enumShopList:
			nowFrag = new ShopList();
			fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out);
			break;
		default:
			break;
		}
		if(nowFrag == null)
			return;
		fragmentTransaction.replace(R.id.mainFrameLayout, nowFrag);
		
		fragmentTransaction.commit();
	}
	
	public void onChangeFragment(GlobalData.NowDescTypeState nowState, int nChoice)
	{
		FragmentTransaction fragmentTransaction = MainActivity.MainActivityThis.getSupportFragmentManager().beginTransaction();
		
		Fragment nowFrag = null;
		switch(nowState)
		{
		case enumShopWeb:
		case enumShopDescription:
			nowFrag = new ShopDesc(nowState, nChoice);
			fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out);
			break;
		default:
			break;
		}
		if(nowFrag == null)
			return;
		fragmentTransaction.replace(R.id.mainFrameLayout, nowFrag);
		
		fragmentTransaction.addToBackStack(null);
		
		fragmentTransaction.commit();
	
	}
	public ArrayList<HashMap<String,Object>> getShopList()
	{
		return mShopList;
	}
	public HashMap<String,Object> getShopListPos(int nPos)
	{
		return mShopList.get(nPos);
	}
	public GlobalData.NowDescTypeState getShopListPosDescChoice(int nPos)
	{
		return GlobalData.NowDescTypeState.getNowFragStateByString((String) mShopList.get(nPos).get("DescChoice"));
	}

	public String[] getShopListDescPos(int nPos)
	{
		return mShopListDesc.get(nPos);
	}
	
}