/*
 * Copyright 2014 Yubico.
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Use of this source code is governed by a BSD-style
 * license that can be found in the LICENSE file or at
 * https://developers.google.com/open-source/licenses/bsd
 */

package com.yubico.u2f.server.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

public class SignRequest {
  /**
   * Version of the protocol that the to-be-registered U2F token must speak. For
   * the version of the protocol described herein, must be "U2F_V2"
   */
  @JsonProperty
  private final String version;

  /** The websafe-base64-encoded challenge. */
  @JsonProperty
  private final String challenge;

  /**
   * The application id that the RP would like to assert. The U2F token will
   * enforce that the key handle provided above is associated with this
   * application id. The browser enforces that the calling origin belongs to the
   * application identified by the application id.
   */
  @JsonProperty
  private final String appId;

  /**
   * websafe-base64 encoding of the key handle obtained from the U2F token
   * during registration.
   */
  @JsonProperty
  private final String keyHandle;

  /**
   * A session id created by the RP. The RP can opaquely store things like
   * expiration times for the sign-in session, protocol version used, public key
   * expected to sign the identity assertion, etc. The response from the API
   * will include the sessionId. This allows the RP to fire off multiple signing
   * requests, and associate the responses with the correct request
   */
  @JsonProperty
  private final String sessionId;

  public SignRequest(String version, String challenge, String appId, String keyHandle,
      String sessionId) {
    super();
    this.version = version;
    this.challenge = challenge;
    this.appId = appId;
    this.keyHandle = keyHandle;
    this.sessionId = sessionId;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(version, challenge, appId, keyHandle, sessionId);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SignRequest other = (SignRequest) obj;
    if (appId == null) {
      if (other.appId != null)
        return false;
    } else if (!appId.equals(other.appId))
      return false;
    if (challenge == null) {
      if (other.challenge != null)
        return false;
    } else if (!challenge.equals(other.challenge))
      return false;
    if (keyHandle == null) {
      if (other.keyHandle != null)
        return false;
    } else if (!keyHandle.equals(other.keyHandle))
      return false;
    if (sessionId == null) {
      if (other.sessionId != null)
        return false;
    } else if (!sessionId.equals(other.sessionId))
      return false;
    if (version == null) {
      if (other.version != null)
        return false;
    } else if (!version.equals(other.version))
      return false;
    return true;
  }
}