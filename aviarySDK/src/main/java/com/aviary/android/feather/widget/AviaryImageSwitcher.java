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

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ViewSwitcher;

public class AviaryImageSwitcher extends ViewSwitcher {

	protected boolean mSwitchEnabled = true;

	public AviaryImageSwitcher ( Context context ) {
		super( context );
	}

	public AviaryImageSwitcher ( Context context, AttributeSet attrs ) {
		super( context, attrs );
	}

	public void setImageBitmap( Bitmap bitmap, Matrix matrix ) {
		ImageViewTouch image = null;

		if ( mSwitchEnabled ) image = (ImageViewTouch) this.getNextView();
		else image = (ImageViewTouch) this.getChildAt( 0 );

		image.setImageBitmap( bitmap, matrix, ImageViewTouchBase.ZOOM_INVALID, ImageViewTouchBase.ZOOM_INVALID );

		if ( mSwitchEnabled ) showNext();
		else setDisplayedChild( 0 );
	}

	public void setImageDrawable( Drawable drawable, Matrix matrix ) {
		ImageViewTouch image = null;

		if ( mSwitchEnabled ) image = (ImageViewTouch) this.getNextView();
		else image = (ImageViewTouch) this.getChildAt( 0 );

		image.setImageDrawable( drawable, matrix, ImageViewTouchBase.ZOOM_INVALID, ImageViewTouchBase.ZOOM_INVALID );

		if ( mSwitchEnabled ) showNext();
		else setDisplayedChild( 0 );
	}

	public void setSwitchEnabled( boolean enable ) {
		mSwitchEnabled = enable;
	}
}
