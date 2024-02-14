package com.alura.foro.api.domain.user.validators.create;

import com.alura.foro.api.domain.user.CreateUserDTO;

public interface CreateUserValidator {
    public void validate(CreateUserDTO data);
}
