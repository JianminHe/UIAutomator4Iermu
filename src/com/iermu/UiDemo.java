package com.iermu;

import java.io.File;

import android.graphics.Rect;
import android.view.Display;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;

public class UiDemo extends UiAutomatorTestCase {

	public void testDevice() throws UiObjectNotFoundException {
		
		
		

		try {

			UiDevice.getInstance().wakeUp();
			
			System.out.println("testDevice1");

		} catch (Exception ex) {

			System.out.println(ex.toString());
		}

		// // getUiDevice().pressBack();
		// // getUiDevice().click(130, 1645);
		//
		// // getUiDevice().drag(130, 1645, 1080, 1657, 3);
		//
		// // UiDevice.getInstance().click(130, 1645);
		//
		//
		// for(int j =0; j<15; j++){
		//
		//
		// sleep(300);
		// getUiDevice().takeScreenshot(new File("/mnt/sdcard/Screenshot" +j
		// +".png") , 0, 10);
		//
		// Step s = new Step();
		//
		// s.loadPng("/mnt/sdcard/Screenshot" +j +".png");
		//
		// System.out.println(s.height);
		// System.out.println(s.width);
		//
		//
		// sleep(10);
		// s.calcStep();
		//
		// System.out.println("Start sleep");
		//
		// // sleep(10);
		// //
		// // UiDevice.getInstance().click(130, 1645);
		// //
		// // UiDevice.getInstance().swipe(130, 130, 1645, 1645, 10 * 10);
		//
		// for(int i =0; i<4; i++)
		// {
		// System.out.println("Click time = " + i);
		//
		// System.out.println("Step =" +s.step[i]);
		// switch(s.step[i])
		// {
		// case 0:
		// UiDevice.getInstance().click(130, 1340);
		// break;
		// case 1:
		// UiDevice.getInstance().click(400, 1340);
		// break;
		// case 2:
		// UiDevice.getInstance().click(680, 1340);
		// break;
		// case 3:
		// UiDevice.getInstance().click(940, 1340);
		// break;
		//
		// }
		// // sleep(2);
		//
		// }
		// }

		/*
		 * 通过press home,然后查看 爱耳目应用的方式启动程序并不好。替换成使用 adb shell am start -n
		 * com.cms.iermu/startActivity 修改UiAutomatorHelper.java文件，增加启动应用项
		 * UiDevice.getInstance().pressHome();
		 * 
		 * UiObject settingApp = new UiObject(new UiSelector().text("爱耳目"));
		 * settingApp.click();
		 */

		// 判断是否在摄像机视频播放界面，如果是，则返回我的摄像机列表 使用adb shell am start -n 方式启动应用，不需要再判断
		
		
		
		UiObject activityPage = new UiObject(new UiSelector().text("按住对讲"));

		if (activityPage.exists()) {

			UiObject backButton = new UiObject(
					new UiSelector().resourceId("com.cms.iermu:id/back"));

			backButton.click();
		}

		while (true) {
			// 点击播放我的第一台摄像机

			// 第一个地方判断当前页面是否在我的耳目

			UiObject okButton = new UiObject(
					new UiSelector().resourceId("android:id/button1"));

			if (okButton.exists()) {
				System.out.println("1. Find OK button");
				okButton.click();
				System.out.println("1. click OK button");
			}
			

			UiObject myErmu = new UiObject(new UiSelector().text("回看录像"));

			if (myErmu.exists()) {

				UiObject camera = new UiObject(
						new UiSelector().resourceId("com.cms.iermu:id/image"));

				if (camera.exists()) {

					// 第二个地方判断是否弹出云录制已经过期对话框
					UiObject okButton1 = new UiObject(
							new UiSelector().resourceId("android:id/button1"));

					if (okButton1.exists()) {
						System.out.println("2. Find OK button");
						okButton.click();
						System.out.println("2. click OK button");
					}

					System.out.print("go to camera view");
					camera.click();
				}
			}

			// 判断如果没有进入播放页面，则进入下次循环
			UiObject talk1 = new UiObject(
					new UiSelector().resourceId("com.cms.iermu:id/talk_dvr"));

			if (!talk1.exists())
				continue;

			try {
				Thread.sleep(5000);
			} catch (Exception ex) {
				System.out.println("ddd");
			}

			for (int j = 0; j < 100; j++) {

				UiObject talk = new UiObject(
						new UiSelector()
								.resourceId("com.cms.iermu:id/talk_dvr"));

				if (talk.isEnabled()) {

					// 获取对讲按钮的中心位置；
					Rect rect = talk.getVisibleBounds();

					int x = rect.centerX();
					int y = rect.centerY();
					int time = (int) (1 + Math.random() * (10 - 1 + 1));

					System.out.println("Start ......");

					// 使用一个比较笨的办法，采用移动方法来实现长按。大概100=1秒
					UiDevice.getInstance().swipe(x, y, x, y, time * 10);

					System.out.println("x =" + x);
					System.out.println("y =" + y);

					System.out.println("End ......");

					break;
				}

				// 等待0.1秒
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {

					e1.printStackTrace();
				}

			}

			UiObject backButton = new UiObject(
					new UiSelector().resourceId("com.cms.iermu:id/back"));

			if (backButton.exists())
				backButton.click();

		}

	}

	public static void main(String[] args) {

		new UiAutomatorHelper("Demo", "com.iermu.UiDemo", "testDevice", "3",
				"com.cms.iermu/com.iermu.ui.activity.SplashActivity");

		// new UiAutomatorHelper("Demo", "com.iermu.UiDemo", "testDevice", "3",
		// "");
	}

}
