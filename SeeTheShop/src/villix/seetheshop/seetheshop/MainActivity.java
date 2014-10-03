package villix.seetheshop.seetheshop;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import villix.seetheshop.globaldata.GlobalData;
import villix.seetheshop.shopview.ShopList;
import villix.seetheshop.shopview.ShopList.ShopListListener;
import villix.seetheshop.shopview.ShopWeb;

public class MainActivity extends FragmentActivity implements ShopListListener
{
	private ArrayList<HashMap<String,Object>> mShopList = new ArrayList<HashMap<String,Object>>();
	
	// 商店狀態名稱
	enum ShopListType
	{
		enumShopListName,
		enumShopListHeadPic,
		enumShopListAddress,
		enumWebAddress,
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		InputStream inputStream = getResources().openRawResource(R.raw.allshoplist);
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
			item.put("Name", shopList.get(nLinePos)[ShopListType.enumShopListName.ordinal()]);
			item.put("HeadPic", shopList.get(nLinePos)[ShopListType.enumShopListHeadPic.ordinal()]);
			item.put("Address", shopList.get(nLinePos)[ShopListType.enumShopListAddress.ordinal()] );
			item.put("WebAddress", shopList.get(nLinePos)[ShopListType.enumWebAddress.ordinal()] );
			
			mShopList.add(item);
		}
		
		onChangeMainFragment(GlobalData.NowFragState.enumShopList, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onChangeMainFragment(GlobalData.NowFragState nowState, int nChoice)
	{
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		
		Fragment nowFrag = null;
		switch(nowState)
		{
		case enumShopList:
			nowFrag = new ShopList(mShopList);
			break;
		case enumShopWeb:
			nowFrag = new ShopWeb(mShopList.get(nChoice).get("WebAddress").toString());
			fragmentTransaction.setCustomAnimations(R.anim.push_left_in,R.anim.push_left_out,R.anim.push_left_in,R.anim.push_left_out);
			break;
		default:
			break;
		}
		if(nowFrag == null)
			return;
		fragmentTransaction.replace(R.id.mainFrameLayout, nowFrag);
		// 一開始不要用
		if(nowState != GlobalData.NowFragState.enumShopList)
			fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}
}
