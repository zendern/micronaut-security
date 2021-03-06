/*
 * Copyright 2017-2020 original authors
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
package io.micronaut.security.utils.serverrequestcontextspec

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import io.reactivex.Flowable
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
class ServerRequestContextReactiveSpec extends Specification {

    @Shared @AutoCleanup EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, [
            'spec.name': 'ServerRequestContextReactiveSpec',
            'micronaut.security.enabled': true,
    ])

    @Shared @AutoCleanup RxHttpClient httpClient =
            embeddedServer.getApplicationContext().createBean(RxHttpClient.class, embeddedServer.URL)

    def "verifies ServerRequestContext.currentRequest() does not return null for reactive flows"() {
        expect:
        embeddedServer.applicationContext.containsBean(MyController)

        when:

        Flowable<Message> messages = httpClient.retrieve(HttpRequest.GET("/test/request-context/simple"), Message)

        then:
        messages

        when:
        Message message = messages.blockingFirst()

        then:
        message
        message.message == 'Sergio'

        when:
        messages = httpClient.retrieve(HttpRequest.GET("/test/request-context"), Message)

        then:
        messages

        when:
        message = messages.blockingFirst()

        then:
        message
        message.message == 'Sergio'
    }

    def "verify flowable with subscribe on"() {
        when:
        def messages = httpClient.retrieve(HttpRequest.GET("/test/request-context/flowable-subscribeon"), Message)

        then:
        messages

        when:
        def message = messages.blockingFirst()

        then:
        message
        message.message == 'Sergio'
    }

    def "verify flowable callable"() {
        when:
        def messages = httpClient.retrieve(HttpRequest.GET("/test/request-context/flowable-callable"), Message)

        then:
        messages

        when:
        def message = messages.blockingFirst()

        then:
        message
        message.message == 'Sergio'
    }

    def "verify flux"() {
        when:
        def messages = httpClient.retrieve(HttpRequest.GET("/test/request-context/flux"), Message)

        then:
        messages

        when:
        def message = messages.blockingFirst()

        then:
        message
        message.message == 'Sergio'
    }

    def "verify flux subscribe on"() {
        when:
        def messages = httpClient.retrieve(HttpRequest.GET("/test/request-context/flux-subscribeon"), Message)

        then:
        messages

        when:
        def message = messages.blockingFirst()

        then:
        message
        message.message == 'Sergio'
    }
}
