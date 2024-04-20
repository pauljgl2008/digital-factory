package com.scotiabank.digital.factory.domain.ports.out;

import com.scotiabank.digital.factory.domain.model.Student;

public interface InsertStudentOutputPort {

    void insert(Student student);
}
