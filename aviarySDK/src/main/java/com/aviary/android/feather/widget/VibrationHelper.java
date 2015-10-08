/*
 *   Copyright 2015 Azmeer Raja
 *
 *         Licensed under the Apache License, Version 2.0 (the "License");
 *         you may not use this file except in compliance with the License.
 *         You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *         See the License for the specific language governing permissions and
 *         limitations under the License.
 */

package com.aviary.android.feather.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;

import com.aviary.android.feather.common.log.LoggerFactory;

public class VibrationHelper {

	private static final String LOG_TAG = "VibrationHelper";

	private static final int MSG_VIBRATE = 101;

	private boolean mEnabled;
	private static Vibrator mVibrator;
	private VibrationHandler mHandler = new VibrationHandler();

	public VibrationHelper ( Context context, boolean enabled ) {
		initialize( context );
		setEnabled( enabled );

		if ( !isAvailable() ) {
			setEnabled( false );
		}
	}

	/**
	 * Returns if the vibration is currently available on
	 * the current device
	 * 
	 * @return
	 */
	public boolean isAvailable() {
		if ( null != mVibrator ) {
			if ( android.os.Build.VERSION.SDK_INT >= 11 ) {
				return mVibrator.hasVibrator();
			} else {
				return true;
			}
		}
		return false;
	}

	private void initialize( Context context ) {
		synchronized ( VibrationHelper.class ) {
			if ( null == mVibrator ) {
				try {
					mVibrator = (Vibrator) context.getSystemService( Context.VIBRATOR_SERVICE );
				} catch ( Exception e ) {
					Log.e( LOG_TAG, e.toString() );
				}
			}
		}
	}

	public void setEnabled( boolean value ) {
		if ( LoggerFactory.LOG_ENABLED ) {
			Log.i( LOG_TAG, "setEnabled: " + value );
		}
		mEnabled = value && ( mVibrator != null );
	}

	public boolean enabled() {
		return mEnabled;
	}

	public void vibrate( int milliseconds ) {
		if ( mEnabled ) {
			if ( null != mHandler ) {
				mHandler.removeMessages( MSG_VIBRATE );

				Message msg = mHandler.obtainMessage( MSG_VIBRATE );
				msg.arg1 = milliseconds;

				mHandler.sendMessage( msg );
			}
		}
	}

	static class VibrationHandler extends Handler {

		@Override
		public void handleMessage( Message msg ) {
			switch ( msg.what ) {
				case MSG_VIBRATE:
					if ( mVibrator != null ) {
						try {
							mVibrator.vibrate( msg.arg1 );
						} catch ( Throwable t ) {
							if ( LoggerFactory.LOG_ENABLED ) {
								t.printStackTrace();
							}
						}
					}
			}
		}
	}
}
