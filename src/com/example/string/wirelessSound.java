package com.example.string;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class wirelessSound extends Activity{
	private final String PREFERENCE_NAME = "VirtualGuitarscaleLibrary";
	private final String INSTRUMENT_KEY = "Instrument";

	protected static final String DEBUG_TAG = "wireless";

	// protected static final int INSTRUMENT_TYPE_ACOUTSTIC = 0;
	// protected static final int INSTRUMENT_TYPE_ELECTRIC = 1;

	protected static final int INVALID_POINTER_ID = -1;
	protected static final int STRING6 = 0;
	protected static final int STRING5 = 1;
	protected static final int STRING4 = 2;
	protected static final int STRING3 = 3;
	protected static final int STRING2 = 4;
	protected static final int STRING1 = 5;

	protected LinearLayout[] mStringLinear = { null, null, null, null, null,
			null };
	protected ImageView[] mStringImage = { null, null, null, null, null, null };

	// protected int[] stringWidth = { 0, 0, 0, 0, 0, 0 };
	// protected int[] stringHeight = { 0, 0, 0, 0, 0, 0 };
	protected int stringWidth = 0;
	protected int stringHeight = 0;
	protected int[] stringStartX = { 0, 0, 0, 0, 0, 0 };
	protected int[] stringStartY = { 0, 0, 0, 0, 0, 0 };

	protected int buttonWidth = 0;
	protected int buttonHeight = 0;
	protected int[] buttonStartX = { 0, 0, 0, 0, 0, 0, 0, 0 };
	protected int[] buttonStartY = { 0, 0, 0, 0, 0, 0, 0, 0 };

	protected float mPosX, mPosY, mLastTouchX, mLastTouchY;
	protected int mActivePointerId;

	protected boolean[] isStringTouched = { false, false, false, false, false,
			false };

	protected Context context;
	protected SoundPool soundPool;
	protected static int[] rawResIds = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	
	boolean ScaleSelectFlag = false;
	boolean isDialogDismiss;

	protected static int curString;

	protected int[][] soundCoordinates;
	protected int instrument_type;

	protected boolean[] mute = { false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false };

	protected boolean[] loading = new boolean[6];


	int clickedButtonIndex;
	int longClickedButtonIndex;

	
	String[] scaleLibrary = { "", "", "", "", "", ""};

	ProgressDialog dialog;
	ScaleData Scale;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	// 각각 레이아웃 적용하기 위해 onCreate 만 super.onCreate() 안하고 다함

	setContentView(R.layout.activity_main);
	this.context = this;

	getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

	soundCoordinates = soundcoordinates.ACOUSTIC_RESOURCE_TABLE;

	// Dialog Starts
	SharedPreferences pref = getSharedPreferences(PREFERENCE_NAME,
			MODE_PRIVATE);
	isDialogDismiss = pref.getBoolean("isDialogDismiss", false);
	if (!isDialogDismiss) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(
				"You can change presetted Scale by Long Click the Scale Button")
				.setCancelable(false)
				.setPositiveButton("Dismiss",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								isDialogDismiss = true;
								SharedPreferences pref = getSharedPreferences(
										PREFERENCE_NAME, MODE_PRIVATE);
								SharedPreferences.Editor edit = pref.edit();
								edit.putBoolean("isDialogDismiss",
										isDialogDismiss);
								edit.commit();
								dialog.cancel();
							}
						})
				.setNegativeButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();
		alert.setTitle("Tip");
		alert.show();

	}
	// Dialog Ends

	// soundPool = new SoundPool(12, AudioManager.STREAM_MUSIC, 0);
	//
	// readPreference();
	// setScale();

	mStringLinear[0] = (LinearLayout) findViewById(R.id.Scale_library_6th_string_linear);
	mStringLinear[1] = (LinearLayout) findViewById(R.id.Scale_library_5th_string_linear);
	mStringLinear[2] = (LinearLayout) findViewById(R.id.Scale_library_4th_string_linear);
	mStringLinear[3] = (LinearLayout) findViewById(R.id.Scale_library_3rd_string_linear);
	mStringLinear[4] = (LinearLayout) findViewById(R.id.Scale_library_2nd_string_linear);
	mStringLinear[5] = (LinearLayout) findViewById(R.id.Scale_library_1st_string_linear);

	mStringImage[0] = (ImageView) findViewById(R.id.Scale_library_6th_string_img);
	mStringImage[1] = (ImageView) findViewById(R.id.Scale_library_5th_string_img);
	mStringImage[2] = (ImageView) findViewById(R.id.Scale_library_4th_string_img);
	mStringImage[3] = (ImageView) findViewById(R.id.Scale_library_3rd_string_img);
	mStringImage[4] = (ImageView) findViewById(R.id.Scale_library_2nd_string_img);
	mStringImage[5] = (ImageView) findViewById(R.id.Scale_library_1st_string_img);

	mStringImage[0].setImageResource(R.drawable.acoustic_6);
	mStringImage[1].setImageResource(R.drawable.acoustic_5);
	mStringImage[2].setImageResource(R.drawable.acoustic_4);
	mStringImage[3].setImageResource(R.drawable.acoustic_3);
	mStringImage[4].setImageResource(R.drawable.acoustic_2);
	mStringImage[5].setImageResource(R.drawable.acoustic_1);


}

/*
//계이름 신호 선택
@Override
protected void onActivityResult(int requestScale, int resultCode, Intent data) {
	super.onActivityResult(requestScale, resultCode, data);
	if (requestScale == REQUEST_Scale_LIBRARY) {
		if (resultCode == RESULT_OK) {
			String resultStr = data.getStringExtra(ScaleSelector.RESULT);
			scaleLibrary[longClickedButtonIndex] = resultStr;
			button[longClickedButtonIndex].setChecked(true);
			button[longClickedButtonIndex]
					.setText(scaleLibrary[longClickedButtonIndex]);
			clickedButtonIndex = longClickedButtonIndex;

			Scale = ScaleData
					.getScale(scaleLibrary[longClickedButtonIndex]);

			int tempIndexCount = longClickedButtonIndex * 6;
			for (int i = 0; i < 6; i++) {
				if (Scale.WIRE[i] != -1) {
					soundPool.unload(rawResIds[tempIndexCount + i]);
					rawResIds[tempIndexCount + i] = soundPool.load(context,
							soundCoordinates[i][Scale.WIRE[i]], 1);
					mute[tempIndexCount + i] = false;
				} else {
					mute[tempIndexCount + i] = true;
				}
			}

			for (int i = 0; i < 8; i++) {
				if (i != longClickedButtonIndex) {
					button[i].setChecked(false);
					button[i].setText(scaleLibrary[i]);
				} else {
				}
			}
		} else {
			button[clickedButtonIndex].setChecked(true);
			button[clickedButtonIndex]
					.setText(scaleLibrary[clickedButtonIndex]);
			for (int i = 0; i < 8; i++) {
				if (i != clickedButtonIndex) {
					button[i].setChecked(false);
					button[i].setText(scaleLibrary[i]);
				}
			}
		}
	}
}

	
void setScale() {

	ScaleData Scale;
	dialog = ProgressDialog.show(context, "", "Loading... Please wait...",
			true);
	LoadingThread lt = new LoadingThread();
	lt.start();

}

private class LoadingThread extends Thread {
	public void run() {
		for (int i = 0; i < 8; i++) {
			Scale = ScaleData.getScale(scaleLibrary[i]);
			int tempIndexCount = i * 6;
			for (int j = 0; j < 6; j++) {
				if (Scale.position[j] != -1) {
					// soundPool.unload(rawResIds[i]);

					rawResIds[tempIndexCount + j] = soundPool.load(context,
							soundCoordinates[j][Scale.WIRE[j]], 1);
					mute[tempIndexCount + j] = false;
				} else {
					mute[tempIndexCount + j] = true;
				}
			}
		}

		dialog.dismiss();
	}
}

@Override
protected void onDestroy() {
	if (soundPool != null) {
		soundPool.release();
		soundPool = null;
	}
	super.onDestroy();
}

@Override
protected void onPause() {
	writePreference();

	if (!ScaleSelectFlag) {
		if (soundPool != null) {
			soundPool.release();
			soundPool = null;
		}
	}

	super.onPause();

	// 마지막 코드 값도 저장
}

@Override
protected void onResume() {
	// onCreate에 있던거 복사//////////////////////////////////////////////////

	/*if (!ScaleSelectFlag) {
		readPreference();

		

		if (soundPool == null) {
			soundPool = new SoundPool(12, AudioManager.STREAM_MUSIC, 0);
			setScale();
		}

	} else {
		ScaleSelectFlag = false;
	}
	//블루투스로 부터 받은 플랫신호와 레이아웃의 기타줄을 터치하는 신호를 합성하여 소리낸다
	super.onResume();*/
	/*
	if(soundPool == null){
		soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);

		soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
			
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
				loading = false;
			}
		});
		
		Scale scale = ScaleData.getChord(String sca, String wir);
		
		loading = true;
		for(int i = 0; i < 6; i++){
			if(Scale.WIRE[i] != -1){
				rawResIds[i] = soundPool.load(context, soundcoordinates.ACOUSTIC_RESOURCE_TABLE[i][Scale.WIRE[i]], 1);
				mute[i] = false;
			}
			else{
				mute[i] = true;
			}
		}
	}
	super.onResume();
}
*/




protected int checkTouchArea(float x, float y) {

	for (int i = 0; i < 6; i++) {
		if (stringStartX[i] <= x && (x <= (stringStartX[i] + stringWidth)) && stringStartY[i] <= y && (y <= stringStartY[i] + stringHeight)) {
			return i;
		}
	}
	
	return -1;
}
@Override
public boolean onTouchEvent(MotionEvent event) {
	final int action = event.getAction();

	// 최초 터치점만 연주여부 true, false 주어서
	// move 때 계속 갱신되면 연주되지 않게 함
	// 따라서 멀티 터치점으로는 move 판별 안되게 함

	switch (action & MotionEvent.ACTION_MASK) {
	case MotionEvent.ACTION_DOWN: {
		final float x = event.getX();
		final float y = event.getY();
		mLastTouchX = x;
		mLastTouchX = y;

		// Pointer ID 저장
		mActivePointerId = event.getPointerId(0);
		int index = checkTouchArea(x, y);
		if (index != -1) {
			for (int i = 0; i < 6; i++) {
				if (i != index) {
					isStringTouched[i] = false;
				}
			}
			isStringTouched[index] = true;
			playString(index);
		}
		break;
	}
	case MotionEvent.ACTION_MOVE: {
		// 현재 활성화된 포인터의 인덱스를 찾아 위치를 받아온다.
		final int pointerIndex = event.findPointerIndex(mActivePointerId);
		final float x = event.getX(mActivePointerId);
		final float y = event.getY(mActivePointerId);
		final float dx = x - mLastTouchX;
		final float dy = y - mLastTouchY;

		mPosX += dx;
		mPosY += dy;

		mLastTouchX = x;
		mLastTouchY = y;

		int index = checkTouchArea(x, y);
		if (index != -1) {
			if (isStringTouched[index] == false) {
				for (int i = 0; i < 6; i++) {
					if (i != index) {
						isStringTouched[i] = false;
					}
				}
				isStringTouched[index] = true;
				playString(index);
			}
		}
		break;
	}

	case MotionEvent.ACTION_POINTER_DOWN: {
		// 남아있는 멀티터치 포인터의 인덱스를 추출한다
		final int pointerIndex = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
		final int pointerId = event.getPointerId(pointerIndex);

		final float x = event.getX(pointerId);
		final float y = event.getY(pointerId);
		mLastTouchX = x;
		mLastTouchX = y;

		int index = checkTouchArea(x, y);
		if (index != -1) {
			playString(index);
		}
		break;
	}

	case MotionEvent.ACTION_POINTER_UP: {
		final int pointerIndex = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
		final int pointerId = event.getPointerId(pointerIndex);

		final float x = event.getX(pointerId);
		final float y = event.getY(pointerId);

		Log.d("actionup", "pointerIndex = " + pointerIndex
				+ ", pointerId = " + pointerId + ", x = " + x + ", y = "
				+ y);

		break;
	}

	}

	return true;
}

protected int checkTouchAreaInScaleButton(float x, float y) {
	
	for(int i = 0; i < 8; i++){
		if (buttonStartX[i] <= x && (x <= (buttonStartX[i] + buttonWidth)) && buttonStartY[i] <= y && (y <= buttonStartY[i] + buttonHeight)) {
			return i;
		}
	}
	
	return -1;
}

protected void playString(int index) {
	soundPool.play(rawResIds[clickedButtonIndex * 6 + index], 1, 1, 0, 0, 1);
}



void writePreference() {
	SharedPreferences pref = getSharedPreferences(PREFERENCE_NAME,
			MODE_PRIVATE);
	SharedPreferences.Editor edit = pref.edit();

	edit.putString("Scale1", scaleLibrary[0]);
	edit.putString("Scale2", scaleLibrary[1]);
	edit.putString("Scale3", scaleLibrary[2]);
	edit.putString("Scale4", scaleLibrary[3]);
	edit.putString("Scale5", scaleLibrary[4]);
	edit.putString("Scale6", scaleLibrary[5]);

	edit.commit();
}

void readPreference() {
	SharedPreferences pref = getSharedPreferences(PREFERENCE_NAME,
			MODE_PRIVATE);
	scaleLibrary[0] = pref.getString("Scale1", "");
	scaleLibrary[1] = pref.getString("Scale2", "");
	scaleLibrary[2] = pref.getString("Scale3", "");
	scaleLibrary[3] = pref.getString("Scale4", "");
	scaleLibrary[4] = pref.getString("Scale5", "");
	scaleLibrary[5] = pref.getString("Scale6", "");
}
}