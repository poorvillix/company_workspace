package villix.shinninggroup.shopview;

import java.util.Timer;
import java.util.TimerTask;

import villix.shinninggroup.globaldata.GlobalData;
import villix.shinninggroup.seetheshop.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
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
		Bitmap oriBmp= BitmapFactory.decodeResource(getResources(), R.drawable.cover);
		int oriWidth = oriBmp.getWidth();
		int oriHeight = oriBmp.getHeight();
		
		DisplayMetrics metrics = new DisplayMetrics();  
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
		float scaleWidth = ((float) metrics.widthPixels) / oriWidth;
		float scaleHeight = ((float) metrics.heightPixels) / oriHeight;
		
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		
		Bitmap ResizedBmp = Bitmap.createBitmap(oriBmp, 0, 0, oriWidth, oriHeight, matrix, true);
		BitmapDrawable ResizeBmpDrawable = new BitmapDrawable(getResources(), ResizedBmp);
		//會這樣設定是因為似乎圖太大不會顯示,先縮放到差不多大小再說
		m_ShopCoverView.setBackground(ResizeBmpDrawable);
	    
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
