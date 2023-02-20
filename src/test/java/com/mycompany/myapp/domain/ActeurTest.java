package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ActeurTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Acteur.class);
        Acteur acteur1 = new Acteur();
        acteur1.setId(1L);
        Acteur acteur2 = new Acteur();
        acteur2.setId(acteur1.getId());
        assertThat(acteur1).isEqualTo(acteur2);
        acteur2.setId(2L);
        assertThat(acteur1).isNotEqualTo(acteur2);
        acteur1.setId(null);
        assertThat(acteur1).isNotEqualTo(acteur2);
    }
}
