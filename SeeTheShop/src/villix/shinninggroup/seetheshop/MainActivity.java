package villix.shinninggroup.seetheshop;

import villix.shinninggroup.globaldata.GlobalData;
import villix.shinninggroup.shopview.ShopFragChoice;
import android.os.Bundle;
import android.view.Menu;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity
{
	public static MainActivity MainActivityThis;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		MainActivityThis = this;
	    //requestWindowFeature(Window.FEATURE_NO_TITLE);   //¥þ¿Ã¹õ³]©w
	    //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	    //WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);

		ShopFragChoice.getInstance().onChangeMainFragment(GlobalData.NowMainFragState.enumShopCover);
		//onChangeMainFragment(GlobalData.NowFragState.enumShopList, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
