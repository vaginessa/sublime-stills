/*
 * Copyright {2017} {Aashrey Kamal Sharma}
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.aashreys.walls.utils;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

/**
 * A wrapper over Android's default logger which can be disabled for unit tests.
 * Created by aashreys on 11/02/17.
 */

public class LogWrapper {

    private static boolean isEnabled = true;

    private static boolean isProductionLogging = false;

    public static void setEnabled(boolean isEnabled) {
        LogWrapper.isEnabled = isEnabled;
    }

    public static void setProductionLogging(boolean isEnabled) {
        isProductionLogging = isEnabled;
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (isEnabled && !isProductionLogging) {
            Log.d(tag, msg, tr);
        }
    }

    public static void d(String tag, String msg) {
        if (isEnabled && !isProductionLogging) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isEnabled) {
            if (isProductionLogging) {
                logWithCrashlytics(tag, msg, null);
            } else {
                Log.e(tag, msg);
            }
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (isEnabled) {
            if (isProductionLogging) {
                logWithCrashlytics(tag, msg, tr);
            } else {
                Log.e(tag, msg, tr);
            }
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (isEnabled && !isProductionLogging) {
            Log.i(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (isEnabled && !isProductionLogging) {
            Log.i(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (isEnabled && !isProductionLogging) {
            Log.v(tag, msg, tr);
        }
    }

    public static void v(String tag, String msg) {
        if (isEnabled && !isProductionLogging) {
            Log.v(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (isEnabled && !isProductionLogging) {
            Log.w(tag, msg, tr);
        }
    }

    public static void w(String tag, Throwable tr) {
        if (isEnabled && !isProductionLogging) {
            Log.w(tag, tr);
        }
    }

    public static void w(String tag, String msg) {
        if (isEnabled && !isProductionLogging) {
            Log.w(tag, msg);
        }
    }

    public static void wtf(String tag, String msg, Throwable tr) {
        if (isEnabled) {
            Log.wtf(tag, msg, tr);
        }
    }

    public static void wtf(String tag, Throwable tr) {
        if (isEnabled) {
            Log.wtf(tag, tr);
        }
    }

    public static void wtf(String tag, String msg) {
        if (isEnabled) {
            Log.wtf(tag, msg);
        }
    }

    private static void logWithCrashlytics(String tag, String message, Throwable throwable) {
        Crashlytics.log(tag + ": " + message);
        if (throwable != null) {
            Crashlytics.logException(throwable);
        }
    }

}
