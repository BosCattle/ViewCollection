/*
 * Copyright 2015 Google Inc.
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

package com.kevin.library.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.transition.Slide;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Helper class for creating content transitions used with {@link android.app.ActivityOptions}.
 */
public class TransitionHelper {

  /**
   * Create the transition participants required during a activity transition while
   * avoiding glitches with the system UI.
   *
   * @param activity The activity used as start for the transition.
   * @param includeStatusBar If false, the status bar will not be added as the transition
   * participant.
   * @return All transition participants.
   */
  public static Pair<View, String>[] createSafeTransitionParticipants(@NonNull Activity activity,
                                                                      boolean includeStatusBar, @Nullable Pair... otherParticipants) {
    // Avoid system UI glitches as described here:
    // https://plus.google.com/+AlexLockwood/posts/RPtwZ5nNebb
    View decor = activity.getWindow().getDecorView();
    View statusBar = null;
    if (includeStatusBar) {
      statusBar = decor.findViewById(android.R.id.statusBarBackground);
    }
    View navBar = decor.findViewById(android.R.id.navigationBarBackground);

    // Create pair of transition participants.
    List<Pair> participants = new ArrayList<>(3);
    addNonNullViewToTransitionParticipants(statusBar, participants);
    addNonNullViewToTransitionParticipants(navBar, participants);
    // only add transition participants if there's at least one none-null element
    if (otherParticipants != null && !(otherParticipants.length == 1
        && otherParticipants[0] == null)) {
      participants.addAll(Arrays.asList(otherParticipants));
    }
    return participants.toArray(new Pair[participants.size()]);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  private static void addNonNullViewToTransitionParticipants(View view, List<Pair> participants) {
    if (view == null) {
      return;
    }
    participants.add(new Pair<>(view, view.getTransitionName()));
  }

  /**
   * @param context Context
   * @param time long
   * @param location int
   */
  @TargetApi(Build.VERSION_CODES.LOLLIPOP) public static void setupWindowAnimations(
          @NonNull Context context, @NonNull long time, @NonNull int location) {
    // Re-enter transition is executed when returning to this activity
    Activity activity = (Activity) context;
    Slide slideTransition = new Slide();
    slideTransition.setSlideEdge(location);
    slideTransition.setDuration(time);
    activity.getWindow().setReenterTransition(slideTransition);
    activity.getWindow().setExitTransition(slideTransition);
  }

  /**
   *
   * @param target
   * @param activity
   */
  public static void transitionToActivity(Class target, Activity activity) {
    final Pair<View, String>[] pairs =
        TransitionHelper.createSafeTransitionParticipants(activity, true);
    startActivity(target, pairs, activity);
  }

  /**
   *
   * @param target
   * @param pairs
   * @param activity
   */
  @TargetApi(Build.VERSION_CODES.JELLY_BEAN) private static void startActivity(Class target,
                                                                               Pair<View, String>[] pairs, Activity activity) {
    Intent i = new Intent(activity, target);
    ActivityOptionsCompat transitionActivityOptions =
        ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
    activity.startActivity(i, transitionActivityOptions.toBundle());
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public static void transitionToActivity(Class target, View view, Activity activity) {
    final Pair<View, String>[] pairs =
        TransitionHelper.createSafeTransitionParticipants(activity, false,
            new Pair<>(view, "share"));
    startActivity(target, pairs, activity);
  }
}
