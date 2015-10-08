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

import java.lang.ref.SoftReference;
import java.util.concurrent.Callable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.aviary.android.feather.R;
import com.aviary.android.feather.cds.AviaryCds.PackType;
import com.aviary.android.feather.library.utils.BitmapUtils;
import com.squareup.picasso.Transformation;

public class PackIconCallable implements Transformation, Callable<Bitmap> {

	final String imagePath;
	final PackType packType;
	final SoftReference<Resources> resourcesRef;
	
	int fallbackResId = -1;
	int maxSize = -1;

	public PackIconCallable ( Resources resources, PackType packType, String imagePath ) {
		this.imagePath = imagePath;
		this.packType = packType;
		this.resourcesRef = new SoftReference<Resources>( resources );
	}
	
	public PackIconCallable ( Resources resources, PackType packType, String imagePath, int fallbackResId, int maxSize ) {
		this( resources, packType, imagePath );
		this.fallbackResId = fallbackResId;
		this.maxSize = maxSize;
	}

	@Override
	public Bitmap call() throws Exception {

		Bitmap result = null;
		Bitmap bitmap = null;
		final Resources resources = resourcesRef.get();

		if ( null == resources ) {
			return null;
		}

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Config.ARGB_8888;

		if ( null != imagePath && imagePath.length() > 0 ) {
			result = BitmapFactory.decodeFile( imagePath, options );
		}

		if ( null == result ) {
			result = BitmapFactory.decodeResource( resources, fallbackResId );
		}
		
		result = transform( result );

		if ( maxSize > 0 && null != result ) {
			bitmap = BitmapUtils.resizeBitmap( result, maxSize, maxSize );
			if ( null != bitmap && result != bitmap ) {
				result.recycle();
				result = bitmap;
			}
		}

		return result;
	}

	public static Bitmap generate( Resources res, Bitmap icon, PackType packType, int maxSize ) {
		Bitmap result = generate( res, icon, packType );
		Bitmap resized = BitmapUtils.resizeBitmap( result, maxSize, maxSize );
		
		if( resized != null && resized != result ) {
			if( result != icon ) {
				result.recycle();
			}
		}
		return resized;
	}
	
	public static Bitmap generate( Resources res, Bitmap icon, PackType packType ) {
		Bitmap background;
		
		if( res == null ) return icon;
		
		if ( PackType.EFFECT.equals( packType ) ) {
			background = BitmapFactory.decodeResource( res, R.drawable.aviary_effects_pack_background );
			if ( null != background ) {
				Bitmap newBitmap = BitmapUtils.roundedCorners( icon, 10, 10 );
				Bitmap result = BitmapUtils.flattenDrawables( new BitmapDrawable( res, background ), new BitmapDrawable( res, newBitmap ), 0.76f, 0f );
				
				if( null != result && !newBitmap.equals( result ) ) {
					newBitmap.recycle();
					newBitmap = null;
				}
				
				return result;
			}
		} else if ( PackType.STICKER.equals( packType ) ) {
			background = BitmapFactory.decodeResource( res, R.drawable.aviary_sticker_pack_background );
			if ( null != background ) {
				return BitmapUtils.flattenDrawables( new BitmapDrawable( res, background ), new BitmapDrawable( res, icon ), 0.58f, 0.05f );
			}
		}
		return icon;
	}

	@Override
	public String key() {
		return null;
	}

	@Override
	public Bitmap transform( Bitmap bitmap ) {

		final Resources resources = resourcesRef.get();

		if ( null == resources ) {
			return null;
		}

		// packtype
		if ( null != bitmap ) {
			Bitmap result = generate( resources, bitmap, packType );

			if ( null != result && result != bitmap ) {
				bitmap.recycle();
				bitmap = result;
			}
		}
		
		return bitmap;
	}
}
