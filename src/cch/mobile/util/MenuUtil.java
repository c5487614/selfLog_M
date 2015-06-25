package cch.mobile.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import cch.selflog.R;
import cch.selflog.menu.MenuFragment;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MenuUtil {
	public static void attachMenu(FragmentActivity a,int resId, Fragment content){
		SlidingMenu menu = new SlidingMenu(a);
        menu.setMode(SlidingMenu.RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
//        menu.setShadowDrawable(android.R.drawable.s);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.4f);
        menu.attachToActivity(a, SlidingMenu.SLIDING_CONTENT, true);

        menu.setMenu(R.layout.menu_frame);
        
        a.getSupportFragmentManager()
		.beginTransaction().replace(R.id.menu_frame, new MenuFragment())
		.commit();
	}
}
