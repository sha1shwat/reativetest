package com.reactive.reactivespring.playground;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomError extends Throwable {

    String msg;
    public CustomError(Throwable e) {
    }
}
