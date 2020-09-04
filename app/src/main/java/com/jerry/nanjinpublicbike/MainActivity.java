package com.jerry.nanjinpublicbike;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.jerry.nanjinpublicbike.utils.Utils;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		test();
	}


	public static void decodeLatlng(String lat,String lng,String key)
	{
		byte[]lats= Base64.decode(lat,Base64.DEFAULT);
		byte[]lngs=Base64.decode(lng,Base64.DEFAULT);

		String latStr=new String(Utils.decryptMode(key,lats));
		String lngStr=new String(Utils.decryptMode(key,lngs));
		//System.out.println("---"+latStr+"---"+lngStr);
		Log.d("xuxu","---"+latStr+"---"+lngStr);
	}


	String [][] strs={{"0G8Iz2F3Z8tiPh4N1kBwFA==","fWb+iW+Ydlm7QOLXTDyvfQ==","013031"}};
	public void test()
	{
		for (int i = 0; i < strs.length; i++)
		{
			decodeLatlng(strs[i][0],strs[i][1],strs[i][2]);
		}
	}
}