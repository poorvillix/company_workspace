package villix.shinninggroup.shopview;

import java.util.Timer;
import java.util.TimerTask;

import villix.shinninggroup.globaldata.GlobalData;
import villix.shinninggroup.seetheshop.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShopCover extends Fragment
{
	View m_ShopCoverView = null;
	Timer m_timer = null;
	private TimerTask m_task = null;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		m_ShopCoverView = inflater.inflate(R.layout.shop_cover, container,false);
		if(m_timer == null)
			m_timer = new Timer();
		if(m_task == null)
			m_task = new TimerTask()
			{
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Message message = new Message();
					handler.sendMessage(message);
				}
			};
		m_timer.schedule(m_task,3000);
		System.out.println("123");
		return m_ShopCoverView;
	}
	
	
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		DestroyTimer();
		ShopFragChoice.getInstance().onChangeMainFragment(GlobalData.NowMainFragState.enumShopList);
	}

	private Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			System.out.println("456");
			ShopFragChoice.getInstance().onChangeMainFragment(GlobalData.NowMainFragState.enumShopList);
			DestroyTimer();
		}
	};
	

	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		DestroyTimer();
		
		super.onDestroy();
	}

	private void DestroyTimer()
	{
		if(m_timer != null)
		{
			m_timer.cancel();
			m_timer = null;
			
			m_task.cancel();
			m_task = null;
		}
	}
}
