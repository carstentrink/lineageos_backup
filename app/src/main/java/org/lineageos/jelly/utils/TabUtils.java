/*
 * Copyright (C) 2018 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lineageos.jelly.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import org.lineageos.jelly.MainActivity;
import org.lineageos.jelly.R;

import java.util.List;

public final class TabUtils {
    public static void openInNewTab(Context context, String url, boolean incognito) {
        Intent intent = new Intent(context, MainActivity.class);
        if (url != null && !url.isEmpty()) {
            intent.setData(Uri.parse(url));
        }
        intent.setAction(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        intent.putExtra(IntentUtils.EXTRA_INCOGNITO, incognito);
        context.startActivity(intent);
    }
    public static void killAll(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am != null) {
            List<ActivityManager.AppTask> tasks = am.getAppTasks();
            if (tasks != null && tasks.size() > 0) {
                for (int i = 1; i < tasks.size(); i++) {
                    tasks.get(i).setExcludeFromRecents(true);
                    tasks.get(i).finishAndRemoveTask();
                }
                tasks.get(0).setExcludeFromRecents(true);
                tasks.get(0).finishAndRemoveTask();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
