package com.mycompany.myapp.config.audit;

import com.mycompany.myapp.config.Constants;
import com.mycompany.myapp.security.SecurityUtils;
import org.javers.spring.auditable.AuthorProvider;
import org.springframework.stereotype.Component;

@Component
public class JaversAuthorProvider implements AuthorProvider {

    @Override
    public String provide() {
        return SecurityUtils.getCurrentUserLogin().orElse(Constants.SYSTEM);
    }
}
