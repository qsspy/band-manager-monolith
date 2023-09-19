package com.qsspy.authservice.infrastructure.adapter.controller;


record RegisterRequestBody(
        String email,
        String password,
        String firstName,
        String lastName
) { }
