package com.scotiabank.digital.factory.domain.ports.in;

import com.scotiabank.digital.factory.domain.model.Student;

public interface InsertStudentInputPort {

    void insert(Student student);

}
