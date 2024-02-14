package com.alura.foro.api.domain.answer.validators.update;

import com.alura.foro.api.domain.answer.UpdateAnswerDTO;

public interface UpdateAnswerValidator {
    public void validate(UpdateAnswerDTO data, Long answerId);
}
