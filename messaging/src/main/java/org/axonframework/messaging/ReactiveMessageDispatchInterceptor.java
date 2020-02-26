/*
 * Copyright (c) 2010-2020. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.axonframework.messaging;

import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Interceptor that allows messages to be intercepted and modified before they are dispatched. Implementations are
 * required to provide a function that modifies a {@link Mono} of a message and returns a modified/new {@link Mono} to
 * be passed down the interceptor chain or to be sent. This way we provide a reactive way to intercept messages.
 *
 * @param <T> the message type this interceptor can process
 * @author Milan Savic
 * @since 4.4
 */
public interface ReactiveMessageDispatchInterceptor<T extends Message<?>> extends Supplier<Function<Mono<T>, Mono<T>>> {

    /**
     * Intercepts a message.
     *
     * @param message a {@link Mono} of a message to be intercepted
     * @return the message {@link Mono} to dispatch
     */
    default Mono<T> intercept(Mono<T> message) {
        return get().apply(message);
    }
}