package com.mapbox.navigation.ui.voice;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.mapbox.api.directions.v5.models.VoiceInstructions;

/**
 * Used to play {@link VoiceInstructions}s.
 * <p>
 * Takes a {@link SpeechPlayerProvider} which will provide either a {@link MapboxSpeechPlayer}
 * or {@link AndroidSpeechPlayer} based on the given language - if it is supported by our Voice API.
 * <p>
 * {@link MapboxSpeechPlayer} requires Internet connectivity.  In cases where a connection is not
 * available, the provider will fall back to the {@link AndroidSpeechPlayer}.
 *
 */
public class NavigationSpeechPlayer implements SpeechPlayer {

  private SpeechPlayerProvider speechPlayerProvider;
  private boolean isMuted;

  public NavigationSpeechPlayer(SpeechPlayerProvider speechPlayerProvider) {
    this.speechPlayerProvider = speechPlayerProvider;
  }

  /**
   * Plays the given {@link VoiceInstructions}.
   *
   * @param voiceInstructions with SSML and normal announcement text
   */
  @Override
  public void play(VoiceInstructions voiceInstructions) {
    speechPlayerProvider.retrieveSpeechPlayer().play(voiceInstructions);
  }

  /**
   * Returns the current muted state of the player.
   *
   * @return current muted state
   */
  @Override
  public boolean isMuted() {
    return isMuted;
  }

  /**
   * Mutes or un-mutes the {@link SpeechPlayer}.
   * <p>
   * If an announcement is playing at the time this method is called,
   * the announcement will be stopped immediately.
   *
   * @param isMuted true to mute, false to un-mute
   */
  @Override
  public void setMuted(boolean isMuted) {
    this.isMuted = isMuted;
    speechPlayerProvider.setMuted(isMuted);
  }

  /**
   * Optional method to implement in an {@link com.mapbox.services.android.navigation.v5.offroute.OffRouteListener}.
   * <p>
   * During an off-route scenario, you can use this method to cancel existing announcements without
   * completely muting the player.
   *
   */
  @Override
  public void onOffRoute() {
    speechPlayerProvider.onOffRoute();
  }

  /**
   * Required method to implement in {@link FragmentActivity#onDestroy()} or
   * {@link Fragment#onDestroy()}.
   * <p>
   * Ensures the player is properly shutdown and finishes any running announcements.
   *
   */
  @Override
  public void onDestroy() {
    speechPlayerProvider.onDestroy();
  }
}
