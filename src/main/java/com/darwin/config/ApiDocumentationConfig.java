package com.darwin.config;

import io.swagger.annotations.*;

@SwaggerDefinition(
        info = @Info(
                description = "Simple Employee API Using SpringBoot",
                version = "V1",
                title = "Simple Employee API",
                contact = @Contact(
                        name = "Lakshmi Majeti",
                        email = "lakshmi.majeti21@gmail.com",
                        url = "https://spring.io/"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json"},
        produces = {"application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        externalDocs = @ExternalDocs(value = "Read This", url = "https://spring.io/")
)
public interface ApiDocumentationConfig {

}
