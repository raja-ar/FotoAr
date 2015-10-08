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
import java.util.HashMap;

import android.content.res.AssetManager;
import android.graphics.Typeface;

public class TypefaceUtils {

	private static final HashMap<String, SoftReference<Typeface>> sTypeCache = new HashMap<String, SoftReference<Typeface>>();

	public static Typeface createFromAsset( final AssetManager assets, final String fontname ) {
		Typeface result = null;
		SoftReference<Typeface> cachedFont = getFromCache( fontname );

		if ( null != cachedFont && cachedFont.get() != null ) {
			result = cachedFont.get();
		} else {
			result = Typeface.createFromAsset( assets, fontname );
			putIntoCache( fontname, result );
		}

		return result;
	}

	private static SoftReference<Typeface> getFromCache( final String fontname ) {
		synchronized ( sTypeCache ) {
			return sTypeCache.get( fontname );
		}
	}

	private static void putIntoCache( final String fontname, final Typeface font ) {
		synchronized ( sTypeCache ) {
			sTypeCache.put( fontname, new SoftReference<Typeface>( font ) );
		}
	}
}
