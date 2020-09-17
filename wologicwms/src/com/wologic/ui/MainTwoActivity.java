package com.wologic.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wologic.R;
import com.wologic.control.ImageFunction;
import com.wologic.dao.ItemDataDao;
import com.wologic.dao.RuKuDao;
import com.wologic.dao.VersionInfoDao;
import com.wologic.dao.WFunctionDao;
import com.wologic.domain.VersionInfo;
import com.wologic.domain.WFuction;
import com.wologic.domain.WorkItem;
import com.wologic.domainnew.Menu;
import com.wologic.domainnew.SubMenu;
import com.wologic.util.Common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

//主界面显示设置,table1存放显示的图标信息
public class MainTwoActivity extends Activity {

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == 97) {
			Intent intent = new Intent(MainTwoActivity.this,
					PickerActivity.class);
			startActivity(intent);
			return true;
		}
		if (keyCode == 64) {
			Intent intent = new Intent(MainTwoActivity.this,
					CancelPickerActivity.class);
			startActivity(intent);
			return true;
		}
		if (keyCode == 2) {
			Intent intent = new Intent(MainTwoActivity.this, ExecActivity.class);
			startActivity(intent);
			return true;
		}
		if (keyCode == 106) {
			Intent intent = new Intent(MainTwoActivity.this,
					PartnerPickerActivity.class);
			startActivity(intent);
			return true;
		}
		if (keyCode == 96) {
			finish();
			Intent intent = new Intent(MainTwoActivity.this,
					LoginActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private ImageView imgruku, imgmore, imgfahuo, imgchuku, imgpandian;

	private ListView lv;

	private TableLayout tl;

	private TextView tvtitle, tvversion, tvuser, tbPartener, tbCustomer,
			tbWare;

	private LinearLayout wologiccalculator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scanfragmentpage);
		tl = (TableLayout) findViewById(R.id.table1);
		tvtitle = (TextView) findViewById(R.id.tvtitle);
		tvversion = (TextView) findViewById(R.id.tvversion);
		tvuser = (TextView) findViewById(R.id.tvuser);
		tbPartener = (TextView) findViewById(R.id.tbPartener);

		tbWare = (TextView) findViewById(R.id.tbWare);
		tbCustomer = (TextView) findViewById(R.id.tbCustomer);

		tvtitle.setText("分拣系统");
		tvuser.setText(Common.RealName + "(" + Common.UserName + ")");
		tbPartener.setText(Common.partnerName + "(" + Common.partnerCode + ")");
		tbWare.setText(Common.WareHouseName);
		tbCustomer.setText(Common.CustomerName);
		//init();
		initDebugMenu();
		try {
			tvversion.setText("Version " + getVersionName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// getServerVersion();
	}

	/*
	 * 获取当前程序的版本号
	 */
	private String getVersionName() throws Exception {
		// 获取packagemanager的实例
		PackageManager packageManager = getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),
				0);
		return packInfo.versionName;
	}

	private void initDebugMenu() {

		// 获取显示的业务信息
		List<WFuction> list = new ArrayList<WFuction>();

		List<Menu> menuList = Common.menuDtos;
		List<SubMenu> subMenuList = null;
		if (menuList != null) {
			for (Menu menuItem : menuList) {
				if (menuItem.getMenuCode().equals("RE00061")) {
					subMenuList = menuItem.getSubMenus();
					break;
				}
			}
		}

		WFuction wone = new WFuction();
		wone.setFuctionid("fj");
		wone.setFuctionname("分拣(F2)");
		list.add(wone);

		WFuction wtwo = new WFuction();
		wtwo.setFuctionid("qxfj");
		wtwo.setFuctionname("解除装箱(F3)");
		list.add(wtwo);

		WFuction wBox = new WFuction();
		wBox.setFuctionid("pp");
		wBox.setFuctionname("包装装箱(F5)");
		list.add(wBox);

		WFuction wPartnerPre = new WFuction();
		wPartnerPre.setFuctionid("fjquery");
		wPartnerPre.setFuctionname("分拣进度(F4)");
		list.add(wPartnerPre);

		WFuction wgoods = new WFuction();
		wgoods.setFuctionid("goodbarcode");
		wgoods.setFuctionname("条码采集");
		list.add(wgoods);

		WFuction standgoodssorting = new WFuction();
		standgoodssorting.setFuctionid("standsort");
		standgoodssorting.setFuctionname("标品分拣");
		list.add(standgoodssorting);

		WFuction wAccept = new WFuction();
		wAccept.setFuctionid("accept");
		wAccept.setFuctionname("采购收货");
		list.add(wAccept);

		WFuction wInventory = new WFuction();
		wInventory.setFuctionid("inventory");
		wInventory.setFuctionname("盘点");
		list.add(wInventory);

		WFuction wPicker = new WFuction();
		wPicker.setFuctionid("picker");
		wPicker.setFuctionname("标品拣货");
		list.add(wPicker);

		WFuction wfreePanDian = new WFuction();
		wfreePanDian.setFuctionid("freeinventory");
		wfreePanDian.setFuctionname("自由盘点");
		list.add(wfreePanDian);

		WFuction wpartnersorting = new WFuction();
		wpartnersorting.setFuctionid("partnersorting");
		wpartnersorting.setFuctionname("标品供应商分拣");
		list.add(wpartnersorting);
		
		WFuction wsuitcaiji = new WFuction();
		wsuitcaiji.setFuctionid("wsuitcaiji");
		wsuitcaiji.setFuctionname("套装采集");
		list.add(wsuitcaiji);
		
		WFuction wsuitruku = new WFuction();
		wsuitruku.setFuctionid("wsuitruku");
		wsuitruku.setFuctionname("套装入库");
		list.add(wsuitruku);
		
		
		WFuction wsuitfenjian = new WFuction();
		wsuitfenjian.setFuctionid("wsuitfenjian");
		wsuitfenjian.setFuctionname("套装分拣");
		list.add(wsuitfenjian);
		
		WFuction wsuithuiku = new WFuction();
		wsuithuiku.setFuctionid("wsuithuiku");
		wsuithuiku.setFuctionname("套装回库");
		list.add(wsuithuiku);
		
		//出库打签
		WFuction outbiaoqian = new WFuction();
		outbiaoqian.setFuctionid("outsuitbiaoqian");
		outbiaoqian.setFuctionname("出库打签");
		list.add(outbiaoqian);
		
//		WFuction wsuittuigong = new WFuction();
//		wsuittuigong.setFuctionid("wsuittuigong");
//		wsuittuigong.setFuctionname("套装退供");
//		list.add(wsuittuigong);
//		
//		WFuction wsuitketui= new WFuction();
//		wsuitketui.setFuctionid("wsuitketui");
//		wsuitketui.setFuctionname("套装客退");
//		list.add(wsuitketui);
//		
		

		WFuction wexit = new WFuction();
		wexit.setFuctionid("exit");
		wexit.setFuctionname("重新登录(F1)");
		list.add(wexit);

		if (list != null && list.size() > 0) {

			if (list.size() < 3) {
				// 权限少于3个
				TableRow tableRow1 = new TableRow(MainTwoActivity.this);
				TableRow tableRow2 = new TableRow(MainTwoActivity.this);

				tl.addView(tableRow1);
				tl.addView(tableRow2);

				for (int i = 0; i < list.size(); i++) {
					ImageView img = new ImageView(MainTwoActivity.this);
					ImageFunction imgFunction = new ImageFunction(
							MainTwoActivity.this, list.get(i).getFuctionid());
					TextView textView = new TextView(MainTwoActivity.this);
					textView.setGravity(Gravity.CENTER);
					textView.setTextColor(Color.parseColor("#000000"));
					textView.setText(list.get(i).getFuctionname());

					tableRow1.addView(imgFunction);
					tableRow2.addView(textView);
				}
			} else {

				TableRow tableRow1 = null;
				TableRow tableRow2 = null;
				int m = 0;
				for (int i = 0; i < list.size(); i++) {
					if (i % 3 == 0) {

						tableRow1 = new TableRow(MainTwoActivity.this);
						tableRow2 = new TableRow(MainTwoActivity.this);
						tableRow2.setPadding(0, 0, 0, 35);
						tl.addView(tableRow1);
						tl.addView(tableRow2);

						ImageView img = new ImageView(MainTwoActivity.this);
						ImageFunction imgFunction = new ImageFunction(
								MainTwoActivity.this, list.get(i)
										.getFuctionid());
						TextView textView = new TextView(MainTwoActivity.this);
						textView.setGravity(Gravity.CENTER);
						textView.setTextColor(Color.parseColor("#000000"));
						textView.setText(list.get(i).getFuctionname());

						tableRow1.addView(imgFunction);
						tableRow2.addView(textView);
					} else {
						ImageView img = new ImageView(MainTwoActivity.this);
						ImageFunction imgFunction = new ImageFunction(
								MainTwoActivity.this, list.get(i)
										.getFuctionid());
						TextView textView = new TextView(MainTwoActivity.this);
						textView.setGravity(Gravity.CENTER);
						textView.setTextColor(Color.parseColor("#000000"));
						textView.setText(list.get(i).getFuctionname());

						tableRow1.addView(imgFunction);
						tableRow2.addView(textView);

					}

				}

			}
		}
	}

	private void init() {
		// 获取显示的业务信息
		List<WFuction> list = new ArrayList<WFuction>();

		List<Menu> menuList = Common.menuDtos;
		List<SubMenu> subMenuList = null;
		if (menuList != null) {
			for (Menu menuItem : menuList) {
				if (menuItem.getMenuCode().equals("RE00061")) {
					subMenuList = menuItem.getSubMenus();
					break;
				}
			}
		}

		if (subMenuList != null) {

			for (SubMenu item : subMenuList) {

				if (item.getSubMenuCode().equals("RE00062")) {
					WFuction wone = new WFuction();
					wone.setFuctionid("fj");
					wone.setFuctionname("分拣(F2)");
					list.add(wone);
				}
				if (item.getSubMenuCode().equals("RE00064")) {
					WFuction wtwo = new WFuction();
					wtwo.setFuctionid("qxfj");
					wtwo.setFuctionname("解除装箱(F3)");
					list.add(wtwo);
				}

				if (item.getSubMenuCode().equals("RE00063")) {
					WFuction wPartnerPre = new WFuction();
					wPartnerPre.setFuctionid("pp");
					wPartnerPre.setFuctionname("包装装箱(F5)");
					list.add(wPartnerPre);
				}

				if (item.getSubMenuCode().equals("RE00068")) {
					WFuction wPartnerPre = new WFuction();
					wPartnerPre.setFuctionid("fjquery");
					wPartnerPre.setFuctionname("分拣进度(F4)");
					list.add(wPartnerPre);
				}

				if (item.getSubMenuCode().equals("RE00093")) {
					WFuction wgoods = new WFuction();
					wgoods.setFuctionid("goodbarcode");
					wgoods.setFuctionname("条码采集");
					list.add(wgoods);
				}

				if (item.getSubMenuCode().equals("RE00092")) {
					WFuction standgoodssorting = new WFuction();
					standgoodssorting.setFuctionid("standsort");
					standgoodssorting.setFuctionname("标品分拣");
					list.add(standgoodssorting);
				}

				if (item.getSubMenuCode().equals("RE00136")) {
					WFuction wAccept = new WFuction();
					wAccept.setFuctionid("accept");
					wAccept.setFuctionname("采购收货");
					list.add(wAccept);
				}
				if (item.getSubMenuCode().equals("RE00137")) {
					WFuction wInventory = new WFuction();
					wInventory.setFuctionid("inventory");
					wInventory.setFuctionname("盘点");
					list.add(wInventory);
				}
				if (item.getSubMenuCode().equals("RE00138")) {
					WFuction wPicker = new WFuction();
					wPicker.setFuctionid("picker");
					wPicker.setFuctionname("标品拣货");
					list.add(wPicker);
				}

				if (item.getSubMenuCode().equals("RE00240")) {
					WFuction wPicker = new WFuction();
					wPicker.setFuctionid("freeinventory");
					wPicker.setFuctionname("自由盘点");
					list.add(wPicker);
				}
				
				if (item.getSubMenuCode().equals("RE00273")) {
					WFuction wpartnersorting = new WFuction();
					wpartnersorting.setFuctionid("partnersorting");
					wpartnersorting.setFuctionname("标品供应商分拣");
					list.add(wpartnersorting);
				}
				
//				套装
				if (item.getSubMenuCode().equals("RE00290"))
				{
					WFuction wsuitcaiji = new WFuction();
					wsuitcaiji.setFuctionid("wsuitcaiji");
					wsuitcaiji.setFuctionname("套装采集");
					list.add(wsuitcaiji);
				}
			
				if (item.getSubMenuCode().equals("RE00291"))
				{
					
					WFuction wsuitruku = new WFuction();
					wsuitruku.setFuctionid("wsuitruku");
					wsuitruku.setFuctionname("套装入库");
					list.add(wsuitruku);
				}
			
				if (item.getSubMenuCode().equals("RE00292"))
				{
					WFuction wsuitfenjian = new WFuction();
					wsuitfenjian.setFuctionid("wsuitfenjian");
					wsuitfenjian.setFuctionname("套装分拣");
					list.add(wsuitfenjian);
				}
				
				if (item.getSubMenuCode().equals("RE00293"))
				{
					WFuction wsuithuiku = new WFuction();
					wsuithuiku.setFuctionid("wsuithuiku");
					wsuithuiku.setFuctionname("套装回库");
					list.add(wsuithuiku);
				}
				
			
			}

		}

	

		WFuction wexit = new WFuction();
		wexit.setFuctionid("exit");
		wexit.setFuctionname("重新登录(F1)");
		list.add(wexit);

		if (list != null && list.size() > 0) {

			if (list.size() < 3) {
				// 权限少于3个
				TableRow tableRow1 = new TableRow(MainTwoActivity.this);
				TableRow tableRow2 = new TableRow(MainTwoActivity.this);

				tl.addView(tableRow1);
				tl.addView(tableRow2);

				for (int i = 0; i < list.size(); i++) {
					ImageView img = new ImageView(MainTwoActivity.this);
					ImageFunction imgFunction = new ImageFunction(
							MainTwoActivity.this, list.get(i).getFuctionid());
					TextView textView = new TextView(MainTwoActivity.this);
					textView.setGravity(Gravity.CENTER);
					textView.setTextColor(Color.parseColor("#000000"));
					textView.setText(list.get(i).getFuctionname());

					tableRow1.addView(imgFunction);
					tableRow2.addView(textView);
				}
			} else {

				TableRow tableRow1 = null;
				TableRow tableRow2 = null;
				int m = 0;
				for (int i = 0; i < list.size(); i++) {
					if (i % 3 == 0) {

						tableRow1 = new TableRow(MainTwoActivity.this);
						tableRow2 = new TableRow(MainTwoActivity.this);
						tableRow2.setPadding(0, 0, 0, 35);
						tl.addView(tableRow1);
						tl.addView(tableRow2);

						ImageView img = new ImageView(MainTwoActivity.this);
						ImageFunction imgFunction = new ImageFunction(
								MainTwoActivity.this, list.get(i)
										.getFuctionid());
						TextView textView = new TextView(MainTwoActivity.this);
						textView.setGravity(Gravity.CENTER);
						textView.setTextColor(Color.parseColor("#000000"));
						textView.setText(list.get(i).getFuctionname());

						tableRow1.addView(imgFunction);
						tableRow2.addView(textView);
					} else {
						ImageView img = new ImageView(MainTwoActivity.this);
						ImageFunction imgFunction = new ImageFunction(
								MainTwoActivity.this, list.get(i)
										.getFuctionid());
						TextView textView = new TextView(MainTwoActivity.this);
						textView.setGravity(Gravity.CENTER);
						textView.setTextColor(Color.parseColor("#000000"));
						textView.setText(list.get(i).getFuctionname());

						tableRow1.addView(imgFunction);
						tableRow2.addView(textView);

					}

				}

			}
		}

		// if (list != null && list.size() > 0) {
		//
		// if(list.size()==1)
		// {
		// TableRow tableRow1 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow2 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow1);
		// tl.addView(tableRow2);
		// for (int i = 0; i < 1; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		//
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow1.addView(imgFunction);
		// tableRow2.addView(textView);
		// }
		// }
		//
		// if(list.size()==2)
		// {
		// TableRow tableRow1 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow2 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow1);
		// tl.addView(tableRow2);
		// for (int i = 0; i < 2; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		//
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow1.addView(imgFunction);
		// tableRow2.addView(textView);
		// }
		// }
		//
		// if(list.size()==3)
		// {
		// TableRow tableRow1 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow2 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow1);
		// tl.addView(tableRow2);
		// for (int i = 0; i < 2; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		//
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow1.addView(imgFunction);
		// tableRow2.addView(textView);
		// }
		//
		// TextView tvPad = new TextView(MainTwoActivity.this);
		// tvPad.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.FILL_PARENT, 15, 1.0f));
		// tvPad.setGravity(Gravity.CENTER);
		// tvPad.setTextColor(Color.parseColor("#000000"));
		// tl.addView(tvPad);
		//
		// TableRow tableRow3 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow4 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow3);
		// tl.addView(tableRow4);
		// for (int i = 2; i < list.size(); i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		// img.setImageResource(R.drawable.pandian);
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		//
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow3.addView(imgFunction);
		// tableRow4.addView(textView);
		// }
		// }
		//
		// if(list.size()==4)
		// {
		// TableRow tableRow1 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow2 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow1);
		// tl.addView(tableRow2);
		// for (int i = 0; i < 2; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		//
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow1.addView(imgFunction);
		// tableRow2.addView(textView);
		// }
		//
		// TextView tvPad = new TextView(MainTwoActivity.this);
		// tvPad.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.FILL_PARENT, 15, 1.0f));
		// tvPad.setGravity(Gravity.CENTER);
		// tvPad.setTextColor(Color.parseColor("#000000"));
		// tl.addView(tvPad);
		//
		// TableRow tableRow3 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow4 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow3);
		// tl.addView(tableRow4);
		// for (int i = 2; i < list.size(); i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		// img.setImageResource(R.drawable.pandian);
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		//
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow3.addView(imgFunction);
		// tableRow4.addView(textView);
		// }
		// }
		//
		// if(list.size()==5||list.size()==6)
		// {
		// TableRow tableRow1 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow2 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow1);
		// tl.addView(tableRow2);
		// for (int i = 0; i < 2; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		//
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow1.addView(imgFunction);
		// tableRow2.addView(textView);
		// }
		//
		// TextView tvPad = new TextView(MainTwoActivity.this);
		// tvPad.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.FILL_PARENT, 15, 1.0f));
		// tvPad.setGravity(Gravity.CENTER);
		// tvPad.setTextColor(Color.parseColor("#000000"));
		// tl.addView(tvPad);
		//
		// TableRow tableRow3 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow4 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow3);
		// tl.addView(tableRow4);
		// for (int i = 2; i < 4; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		// img.setImageResource(R.drawable.pandian);
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		//
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow3.addView(imgFunction);
		// tableRow4.addView(textView);
		// }
		//
		//
		// TextView tvPad1 = new TextView(MainTwoActivity.this);
		// tvPad1.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.FILL_PARENT, 15, 1.0f));
		// tvPad1.setGravity(Gravity.CENTER);
		// tvPad1.setTextColor(Color.parseColor("#000000"));
		// tl.addView(tvPad1);
		//
		// TableRow tableRow5 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow6 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow5);
		// tl.addView(tableRow6);
		// for (int i = 4; i <list.size(); i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		// img.setImageResource(R.drawable.pandian);
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		//
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow5.addView(imgFunction);
		// tableRow6.addView(textView);
		// }
		//
		// }
		//
		// if(list.size()==7||list.size()==8)
		// {
		// TableRow tableRow1 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow2 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow1);
		// tl.addView(tableRow2);
		// for (int i = 0; i < 2; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		//
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow1.addView(imgFunction);
		// tableRow2.addView(textView);
		// }
		//
		// TextView tvPad = new TextView(MainTwoActivity.this);
		// tvPad.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.FILL_PARENT, 15, 1.0f));
		// tvPad.setGravity(Gravity.CENTER);
		// tvPad.setTextColor(Color.parseColor("#000000"));
		// tl.addView(tvPad);
		//
		// TableRow tableRow3 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow4 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow3);
		// tl.addView(tableRow4);
		// for (int i = 2; i < 4; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		// img.setImageResource(R.drawable.pandian);
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		//
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow3.addView(imgFunction);
		// tableRow4.addView(textView);
		// }
		//
		//
		// TextView tvPad1 = new TextView(MainTwoActivity.this);
		// tvPad1.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.FILL_PARENT, 15, 1.0f));
		// tvPad1.setGravity(Gravity.CENTER);
		// tvPad1.setTextColor(Color.parseColor("#000000"));
		// tl.addView(tvPad1);
		//
		// TableRow tableRow5 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow6 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow5);
		// tl.addView(tableRow6);
		// for (int i = 4; i <6; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		// img.setImageResource(R.drawable.pandian);
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		//
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow5.addView(imgFunction);
		// tableRow6.addView(textView);
		// }
		//
		//
		// TextView tvPad2 = new TextView(MainTwoActivity.this);
		// tvPad2.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.FILL_PARENT, 15, 1.0f));
		// tvPad2.setGravity(Gravity.CENTER);
		// tvPad2.setTextColor(Color.parseColor("#000000"));
		// tl.addView(tvPad2);
		//
		// TableRow tableRow7 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow8 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow7);
		// tl.addView(tableRow8);
		// for (int i = 6; i <list.size(); i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		// img.setImageResource(R.drawable.pandian);
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		//
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow7.addView(imgFunction);
		// tableRow8.addView(textView);
		// }
		//
		//
		// }
		//
		//
		// if(list.size()==9||list.size()==10)
		// {
		// TableRow tableRow1 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow2 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow1);
		// tl.addView(tableRow2);
		// for (int i = 0; i < 2; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		//
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow1.addView(imgFunction);
		// tableRow2.addView(textView);
		// }
		//
		// TextView tvPad = new TextView(MainTwoActivity.this);
		// tvPad.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.FILL_PARENT, 15, 1.0f));
		// tvPad.setGravity(Gravity.CENTER);
		// tvPad.setTextColor(Color.parseColor("#000000"));
		// tl.addView(tvPad);
		//
		// TableRow tableRow3 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow4 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow3);
		// tl.addView(tableRow4);
		// for (int i = 2; i < 4; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		// img.setImageResource(R.drawable.pandian);
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		//
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow3.addView(imgFunction);
		// tableRow4.addView(textView);
		// }
		//
		//
		// TextView tvPad1 = new TextView(MainTwoActivity.this);
		// tvPad1.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.FILL_PARENT, 15, 1.0f));
		// tvPad1.setGravity(Gravity.CENTER);
		// tvPad1.setTextColor(Color.parseColor("#000000"));
		// tl.addView(tvPad1);
		//
		// TableRow tableRow5 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow6 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow5);
		// tl.addView(tableRow6);
		// for (int i = 4; i <6; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		// img.setImageResource(R.drawable.pandian);
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		//
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow5.addView(imgFunction);
		// tableRow6.addView(textView);
		// }
		//
		//
		// TextView tvPad2 = new TextView(MainTwoActivity.this);
		// tvPad2.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.FILL_PARENT, 15, 1.0f));
		// tvPad2.setGravity(Gravity.CENTER);
		// tvPad2.setTextColor(Color.parseColor("#000000"));
		// tl.addView(tvPad2);
		//
		// TableRow tableRow7 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow8 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow7);
		// tl.addView(tableRow8);
		// for (int i = 6; i <8; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		// img.setImageResource(R.drawable.pandian);
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		//
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow7.addView(imgFunction);
		// tableRow8.addView(textView);
		// }
		//
		//
		//
		//
		// TextView tvPad3 = new TextView(MainTwoActivity.this);
		// tvPad3.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.FILL_PARENT, 15, 1.0f));
		// tvPad3.setGravity(Gravity.CENTER);
		// tvPad3.setTextColor(Color.parseColor("#000000"));
		// tl.addView(tvPad3);
		//
		// TableRow tableRow9 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow10 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow9);
		// tl.addView(tableRow10);
		// for (int i = 8; i <list.size(); i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		// img.setImageResource(R.drawable.pandian);
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		//
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow9.addView(imgFunction);
		// tableRow10.addView(textView);
		// }
		// }
		//
		// if(list.size()==11||list.size()==12)
		// {
		// TableRow tableRow1 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow2 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow1);
		// tl.addView(tableRow2);
		// for (int i = 0; i < 2; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		//
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow1.addView(imgFunction);
		// tableRow2.addView(textView);
		// }
		//
		// TextView tvPad = new TextView(MainTwoActivity.this);
		// tvPad.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.FILL_PARENT, 15, 1.0f));
		// tvPad.setGravity(Gravity.CENTER);
		// tvPad.setTextColor(Color.parseColor("#000000"));
		// tl.addView(tvPad);
		//
		// TableRow tableRow3 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow4 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow3);
		// tl.addView(tableRow4);
		// for (int i = 2; i < 4; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		// img.setImageResource(R.drawable.pandian);
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		//
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow3.addView(imgFunction);
		// tableRow4.addView(textView);
		// }
		//
		//
		// TextView tvPad1 = new TextView(MainTwoActivity.this);
		// tvPad1.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.FILL_PARENT, 15, 1.0f));
		// tvPad1.setGravity(Gravity.CENTER);
		// tvPad1.setTextColor(Color.parseColor("#000000"));
		// tl.addView(tvPad1);
		//
		// TableRow tableRow5 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow6 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow5);
		// tl.addView(tableRow6);
		// for (int i = 4; i <6; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		// img.setImageResource(R.drawable.pandian);
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		//
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow5.addView(imgFunction);
		// tableRow6.addView(textView);
		// }
		//
		//
		// TextView tvPad2 = new TextView(MainTwoActivity.this);
		// tvPad2.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.FILL_PARENT, 15, 1.0f));
		// tvPad2.setGravity(Gravity.CENTER);
		// tvPad2.setTextColor(Color.parseColor("#000000"));
		// tl.addView(tvPad2);
		//
		// TableRow tableRow7 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow8 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow7);
		// tl.addView(tableRow8);
		// for (int i = 6; i <8; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		// img.setImageResource(R.drawable.pandian);
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		//
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow7.addView(imgFunction);
		// tableRow8.addView(textView);
		// }
		//
		//
		//
		//
		// TextView tvPad3 = new TextView(MainTwoActivity.this);
		// tvPad3.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.FILL_PARENT, 15, 1.0f));
		// tvPad3.setGravity(Gravity.CENTER);
		// tvPad3.setTextColor(Color.parseColor("#000000"));
		// tl.addView(tvPad3);
		//
		// TableRow tableRow9 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow10 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow9);
		// tl.addView(tableRow10);
		// for (int i = 8; i <10; i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		// img.setImageResource(R.drawable.pandian);
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		//
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow9.addView(imgFunction);
		// tableRow10.addView(textView);
		// }
		//
		// TableRow tableRow11 = new TableRow(MainTwoActivity.this);
		// TableRow tableRow12 = new TableRow(MainTwoActivity.this);
		// tl.addView(tableRow11);
		// tl.addView(tableRow12);
		// for (int i = 10; i <list.size(); i++) {
		// ImageView img = new ImageView(MainTwoActivity.this);
		// img.setImageResource(R.drawable.geduo);
		// ImageFunction imgFunction = new
		// ImageFunction(MainTwoActivity.this,list.get(i).getFuctionid());
		//
		// TextView textView = new TextView(MainTwoActivity.this);
		// textView.setGravity(Gravity.CENTER);
		// textView.setTextColor(Color.parseColor("#000000"));
		// textView.setText(list.get(i).getFuctionname());
		//
		// tableRow11.addView(imgFunction);
		// tableRow12.addView(textView);
		// }
		// }
		//
		//
		// }
	}

}
