package com.alura.foro.api.domain.user.validators.update;

import com.alura.foro.api.domain.user.UpdateUserDTO;

public interface UpdateUserValidator {
    public void validate(UpdateUserDTO data);
}
