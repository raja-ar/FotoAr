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

public class SimpleStatusMachine {

	public static int INVALID_STATUS = -1;

	private int currentStatus = INVALID_STATUS;
	private int previousStatus = INVALID_STATUS;

	private OnStatusChangeListener mStatusListener;

	public void setOnStatusChangeListener( OnStatusChangeListener listener ) {
		mStatusListener = listener;
	}

	public void setStatus( int newStatus ) {
		if ( newStatus != currentStatus ) {
			previousStatus = currentStatus;
			currentStatus = newStatus;

			if ( null != mStatusListener ) {
				mStatusListener.OnStatusChanged( previousStatus, currentStatus );
			}
		} else {
			if ( null != mStatusListener ) {
				mStatusListener.OnStatusUpdated( newStatus );
			}
		}
	}

	public int getCurrentStatus() {
		return currentStatus;
	}

	public static interface OnStatusChangeListener {

		public void OnStatusChanged( int oldStatus, int newStatus );

		public void OnStatusUpdated( int status );
	}
}
