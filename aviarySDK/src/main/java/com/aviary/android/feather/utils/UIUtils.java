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

package com.aviary.android.feather.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.aviary.android.feather.R;
import com.aviary.android.feather.widget.AviaryToast;

public class UIUtils {

	public static final int HIGHLIGHT_MODE_PRESSED = 2;
	public static final int HIGHLIGHT_MODE_CHECKED = 4;
	public static final int HIGHLIGHT_MODE_SELECTED = 8;

	public static final int GLOW_MODE_PRESSED = 2;
	public static final int GLOW_MODE_CHECKED = 4;
	public static final int GLOW_MODE_SELECTED = 8;

	public static boolean checkBits( int status, int checkBit ) {
		return ( status & checkBit ) == checkBit;
	}

	/**
	 * @see #makeCustomToast(Context, int)
	 */
	public static Toast makeCustomToast( Context context ) {
		return makeCustomToast( context, R.layout.aviary_toast_layout );
	}

	/**
	 * Creates a custom {@link Toast} with a custom layout View
	 * 
	 * @param context
	 *            the context
	 * @param resId
	 *            the custom view
	 * @return the created {@link Toast}
	 */
	public static Toast makeCustomToast( Context context, int resId ) {
		View view = LayoutInflater.from( context ).inflate( resId, null );
		Toast t = new Toast( context );
		t.setDuration( Toast.LENGTH_SHORT );
		t.setView( view );
		t.setGravity( Gravity.CENTER, 0, 0 );
		return t;
	}

	/**
	 * Creates an {@link AviaryToast} with an animate progress drawable
	 * 
	 * @param context
	 * @return
	 */
	public static AviaryToast createModalLoaderToast( Context context ) {
		AviaryToast mToastLoader = AviaryToast.make( context, R.layout.aviary_modal_progress_view, -1 );
		return mToastLoader;
	}
}
