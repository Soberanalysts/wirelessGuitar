package com.example.string;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class wireless extends Activity {
	protected static final String DEBUG_TAG = "Wireless";

	protected static final int INSTRUMENT_TYPE_ACOUTSTIC = 0;
	protected static final int INSTRUMENT_TYPE_ELECTRIC = 1;

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

	protected int[] stringWidth = { 0, 0, 0, 0, 0, 0 };
	protected int[] stringHeight = { 0, 0, 0, 0, 0, 0 };
	protected int[] stringStartX = { 0, 0, 0, 0, 0, 0 };
	protected int[] stringStartY = { 0, 0, 0, 0, 0, 0 };

	protected float mPosX, mPosY, mLastTouchX, mLastTouchY;
	protected int mActivePointerId;

	protected boolean[] isStringTouched = { false, false, false, false, false,
			false };

	protected Context context;
	protected SoundPool soundPool;
	protected static int[] rawResIds = { 0, 0, 0, 0, 0, 0 };
	protected static int curString;

	protected SharedPreferences pref;
	protected int[][] soundResourceTable;
	protected int instrument_type;

	protected boolean [] mute = new boolean[6];
	protected boolean [] loading = new boolean[6];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.context = getApplicationContext();
	}

	@Override
	protected void onPause() {
		if (soundPool != null) {
			soundPool.release();
			soundPool = null;
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		if (soundPool == null) {
			soundPool = new SoundPool(12, AudioManager.STREAM_MUSIC, 0);
		}
		super.onResume();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		final int action = event.getAction();

		// ���� ��ġ���� ���ֿ��� true, false �־
		// move �� ��� ���ŵǸ� ���ֵ��� �ʰ� ��
		// ���� ��Ƽ ��ġ�����δ� move �Ǻ� �ȵǰ� ��

		switch (action & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN: {
				final float x = event.getX();
				final float y = event.getY();
				mLastTouchX = x;
				mLastTouchX = y;
	
				// Pointer ID ����
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
				// ���� Ȱ��ȭ�� �������� �ε����� ã�� ��ġ�� �޾ƿ´�.
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
				// �����ִ� ��Ƽ��ġ �������� �ε����� �����Ѵ�
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
		}

		return true;
	}

	protected void playString(int index) {
		soundPool.play(rawResIds[index], 1, 1, 0, 0, 1);
	}

	protected int checkTouchArea(float x, float y) {

		for (int i = 0; i < 6; i++) {
			if (stringStartX[i] <= x && (x <= (stringStartX[i] + stringWidth[i])) && stringStartY[i] <= y && (y <= stringStartY[i] + stringHeight[i])) {
				return i;
			}
		}
		return -1;
	}

	// ���̾ƿ� width, height ������ ����
	/*@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		int [][][] coordinates = new int[6][2][2];
		int [] coordinate = new int[2];
		for (int i = 0; i < 6; i++) {
			mStringLinear[i].getLocationOnScreen(coordinate);
			stringWidth[i] = mStringLinear[i].getWidth();
			stringHeight[i] = mStringLinear[i].getHeight();
			stringStartX[i] = coordinate[0];
			stringStartY[i] = coordinate[1];
			mStringLinear[i].getLocationInWindow(coordinates[i][0]);
			mStringLinear[i].getLocationOnScreen(coordinates[i][1]);
		}

		super.onWindowFocusChanged(hasFocus);
	}
*/
}
