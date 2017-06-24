package com.snake1999.myserver.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.snake1999.myserver.core.API.Definition.UNIVERSAL;
import static com.snake1999.myserver.core.API.Usage.BLEEDING;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@API(usage = BLEEDING, definition = UNIVERSAL)
public @interface API {

    Usage usage();

    Definition definition();

    enum Usage{
        INCUBATORY,

        BLEEDING,

        EXPERIMENTAL,

        MAINTAINED,

        STABLE
    }

    enum Definition{

        INTERNAL,

        PLATFORM_NATIVE,

        UNIVERSAL
    }
}
