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

package com.aviary.android.feather.effects;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import android.view.LayoutInflater;
import android.view.View;

import com.aviary.android.feather.effects.AbstractPanel.ContentPanel;
import com.aviary.android.feather.library.content.ToolEntry;
import com.aviary.android.feather.library.services.IAviaryController;

abstract class AbstractContentPanel extends AbstractOptionPanel implements ContentPanel {

	protected OnContentReadyListener mContentReadyListener;
	protected View mDrawingPanel;
	protected ImageViewTouch mImageView;

	public AbstractContentPanel ( IAviaryController context, ToolEntry entry ) {
		super( context, entry );
	}

	@Override
	public final void setOnReadyListener( OnContentReadyListener listener ) {
		mContentReadyListener = listener;
	}

	@Override
	public final View getContentView( LayoutInflater inflater ) {
		mDrawingPanel = generateContentView( inflater );
		return mDrawingPanel;
	}

	@Override
	public final View getContentView() {
		return mDrawingPanel;
	}

	@Override
	protected void onDispose() {
		mContentReadyListener = null;
		super.onDispose();
	}

	@Override
	public void setEnabled( boolean value ) {
		super.setEnabled( value );
		getContentView().setEnabled( value );
	}

	/**
	 * Call this method when your tool is ready to display its overlay.
	 * After this call the main context will remove the main image
	 * and will replace it with the content of this panel
	 */
	protected void contentReady() {
		if ( mContentReadyListener != null && isActive() ) mContentReadyListener.onReady( this );
	}

	protected abstract View generateContentView( LayoutInflater inflater );

	@Override
	public boolean isRendering() {
		// assume that a content panel is always in rendering mode
		return true;
	}
}
