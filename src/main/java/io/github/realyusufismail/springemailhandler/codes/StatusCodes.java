/*
 * Copyright 2023 RealYusufIsmail
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
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
package io.github.realyusufismail.springemailhandler.codes;

import lombok.Getter;

@Getter
public enum StatusCodes {
	/**
	 * This is returned when the request is successful.
	 */
	SUCCESS(200),
	/**
	 * This is returned when the request body is invalid.
	 */
	BAD_REQUEST(400),
	/**
	 * This is returned when an error occurs in the server.
	 */
	INTERNAL_SERVER_ERROR(500),
	/**
	 * This is returned when the email fails to send.
	 */
	EMAIL_SEND_FAILED(412),
	/**
	 * This is returned when the token is invalid.
	 */
	UNAUTHORIZED(401);

	private final int code;

	StatusCodes(int code) {
		this.code = code;
	}
}
