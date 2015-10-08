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
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.aviary.android.feather.R;
import com.aviary.android.feather.graphics.GalleryTopIndicatorDrawable;

public class AviaryGalleryTopIndicatorView extends LinearLayout {

	public AviaryGalleryTopIndicatorView ( Context context ) {
		this( context, null );
	}

	public AviaryGalleryTopIndicatorView ( Context context, AttributeSet attrs ) {
		this( context, attrs, R.attr.aviaryGalleryTopIndicatorStyle );
	}

	@SuppressWarnings ( "deprecation" )
	public AviaryGalleryTopIndicatorView ( Context context, AttributeSet attrs, int defStyle ) {
		super( context, attrs );

		Theme theme = context.getTheme();

		TypedArray array = theme.obtainStyledAttributes( attrs, R.styleable.AviaryGalleryIndicatorView, defStyle, 0 );
		int resId = array.getResourceId( R.styleable.AviaryGalleryIndicatorView_aviary_drawableStyle, 0 );
		array.recycle();

		if ( resId != 0 ) {
			setBackgroundDrawable( new GalleryTopIndicatorDrawable( context, 0, resId ) );
		} else {
			setBackgroundDrawable( new GalleryTopIndicatorDrawable( context ) );
		}

	}

}
