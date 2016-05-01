/*
Simple DirectMedia Layer
Java source code (C) 2009-2014 Sergii Pylypenko

This software is provided 'as-is', without any express or implied
warranty.  In no event will the authors be held liable for any damages
arising from the use of this software.

Permission is granted to anyone to use this software for any purpose,
including commercial applications, and to alter it and redistribute it
freely, subject to the following restrictions:

1. The origin of this software must not be misrepresented; you must not
   claim that you wrote the original software. If you use this software
   in a product, an acknowledgment in the product documentation would be
   appreciated but is not required. 
2. Altered source versions must be plainly marked as such, and must not be
   misrepresented as being the original software.
3. This notice may not be removed or altered from any source distribution.
*/

package net.sourceforge.clonekeenplus;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.EditText;
import android.text.Editable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.graphics.drawable.Drawable;
import android.graphics.Color;
import android.content.res.Configuration;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View.OnKeyListener;
import android.view.MenuItem;
import android.view.Menu;
import android.view.Gravity;
import android.text.method.TextKeyListener;
import java.util.LinkedList;
import java.io.SequenceInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.CheckedInputStream;
import java.util.zip.CRC32;
import java.util.Set;
import android.text.SpannedString;
import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import android.view.inputmethod.InputMethodManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import java.util.concurrent.Semaphore;
import android.content.pm.ActivityInfo;
import android.view.Display;
import android.util.DisplayMetrics;
import android.text.InputType;
import android.util.Log;
import android.view.Surface;
import android.app.ProgressDialog;
import android.app.KeyguardManager;
import android.view.ViewTreeObserver;
import android.graphics.Rect;
import android.view.InputDevice;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.Keyboard;
import android.app.Notification;
import android.app.PendingIntent;
import android.widget.RemoteViews;

public class DummyService extends Service
{
	public DummyService()
	{
		super();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		if (intent != null && Intent.ACTION_DELETE.equals(intent.getAction()))
		{
			Log.v("SDL", "User dismissed notification, killing myself");
			stopSelfResult(5);
			stopSelfResult(0);
			System.exit(0);
		}
		Log.v("SDL", "Starting dummy service - displaying notification");
		Notification ntf = new Notification();
		ntf.icon = R.drawable.icon;
		ntf.flags |= Notification.FLAG_NO_CLEAR;
		PendingIntent killIntent = PendingIntent.getService(this, 5, new Intent(Intent.ACTION_DELETE, null, this, DummyService.class), PendingIntent.FLAG_CANCEL_CURRENT);
		PendingIntent showIntent = PendingIntent.getActivity(this, 0, new Intent("", null, this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);
		ntf.deleteIntent = killIntent;
		ntf.tickerText = getString(getApplicationInfo().labelRes);
		RemoteViews view = new RemoteViews(getPackageName(), R.layout.notification);
		view.setCharSequence(R.id.notificationText, "setText", getString(R.string.notification_app_is_running, getString(getApplicationInfo().labelRes)));
		view.setOnClickPendingIntent(R.id.notificationText, showIntent);
		view.setOnClickPendingIntent(R.id.notificationIcon, showIntent);
		view.setOnClickPendingIntent(R.id.notificationView, showIntent);
		view.setOnClickPendingIntent(R.id.notificationStop, killIntent);
		ntf.contentView = view;
		startForeground(1, ntf);
		return Service.START_NOT_STICKY;
	}
	@Override
	public void onDestroy()
	{
	}
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
}
