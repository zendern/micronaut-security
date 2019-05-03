/*
 * Copyright 2017-2019 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.micronaut.security.oauth2.endpoint.authorization.request;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * OAuth 2.0 Authorization Request.
 *
 * @see <a href="https://openid.net/specs/openid-connect-core-1_0.html#AuthRequest">Authentication Request</a>
 */
public interface AuthorizationRequest {

    String PARAMETER_SCOPE = "scope";
    String PARAMETER_RESPONSE_TYPE = "response_type";
    String PARAMETER_CLIENT_ID = "client_id";
    String PARAMETER_REDIRECT_URI = "redirect_uri";
    String PARAMETER_STATE = "state";

    /**
     *
     * @return OAuth 2.0 scopes.
     */
    @Nonnull
    List<String> getScopes();

    /**
     *
     * @return OAuth 2.0 Client Identifier valid at the Authorization Server.
     */
    @Nonnull
    String getClientId();

    /**
     *
     * @return Opaque value used to maintain state between the request and the callback.
     */
    @Nullable
    String getState();

    /**
     * @return OAuth 2.0 Response Type value that determines the authorization processing flow to be used, including what parameters are returned from the endpoints used.
     */
    @Nonnull
    String getResponseType();

    /**
     * @return Redirection URI to which the response will be sent.
     */
    @Nullable
    String getRedirectUri();

}
