package villix.seetheshop.shopview;

import villix.seetheshop.seetheshop.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ShopPic extends Fragment
{
	ImageView mShopImage;
	String m_szPicName;
	int m_nChoice;
	public ShopPic(String szPicName, int nChoice)
	{
		m_szPicName = szPicName;
		m_nChoice = nChoice;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		View ShopPicView = inflater.inflate(R.layout.shop_pic, container,false);
		int nPicID = getResources().getIdentifier(m_szPicName, "drawable", "com.example.seetheshop");
		if(nPicID != 0)
		{
			mShopImage = (ImageView)ShopPicView.findViewById(R.id.shopImageView);
			mShopImage.setImageResource(nPicID);
			mShopImage.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					//ShopMain fragMain = ((ShopMain)getActivity().getSupportFragmentManager().findFragmentByTag(GlobalData.NowMainFragState.enumShopList.toString()));
					//fragMain.onChangeShopListFragment(NowShopListFragState.enumShopWeb, m_nChoice);
				}
			});
		}
		return ShopPicView;
	}
	
}
