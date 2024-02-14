package com.alura.foro.api.domain.answer.validators.create;

import com.alura.foro.api.domain.answer.CreateAnswerDTO;

public interface CreateAnswerValidator {
    public void validate(CreateAnswerDTO data);
}
