/*
 * BSD 2-Clause License
 *
 * Copyright (c) 2018, Ondrej Fischer
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package fluent.api.generator;

import java.time.ZonedDateTime;
import java.util.List;

public interface FixtureInterface {

    @GenerateFullBuilder(className = "FixtureBeanFullBuilder")
    FixtureBean BEAN = null;

    @GenerateParameterBuilder(methodName = "call")
    void myMethod(String first, String last, int age, ZonedDateTime birth, List<Double> list);

    @GenerateParameterBuilder(methodName = "send")
    Integer createName(String prefix, String suffix, int padding);

    @GenerateFullParameterBuilder
    String create(String first, String last, int age);

    @GenerateSender(methodName = "accept")
    @GenerateFullSender(className = "FixtureSender")
    void accept(@GenerateBuilder FixtureBean bean);

    @GenerateFullSender
    void generic(@GenerateBuilder(methodName = "pass") GenericFixture<String> genericValue);

    @GenerateSender(methodName = "genericSend")
    <T> void otherGeneric(GenericFixture<T> genericValue);

}
